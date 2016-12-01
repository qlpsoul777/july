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
  		<style type="text/css">
		  iframe#mainIframe{
		  		border-style: none;
		  		overflow: scroll;
				overflow-x: hidden;
		  }
  		</style>
  	</head>
	<body>
		<div class="container">
	  		<div class="row">
	  			<nav class="navbar navbar-default navbar-fixed-top">
				  <div class="container">
				    <div class="collapse navbar-collapse" >
			          <ul class="nav navbar-nav" id="menuContainer">
			            <li class="active"><a href="${ctx }/catalog/manager">栏目管理</a></li>
			            <li><a href="${ctx }/template/manager">模板管理</a></li>
			            <li><a href="${ctx }/content/manager">新闻管理</a></li>
			            <li><a href="#">活动管理</a></li>
			            <li><a href="#">文件管理</a></li>
			            <li><a href="#">广告管理</a></li>
			          </ul>
			        </div>
				  </div>
				</nav>
	  		</div>
	  		<div class="row" style="padding-top: 60px;">
	  			<iframe id="mainIframe" name="mainIframe"></iframe>
	  		</div>	
  		</div>
		<script src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
		<script src="${ctx}/static/js/bootstrap.min.js"></script>
		<script type="text/javascript">
		$(function () {
			var obj = $('#menuContainer li').find('a');
			init(obj);
			
			obj.on('click',function(){
				$(this).parent().addClass('active').siblings().removeClass('active');
			});
			
	    	
	    });
	
		function init(target){
			var y = window.screen.availHeight - 200;
			var x =  window.screen.availWidth  - 200;
			$('#mainIframe').css({"height":y+"px", "width":x+"px"});
			$('#mainIframe').attr('src',target.eq(0).attr('href'));
		}
		</script>
	</body>
</html>