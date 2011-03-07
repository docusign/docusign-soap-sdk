<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Send a DocuSign Template</title>
</head>
<body>
<form action="SendToDocuSign" method="post">
        Name:<input type="text" name="userName" value="C. Smith" size="20"/>
        <br/>
        E-mail: <input type="text" name="email" value="jocrag@gmail.com" size="20"/>
        <br/>
        Template Id: <input type="text" name="templateId" value="05CFFEBB-E1EF-4C1B-A27D-65D6731FADCC" size="20"/>
        <br/>
        Role Name: <input type="text" name="roleName" value="Signer" size="20"/>
        <br/>
        <input type="submit" value="Send"/>
    </form>
</body>
</html>