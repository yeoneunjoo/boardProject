<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <!-- el식 넣기 위해 사용 -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

 <c:set var="role" value="${member.role}" /> <!-- <c:set var="변수" value="언어가 저장되어있는 곳 아마.. 세션...." />  -->
 <c:set var="lang" value="${param.lang}" /> 
<!-- session.getAttribute(언어들...?) -->
<%-- <c:if test="${lang eq null }">
	<c:if test="${role eq 'MEMBER'}" >
		<td colspan="2"><spring:message code="message.mypage.member" /></td>
	</c:if>
	<c:if test="${role eq 'ADMIN'}" >
	<td colspan="2"><spring:message code="message.mypage.admin" /></td>
	</c:if>
</c:if> --%>

<c:if test="${lang eq 'en' && role eq 'MEMBER'}">
<td colspan="2">MEMBER</td>
</c:if>
<c:if test="${lang eq 'en' && role eq 'ADMIN'}">
<td colspan="2">ADMIN</td>
</c:if>
<c:if test="${lang eq 'ko_KR' && role eq 'MEMBER'}">
<td colspan="2">회원</td>
</c:if>
<c:if test="${lang eq 'ko_KR' && role eq 'ADMIN'}">
<td colspan="2">관리자</td>
</c:if>