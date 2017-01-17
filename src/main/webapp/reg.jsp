<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<body>

	<form method="post" action=RegServlet.do>

		Name: <input type="text" name="paramNameReg" /><br /> 
		Password: <input type="password" name="paramPasswordReg" /><br />
		Email: <input type="text" name="paramEmailReg" /><br /> 
		<br />
		<br />
		<center>
			<button type="SUBMIT">Registration</button>
			<br />
			
		</center>
		
	</form>

</body>
</html>