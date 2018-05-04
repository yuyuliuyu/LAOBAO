<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path;
	pageContext.setAttribute("base", basePath);
	String message = (String)request.getSession().getAttribute("message");
	request.getSession().removeAttribute("message");
%>
<%@ taglib uri="/WEB-INF/tld/security.tld" prefix="sec"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<title>导入</title>
	<script src="${base}/template/miniui/boot.js" type="text/javascript"></script>
	<link href="${base}/template/system/css/base.css" rel="stylesheet" type="text/css" />
	<link href="${base}/template/miniui/themes/other/skin.css" rel="stylesheet" type="text/css" />
	<script src="${base}/template/system/js/upload1.js" type="text/javascript"></script>
</head>

<body>
	<!-- 导入结果 -->
	<input type="hidden" value=<%=message%> id="msg" />
	<IFRAME height="50px" width="200px" name="ifram" id="ifram"  style="display:none;"></IFRAME>
	<form id="form1" action="../futures/futures_cd!beachSave.action" method="post" enctype="multipart/form-data" target="ifram" >
		<div class="zhukj">
			<!-- 页面头部 -->
			<table width="100%" style="background:rgb(210,224,240);" class="win1">
				<tr>
					<td style="width:100%; height:24px;">
						<a class="topname">导入</a>
					</td>
				</tr>
			</table>
			<!-- 页面头部结束 -->
			<div style="width:100%;">
				<!-- 单据主表部分 -->
				<div class="mini-toolbar" style="padding:0px;border-top:0;">
					<table width="100%" class="win2">
						<tr>
						<!--  
							<td align="right">导入内容：</td>
							<td align="left">
								<input class="mini-combobox" id="DRLX" name="DRLX" 
										 valueField="tableName" textField="chineseName" url="../hairunplan/enchase_mess!getExportContentData.action"
										required="true" onvaluechanged="onContentChanged" />
							</td>
							-->
							<td align="right">文件格式：</td>
							<td align="left">
								<input class="mini-combobox" id="fileTypeSel" name="fileTypeSel" value="excel" data="[{id:'excel',text:'excel'}]"
									enabled="false"	required="true" onvaluechanged="onTypeChanged" />
							</td>
							<td align="right">开始行：</td>
							<td align="left">
								<input class="mini-spinner" id="startline" name="startline" enabled="true"	 minValue="1" value="2" />
							</td>
							<s:if test='"2".equals(ckId)' >
								<td align="right">工作表名：</td>
								<td align="left">
									<input class="mini-textbox" id="sheetName" name="sheetName" value="sheet1" required="true"/>
								</td>
							</s:if>
							<!-- 
							<td align="right">结束行：</td>
							<td align="left">
								<input class="mini-spinner" id="endline" name="endline" value="excel" enabled="false"	/>
							</td>
							 -->
						</tr>
						<tr>
							<td align="right">上传格式：</td>
							<td align="left">
								<input class="mini-combobox" id="CGSM" name="CGSM" value="DEF" url="../hairunplan/enchase_mess!getUploadType.action?DRLX=CARGO_INFO"
										enabled="true"	 valueField="id" textField="text" required="true" onvaluechanged="formatChanged" />
							</td>
							<td align="right">上传文件：</td>
							<td align="left" id="uploadtd">
								<input class="mini-htmlfile" id="uploadFile" name="uploadFile" limitType="*.xls;*.xlsx;" required="true" width="250px;" onfileselect="onfileselect" 
									enabled="true" />
							</td>
							<td align="right">格式名称(保存为新格式)：</td>
							<td align="left" >
								<input class="mini-textbox" id="saveType" name="saveType" vtype="maxLength:5" />
							</td>
<!-- 							<td align="right">贸易类型：</td>
							<td align="left" >
								<input id="type" name="type" class="mini-combobox" emptyText="请输入类别" data="[{id : '0',text : '国产'},{id : '1',text : '进口'}]" value='0' />							
							</td> -->
						</tr>
					</table>
					
					<div style="width:100%;height:1px;border:0;border-top:solid 1px #99bce8"></div>
					<div id="bar" class="mini-toolbar" style="border:0;padding:0px; border-bottom:solid 1px #99bce8;">
						<table style="width:100%;">
							<tr>
								<td style="width:100%;">
									<a class="mini-button" iconCls="icon-add" id="add" onclick="addRow()">增加</a> 
									<a class="mini-button" iconCls="icon-remove" id="remove" onclick="removeRow()">删除</a>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<!-- 单据子表Grid部分 -->
				<div id="zsgd" width="100%">
					<div class="mini-fit" style=" border:none;">
						<div id="grid" class="mini-datagrid" style="width:100%;height:100%;" borderStyle="border:0;"
							idField="ZWLM" allowCellValid="true" url="../jcsj/s_drgs!getTypeData.action"
							allowCellEdit="true" allowCellSelect="true" allowSortColumn="false"  showSummaryRow="true" 
							multiSelect="true" editNextOnEnterKey="true" showPager="false" pageSize="500">
						 
							<div property="columns">
								<div type="checkcolumn"></div>
								<div type="indexcolumn" headerAlign="center">序列</div>
								<div field="ZWLM" width="60" headerAlign="center" align="left" displayField="ZWLM" >
									中文列名 <input id="ZWLM" property="editor" name="ZWLM" class="mini-buttonEdit"  allowinput="false"
											onbuttonclick="onGridButtonEdit(this,'列','../jcsj/s_drgs!show.action',800,'400','LM,ZDLX,DYBM,DYLM,FHLM','LM,ZDLX,DYBM,DYLM,FHLM','ZWLM','CGSM','CGSM')" />       
								</div>
								<div field="LM" width="60" headerAlign="center" align="left" displayField="LM" >
									列名 <input id="LM" property="editor" name="LM" class="mini-textbox" allowinput="false" enabled="false" />       
								</div>
								<!-- 
								<div field="DRLX" width="60" headerAlign="center" align="left" vtype="required;maxLength:16" >
									表名 <input id="DRLX" property="editor" name="DRLX" class="mini-textbox" allowinput="false" />       
								</div>
								 -->
								<div field="ZDLX" width="60" headerAlign="center" align="left" displayField="ZDLX" >
									字段类型 <input id="ZDLX" property="editor" name="ZDLX" class="mini-textbox" allowinput="false" enabled="false" />       
								</div>
								<div field="DYBM" width="60" headerAlign="center" align="left" displayField="DYBM" >
									对应表名<input id="DYBM" property="editor" name="DYBM" class="mini-textbox" allowinput="false" />       
								</div>
								<div field="DYLM" width="60" headerAlign="center" align="left" displayField="DYLM" >
									对应列名 <input id="DYLM" property="editor" name="DYLM" class="mini-textbox" allowinput="false" />       
								</div>
								<div field="FHLM" width="60" headerAlign="center" align="left" displayField="FHLM" >
									返回列名 <input id="FHLM" property="editor" name="FHLM" class="mini-textbox" allowinput="false" />       
								</div>
								<div field="SFBTname" width="60" headerAlign="center" align="left" displayField="SFBTname">
									是否必填 <input id="SFBTname" property="editor" name="SFBTname" class="mini-combobox" url="../jcsj/s_drgs!getSFBTData.action" 
										valueField="id" textField="name" onvaluechanged="SFBTchanged(this)"	/>       
								</div>
								<div field="SFBT" id="SFBT" width="40" name="SFBT" headerAlign="center" allowSort="true" class="mini-hidden" visible="false" >是否必填id</div>
							</div>
						
						</div>
					</div>
				</div>
					
				
				<!-- 单据子表Grid部分结束 -->
				<div style="text-align:center;">
					<div id="uploadOk" class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
						<a class="mini-button" onclick="onOk" style="width:60px;margin-right:20px;" iconCls="icon-upload">上传</a>
						<a class="mini-button" onclick="onReload" id="cz" style="width:60px;margin-right:20px;" iconCls="icon-reload">重置</a>
						<a class="mini-button" onclick="onCancel" style="width:60px;" iconCls="icon-close">关闭</a>
					</div>
				</div>
				<input type="hidden" name="ckId" value="${ckId}" />

			</div>
			<!-- 
			<iframe id="ifm" name="ifm" frameborder="0" marginheight="0" marginwidth="0" height="700" width="100%"
				src="../hairunplan/enchase_mess!style.action" ></iframe>
			 -->
			 <input type="hidden" id="typeJson" name="typeJson" />
		</div>
	</form>
	<div id="htmlContent"></div>
</body>
</html>
