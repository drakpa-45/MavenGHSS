<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 16/05/2019
  Time: 06:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
<link rel="stylesheet" href="<c:url value="/resources/vendor/bootstrap-4.1.1/css/bootstrap.min.css"/>"/>
<link rel="stylesheet" href="<c:url value='/resources/css/font-awesome.min.css'/>"/>
<link rel="stylesheet" href="<c:url value="/resources/css/dashboard.css"/>"  />--%>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="<c:url value="/resources/assets/css/bootstrap.min.css"/>" rel="stylesheet" />
<link rel="stylesheet" href="<c:url value="/resources/assets/css/dataTables.bootstrap4.min.css"/>"/>
<link rel="stylesheet" href="<c:url value='/resources/css/sweetalert/sweetalert.css'/>"/>
<link href="<c:url value="/resources/assets/css/dashboard.css"/>" rel="stylesheet" />
<link href="<c:url value="/resources/css/datepicker.css"/>" rel="stylesheet" />
<link href="<c:url value="/resources/css/cdb.css"/>" rel="stylesheet" />

<style>
    img {
        vertical-align: middle;
        border-style: none;
        /*padding-top: 9px;*/
    }
    .pb-4, .py-4 {
        padding-bottom: 0.5rem !important;
    }
    .pt-4, .py-4 {
        padding-top: 0.7rem !important;
    }
    .chosen-container-single .chosen-single {
        height: 35px;
        border-radius: 3px;
        border: 1px solid #CCCCCC;
    }
    .chosen-container
    {
        width: 100% !important;
    }
    .dropdown-submenu {
        position: relative;
    }
    .dropdown-item {
        color: #ffffff;
    }
    .header {
        background-color:#ffffff;
    }
    .d-flex {
        margin-left: 25px;
    }
    .dropdown {
        margin-right: 25px;
        margin-top: 22px;
    }
    .ml-2 {
        font-size: 17px;
    }
    .col-12 {
        margin-top: -9px;
    }
    .dropdown-submenu a::after {
        transform: rotate(-90deg);
        position: absolute;
        right: 6px;
        top: 0.8em;
    }
    .dropdown-submenu .dropdown-menu {
        top: 0;
        left: 100%;
        margin-left: 0.1rem;
        margin-right: 0.1rem;
    }
    * {
        margin: 0px;
        padding: 0px;
    }
    .dropdown-menu {
        margin: 0px;
        padding: 0px;
        background-color:#08586d;;
        color: #ffffff;
    }
    .dropdown-sub-menu {
        background-color:#08586d;;
        margin-left: 100%!important;
    }
    .has-dropdown {
        width: 100%;
    }
    .dropdown-sub-menu li {
        width: 100%;
    }
    #navbarSupportedContent ul {
        list-style: none;
    }
    #navbarSupportedContent ul li {
        height: 40px;
        float: left;
        position: relative;
    }
    #navbarSupportedContent ul li a {
        text-decoration: none;
        display: block;
    }
    #navbarSupportedContent ul li a:hover {
        color: #13ab8f;
        /* font-weight: bold;*/
    }
    #navbarSupportedContent ul ul {
        position: absolute;
        display: none;
    }
    #navbarSupportedContent ul li:hover > ul {
        display: block;
    }
    #navbarSupportedContent ul ul ul {
        margin-left: 177px;
        top: 0px;
    }
    #navbarSupportedContent {
         font-weight: bold;
    }
    .banner-heading {
        font-family: 'Libre Baskerville', serif;
        font-size: 30px;
        line-height: 25px;

    }
    .banner-sub-heading {
        font-family: 'Libre Baskerville', serif;
        font-size: 25px;
        line-height: 25px;
    }
    .footer {
        position: relative;
        bottom: 0;
        width: 100%;
        background: #1b1d29;
        color: #ffffff;
        clear: both;
        height: 25px;
        /*padding: 15px 0;*/
        /*margin-left: 13px;*/
    }

    .card {
        min-height: 168px;
    }
    .rounded {
        border-radius:.25rem!important
    }

    body{
        background: silver;
        font-family:Arial;
    }

    aside {
        float:left;
        width:33.3%;
        padding:20px;
        box-sizing: border-box;
        height: 200px;
        color: white;
        background-color: #1b6c82;
        clear:right;
    }
    aside a{
        text-decoration: none;
        color:white;
    }
    aside ul{
        list-style-type: none;
    }
</style>
