<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>质量报告管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			laydate({
	            elem: '#createTime', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
	            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	        });
		});
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
	<div class="ibox">
	<div class="ibox-title">
		<h5>质量报告列表 </h5>
		<div class="ibox-tools">
			<a class="collapse-link">
				<i class="fa fa-chevron-up"></i>
			</a>
			<a class="dropdown-toggle" data-toggle="dropdown" href="#">
				<i class="fa fa-wrench"></i>
			</a>
			<ul class="dropdown-menu dropdown-user">
				<li><a href="#">选项1</a>
				</li>
				<li><a href="#">选项2</a>
				</li>
			</ul>
			<a class="close-link">
				<i class="fa fa-times"></i>
			</a>
		</div>
	</div>
    
    <div class="ibox-content">
	<sys:message content="${message}"/>
	
	<!--查询条件-->
	<div class="row">
	<div class="col-sm-12">
	<form:form id="searchForm" modelAttribute="qmgeQreport" action="${ctx}/quality/qmgeQreport/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span>报告名称：</span>
				<form:input path="qreportName" htmlEscape="false" maxlength="200"  class=" form-control input-sm"/>
			<span>创建时间：</span>
				<input id="createTime" name="createTime" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm"
					value="<fmt:formatDate value="${qmgeQreport.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
			<span>创建人：</span>
				<form:input path="createWriter" htmlEscape="false" maxlength="10"  class=" form-control input-sm"/>
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="quality:qmgeQreport:add">
				<table:addRow url="${ctx}/quality/qmgeQreport/form" title="质量报告"></table:addRow><!-- 增加按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="quality:qmgeQreport:edit">
			    <table:editRow url="${ctx}/quality/qmgeQreport/form" title="质量报告" id="contentTable"></table:editRow><!-- 编辑按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="quality:qmgeQreport:del">
				<table:delRow url="${ctx}/quality/qmgeQreport/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="quality:qmgeQreport:import">
				<table:importExcel url="${ctx}/quality/qmgeQreport/import"></table:importExcel><!-- 导入按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="quality:qmgeQreport:export">
	       		<table:exportExcel url="${ctx}/quality/qmgeQreport/export"></table:exportExcel><!-- 导出按钮 -->
	       	</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="sortOrRefresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
		<div class="pull-right">
			<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
			<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 重置</button>
		</div>
	</div>
	</div>
	
	<!-- 表格 -->
	<table id="contentTable" class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
		<thead>
			<tr>
				<th> <input type="checkbox" class="i-checks"></th>
				<th  class="sort-column qreportName">报告名称</th>
				<th  class="sort-column createTime">创建时间</th>
				<th  class="sort-column createWriter">创建人</th>
				<th  class="sort-column isDisplay">是否显示</th>
				<th  class="sort-column qreportDes">描述</th>
				<th  class="sort-column insertTime">新增时间</th>
				<th  class="sort-column updateTime">修改时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="qmgeQreport">
			<tr>
				<td> <input type="checkbox" id="${qmgeQreport.id}" class="i-checks"></td>
				<td><a  href="#" onclick="openDialogView('查看质量报告', '${ctx}/quality/qmgeQreport/form?id=${qmgeQreport.id}','800px', '500px')">
					${qmgeQreport.qreportName}
				</a></td>
				<td>
					<fmt:formatDate value="${qmgeQreport.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${qmgeQreport.createWriter}
				</td>
				<td>
					${qmgeQreport.isDisplay}
				</td>
				<td>
					${fns:getDictLabel(qmgeQreport.qreportDes, 'del_flag', '')}
				</td>
				<td>
					<fmt:formatDate value="${qmgeQreport.insertTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${qmgeQreport.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<shiro:hasPermission name="quality:qmgeQreport:view">
						<a href="#" onclick="openDialogView('查看质量报告', '${ctx}/quality/qmgeQreport/form?id=${qmgeQreport.id}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="quality:qmgeQreport:edit">
    					<a href="#" onclick="openDialog('修改质量报告', '${ctx}/quality/qmgeQreport/form?id=${qmgeQreport.id}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
    				</shiro:hasPermission>
    				<shiro:hasPermission name="quality:qmgeQreport:del">
						<a href="${ctx}/quality/qmgeQreport/delete?id=${qmgeQreport.id}" onclick="return confirmx('确认要删除该质量报告吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
					</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
		<!-- 分页代码 -->
	<table:page page="${page}"></table:page>
	<br/>
	<br/>
	</div>
	</div>
</div>
</body>
</html>