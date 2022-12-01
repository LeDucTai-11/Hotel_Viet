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
	
	<div style="margin-top: 32px;">
		<h2 style="text-align: center;letter-spacing: 4px;color : red;">${tittle}</h2>
	</div>
	
	<div class="container-fluid" style="margin-top: 16px;">
		<form action="<c:url value='/admin/user'/>" method="post" style="max-width: 700px;margin: 0 auto">
			<input type="hidden" id="id" name="id" value="${user.id}" />
			<div class="border border-secondary rounded p-3">
				<div class="form-group row">
					<label class="col-sm-4 col-form-label">Role :</label>
					<div class="col-sm-8">
						<select class="form-control" id="role_id" name="role_id">
							<c:if test="${empty user.id}">
								<option value="">Role</option>
								<c:forEach var="item" items="${listRoles}">
									<option value="${item.id}">${item.name}</option>
								</c:forEach>
							</c:if>
							<c:if test="${not empty user.id}">
								<c:forEach var="item" items="${listRoles}">
									<option value="${item.id}"
										<c:if test="${item.id == user.role_id}">selected="selected"</c:if>>
										${item.name}</option>
								</c:forEach>
							</c:if>
						</select>
					</div>
				</div>

				<div class="form-group row">
					<label class="col-sm-4 col-form-label">FullName :</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="fullName" name="fullName" value="${user.fullName}" required/>
					</div>
				</div>
				
				<div class="form-group row">
					<label class="col-sm-4 col-form-label">UserName :</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="userName" name="userName" value="${user.userName}" required/>
					</div>
				</div>
				
				<div class="form-group row">
					<label class="col-sm-4 col-form-label">Password :</label>
					<div class="col-sm-8">
						<input type="password" class="form-control" id="password" name="password" value="${user.password}" required/>
					</div>
				</div>
				
				<div class="form-group row">
					<label class="col-sm-4 col-form-label">Address :</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="address" name="address" value="${user.address}" required/>
					</div>
				</div>
				
				
				<div class="text-center">
					<input type="submit" value="Save" class="btn btn-primary m-3"/>
					<a href="<c:url value='/admin/user'/>" class="btn btn-secondary" id="buttonCancel">Cancel</a>
				</div>
				
			</div>
		</form>
	</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>