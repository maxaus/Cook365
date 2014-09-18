package com.baev.cook365.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Пользователь.
 *
 * @author Maxim Baev
 */
@Entity
@Table(name = "user")
public class User extends AbstractEntity {

	private static final long serialVersionUID = -275456246419416086L;

	/**
	 * Имя.
	 */
	private String firstName;

	/**
	 * Фамилия.
	 */
	private String lastName;

	/**
	 * Адрес электронной почты.
	 */
	private String email;

	/**
	 * Пароль.
	 */
	private String password;

	/**
	 * Роль пользователя (администратор, гость и т.п.).
	 */
	private String role;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
