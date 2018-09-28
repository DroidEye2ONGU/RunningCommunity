<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DroidEye
  Date: 2018/9/28
  Time: 18:28
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
            background: #F5F5F5;
            border: 1px solid white;
            vertical-align: middle;
            text-align: right
        }

        -->
    </style>

    <script src="/resources/js/common.js" type="text/javascript"></script>
    <script type="text/javascript" src="/resources/js/common.js"></script>
    <script type="text/javascript" src="/resources/js/jquery-3.1.1.js"></script>
</head>
<body>
<c:if test="${requestScope.fromController != null}">
    <script>
        alert("发送成功")
    </script>
</c:if>
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
                <li><a href="/member/toMatchFriendPage" title="好友速配"><span><b>好友速配</b></span></a>
                </li>
                <li><a href="/member/toBuddyListPage" title="好友名单"><span>好友名单</span></a></li>
                <li><a href="/member/toBlackListPage" title="黑名单"><span>黑名单</span></a></li>
            </ul>
        </div>
        <br/><br/>

        <div id="content" align="center">
            <div id="center">
                <br/><br/>
                <table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
                    <thead>
                    <tr>
                        <td colspan="2" height="40">
                            <h4>好友速配</h4>
                        </td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td width="100%">
                            <form action="/member/toMatchFriendPageByExactSearch" method="post"
                                  name="matchFriendForm">
                                <table width="100%" cellspacing="1" cellpadding="3" align="center">
                                    <tr>
                                        <td colspan="4">
                                            <b>找到您跑步世界的另一半，您可以 <a href="#"><FONT
                                                    color=#ff0000>马上速配</FONT></a> 一位跑友</b>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4"><b>更可以按要求检索</b></td>
                                    </tr>
                                    <tr>
                                        <td align="center">年龄范围：
                                            <c:if test="${requestScope.age == null}">
                                                <select name="age">
                                                    <option value="1">10-19岁</option>
                                                    <option value="2">20-29岁</option>
                                                    <option value="3">30-39岁</option>
                                                    <option value="4">40-49岁</option>
                                                    <option value="5">50-59岁</option>
                                                    <option value="6">60-69岁</option>
                                                    <option value="7">70-79岁</option>
                                                    <option value="0" selected="selected">不限
                                                    </option>
                                                </select>
                                            </c:if>
                                            <c:if test="${requestScope.age != null}">
                                                <select name="age">
                                                    <c:forEach items="${requestScope.ageMap}"
                                                               var="item">
                                                        <c:if test="${item.value eq requestScope.age}">
                                                            <option value="${item.value}"
                                                                    selected="selected">${item.key}
                                                            </option>
                                                        </c:if>
                                                        <c:if test="${item.value ne requestScope.age}">
                                                            <option value="${item.value}">
                                                                    ${item.key}
                                                            </option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                            </c:if>
                                        </td>
                                        <td align="center">性别：
                                            <c:if test="${requestScope.gender == null}">
                                                <select name="gender">
                                                    <option value="男">男</option>
                                                    <option value="女">女</option>
                                                    <option value="0" selected="selected">
                                                        不限
                                                    </option>
                                                </select>
                                            </c:if>
                                            <c:if test="${requestScope.gender != null}">
                                                <select name="gender">
                                                    <c:if test="${requestScope.gender eq '男'}">
                                                        <option value="男" selected="selected">男
                                                        </option>
                                                        <option value="女">女</option>
                                                        <option value="0">不限</option>
                                                    </c:if>
                                                    <c:if test="${requestScope.gender eq '女'}">
                                                        <option value="男">男</option>
                                                        <option value="女" selected="selected">女
                                                        </option>
                                                        <option value="0">不限</option>
                                                    </c:if>
                                                    <c:if test="${requestScope.gender eq '0'}">
                                                        <option value="男">男</option>
                                                        <option value="女">女</option>
                                                        <option value="0" selected="selected">
                                                            不限
                                                        </option>
                                                    </c:if>
                                                </select>
                                            </c:if>
                                        </td>
                                        <td align="center">所在省/城市：
                                            <c:if test="${requestScope.provinceCity == null}">
                                                <select name="provinceCity">
                                                    <option selected="selected" value="0">
                                                        不限
                                                    </option>
                                                    <c:forEach items="${requestScope.provinces}"
                                                               var="province">
                                                        <option value="${province}">${province}</option>
                                                    </c:forEach>
                                                </select>
                                            </c:if>
                                            <c:if test="${requestScope.provinceCity != null}">
                                                <select name="provinceCity">
                                                    <option value="0">
                                                        不限
                                                    </option>
                                                    <c:forEach items="${requestScope.provinces}"
                                                               var="province">
                                                        <c:if test="${province eq requestScope.provinceCity}">
                                                            <option value="${province}"
                                                                    selected="selected">
                                                                    ${province}
                                                            </option>
                                                        </c:if>
                                                        <c:if test="${province ne requestScope.provinceCity}">
                                                            <option value="${province}">
                                                                    ${province}
                                                            </option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                            </c:if>
                                        </td>
                                        <td align="center">
                                            <input type="submit" name="Submit2" value="开始检索"/>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </td>
                    </tr>
                    </tbody>

                    <table width="100%" cellspacing="1" cellpadding="3" align="center">
                        <tfoot>
                        <c:forEach items="${requestScope.allMembers}" var="member">
                            <tr height="25">
                                <td width="15%" align="center">${member.nickName}</td>
                                <td width="15%" align="center">${member.gender}</td>
                                <td width="15%" align="center">${member.age}</td>
                                <td width="15%" align="center">${member.provinceCity}</td>
                                <td width="20%" align="center">
                                    <a href="${path}/messenger/addFriend?friendName=${member.nickName}">添加好友</a>
                                </td>
                                <td width="20%" align="center">
                                    <a href="/member/toWriteMessagePage?receiver=${member.nickName}">发送短信</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tfoot>
                    </table>
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
