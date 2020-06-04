<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <title>电脑医院信息管理与预约系统 ——修改公告</title>
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
        <script type="text/javascript">
        
         $(function(){
        	 $("#appointstatus").val("${appointment.appointstatus}");
        	 
            /** 表单提交的校验 */
           $("#appointmentForm").submit(function(){ 
        	   return true;
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
                <td class="main_locbg font2"><img src="${ctx }/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：公告管理  &gt;  修改公告</td>
                <td width="15" height="32"><img src="${ctx }/images/main_locright.gif" width="15" height="32"></td>
             </tr>
        </table>
    
         <!-- 请求异常错误  -->
        <table width="100%" height="90%" border="0" cellpadding="10" cellspacing="0" class="main_tabbor">
              <tr valign="top">
                <td>
                <form id="appointmentForm" name="appointmentForm" action="${ctx }/appointment/updateAppointment" method="post">
                  <!-- 隐藏表单，flag表示添加标记 -->
                     <input type="hidden" name="flag" value="2">
                     <input type="hidden" name="id" value="${appointment.id }">
                  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
                        <tr><td class="font3 fftd">
                                    预约者：<input type="text" name="title" size="30" id="title" value="${appointment.realname }"/>
                              <span style="color: #ff0000;"></span>
                            </td>
                        </tr>
                     <tr><td class="main_tdbor"></td></tr>
                        
                        <tr><td class="font3 fftd">
                                    班级：<input type="text" name="outline" size="30" id="outline" value="${appointment.realclass }"/>
                              <span style="color: #ff0000;"></span>
                            </td>
                        </tr>
                       <tr><td class="main_tdbor"></td></tr>
                        
                        <tr><td class="font3 fftd">
                                    预约时间：<input type="text" name="outline" size="30" id="outline" value="${appointment.appointdate }"/>
                              <span style="color: #ff0000;"></span>
                            </td>
                        </tr> 
                     <tr><td class="main_tdbor"></td></tr>
                        
                        <tr><td class="font3 fftd">
                                    预约状态：<select name="appointstatus" style="width:143px;">
                                    <option value="0">--请选择--</option>
                                    <option value="未审核">未通过</option>
                                    <option value="审核通过">审核通过</option>
                                </select>
                              <span style="color: #ff0000;"></span>
                            </td>
                        </tr>   
						<tr><td class="main_tdbor"></td></tr>
						
                        <tr><td class="font3 fftd">详细内容：<br/>
                            <textarea name="content" cols="88" rows="11" id="content" >${appointment.detaildescribe }</textarea>
                        </td></tr>
                        <tr><td class="main_tdbor"></td></tr>
                        
                        <tr><td class="font3 fftd">
                                <input type="submit" value="修改">
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