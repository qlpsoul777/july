<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
	<head>
    	<title>文件列表</title>
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
  	</head>
	<body>
		<div class="container">
	  		<div class="row">
	  			<h3>文件列表</h3>
					<div class="col-xs-12 col-md-12">
						<a href="javascript:removett()" class="btn btn-primary">上传</a>
						<a id="downloadFile" class="btn btn-primary">下载</a>
						<a id="deleteFiles" class="btn btn-danger">删除</a>
						<a href="${ctx }/staticFile/list" class="btn btn-link">返回根目录</a>
						<form id="queryForm" class="form-horizontal" action="${ctx }/staticFile/list">
							<input id="totalSize" type="hidden" name="totalSize" value="${pageInfo.totalPages }"/>
		  					<input id="pageSize" type="hidden" name="pageSize" value="${pageInfo.size }"/>
		  					<input id="currentPage" type="hidden" name="currentPage" value="${pageInfo.number }"/>
							<input id="filePath" type="hidden" name="filePath" value="${filePath}"/>
						</form>
					</div>
					<div class="col-xs-12 col-md-12">
						<ol class="breadcrumb">
						  <li><a id="brd">待优化</a></li>
						</ol>
					</div>
					<div class="smart-widget-body">
						<table class="table table-bordered">
				      		<thead>
				        		<tr>
						        	<th><input id="chkAll" type="checkbox"/></th>
						          	<th>名称</th>
						          	<th>类型</th>
						          	<th>路径</th>
						          	<th>大小</th>
						          	<th>修改时间</th>
						          	<th>操作</th>
				        		</tr>
				      		</thead>
				      		<tbody id="tbody">
					        	<c:forEach items="${pageInfo.content }" var="file">
					        		<tr>
					        			<td><input name="chkName" type="checkbox" id="${file.name}" value="${file.path }" /></td>
					        			<td>${file.name}</td>
					        			<td>
					        				<c:choose>
					        					<c:when test="${file.isFile}">文件</c:when>
					        					<c:otherwise><a href="${ctx }/staticFile/list?filePath=${file.path }">文件夹</a></c:otherwise>
					        				</c:choose>
					        			</td>
					        			<td>${file.path }</td>
					        			<td>
					        				<c:choose>
					        					<c:when test="${file.isFile}">${file.size }</c:when>
					        					<c:otherwise>未知</c:otherwise>
					        				</c:choose>
					        			</td>
					        			<td>${file.modifyTime }</td>
					        			<td>
					        				<c:if test="${file.isFile}">
					        					<a href="${ctx }/staticFile/preView?path=${file.path}" target="_blank">查看</a>
					        				</c:if>
					        			</td>
					        		</tr>
					        	</c:forEach>
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
	    	
	    	$("input[name='chkName']").each(function(){
				var oldId = $(this).attr('id');
				oldId = formatId(oldId);
				$(this).attr('id',oldId);
			});
	    	
	    	//全选
	    	$('#chkAll').on('click',function(){
	    		$('input[name="chkName"]').prop('checked',$(this).prop('checked'));
	    	});
	    	
	    	$('#downloadFile').on('click',function(){
	    		var checked = checkedCheckBox();
	    		if(checked.length > 1){
					alert("只能同时下载一个文件或文件夹");
					return;
				}
	    		if(checked.length == 1){
	    			window.location.href = "${ctx }/staticFile/download?path="+checked;
	    		}
	    	});
	    	
	    	$('#deleteFiles').on('click',function(){
	    		var checked = checkedCheckBox();
	    		if(checked.length > 0){
	    			if(confirm("确定删除吗？")){
   				 		$.ajax({
	    					url : "${ctx}/staticFile/delete",
	    					type : "POST",
	    					async : false,
	    					data :{"paths":checked.join(",")},
	    					dataType : "json",
	    					success : function(data){
	    						if(data.isSuccess){
	    							removeTd();
	    						}else{
	    							alert(data.msg);
	    						}
	    					}
	    				}); 
	    			}
	    		}
	    	});
	    	
	    });
		
		//去除空格和.号
		function formatId(oldId){
			oldId = oldId.replace(/\.|\s/g,'');
			return oldId;
		}
		
		function removeTd(){
			var tds = [];
			$("input[name='chkName']:checked").each(function(){
				tds.push($(this).attr('id'));
			});
			if(tds.length > 0){
				for(var i = 0;i<tds.length;i++){
					$('#'+tds[i]).parent().parent().empty();
				}
			}
		}
		
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