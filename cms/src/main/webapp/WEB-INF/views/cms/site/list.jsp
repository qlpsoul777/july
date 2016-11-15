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
					<div>
						<form id="queryForm" action="${ctx }/site/list">
							<input id="totalSize" type="hidden" name="totalSize" value="${pageInfo.totalPages }"/>
		  					<input id="pageSize" type="hidden" name="pageSize" value="${pageInfo.size }"/>
		  					<input id="currentPage" type="hidden" name="currentPage" value="${pageInfo.number }"/>
						</form>
					</div>
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
					   <div class="row">
					   <ul class="pagination" id="pageDiv"></ul>
					   </div>
					</div>
				</div>
			</div>
  		</div>
		<script src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
		<script src="${ctx}/static/js/bootstrap.min.js"></script>
		<script src="${ctx}/static/js/page_sync.js"></script>
		<script type="text/javascript">
		$(function () {
	    	var currentPage = $('#currentPage').val(),
	    	totalSize = $('#totalSize').val(),
	    	pageSize = $('#pageSize').val();
	    	PageSync.init(currentPage,pageSize,totalSize);//分页
	    });
		</script>
	</body>
</html>