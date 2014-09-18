package com.baev.cook365.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.baev.cook365.BasePersistenceTest;

/**
 * @author Maxim Baev
 */
public class UserDaoTest extends BasePersistenceTest {

	@Autowired
	private UserDao userDao;

	@Test
	public void testUserLifecycle() {
		System.out.println("f");
	}
}
