<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<td><spring:message code="reply.writereply" /></td>
<form action="insertReply.do" method="post">
<td colspan="2"><input type="text" name="rcontent" required></td>
<td align="right"><input type="submit" class="button primary" value="<spring:message code="reply.replyinsert" />"></td>
</form>