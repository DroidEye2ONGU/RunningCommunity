<%--
  Created by IntelliJ IDEA.
  User: DroidEye
  Date: 2018/9/22
  Time: 23:43
  To change this template use File | Settings | File Templates.
--%>
<%--
    该页面用于PageContoller获取到Cookie的值后,通过此页面将参数传递给MemberInfoController的login方法进行
    登陆.由于直接使用forward无法在controller之间传参,而使用forward会使用户名和密码直接暴露在URL下,且为了
    不使用ServletAPI增加耦合,故增加次页面进行参数之间的传递
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>正在检查登录信息,请稍后</title>
</head>
<body>
    <form action="/member/login" method="post">
        <input type="hidden" value="${requestScope.username}" name="username"/>
        <input type="hidden" value="${requestScope.password}" name="password"/>
        <input type="hidden" value="${requestScope.isFromPageController}" name="isFromPageController">
    </form>
    <script>
        document.forms[0].submit();
    </script>
</body>
</html>
