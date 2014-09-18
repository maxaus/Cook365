package com.baev.cook365;

import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.Statement;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.DefaultDataSet;
import org.dbunit.dataset.DefaultTable;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.operation.DatabaseOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

/**
 * @author Maxim Baev
 */
public class BaseDbUnitTestUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseDbUnitTestUtils.class);

	/**
	 * Table names ordered by dependency
	 * IDataSet filtered = new FilteredDataSet(new
	 * DatabaseSequenceFilter(databaseConnection), dataSet);
	 * <p/>
	 * for (String name : filtered.getTableNames()) {
	 * <p/>
	 * System.out.println(name);
	 * <p/>
	 * }
	 */
	private static final String[] tables = new String[]{
			"user",
			"measure_unit",
			"product",
			"recipe",
			"ingredient"
	};

	public static void insertData(IDatabaseConnection databaseConnection) throws Exception {
		// Insert all needed test data

		Connection connection = databaseConnection.getConnection();
		try {
			connection.setAutoCommit(true);

			IDataSet[] dataSets = new IDataSet[tables.length];
			for (int i = 0; i < tables.length; i++) {
				try {
					dataSets[i] = getDataSet(tables[i]);
				} catch (DataSetException e) {
					if (e.getCause().getClass().equals(MalformedURLException.class)) {
						LOGGER.info("File {}.xml doesn't exist, ignoring.", tables[i]);
					} else {
						LOGGER.error("Exception reading " + tables[i] + ".xml", e);
						throw e;
					}
				}
			}

			//truncate from tail
			for (int i = tables.length - 1; i >= 0; i--) {
				String table = tables[i];
				try {
					IDataSet dataSet = new DefaultDataSet(new DefaultTable(tables[i].toUpperCase()));
					DatabaseOperation.DELETE_ALL.execute(databaseConnection, dataSet);
				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception("problem cleaning table: " + table, e);
				}
			}

			//insert from head
			for (int i = 0; i < dataSets.length; i++) {
				String table = tables[i];
				try {
					IDataSet dataSet = dataSets[i];
					if (dataSet == null)
						continue;//could not be read
					DatabaseOperation.INSERT.execute(databaseConnection, dataSet);
				} catch (Exception e) {
					throw new Exception("problem inserting: " + table, e);
				}
			}
		} finally {
			if (connection != null) {
				connection.setAutoCommit(false);
				connection.close();
			}
		}
	}

	/**
	 * Returns dataset from XML file.
	 *
	 * @param tableName table name.
	 * @return Dataset loaded from file.
	 * @throws org.dbunit.dataset.DataSetException
	 *          is some error occurs.
	 */
	public static IDataSet getDataSet(String tableName) throws DataSetException {
		String fileName = "dbunit/" + tableName + ".xml";
		FlatXmlProducer producer = new FlatXmlProducer(new InputSource(
				BasePersistenceTest.class.getClassLoader().getResourceAsStream(fileName)),
				false,
				/*column sensing*/true);
		return new FlatXmlDataSet(producer);
	}
}
