<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<c:forEach var="rp" items="${reply}">
	<c:choose>
		<c:when test="${member.mid == rp.rmid}">
			<form action="updateReply.do" method="post">
				<input type="hidden" name="rid" value="${rp.rid}"> <input
					type="hidden" name="rbid" value="${rp.rbid}">
				<tr>
					<!-- rp:댓글정보 -->
					<td>${rp.rname}</td>
					<c:if test="${rp.rblind!=0 }">
						<td colspan="3"><input type="text" name="rcontent"
							value="<spring:message code="reply.blindreply" /> ">
					</c:if>
					<c:if test="${rp.rblind==0}">
						<td colspan="3"><input type="text" name="rcontent"
							value="${rp.rcontent}">
					</c:if>
					<input type="submit" id="reply" value="<spring:message code="reply.editreply" />">
					<button type="button" id="reply">
						<a href="deleteReply.do?rid=${rp.rid}&rbid=${rp.rbid}"><spring:message code="reply.deletereply" /></a>
					</button>
					</td>
				</tr>
			</form>
		</c:when>

		<c:when test="${member.mid != rp.rmid && member.mid ne 'admin'}">
			<input type="hidden" name="rid" value="${rp.rid}">
			<input type="hidden" name="rbid" value="${rp.rbid}">
			<tr>
				<!-- rp:댓글정보 -->
				<td>${rp.rname}</td>
				<c:if test="${rp.rblind!=0}">
					<td colspan="3"><input type="text" name="rcontent"
						value="<spring:message code="reply.blindreply" />">
				</c:if>
				<c:if test="${rp.rblind==0}">
					<td colspan="3"><input type="text" value="${rp.rcontent}">
						<span style="font-size: 7px; color: red;" onclick="reportcheck()"><a
							href="report.do?rid=${rp.rid}&bid=${rp.rbid}"><spring:message code="reply.reportreply" /></a></span>
				</c:if>
				</td>
			</tr>
		</c:when>

		<c:when test="${member.mid eq 'admin'}">
			<input type="hidden" name="rbid" value="${rp.rbid}">
			<input type="hidden" name="rmid" id="rmid" value="${rp.rmid}">
			<tr>
				<!-- rp:댓글정보 -->
				<td>${rp.rname}(${rp.rmid})</td>
				<c:choose>
					<c:when test="${rp.rblind==0 && rp.rreport!=0}">
						<%-- <td><input type="text" name="rid" id="rid" value="${rp.rid}"></td> --%>
						<td colspan="3"><input type="text" value="${rp.rcontent}">
							<span style="font-size: 7px; color: red;"><a
								href="javascript:confirmBlind('${rp.rid}')"><spring:message code="reply.confirmblind" /></a></span>
								<script type="text/javascript">
						function confirmBlind(reply) {
							var blind = "<spring:message code="reply.checktoblindreply" />";
							var result = confirm(blind);
							console.log(reply);
							if(result) {
								$.ajax({
									url: "blindReply.do?rid="+reply,
					
									success: function(confirm){ // 요청 성공 시 수행할 이벤트
				    					console.log(confirm);
								        if(confirm==0){
								        	var blindcomplete = "<spring:message code="reply.blindcomplete" />";
				        					console.log("블라인드 완료");
				        					alert(blindcomplete);
				        	
				       					} else {
				         					console.log("블라인드 불가");
				        				}
								    }
			 					});
							} else {
									$.ajax({
										url: "notBlindReply.do?rid="+reply,
										
										success: function(confirm){ // 요청 성공 시 수행할 이벤트
									    	console.log(confirm);
									        if(confirm==0){
									        	var cancelblind = "<spring:message code="reply.cancelblind" />";
									        	console.log("블라인드취소 완료, 신고삭제 완료");
									        	alert(cancelblind);
									         }else {
									         	console.log("블라인드처리 취소 불가");
									         }
									    }
									});
								}
							}
			</script></td>
					</c:when>
					<c:when test="${rp.rblind!=0 && rp.rreport!=0}">
						<td colspan="3"><input type="text" value="${rp.rcontent}">
							<span style="font-size: 7px; color: red;"><spring:message code="reply.blindreply" /></span></td>
					</c:when>
					<c:otherwise>
						<td colspan="3"><input type="text" value="${rp.rcontent}"></td>
					</c:otherwise>
				</c:choose>
			</tr>
		</c:when>
	</c:choose>
</c:forEach>