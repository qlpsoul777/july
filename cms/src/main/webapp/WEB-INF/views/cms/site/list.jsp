<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
	<head>
    	<title>站点列表</title>
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
  	</head>
	<body>
		<div class="container">
	  		<div class="row">
	  			<h3>站点列表</h3>
					<div class="col-xs-12 col-md-12">
						<a href="${ctx }/site/edit" class="btn btn-primary">新增</a>
						<a id="batchDel" class="btn btn-danger">删除</a>
						<form id="queryForm" class="form-horizontal" action="${ctx }/site/list">
							<input id="totalSize" type="hidden" name="totalSize" value="${pageInfo.totalPages }"/>
		  					<input id="pageSize" type="hidden" name="pageSize" value="${pageInfo.size }"/>
		  					<input id="currentPage" type="hidden" name="currentPage" value="${pageInfo.number }"/>
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
							    <div class="col-sm-offset-2 col-sm-10">
							      <button type="submit" class="btn btn-default">搜索</button>
							    </div>
							</div>
						</form>
					</div>
					<div class="smart-widget-body">
						<table class="table table-bordered">
				      		<thead>
				        		<tr>
						        	<th><input id="chkAll" type="checkbox"/></th>
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
						        			<td><input name="chkName" type="checkbox" value="${site.id }" /></td>
						        			<td>${site.name }</td>
						        			<td>${site.num }</td>
						        			<td>${site.path }</td>
						        			<td>${site.status.desc }</td>
						        			<td>${site.createBy }</td>
						        			<td>
						        				<a href="${ctx }/site/edit?id=${site.id}">编辑</a>
						        				<a href="${ctx }/site/manager?id=${site.id}">管理</a>
						        			</td>
						        			
						        		</tr>
						        	</c:forEach>
					        	</tr>
					      	</tbody>
					   </table>
					   <div>
					   		<nav><ul class="pagination" id="pageDiv"></ul></nav>
					   </div>
				</div>
			</div>
  		</div>
		<script src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
		<script src="${ctx}/static/js/bootstrap.min.js"></script>
		<script src="${ctx}/static/js/page_sync.js"></script>
		<script type="text/javascript">
		$(function () {
			//分页
	    	var currentPage = $('#currentPage').val(),
	    	totalSize = $('#totalSize').val(),
	    	pageSize = $('#pageSize').val();
	    	PageSync.init(currentPage,pageSize,totalSize);
	    	
	    	//全选
	    	$('#chkAll').on('click',function(){
	    		$('input[name="chkName"]').prop('checked',$(this).prop('checked'));
	    	});
	    	
	    	//删除
	    	$('#batchDel').on('click',function(){
	    		var checked = checkedCheckBox();
	    		if(checked.length > 0){
		    		if(confirm("确定删除吗？")){
		    			window.location.href = "${ctx}/site/delete?ids=" + checked;
		    		}
	    		}
	    	});
	    	
	    });
		
		//获取选中的checkbox值
		function checkedCheckBox(){
			var checked = [];
			$("input[name='chkName']:checked").each(function(){
				checked.push($(this).val());
			});
			if(checked.length<=0){
				alert("请选择");
			}
			return checked;
		}
		</script>
	</body>
</html>