<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE HTML>
<!--
	Stellar by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
<title>Generic - Stellar by HTML5 UP</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<noscript>
	<link rel="stylesheet" href="assets/css/noscript.css" />
</noscript>
<style>
#file {
  display: none;
}
</style>
</head>
<body class="is-preload">

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Header -->
		<header id="header">
			<h1><spring:message code="write.write" /></h1>
		</header>

		<!-- Main -->
		<div id="main">

			<!-- Content -->
			<form action="write.do" method="post" enctype="multipart/form-data">
				<section id="content" class="main">
					<table class="alt">
						<tbody>
							<tr>
								<td><spring:message code="write.tabletitle" /></td>
								<td colspan="2"><input type="text" name="title" required></td>
							</tr>
							<tr>
								<td><spring:message code="write.tablewriter" /></td>
								<td colspan="2"><input type="text" name="writer" value="${member.name}" required readonly></td>
								<input type="hidden" name="mid" value="${member.mid}" > <!-- 이렇게 작성하면 view에서는 안보이지만 모델에서는 값을 받을 수 있음 -->
							</tr>
							<tr>
								<td><spring:message code="write.tablecontent" /></td>
								<td colspan="2"><input type="text" name="content" required></td>
							</tr>
							<tr>
								<td><spring:message code="write.tablefile" /></td>
								<td><div><img id="preview"/></div></td>
								<td colspan="2">
								<div class="button primary icon solidfa-download">
									<label for="file" style="color:white;"><spring:message code="write.tablefindfile" /></label>
									<input type="file" name="uploadFile" id="file" onchange="preimg(this);" ></td>
								</div>
							</tr>
							<tr>
								<td colspan="3" align="right"><input type="submit"
									class="button primary" value="<spring:message code="write.boardinsert" />"></td>
							</tr>
						</tbody>
					</table>
				</section>
			</form>

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
					<li><a href="#" class="button">Learn More</a></li>
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
	</script>

</body>
</html>