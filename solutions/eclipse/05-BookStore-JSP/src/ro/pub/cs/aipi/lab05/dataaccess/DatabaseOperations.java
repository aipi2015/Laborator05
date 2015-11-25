package ro.pub.cs.aipi.lab05.dataaccess;

import java.sql.SQLException;
import java.util.List;

import ro.pub.cs.aipi.lab05.helper.Referrence;

public interface DatabaseOperations {

	public void releaseResources();

	public void setDatabaseName(String currentDatabase);

	public List<String> getTableNames() throws SQLException;

	public int getTableNumberOfRows(String tableName) throws SQLException;

	public int getTableNumberOfColumns(String tableName) throws SQLException;

	public String getTablePrimaryKey(String tableName) throws SQLException;

	public List<String> getTablePrimaryKeys(String tableName) throws SQLException;

	public long getTablePrimaryKeyMaximumValue(String tableName) throws SQLException;

	public int getTablePrimaryKeyIndex(String tableName) throws SQLException;

	public List<String> getTableColumns(String tableName) throws SQLException;

	public List<List<String>> getTableContent(String tableName, List<String> columnNames, String whereClause,
			String havingClause, String orderByClause, String groupByClause) throws SQLException;

	public long insertValuesIntoTable(String tableName, List<String> columnNames, List<String> values,
			boolean skipPrimaryKey) throws SQLException, DatabaseException;

	public int updateRecordsIntoTable(String tableName, List<String> columnNames, List<String> values,
			String whereClause) throws SQLException, DatabaseException;

	public int deleteRecordsFromTable(String tableName, List<String> columnNames, List<String> values,
			String whereClause) throws SQLException, DatabaseException;

	public boolean executeQuery(String query) throws SQLException;

	public List<String> executeStoredRoutine(String storedRoutineName, List<String> parameterTypes,
			List<String> inputParameterValues, List<Integer> outputParameterDataTypes) throws SQLException;

	public List<Referrence> getReferrences(String tableName) throws SQLException;

	public void runScript(String fileName) throws SQLException;

	public void startTransaction() throws SQLException;

	public void endTransactionWithCommit() throws SQLException;

	public void endTransactionWithRollback() throws SQLException;

}
