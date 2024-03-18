<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.se4f7.prj301.constants.Constants"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Import Success</title>
<%@include file="header.jsp"%>
</head>

<body>
	<div class="container card-container">
		<div class="card p-4">
			<div class="fieldset-container">
				<fieldset>
					<br>
					<legend>Import Successful</legend>
					<form>
						<br>
						<div id='view2'>
							<p>Your data has been successfully imported.</p>
						</div>
					</form>
				</fieldset>
			</div>
			<a href="./load-data-user" class="btn btn-primary">Back home</a>
		</div>
	</div>
	<div
		class="d-flex flex-column flex-md-row text-center text-md-start justify-content-between py-4 px-4 px-xl-5 bg-light"
		style="width: 100%; position: fixed; bottom: 0;">
		<!-- Copyright -->
		<div class="text-dark mb-3 mb-md-0">
			&copy;
			<%=Constants.COPYRIGHT_TEXT%>
		</div>
		<!-- Copyright -->

		<!-- Right -->
		<div>
			<a href="#!" class="text-dark me-4"> <i class="fab fa-twitter"></i>
			</a> <a href="#!" class="text-dark me-4"> <i class="fab fa-google"></i>
			</a> <a href="#!" class="text-dark"> <i class="fab fa-linkedin-in"></i>
			</a>
		</div>
	</div>

	<%@include file="./JS.jsp"%>
</body>
</html>
