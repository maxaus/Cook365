package com.baev.cook365;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * @author Maxim Baev
 */
@ContextConfiguration(locations = {
		"classpath*:META-INF/spring/test-appContext.xml"
})
@TransactionConfiguration(defaultRollback = false)
public abstract class BasePersistenceTest extends AbstractTransactionalJUnit4SpringContextTests {

	private static boolean dataInserted;

	@Qualifier("dataSource")
	@Autowired
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	@Before
	public void insertData() throws Exception {
		if (!dataInserted) {
			BaseDbUnitTestUtils.insertData(getDatabaseConnection());
			dataInserted = true;
		}
	}

	protected IDatabaseConnection getDatabaseConnection() throws SQLException {
		IDatabaseConnection databaseConnection = new DatabaseDataSourceConnection(dataSource);
		DatabaseConfig databaseConfig = databaseConnection.getConfig();
		databaseConfig.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
		return databaseConnection;
	}
}
