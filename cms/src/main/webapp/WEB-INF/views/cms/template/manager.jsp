<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
	<head>
    	<title>模板管理</title>
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
	    <link href="${ctx}/static/js/ztree3/zTreeStyle/zTreeStyle.css" rel="stylesheet">
  		<style type="text/css">
  			#templateIframe{
		  		border-style: none;
		  		overflow: scroll;
				overflow-x: hidden;
		  }
  		</style>
  	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-md-3">
					<ul id="templateTree" class="ztree"></ul>
				</div>
				<div class="col-md-9">
					<iframe id="templateIframe" name="templateIframe"></iframe>
				</div>
			</div>
		</div>
		<script src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
		<script src="${ctx}/static/js/bootstrap.min.js"></script>
		<script src="${ctx}/static/js/ztree3/jquery.ztree.all.min.js"></script>
		<script type="text/javascript">
			$(function () {
				loadTree();
				init();
		    });
			
			var zTree,root;
			function loadTree(){
				var setting = {
					view : {
						dblClickExpand : false,
						selectedMulti : false
					},
					simpleData: {
						enable: false,
						idKey: "id",
						rootPId: null
					},
					callback:{
						onClick: zTreeOnClick
					}
				};
				$.ajax({
					url : "${ctx}/catalog/tree",
					type : "GET",
					async : true,
					dataType : "json",
					success : function(data){
						if(data){
							zTree = $.fn.zTree.init($("#templateTree"), setting, data);
							zTree.expandAll(true);//全部展开 
							root = zTree.getNodeByParam("id", 0, null);
							$('#siteName').val(root.name);
						}
					}
				});
			}
			
			function zTreeOnClick(event, treeId, treeNode){
				if(treeNode){
					if(treeNode.id != 0){
						$('#templateIframe').attr('src','${ctx}/template/list?catalogId='+treeNode.id);
					}
				}
			}
			
			function init(){
				var y = window.screen.availHeight - 200;
				var x =  window.screen.availWidth  - 200;
				if(y <= 200){y = 200;}
				if(x <= 200){x = 200;}
				$('#templateIframe').css({"height":y+"px", "width":x+"px"});
			}
		</script>
	</body>
</html>