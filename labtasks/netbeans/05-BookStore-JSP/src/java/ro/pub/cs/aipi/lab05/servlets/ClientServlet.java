package ro.pub.cs.aipi.lab05.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ro.pub.cs.aipi.lab05.businesslogic.BookManager;
import ro.pub.cs.aipi.lab05.businesslogic.BookPresentationManager;
import ro.pub.cs.aipi.lab05.businesslogic.CategoryManager;
import ro.pub.cs.aipi.lab05.businesslogic.FormatManager;
import ro.pub.cs.aipi.lab05.businesslogic.InvoiceHeaderManager;
import ro.pub.cs.aipi.lab05.businesslogic.InvoiceLineManager;
import ro.pub.cs.aipi.lab05.businesslogic.LanguageManager;
import ro.pub.cs.aipi.lab05.businesslogic.UserManager;
import ro.pub.cs.aipi.lab05.general.Constants;
import ro.pub.cs.aipi.lab05.general.Utilities;
import ro.pub.cs.aipi.lab05.helper.Record;

public class ClientServlet extends HttpServlet {

    final public static long serialVersionUID = 10021002L;

    private BookManager bookManager;
    private BookPresentationManager bookPresentationManager;
    private UserManager userManager;
    private InvoiceHeaderManager invoiceHeaderManager;
    private InvoiceLineManager invoiceLineManager;
    private FormatManager formatManager;
    private LanguageManager languageManager;
    private CategoryManager categoryManager;

    private List<Record> shoppingCart;
    private List<String> formatsFilter;
    private List<String> languagesFilter;
    private List<String> categoriesFilter;
    private String previousRecordsPerPage;
    private String currentRecordsPerPage;
    private String currentPage;

    private List<List<Record>> books;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        bookManager = new BookManager();
        bookPresentationManager = new BookPresentationManager();
        userManager = new UserManager();
        invoiceHeaderManager = new InvoiceHeaderManager();
        invoiceLineManager = new InvoiceLineManager();
        formatManager = new FormatManager();
        languageManager = new LanguageManager();
        categoryManager = new CategoryManager();
        previousRecordsPerPage = String.valueOf(Constants.RECORDS_PER_PAGE_VALUES[0]);
        currentRecordsPerPage = String.valueOf(Constants.RECORDS_PER_PAGE_VALUES[0]);
        currentPage = String.valueOf(1);
        books = null;
    }

    @Override
    public void destroy() {
    }

    @SuppressWarnings("unchecked")
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (session == null || session.getAttribute(Constants.DISPLAY) == null) {
            return;
        }
        String display = session.getAttribute(Constants.DISPLAY).toString();
        shoppingCart = (List<Record>) session
                .getAttribute(Utilities.removeSpaces(Constants.SHOPPING_CART.toLowerCase()));
        if (shoppingCart == null) {
            shoppingCart = new ArrayList<>();
        }
        formatsFilter = (List<String>) session.getAttribute(Constants.FORMATS_FILTER);
        if (formatsFilter == null) {
            formatsFilter = new ArrayList<>();
        }
        languagesFilter = (List<String>) session.getAttribute(Constants.LANGUAGES_FILTER);
        if (languagesFilter == null) {
            languagesFilter = new ArrayList<>();
        }
        categoriesFilter = (List<String>) session.getAttribute(Constants.CATEGORIES_FILTER);
        if (categoriesFilter == null) {
            categoriesFilter = new ArrayList<>();
        }
        String errorMessage = "";
        boolean filterChange = false;
        RequestDispatcher dispatcher = null;
        Enumeration<String> parameters = request.getParameterNames();
        while (parameters.hasMoreElements()) {
            String parameter = (String) parameters.nextElement();
            if (parameter.equals(Utilities.removeSpaces(Constants.RECORDS_PER_PAGE.toLowerCase().trim()))) {
                currentRecordsPerPage = request.getParameter(parameter);
            }
            if (parameter.equals(Utilities.removeSpaces(Constants.PAGE.toLowerCase().trim()))) {
                currentPage = request.getParameter(parameter);
            }
            if (parameter.equals(Constants.INSERT_BUTTON_NAME.toLowerCase() + "_" + Constants.FORMAT + ".x")) {
                String format = request.getParameter(Constants.CURRENT_FORMAT);
                if (!formatsFilter.contains(format)) {
                    formatsFilter.add(format);
                    filterChange = true;
                }
            }
            if (parameter.startsWith(Constants.DELETE_BUTTON_NAME.toLowerCase() + "_" + Constants.FORMAT + "_")
                    && parameter.endsWith(".x")) {
                String format = parameter.substring(parameter.lastIndexOf("_") + 1, parameter.indexOf(".x"));
                if (formatsFilter.contains(format)) {
                    formatsFilter.remove(format);
                    filterChange = true;
                }
            }

            // TODO: exercise 7
            if (parameter.equals(Constants.INSERT_BUTTON_NAME.toLowerCase() + "_" + Constants.CATEGORY + ".x")) {
                String category = request.getParameter(Constants.CURRENT_CATEGORY);
                if (!categoriesFilter.contains(category)) {
                    categoriesFilter.add(category);
                    filterChange = true;
                }
            }
            if (parameter.startsWith(Constants.DELETE_BUTTON_NAME.toLowerCase() + "_" + Constants.CATEGORY + "_")
                    && parameter.endsWith(".x")) {
                String category = parameter.substring(parameter.lastIndexOf("_") + 1, parameter.indexOf(".x"));
                if (categoriesFilter.contains(category)) {
                    categoriesFilter.remove(category);
                    filterChange = true;
                }
            }

            // TODO: exercise 8

            // TODO: exercise 11

            session.setAttribute(Constants.FORMATS_FILTER, formatsFilter);
            session.setAttribute(Constants.LANGUAGES_FILTER, languagesFilter);
            session.setAttribute(Constants.CATEGORIES_FILTER, categoriesFilter);

            // TODO: exercise 12
        }
        if (books == null || filterChange) {
            books = bookManager.getCollection(formatsFilter, languagesFilter, categoriesFilter);
        }
        List<String> formatAttributes = new ArrayList<>();
        formatAttributes.add(Constants.FORMAT_ATTRIBUTE);
        List<List<String>> formats = formatManager.getCollection(formatAttributes);
        List<String> formatsList = new ArrayList<>();
        for (List<String> currentFormat : formats) {
            formatsList.add(currentFormat.get(0));
        }
        List<String> languageAttributes = new ArrayList<>();
        languageAttributes.add(Constants.LANGUAGE_ATTRIBUTE);
        List<List<String>> languages = languageManager.getCollection(languageAttributes);
        List<String> languagesList = new ArrayList<>();
        for (List<String> currentLanguage : languages) {
            languagesList.add(currentLanguage.get(0));
        }
        List<String> categoryAttributes = new ArrayList<>();
        categoryAttributes.add(Constants.CATEGORY_ATTRIBUTE);
        List<List<String>> categories = categoryManager.getCollection(categoryAttributes);
        List<String> categoriesList = new ArrayList<>();
        for (List<String> currentCategory : categories) {
            categoriesList.add(currentCategory.get(0));
        }
        response.setContentType("text/html");
        dispatcher = getServletContext().getRequestDispatcher("/" + Constants.CLIENT_PAGE);
        if (dispatcher != null) {
            request.setAttribute(Constants.DISPLAY, session.getAttribute(Constants.DISPLAY).toString());
            request.setAttribute(Constants.BOOKS, books);
            request.setAttribute(Constants.ERROR_MESSAGE, errorMessage);
            request.setAttribute(Constants.SHOPPING_CART, shoppingCart);
            request.setAttribute(Constants.FORMATS_FILTER, formatsFilter);
            request.setAttribute(Constants.LANGUAGES_FILTER, languagesFilter);
            request.setAttribute(Constants.CATEGORIES_FILTER, categoriesFilter);
            if (currentRecordsPerPage != null && !filterChange) {
                request.setAttribute(Constants.CURRENT_RECORDS_PER_PAGE, Integer.parseInt(currentRecordsPerPage));
            } else {
                request.setAttribute(Constants.CURRENT_RECORDS_PER_PAGE, Constants.RECORDS_PER_PAGE_VALUES[0]);
            }
            if (currentPage != null && !filterChange && currentRecordsPerPage != null
                    && currentRecordsPerPage.equals(previousRecordsPerPage)) {
                request.setAttribute(Constants.CURRENT_PAGE, Integer.parseInt(currentPage));
            } else {
                request.setAttribute(Constants.CURRENT_PAGE, 1);
            }
            previousRecordsPerPage = currentRecordsPerPage;
            request.setAttribute(Constants.FILTER_CHANGE, filterChange);
            request.setAttribute(Constants.FORMATS_LIST, formatsList);
            request.setAttribute(Constants.LANGUAGES_LIST, languagesList);
            request.setAttribute(Constants.CATEGORIES_LIST, categoriesList);
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
