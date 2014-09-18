package com.baev.cook365;

import org.junit.Before;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionAttributeSourceAdvisor;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.transaction.support.SimpleTransactionStatus;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Maxim Baev
 */
public abstract class BaseMockitoTest {

	/**
	 * Method to wrap an object in a transaction {@link org.springframework.transaction.interceptor.TransactionInterceptor interceptor}.
	 *
	 * @param mock
	 * @param transactionManager
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T wrapInTransaction(T mock, PlatformTransactionManager transactionManager) {
		when(transactionManager.getTransaction(any(TransactionDefinition.class))).thenReturn(new SimpleTransactionStatus());

		final TransactionInterceptor interceptor = new TransactionInterceptor(transactionManager, new AnnotationTransactionAttributeSource());
		final ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(mock);
		proxyFactory.addAdvisor(new TransactionAttributeSourceAdvisor(interceptor));
		return (T) (proxyFactory.getProxy(this.getClass().getClassLoader()));
	}

	@Before
	public void setup() {
		initMocks(this);
	}
}
