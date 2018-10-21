<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kutar</title>

<%@ include file="commons/_head.jspf" %>

</head>
<body>

	<%@ include file="commons/_nav.jspf" %>

	<header class="jumbotron subhead" id="overview">
		<div class="container">
			<h1>Hello ${sessionScope.userId}</h1>
			<p class="lead">Sustaninable Life, Programming, Programmer</p>
		</div>
	</header>

	<div class="container">
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span6">
					<h1>메인 페이지</h1>
				</div>
			</div>
		</div>
	</div>
</body>
</html>