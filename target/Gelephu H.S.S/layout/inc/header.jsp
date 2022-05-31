
<%--
  Created by IntelliJ IDEA.
  User: Pema Drakpa
  Date: 1/15/2020
  Time: 2:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta http-equiv="Content-Language" content="en"/>
    <meta name="msapplication-TileColor" content="#2d89ef">
    <meta name="theme-color" content="#4188c9">
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="mobile-web-app-capable" content="yes">
    <meta name="HandheldFriendly" content="True">
    <meta name="MobileOptimized" content="320">
    <title>Home</title>
</head>
<body>
<div class="header py-4" style="background:#08586d; height:120px;">
    <div class="container">
        <div class="d-flex">
            <img src="<c:url value="/resources/img/rgob.png"/>" class="header-brand-img" alt="tabler logo"
                 style="height:100px; width:90px;">

            <h3 class="text-white" style="margin-top:-2px;"><br/>
                དགེ་ལེགལ་ཕུག་འབྲིང་རིམ་སློབ་གྲྭ་གོང་མ།<br/>
                <span style="font-size:smaller;">Gelephu Higher Secondary School</span>
            </h3>

            <div class="d-flex order-lg-2 ml-auto">
               <%-- <div class="dropdown">
                    <a href="#" class="nav-link pr-0 leading-none" data-toggle="dropdown" style="margin-top:-2px;">
                        &lt;%&ndash;<span style="background-image: url(../../resources/images/profile.png)" class="avatar"></span>&ndash;%&gt;
                        <span class="ml-2 d-none d-lg-block">

                            <small class="text-white d-block mt-1"></small>

                            <small class="text-white d-block mt-1">Community Center</small>

                            <small class="text-white d-block mt-1">
                            </small>

                    </a>
                    <div class="dropdown-menu dropdown-menu-right dropdown-menu-arrow">
                        <a class="dropdown-item" style="color:#71dd8a;">
                            <i class="dropdown-icon fe fe-help-circle"></i>Need help?
                        </a>
                        <a class="dropdown-item" href="<c:url value="/common/logout"/>" style="color:#71dd8a;">
                            <i class="dropdown-icon fe fe-log-out"></i>Sign out
                        </a>
                    </div>
                    </span>
                </div>--%>
            </div>
        </div>
    </div>
</div>
<div class="header collapse d-lg-flex p-0" id="headerMenuCollapse" style="height:55px;">
    <div class="container">
        <div class="row pull-right">
            <div class="col-lg order-lg-first">
                <nav class="navbar navbar-expand-lg navbar-dark">
                    <button class="navbar-toggler" type="button" data-toggle="collapse"
                            data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                            aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item active">
                                <a class="nav-link" style="color:#000000;" href="${pageContext.request.contextPath}/home">
                                <i class="fa fa-home"></i>&nbsp;HOME <span class="sr-only">(current)</span>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="javascript:void(0)" class="nav-link" style="color:#000000;"
                                   data-toggle="dropdown"> ABOUT US &nbsp;<i
                                        class="fa fa-chevron-circle-down"></i></a>
                                <ul class="dropdown-menu dropdown-menu-arrow"
                                    style="margin-top:2px; color:#000000;">
                                    <li class="has-dropdown" class="dropdown-item" style="position:relative;"><a href="${pageContext.request.contextPath}/public_view/scprofile"
                                            class="dropdown-item">School Profile</a>
                                    </li>
                                    <li class="has-dropdown"><a href="${pageContext.request.contextPath}/public_view/scprofile" class="dropdown-item">Vission & Mission</a>
                                    </li>
                                    <li class="has-dropdown" class="dropdown-item" style="position: relative;"><a href="#"
                                            class="dropdown-item">Staff Profile &nbsp; <i class="fa fa-chevron-circle-right"></i></a>
                                        <ul class="dropdown-sub-menu" style="text-align:left;">
                                            <li><a href="<c:url value="/public_view/staffdtls"><c:param name="type" value="180"/></c:url>" class="dropdown-item">Management</a></li>
                                            <li><a href="<c:url value="/public_view/staffdtls"><c:param name="type" value="182"/></c:url>" class="dropdown-item">Dzongkha Faculty</a></li>
                                            <li><a href="<c:url value="/public_view/staffdtls"><c:param name="type" value="181"/></c:url>" class="dropdown-item">English Faculty</a></li>
                                            <li><a href="<c:url value="/public_view/staffdtls"><c:param name="type" value="183"/></c:url>" class="dropdown-item">Humanities Faculty</a></li>
                                            <li><a href="<c:url value="/public_view/staffdtls"><c:param name="type" value="184"/></c:url>" class="dropdown-item">Commerece Faculty</a></li>
                                            <li><a href="<c:url value="/public_view/staffdtls"><c:param name="type" value="185"/></c:url>" class="dropdown-item">Science Faculty</a></li>
                                            <li><a href="<c:url value="/public_view/staffdtls"><c:param name="type" value="186"/></c:url>" class="dropdown-item">Mathematics Faculty</a></li>
                                        </ul>
                                    </li>
                                    <li class="has-dropdown"><a href="<c:url value="/ruralTimber/ruralTimberApplication"><c:param name="cons_type" value="r"/></c:url>" class="dropdown-item">School Policy</a>
                                    </li>
                                </ul>
                            </li>
                            <li class="nav-item">
                                <a href="javascript:void(0)" class="nav-link" style="color:#000000;"
                                   data-toggle="dropdown"> ACADEMICS &nbsp;<i
                                        class="fa fa-chevron-circle-down"></i></a>
                                <ul class="dropdown-menu dropdown-menu-arrow"
                                    style="margin-top:2px; color:#000000;">
                                    <li class="has-dropdown" class="dropdown-item" style="position:relative;"><a href="<c:url value="/ruralTimber/ruralTimberApplication"><c:param name="cons_type" value="n"/></c:url>"
                                                                                                                 class="dropdown-item">Curriculum</a>
                                    </li>
                                    <li class="has-dropdown" class="dropdown-item" style="position: relative;"><a href="#" class="dropdown-item">Department &nbsp; <i class="fa fa-chevron-circle-right"></i></a>
                                        <ul class="dropdown-sub-menu" style="text-align:left;">
                                            <li>
                                                <a href="${pageContext.request.contextPath}/animalHealth/declareMedicineStock"
                                                   class="dropdown-item">Management</a>
                                            </li>
                                            <li><a href="${pageContext.request.contextPath}/animalHealth/declareEquipmentStock" class="dropdown-item">Dzongkha Department</a></li>
                                            <li><a href="${pageContext.request.contextPath}/animalHealth/declareEquipmentStock" class="dropdown-item">English Department</a></li>
                                            <li><a href="${pageContext.request.contextPath}/animalHealth/declareEquipmentStock" class="dropdown-item">Humanities Department</a></li>
                                            <li><a href="${pageContext.request.contextPath}/animalHealth/declareEquipmentStock" class="dropdown-item">Commerece Department</a></li>
                                            <li><a href="${pageContext.request.contextPath}/animalHealth/declareEquipmentStock" class="dropdown-item">Science Department</a></li>
                                            <li><a href="${pageContext.request.contextPath}/animalHealth/declareEquipmentStock" class="dropdown-item">Mathematics Department</a></li>
                                        </ul>
                                    </li>
                                    <li class="has-dropdown"><a href="${pageContext.request.contextPath}/public_view/scprofile" class="dropdown-item">Examination</a>
                                    </li>
                                    <li class="has-dropdown"><a href="<c:url value="/public_view/result">></c:url>" class="dropdown-item">Result</a>
                                    </li>
                                </ul>
                            </li>
                            <li class="nav-item">
                                <a href="javascript:void(0)" class="nav-link" style="color:#000000;"
                                   data-toggle="dropdown"> ADMISSION &nbsp;<i
                                        class="fa fa-chevron-circle-down"></i></a>
                                <ul class="dropdown-menu dropdown-menu-arrow"
                                    style="margin-top:2px; color:#000000;">
                                    <li class="has-dropdown" class="dropdown-item" style="position:relative;"><a href="${pageContext.request.contextPath}/public_view/scprofile"
                                                                                                                 class="dropdown-item">Admission Policy</a>
                                    </li>
                                    <li class="has-dropdown" class="dropdown-item" style="position: relative;"><a href="#" class="dropdown-item">General Admission</a>
                                    </li>
                                    <li class="has-dropdown"><a href="${pageContext.request.contextPath}/public_view/scprofile">Class XI Admission</a>
                                    </li>
                                    <li class="has-dropdown"><a href="${pageContext.request.contextPath}/public_view/admissionform" class="dropdown-item">Admission Form</a>
                                    </li>
                                </ul>
                            </li>
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath}/ruralTimber/viewAppStatus" class="nav-link" style="color:#000000;"> <i class="fa fa-phone-square"></i>&nbsp;CONTACTS</a>
                            </li>
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath}/public_view/download" class="nav-link" style="color:#000000;"> <i class="fa fa-download"></i>&nbsp;DOWNLOADS</a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
    </div>
</div>
</body>
</html>
