<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DroidEye
  Date: 2018/9/23
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>跑步社区</title>
    <
    <style type="text/css">
        @import "/resources/style/main.css";
        @import "/resources/style/style1.css";
        /*@import "/resources/style/style.css";*/
    </style>

    <script src="/resources/js/main.js" type="text/javascript"></script>
    <script src="/resources/js/base.js" type="text/javascript"></script>
    <script src="/resources/js/common.js" type="text/javascript"></script>
    <style type="text/css">
        <!--
        table {
            border-spacing: 1px;
            border: 1px solid #A2C0DA;
        }

        td, th {
            padding: 2px 5px;
            border-collapse: collapse;
            text-align: left;
            font-weight: normal;
            text-align: left
        }

        thead tr th {
            height: 30px;
            background: #FFFFFF;
            border: 1px solid white;
        }

        thead tr th.line1 {
            background: #FFFFFF;
        }

        thead tr th.line4 {
            background: #C6C6C6;
        }

        tbody tr td {
            height: 35px;
            background: #CBE2FB;
            border: 1px solid white;
            vertical-align: middle;
        }

        tbody tr td.line4 {
            background: #D5D6D8;
        }

        tbody tr th {
            height: 35px;
            background: #DFEDFF;
            border: 1px solid white;
            vertical-align: middle;
        }

        tfoot tr td {
            height: 35px;
            background: #FFFFFF;
            border: 1px solid white;
            vertical-align: middle;
            text-align: center
        }

        -->
    </style>
</head>
<body>
<script type="text/javascript"
        src="/resources/js/jquery-3.1.1.js">
</script>
<script type="text/javascript">
    function checkIsExist() {
        // alert("开始异步方法")
        //获得表单内所填内容
        var username = $.trim($("#username").val());
        if (username == "") {
            $("#showResult").html("请输入您的用户名");
            $("#showResult").css("color", "red");
            //使提交按钮禁用
            $("#tijiao").attr("disabled", "disabled")
        } else {
            $.ajax({
                       type: "POST",   //http请求方式
                       url: "${path}/member/sameNickNameCheck",//发送给服务器的url
                       data: "username=" + username, //发送给服务器的参数
                       dataType: "text",  //告诉JQUERY返回的数据格式

                       success: function (result) {//用来接收返回的数据
                           var eval1 = eval(result);
                           if (eval1 == 1) {
                               $("#showResult").html("您的用户名可以注册");
                               $("#showResult").css("color", "green");

                               //关闭提交按钮禁用状态
                               $("#tijiao").removeAttr("disabled")
                           } else {
                               $("#showResult").html("您的用户名已被注册,请更改");
                               $("#showResult").css("color", "red");
                               //使提交按钮禁用
                               $("#tijiao").attr("disabled", "disabled")
                           }
                       }
                   });
        }
    }
</script>
<script>
    function checkRePass() {
        //获得password的值
        var password = $.trim($("#password").val());
        var rePassword = $.trim($("#repassword").val());

        if (password != rePassword) {
            //如果两次密码不同,则给出提示并锁定提交按钮
            $("#rePassWordResult").html("您两次输入密码不同,请检查");
            $("#rePassWordResult").css("color", "red");
            //锁定提交按钮
            $("#tijiao").attr("disabled", "disabled");
        } else {
            //若相同,解锁提交按钮
            $("#tijiao").removeAttr("disabled");
            $("#rePassWordResult").html("两次密码相同!");
            $("#rePassWordResult").css("color", "green");

        }
    }
</script>
<script>
    function checkAge() {
        var age = $.trim($("#age").val());
        // alert(age);
        var regex =  /^(?:10|[1-9][0-9]?|90)$/;
        if (!regex.test(age)) {
            $("#ageSpan").html("你输入的年龄不是数字或数字不在范围");
            $("#ageSpan").css("color", "red");
            //锁定提交按钮
            $("#tijiao").attr("disabled", "disabled");
        } else {
            $("#ageSpan").html("");
            $("#tijiao").removeAttr("disabled");
        }
    }
</script>
<div id="btm">
    <div id="main">

        <div id="header">
            <div id="top"></div>
            <div id="logo"><h1>跑步社区</h1></div>
            <div id="mainnav">
                <ul>
                    <li class="current"><a href="/toLoginPage">首页</a></li>
                    <li><a href="other/musicrun.html">音乐跑不停</a></li>
                    <li><a href="other/equip.html">跑步装备库</a></li>
                    <li><a href="other/guide.html">专业跑步指南</a></li>
                    <li><a href="other/bbs.html">跑步论坛</a></li>

                </ul>
                <span></span></div>
        </div>

        <div align="center" id="content">

            <div id="center">

                <div id="ltd">
                    <h2>新会员注册</h2>
                    <TABLE cellSpacing=0 cellPadding=0 border=0>
                        <TR>
                            <TD width="100%">
                                <form:form action="/member/register" modelAttribute="memberinfo"
                                           method="post"
                                           onSubmit="return validateRegisterForm(this);">
                                    <TABLE width="100%">
                                        <thead>
                                        <TR>
                                            <TH colSpan=3>
                                                <font face="Arial, Helvetica, sans-serif"><b>
                                                    请填写个人资料：（注意带有<FONT color=#ff0000>*</FONT>的项目必须填写）</b><br>
                                                </font>
                                                <font color="red">
                                                </font>
                                            </TH>
                                        </TR>
                                        </thead>
                                        <TBODY>
                                        <TR>
                                            <TH width="25%">
                                                <DIV align="right"><FONT color=#ff0000>*</FONT>昵称：
                                                </DIV>
                                            </TH>
                                            <TD width="30%"><INPUT maxlength="14" name="nickName"
                                                                   onblur="checkIsExist()"
                                                                   id="username"
                                                                   style="width:200px"/></TD>
                                                <%--<th id="n"></th>--%>
                                            <TH width="45%"><FONT
                                                    color=black>昵称可使用长度为0-16的任何字符</FONT><br>
                                                <span id="showResult"></span><br>
                                                <form:errors path="nickName" cssStyle="color: red"/>
                                            </TH>
                                        </TR>
                                        <TR>
                                            <TH>
                                                <DIV align="right"><FONT color=#ff0000>*</FONT> 密码：
                                                </DIV>
                                            </TH>
                                            <TD><INPUT type=password maxLength=14
                                                       style="width:200px" name="password"
                                                       id="password"/></TD>
                                            <TH>
                                                <FONT color=black>密码可使用长度为6-14的任何字符，并区分英文字母大小写</FONT>
                                                <br>
                                                <form:errors path="password" cssStyle="color: red"/>
                                            </TH>
                                        </TR>
                                        <TR>
                                            <TH>
                                                <DIV align=right><FONT color=#ff0000>*</FONT>密码确认：
                                                </DIV>
                                            </TH>
                                            <TD><INPUT type=password maxLength=14
                                                       style="width:200px" onblur="checkRePass()"
                                                       name="repassword" id="repassword"></TD>
                                            <TH><FONT color=black>请再输入一次密码</FONT><br>
                                                <span id="rePassWordResult"></span>
                                            </TH>
                                        </TR>
                                        <TR>
                                            <TH>
                                                <DIV align=right><FONT color=#ff0000>*</FONT>电子邮箱：
                                                </DIV>
                                            </TH>
                                            <TD><INPUT maxLength=30 style="width:200px"
                                                       name="email"/>
                                            </TD>
                                            <TH><FONT color=black>请输入您常用的其它电子邮箱</FONT>
                                                <br>
                                                <form:errors path="email" cssStyle="color: red"/>
                                            </TH>
                                        </TR>
                                        <TR>
                                            <TH>
                                                <DIV align=right><FONT color=#ff0000>*</FONT>
                                                    密码提示问题：
                                                </DIV>
                                            </TH>
                                            <TD><INPUT style="width:200px" name="passwordQuestion"/>
                                            </TD>
                                            <TH><FONT color=black>例如：我的哥哥是谁？</FONT>
                                                <br>
                                                <form:errors path="passwordQuestion"
                                                             cssStyle="color: red"/>
                                            </TH>
                                        </TR>
                                        <TR>
                                            <TH>
                                                <DIV align=right><FONT color=#ff0000>*</FONT>
                                                    密码提示答案：
                                                </DIV>
                                            </TH>
                                            <TD><INPUT style="width:200px" name="passwordAnswer"/>
                                            </TD>
                                            <TH><FONT color=black>注意：密码提示问题答案长度不少于6位</FONT>
                                                <br>
                                                <form:errors path="passwordAnswer"
                                                             cssStyle="color: red"/>
                                            </TH>
                                        </TR>
                                        <TR>
                                            <TH>
                                                <DIV align=right><FONT color=#ff0000>*</FONT> 性别：
                                                </DIV>
                                            </TH>
                                            <TD><INPUT type=radio CHECKED value="男" name="gender"/>男
                                                <INPUT type=radio value="女" name="gender"/>女
                                            </TD>
                                            <TH>&nbsp;<form:errors path="gender"
                                                                   cssStyle="color: red"/></TH>
                                        </TR>
                                        <TR>
                                            <TH>
                                                <DIV align=right>是否有推荐人：</DIV>
                                            </TH>
                                            <TD><INPUT onclick=javascript:changeText();
                                                       type=checkbox name=re><input type="text"
                                                                                    style="width:130px; display:none"
                                                                                    id="retext"
                                                                                    name="recommender"/>
                                            </TD>
                                            <TH><FONT color=black>如果有推荐人，请选择。并输入推荐人的会员名</FONT><br>
                                                <form:errors path="recommender"
                                                             cssStyle="color: red"/>
                                            </TH>
                                        </TR>

                                        <TR>
                                            <TH>
                                                <DIV align=right><FONT color=#ff0000>*</FONT>所在省份/城市：
                                                </DIV>
                                            </TH>
                                            <TD><SELECT name="provinceCity">
                                                <OPTION value=0
                                                        selected>请选择
                                                </OPTION>
                                                <c:forEach items="${requestScope.provinces}"
                                                           var="province">
                                                    <option value="${province}">${province}</option>
                                                </c:forEach>
                                            </SELECT>
                                            </TD>
                                            <TH>&nbsp;<from:errors path="provinceCity"
                                                                   cssStyle="color: red"/></TH>
                                        </TR>
                                        <TR>
                                            <TH>
                                                <DIV align=right><FONT color=#ff0000>*</FONT>年龄：
                                                </DIV>
                                            </TH>
                                            <TD><INPUT style="width:200px" name="age" id="age"
                                                       onblur="checkAge()"/></TD>
                                            <TH><FONT color=black>请输入年龄（必须为数字）</FONT><br>
                                                <span id="ageSpan"></span>
                                                <br><form:errors path="age" cssStyle="color: red"/>
                                            </TH>
                                        </TR>
                                        <TR>
                                            <TH>
                                                <DIV align=right>联系电话：</DIV>
                                            </TH>
                                            <TD><INPUT style="width:200px" name="phone"/></TD>
                                            <TH><FONT color=black>请输入区号和真实的电话，以便我们与您联系</FONT>
                                                <br>
                                                <form:errors path="phone" cssStyle="color: red"/>
                                            </TH>
                                        </TR>
                                        <TR>
                                            <TH>
                                                <DIV align=right>详细地址：</DIV>
                                            </TH>
                                            <TD><INPUT style="width:200px" name="address"/></TD>
                                            <TH>&nbsp;</TH>
                                        </TR>
                                        <tfoot>
                                        <TR>
                                            <TD colSpan=3>
                                                <DIV align=center><INPUT style="CURSOR: hand"
                                                                         id="tijiao"
                                                                         disabled="disabled"
                                                                         type=submit value="提交"
                                                />
                                                    &nbsp;&nbsp; <INPUT style="CURSOR: hand"
                                                                        type=reset value="重置"/>
                                                </DIV>
                                            </TD>
                                        </TR>
                                        </tfoot>
                                    </TABLE>
                                </form:form>
                            </TD>
                        </TR>
                    </TABLE>

                </div>

            </div>

            <div class="clear"></div>

        </div>

        <div id="footer">
            <div id="copyright">
                <div id="copy">
                    <p>CopyRight&copy;2018</p>
                    <p>跑步社区(By DroidEye)</p>
                </div>
            </div>
            <div id="bgbottom"></div>
        </div>

    </div>
</div>
</body>
</html>
