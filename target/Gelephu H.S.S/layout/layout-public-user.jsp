<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="com.gov.edu.gelephuHSS.auth.LoginDTO" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title><sitemesh:write property='title'/></title>

   <%-- <%
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LoginDTO loginDTO = (LoginDTO) auth.getPrincipal();
    %>--%>
    <jsp:include page="inc/js.jsp"/>
    <jsp:include page="inc/css.jsp"/>
    <jsp:include page="inc/context.jsp"/>

</head>
<body class="">
<jsp:include page="inc/header.jsp"/>

<div class="mt-5">
    <div class="container mb-9">
        <div class="row">
            <div class="col-12" id="content_main_div_public_user">
                <sitemesh:write property='body'/>
            </div>
        </div>
    </div>
</div>

<jsp:include page="inc/footer.jsp"/>

</body>
</html>
