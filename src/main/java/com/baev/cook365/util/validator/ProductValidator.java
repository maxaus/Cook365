package com.baev.cook365.util.validator;

import javax.persistence.NoResultException;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.baev.cook365.model.Product;
import com.baev.cook365.service.ProductService;

/**
 * Валидатор для продуктов.
 *
 * @author Maxim Baev
 */
public class ProductValidator implements Validator {

	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Product.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		Product product = (Product) o;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "fill-in-the-name", "Надо заполнить");
		if (product.getId() == null) {
			try {
				Product productWithSameName = productService.findByName(product.getName());
				if (productWithSameName != null) {
					errors.rejectValue("name", "product-with-same-name-already-exists", "Продукт с таким именем уже есть!");
				}
			} catch (NoResultException e) {
				//ignore
			}
		}
	}
}
