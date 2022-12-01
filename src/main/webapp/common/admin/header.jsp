<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			 
			<a href="#" class="navbar-brand">
	            Hotel-Viet
	       	</a>
			
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#topNavbar">
	            	<span class="navbar-toggler-icon"></span>
	        </button>
	        
	        <div class="collapse navbar-collapse" id="topNavbar">
	        	<ul class="nav navbar-nav">
					<li class="navbar-item">
						<a class="nav-link" href="<c:url value='/admin/city'/>">Cities</a>
					</li>
					<li class="navbar-item">
						<a class="nav-link" href="<c:url value='/admin/hotel'/>">Hotels</a>
					</li>
					<li class="navbar-item">
						<a class="nav-link" href="<c:url value='/admin/cateRoom'/>">Categories</a>
					</li>
					<li class="navbar-item">
						<a class="nav-link" href="<c:url value='/admin/room'/>">Rooms</a>
					</li>
					<li class="navbar-item">
						<a class="nav-link" href="<c:url value='/admin/role'/>">Roles</a>
					</li>
					<li class="navbar-item">
						<a class="nav-link" href="<c:url value='/admin/user'/>">Users</a>
					</li>
				</ul>
				
				<ul class="nav navbar-nav ml-auto">
					<li class="navbar-item">
						<a class="active nav-link" href="#">Xin chào, ${USERMODEL.fullName}</a>
					</li>
					<li class="navbar-item">
						<a class="nav-link" href="<c:url value='/dang-nhap?action=logout'/>">Thoát</a>
					</li>
				</ul>
	        </div>
	</nav>