package com.baev.cook365.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.baev.cook365.model.User;
import org.springframework.stereotype.Repository;

/**
 * @author Maxim Baev
 */
@Repository("userDao")
public interface UserDao extends JpaRepository<User, Long> {

    /**
	 * @param email
	 * @return user with given email
	 */
	User findUserByEmail(String email);
}
