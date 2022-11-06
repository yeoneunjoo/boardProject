<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Locale"%>
<%-- <%
	response.sendRedirect("login.do");
%> --%>

	<%
		Locale locale_K = new Locale("ko","KR");
	
		if(Locale.getDefault().equals(locale_K)) {
			session.setAttribute("lang",locale_K);
			System.out.println(session.getAttribute("lang"));
			response.sendRedirect("login.do?lang=ko_KR");
		}
	
		else {
			Locale locale_E = new Locale("en");
			session.setAttribute("lang",locale_E);
			System.out.println(session.getAttribute("lang"));
			response.sendRedirect("login.do?lang=en");
		}
	%>

