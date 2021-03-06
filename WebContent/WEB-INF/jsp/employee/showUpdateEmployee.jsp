<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
    <title>电脑医院信息管理与预约系统——修改员工</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="pragma" content="no-cache" />
    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="expires" content="0" />    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
    <meta http-equiv="description" content="This is my page" />
    <link href="${ctx}/css/css.css" type="text/css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${ctx}/js/ligerUI/skins/Aqua/css/ligerui-dialog.css"/>
    <link href="${ctx}/js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
    <script src="${ctx}/js/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="${ctx}/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script> 
    <script src="${ctx}/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="${ctx}/js/ligerUI/js/plugins/ligerResizable.jss" type="text/javascript"></script>
    <link href="${ctx}/css/pager.css" type="text/css" rel="stylesheet" />
    <script language="javascript" type="text/javascript" src="${ctx }/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
    $(function(){
        // 控制文档加载完成以后 选中性别 
        $("#sex").val("${employee.sex}");
        $("#position").val("${employee.position}");
        $("#department").val("${employee.department}");
        
        /** 员工表单提交 */
        $("#employeeForm").submit(function(){
        	var realname = $("#realname");
            var realclass=$("#realclass");
            var phone = $("#phonenum");
            var party = $("#party");
            var empnum=$("#empnum");
            var msg = "";
            if ($.trim(realname.val()) == ""){
                msg = "姓名不能为空！";
                realname.focus();
            }else if ($.trim(realclass.val()) == ""){
                msg = "班级不能为空！";
                realclass.focus();
            }else if ($.trim(phonenum.val()) == ""){
                msg = "手机号码不能为空！";
                phonenum.focus();
            }else if (!/^1[3|5|8]\d{9}$/.test($.trim(phonenum.val()))){
                msg = "手机号码格式不正确！";
                phonenum.focus();
            }else if($.trim(empnum.val())==""){
            	msg="员工编号不能为空！";
            	empnum.focus();
            }
            
            if (msg != ""){
                $.ligerDialog.error(msg);
                return false;
            }else{
                return true;
            }
            $("#employeeForm").submit();
        });
    });
        

    </script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
    <td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：员工管理  &gt; 修改员工</td>
    <td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
         <form action="${ctx}/employee/updateEmployee" id="employeeForm" method="post">
            <!-- 隐藏表单，flag表示添加标记 -->
             <input type="hidden" name="flag" value="2">
            <input type="hidden" name="id" value="${employee.id }">
          <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
            <tr><td class="font3 fftd">
                <table>
                    <tr>
                        <td class="font3 fftd">姓名：<input type="text" name="realname" id="realname" size="20" value="${employee.realname }"/></td>
                        <td class="font3 fftd">班级：<input type="text" name="realclass" id="realclass" size="20" value="${employee.realclass }"/></td>
                    </tr>
                    <tr>
                        <td class="font3 fftd">电话：<input type="text" name="phonenum" id="phonenum" size="20" value="${employee.phonenum }"/></td>
               			<td class="font3 fftd">政治面貌：<input name="party" id="party" size="20" value="${employee.party }"/>
                </td>
            		</tr>
                    <tr>
                        <td class="font3 fftd">性别：
                                <select id="sex" name="sex" style="width:143px;">
                                    <option value="0">--请选择性别--</option>
                                    <option value="1">男</option>
                                    <option value="2">女</option>
                                </select>
                        </td>
                        <td class="font3 fftd">职位：
                         <select name="position" id="position" style="width:143px;">
                                    <option value="0">--请选择职位--</option>
                                    <option value="部长">部长</option>
                                    <option value="副部长">副部长</option>
                                    <option value="院长">院长</option>
                                    <option value="副院长">副院长</option>
                                    <option value="干事">干事</option>
                                </select>
                        </td>
                    </tr>
                    
                    <tr>
                    	<td class="font3 fftd">
                   		 部门：
                    		<select name="department" id="department" style="width:100px;">
	                           	<option value="0">--部门选择--</option>
	                           	<option value="宣传部">宣传部</option>
	                           	<option value="技术部">技术部</option>
	                           	<option value="组织部">组织部</option>
	                           	<option value="学习部">学习部</option>
	                           	<option value="权益部">权益部</option>
                   		 	</select>
               			 </td>
                    	<td class="font3 fftd">编号：<input type="text" name="empnum" id="empnum" size="20" value="${employee.empnum }"/></td>
                    	
                    </tr>
                    
                </table>
            </td></tr>
            <tr><td class="main_tdbor"></td></tr>
            
            <tr><td align="left" class="fftd"><input type="submit" value="修改">&nbsp;&nbsp;<input type="reset" value="取消 "></td></tr>
          </table>
         </form>
    </td>
  </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>