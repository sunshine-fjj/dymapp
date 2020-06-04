<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>User Register</title>
    <script src="${ctx}/js/metronic/scripts/register.js"></script>
    <link href="${ctx}/js/metronic/plugins/bootstrap/css/bootstrap.min.css"
    rel="stylesheet" type="text/css" />
<link
    href="${ctx}/js/metronic/plugins/bootstrap/css/bootstrap-responsive.min.css"
    rel="stylesheet" type="text/css" />
<link
    href="${ctx}/js/metronic/plugins/font-awesome/css/font-awesome.min.css"
    rel="stylesheet" type="text/css" />
<link href="${ctx}/js/metronic/css/style-metro.css" rel="stylesheet"
    type="text/css" />
<link href="${ctx}/js/metronic/css/style.css" rel="stylesheet"
    type="text/css" />
<link href="${ctx}/js/metronic/css/style-responsive.css" rel="stylesheet"
    type="text/css" />
<link href="${ctx}/js/metronic/css/themes/default.css" rel="stylesheet"
    type="text/css" id="style_color" />
<link href="${ctx}/js/metronic/plugins/uniform/css/uniform.default.css"
    rel="stylesheet" type="text/css" />

<link href="${ctx}/js/metronic/css/pages/lock.css" rel="stylesheet"
    type="text/css" />

<link rel="shortcut icon" href="favicon.ico" />

<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>

<link href="${ctx }/js/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />  
 
<script src="${ctx }/js/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="${ctx }/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
<script src="${ctx }/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
<script src="${ctx }/js/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
</head>
<body onload="SubmitCheck()">
<!-- 从页面加载开始就检测是否可以提交 -->
    <div class="page-lock">
        <br/>
        <br/>
        <div class="page-logo" style="margin-bottom: 2px">
            <a class="brand" style="font-size: 22px; color: #FFF;"> 电脑医院信息管理与预约系统--用户注册</a>
        </div>
        
    <br/><br/>
    <form action="register" method="POST">
    	<div class="page-body">
    		<div>
    			<span></span>登录名称: <input onmouseout = "loginnameCheck()"  id="loginname"  type="text" name="loginname" /><span id="1"></span><br>
		  	  	<span></span>登陆密码: <input onmouseout = "passCheck()" id="password" type="password" name="password" ></input><span id="2"></span><br>
		  	  	<span></span>重复密码: <input onmouseout = "passTwoCheck()" id = "passtwo" type="password" name="passTwo" ></input><span id="3"></span><br>
		  	    <span></span>真实姓名: <input id="realname" type="text" name="realname" ></input><span id="4"></span><br>
		        <span></span>所在班级: <input id = "realclass" type="text" name="realclass" ></input><span id="5"></span><br>
		        <span></span>手机号码: <input onmouseout = "phonenumCheck()" id = "phonenum" type="text" name="phonenum" ></input><span id="6"></span><br>
    		</div>
    		<br><br>
    		<div>
    			<input id = "submit" class="btn green" disabled style="margin-left:150px" type="submit" value="立即注册"/><br>
    			
    		</div>
    	</div>
    	
   		
    </form>
    </div>
    <script
        src="${ctx}/js/metronic/plugins/jquery-ui/jquery-ui-1.10.1.custom.min.js"
        type="text/javascript">
    </script>
    
    <script src="${ctx}/js/metronic/plugins/bootstrap/js/bootstrap.min.js"
        type="text/javascript">
    </script>
    
    <script src="${ctx}/js/metronic/plugins/breakpoints/breakpoints.js"
        type="text/javascript">
    </script>
    
    <script src="${ctx}/js/metronic/plugins/uniform/jquery.uniform.min.js"
        type="text/javascript">
    </script>
   
    <script
        src="${ctx}/js/metronic/plugins/backstretch/jquery.backstretch.min.js"
        type="text/javascript">
    </script>
   
    <script src="${ctx}/js/metronic/scripts/app.js"></script>
    <script src="${ctx}/js/metronic/scripts/lock.js"></script>
   <script>
        $(function() {
            App.init();
            Lock.init();
        });
   </script> 
    
</body>
</html>

