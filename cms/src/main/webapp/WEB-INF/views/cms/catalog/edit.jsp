<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
	<head>
    	<title>栏目编辑</title>
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
  	</head>
	<body>
		<div class="container">
	  		<div class="row">
	  				<h3>
						栏目编辑
					</h3>
	  			<form id="editForm" class="form-horizontal" action="${ctx}/site/save" method="post">
				  <input type="hidden" id="id" name="id" value="${site.id }"/>
				  <div class="form-group">
				    <label for="name" class="col-sm-2 control-label">站点中文名</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" id="name" name="name" value="${site.name }"/>
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="num" class="col-sm-2 control-label">站点编号</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" id="num" name="num" value="${site.num }">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <label for="path" class="col-sm-2 control-label">访问路径</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" id="path" name="path" value="${site.path }">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="introduction" class="col-sm-2 control-label">站点简介</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" id="introduction" name="introduction" value="${site.introduction }">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10">
				      <button type="submit" class="btn btn-default">保存</button>
				      <a href="${ctx }/site/list" class="btn btn-default">取消</a>
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