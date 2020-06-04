<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <title>电脑医院信息管理与预约系统 --进行预约</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="pragma" content="no-cache" />
        <meta http-equiv="cache-control" content="no-cache" />
        <meta http-equiv="expires" content="0" />    
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
        <meta http-equiv="description" content="This is my page" />
        
        <link href="${ctx}/css/css.css" type="text/css" rel="stylesheet" />
        <link rel="stylesheet" type="text/css" href="${ctx}/js/ligerUI/skins/Aqua/css/ligerui-dialog.css"/>
        <link href="${ctx}/js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/js/metronic/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx}/js/metronic/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/>
    
    	<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
        <script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
        
        <script type="text/javascript" src="${ctx }/js/metronic/plugins/jquery-1.8.3.min.js"></script>
        <script type="text/javascript" src="${ctx }/js/metronic/plugins/bootstrap/bootstrap.min.js"></script>
        <script type="text/javascript" src="${ctx }/js/metronic/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
        <script language="javascript" type="text/javascript" src="${ctx }/js/My97DatePicker/WdatePicker.js"></script>
        
        <script src="${ctx}/js/ligerUI/js/core/base.js" type="text/javascript"></script>
        <script src="${ctx}/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script> 
        <script src="${ctx}/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
        <script src="${ctx}/js/ligerUI/js/plugins/ligerResizable.jss" type="text/javascript"></script>
        <link href="${ctx}/css/pager.css" type="text/css" rel="stylesheet" />
        
        <script type="text/javascript">
        
        $(function(){
        	
            /** 员工表单提交 */
            $("#appointmentForm").submit(function(){
                var realclass=$("#realclass");
                var phonenum = $("#phonenum");
                var outlinedescribe=$("#outlinedescribe");
                var detaildescribe=$("#detaildescribe");
                var appointdate=$("#appointdate");
                
                var msg = "";
                if ($.trim(realclass.val()) == ""){
                    msg = "班级不能为空！";
                    realclass.focus();
                }else if ($.trim(phonenum.val()) == ""){
                    msg = "手机号码不能为空！";
                    phonenum.focus();
                }else if (!/^1[3|5|8]\d{9}$/.test($.trim(phonenum.val()))){
                    msg = "手机号码格式不正确！";
                    phonenum.focus();
                }else if($.trim(outlinedescribe.val())==""){
                	msg="概要描述不能为空！";
                	outlinedescribe.focus();
                }else if($.trim(detaildescribe.val())==""){
                	msg="详细描述不能为空！";
                	detaildescribe.focus();
                }else if($.trim(appointdate.val())==""){
                	msg="预约时间不能为空！";
                	appointdate.focus();
                }
                
                if (msg != ""){
                    $.ligerDialog.error(msg);
                    return false;
                }else{
                    return true;
                }
                $("#appointmentForm").submit();
            });
            
        });
        
        </script>
    </head>
    <body>
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
             <tr><td height="10"></td></tr>
             <tr>
                <td width="15" height="32"><img src="${ctx }/images/main_locleft.gif" width="15" height="32"></td>
                <td class="main_locbg font2"><img src="${ctx }/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：预约中心  &gt;  进行预约</td>
                <td width="15" height="32"><img src="${ctx }/images/main_locright.gif" width="15" height="32"></td>
             </tr>
        </table>
    
         <!-- 请求异常错误  -->
        <table width="100%" height="90%" border="0" cellpadding="10" cellspacing="0" class="main_tabbor">
              <tr valign="top">
                <td>
                <form id="appointmentForm" name="appointmentForm" action="${ctx }/appointment/addMyAppointment" method="post">
                  <!-- 隐藏表单，flag表示添加标记 -->
                     <input type="hidden" name="flag" value="2">
                  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
                        <tr><td class="font3 fftd">
                                    班级：<input type="text" name="realclass" size="30" id="realclass"/>
                              <span style="color: #ff0000;"></span>
                            </td>
                        </tr>
                        <tr><td class="main_tdbor"></td></tr>
                        
                        <tr><td class="font3 fftd">
                                    电话：<input type="text" name="phonenum" size="30" id="phonenum"/>
                              <span style="color: #ff0000;"></span>
                            </td>
                        </tr>
                        <tr><td class="main_tdbor"></td></tr>
                        <tr><td class="font3 fftd">概要描述：<br/>
                            <textarea name="outlinedescribe" cols="88" rows="2" id="outlinedescribe"></textarea>
                        </td></tr>
                        <tr><td class="main_tdbor"></td></tr>
                        
                        <tr><td class="font3 fftd">详细描述：<br/>
                            <textarea name="detaildescribe" cols="88" rows="6" id="detaildescribe"></textarea>
                        </td></tr>
                        <tr><td class="main_tdbor"></td></tr>
                        
                        <tr><td class="font3 fftd">
                                    预约时间：
                              <input class="Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});" 
                    name="appointdate" id="appointdate"/>
                              <span style="color: #ff0000;"></span>
                            </td>
                        </tr>
                       <tr><td class="main_tdbor"></td></tr> 
                        <tr><td class="font3 fftd">
                                <input type="submit" value="添加">
                                <input type="reset" value="重置">
                        </td></tr>
                        <tr><td class="main_tdbor"></td></tr>
                    
                  </table>
                  </form>
                </td>
              </tr>
        </table>
        
        <div style="height:10px;"></div>
    </body>
</html>