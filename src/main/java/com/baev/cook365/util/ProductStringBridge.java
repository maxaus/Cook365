package com.baev.cook365.util;

import java.util.List;
import org.hibernate.search.bridge.StringBridge;
import com.baev.cook365.model.Product;

/**
 * Класс, определяющий представление продуктов в виде строки (для поиска).
 *
 * @author Maxim Baev
 */
public class ProductStringBridge implements StringBridge {
	@Override
	public String objectToString(Object param) {
		StringBuilder sb = new StringBuilder();
		List<Product> paramList = (List<Product>) param;
		for (Product entry : paramList) {
			sb.append(entry.getName());
			sb.append(" ");
		}
		return sb.toString();
	}
}
