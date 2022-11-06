<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="match" tagdir="/WEB-INF/tags"%>
<!DOCTYPE HTML>
<!--
	Stellar by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
<title>board</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<noscript>
	<link rel="stylesheet" href="assets/css/noscript.css" />
</noscript>
<style>
#file {
	display: none !important;
}

#reply {
	font-size: 7px !important;
}
</style>
</head>
</head>
<body class="is-preload">

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<div id="main">

			<!-- Introduction -->
			<section id="intro" class="main">
				<div class="spotlight">
					<div class="content">
						<header class="major">
							<h2>
								<spring:message code="board.board" />
							</h2>
						</header>
						<input type="hidden" name="bid" value="${data.bid}">
						<!-- data:글정보 -->
						<table class="alt">
							<form action="updateBoard.do" method="post"
								enctype="multipart/form-data">
								<tbody>
									<tr>
										<td><spring:message code="write.tabletitle" /></td>
										<td colspan="3"><input type="text" name="title"
											value="${data.title}"></td>
									</tr>
									<tr>
										<td><spring:message code="write.tablewriter" /></td>
										<td><input type="text" name="writer"
											value="${data.writer}" disabled></td>
										<td colspan="2"><label for=file>
												<div class="button primary icon solidfa-download">
													<spring:message code="write.tablefindfile" />
												</div>
										</label><input type="file" name="uploadFile" id="file"
											onchange="preimg(this);"></td>
									</tr>
									<tr>
										<td><spring:message code="write.tabledate" /></td>
										<td><input type="datetime-local" name="regdate"
											value="${data.regdate}" disabled></td>
										<td><spring:message code="board.tableviews" /></td>
										<td><input type="text" name="views" value="${data.views}"
											disabled></td>
									</tr>
									<tr>
										<td><spring:message code="write.tablecontent" /></td>
										<td colspan="3"><c:if
												test="${data.fileName!='pic01.jpg' }">
												<span class="image"><img
													src="images/${data.fileName}" id="preview" alt="" /></span>
											</c:if> <input type="text" name="content" value="${data.content}"></td>
									</tr>
									<tr>
										<td colspan="4" align="right">
										<c:choose>
										<c:when test="${member.mid==data.mid}">
											<input type="submit" class="button primary" value="<spring:message code="board.tableupdate" />">
												<a href="deleteBoard.do?bid=${data.bid}" class="button primary">
												<spring:message code="board.tabledelete" /></a></c:when>
										<c:when test="${member.mid eq 'admin'}">
											<a href="deleteBoard.do?bid=${data.bid}" class="button primary"> <spring:message code="board.deletereportboard" /></a>
										</c:when>
										<c:otherwise>
												<a href="report.do?bid=${data.bid}"><span style="color: red;" onclick="reportcheck()"><spring:message code="board.reportboard" /></span></a>
										<%-- </c:if> --%>
										</c:otherwise>
										</c:choose>
										</td>
									</tr>
							</form>
							<tr>
								<td colspan="4"><spring:message code="reply" /></td>
							</tr>
							<match:reply />
							<tr>
								<match:insertR />
							</tr>
							</tbody>
						</table>
						<a href="main.do"><spring:message code="gotomain" /></a>

					</div>
					<%-- <span class="image" /><img src="images/${data.fileName}"
						id="preview" alt="" /></span> --%>
				</div>
			</section>

		</div>

		<!-- Footer -->
		<footer id="footer">
			<section>
				<h2>Aliquam sed mauris</h2>
				<p>Sed lorem ipsum dolor sit amet et nullam consequat feugiat
					consequat magna adipiscing tempus etiam dolore veroeros. eget
					dapibus mauris. Cras aliquet, nisl ut viverra sollicitudin, ligula
					erat egestas velit, vitae tincidunt odio.</p>
				<ul class="actions">
					<li><a href="generic.html" class="button">Learn More</a></li>
				</ul>
			</section>
			<section>
				<h2>Etiam feugiat</h2>
				<dl class="alt">
					<dt>Address</dt>
					<dd>1234 Somewhere Road &bull; Nashville, TN 00000 &bull; USA</dd>
					<dt>Phone</dt>
					<dd>(000) 000-0000 x 0000</dd>
					<dt>Email</dt>
					<dd>
						<a href="#">information@untitled.tld</a>
					</dd>
				</dl>
				<ul class="icons">
					<li><a href="#" class="icon brands fa-twitter alt"><span
							class="label">Twitter</span></a></li>
					<li><a href="#" class="icon brands fa-facebook-f alt"><span
							class="label">Facebook</span></a></li>
					<li><a href="#" class="icon brands fa-instagram alt"><span
							class="label">Instagram</span></a></li>
					<li><a href="#" class="icon brands fa-github alt"><span
							class="label">GitHub</span></a></li>
					<li><a href="#" class="icon brands fa-dribbble alt"><span
							class="label">Dribbble</span></a></li>
				</ul>
			</section>
			<p class="copyright">
				&copy; Untitled. Design: <a href="https://html5up.net">HTML5 UP</a>.
			</p>
		</footer>

	</div>

	<!-- Scripts -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/jquery.scrollex.min.js"></script>
	<script src="assets/js/jquery.scrolly.min.js"></script>
	<script src="assets/js/browser.min.js"></script>
	<script src="assets/js/breakpoints.min.js"></script>
	<script src="assets/js/util.js"></script>
	<script src="assets/js/main.js"></script>

	<script type="text/javascript">
		function preimg(img) {
			if (img.files && img.files[0]) {
				// 이미지가 있다면
				var reader = new FileReader();
				// file을 읽어올 수 있는 FileReader 생성해서
				reader.onload = function(e) {
					document.getElementById('preview').src = e.target.result;
				}; // 'preview'위치에 이미지가 로드되면
				reader.readAsDataURL(img.files[0]);
				// reader가 이미지를 읽어냄
			} else {
				document.getElementById('preview').src = "";
				// 이미지가 없으면 아무것도 나타내지 않기 위해 작성
			}
		}
		
		function reportcheck() {
			var reportcheck="<spring:message code="reply.completereply" />";
			alert(reportcheck);
		}
		
		

	</script>
</body>
</html>