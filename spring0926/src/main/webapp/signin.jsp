<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="lang" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE HTML>
<!--
	Stellar by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
<title>sign in</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<noscript>
	<link rel="stylesheet" href="assets/css/noscript.css" />
</noscript>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

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
							<h2><spring:message code="signin.signup" /></h2>
						</header>
						<form action="signup.do" method="post">
							<table class="alt">
								<tbody>
									<tr>
										<td><spring:message code="signin.tableID" /></td>
										<td colspan="2">
										<input type="text" id="mid" name="mid" required pattern="^[a-z0-9]{3,7}$">
										<td><input type="button" class="button primary fit"
										onclick="idcheck()" value="<spring:message code="signin.table.IDCheck" />">
										</td>
									</tr>
									<tr>
										<td><spring:message code="signin.tablePW" /></td>
										<td colspan="3"><input type="password" name="mpw" id="mpw" pattern="^[a-z0-9]{5,10}$"
										onchange="pwcheck()"><span id="pwcheck" style="font-size:13px;"></span></td>
									</tr>
									<tr>
										<td><spring:message code="signin.tableName" /></td>
										<td colspan="2">
										<input type="text" id="name" name="name" required pattern="^[a-z가-힣0-9]{2,5}$"></td>
										<td><input type="button" class="button primary fit" 
										onclick="namecheck()" value="<spring:message code="signin.table.NameCheck" />"></td>
									</tr>
									<!-- 커스텀태그 넣을 곳 -->
									<tr>
										<td><spring:message code="signin.tableRole" /></td>
										<%-- <td><select name="role">
											<c:forEach var="v" items="${scMapMemRole}">
												<option value="${v.value}">${v.key}</option>
											</c:forEach>
										</select></td> --%>
										<lang:role/>
										<!-- <td colspan="2"><select name="role">
												<option selected>MEMBER</option>
												<option>ADMIN</option>
										</select></td> -->
									</tr>
									<tr>
										<td colspan="4" align="right">
											<input type="submit" class="button primary" value="<spring:message code="signin.table.signup" />"></td>
									</tr>
								</tbody>
							</table>
						</form>
						<a href="login.do?lang=${param.lang}"><spring:message code="signin.login" /></a>
					</div>
					<span class="image"><img src="images/pic01.jpg" alt="" /></span>
				</div>
			</section>

		</div>

<script type="text/javascript">
	function idcheck(){
		//alert("사용하실 수 있는 아이디입니다.");
		var id = $("#mid").val();
		console.log(id);
		var idpattern=/[a-z0-9]{3,7}$/;
		if(!idpattern.test(id)){
			var idalert = "<spring:message code="singin.idalert" />";
			alert(idalert);
		}
		else {
			$.ajax({
			    url: "check.do?mid="+id,
			    
			    success: function(data){ // 요청 성공 시 수행할 이벤트
			    	console.log(data);
					/* if(data==1){
						document.getElementById('mid').innerHTML = "중복된 아이디입니다.";
					}else if(data==0){
						document.getElementById('mid').innerHTML = "사용 가능한 아이디입니다.";
					} */
					
					var ava = "<spring:message code="signin.available" />";
					var unava = "<spring:message code="signin.unavailable" />";
	/* 					console.log("ava");
						console.log(ava);
						console.log("unava"); */
			        if(data==0){
			         console.log("데이터 존재하지 않음");
			         alert(ava);
			         }else if(data==1){
			         	console.log("데이터 존재함");
			         	alert(unava);
			         }
			    }
			  });
			}
		}
	
	function namecheck(){
		var name = $("#name").val();
		console.log(name);
		var namepattern=/[a-z가-힣0-9]{2,5}$/;
		if(!namepattern.test(name)){
			var namealert = "<spring:message code="singin.namealert" />";
			alert(namealert);
		}
		else {
			$.ajax({
			    url: "check.do?name="+name,
			    
			    success: function(data){ // 요청 성공 시 수행할 이벤트
			    	console.log(data);
					var ava = "<spring:message code="signin.availableName" />";
					var unava = "<spring:message code="signin.unavailableName" />";
					
			        if(data==0){
			         console.log("데이터 존재하지 않음");
			         alert(ava);
			         }else if(data==1){
			         	console.log("데이터 존재함");
			         	alert(unava);
			         }
			    }
			  });
			}
		}
	function pwcheck() {
		var pw = $("#mpw").val();
		var pwpattern = /[a-z0-9]{5,10}$/;
			
		if (!pwpattern.test(pw)) {
			var pwguide = "<spring:message code="signin.pwguide" />";
			document.getElementById('pwcheck').innerHTML = pwguide;
			document.getElementById('pwcheck').style.color = '#935d8c';
		}
		else {
			var availablepw = "<spring:message code="signin.availablepw" />";
			document.getElementById('pwcheck').innerHTML = availablepw;
			document.getElementById('pwcheck').style.color = '#8cc9f0';
		}
	}
</script>

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
</body>
</html>