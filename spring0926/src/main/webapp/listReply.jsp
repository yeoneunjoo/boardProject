<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE HTML>
<!--
	Stellar by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>main</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
		<noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
	</head>
	<body class="is-preload">

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Main -->
					<div id="main">
						<nav id="nav">
							<ul>
								<li><a href="list.do" class="active">Introduction</a></li>
								<li><a href="listReply.do">First Section</a></li>
							</ul>
					</nav>
						<!-- Introduction -->
							<section id="intro" class="main">
								<div class="spotlight">
									<div class="content">
										<header class="major">
											<h2>내가 작성한 댓글 목록&nbsp;&nbsp;:D</h2>
										</header>
										
										<!-- 검색, 검색을 할 때에 main.do를 하는 이유==조건이 달라지긴 하지만 결과를 main에서 보여줄 것이기 때문에-->
										<form action="list.do" method="post">
										<table>
											<thead>
												<tr>
													<th><select name="searchCondition">
														<c:forEach var="v" items="${scMapB}">
															<option value="${v.value}">${v.key}</option>
														</c:forEach>
													</select></th>
													<th><input type="text" name="searchContent" placeholder="검색어를 입력하세요..."></th>
													<th><input type="submit" class="button" value="search"></th>
												</tr>
											</thead>
										</table>
										</form>
										
										<div class="table-wrapper">
											<table>
												<thead>
													<tr>
														<th><spring:message code="main.table.num" /></th>
														<th><spring:message code="main.table.title" /></th>
														<th><spring:message code="main.table.writer" /></th>
														<th><spring:message code="main.table.views" /></th>
														<%-- <th><spring:message code="main.table.fav" /></th> --%>
													</tr>
												</thead>
												<tbody>
												<c:forEach var="v" items="${datas}">
													<tr>
														<td><a href="board.do?bid=${v.bid}">${v.bid}</a></td>
														<td>${v.title}</td>
														<td>${v.writer}</td>
														<td>${v.views}</td>
														<%-- <td>${v.fav}</td> --%>
													</tr>
												</c:forEach>
												</tbody>
												<tfoot>
													<tr>
														<td colspan="2"></td>
														<td colspan="2"><a href="mypage.do?lang=${param.lang}">마이페이지</a>
														&nbsp;&nbsp;&nbsp;&nbsp;<a href="logout.do?lang=${param.lang}"><spring:message code="main.logout" /></a></td>
													</tr>
												</tfoot>
											</table>
										</div>
									</div>
								</div>
							</section>

					</div>

				<!-- Footer -->
					<footer id="footer">
						<section>
							<h2>Aliquam sed mauris</h2>
							<p>Sed lorem ipsum dolor sit amet et nullam consequat feugiat consequat magna adipiscing tempus etiam dolore veroeros. eget dapibus mauris. Cras aliquet, nisl ut viverra sollicitudin, ligula erat egestas velit, vitae tincidunt odio.</p>
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
								<dd><a href="#">information@untitled.tld</a></dd>
							</dl>
							<ul class="icons">
								<li><a href="#" class="icon brands fa-twitter alt"><span class="label">Twitter</span></a></li>
								<li><a href="#" class="icon brands fa-facebook-f alt"><span class="label">Facebook</span></a></li>
								<li><a href="#" class="icon brands fa-instagram alt"><span class="label">Instagram</span></a></li>
								<li><a href="#" class="icon brands fa-github alt"><span class="label">GitHub</span></a></li>
								<li><a href="#" class="icon brands fa-dribbble alt"><span class="label">Dribbble</span></a></li>
							</ul>
						</section>
						<p class="copyright">&copy; Untitled. Design: <a href="https://html5up.net">HTML5 UP</a>.</p>
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

	</body>
</html>