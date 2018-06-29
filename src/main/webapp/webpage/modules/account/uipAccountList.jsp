<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>account管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
	<div class="ibox">
	<div class="ibox-title">
		<h5>account列表 </h5>
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
	<form:form id="searchForm" modelAttribute="uipAccount" action="${ctx}/account/uipAccount/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span>账户ID：</span>
				<form:input path="id" htmlEscape="false"  class=" form-control input-sm"/>
			<span>账户名称：</span>
				<form:input path="name" htmlEscape="false" maxlength="80"  class=" form-control input-sm"/>
			<span>账户金额：</span>
				<form:input path="amount" htmlEscape="false"  class=" form-control input-sm"/>
			<span>账户余额：</span>
				<form:input path="balance" htmlEscape="false"  class=" form-control input-sm"/>
			<span>证书ID：</span>
				<form:input path="certifId" htmlEscape="false" maxlength="40"  class=" form-control input-sm"/>
			<span>代理ID：</span>
				<form:input path="agentId" htmlEscape="false" maxlength="200"  class=" form-control input-sm"/>
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="account:uipAccount:add">
				<table:addRow url="${ctx}/account/uipAccount/form" title="account"></table:addRow><!-- 增加按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="account:uipAccount:edit">
			    <table:editRow url="${ctx}/account/uipAccount/form" title="account" id="contentTable"></table:editRow><!-- 编辑按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="account:uipAccount:del">
				<table:delRow url="${ctx}/account/uipAccount/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="account:uipAccount:import">
				<table:importExcel url="${ctx}/account/uipAccount/import"></table:importExcel><!-- 导入按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="account:uipAccount:export">
	       		<table:exportExcel url="${ctx}/account/uipAccount/export"></table:exportExcel><!-- 导出按钮 -->
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
				<th  class="sort-column id">账户ID</th>
				<th  class="sort-column name">账户名称</th>
				<th  class="sort-column amount">账户金额</th>
				<th  class="sort-column balance">账户余额</th>
				<th  class="sort-column certifId">证书ID</th>
				<th  class="sort-column agentId">代理ID</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="uipAccount">
			<tr>
				<td> <input type="checkbox" id="${uipAccount.id}" class="i-checks"></td>
				<td><a  href="#" onclick="openDialogView('查看account', '${ctx}/account/uipAccount/form?id=${uipAccount.id}','800px', '500px')">
					${uipAccount.id}
				</a></td>
				<td>
					${uipAccount.name}
				</td>
				<td>
					${uipAccount.amount}
				</td>
				<td>
					${uipAccount.balance}
				</td>
				<td>
					${uipAccount.certifId}
				</td>
				<td>
					${uipAccount.agentId}
				</td>
				<td>
					<shiro:hasPermission name="account:uipAccount:view">
						<a href="#" onclick="openDialogView('查看account', '${ctx}/account/uipAccount/form?id=${uipAccount.id}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="account:uipAccount:edit">
    					<a href="#" onclick="openDialog('修改account', '${ctx}/account/uipAccount/form?id=${uipAccount.id}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
    				</shiro:hasPermission>
    				<shiro:hasPermission name="account:uipAccount:del">
						<a href="${ctx}/account/uipAccount/delete?id=${uipAccount.id}" onclick="return confirmx('确认要删除该account吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
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