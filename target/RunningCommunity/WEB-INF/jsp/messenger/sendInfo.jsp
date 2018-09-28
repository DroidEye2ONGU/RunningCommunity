<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DroidEye
  Date: 2018/9/29
  Time: 0:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>跑步社区</title>
    <link rel="stylesheet" type="text/css" href="/resources/style/main.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/style/style1.css"/>
    <script src="/resources/js/main.js" type="text/javascript"></script>
    <script type="text/javascript" src="/resources/js/jquery-3.1.1.js"></script>
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
        }

        thead tr th {
            height: 35px;
            background: #FFFFFF;
            border: 1px solid white;
        }

        thead tr th.line1 {
            background: #D3E5FD;
        }

        thead tr th.line4 {
            background: #C6C6C6;
        }

        tbody tr td {
            height: 30px;
            background: #CBE2FB;
            border: 1px solid white;
            vertical-align: middle;
        }

        tbody tr td.line4 {
            background: #D5D6D8;
        }

        tbody tr th {
            height: 30px;
            background: #DFEDFF;
            border: 1px solid white;
            vertical-align: middle;
            text-align: right
        }

        tfoot.tr th {
            height: 30px;
            background: #FFFFFF;
            border: 1px solid white;
            vertical-align: middle;
            text-align: center
        }

        -->
    </style>

</head>
<body>

<c:if test="${requestScope.fromController != null}">
    <script>
        alert("发送成功")
    </script>
</c:if>

<script type="text/javascript">
    function checkIsExist() {
        var receiver = $.trim($("#receiver").val());
        var mySelf = "<%=session.getAttribute("username")%>";

        if (receiver == "") {
            $("#receiverSpan").html("请输入收件人");
            $("#receiverSpan").css("color", "red");

            //使提交按钮禁用
            $("#tijiao").attr("disbaled", "disabled");
        } else if (receiver == mySelf) {
            $("#receiverSpan").html("不能向自己发送信息");
            $("#receiverSpan").css("color", "red");

            //使提交按钮禁用
            $("#tijiao").attr("disbaled", "disabled");
        } else {
            $.ajax({
                       type: "POST",
                       url: "${path}/member/sameNickNameCheck",
                       data: "username=" + receiver,
                       dataType: "text",
                       success: function (result) {
                           var eval1 = eval(result);
                           if (eval1 == 0) {
                               $("#receiverSpan").html("收件人存在,您可向" + receiver + "发送消息");
                               $("#receiverSpan").css("color", "green");

                               $("#tijiao").removeAttr("disabled");
                           } else {
                               $("#receiverSpan").html("收件人不存在");
                               $("#receiverSpan").css("color", "red");

                               $("#tijiao").attr("disbaled", "disabled");
                           }
                       }
                   });
        }
    }
</script>

<script type="text/javascript">
    function checkAutoComplete() {
        var title = $.trim($("#title").val());
        var messageContent = $.trim($("#messageContent").val());

        var flag = true;

        if (title == "") {
            $("#titleSpan").html("请输入标题!");
            $("#titleSpan").css("color", "red");

            flag = false;
        } else {
            flag = true;
        }

        if (messageContent == "") {
            $("#messageContentSpan").html("请输入内容!");
            $("#messageContentSpan").css("color", "red");

            flag = false;
        } else {
            flag = true;
        }

        return flag;
    }
</script>

<script type="text/javascript">
    function relockSubmit() {
        $("#tijiao").removeAttr("disabled");
    }
</script>

<div id="btm">
    <div id="main">

        <div id="header">
            <div id="top"></div>
            <div id="logo">
                <h1>跑步社区</h1></div>
            <div id="logout">
                <a href="/member/logout">注 销</a>
            </div>
            <div id="mainnav">
                <ul>
                    <li><a href="/memebr/toMemberActivityPage">首页</a></li>
                    <li><a href="../other/musicrun.html">音乐跑不停</a></li>
                    <li><a href="../other/equip.html">跑步装备库</a></li>
                    <li><a href="../other/guide.html">专业跑步指南</a></li>
                    <li><a href="../other/bbs.html">跑步论坛</a></li>

                </ul>
                <span></span>
            </div>
        </div>
        <div id="tabs1">
            <ul>
                <li><a href="/member/toWriteMessagePage" title="写纸条"><span><b>写纸条</b></span></a>
                </li>
                <li><a href="/member/toMessagePage" title="收件箱"><span>收件箱</span></a></li>
                <li><a href="/member/toSendMessagePage" title="发件箱"><span>发件箱</span></a></li>
            </ul>

        </div>
        <div id="content" align="center">
            <div id="center"><br/><br/>
                <table width="600">
                    <thead>
                    <tr>
                        <th colspan="2">
                            <h4>
                                <c:if test="${requestScope.receiver == null}">
                                    发送讯息
                                </c:if>
                                <c:if test="${requestScope.receiver != null}">
                                    向${requestScope.receiver}发送讯息
                                </c:if>
                            </h4>
                        </th>
                    </tr>
                    </thead>

                    <tr>
                        <td width="100%">
                            <form name="sendInfoForm" action="/message/sendMessage"
                                  method="post" onsubmit="return checkAutoComplete();">
                                <table width="100%">
                                    <tbody>
                                    <tr>
                                        <th width="30%" class="line1" scope="col">收信人：</th>
                                        <td>
                                            <c:if test="${requestScope.receiver == null}">
                                                <input type="text" name="receiver"
                                                       style="width: 250;height: 25" id="receiver"
                                                       onblur="checkIsExist()"/>
                                                &nbsp;&nbsp;
                                                <span id="receiverSpan"></span>
                                            </c:if>
                                            <c:if test="${requestScope.receiver != null}">
                                                <input type="text" name="receiver"
                                                       style="width: 250;height: 25" id="receiver"
                                                       value="${requestScope.receiver}"
                                                       readonly="readonly"
                                                       placeholder="${requestScope.receiver}"/>
                                            </c:if>

                                        </td>
                                    </tr>
                                    <tr>
                                        <th>主题：</th>
                                        <td>
                                            <c:if test="${requestScope.receiver == null}">
                                                <input type="text" name="title" id="title"
                                                       style="width: 350;height: 25"/>
                                            </c:if>
                                            <c:if test="${requestScope.receiver != null}">
                                                <input type="text" name="title" id="title"
                                                       style="width: 350;height: 25"
                                                       onblur="relockSubmit()"/>
                                            </c:if>
                                            &nbsp;&nbsp;
                                            <span id="titleSpan"></span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>消息内容：</th>
                                        <td>
                                            <textarea name="content" rows="8"
                                                      cols="55" id="messageContent">
                                            </textarea>
                                            &nbsp;&nbsp;
                                            <span id="messageContentSpan"></span>
                                        </td>
                                    </tr>
                                    </tbody>
                                    <tfoot>
                                    <tr>
                                        <th colspan="2">
                                            <c:if test="${requestScope.receiver == null}">
                                                <input type="submit" value="发送" id="tijiao"
                                                       disabled="disabled"/>
                                            </c:if>
                                            <c:if test="${requestScope.receiver != null}">
                                                <input type="submit" value="发送" id="tijiao"/>
                                            </c:if>

                                            <input type="reset" value="重置"/>
                                        </th>
                                    </tr>
                                    </tfoot>

                                </table>
                            </form>
                        </td>

                    </tr>

                </table>
                <br/><br/>
                <div id="hots">
                    <h2>我的地盘</h2>
                    <ul>
                        <li>
                            <div>
                                <img src="/resources/images/a.gif"/>
                                <a href="/member/toModifyPage">基本信息</a>
                                <p>可修改自己的基本信息</p>
                            </div>
                        </li>
                        <li>
                            <div>
                                <img src="/resources/images/b.gif"/>
                                <a href="/member/toMessagePage">我的信箱</a>
                                <p>写信息、收件箱、发件箱</p>
                            </div>
                        </li>
                        <li>
                            <div>
                                <img src="/resources/images/c.gif"/>
                                <a href="/member/toBuddyListPage">我的好友</a>
                                <p>好友管理及黑名单</p>
                            </div>
                        </li>
                        <li>
                            <div>
                                <img src="/resources/images/d.gif"/>
                                <a href="/member/toSpacePage">个性空间</a>
                                <p>创建自己的个性空间</p>
                            </div>
                        </li>
                    </ul>
                </div>

            </div>
            <div id="right">
                <h2>&nbsp;</h2>
                <ul>
                    <li></li>
                </ul>
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