package com.baev.cook365.service.impl;

import com.baev.cook365.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Реализация {@link UserDetailsService}.
 *
 * @author Maxim Baev
 */
@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserService getUserService() {
		return userService;
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		return userService.loadUserByUsername(username);
	}
}
