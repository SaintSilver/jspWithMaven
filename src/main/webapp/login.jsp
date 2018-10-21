<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kutar::</title>

<%@ include file="./commons/_head.jspf"%>

</head>
<body>
	<%@ include file="./commons/_nav.jspf"%>

	<div class="container">
		<div class="row">
			<div class="span12">
				<section id="typography">
					<div class="page-header">
						<h1>로그인</h1>
					</div>

					<form class="form-horizontal" action="/users/login"	method="post">
						
						<!-- errorMessage -->
						<c:if test="${not empty errorMessage}">
						<div class="control-group">
							<label class="error">${errorMessage}</label>
						</div>
						</c:if>

						<div class="control-group">
							<label class="control-label" for="userId">사용자 아이디</label>
							<div class="controls">
								<input type="text" name="userId" value="kutar37" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="password">비밀번호</label>
							<div class="controls">
								<input type="password" id="password" name="password"
									placeholder="">
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<button type="submit" class="btn btn-primary">로그인</button>
							</div>
						</div>
					</form>
					</section>
			</div>
		</div>
	</div>
</body>
</html>