<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DroidEye
  Date: 2018/9/28
  Time: 15:57
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

        function addFriend() {
            cCount = getCheckedCount("friendName");
            if (cCount == 0) {
                alert("请至少选择一条信息!");
                return;
            }
            if (confirm("确定要添加到黑名单吗？") == false) {
                return false;
            }

            document.forms.buddyListForm.action = "/messenger/addManyToBlackRecord";
            document.forms.buddyListForm.submit();
        }

        function delBuddy() {
            cCount = getCheckedCount("friendName");
            if (cCount == 0) {
                alert("请至少选择一条信息!");
                return;
            }
            if (confirm("确定要删除这些好友吗？") == false) {
                return false;
            }
            document.forms.buddyListForm.action = "/messenger/deleteFriend";
            document.forms.buddyListForm.submit();
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
                <li><a href="/member/toMatchFriendPage" title="好友速配"><span>好友速配</span></a></li>
                <li><a href="/member/toBuddyListPage" title="好友名单"><span><b>好友名单</b></span></a></li>
                <li><a href="/member/toBlackListPage" title="黑名单"><span>黑名单</span></a></li>
            </ul>
        </div>
        <br/><br/>

        <div id="content" align="center">
            <div id="center">
                <BR/><BR/>
                <form method="post" name="buddyListForm" >
                    <table width="600" align="center" cellpadding="0" cellspacing="0">
                        <thead>
                        <tr>
                            <td><h4>系统讯息 - 好友列表</h4></td>
                            <td valign="bottom">
							<span onclick="javascript:addFriend();">
								<img src="/resources/images/icon06.gif" height="14"/>&nbsp;添加到黑名单</span>
                                <span onclick="javascript:delBuddy();">
								<img src="/resources/images/delete.gif" height="14"/>&nbsp;从列表中删除</span>
                            </td>
                        </tr>

                        </thead>

                        <tr>
                            <td colspan="2" width="100%">
                                <table width="100%">
                                    <thead>
                                    <tr>
                                        <th width="10%" align="center" class="line1" scope="col">
                                            <b>选择</b>
                                        </th>
                                        <th width="20%" align="center" scope="col">
                                            <b>姓名</b>
                                        </th>
                                        <th width="20%" align="center" scope="col">
                                            <b>性别</b>
                                        </th>
                                        <th width="20%" align="center" scope="col">
                                            <b>年龄</b>
                                        </th>
                                        <th width="20%" align="center" scope="col">
                                            <b>来自</b>
                                        </th>
                                        <th width="10%" align="center">
                                            <b>拉黑</b>
                                        </th>
                                    </tr>
                                    </thead>

                                    <tbody>

                                    <c:forEach items="${requestScope.friends}" var="friend">

                                        <tr>
                                            <th width="10%" align="center">
                                                <input type="checkbox" name="friendName"
                                                       value="${friend.nickName}"/>
                                            </th>
                                            <td width="20%" align="center">
                                                    ${friend.nickName}
                                            </td>
                                            <td width="20%" align="center">${friend.gender}</td>
                                            <td width="20%" align="center">${friend.age}</td>
                                            <td width="20%"
                                                align="center">${friend.provinceCity}</td>
                                            <td width="10%" align="center">
                                                <img src="/resources/images/button_delete.gif"
                                                     alt="移动到黑名单"
                                                     onclick="window.location='${path}/messenger/addOneToBlackRecord?blackName=${friend.nickName}'"
                                                     style="cursor:hand"/>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                    </tbody>

                                    <tfoot>
                                    <tr>
                                        <td align="right" width="30%" colspan="6">
                                            选择：<a href="#"
                                                  onclick="javascript:selAllCheckbox('selfName');">全部</a>-
                                            <a href="#"
                                               onclick="javascript:unselAllCheckbox('selfName');">不选</a>-
                                            <a href="#"
                                               onclick="javascript:reAllCheckbox('selfName');">反选</a>
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