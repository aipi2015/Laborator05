package ro.pub.cs.aipi.lab05.businesslogic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ro.pub.cs.aipi.lab05.dataaccess.DatabaseOperations;
import ro.pub.cs.aipi.lab05.dataaccess.DatabaseOperationsImplementation;
import ro.pub.cs.aipi.lab05.general.Constants;
import ro.pub.cs.aipi.lab05.helper.Record;

public class BookManager extends EntityManager {

    public BookManager() {
        table = "book";
    }

    public List<List<Record>> getCollection(List<String> formatsFilter, List<String> languagesFilter,
            List<String> categoriesFilter) {
        DatabaseOperations databaseOperations = null;
        try {
            List<List<Record>> result = new ArrayList<>();
            databaseOperations = DatabaseOperationsImplementation.getInstance();
            List<String> formatsAttributes = new ArrayList<>();
            formatsAttributes.add(Constants.ID_ATTRIBUTE);
            StringBuilder formats = new StringBuilder("(");
            for (String formatFilter : formatsFilter) {
                formats.append("\'" + formatFilter.replace("'", "\\\'") + "\', ");
            }
            if (formats.length() > 2) {
                formats.setLength(formats.length() - 2);
            }
            formats.append(")");
            List<List<String>> formatsContent = databaseOperations.getTableContent(Constants.FORMAT_TABLE,
                    formatsAttributes,
                    (formatsFilter != null && !formatsFilter.isEmpty())
                            ? Constants.FORMAT_ATTRIBUTE + " IN " + formats.toString() : null,
                    null, Constants.ID_ATTRIBUTE, null);
            StringBuilder formatsIdentifiers = new StringBuilder("(");
            for (List<String> formatContent : formatsContent) {
                formatsIdentifiers.append(formatContent.get(0) + ", ");
            }
            if (formatsIdentifiers.length() > 2) {
                formatsIdentifiers.setLength(formatsIdentifiers.length() - 2);
            }
            formatsIdentifiers.append(")");
            List<String> languagesAttributes = new ArrayList<>();
            languagesAttributes.add(Constants.ID_ATTRIBUTE);
            StringBuilder languages = new StringBuilder("(");
            for (String languageFilter : languagesFilter) {
                languages.append("\'" + languageFilter.replace("'", "\\\'") + "\', ");
            }
            if (languages.length() > 2) {
                languages.setLength(languages.length() - 2);
            }
            languages.append(")");
            List<List<String>> languagesContent = databaseOperations.getTableContent(Constants.LANGUAGE_TABLE,
                    languagesAttributes,
                    (languagesFilter != null && !languagesFilter.isEmpty())
                            ? Constants.LANGUAGE_ATTRIBUTE + " IN " + languages.toString() : null,
                    null, Constants.ID_ATTRIBUTE, null);
            StringBuilder languagesIdentifiers = new StringBuilder("(");
            for (List<String> languageContent : languagesContent) {
                languagesIdentifiers.append(languageContent.get(0) + ", ");
            }
            if (languagesIdentifiers.length() > 2) {
                languagesIdentifiers.setLength(languagesIdentifiers.length() - 2);
            }
            languagesIdentifiers.append(")");
            List<String> categoriesAttributes = new ArrayList<>();
            categoriesAttributes.add(Constants.ID_ATTRIBUTE);
            StringBuilder categories = new StringBuilder("(");
            for (String categoryFilter : categoriesFilter) {
                categories.append("\'" + categoryFilter.replace("'", "\\\'") + "\', ");
            }
            if (categories.length() > 2) {
                categories.setLength(categories.length() - 2);
            }
            categories.append(")");
            List<List<String>> categoriesContent = databaseOperations.getTableContent(Constants.CATEGORY_TABLE,
                    categoriesAttributes,
                    (categoriesFilter != null && !categoriesFilter.isEmpty())
                            ? Constants.CATEGORY_ATTRIBUTE + " IN " + categories.toString() : null,
                    null, Constants.ID_ATTRIBUTE, null);
            StringBuilder categoriesIdentifiers = new StringBuilder("(");
            for (List<String> categoryContent : categoriesContent) {
                categoriesIdentifiers.append(categoryContent.get(0) + ", ");
            }
            if (categoriesIdentifiers.length() > 2) {
                categoriesIdentifiers.setLength(categoriesIdentifiers.length() - 2);
            }
            categoriesIdentifiers.append(")");
            List<String> bookAttributes = new ArrayList<>();
            bookAttributes.add("b.id AS id");
            bookAttributes.add("b.title AS title");
            bookAttributes.add("b.subtitle AS subtitle");
            bookAttributes.add(
                    "(SELECT COALESCE(GROUP_CONCAT(DISTINCT CONCAT(w.first_name, \' \', w.last_name) SEPARATOR ', '), '* * *') FROM author a, writer w WHERE a.book_id = b.id AND w.id = a.writer_id) AS writers");
            bookAttributes.add("b.description AS description");
            bookAttributes.add("cl.name AS collection");
            bookAttributes.add("ph.name AS publishing_house");
            bookAttributes.add("c.name AS country");
            bookAttributes.add("b.edition AS edition");
            bookAttributes.add("b.printing_year AS printing_year");
            bookAttributes.add(
                    "(SELECT COALESCE(GROUP_CONCAT(DISTINCT c.name SEPARATOR ', '), '-') FROM category_content cc, category c WHERE cc.book_id = b.id AND c.id = cc.category_id) AS categories");
            List<List<String>> booksContent = databaseOperations.getTableContent(
                    "book b, collection cl, publishing_house ph, country c", bookAttributes,
                    "cl.id = b.collection_id AND ph.id = cl.publishing_house_id AND c.id = ph.country_id"
                    + ((categoriesFilter != null && !categoriesFilter.isEmpty())
                            ? " AND EXISTS(SELECT cc.id FROM category_content cc WHERE cc.book_id = b.id AND cc.category_id IN "
                            + categoriesIdentifiers.toString() + ")"
                            : ""),
                    null, "b.id", null);
            for (List<String> bookContent : booksContent) {
                List<String> bookPresentationAttributes = new ArrayList<>();
                bookPresentationAttributes.add("bp.id AS id");
                bookPresentationAttributes.add("bp.isbn AS isbn");
                bookPresentationAttributes.add("f.value AS format");
                bookPresentationAttributes.add("l.name AS language");
                bookPresentationAttributes.add("bp.price AS price");
                bookPresentationAttributes.add("bp.stockpile AS stockpile");
                List<List<String>> bookPresentationsContent = databaseOperations.getTableContent(
                        "book_presentation bp, format f, language l", bookPresentationAttributes,
                        "bp.book_id = " + bookContent.get(Constants.ID_INDEX)
                        + " AND f.id = bp.format_id AND l.id = bp.language_id"
                        + ((formatsContent != null && !formatsContent.isEmpty())
                                ? " AND f.id IN " + formatsIdentifiers.toString() : "")
                        + ((languagesContent != null && !languagesContent.isEmpty())
                                ? " AND l.id IN " + languagesIdentifiers.toString() : ""),
                        null, "bp.id", null);
                if (bookPresentationsContent != null && !bookPresentationsContent.isEmpty()) {
                    List<Record> bookRecord = new ArrayList<>();
                    bookRecord.add(new Record(Constants.ID_ATTRIBUTE, bookContent.get(Constants.ID_INDEX)));
                    bookRecord.add(new Record(Constants.TITLE_ATTRIBUTE, bookContent.get(Constants.TITLE_INDEX)));
                    bookRecord.add(new Record(Constants.SUBTITLE_ATTRIBUTE, bookContent.get(Constants.SUBTITLE_INDEX)));
                    bookRecord.add(new Record(Constants.WRITERS_ATTRIBUTE, bookContent.get(Constants.WRITERS_INDEX)));
                    bookRecord.add(
                            new Record(Constants.DESCRIPTION_ATTRIBUTE, bookContent.get(Constants.DESCRIPTION_INDEX)));
                    bookRecord.add(
                            new Record(Constants.COLLECTION_ATTRIBUTE, bookContent.get(Constants.COLLECTION_INDEX)));
                    bookRecord.add(new Record(Constants.PUBLISHING_HOUSE_ATTRIBUTE,
                            bookContent.get(Constants.PUBLISHING_HOUSE_INDEX)));
                    bookRecord.add(new Record(Constants.COUNTRY_ATTRIBUTE, bookContent.get(Constants.COUNTRY_INDEX)));
                    bookRecord.add(new Record(Constants.EDITION_ATTRIBUTE, bookContent.get(Constants.EDITION_INDEX)));
                    bookRecord.add(new Record(Constants.PRINTING_YEAR_ATTRIBUTE,
                            bookContent.get(Constants.PRINTING_YEAR_INDEX)));
                    bookRecord.add(
                            new Record(Constants.CATEGORIES_ATTRIBUTE, bookContent.get(Constants.CATEGORIES_INDEX)));
                    List<List<Record>> bookPresentations = new ArrayList<>();
                    for (List<String> bookPresentationContent : bookPresentationsContent) {
                        List<Record> bookPresentationRecord = new ArrayList<>();
                        bookPresentationRecord.add(
                                new Record(Constants.ID_ATTRIBUTE, bookPresentationContent.get(Constants.ID_INDEX)));
                        bookPresentationRecord.add(new Record(Constants.ISBN_ATTRIBUTE,
                                bookPresentationContent.get(Constants.ISBN_INDEX)));
                        bookPresentationRecord.add(new Record(Constants.FORMATS_ATTRIBUTE,
                                bookPresentationContent.get(Constants.FORMATS_INDEX)));
                        bookPresentationRecord.add(new Record(Constants.LANGUAGES_ATTRIBUTE,
                                bookPresentationContent.get(Constants.LANGUAGES_INDEX)));
                        bookPresentationRecord.add(new Record(Constants.PRICE_ATTRIBUTE,
                                bookPresentationContent.get(Constants.PRICE_INDEX)));
                        bookPresentationRecord.add(new Record(Constants.STOCKPILE_ATTRIBUTE,
                                bookPresentationContent.get(Constants.STOCKPILE_INDEX)));
                        bookPresentations.add(bookPresentationRecord);
                    }
                    bookRecord.add(new Record(Constants.BOOK_PRESENTATIONS_ATTRIBUTE, bookPresentations));
                    result.add(bookRecord);
                }
            }
            return result;
        } catch (SQLException sqlException) {
            System.out
                    .println("An exception has occurred while handling database records: " + sqlException.getMessage());
            if (Constants.DEBUG) {
                sqlException.printStackTrace();
            }
        } finally {
            databaseOperations.releaseResources();
        }
        return null;
    }

}
