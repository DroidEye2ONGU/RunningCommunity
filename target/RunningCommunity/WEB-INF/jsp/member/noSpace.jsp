<%--
  Created by IntelliJ IDEA.
  User: DroidEye
  Date: 2018/9/27
  Time: 16:53
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
                <table style="margin-top:50px;border:1px solid #cccccc;" width="500"
                       align="center">
                    <tr>
                        <td class='titlehead'>
                            <img src="/resources/images/icon01.gif" width="14" height="14"/>
                            &nbsp;&nbsp;
                            <b>系统提示</b></td>
                    </tr>
                    <tr>
                        <td>
                            <table width="100%" height="150">
                                <tr>
                                    <td width="140" align="center">
                                        <img src="/resources/images/error.gif" width="80" height="72"/></td>
                                    <td>
                                        <div style="padding-bottom:15px;" class="fontblue">
                                            <font color="red">对不起，您还没有创建个性化空间！</font>
                                        </div>
                                        <div style="padding-bottom:15px;">
                                            <li>
                                                <a href="/member/toCreateSpacePage" class="textlink02"><font
                                                        color="red">点击这里</font></a>创建个性化空间！
                                            </li>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3" height="30"></td>
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