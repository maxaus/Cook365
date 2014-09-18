package com.baev.cook365.util;

import com.baev.cook365.model.User;
import com.baev.cook365.model.UserRoles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Класс для сохранения пользователя в сессии.
 *
 * @author Maxim Baev
 */
public class SessionManager {

	public static enum Role {
		ADMIN,
		ANONYMOUS
	}

	private static final ThreadLocal<User> user = new ThreadLocal<User>();

	public static void saveUserInSession(User user) {
		SessionManager.user.set(user);
	}

	public static void removeUserFromSession() {
		SessionManager.user.remove();
	}

	public static UserDetails getUser() {
		//if not logged in getPrincipal() method will return string "anonymousUser"
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("roleAnonymous")) {
			return null;
		}
		return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
	}

	public static Role getUserRole() {
		UserDetails user = getUser();

		if (user == null) {
			return Role.ANONYMOUS;
		}

		for (GrantedAuthority authority : user.getAuthorities()) {
			if (authority.getAuthority().equals(UserRoles.ADMIN)) {
				return Role.ADMIN;
			}
		}

		return Role.ANONYMOUS;
	}
}