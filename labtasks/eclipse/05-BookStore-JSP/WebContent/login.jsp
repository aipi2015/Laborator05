<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="ro.pub.cs.aipi.lab05.general.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />
<title><%=Constants.APPLICATION_NAME%></title>
<link rel="stylesheet" type="text/css" href="css/bookstore.css" />
<link rel="icon" type="image/x-icon" href="./images/favicon.ico" />
</head>
<body style="text-align: center">
	<h2><%=Constants.APPLICATION_NAME.toUpperCase()%></h2>
	<form action="LoginServlet" method="post">
		<div id="wrapperabsolute">
			<div id="wrappertop"></div>
			<div id="wrappermiddle">
				<h2><%=Constants.SIGNIN%></h2>
				<div id="username_input">
					<div id="field_inputleft"></div>
					<div id="field_inputmiddle">
						<input type="text" name="<%=Constants.USERNAME%>" id="url"
							value="<%=Constants.USERNAME%>" onclick="this.value = ''" /> <img
							id="url_field" src="./images/user_interface/username.png" alt="" />
					</div>
					<div id="field_inputright"></div>
				</div>
				<div id="password_input">
					<div id="field_inputleft"></div>
					<div id="field_inputmiddle">
						<input type="password" name="<%=Constants.PASSWORD%>" id="url"
							value="<%=Constants.PASSWORD%>" onclick="this.value = ''" /> <img
							id="url_field" src="./images/user_interface/password.png" alt="" />
					</div>
					<div id="field_inputright"></div>
				</div>
				<div id="submit">
					<input type="image" src="./images/user_interface/signin.png"
						id="submit2" name="<%=Constants.SIGNIN.toLowerCase()%>"
						value="<%=Constants.SIGNIN%>" />
				</div>
				<c:if test="${not empty error && error eq true}">
					<h3 style="color: red"><%=Constants.ERROR_USERNAME_PASSWORD%></h3>
				</c:if>
			</div>
			<div id="wrapperbottom"></div>
		</div>
	</form>
</body>
</html>
