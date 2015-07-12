<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<LINK href="<c:url value='/css/Style.css'/>" type="text/css" rel="stylesheet">
		<script language="javascript" src="<c:url value='/js/public.js'/>"></script>
		<script language="javascript" src="<c:url value='/js/check.js'/>"></script>
		<!-- 日期插件，使用jquery -->
		<script type="text/javascript" src="<c:url value='/jquery/jquery-1.4.2.js'/>"></script>
		<link rel="stylesheet" href="<c:url value='/jquery/jquery.datepick.css'/>" type="text/css">
		<script type="text/javascript" src="<c:url value='/jquery/jquery.datepick.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/jquery/jquery.datepick-zh-CN.js'/>"></script>
	</HEAD>
	<script type="text/javascript">
		$(document).ready(function(){
			//使用class属性处理  'yy-mm-dd' 设置格式"yyyy/mm/dd"
			$('#birthday').datepick({dateFormat: 'yy-mm-dd'}); 
		});
	</script>
	<body>
		<form id="userAction_save_do" name="Form1" action="<c:url value='/user/list.jsp'/>" method="post" enctype="multipart/form-data">
			&nbsp;
			<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
				<input type="hidden" name="userID" value="17" id="userID"/>
				<input type="hidden" name="path" value="D:\apache-tomcat-6.0.18\webapps\itcastStrutsProject\upload/2012/03/29/&#36153;&#29992;&#25253;&#38144;&#21333;&#27169;&#26495;.doc" id="path"/>
				<input type="hidden" name="filename" value="&#36153;&#29992;&#25253;&#38144;&#21333;&#27169;&#26495;.doc" id="filename"/>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
						height="26">
						<strong><STRONG>编辑用户</STRONG>
						</strong>
					</td>
				</tr>

				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						登录名：
					</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3">
						<input type="text" name="loginname" value="caocao" id="userAction_save_do_loginname" class="bg"/>
					</td>
				</tr>
				<tr>
					<td align="center" bgColor="#f5fafe" class="ta_01">
						 密码：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="password" name="loginpass" value="123" id="loginpass"/>
					</td>
					<td align="center" bgColor="#f5fafe" class="ta_01">
						用户姓名：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="text" name="username" value="&#26361;&#25805;" id="userAction_save_do_username" class="bg"/>
					</td>
				</tr>
				<tr>
					<td align="center" bgColor="#f5fafe" class="ta_01">
						性别：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="radio" name="gender" id="gender男" value="男"/><label for="gender男">男</label>
						<input type="radio" name="gender" id="gender女" checked="checked" value="女"/><label for="gender女">女</label>

					</td>
					<td align="center" bgColor="#f5fafe" class="ta_01">
						学历：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						
						
						<select name="education" id="education">
						    <option value="">--选择学历--</option>
						    <option value="博士">博士</option>
						    <option value="硕士">硕士</option>
						    <option value="研究生" selected="selected">研究生</option>
						    <option value="本科">本科</option>
						    <option value="专科">专科</option>
						    <option value="高中">高中</option>
						
						
						</select>

					</td>
				</tr>
				<tr>
					<td align="center" bgColor="#f5fafe" class="ta_01">
						出生日期：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="text" name="birthday" size="20" value="2012-03-01" readonly="readonly" id="birthday"/>
					</td>
					<td align="center" bgColor="#f5fafe" class="ta_01">
						电话：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="text" name="cellphone" value="12312121" id="cellphone"/>
					</td>
				</tr>
				<tr>
					<td align="center" bgColor="#f5fafe" class="ta_01">
						兴趣爱好：
					</td>
					<td class="ta_01" bgColor="#ffffff" colSpan="3">
						 <input type="checkbox" name="hobby" value="看电影" id="hobby-1" checked="checked"/>
						<label for="hobby-1" class="checkboxLabel">看电影</label>
						<input type="checkbox" name="hobby" value="旅游" id="hobby-2" checked="checked"/>
						<label for="hobby-2" class="checkboxLabel">旅游</label>
						<input type="checkbox" name="hobby" value="健身" id="hobby-3"/>
						<label for="hobby-3" class="checkboxLabel">健身</label>
						<input type="checkbox" name="hobby" value="购物" id="hobby-4"/>
						<label for="hobby-4" class="checkboxLabel">购物</label>
						<input type="checkbox" name="hobby" value="睡觉" id="hobby-5"/>
						<label for="hobby-5" class="checkboxLabel">睡觉</label>
						<input type="hidden" id="__multiselect_userAction_save_do_hobby" name="__multiselect_hobby" value="" /> 
					</td>
				</tr>
				<tr>
					<td align="center" bgColor="#f5fafe" class="ta_01">
						简历资料：
					</td>
					<td class="ta_01" bgColor="#ffffff" colSpan="3">
						<input type="file" name="upload" size="30" value="" id="userAction_save_do_upload"/>
					</td>
				</tr>
				<TR>
					<TD class="ta_01" align="center" bgColor="#f5fafe">
						备注：
					</TD>
					<TD class="ta_01" bgColor="#ffffff" colSpan="3">
						<textarea name="remark" cols="30" rows="3" id="userAction_save_do_remark" style="WIDTH: 96%">&#30340;</textarea>
					</TD>
				</TR>
				<TR>
					<td align="center" colSpan="4" class="sep1">
						<img src="<c:url value='/images/shim.gif'/>">
					</td>
				</TR>
				<tr>
					<td class="ta_01" style="WIDTH: 100%" align="center"
						bgColor="#f5fafe" colSpan="4">
						<button type="submit" id="userAction_save_do_submit" name="submit" value="&#30830;&#23450;" class="button_ok">
							&#30830;&#23450;
						</button>

						<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<button type="reset" value="&#37325;&#32622;" class="button_cancel">&#37325;&#32622;</button>

						<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<INPUT class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
						<span id="Label1"></span>
					</td>
				</tr>
			</table>
		</form>




	</body>
</HTML>