package ro.pub.cs.aipi.lab05.general;

public interface Constants {

	public final static String APPLICATION_NAME = "BookStore";

	public final static String DATABASE_CONNECTION = "jdbc:mysql://localhost:3306/bookstore";
	public final static String DATABASE_USERNAME = "root";
	public final static String DATABASE_PASSWORD = "StudentAipi2015$";
	public final static String DATABASE_NAME = "bookstore";

	public final static boolean DEBUG = false;

	public final static long SEED = 20152015L;

	public final static String LOGIN_SERVLET_PAGE_CONTEXT = "LoginServlet";
	public final static String LOGIN_PAGE = "login.jsp";
	public final static String ADMINISTRATOR_SERVLET_PAGE_CONTEXT = "AdministratorServlet";
	public final static String ADMINISTRATOR_PAGE = "administrator.jsp";
	public final static String CLIENT_SERVLET_PAGE_CONTEXT = "ClientServlet";
	public final static String CLIENT_PAGE = "client.jsp";

	public final static String LOGIN_SERVLET_PAGE_NAME = "Authentication";
	public final static String ADMINISTRATOR_SERVLET_PAGE_NAME = "Administration Page";
	public final static String CLIENT_SERVLET_PAGE_NAME = "Book Catalog";

	public final static String LOGIN_FORM = "login_form";
	public final static String ADMINISTRATOR_FORM = "administrator_form";
	public final static String CLIENT_FORM = "client_form";

	public final static String SIGNIN = "Sign in";
	public final static String SIGNOUT = "Sign out";
	public final static String WELCOME_MESSAGE = "Welcome, ";
	public final static String DISPLAY = "display";
	public final static String ERROR_USERNAME_PASSWORD = "Either username or password are incorrect!";

	public final static String USER_TYPE = "type";
	public final static String ADMINISTRATOR_TYPE = "administrator";
	public final static String CLIENT_TYPE = "client";
	public final static String USERNAME = "username";
	public final static String PASSWORD = "password";

	public final static int USER_NONE = 0;
	public final static int USER_ADMINISTRATOR = 1;
	public final static int USER_CLIENT = 2;

	public final static int OPERATION_NONE = 0;
	public final static int OPERATION_INSERT = 1;
	public final static int OPERATION_UPDATE_PHASE1 = 2;
	public final static int OPERATION_UPDATE_PHASE2 = 3;
	public final static int OPERATION_DELETE = 4;
	public final static int OPERATION_LOGOUT = 5;

	public final static String INSERT_BUTTON_NAME = "Insert";
	public final static String UPDATE_BUTTON_NAME = "Update";
	public final static String DELETE_BUTTON_NAME = "Delete";

	public final static String ERROR_ATTRIBUTE = "error";

	public final static String OPERATION_TABLE_HEADER = "operation";

	public final static String INVALID_VALUE = "invalid";

	public final static String CURRENT_TABLE = "currentTable";

	public final static String RECORDS_PER_PAGE = "Records per Page ";
	public final static int RECORDS_PER_PAGE_VALUES[] = { 1, 5, 10, 15, 20, 25, 40, 50, 60, 75, 80, 100 };
	public final static String PAGE = "Page ";

	public final static String FORMAT = "Format: ";
	public final static String CURRENT_FORMAT = "currentFormat";
	public final static String FORMAT_ATTRIBUTE = "value";
	public final static String FORMATS_FILTER = "formatsFilter";
	public final static String LANGUAGE = "Language: ";
	public final static String CURRENT_LANGUAGE = "currentLanguage";
	public final static String LANGUAGE_ATTRIBUTE = "name";
	public final static String LANGUAGES_FILTER = "languagesFilter";
	public final static String CATEGORY = "Category: ";
	public final static String CURRENT_CATEGORY = "currentCategory";
	public final static String CATEGORY_ATTRIBUTE = "name";
	public final static String CATEGORIES_FILTER = "categoriesFilter";

	public final static String FORMAT_TABLE = "format";
	public final static String LANGUAGE_TABLE = "language";
	public final static String CATEGORY_TABLE = "category";

	public final static int ID_INDEX = 0;
	public final static String ID_ATTRIBUTE = "id";
	// book
	public final static int TITLE_INDEX = ID_INDEX + 1; // 1
	public final static int SUBTITLE_INDEX = TITLE_INDEX + 1; // 2
	public final static int WRITERS_INDEX = SUBTITLE_INDEX + 1; // 3
	public final static int DESCRIPTION_INDEX = WRITERS_INDEX + 1; // 4
	public final static int COLLECTION_INDEX = DESCRIPTION_INDEX + 1; // 5
	public final static int PUBLISHING_HOUSE_INDEX = COLLECTION_INDEX + 1; // 6
	public final static int COUNTRY_INDEX = PUBLISHING_HOUSE_INDEX + 1; // 7
	public final static int EDITION_INDEX = COUNTRY_INDEX + 1; // 8
	public final static int PRINTING_YEAR_INDEX = EDITION_INDEX + 1; // 9
	public final static int CATEGORIES_INDEX = PRINTING_YEAR_INDEX + 1; // 10
	public final static int BOOK_PRESENTATIONS_INDEX = CATEGORIES_INDEX + 1; // 11
	public final static String TITLE_ATTRIBUTE = "title";
	public final static String SUBTITLE_ATTRIBUTE = "subtitle";
	public final static String WRITERS_ATTRIBUTE = "writers";
	public final static String DESCRIPTION_ATTRIBUTE = "description";
	public final static String COLLECTION_ATTRIBUTE = "collection";
	public final static String PUBLISHING_HOUSE_ATTRIBUTE = "publishing_house";
	public final static String COUNTRY_ATTRIBUTE = "country";
	public final static String EDITION_ATTRIBUTE = "edition";
	public final static String PRINTING_YEAR_ATTRIBUTE = "printing_year";
	public final static String CATEGORIES_ATTRIBUTE = "categories";
	public final static String BOOK_PRESENTATIONS_ATTRIBUTE = "book_presentations";
	// book_presentation
	public final static int ISBN_INDEX = ID_INDEX + 1; // 1
	public final static int FORMATS_INDEX = ISBN_INDEX + 1; // 2
	public final static int LANGUAGES_INDEX = FORMATS_INDEX + 1; // 3
	public final static int PRICE_INDEX = LANGUAGES_INDEX + 1; // 4
	public final static int STOCKPILE_INDEX = PRICE_INDEX + 1; // 5
	public final static String ISBN_ATTRIBUTE = "isbn";
	public final static String FORMATS_ATTRIBUTE = "formats";
	public final static String LANGUAGES_ATTRIBUTE = "languages";
	public final static String PRICE_ATTRIBUTE = "price";
	public final static String STOCKPILE_ATTRIBUTE = "stockpile";

	public final static String COPIES = "Copies";
	public final static String ADD_TO_CART = "Add to Cart";
	public final static String COMPLETE_COMMAND = "Complete Command";
	public final static String CANCEL_COMMAND = "Cancel Command";
	public final static String ORDER_TOTAL = "Order Total: ";
	public final static String EMPTY_CART = "The shopping cart is empty!";

	public final static String STATE_ISSUED = "issued";

	public final static String INVALID_COMMAND_ERROR1 = "the order cannot be meet for the book_presentation_id ";
	public final static String INVALID_COMMAND_ERROR2 = " (insufficient stock)";

	public final static String DATABASE_STRUCTURE = "databaseStructure";
	public final static String TABLE_CONTENT = "tableContent";
	public final static String ATTRIBUTES = "attributes";
	public final static String IDENTIFIER = "identifier";
	public final static String IDENTIFIER_INDEX = "identifierIndex";
	public final static String IDENTIFIER_NEXT_VALUE = "identifierNextValue";
	public final static String IDENTIFIER_VALUE_OF_RECORD_TO_BE_UPDATED = "identifierValueOfRecordToBeUpdated";

	public final static String BOOKS = "books";
	public final static String ERROR_MESSAGE = "errorMessage";
	public final static String SHOPPING_CART = "shoppingCart";
	public final static String CURRENT_RECORDS_PER_PAGE = "currentRecordsPerPage";
	public final static String CURRENT_PAGE = "currentPage";
	public final static String FILTER_CHANGE = "filterChange";
	public final static String FORMATS_LIST = "formatsList";
	public final static String LANGUAGES_LIST = "languagesList";
	public final static String CATEGORIES_LIST = "categoriesList";

}