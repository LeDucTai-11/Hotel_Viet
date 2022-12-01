<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Đăng nhập</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<link rel="stylesheet" href="<c:url value = '/template/login/login.css'/>" media="all" />
</head>
<body id="LoginForm">
	<div class="container">
		<div class="row">
			<c:if test="${not empty message}">
					<div class="alert alert-${alert}" style="text-align: center;width: 100%;">
							<h3>${message}</h3>
					</div>
				</c:if>
			<div class="col-lg-3 col-md-2"></div>
            <div class="col-lg-6 col-md-8 login-box">
                <div class="col-lg-12 login-key">
                    <i class="fa fa-key" aria-hidden="true"></i>
                </div>
                <div class="col-lg-12 login-title">
                    REGISTER
                </div>

                <div class="col-lg-12 login-form">
                    <div class="col-lg-12 login-form">
                        <form action="<c:url value='/register'/>" id="formLogin" method="post">

							<div class="form-group">
								<label class="form-control-label">FULLNAME</label>
								<input type="text" id="fullName" name="fullName" class="form-control">
							</div>

							<div class="form-group">
                                <label class="form-control-label">USERNAME</label>
                                <input type="text" id="userName" name="userName" class="form-control">
                            </div>
                            <div class="form-group">
                                <label class="form-control-label">PASSWORD</label>
                                <input type="password" id="password" name="password" class="form-control" i>
                            </div>
                            
                            <div class="form-group">
                              <label class="form-control-label">ADDRESS</label>
                              <input type="text" id="address" name="address" class="form-control">
                          	</div>
							
                            <div class="col-lg-12 loginbttm">
                                <div class="col-lg-12 login-btm login-button">
                                	<input type="hidden" value="login" name="action"/>
                                    <button type="submit" class="btn btn-outline-primary btn-block btn-lg">REGISTER</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-md-2"></div>
        </div>
		
	</div>
	
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
</body>
</html>