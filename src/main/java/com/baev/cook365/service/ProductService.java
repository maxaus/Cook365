package com.baev.cook365.service;

import com.baev.cook365.model.Product;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Сервис для работы с продуктами.
 *
 * @author Maxim Baev
 */
public interface ProductService {

	void save(Product product);

	void update(Product product);

	void delete(Product product);

	Product findById(Long id);

	List<Product> findAll();

	Page<Product> findAllByPage(Pageable pageable);

	Product findByName(String name);
}
