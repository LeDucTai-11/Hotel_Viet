<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
</head>
<body>

	<%@ include file="/common/web/header.jsp" %>
	
	
	<div class="container">

		<div style="margin-top: 32px;" class="card text-white bg-secondary my-5 py-4 text-center">
			<h1 style="text-align: center; letter-spacing: 4px; color: #fff;">
				DANH SÁCH THÀNH PHỐ 
			</h1>
		</div>
		<!-- Content Row -->
		<div class="row">
			<c:forEach var="item" items="${listCities}" >
				<div class="col-md-4 mb-5">
					<div class="card h-100">
					<img class="card-img-top" src="<c:url value='/template/img/view.jpg'/>" alt=""">
						<div class="card-body">
							<h2 class="card-title">${item.name}</h2>
							<p class="card-text">Đây là thành phố đáng sống</p>
						</div>
						<div class="card-footer">
							<a href="<c:url value='/hotel?idCity=${item.id}'/>" class="btn btn-primary btn-sm">More Info</a>
						</div>
					</div>
			</div>
			</c:forEach>

		</div>

	</div>
	
	<%@ include file="/common/web/footer.jsp" %>


<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
</body>
</html>