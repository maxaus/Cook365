package com.baev.cook365.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.baev.cook365.model.Product;
import org.springframework.stereotype.Repository;

/**
 * @author Maxim Baev
 */
@Repository("productDao")
public interface ProductDao extends JpaRepository<Product, Long> {

    Product findByName(String name);
}
