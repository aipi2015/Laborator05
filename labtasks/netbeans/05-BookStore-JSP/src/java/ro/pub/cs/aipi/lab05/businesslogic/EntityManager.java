package ro.pub.cs.aipi.lab05.businesslogic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import com.sun.rowset.CachedRowSetImpl;

import ro.pub.cs.aipi.lab05.dataaccess.DatabaseException;
import ro.pub.cs.aipi.lab05.dataaccess.DatabaseOperations;
import ro.pub.cs.aipi.lab05.dataaccess.DatabaseOperationsImplementation;
import ro.pub.cs.aipi.lab05.general.Constants;

public class EntityManager {

    protected String table;

    public EntityManager() {
        this.table = null;
    }

    public EntityManager(String table) {
        this.table = table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getTable() {
        return table;
    }

    public long create(List<String> values) {
        DatabaseOperations databaseOperations = null;
        try {
            databaseOperations = DatabaseOperationsImplementation.getInstance();
            long result = databaseOperations.insertValuesIntoTable(table, null, values, true);
            if (result == -1) {
                if (Constants.DEBUG) {
                    System.out.println("Insert operation failed!");
                }
            }
            return result;
        } catch (DatabaseException | SQLException exception) {
            System.out.println("An exception has occurred: " + exception.getMessage());
            if (Constants.DEBUG) {
                exception.printStackTrace();
            }
        }
        return -1;
    }

    public List<String> read(long id) {
        DatabaseOperations databaseOperations = null;
        try {
            databaseOperations = DatabaseOperationsImplementation.getInstance();
            List<List<String>> result = databaseOperations.getTableContent(table, null,
                    databaseOperations.getTablePrimaryKey(table) + "=" + id, null, null, null);
            if (result.size() != 1) {
                if (Constants.DEBUG) {
                    System.out.println("The query returned more than one result!");
                }
                return null;
            }
            return result.get(0);
        } catch (SQLException sqlException) {
            System.out.println("An exception has occurred: " + sqlException.getMessage());
            if (Constants.DEBUG) {
                sqlException.printStackTrace();
            }
        }
        return null;
    }

    public int update(List<String> values, long id) {
        return update(null, values, id);
    }

    public int update(List<String> attributes, List<String> values, long id) {
        DatabaseOperations databaseOperations = null;
        try {
            databaseOperations = DatabaseOperationsImplementation.getInstance();
            int result = databaseOperations.updateRecordsIntoTable(table, attributes, values,
                    databaseOperations.getTablePrimaryKey(table) + "=" + id);
            if (result != 1) {
                if (Constants.DEBUG) {
                    System.out.println("Update operation failed!");
                }
            }
            return result;
        } catch (DatabaseException | SQLException exception) {
            System.out.println("An exception has occurred: " + exception.getMessage());
            if (Constants.DEBUG) {
                exception.printStackTrace();
            }
        }
        return -1;
    }

    public int delete(long id) {
        DatabaseOperations databaseOperations = null;
        try {
            databaseOperations = DatabaseOperationsImplementation.getInstance();
            int result = databaseOperations.deleteRecordsFromTable(table, null, null,
                    databaseOperations.getTablePrimaryKey(table) + "=" + id);
            if (result != 1) {
                if (Constants.DEBUG) {
                    System.out.println("Delete operation failed!");
                }
            }
            return result;
        } catch (DatabaseException | SQLException exception) {
            System.out.println("An exception has occurred: " + exception.getMessage());
            if (Constants.DEBUG) {
                exception.printStackTrace();
            }
        }
        return -1;
    }

    public List<String> getStructure() {
        DatabaseOperations databaseOperations = null;
        try {
            databaseOperations = DatabaseOperationsImplementation.getInstance();
            return databaseOperations.getTableColumns(table);
        } catch (SQLException sqlException) {
            System.out.println("An exception has occurred: " + sqlException.getMessage());
            if (Constants.DEBUG) {
                sqlException.printStackTrace();
            }
        } finally {
            databaseOperations.releaseResources();
        }
        return null;
    }

    public List<List<String>> getCollection() {
        return getCollection((ArrayList<String>) null);
    }

    public List<List<String>> getCollection(List<String> attributes) {
        return getCollection(attributes, null);
    }

    public List<List<String>> getCollection(List<String> attributes, String tableAlias) {
        DatabaseOperations databaseOperations = null;
        try {
            databaseOperations = DatabaseOperationsImplementation.getInstance();
            return databaseOperations.getTableContent(table + (tableAlias != null ? " " + tableAlias : ""), attributes,
                    null, null, databaseOperations.getTablePrimaryKey(table), null);
        } catch (SQLException sqlException) {
            System.out.println("An exception has occurred: " + sqlException.getMessage());
            if (Constants.DEBUG) {
                sqlException.printStackTrace();
            }
        } finally {
            databaseOperations.releaseResources();
        }
        return null;
    }

    public CachedRowSet getCollection(String matchColumn) {
        return getCollection(matchColumn, null);
    }

    public CachedRowSet getCollection(String matchColumn, List<String> attributes) {
        return getCollection(matchColumn, attributes, null);
    }

    public CachedRowSet getCollection(String matchColumn, List<String> attributes, String tableAlias) {
        try {
            CachedRowSet result = new CachedRowSetImpl();
            result.setUrl(Constants.DATABASE_CONNECTION + Constants.DATABASE_NAME);
            result.setUsername(Constants.DATABASE_USERNAME);
            result.setPassword(Constants.DATABASE_PASSWORD);
            StringBuilder attributesList = new StringBuilder();
            Iterator<String> attributesIterator = attributes.iterator();
            while (attributesIterator.hasNext()) {
                attributesList.append(attributesIterator.next() + ", ");
            }
            attributesList.setLength(attributesList.length() - 2);
            result.setCommand("SELECT " + attributesList.toString() + " FROM " + table
                    + (tableAlias != null ? " " + tableAlias : ""));
            result.setMatchColumn(matchColumn);
            result.execute();
            return result;
        } catch (SQLException sqlException) {
            System.out.println("An exception has occurred: " + sqlException.getMessage());
            if (Constants.DEBUG) {
                sqlException.printStackTrace();
            }
        }
        return null;
    }

    public int getTableSize() {
        DatabaseOperations databaseOperations = null;
        try {
            databaseOperations = DatabaseOperationsImplementation.getInstance();
            return databaseOperations.getTableNumberOfRows(table);
        } catch (SQLException sqlException) {
            System.out.println("An exception has occurred: " + sqlException.getMessage());
            if (Constants.DEBUG) {
                sqlException.printStackTrace();
            }
        } finally {
            databaseOperations.releaseResources();
        }
        return -1;
    }

    public String getIdentifier() {
        DatabaseOperations databaseOperations = null;
        try {
            databaseOperations = DatabaseOperationsImplementation.getInstance();
            return databaseOperations.getTablePrimaryKey(table);
        } catch (SQLException sqlException) {
            System.out.println("An exception has occurred: " + sqlException.getMessage());
            if (Constants.DEBUG) {
                sqlException.printStackTrace();
            }
        } finally {
            databaseOperations.releaseResources();
        }
        return null;
    }

    public int getIdentifierIndex() {
        DatabaseOperations databaseOperations = null;
        try {
            databaseOperations = DatabaseOperationsImplementation.getInstance();
            return databaseOperations.getTablePrimaryKeyIndex(table);
        } catch (SQLException sqlException) {
            System.out.println("An exception has occurred: " + sqlException.getMessage());
            if (Constants.DEBUG) {
                sqlException.printStackTrace();
            }
        } finally {
            databaseOperations.releaseResources();
        }
        return -1;
    }

    public long generateIdentifierNextValue() {
        DatabaseOperations databaseOperations = null;
        try {
            databaseOperations = DatabaseOperationsImplementation.getInstance();
            return databaseOperations.getTablePrimaryKeyMaximumValue(table);
        } catch (SQLException sqlException) {
            System.out.println("An exception has occurred: " + sqlException.getMessage());
            if (Constants.DEBUG) {
                sqlException.printStackTrace();
            }
        } finally {
            databaseOperations.releaseResources();
        }
        return -1;
    }

    public List<String> getDatabaseStructure() {
        DatabaseOperations databaseOperations = null;
        try {
            databaseOperations = DatabaseOperationsImplementation.getInstance();
            return databaseOperations.getTableNames();
        } catch (SQLException sqlException) {
            System.out.println("An exception has occurred: " + sqlException.getMessage());
            if (Constants.DEBUG) {
                sqlException.printStackTrace();
            }
        } finally {
            databaseOperations.releaseResources();
        }
        return null;
    }
}
