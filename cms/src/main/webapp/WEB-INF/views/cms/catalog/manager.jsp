<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>栏目管理</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx}/static/js/ztree3/zTreeStyle/zTreeStyle.css"
	rel="stylesheet">
<style type="text/css">
div#rMenu {
	position: absolute;
	display: none;
	background-color: #555;
	text-align: left;
	padding: 2px;
	border: 2px solid #ccc;
	cursor: pointer;
}

</style>
</head>
<body>
	<div class="container">
		<div class="row">
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
			<div class="col-md-9">
				<h3>栏目编辑</h3>
				<form id="editForm" class="form-horizontal"
					action="${ctx}/catalog/save" method="post">
					<input type="hidden" id="id" name="id" />
					<input type="hidden" id="pId" name="pId" />
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">栏目中文名</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="name" name="name"/>
						</div>
					</div>
					<div class="form-group">
						<label for="alias" class="col-sm-2 control-label">栏目别名</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="alias" name="alias"/>
						</div>
					</div>
					<div class="form-group">
						<label for="status" class="col-sm-2 control-label">栏目状态</label>
						<div class="col-sm-10">
							<c:forEach items="${statuss}" var="s">
								<div class="radio">
									<label> 
										<input type="radio" value="${s}" name="status" />${s.desc}
									</label>
								</div>
							</c:forEach>
						</div>
					</div>
					<div class="form-group">
						<label for="introduction" class="col-sm-2 control-label">栏目描述</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="introduction" name="introduction">
						</div>
					</div>
					<div class="form-group">
						<label for="path" class="col-sm-2 control-label">访问路径</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="path" name="path" readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label for="type" class="col-sm-2 control-label">内容类型</label>
						<div class="col-sm-10">
							<select id="type" name="type" class="form-control">
								<c:forEach items="${types}" var="t">
									<option value="${t.desc}" >${t.desc}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="sort" class="col-sm-2 control-label">排序字段</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="sort" name="sort"/>
						</div>
					</div>
					<div class="form-group">
						<label for="pName" class="col-sm-2 control-label">父级栏目</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="pName" readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label for="siteName" class="col-sm-2 control-label">所属站点</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="siteName" readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default">保存</button>
						</div>
					</div>
				</form>
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
				async : true,
				dataType : "json",
				success : function(data){
					if(data){
						zTree = $.fn.zTree.init($("#catalogTree"), setting, data);
						zTree.expandAll(true);//全部展开 
						rMenu = $("#rMenu");
					}
				}
			});
		}
		
		function zTreeOnRightClick(event, treeId, treeNode){
			if(treeNode){
				var root = zTree.getNodeByParam("id", 0, null);
				$('#siteName').val(root.name);
				if(treeNode.id == 0){
					showRMenu("root", event.clientX, event.clientY);
				}else{
					showRMenu("node", event.clientX, event.clientY);
				}
				$('#addChild').on('click',function(){
					console.log(treeNode);
					var pId = treeNode.id;
					var pName = treeNode.name;
					$('#pId').val(pId);
					$('#pName').val(pName);
					hideRMenu();
				});
				
				$('#addSibling').on('click',function(){
					var node = treeNode.getParentNode();
					var pId = node.id;
					var pName = node.name;
					$('#pId').val(pId);
					$('#pName').val(pName);
					hideRMenu();
				});
				
				$('#deletes').on('click',function(){
					if(treeNode.isParent){
						alert("该栏目下有子栏目，不允许删除。");
					}else{
						if(confirm("确定删除当前栏目吗?")){
							deleteTreeNode(treeNode);
						}
						hideRMenu();
					}
				});
			}
		}
		
		function deleteTreeNode(node){
			$.get("${ctx}/catalog/delete?id=" + node.id,function(data){
		    	if(data){
		    		zTree.removeNode(node);
		    	}else{
		    		alert("删除失败");
		    	}
		    });
		}
		
		function zTreeOnClick(event, treeId, treeNode){
			if(treeNode){
				if(treeNode.id != 0){
					$.get("${ctx}/catalog/info?id=" + treeNode.id,function(data){
				    	if(data){
				    		
				    	}
				    });
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