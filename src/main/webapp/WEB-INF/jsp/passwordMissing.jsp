<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DroidEye
  Date: 2018/9/25
  Time: 18:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>跑步社区</title>
    <link rel="stylesheet" type="text/css"  href="/resources/style/main.css"/>
    <link rel="stylesheet" type="text/css"  href="/resources/style/style1.css"/>
    <script src="/resources/js/main.js" type="text/javascript"></script>
</head>

<script type="text/javascript"
        src="/resources/js/jquery-3.1.1.js"></script>

<script type="text/javascript">
    function checkUserExist() {
        var username = $.trim($("#username").val());

        $.ajax({
                   type: "POST",
                   url: "${path}/member/findPasswordQuestion",
                   data: "nickName=" + username,
                   dataType: "text",

                   success: function (result) {
                       // alert(result)
                       var responseText = JSON.stringify(result);

                       if (result == "0") {
                           $("#userSpan").html("未找到该用户,请核对您的用户名");
                           $("#userSpan").css("color", "red");

                           //清空提示问题值
                           $("#passwordQuestionSpan").html("");

                           //锁定提交按钮
                           $("#tijiao").attr("disabled", "disabled");
                       } else {
                           //获取服务器的返回值
                           $("#userSpan").html("已找到您的用户");
                           $("#userSpan").css("color", "green");

                           //设置表单value值
                           var input = document.getElementById("passwdQuestion");
                           input.value = result;

                           //设置提示问题
                           $("#passwordQuestionSpan").html(result);

                           //解锁提交按钮
                           $("#tijiao").removeAttr("disabled");

                       }
                   }
               })
    }
</script>

<script type="text/javascript">
    function disabledThenSubmit() {
        //禁用提交按钮
        $("#tijiao").attr("disabled", "disabled");
        alert("正在重置您的密码,请稍后");
    }
</script>

<body>
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

        <div id="content" align="center">

            <div id="center">

                <div id="ltd">
                    <h2>输入下面信息，系统会帮你找回密码！</h2>
                    <TABLE style="MARGIN: 5px 0px" cellSpacing=0 cellPadding=0 width="100%"
                           border=0>
                        <TBODY align="left">
                        <TR>
                            <TD style="PADDING-TOP: 0px" vAlign=top align=middle width=24
                                rowSpan=3><IMG src="/resources/images/icon03.gif" width="16"
                                               height=16></TD>
                            <TD class=fontgreen style="PADDING-TOP: 2px" width=556><B>系统提醒</B>
                            </TD>
                        </TR>
                        <TR>
                            <TD class=fontgray style="PADDING-TOP: 2px" colSpan=2>
                                <OL>
                                    <LI><FONT color=#ff0000>请确保您输入的用户名存在</FONT>
                                    <LI><FONT color=#ff0000>密码提示问题和答案完全正确，方可找回密码</FONT>
                                    </LI>
                                </OL>
                            </TD>
                        </TR>
                        </TBODY>
                    </TABLE>
                    <FORM action="/member/rePassword"
                          method="post" onsubmit="return disabledThenSubmit()">
                        <TABLE width="100%" align=center border=0>
                            <!--DWLayoutTable-->
                            <TBODY>
                            <TR>
                                <TD align=right width="40%">用户名：</TD>
                                <TD align=left width="60%">
                                    <INPUT class=input1 name="userName" id="username"
                                           onblur="checkUserExist()"/>
                                    &nbsp;&nbsp;
                                    <span id="userSpan"></span>
                                </TD>
                            </TR>
                            <TR>
                                <TD align=right width="40%">密码提示问题：
                                </TD>
                                <TD align=left width="60%">
                                    <INPUT class=input1 name="passwdQuestion" id="passwdQuestion"
                                           value="" type="hidden"/>

                                    <span id="passwordQuestionSpan"></span>
                                </TD>
                            </TR>
                            <TR>
                                <TD align=right width="40%">密码提示答案：</TD>
                                <TD align=left width="60%">
                                    <INPUT class=input1 name="passwdAnswer"/>
                                    &nbsp;&nbsp;
                                    <c:if test="${not empty requestScope.answer or requestScope.answer == null}">
                                        <font color="red">${requestScope.answer}</font>
                                    </c:if>

                                </TD>
                            </TR>
                            <TR>
                                <TD colSpan=2>
                                    <DIV align=center><INPUT style="CURSOR: hand" type=submit
                                                             id="tijiao" disabled="disabled"
                                                             value="提交" />
                                        &nbsp;&nbsp; <INPUT style="CURSOR: hand" type=reset
                                                            value="重置"/>
                                    </DIV>
                                </TD>
                            </TR>
                            <tr>
                                <td height="13"></td>
                                <td></td>
                            </tr>
                            </TBODY>
                        </TABLE>
                    </FORM>
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

