<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
	<head>
    	<title>模板编辑</title>
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
  	</head>
	<body>
		<div class="container">
  			<h3>模板编辑</h3>
  			<form id="editForm" class="form-horizontal" action="${ctx}/template/save" method="post">
				<input type="hidden" id="id" name="id" value="${template.id }"/>
			  	<div class="form-group">
			    	<label for="name" class="col-sm-2 control-label">模板名称</label>
				    <div class="col-sm-7">
				    	<input type="text" class="form-control" id="name" name="name" value="${template.name }"/>
				    </div>
			  	</div>
			   	<div class="form-group">
			  	  	<label for="type" class="col-sm-2 control-label">模板类型</label>
			      	<div class="col-sm-7">
			      		<select id="type" name="type" class="form-control">
			      			<c:forEach items="${types}" var="t">
			      				<option value="${t}" <c:if test="${t eq template.type}">selected="selected"</c:if>>${t.desc}</option>
							</c:forEach>
			      		</select>
			      	</div>
			    </div>
			    <div class="form-group">
				    <label for="path" class="col-sm-2 control-label">发布路径</label>
				    <div class="col-sm-7">
				      	<input type="text" class="form-control" id="path" name="path" value="${template.path}">
				    </div>
			  	</div>
			    <div class="form-group">
				    <label for="sort" class="col-sm-2 control-label">排序字段</label>
				    <div class="col-sm-7">
				      	<input type="text" class="form-control" id="sort" name="sort" value="${template.sort }">
				    </div>
			  	</div>
			  	<div class="form-group">
			    <label for="status" class="col-sm-2 control-label">模板状态</label>
				    <div class="col-sm-7">
					    <c:forEach items="${statuss}" var="s">
						    <div class="radio">
						    	<label>
						      		<input type="radio" value="${s}" <c:if test="${s eq template.status}">checked="checked"</c:if> name="status"/>${s.desc}
						      	</label>
						    </div>
					    </c:forEach>
				    </div>
			  	</div>
			  	<div class="form-group">
				    <label for="introduction" class="col-sm-2 control-label">模板说明</label>
				    <div class="col-sm-7">
				      	<input type="text" class="form-control" id="introduction" name="introduction" value="${template.introduction }">
				    </div>
			  	</div>
			  	<div class="form-group">
				    <label for="content" class="col-sm-2 control-label">模板内容</label>
				    <div class="col-sm-7">
				      	<textarea id="content" name="content" class="form-control" rows="20">${template.content}</textarea>
				    </div>
			  	</div>
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-7">
			      <button type="submit" class="btn btn-default">保存</button>
			    </div>
			  </div>
			</form>
  		</div>
		<script src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
		<script src="${ctx}/static/js/bootstrap.min.js"></script>
		<script type="text/javascript">
		$(function () {
	    	addInit();
	    });
	
		function addInit(){
			var id = $('#id').val();
			if(!id){
				$('input[type="radio"][name="status"]').eq(1).prop("checked",true);
			}
		}
		</script>
	</body>
</html>