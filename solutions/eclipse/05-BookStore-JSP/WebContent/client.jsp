<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="ro.pub.cs.aipi.lab05.general.Constants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />
        <title><%= Constants.APPLICATION_NAME %></title>
        <link rel="stylesheet" type="text/css" href="css/bookstore.css" />
        <link rel="icon" type="image/x-icon" href="./images/favicon.ico" />
    </head>
    <body>
        <h2 style="text-align: center"><%= Constants.APPLICATION_NAME.toUpperCase() %></h2>
        <form action="<%= Constants.CLIENT_SERVLET_PAGE_CONTEXT %>" method="post" name="<%= Constants.CLIENT_FORM %>">
        	<p style="text-align:right">
	        	<%= Constants.WELCOME_MESSAGE %>${display}
	        	<br/>
	        	<input type="image" name="<%= Constants.SIGNOUT.toLowerCase() %>" value="<%= Constants.SIGNOUT %>" src="./images/user_interface/signout.png" />
	        	<br/>
            </p>
	        <h2 style="text-align: center"><%= Constants.CLIENT_SERVLET_PAGE_NAME %></h2>
	        <p style="text-align: right">
	            <%= Constants.RECORDS_PER_PAGE %>
	            <c:set var="recordsPerPage" value="<%= Constants.RECORDS_PER_PAGE %>" />
	            <select name="${fn:toLowerCase(fn:replace(recordsPerPage, ' ', ''))}" onchange="document.<%= Constants.CLIENT_FORM %>.submit()">
	            	<c:forEach var="recordsPerPageValue" items="<%= Constants.RECORDS_PER_PAGE_VALUES %>">
		            	<option value="${recordsPerPageValue}"
		            		<c:if test="${recordsPerPageValue eq currentRecordsPerPage}">
		            			selected
		            		</c:if>
						>${recordsPerPageValue}
						</option>
	            	</c:forEach>
	            </select>
	            <c:set var="numberOfPages" scope="request" value="${fn:length(books) / currentRecordsPerPage + (fn:length(books) % currentRecordsPerPage != 0 ? 1 : 0) }" />
	            <%= Constants.PAGE %>
	            <c:set var="page" value="<%= Constants.PAGE %>" />
	            <select name="${fn:toLowerCase(fn:replace(page, ' ', ''))}" onchange="document.<%= Constants.CLIENT_FORM %>.submit()">
	            	<c:forEach var="pageValue" begin="1" end="${numberOfPages}" step="1">
		            	<option value="${pageValue}"
							<c:if test="${pageValue eq currentPage}">
			            		selected
			            	</c:if>
						>${pageValue}
						</option>
	            	</c:forEach>
	            </select>
	        </p>
	        <table border="0" cellpadding="4" cellspacing="1" style="width: 100%; background-image: url(./images/user_interface/background.jpg); margin: 0px auto;">
	            <tbody>
	                <tr>
	                    <td style="width: 20%; text-align: left; vertical-align: top">
	                        <div id="wrapperrelative">
                                <div id="wrappertop"></div>
                                <div id="wrappermiddle">
	                                <table border="0" cellpadding="4" cellspacing="1">
	                                    <tbody>
	                                        <tr>
	                                            <td><%= Constants.FORMAT %></td>
	                                            <td>
	                                                <select name="<%= Constants.CURRENT_FORMAT %>" style="width: 100%">
	                                                	<c:forEach var="format" items="${formatsList}">
	                                                		<option value="${format}">${format}</option>
	                                                	</c:forEach>
	                                                </select>
	                                            </td>
	                                            <td>
	                                            	<input type="image" name="<%= Constants.INSERT_BUTTON_NAME.toLowerCase() %>_<%= Constants.FORMAT %>" src="./images/user_interface/insert.png" />
	                                            </td>
	                                        </tr>
	                                        <tr>
	                                        	<td colspan="3">
	                                        		<table>
	                                        			<c:forEach var="formatFilter" items="${formatsFilter}">
	                                        				<tr>
	                                        					<td style="background: #ebebeb; text-align: left;">
	                                        						${formatFilter}
	                                        					</td>
	                                        					<td>
	                                        						<input type="image" name="<%= Constants.DELETE_BUTTON_NAME.toLowerCase() %>_<%= Constants.FORMAT %>_${formatFilter}" src="./images/user_interface/delete.png" width="16" height="16" />
	                                        					</td>
	                                        				</tr>
	                                        			</c:forEach>
	                                        		</table>
	                                        	</td>
	                                        </tr>
	                                        <tr>
	                                            <td><%= Constants.LANGUAGE %></td>
	                                            <td>
	                                                <select name="<%= Constants.CURRENT_LANGUAGE %>" style="width: 100%">
	                                                	<c:forEach var="language" items="${languagesList}">
	                                                		<option value="${language}">${language}</option>
	                                                	</c:forEach>
	                                                </select>
	                                            </td>
	                                            <td>
	                                            	<input type="image" name="<%= Constants.INSERT_BUTTON_NAME.toLowerCase() %>_<%= Constants.LANGUAGE %>" src="./images/user_interface/insert.png" />
	                                            </td>
	                                        </tr>
	                                        <tr>
	                                        	<td colspan="3">
	                                        		<table>
	                                        			<c:forEach var="languageFilter" items="${languagesFilter}">
	                                        				<tr>
	                                        					<td style="background: #ebebeb; text-align: left;">
	                                        						${languageFilter}
	                                        					</td>
	                                        					<td>
	                                        						<input type="image" name="<%= Constants.DELETE_BUTTON_NAME.toLowerCase() %>_<%= Constants.LANGUAGE %>_${languageFilter}" src="./images/user_interface/delete.png" width="16" height="16" />
	                                        					</td>
	                                        				</tr>
	                                        			</c:forEach>
	                                        		</table>
	                                        	</td>
	                                        </tr>
	                                        <tr>
	                                            <td><%= Constants.CATEGORY %></td>
	                                            <td>
	                                                <select name="<%= Constants.CURRENT_CATEGORY %>" style="width: 100%">
	                                                	<c:forEach var="category" items="${categoriesList}">
	                                                		<option value="${category}">${category}</option>
	                                                	</c:forEach>
	                                                </select>
	                                            </td>
	                                            <td>
	                                            	<input type="image" name="<%= Constants.INSERT_BUTTON_NAME.toLowerCase() %>_<%= Constants.CATEGORY %>" src="./images/user_interface/insert.png" />
	                                            </td>
	                                        </tr>
	                                        <tr>
	                                        	<td colspan="3">
	                                        		<table>
	                                        			<c:forEach var="categoryFilter" items="${categoriesFilter}">
	                                        				<tr>
	                                        					<td style="background: #ebebeb; text-align: left;">
	                                        						${categoryFilter}
	                                        					</td>
	                                        					<td>
	                                        						<input type="image" name="<%= Constants.DELETE_BUTTON_NAME.toLowerCase() %>_<%= Constants.CATEGORY %>_${categoryFilter}" src="./images/user_interface/delete.png"  width="16" height="16" />
	                                        					</td>
	                                        				</tr>
	                                        			</c:forEach>
	                                        		</table>
	                                        	</td>
	                                        </tr>	                                        
	                                    </tbody>
                                    </table>
	                            </div>
	                            <div id="wrapperbottom"></div>
	                        </div>
	                    </td>
	                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	                    <td style="width: 60%; text-align: center">
	                        <c:if test="${not empty errorMessage}">
	                            ${errorMessage}
	                            <br />
	                            <br />
	                        </c:if>
	                        <table border="0" cellpadding="4" cellspacing="1" style="margin: 0px auto;">
	                            <tbody>
	                                <c:forEach var="book" items="${books}" varStatus="position">
	                                	<c:if test="${position.index >= ((currentPage - 1) * currentRecordsPerPage) && position.index <= (currentPage * currentRecordsPerPage - 1) }">
		                                    <tr>
		                                        <td>
		                                            <div id="wrappertop"></div>
		                                            <div id="wrappermiddle">
			                                            <table style="width:100%;" border="0" cellpadding="4" cellspacing="4">
			                                                <tbody>
			                                                    <tr>
			                                                        <td>&nbsp;</td>
			                                                        <td style="text-align: left">
			                                                        	<c:forEach var="field" begin="<%= Constants.TITLE_INDEX %>" end="<%= Constants.CATEGORIES_INDEX %>" step="1">
			                                                        		<b>${book[field].attribute}</b>: ${book[field].value}
			                                                        		<br/>
			                                                        	</c:forEach>
			                                                        	<br/>
			                                                        	<c:set var="bookPresentationsIndex" scope="request" value="<%= Constants.BOOK_PRESENTATIONS_INDEX %>" />
			                                                        	<c:set var="bookPresentations" scope="request" value="${book[bookPresentationsIndex].value}"/>
			                                                        	<table style="width: 100%;">
			                                                        		<c:forEach var="bookPresentation" items="${bookPresentations}">
			                                                        			<tr>
			                                                        				<td style="width: 100%; background: #ebebeb; text-align: left;">
			                                                        					<c:forEach var="field" begin="<%= Constants.ISBN_INDEX %>" end="<%= Constants.STOCKPILE_INDEX %>" step="1">
			                                                        						<b>${bookPresentation[field].attribute}</b>: ${bookPresentation[field].value}
			                                                        						<br/>
			                                                        					</c:forEach>
			                                                        				</td>
			                                                        				<td>&nbsp;</td>
			                                                        				<c:set var="idIndex" scope="request" value="<%= Constants.ID_INDEX %>" />
			                                                        				<c:set var="currentIdentifier" scope="request" value="${bookPresentation[idIndex].value}" />
			                                                        				<td style="vertical-align: middle;">
			                                                        					<table>
			                                                        						<tr>
			                                                        							<td>
			                                                        								<c:set var="shoppingCartName" value="<%= Constants.SHOPPING_CART %>" />
			                                                        								<input type="text" name="<%= Constants.COPIES.toLowerCase() %>_${fn:toLowerCase(fn:replace(shoppingCartName, ' ', ''))}_${currentIdentifier}" size="3" />
			                                                        								<br />
			                                                        								<input type="image" name="<%= Constants.INSERT_BUTTON_NAME.toLowerCase() %>_${fn:toLowerCase(fn:replace(shoppingCartName, ' ', ''))}_${currentIdentifier}" value="<%= Constants.INSERT_BUTTON_NAME %>" src="./images/user_interface/add_to_shopping_cart.png" />
			                                                        							</td>
			                                                        						</tr>
			                                                        					</table>
			                                                        				</td>
			                                                        			</tr>
			                                                        			<tr>
			                                                        				<td colspan="2">&nbsp;</td>
			                                                        			</tr>
			                                                        		</c:forEach>
			                                                        	</table>
			                                                        </td>
			                                                    </tr>
			                                                </tbody>
			                                            </table>
			                                        </div>
			                                        <div id="wrapperbottom"></div>
			                                    </td>
			                                </tr>
			                                <tr>
			                                	<td>&nbsp;</td>
			                                </tr>              
	                                	</c:if>
	                                </c:forEach>
	                            </tbody>
	                        </table>
	                    </td>
		                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	                    <td style="width: 20%; text-align: left; vertical-align: top;">
	                        <div id="wrappertop"></div>
	                        <div id="wrappermiddle">
		                        <table style="width: 100%;">
		                            <tr>
	                                    <td style="text-align: center;"><%= Constants.SHOPPING_CART %> ( ${fn:length(shoppingCart)} )</td>
		                            </tr>
		                            <c:choose>
	                                    <c:when test="${not empty shoppingCart}">
			                            <c:set var="shoppingCartValue" scope="request" value="0" />
				                            <tr>
				                                <td>		                                        
				                                    <table border="0" cellpadding="4" cellspacing="1" style="width: 100%; background: #ffffff;">
				                                        <tbody>
				                                            <c:forEach var="shoppingCartContent" items="${shoppingCart}">
				                                            	<c:set var="bookPresentationIdentifier" value="${shoppingCartContent.attribute}" />
				                                            	<c:set var="quantity" value="${shoppingCartContent.value}" />
				                                            	<jsp:useBean id="bookPresentationManager" class="ro.pub.cs.aipi.lab05.businesslogic.BookPresentationManager" />
				                                            	<c:set var="currentBookPrice" scope="request" value="<%= bookPresentationManager.getPrice(Long.parseLong(pageContext.getAttribute("bookPresentationIdentifier").toString())) %>" />
				                                            	<c:set var="currentBookValue" scope="request" value="${currentBookPrice * quantity}" />
				                                                <c:set var="currentBookInformation" scope="request" value="<%= bookPresentationManager.getInformation(Long.parseLong(pageContext.getAttribute("bookPresentationIdentifier").toString())) %>" />                                              
				                                                <tr style="background: #ebebeb;">
				                                                    <td>
				                                                        ${shoppingCartContent.value} x ${currentBookInformation[0]}
				                                                        <br />&nbsp;&nbsp;&nbsp;&nbsp;(${currentBookInformation[1]}, ${currentBookInformation[2]})
				                                                        <br />&nbsp;&nbsp;&nbsp;&nbsp;= ${currentBookValue}
				                                                    </td>
				                                                </tr>
				                                                <c:set var="shoppingCartValue" scope="request" value="${shoppingCartValue + currentBookValue}" />
				                                            </c:forEach>
				                                            <tr style="background: #ebebeb;"><td></td></tr>
				                                            <tr style="background: #ebebeb;"><td><%= Constants.ORDER_TOTAL %> <b>${shoppingCartValue}</b></td></tr>
				                                        </tbody>
				                                    </table>
				                                </td>
				                            </tr>                                 
	                                        <tr>
	                                            <td style="text-align: center;">
	                                                <c:set var="cancelCommand" scope="request" value="<%= Constants.CANCEL_COMMAND %>" />
	                                                <input type="image" name="${fn:replace(fn:toLowerCase(cancelCommand), ' ','')}" value="<%= Constants.CANCEL_COMMAND %>" src="./images/user_interface/remove_from_shopping_cart.png" />
	                                                &nbsp;&nbsp;
	                                                <c:set var="completeCommand" scope="request" value="<%= Constants.COMPLETE_COMMAND %>" />
	                                                <input type="image" name="${fn:replace(fn:toLowerCase(completeCommand),' ','')}" value="<%= Constants.COMPLETE_COMMAND %>" src="./images/user_interface/shopping_cart_accept.png" />
	                                            </td>
	                                        </tr>
	                                    </c:when>
				                        <c:otherwise>
				                            <tr>
				                                <td style="text-align: center;"><%= Constants.EMPTY_CART %></td>
				                            </tr>
				                        </c:otherwise>                                
				                    </c:choose>
		                        </table>
		                    </div>
		                    <div id="wrapperbottom"></div>
		                </td>
	                </tr>
	            </tbody>
	        </table>
        </form>
    </body>
</html>  