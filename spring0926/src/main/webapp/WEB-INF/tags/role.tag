<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${lang eq 'en'}">
<td colspan="3">
	<select name="role">
	<option selected>MEMBER</option>
	<option>ADMIN</option>
</select></td>
</c:if>

<c:if test="${lang eq 'ko_KR'}">
<td colspan="3">
	<select name="role">
	<c:forEach var="v" items="${scMapMemRole}">
		<option value="${v.value}">${v.key}</option>
	</c:forEach>
</select></td>
</c:if>