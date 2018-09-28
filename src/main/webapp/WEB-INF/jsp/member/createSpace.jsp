<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%--
  Created by IntelliJ IDEA.
  User: DroidEye
  Date: 2018/9/27
  Time: 17:06
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
</head>
<body>
<script type="text/javascript"
        src="/resources/js/jquery-3.1.1.js">
</script>

<script>
    function checkForm() {
        var flag = true;

        var opinion = $.trim($("#opinion").val());
        var opinionP = $("#opinion").attr('placeholder');

        var runStar = $.trim($("#runStar").val());
        var runStarP = $("#runStar").attr('placeholder');

        var cellPhone = $.trim($("#cellPhone").val());
        var cellPhoneP = $("#cellPhone").attr('placeholder');

        var runPlace = $.trim($("#runPlace").val());
        var runPlaceP = $("#runPlace").attr('placeholder');

        if (opinion == "") {
            if (opinionP == "") {
                $("#opinionSpan").html("请输入您对跑步的主张");
                $("#opinionSpan").css("color", "red");

                flag = false;
            } else {
                $("#opinion").attr("value", opinionP);
            }
        } else {
            $("#opinionSpan").html("");
        }

        if (runStar == "") {
            if (runStarP == "") {

                $("#runStarSpan").html("请输入您最喜欢的体育明星");
                $("#runStarSpan").css("color", "red");

                flag = false;
            } else {
                $("#runStar").attr("value", runStarP);
            }
        } else {
            $("#runStarSpan").html("");
        }

        if (cellPhone == "") {
            if (cellPhoneP == "") {
                $("#cellPhoneSpan").html("请输入您正在使用的手机");
                $("#cellPhoneSpan").css("color", "red");
                flag = false;
            } else {
                $("#cellPhone").attr("value", cellPhoneP);
            }
        } else {
            $("#cellPhoneSpan").html("");
        }

        if (runPlace == "") {
            if (runStarP == "") {
                $("#runPlaceSpan").html("请输入您经常跑步的地点");
                $("#runPlaceSpan").css("color", "red");
                flag = false;
            } else {
                $("#runPlace").attr("value", runPlaceP);
            }
        } else {
            $("#runPlaceSpan").html("");
        }

        return flag;

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

        <div id="content" align="center">

            <div id="center">

                <table style="margin-top: 50px; border: 1px solid #cccccc;"
                       width="700" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                        <td colspan="3">
                            <table cellpadding="0" cellspacing="0" width="100%" height="62">
                                <tr>
                                    <td align="center">
                                        <c:if test="${requestScope.memberspace == null}">
                                            <b>创建个性化空间</b>
                                        </c:if>
                                        <c:if test="${requestScope.memberspace != null}">
                                            <b>修改个性化空间</b>
                                        </c:if>
                                    </td>

                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td width="100%">
                            <table width="100%" border="0" style="margin: 5px 0;"
                                   cellspacing="0" cellpadding="0" align="center" height="50">
                                <tr>
                                    <td width="126" align="center" rowspan="3" valign="top"
                                        style="padding-top: 0px;">
                                        <img src="/resources/images/icon03.gif" width="16"
                                             height="16"
                                             align="right"/>
                                    </td>
                                    <td width="10" height="30">
                                        &nbsp;
                                    </td>
                                    <td width="466" align="left" valign="top" class="fontgreen"
                                        style="padding-top: 2px;">
                                        <b>系统提醒</b>
                                    </td>
                                </tr>
                                <tr>
                                    <td height="34">
                                        &nbsp;
                                    </td>
                                    <td valign="top" class="fontgray" style="padding-top: 2px;">
                                        <ol>
                                            <li>
                                                <font color="#ff0000">带*号的选项内容必须填写</font>
                                            </li>
                                            <li>
                                                <font color="#ff0000">注意上传的图片尺寸尽量不要太大</font>
                                            </li>
                                        </ol>
                                    </td>
                                </tr>
                            </table>
                            <form action="/member/createOrUpdateMemberSpace" method="post"
                                  enctype="multipart/form-data" onsubmit="return checkForm();">
                                <table width="100%" border="0" style="margin:5px 0;" cellspacing="2"
                                       cellpadding="0" align="center">
                                    <tr>
                                        <TD width="50%" align="right">
                                            <FONT color=#ff9933>*</FONT> 请用一句话形容对跑步的主张：
                                        </TD>
                                        <TD width="50%" align="left">
                                            <INPUT class=INPUT1 type=text maxLength=14
                                                   name="opinion" style="width:200;height:25"
                                                   placeholder="${requestScope.memberspace.opinion}"
                                                   id="opinion"/>
                                            &nbsp;&nbsp;&nbsp;
                                            <span id="opinionSpan"></span>
                                        </TD>
                                    </tr>
                                    <TR>
                                        <TD width="50%" align="right">
                                            <FONT color=#ff9933>*</FONT> 喜欢在什么时段跑步？
                                        </TD>
                                        <TD width="50%" align="left">
                                            <c:if test="${requestScope.memberspace == null}">
                                                <input type="radio" name="runTime" class=radio1
                                                       value="清晨" checked="checked"/>清晨
                                                <input type="radio" name="runTime" class=radio1
                                                       value="午后"/>午后
                                                <input type="radio" name="runTime" class=radio1
                                                       value="黄昏"/>黄昏
                                            </c:if>
                                            <c:if test="${requestScope.memberspace.runTime eq '清晨'}">
                                                <input type="radio" name="runTime" class=radio1
                                                       value="清晨" checked="checked"/>清晨
                                                <input type="radio" name="runTime" class=radio1
                                                       value="午后"/>午后
                                                <input type="radio" name="runTime" class=radio1
                                                       value="黄昏"/>黄昏
                                            </c:if>

                                            <c:if test="${requestScope.memberspace.runTime eq '午后'}">
                                                <input type="radio" name="runTime" class=radio1
                                                       value="清晨"/>清晨
                                                <input type="radio" name="runTime" class=radio1
                                                       value="午后" checked="checked"/>午后
                                                <input type="radio" name="runTime" class=radio1
                                                       value="黄昏"/>黄昏
                                            </c:if>

                                            <c:if test="${requestScope.memberspace.runTime eq '黄昏'}">
                                                <input type="radio" name="runTime" class=radio1
                                                       value="清晨"/>清晨
                                                <input type="radio" name="runTime" class=radio1
                                                       value="午后"/>午后
                                                <input type="radio" name="runTime" class=radio1
                                                       value="黄昏" checked="checked"/>黄昏
                                            </c:if>


                                        </TD>
                                    </TR>
                                    <TR>
                                        <TD width="50%" align="right">
                                            <FONT color=#ff9933>*</FONT> 喜欢独自跑还是结伴跑？
                                        </TD>
                                        <TD width="50%" align="left">
                                            <c:if test="${requestScope.memberspace == null}">
                                                <input type="radio" name="runHabit" class=radio1
                                                       value="独自" checked="checked"/>独自
                                                <input type="radio" name="runHabit" class=radio1
                                                       value="结伴"/>结伴
                                            </c:if>

                                            <c:if test="${requestScope.memberspace.runHabit eq '独自'}">
                                                <input type="radio" name="runHabit" class=radio1
                                                       value="独自" checked="checked"/>独自
                                                <input type="radio" name="runHabit" class=radio1
                                                       value="结伴"/>结伴
                                            </c:if>

                                            <c:if test="${requestScope.memberspace.runHabit eq '结伴'}">
                                                <input type="radio" name="runHabit" class=radio1
                                                       value="独自"/>独自
                                                <input type="radio" name="runHabit" class=radio1
                                                       value="结伴" checked="checked"/>结伴
                                            </c:if>
                                        </TD>
                                    </TR>
                                    <TR>
                                        <TD width="50%" align="right">
                                            <FONT color=#ff9933>*</FONT>最喜欢的体育明星：
                                        </TD>
                                        <TD align="left">
                                            <INPUT class=INPUT1 type=text
                                                   maxLength=20 name="runStar"
                                                   placeholder="${requestScope.memberspace.runStar}"
                                                   style="width:200;height:25" id="runStar"/>
                                            &nbsp;&nbsp;&nbsp;
                                            <span id="runStarSpan"></span>
                                        </TD>
                                    </TR>
                                    <TR>
                                        <TD width="50%" align="right">
                                            <FONT color=#ff9933>*</FONT> 正在使用的手机：
                                        </TD>
                                        <TD width="50%" align="left">
                                            <INPUT class=INPUT1 type=text maxLength=14
                                                   name="cellPhone" id="cellPhone"
                                                   placeholder="${requestScope.memberspace.cellPhone}"
                                                   style="width:200;height:25"/>
                                            &nbsp;&nbsp;&nbsp;
                                            <span id="cellPhoneSpan"></span>
                                        </TD>
                                    </TR>
                                    <TR>
                                        <TD width="50%" align="right">
                                            <FONT color=#ff9933>*</FONT> 经常跑步的地点：
                                        </TD>
                                        <TD width="50%" align="left">
                                            <INPUT class="INPUT1" type="text" maxLength="14"
                                                   name="runPlace" style="width:200;height:25"
                                                   placeholder="${requestScope.memberspace.runPlace}"
                                                   id="runPlace"/>
                                            &nbsp;&nbsp;&nbsp;
                                            <span id="runPlaceSpan">
                                                <font color="red">
                                               <c:if test="${requestScope.errormessage != null}">
                                                   ${requestScope.errormessage}
                                               </c:if>
                                                </font>
                                            </span>
                                        </TD>
                                    </TR>
                                    <TR>
                                        <TD width="50%" align="right">&nbsp;&nbsp;上传个性化形象：</TD>
                                        <TD align="left">
                                            <input type="file" name="iconFile" id="icon"
                                                   style="width:200;height:25"/>
                                            &nbsp;&nbsp;&nbsp;
                                            <span id="iconSpan">

                                            </span>
                                        </TD>
                                    </TR>
                                    <tr>
                                        <td colSpan="2">
                                            <div align="center">
                                                <input type="submit" value="提交"
                                                       style="cursor: hand"/>
                                                &nbsp;&nbsp;
                                                <input type="reset" value="重置"
                                                       style="cursor: hand"/>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td colspan="3" height="30"></td>
                    </tr>
                </table>

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
