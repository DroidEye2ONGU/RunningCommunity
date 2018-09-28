<%@ page import="java.util.List" %>
<%@ page import="droideye.common.util.ProvinceUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: DroidEye
  Date: 2018/9/26
  Time: 19:43
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
<script type="text/javascript"
        src="/resources/js/jquerySession.js">
</script>

<c:if test="${ requestScope.modifyOKFlag != null}">
    <script>
        alert("修改成功");
    </script>
</c:if>

<script type="text/javascript">
    function checkOldPassword() {
        var oldPass = $.trim($("#oldPass").val());

        // alert(oldPass);

        if (oldPass == "") {
            $("#oldPassSpan").html("必须输入密码");
            $("#oldPassSpan").css("color", "red");

            $("#tijiao").attr("disabled", "disabled");
        } else {

            // alert("获取到密码:" + oldPass);
            $.ajax({
                       type: "POST",   //http请求方式
                       url: "${path}/member/checkPassword",//发送给服务器的url
                       data: "password=" + oldPass, //发送给服务器的参数
                       dataType: "text",  //告诉JQUERY返回的数据格式
                       success: function (data) {
                           // alert("进入方法");
                           var result = eval(data);
                           // alert("返回数据为:" + result);
                           if (result == 1) {
                               //密码正确
                               $("#oldPassSpan").html("您的旧密码正确");
                               $("#oldPassSpan").css("color", "green");

                               //关闭提交按钮禁用状态
                               $("#tijiao").removeAttr("disabled");
                           } else {
                               $("#oldPassSpan").html("您的密码错误,请检查");
                               $("#oldPassSpan").css("color", "red");

                               $("#tijiao").attr("disabled", "disabled");
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

        if (password == "" || rePassword == "") {
            $("#rePassWordResult").html("您必须输入新密码");
            $("#rePassWordResult").css("color", "red");
            //锁定提交按钮
            $("#tijiao").attr("disabled", "disabled");
        } else {
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
    }
</script>

<script type="text/javascript">
    function checkForm() {
        var password = $.trim($("#password").val());
        var email = $.trim($("#email").val());
        var passwordQuestion = $.trim($("#passwordQuestion").val());
        var passwordAnswer = $.trim($("#passwordAnswer").val());
        var phone = $.trim($("#phone").val());
        var address = $.trim($("#address").val());

        if (password == "") {
            $("#password").attr("value", $.trim($("#oldPass").val()));
        }
        if (email == "") {
            $("#email").attr("value", $.trim($("#oldEmail").val()));
        }
        if (passwordQuestion == "") {
            $("#passwordQuestion").attr("value", $.trim($("#oldPasswordQuestion").val()))
        }
        if (passwordAnswer == "") {
            $("#passwordAnswer").attr("value", $.trim($("#oldPasswordAnswer").val()))
        }
        if (phone == "") {
            $("#phone").attr("value", $.trim($("#oldPhone").val()))
        }
        if (address == "") {
            $("#address").attr("value", $.trim($("#oldAddress").val()))
        }
    }
</script>

<%
    List<String> provinces = ProvinceUtil.getProvinces();
    request.setAttribute("provinces", provinces);
%>

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

                <table width="600">
                    <thead>
                    <tr>
                        <th colspan="3">
                            <h4>修改会员信息</h4>
                        </th>
                    </tr>
                    </thead>

                    <tr>
                        <td width="100%">

                            <form:form action="/member/updateMemberinfo" modelAttribute="memberinfo"
                                       method="post" onsubmit="return checkForm()">
                                <table width="100%" border="0" style="margin:5px 0;" cellspacing="0"
                                       cellpadding="0" align="center">
                                    <thead>
                                    <tr>
                                        <td colSpan=3>
                                            请填写个人资料：（注意带有<font color=#ff0000>*</font>的项目必须填写）
                                        </td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <th width="25%" class="line1">
                                            <div align="right">
                                                <font color="black">*</font>昵称：
                                            </div>
                                        </th>
                                        <td width="30%">
                                            <input type="hidden" name="nickName"
                                                   value="${sessionScope.user.nickName}"/>
                                                <%--<input type="text" maxLength="14" style="width:200px"--%>
                                                <%----%>
                                                <%--placeholder="${requestScope.memberInfo.nickName}"/>--%>
                                            <span style="width:200px">${sessionScope.user.nickName}</span>
                                            <br>
                                            <span id="showResult"></span><br>
                                            <form:errors path="nickName" cssStyle="color: red"/>
                                        </td>
                                        <th width="45%">
                                                <%--<font color="#ff0000">昵称可使用长度为0-16的任何字符</font>--%>
                                        </th>
                                    </tr>
                                    <tr>
                                        <th class="line1">
                                            <div align="right">
                                                <font color="#ff0000">*</font> 旧密码：
                                            </div>
                                        </th>
                                        <td>
                                            <input type="password" maxLength="14"
                                                   onblur="checkOldPassword()" id="oldPass"
                                                   style="width:200px" name="oldPasswd"/>
                                        </td>
                                        <th>
                                            <font color="black">
                                                请输入旧密码以继续修改
                                            </font>
                                            <br>
                                            <span id="oldPassSpan"></span>

                                        </th>
                                    </tr>
                                    <tr>
                                        <th class="line1">
                                            <div align="right">
                                                <font color="#ff0000">*</font> 新密码：
                                            </div>
                                        </th>
                                        <td>
                                            <input type="password" maxLength="14" id="password"
                                                   style="width:200px" name="password"/>
                                        </td>
                                        <th>
                                            <font color="black">密码可使用长度为6-14的任何字符，并区分英文字母大小写</font>
                                            <br>
                                                <%--<form:errors path="password" cssStyle="color: red"/>--%>
                                            <font color="red"><form:errors path="password"/> </font>
                                        </th>
                                    </tr>
                                    <tr>
                                        <th class="line1">
                                            <div align="right">
                                                <font color="#ff0000">*</font>密码确认：
                                            </div>
                                        </th>
                                        <td>
                                            <input type="password" maxLength="14"
                                                   style="width:200px" name="rePassword"
                                                   id="repassword"
                                                   onblur="checkRePass()"/>
                                        </td>
                                        <th>
                                            <font color="black">请再输入一次密码</font>
                                            <br>
                                            <span id="rePassWordResult"></span>
                                        </th>
                                    </tr>
                                    <tr>
                                        <th class="line1">
                                            <div align="right">
                                                <font color="#ff0000">*</font>电子邮箱：
                                            </div>
                                        </th>
                                        <td>
                                            <input type="hidden" id="oldEmail"
                                                   value="${sessionScope.user.email}"/>
                                            <input type="text" maxLength="30" style="width:200px"
                                                   name="email" id="email"
                                                   placeholder="${sessionScope.user.email}"/>
                                        </td>
                                        <th>
                                            <font color="black">请输入您常用的其它电子邮箱</font>
                                            <br>
                                            <form:errors cssStyle="color: red" path="email"/>
                                        </th>
                                    </tr>
                                    <tr>
                                        <th class="line1">
                                            <div align="right">
                                                <font color="#ff0000">*</font> 密码提示问题：
                                            </div>
                                        </th>
                                        <th>
                                            <input type="hidden" id="oldPasswordQuestion"
                                                   value="${sessionScope.user.passwordQuestion}"/>
                                            <input style="width:200px" type="text"
                                                   name="passwordQuestion" id="passwordQuestion"
                                                   placeholder="${sessionScope.user.passwordQuestion}"/>
                                        </th>
                                        <th>
                                            <font color="black">例如：我的哥哥是谁？</font>
                                            <br>
                                            <form:errors path="passwordQuestion"
                                                         cssStyle="color: red"/>
                                        </th>
                                    </tr>
                                    <tr>
                                        <th class="line1">
                                            <div align="right">
                                                <font color="#ff0000">*</font> 密码提示答案：
                                            </div>
                                        </th>
                                        <td>
                                            <input type="hidden" id="oldPasswordAnswer"
                                                   value="${sessionScope.user.passwordAnswer}"/>
                                            <input type="text" style="width:200px"
                                                   name="passwordAnswer" id="passwordAnswer"
                                                   placeholder="${sessionScope.user.passwordAnswer}"/>
                                        </td>
                                        <th>
                                            <font color="black">注意：密码提示问题答案长度不少于6位</font>
                                            <br>
                                            <form:errors path="passwordAnswer"
                                                         cssStyle="color: red"/>
                                        </th>
                                    </tr>
                                    <tr>
                                        <th class="line1">
                                            <div align="right">
                                                <font color=#ff0000>*</font> 性别：
                                            </div>
                                        </th>
                                        <td>

                                            <c:if test="${sessionScope.user.gender eq '男'}">
                                                <input type="radio" value="男" name="gender"
                                                       checked="checked"/>男
                                                <input type="radio" value="女" name="gender"/>女
                                            </c:if>
                                            <c:if test="${sessionScope.user.gender eq '女'}">
                                                <input type="radio" value="男" name="gender"
                                                />男
                                                <input type="radio" value="女" name="gender"
                                                       checked="checked"/>女
                                            </c:if>
                                        </td>
                                        <th>&nbsp;<form:errors path="gender"
                                                               cssStyle="color: red"/></th>
                                    </tr>
                                    <tr>
                                        <th class="line1">
                                            <div align="right">
                                                <FONT color=#ff0000>*</FONT>
                                                所在省份/城市：
                                            </div>
                                        </th>
                                        <td>
                                            <select name="provinceCity">
                                                <c:forEach items="${requestScope.provinces}"
                                                           var="province">
                                                    <c:if test="${sessionScope.user.provinceCity eq province}">
                                                        <option value="${province}"
                                                                selected="selected">${province}</option>
                                                    </c:if>
                                                    <c:if test="${sessionScope.user.provinceCity  ne province}">
                                                        <option value="${province}">${province}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <th>&nbsp;<from:errors path="provinceCity"
                                                               cssStyle="color: red"/>
                                        </th>
                                    </tr>
                                    <tr>
                                        <th class="line1">
                                            <div align="right">
                                                联系电话：
                                            </div>
                                        </th>
                                        <td>
                                            <input type="hidden" id="oldPhone"
                                                   value="${sessionScope.user.phone}"/>
                                            <input type="text" style="width:200px" name="phone"
                                                   id="phone"
                                                   placeholder="${sessionScope.user.phone}"/>
                                        </td>
                                        <th>
                                            <font color="black">请输入区号和真实的电话，以便我们与您联系</font>
                                            <br>
                                            <form:errors path="phone" cssStyle="color: red"/>
                                        </th>
                                    </tr>
                                    <tr>
                                        <th class="line1">
                                            <div align="right">
                                                详细地址：
                                            </div>
                                        </th>
                                        <td>
                                            <input type="hidden" id="oldAddress"
                                                   value="${sessionScope.user.address}"/>
                                            <input type="text" style="width:200px" name="address"
                                                   id="address"
                                                   placeholder="${sessionScope.user.address}"/>
                                        </td>
                                        <th>&nbsp;</th>
                                    </tr>
                                    <tfoot>
                                    <tr>
                                        <td colSpan="3" align="center">
                                            <input id="tijiao" type="submit" value="提交"
                                                   disabled="disabled"
                                                   style="cursor: hand"/>
                                            &nbsp;&nbsp;
                                            <input type="reset" value="重置" style="cursor: hand"/>
                                        </td>
                                    </tr>
                                    </tfoot>
                                </table>
                            </form:form>
                            <%--</form>--%>
                        </td>
                    </tr>
                </table>
                <BR/><BR/>
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
