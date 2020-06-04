/**register.js**/
/**
 * 
 */
s = new Array("loginname", "password", "passtwo","phonenum");

function loginnameCheck()
{
    var loginname = document.getElementById("loginname");
    if(!/^\w{6,20}$/.test(loginname.value)) document.getElementById("1").innerHTML="登陆名长度必须是6~20之间";
    else document.getElementById("1").innerHTML="";
}

function passCheck()
{
    Pass = document.getElementById("password");
    if(!/^\w{6,20}$/.test(Pass.value)) document.getElementById("2").innerHTML="密码长度必须是6~20之间";
    else document.getElementById("2").innerHTML="";
}

function passTwoCheck()
{
    PassTwo = document.getElementById("passtwo");
    if(PassTwo.value != Pass.value) document.getElementById("3").innerHTML="两次输入密码不一致";
    else document.getElementById("3").innerHTML="";
}

function phonenumCheck()
{
	var phonenum = document.getElementById("phonenum");
    if(!/^[1][358][0-9]{9}$/.test(phonenum.value)) document.getElementById("6").innerHTML="手机号码格式不正确";
    else document.getElementById("6").innerHTML="";
}

function SubmitCheck()
{
    var f = true;
    for(i = 0; i < 4; ++i)
        if(document.getElementById(s[i]).value == "")
        {
            f = false;
            break;
        }
    for(j = 1; j <= 4; ++j)
        if(document.getElementById("" + j).innerHTML != ""){
            f = false;
            break;
        }
    if(f) {
        document.getElementById("submit").disabled = false;
        document.getElementById("submit").style.background = "red";
    }
    setTimeout("SubmitCheck()", 1000);
}