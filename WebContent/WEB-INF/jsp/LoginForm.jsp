<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>电脑医院信息管理与预约系统</title>

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
<script src="js/gVerify.js"></script>
<script type="text/javascript">

!(function(window, document) {
    var size = 5;//设置验证码长度
    function GVerify(options) { //创建一个图形验证码对象，接收options对象为参数
        this.options = { //默认options参数值
            id: "", //容器Id
            canvasId: "verifyCanvas", //canvas的ID
            width: "100", //默认canvas宽度
            height: "30", //默认canvas高度
            type: "blend", //图形验证码默认类型blend:数字字母混合类型、number:纯数字、letter:纯字母
            code: "",
        }
        if(Object.prototype.toString.call(options) == "[object Object]"){//判断传入参数类型
            for(var i in options) { //根据传入的参数，修改默认参数值
                this.options[i] = options[i];
            }
        }else{
            this.options.id = options;
        }
        
        this.options.numArr = "0,1,2,3,4,5,6,7,8,9".split(",");
        this.options.letterArr = getAllLetter();

        this._init();
        this.refresh();
    }

    GVerify.prototype = {
        /**版本号**/
        version: '1.0.0',
        
        /**初始化方法**/
        _init: function() {
            var con = document.getElementById(this.options.id);
            var canvas = document.createElement("canvas");
            this.options.width = con.offsetWidth > 0 ? con.offsetWidth : "100";
            this.options.height = con.offsetHeight > 0 ? con.offsetHeight : "30";
            canvas.id = this.options.canvasId;
            canvas.width = this.options.width;
            canvas.height = this.options.height;
            canvas.style.cursor = "pointer";
            canvas.innerHTML = "您的浏览器版本不支持canvas";
            con.appendChild(canvas);
            var parent = this;
            canvas.onclick = function(){
                parent.refresh();
            }
        },
        
        /**生成验证码**/
        refresh: function() {
            this.options.code = "";
            var canvas = document.getElementById(this.options.canvasId);
            if(canvas.getContext) {
                var ctx = canvas.getContext('2d');
            }else{
                return;
            }
            
            ctx.textBaseline = "middle";

            ctx.fillStyle = randomColor(180, 240);
            ctx.fillRect(0, 0, this.options.width, this.options.height);

            if(this.options.type == "blend") { //判断验证码类型
                var txtArr = this.options.numArr.concat(this.options.letterArr);
            } else if(this.options.type == "number") {
                var txtArr = this.options.numArr;
            } else {
                var txtArr = this.options.letterArr;
            }

            for(var i = 1; i <=size; i++) {
                var txt = txtArr[randomNum(0, txtArr.length)];
                this.options.code += txt;
                ctx.font = randomNum(this.options.height/2, this.options.height) + 'px SimHei'; //随机生成字体大小
                ctx.fillStyle = randomColor(50, 160); //随机生成字体颜色        
                ctx.shadowOffsetX = randomNum(-3, 3);
                ctx.shadowOffsetY = randomNum(-3, 3);
                ctx.shadowBlur = randomNum(-3, 3);
                ctx.shadowColor = "rgba(0, 0, 0, 0.3)";
                var x = this.options.width / (size+1) * i;
                var y = this.options.height / 2;
                var deg = randomNum(-30, 30);
                /**设置旋转角度和坐标原点**/
                ctx.translate(x, y);
                ctx.rotate(deg * Math.PI / 180);
                ctx.fillText(txt, 0, 0);
                /**恢复旋转角度和坐标原点**/
                ctx.rotate(-deg * Math.PI / 180);
                ctx.translate(-x, -y);
            }
            /**绘制干扰线**/
            for(var i = 0; i < 4; i++) {
                ctx.strokeStyle = randomColor(40, 180);
                ctx.beginPath();
                ctx.moveTo(randomNum(0, this.options.width), randomNum(0, this.options.height));
                ctx.lineTo(randomNum(0, this.options.width), randomNum(0, this.options.height));
                ctx.stroke();
            }
            /**绘制干扰点**/
            for(var i = 0; i < this.options.width/4; i++) {
                ctx.fillStyle = randomColor(0, 255);
                ctx.beginPath();
                ctx.arc(randomNum(0, this.options.width), randomNum(0, this.options.height), 1, 0, 2 * Math.PI);
                ctx.fill();
            }
        },
        
        /**验证验证码**/
        validate: function(code){
            var code = code.toLowerCase();
            var v_code = this.options.code.toLowerCase();
            if(code == v_code){
                return true;
            }else{
                this.refresh();
                return false;
            }
        }
    }
    /**生成字母数组**/
    function getAllLetter() {
        var letterStr = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
        return letterStr.split(",");
    }
    /**生成一个随机数**/
    function randomNum(min, max) {
        return Math.floor(Math.random() * (max - min) + min);
    }
    /**生成一个随机色**/
    function randomColor(min, max) {
        var r = randomNum(min, max);
        var g = randomNum(min, max);
        var b = randomNum(min, max);
        return "rgb(" + r + "," + g + "," + b + ")";
    }
    window.GVerify = GVerify;
})(window, document);
  

</script>
</head>

<body style="font-family: 微软雅黑">
    <div class="page-lock">
    
        <div class="page-logo" style="margin-bottom: 2px">
            <a class="brand" style="font-size: 22px; color: #FFF;"> 电脑医院信息管理与预约系统</a>
        </div>
        <span style="padding-top: 5px;margin-left:50px;font-size:20px"><font color="red">${requestScope.message}</font></span>
        <form action="login" method="post" id="LoginForm">
            <div class="page-body">
            	<div>
            		<img class="page-lock-img" src="${ctx}/js/metronic/img/profile/logo2.jpg" alt="">
            	</div> 

                <div style="margin-left:250px;margin-top:20px">
                	<input type="text" placeholder="账号" id="loginname" name="loginname" value="${loginname}"><br>
                	<input type="password" placeholder="密码" id="password" name="password" value="${password}"><br>
                	
                	<input type="text" id="code_input" value="" placeholder="请输入验证码"/><br><input type="hidden" name="flag" id="flag" value="0">
                	<div id="v_container" style="margin-left:15px; margin-top:0px; width:100px; height:40px"></div><br>
                	
            	    <button type="submit" style="margin-left:0px" id="login-submit-btn" class="btn green" >登录 </button>	
                </div>
            </div>
        </form>
        
        <form action="registeForm" method="post" id="registeForm">
        	<button type="submit" style="margin-left:350px;margin-top:-140px" id="registe" class="btn green">注册</button>
        </form>
        
        
    </div>

   <script src="js/gVerify.js"></script>
    <script type="text/javascript">
    
    var verifyCode = new GVerify("v_container");
   // var flag=0;
    document.getElementById("login-submit-btn").onclick = function(){
        var res = verifyCode.validate(document.getElementById("code_input").value);
        if(res){
            document.getElementById("flag").value=1;
          //  $("#LoginForm").submit();
        }
        
    }
    
    
    $(function(){
        /** 按了回车键 */
       $(document).keydown(function(event){
           if(event.keyCode == 13){
               $("#login-submit-btn").trigger("click");
           }
       })

       /** 给登录按钮绑定点击事件  */
       $("#login-submit-btn").on("click",function(){
           /** 校验登录参数 ctrl+K */
           var loginname = $("#loginname").val();
           var password = $("#password").val();
           
          
           var msg = "";
           
           if(!/^\w{4,20}$/.test(loginname)){
                 msg = "登录名长度必须是4~20之间";
           }else if(!/^\w{4,20}$/.test(password)){
                 msg = "密码长度必须是4~20之间";
           }
           if(msg !=""||flag==0){
               $.ligerDialog.error(msg);
               return;
           }
           if(flag==1&&msg==""){
        	   $("#LoginForm").submit();
           }
           
       })
       
   })
   
   
       
    </script>
    
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