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
		<h1 style="text-align: center;letter-spacing: 4px;color : red;">MANAGER USERS</h1>
	</div>
	
	<div style="display: flex;justify-content: space-between;">
		<div style="margin: 0 16px;">
			<a style="display: inline-block;margin-bottom: 16px;padding: 16px;background-color: green;color: #fff;text-decoration: none " 
		href="<c:url value = '/admin/user?action=add'/>"><b>Create New User</b></a>
		</div>
		<div style="margin: 0 16px;">
			<button style="display: inline-block;margin-bottom: 16px;padding: 16px;background-color: green;
			color: #fff;text-decoration: none;border: none;cursor: pointer; " 
		onclick="pageBack()"><b>Back</b></button>
		</div>
	</div>
	
	<c:if test="${not empty message}">
		<div  class="alert alert-success text-center">${message}</div>
	</c:if>
	
	<div>
		<table class="table table-bordered table-striped table-hover table-reponsive-xl">
			<thead class="thead-dark">
				<tr class="text-center">
					<th scope="col">ID</th>
					<th scope="col">FullName</th>
					<th scope="col">Role</th>
					<th scope="col">Username</th>
					<th scope="col">Address</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="item" items="${listUsers}">
					<tr class="text-center">
						<td>${item.id}</td>
						<td>${item.fullName}</td>
						<td>${item.roleName}</td>
						<td>${item.userName}</td>
						<td>${item.address}</td>
						<td class="d-inline-flex">
							<a class="btn btn-secondary" href="<c:url value='/admin/user?action=edit&id=${item.id}'/>"><b>Edit</b></a>
							<a class="btn btn-danger" href="<c:url value='/admin/user?action=delete&id=${item.id}'/> " style="margin-left: 16px"><b>Delete</b></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div>
		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
				<li
					class="${currentPage > 1 ? 'page-item' : 'page-item disabled'}">
					<a class="page-link"
					href="<c:url value ='/admin/user?page=1'/>">First</a>
				</li>
				<li
					class="${currentPage > 1 ? 'page-item' : 'page-item disabled'}">
					<a class="page-link"
					href="<c:url value ='/admin/user?page=${currentPage - 1}'/>">Previous</a>
				</li>
				<c:forEach var="i" begin="1" end="${lastPage}">
					<li class="${currentPage != i ? 'page-item' : 'page-item active'}">
						<a class="page-link" href="<c:url value='/admin/user?page=${i}'/>">${i}</a>
					</li>
				</c:forEach>
				<li
					class="${currentPage < lastPage ? 'page-item' : 'page-item disabled'}">
					<a class="page-link"
					href="<c:url value='/admin/user?page=${currentPage+1}'/>">Next</a>
				</li>
				<li
					class="${currentPage < lastPage ? 'page-item' : 'page-item disabled'}">
					<a class="page-link"
					href="<c:url value='/admin/user?page=${lastPage}'/>">Last</a>
				</li>
			</ul>
		</nav>
	</div>

<script>
	function pageBack() {
		history.back();
	}
</script>

	
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>