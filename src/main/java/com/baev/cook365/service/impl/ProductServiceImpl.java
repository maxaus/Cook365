package com.baev.cook365.service.impl;

import com.baev.cook365.dao.ProductDao;
import com.baev.cook365.model.Product;
import com.baev.cook365.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Реализация {@link ProductService}.
 *
 * @author Maxim Baev
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
	@Transactional
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
	@Transactional
    public void update(Product product) {
        productDao.save(product);
    }

    @Override
	@Transactional
    public void delete(Product product) {
        productDao.delete(product);
    }

    @Override
	@Transactional(readOnly = true)
    public Product findById(Long id) {
        return productDao.findOne(id);
    }

    @Override
	@Transactional(readOnly = true)
    public List<Product> findAll() {
        return productDao.findAll();
    }

	@Override
	@Transactional(readOnly = true)
	public Page<Product> findAllByPage(Pageable pageable) {
		return productDao.findAll(pageable);
	}

    @Override
	@Transactional(readOnly = true)
    public Product findByName(String name) {
        return productDao.findByName(name);
    }
}
