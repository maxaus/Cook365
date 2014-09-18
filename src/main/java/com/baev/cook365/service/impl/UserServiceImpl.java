package com.baev.cook365.service.impl;

import com.baev.cook365.dao.UserDao;
import com.baev.cook365.model.User;
import com.baev.cook365.model.UserRoles;
import com.baev.cook365.service.UserService;
import com.baev.cook365.util.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Реализация {@link UserService}.
 *
 * @author Maxim Baev
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public void save(User user) {
		userDao.save(user);
	}

	@Override
	@Transactional(readOnly = true)
	public User findUserByEmail(String email) {
		return userDao.findUserByEmail(email);
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) {
		User user = findUserByEmail(username);
		SessionManager.saveUserInSession(user);
		if (user != null) {
			String userRole = UserRoles.ANONYMOUS;
			if (user.getRole().equalsIgnoreCase(UserRoles.ADMIN)) {
				userRole = UserRoles.ADMIN;
			}

			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new GrantedAuthorityImpl(userRole));

			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), true, true, true, true,
					authorities);
		}
		return null;
	}
}
