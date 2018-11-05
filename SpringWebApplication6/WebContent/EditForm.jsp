
<%@page import="com.tadigital.entity.Vendor"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	Vendor vendor = (Vendor) session.getAttribute("VENDORTOBEEDITED");
%>

<form action="editvendor<%=vendor.getId()%>" method="post">
	NAME <input type="text" name="name" value="<%=vendor.getName()%>" /><br>
	Email<input type="text" name="email"
		value=" <%=vendor.getEmail()%>" /><br /> 
		<input type="submit"
		value="Edit" />
</form>