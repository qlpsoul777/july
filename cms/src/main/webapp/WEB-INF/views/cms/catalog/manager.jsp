<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
	<head>
    	<title>栏目管理</title>
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
	    <link href="${ctx}/static/js/ztree3/zTreeStyle/zTreeStyle.css" rel="stylesheet">
  	</head>
	<body>
		<div class="container">
	  		<div class="row">
	  			<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
				  <div class="container">
				    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-6">
			          <ul class="nav navbar-nav">
			            <li class="active"><a href="#">栏目管理</a></li>
			            <li><a href="#">模板管理</a></li>
			            <li><a href="#">新闻管理</a></li>
			            <li><a href="#">活动管理</a></li>
			            <li><a href="#">文件管理</a></li>
			            <li><a href="#">广告管理</a></li>
			          </ul>
			        </div>
				  </div>
				</nav>
	  		</div>
	  		<div class="row" style="padding-top: 60px;">
	  			<div class="col-md-4">
	  				<ul id="catalogTree" class="ztree"></ul>
	  			</div>
	  			<div class="col-md-8">
	  			hello
	  				<!-- <iframe id="iframeId" frameborder="0"  name="main"  scrolling="yes"></iframe> -->
	  			</div>
	  		</div>
			
  		</div>
		<script src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
		<script src="${ctx}/static/js/bootstrap.min.js"></script>
		<script src="${ctx}/static/js/ztree3/jquery.ztree.core.min.js"></script>
		<script type="text/javascript">
		$(function () {
			loadTree();
	    	
	    });
	
		var zTreeObj;
		function loadTree(){
			var setting = {
				view : {
					dblClickExpand : false,
					selectedMulti : false
				}
			};
			$.ajax({
				url : "${ctx}/catalog/tree",
				type : "GET",
				async : false,
				dataType : "json",
				success : function(data){
					if(data){
						zTreeObj = $.fn.zTree.init($("#catalogTree"), setting, data);
					}
				}
			});
		}
		</script>
	</body>
</html>