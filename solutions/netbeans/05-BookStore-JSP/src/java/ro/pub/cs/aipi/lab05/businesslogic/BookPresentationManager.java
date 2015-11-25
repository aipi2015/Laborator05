package ro.pub.cs.aipi.lab05.businesslogic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ro.pub.cs.aipi.lab05.dataaccess.DatabaseOperations;
import ro.pub.cs.aipi.lab05.dataaccess.DatabaseOperationsImplementation;
import ro.pub.cs.aipi.lab05.general.Constants;

public class BookPresentationManager extends EntityManager {

    public BookPresentationManager() {
        table = "book_presentation";
    }

    public double getPrice(long id) {
        DatabaseOperations databaseOperations = null;
        try {
            databaseOperations = DatabaseOperationsImplementation.getInstance();
            List<String> bookPresentationAttributes = new ArrayList<>();
            bookPresentationAttributes.add(Constants.PRICE_ATTRIBUTE);
            List<List<String>> content = databaseOperations.getTableContent(table, bookPresentationAttributes,
                    databaseOperations.getTablePrimaryKey(table) + "=" + id, null, null, null);
            if (content == null || content.size() != 1) {
                if (Constants.DEBUG) {
                    System.out.format("The query returned a different number of results than expected (%d)!%n",
                            content.size());
                }
                return -1.0;
            }
            return Double.parseDouble(content.get(0).get(0));
        } catch (SQLException sqlException) {
            System.out.println("An exception has occurred: " + sqlException.getMessage());
            if (Constants.DEBUG) {
                sqlException.printStackTrace();
            }
        } finally {
            databaseOperations.releaseResources();
        }
        return -1.0;
    }

    public int getStockpile(long id) {
        DatabaseOperations databaseOperations = null;
        try {
            databaseOperations = DatabaseOperationsImplementation.getInstance();
            List<String> bookPresentationAttributes = new ArrayList<>();
            bookPresentationAttributes.add(Constants.STOCKPILE_ATTRIBUTE);
            List<List<String>> content = databaseOperations.getTableContent(table, bookPresentationAttributes,
                    databaseOperations.getTablePrimaryKey(table) + "=" + id, null, null, null);
            if (content == null || content.size() != 1) {
                if (Constants.DEBUG) {
                    System.out.format("The query returned a different number of results than expected (%d)!%n",
                            content.size());
                }
                return -1;
            }
            return Integer.parseInt(content.get(0).get(0));
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

    public List<String> getInformation(long id) {
        DatabaseOperations databaseOperations = null;
        try {
            List<String> result = new ArrayList<>();
            databaseOperations = DatabaseOperationsImplementation.getInstance();
            List<String> bookPresentationAttributes = new ArrayList<>();
            bookPresentationAttributes.add("b.title");
            bookPresentationAttributes.add("f.value");
            bookPresentationAttributes.add("l.name");
            List<List<String>> content = databaseOperations.getTableContent(
                    "book_presentation bp, book b, format f, language l", bookPresentationAttributes,
                    "bp.id=" + id + " AND b.id=bp.book_id AND f.id=bp.format_id AND l.id=bp.language_id", null, null,
                    null);
            if (content == null || content.size() != 1) {
                if (Constants.DEBUG) {
                    System.out.format("The query returned a different number of results than expected (%d)!%n",
                            content.size());
                }
                return null;
            }
            int BOOK_TITLE_INDEX = 0;
            int FORMAT_VALUE_INDEX = 1;
            int LANGUAGE_NAME_INDEX = 2;
            result.add(content.get(0).get(BOOK_TITLE_INDEX));
            result.add(content.get(0).get(FORMAT_VALUE_INDEX));
            result.add(content.get(0).get(LANGUAGE_NAME_INDEX));
            return result;
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
