<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="com.gov.edu.gelephuHSS.auth.LoginDTO" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 16/05/2019
  Time: 04:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title><sitemesh:write property='title'/></title>

    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,300i,400,400i,500,500i,600,600i,700,700i&amp;subset=latin-ext">
--%>
    <%
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LoginDTO loginDTO = (LoginDTO) auth.getPrincipal();
    %>
    <jsp:include page="inc/js.jsp"/>
    <jsp:include page="inc/css.jsp"/>
    <jsp:include page="inc/context.jsp"/>

</head>
<body class="">
<% if(request.isUserInRole("ROLE_APPROVER")){%>

<%}%>
<jsp:include page="inc/header_admin.jsp"/>

<div class="mt-5">
    <div class="container mb-9">
        <div class="row">
            <div class="col-12" id="content_main_div">
                <sitemesh:write property='body'/>
            </div>
        </div>
    </div>
</div>

<jsp:include page="inc/footer.jsp"/>

</body>
</html>
