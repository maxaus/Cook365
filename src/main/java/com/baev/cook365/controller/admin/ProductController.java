package com.baev.cook365.controller.admin;

import com.baev.cook365.model.Product;
import com.baev.cook365.service.ProductService;
import com.baev.cook365.util.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Контроллер для администрирования продуктов.
 *
 * @author Maxim Baev
 */
@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	private static final int PAGE_SIZE = 20;

	@RequestMapping("/admin/products")
	public ModelAndView showProducts(@RequestParam(value = "page", defaultValue = "0") int pageNum) {
		Pageable pageable = new PageRequest(pageNum, PAGE_SIZE);
		Page<Product> page = productService.findAllByPage(pageable);
		ModelAndView model = new ModelAndView("admin/products");
		model.addObject("page", page);
		return model;

	}

	@RequestMapping(value = "/admin/edit_product", method = RequestMethod.GET)
	public ModelAndView showEditProductForm(@RequestParam("productId") String productIdStr) {
		Long productId = Long.valueOf(productIdStr);
		Product product = productService.findById(productId);

		return new ModelAndView("admin/edit_product", "product", product);
	}

	@RequestMapping(value = "/admin/add_product", method = RequestMethod.GET)
	public ModelAndView showAddProductForm() {
		return new ModelAndView("admin/edit_product", "product", new Product());
	}

	@RequestMapping(value = "/admin/edit_product", method = RequestMethod.POST)
	public ModelAndView editProduct(@ModelAttribute("product")
									Product product, BindingResult result) {
		ProductValidator validator = new ProductValidator();
		validator.setProductService(productService);
		validator.validate(product, result);
		if (result.hasErrors()) {
			return new ModelAndView("admin/edit_product");
		}
		if (product.getId() != null) {
			productService.update(product);
		} else {
			productService.save(product);
		}
		Pageable pageable = new PageRequest(0, PAGE_SIZE);
		Page<Product> page = productService.findAllByPage(pageable);
		ModelAndView model = new ModelAndView("admin/products");
		model.addObject("page", page);
		return model;
	}

	@RequestMapping(value = "/admin/delete_product", method = RequestMethod.GET)
	public ModelAndView deleteProduct(@RequestParam("productId") long productId) {

		Product product = productService.findById(productId);
		productService.delete(product);

		Pageable pageable = new PageRequest(0, PAGE_SIZE);
		Page<Product> page = productService.findAllByPage(pageable);
		ModelAndView model = new ModelAndView("admin/products");
		model.addObject("page", page);
		return model;
	}
}
