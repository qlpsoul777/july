<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
	<head>
    	<title>用户管理</title>
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
  	</head>
	<body>
		<div class="container-fluid">
	  		<div class="row">
	  			<div class="smart-widget">
					<h3>
						站点列表
					</h3>
					<div class="smart-widget-body" style="padding:10px;">
						<table class="table table-bordered">
				      		<thead>
				        		<tr>
						        	<th>Id</th>
						          	<th>名称</th>
						          	<th>编号</th>
						          	<th>路径</th>
						          	<th>状态</th>
						          	<th>创建人</th>
						          	<th>操作</th>
				        		</tr>
				      		</thead>
				      		<tbody>
					        	<tr>
						        	<c:forEach items="${pageInfo.content }" var="site">
						        		<tr>
						        			<td>${site.id }</td>
						        			<td>${site.name }</td>
						        			<td>${site.num }</td>
						        			<td>${site.path }</td>
						        			<td>${site.status }</td>
						        			<td>${site.createBy }</td>
						        			<td></td>
						        			
						        		</tr>
						        	</c:forEach>
					        	</tr>
					      	</tbody>
					   </table>
					</div>
				</div>
			</div>
  		</div>
		<script src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
		<script src="${ctx}/static/js/bootstrap.min.js"></script>
	</body>
</html>