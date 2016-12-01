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
	  			<form id="editForm" class="form-horizontal" action="${ctx}/catalog/save" method="post">
				  <input type="hidden" id="id" name="id" value="${catalog.id }"/>
				  <input type="hidden" id="pId" name="pId" value="${parent.id}"/>
				  <div class="form-group">
				    <label for="name" class="col-sm-2 control-label">栏目中文名</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" id="name" name="name" value="${catalog.name }"/>
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="alias" class="col-sm-2 control-label">栏目别名</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" id="alias" name="alias" value="${catalog.alias }">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="status" class="col-sm-2 control-label">栏目状态</label>
				    <div class="col-sm-10">
				    <c:forEach items="${statuss}" var="s">
					    <div class="radio">
					    	<label>
					      		<input type="radio" value="${s}" <c:if test="${s eq catalog.status}">checked="checked"</c:if> name="status"/>${s.desc}
					      	</label>
					    </div>
				    </c:forEach>
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="introduction" class="col-sm-2 control-label">栏目描述</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" id="introduction" name="introduction" value="${catalog.introduction }">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="path" class="col-sm-2 control-label">访问路径</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" id="path" name="path" value="${catalog.path}" readonly="readonly">
				    </div>
				  </div>
				  <div class="form-group">
				  	  <label for="type" class="col-sm-2 control-label">内容类型</label>
				      <div class="col-sm-10">
				      <select id="type" name="type" class="form-control">
				      	<c:forEach items="${types}" var="t">
				      		<option value="${t.desc}" <c:if test="${t eq catalog.type}">selected="selected"</c:if>>${t.desc}</option>
						</c:forEach>
				      </select>
				      </div>
				    </div>
				  <div class="form-group">
				    <label for="sort" class="col-sm-2 control-label">排序字段</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" id="sort" name="sort" value="${catalog.sort }">
				    </div>
				  </div>
				   <div class="form-group">
				    <label for="pName" class="col-sm-2 control-label">父级栏目</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" id="pName" value="${parent.name }" readonly="readonly">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="siteName" class="col-sm-2 control-label">所属站点</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" id="siteName" value="${siteName}" readonly="readonly">
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