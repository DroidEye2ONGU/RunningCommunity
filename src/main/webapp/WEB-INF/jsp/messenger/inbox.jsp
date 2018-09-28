<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--
  Created by IntelliJ IDEA.
  User: DroidEye
  Date: 2018/9/28
  Time: 21:34
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
    <link rel="stylesheet" type="text/css" href="/resources/style/style.css"/>
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
            height: 50px;
            background: #B0D1FC;
            border: 1px solid white;
        }

        thead tr th.line1 {
            background: #D3E5FD;
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
        }

        -->
    </style>
    <script src="/resources/js/common.js" type="text/javascript"></script>
    <script type="text/javascript" src="/resources/js/common.js"></script>
    <script type="text/javascript">
        function delMessage() {
            cCount = getCheckedCount("messageId");
            if (cCount == 0) {
                alert("请选择至少一条信息!");
                return;
            }
            if (confirm("确定删除吗？") == false) {
                return false;
            }
            document.forms.inboxForm.action = "/message/deleteReceiverMessage";
            document.forms.inboxForm.submit();
        }

        function detailMessage() {
            cCount = getCheckedCount("messageId");
            if (cCount == 0) {
                alert("请选择一条信息!");
                return;
            }
            if (cCount > 1) {
                alert("对不起，一次只能查看一条信息!");
                return;
            }
            document.forms.inboxForm.action = "/message/seeMessageDetail";
            document.forms.inboxForm.submit();
        }
    </script>
</head>
<body>
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
                <li><a href="/member/toWriteMessagePage" title="写纸条"><span>写纸条</span></a></li>
                <li><a href="/member/toMessagePage" title="收件箱"><span><b>收件箱</b></span></a></li>
                <li><a href="/member/toSendMessagePage" title="发件箱"><span>发件箱</span></a></li>
            </ul>
        </div>
        <br/><br/>

        <div id="content" align="center">
            <div id="center">
                <br/><br/>
                <form method="post" name="inboxForm">
                    <table width="600" align="center" cellpadding="0" cellspacing="0">
                        <thead>
                        <tr>
                            <td width="70%"><h4>收件箱</h4></td>
                            <td>
							<span onclick="javascript:detailMessage();">
								<img src="/resources/images/icon06.gif" height="14"/>&nbsp;查看</span>
                                <span onclick="javascript:delMessage();">
								<img src="/resources/images/delete.gif" height="14"/>&nbsp;删除</span>
                            </td>
                        </tr>
                        </thead>

                        <tr>

                            <td width="100%" colspan="2">
                                <table width="100%">
                                    <thead>
                                    <tr>
                                        <th width="10%">
                                            <b>选择</b>
                                        </th>
                                        <th width="20%">
                                            <b>主题</b>
                                        </th>
                                        <th width="20%">
                                            <b>发信人</b>
                                        </th>
                                        <th width="30%">
                                            <b>发送时间</b>
                                        </th>
                                        <th width="20%">
                                            <b>状态</b>
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    <c:forEach items="${requestScope.messages}" var="message">
                                        <tr>
                                            <td width="10%">
                                                <input type="checkbox" name="messageId"
                                                       value="${message.id}"/>
                                            </td>
                                            <td width="20%">
                                                <a href="${path}/message/seeMessageDetail?messageId=${message.id}">
                                                        ${message.title}
                                                </a>
                                            </td>
                                            <td width="20%">${message.sender}</td>
                                            <td width="30%">
                                                <fmt:formatDate
                                                        value="${message.sendDate}"
                                                        pattern="yyyy年MM月dd日 HH:mm"/>
                                            </td>
                                            <td>
                                                <c:if test="${message.status eq '0'}">
                                                    <img src="/resources/images/icon09.gif"/>
                                                </c:if>
                                                <c:if test="${message.status eq '1'}">
                                                    <img src="/resources/images/icon10.gif"/>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>


                                    </tbody>

                                    <tfoot>
                                    <tr>
                                        <td align="right" width="30%" colspan="5">
                                            选择：<a href="#"
                                                  onclick="javascript:selAllCheckbox('messageId');">全部</a>-
                                            <a href="#"
                                               onclick="javascript:unselAllCheckbox('messageId');">不选</a>-
                                            <a href="#"
                                               onclick="javascript:reAllCheckbox('messageId');">反选</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="5">
                                            <img src="/resources/images/icon09.gif"/>未读短信
                                            <img src="/resources/images/icon10.gif"/>已读短信
                                        </td>

                                    </tr>
                                    </tfoot>
                                </table>

                            </td>

                        </tr>

                    </table>
                </form>
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
