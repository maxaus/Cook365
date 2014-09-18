package com.baev.cook365.service;

import org.springframework.security.core.userdetails.UserDetails;
import com.baev.cook365.model.User;

/**
 * Сервис для работы с пользователями.
 *
 * @author Maxim Baev
 */
public interface UserService {

	void save(User user);

	User findUserByEmail(String email);

	UserDetails loadUserByUsername(String username);
}
