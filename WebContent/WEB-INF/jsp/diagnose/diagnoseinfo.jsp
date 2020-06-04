<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>电脑医院信息管理与预约系统 ——自诊中心</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="pragma" content="no-cache" />
    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="expires" content="0" />    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
    <meta http-equiv="description" content="This is my page" />
    <link href="${ctx }/css/css.css" type="text/css" rel="stylesheet" />
    <link href="${ctx }/css/pager.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
    <link href="${ctx }/js/ligerUI/skins/Aqua/css/ligerui-dialog.css" rel="stylesheet" type="text/css" />  
    <script src="${ctx }/js/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="${ctx }/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="${ctx }/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
    <script src="${ctx }/js/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function(){
            
            var boxs = $("input[type='checkbox'][id^='box_']");
            /** 给全选按钮绑定点击事件  */
            $("#checkAll").click(function(){
                // this是checkAll  this.checked是true
                // 所有数据行的选中状态与全选的状态一致
                boxs.attr("checked",this.checked);
            })
            
            /** 给每个数据行绑定点击事件：判断如果数据行都选中全选也应该选中，反之  */
            boxs.click(function(event){
                /** 去掉复选按钮的事件传播：点击复选会触发行点击：因为复选在行中 */
                event.stopPropagation();
                
                /** 判断当前选中的数据行有多少个  */
                var checkedBoxs = boxs.filter(":checked");
                /** 判断选中的总行数是否等于总行数：以便控制全选按钮的状态   */
                $("#checkAll").attr("checked",checkedBoxs.length == boxs.length);
            })
            
            /** 给数据行绑定鼠标覆盖以及鼠标移开事件  */
            $("tr[id^='data_']").hover(function(){
                $(this).css("backgroundColor","#eeccff");
            },function(){
                $(this).css("backgroundColor","#ffffff");
            }).click(function(){
                /** 控制该行是否需要被选中 */
                /** 获取此行的复选框id */
                var checkboxId = this.id.replace("data_","box_");
                
                /** 触发本行的复选点击 */
                $("#"+checkboxId).trigger("click");
            })
            
            /** 下载文档功能 */
            $("a[id^='down_']").click(function(){
                /** 得到需要下载的文档的id */
                var id = this.id.replace("down_","");
                /** 下载该文档 */
                window.location = "${ctx}/diagnose/downLoad?id="+id;
            })
            
            
            
        })
        
        function down(id){
            $("a[id='down_"+id+"']").trigger("click");
        }
        
    </script>
</head>
<body>
    <!-- 导航 -->
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr><td height="10"></td></tr>
      <tr>
        <td width="15" height="32"><img src="${ctx }/images/main_locleft.gif" width="15" height="32"></td>
        <td class="main_locbg font2"><img src="${ctx }/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：自诊中心&gt; 所有故障信息</td>
        <td width="15" height="32"><img src="${ctx }/images/main_locright.gif" width="15" height="32"></td>
      </tr>
    </table>
    
    <table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
      <!-- 查询区  -->
      <tr valign="top">
        <td height="30">
          <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
            <tr>
              <td class="fftd">
                  <form name="diagnoseform" method="post" id="diagnoseform" action="${ctx }/diagnose/selectAllDiagnose">
                    <table width="100%" border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td class="font3">
                            标题：<input type="text" name="title" />
                            描述：<input type="text" name="remark" />
                            上传者：<input type="text" name="author" />
                            <input type="submit"  value="搜索"/>
                        </td>
                      </tr>
                    </table>
                </form>
              </td>
            </tr>
          </table>
        </td>
      </tr>
      
      <!-- 数据展示区 -->
      <tr valign="top">
        <td height="20">
          <table width="100%" border="1" cellpadding="5" cellspacing="0" style="border:#c2c6cc 1px solid; border-collapse:collapse;">
            <tr class="main_trbg_tit" align="center">
              <td><input type="checkbox" id="checkAll" ></td>
              <td>标题</td>
              <td>创建时间</td>
              <td>创建人</td>
              <td>描述</td>
              <td>预览</td>
              <td>下载</td>
            </tr>
            <c:forEach items="${requestScope.diagnoses}" var="diagnose" varStatus="stat">
                <tr ondblclick="down(${diagnose.id});"  class="main_trbg" align="center" id="data_${stat.index}">
                    <td><input type="checkbox" id="box_${stat.index}" value="${diagnose.id}"></td>
                     <td>${diagnose.title }</td>
                     <td>
                          <f:formatDate value="${diagnose.createdate}" 
                                type="date" dateStyle="long"/>
                      </td>
                      <td>${diagnose.author }</td>
                      <td>${diagnose.remark }</td>
                      
                     <td align="center"  width="40px;"><a href="${diagnose.filepath }">
                            <img title="预览" src="${ctx }/images/prev.gif"/></a>
                      </td>
                      
                      <td align="center"  width="40px;"><a href="#" id="down_${diagnose.id }">
                            <img width="20" height="20" title="下载" src="${ctx }/images/downLoad.png"/></a>
                      </td>
                </tr>
            </c:forEach>
             

          </table>
        </td>
      </tr>
      <!-- 分页标签 -->
      <tr valign="top"><td align="center" class="font3">
             <fkjava:pager 
                pageIndex="${pageModel.pageIndex}" 
                pageSize="${pageModel.pageSize}" 
                recordCount="${pageModel.recordCount}" 
                style="digg"
                submitUrl="${ctx}/diagnose/selectAllDiagnose?pageIndex={0}"
                />
      </td></tr>
    </table>
    <div style="height:10px;"></div>
</body>
</html>