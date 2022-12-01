<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">


</head>
<body>
	<%@include file="/common/admin/header.jsp" %>
	<div style="margin-top : 32px;">
		<h1 style="text-align: center;letter-spacing: 4px;color : red;">MANAGER CATEGORIES</h1>
		<a style="display: inline-block;margin-bottom: 16px;padding: 16px;background-color: green;color: #fff;text-decoration: none " 
		href="<c:url value = '/admin/cateRoom?action=add'/>"><b>Create New Category</b></a>
	</div>
	
	<c:if test="${not empty message}">
		<div  class="alert alert-success text-center">${message}</div>
	</c:if>


	<div>
		<table class="table table-bordered table-striped table-hover table-reponsive-xl">
			<thead class="thead-dark">
				<tr class="text-center">
					<th scope="col">ID</th>
					<th scope="col">Loại phòng</th>
					<th scope="col">Danh sách tất cả các phòng</th>
					<th scope="col">Thao tác</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="item" items="${listCategories}">
					<tr class="text-center">
						<td>${item.id}</td>
						<td>${item.name}</td>
						<td>
							<a href="<c:url value='/admin/room?idCategory=${item.id}'/>">
								<b>Xem chi tiết</b>
							</a>
						</td>
						<td class="d-inline-flex">
							<a class="btn btn-secondary" href="<c:url value = '/admin/cateRoom?action=edit&id=${item.id}'/>"><b>Cập nhật</b></a>
							<a class="btn btn-danger" href="<c:url value = '/admin/cateRoom?action=delete&id=${item.id}'/>" style="margin-left: 16px;"><b>Xóa</b></a>
						</td>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>