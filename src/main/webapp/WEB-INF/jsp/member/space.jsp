<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DroidEye
  Date: 2018/9/27
  Time: 17:00
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
    <style type="text/css">
        <!--
        table {
            border-spacing: 1px;
            border: 1px solid #A2C0DA;
        }

        td, th {
            padding: 2px 5px;
            border-collapse: collapse;
            text-align: center;
            font-weight: normal;
        }

        thead tr th {
            background: #FFFFFF;
            border: 1px solid white;
            height: 40px
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
            background: #F5F5F5;
            border: 1px solid white;
            vertical-align: middle;
            text-align: center
        }

        -->
    </style>
    <script src="/resources/js/main.js" type="text/javascript"></script>
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

        <div id="content" align="center">

            <div id="center">

                <form action="result_Flow.htm" method="post" name="form1">
                    <table width="600">
                        <thead>
                        <tr>
                            <th><h4>个性化空间</h4></th>
                        </tr>
                        </thead>
                        <tr>
                            <td width="100%">
                                <table width="100%">
                                    <tbody>
                                    <tr>
                                        <td rowspan="5" width="30%" class="line4">
                                            <c:if test="${requestScope.memberspace.icon == null}">
                                                <img src="/resources/images/noHead.png" alt=Face>
                                            </c:if>
                                            <c:if test="${requestScope.memberspace.icon != null}">
                                                <img src="/iconfiles/${requestScope.memberspace.icon}"
                                                     alt=Face width="84"
                                                     height="84">
                                            </c:if>
                                        </td>
                                        <th width="25%" class="line1">
                                            昵称
                                        </th>
                                        <td width="45%">
                                            ${sessionScope.username}
                                        </td>
                                    </tr>

                                    <tr>
                                        <th class="line1">
                                            性别
                                        </th>
                                        <td>
                                            ${sessionScope.user.gender}
                                        </td>
                                    </tr>
                                    <tr>
                                        <th class="line1">
                                            积分
                                        </th>
                                        <td>
                                            ${sessionScope.user.point}
                                        </td>
                                    </tr>
                                    <tr>
                                        <th class="line1">
                                            等级
                                        </th>
                                        <td>
                                            ${requestScope.graderecord.gradename}
                                        </td>
                                    </tr>
                                    <tr>
                                        <th class="line1">
                                            来自
                                        </th>
                                        <td>
                                            ${sessionScope.user.provinceCity}
                                        </td>
                                    </tr>
                                    <tr>
                                        <th class="line1">
                                            跑步习惯与主张
                                        </th>
                                        <td colspan="2" height="90">
									<span>&nbsp;&nbsp;&nbsp;
										${requestScope.memberspace.runTime}，喜欢
                                        <c:if test="${requestScope.memberspace.runHabit eq '独自'}">
                                            一个人
                                        </c:if>
                                        <c:if test="${requestScope.memberspace.runHabit eq '结伴'}">
                                            和其他人
                                        </c:if>
                                        在
                                        ${requestScope.memberspace.runPlace}跑步，
                                        最喜欢的体育明星是${requestScope.memberspace.runStar}。
										愿意成为我的跑友者请与我(${sessionScope.user.phone})联系。
											本人对跑步的主张是：${requestScope.memberspace.opinion}！
									</span>
                                        </td>
                                    </tr>
                                    </tbody>
                                    <tfoot>
                                    <tr>
                                        <td colspan="3">
                                            <input type="button" value="修改"
                                                   onclick="location.href='/member/toCreateSpacePage';"
                                                   style="width: 60;height: 25"/>
                                            <input type="button" value="返回"
                                                   onclick="location.href='/memebr/toMemberActivityPage' ;"
                                                   style="width: 60;height: 25"/>
                                        </td>
                                    </tr>
                                    </tfoot>
                                </table>
                            </td>

                        </tr>

                    </table>
                </form>

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