<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <title>电脑医院信息管理与预约系统 ——预览公告</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="pragma" content="no-cache" />
        <meta http-equiv="cache-control" content="no-cache" />
        <meta http-equiv="expires" content="0" />    
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
        <meta http-equiv="description" content="This is my page" />
        <link href="fkjava.ico" rel="shortcut icon" type="image/x-icon" />
        <style>
        	div
    		{
         		white-space:normal;
         		word-break:break-all;
	        	word-wrap:break-word; 
	         }
        </style>
    </head>
    <body>
         <center><h3>${notice.title }</h3></center>
         <br/>
         <div>
         	${notice.content}
         </div>
         
        
    </body>
</html>