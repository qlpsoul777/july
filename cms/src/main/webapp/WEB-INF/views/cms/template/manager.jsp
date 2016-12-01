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
  			div#rMenu {
  				position:absolute;
  			 	display:none;
  			  	background-color: #555;
  			  	text-align: left;
  			 	padding: 2px;
  			  	border: 2px solid #ccc;
  			  	cursor:pointer;
  			  }
		  iframe#iframeId{
		  		min-height:700px;
		  		min-width:800px;
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
				    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-6">
			          <ul class="nav navbar-nav">
			            <li class="active"><a href="${ctx }/site/manager">栏目管理</a></li>
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
	  			<div class="col-md-3">
	  				<ul id="catalogTree" class="ztree"></ul>
	  				<div id="rMenu">
						<ul class="dropdown-menu">
							<li id="addChild">增加下级栏目</li>
							<li id="addSibling">增加同级栏目</li>
							<li id="deletes">删除栏目</li>
						</ul>
					</div>
	  			</div>
	  			<div class="col-md-9" style="min-height：500px">
	  				<iframe id="iframeId" name="main" ></iframe>
	  			</div>
	  		</div>
			
  		</div>
		<script src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
		<script src="${ctx}/static/js/bootstrap.min.js"></script>
		<script src="${ctx}/static/js/ztree3/jquery.ztree.all.min.js"></script>
		<script type="text/javascript">
		$(function () {
			loadTree();
			
			$("body").bind("mousedown",function(event){
				if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
					rMenu.hide();
				}
			});
	    	
	    });
	
		var zTree,rMenu;
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
					onRightClick: zTreeOnRightClick,
					onClick: zTreeOnClick
				}
			};
			$.ajax({
				url : "${ctx}/catalog/tree",
				type : "GET",
				async : false,
				dataType : "json",
				success : function(data){
					if(data){
						zTree = $.fn.zTree.init($("#catalogTree"), setting, data);
						rMenu = $("#rMenu");
					}
				}
			});
		}
		
		function zTreeOnRightClick(event, treeId, treeNode){
			if(treeNode){
				if(treeNode.id == 0){
					showRMenu("root", event.clientX-100, event.clientY-60);
				}else{
					showRMenu("node", event.clientX-100, event.clientY-60);
				}
				$('#addChild').on('click',function(){
					$('#iframeId').attr('src','${ctx}/catalog/edit?coType=1&pId='+treeNode.id);
					hideRMenu();
				});
				
				$('#addSibling').on('click',function(){
					var node = treeNode.getParentNode(); 
					$('#iframeId').attr('src','${ctx}/catalog/edit?coType=1&pId='+node.id);
					hideRMenu();
				});
				
				$('#deletes').on('click',function(){
					if(treeNode.isParent){
						alert("该栏目下有子栏目，不允许删除。");
					}else{
						if(confirm("确定删除当前栏目吗?")){
							$('#iframeId').attr('src','${ctx}/catalog/delete?id='+treeNode.id);
						}
						hideRMenu();
					}
				});
			}
		}
		
		function zTreeOnClick(event, treeId, treeNode){
			if(treeNode){
				if(treeNode.id != 0){
					$('#iframeId').attr('src','${ctx}/catalog/edit?coType=0&pId='+treeNode.id);
				}
			}
		}
		
		function showRMenu(type, x, y) {
			rMenu.show();
			$(rMenu).find('ul').show();
			if (type=="root") {
				$("#addChild").show();
				$("#addSibling").hide();
				$("#deletes").hide();
			} else {
				$("#addChild").show();
				$("#addSibling").show();
				$("#deletes").show();
			}
			rMenu.css({"top":y+"px", "left":x+"px"});
		}
		
		function hideRMenu() {
			rMenu.hide();
		}
		function reloadPage(id){
			window.location.href = "${ctx}/site/manager?id="+id;
		}
		</script>
	</body>
</html>