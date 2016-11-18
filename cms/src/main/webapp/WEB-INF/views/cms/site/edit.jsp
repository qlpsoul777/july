<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
	<head>
    	<title>站点管理</title>
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
  	</head>
	<body>
		<div class="container">
	  		<div class="row">
	  			<form id="editForm" class="form-horizontal" action="${ctx}/site/save" method="post">
				  <div class="form-group">
				    <label for="name" class="col-sm-2 control-label">站点中文名</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" id="name">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="num" class="col-sm-2 control-label">站点编号</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" id="num">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
				    <div class="col-sm-10">
				      <input type="password" class="form-control" id="inputPassword3" placeholder="Password">
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10">
				      <div class="checkbox">
				        <label>
				          <input type="checkbox"> Remember me
				        </label>
				      </div>
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10">
				      <button type="submit" class="btn btn-default">保存</button>
				      <button type="reset" class="btn btn-default">取消</button>
				    </div>
				  </div>
				</form>
	  		</div>
  		</div>
		<script src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
		<script src="${ctx}/static/js/bootstrap.min.js"></script>
		<script type="text/javascript">
		$(function () {
	    	
	    	
	    });
		</script>
	</body>
</html>