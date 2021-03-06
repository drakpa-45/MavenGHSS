<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 4/19/2020
  Time: 9:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<body>
<% String nextbtn="true";%>
<div class="mt-5">
    <div class="container mb-9">
        <div class="row">
            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                <div class="page-header mb-0 mt-0 page-header">
                    <h1 class="page-title">
                        Construction Development Board
                    </h1>
                </div>
                <div class="card" id="registrtaionFormCard">
                    <form action="#" id="architectrenewalForm" method="post" enctype="multipart/form-data">
                        <div class="card-header bg-cyan-light">
                            <h3 class="card-title font-weight-bold">Cancellation of CDB Certificates</h3>
                            <span class="pl-9">CDB Number: ${registrationDetails.cdbNo}</span>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                    <div class="card tab2">
                                        <div class="bg-blue card-status card-status-left"></div>
                                            <div class="card-header">
                                                <h3 class="card-title">Cancellation of Architect Details</h3>
                                            </div>
                                            <div class="card-body">
                                                <c:if test='${not empty checkOngoingApplication.updateStatus}'>
                                                    <div class="form-group">
                                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 alert alert-danger text-center">
                                                            You have ongoing application with reference number: <b>${checkOngoingApplication.referenceNo}</b> submitted on ${checkOngoingApplication.applicationDate}. This application is <b>${checkOngoingApplication.updateStatus}</b>. Please wait until complete process for this application.
                                                            <% nextbtn="false1";%>
                                                        </div>
                                                    </div>
                                                </c:if>
                                                <c:if test = "${fn:contains('Deregistered,Blacklisted,Revoked,Suspended', registrationDetails.updateStatus)}">
                                                    <div class="form-group">
                                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 alert alert-danger text-center">
                                                            You are not allow to avail this service as your certificate is <b> ${registrationDetails.updateStatus}</b>.
                                                            <% nextbtn="false2";%>
                                                        </div>
                                                    </div>
                                                </c:if>
                                                <% if(nextbtn=="true"){%>
                                                <div class="alert alert-info">
                                                    You are about to apply for the cancellation of cdb certificate. Are you sure you wish to apply for the cancellation ?
                                                </div>
                                                <input type="hidden" name="cdbNo" value="${registrationDetails.cdbNo}">
                                                <button type="button" onclick="submitcancellation()"  class="btn btn-primary" id="submitbtncancelation">
                                                    <i class="fa fa-save"></i> Accept and apply for cancellation
                                                </button>
                                                <% }%>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<c:url value="/resources/JqueryAjaxFormSubmit.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/jquery.form.js"/>"></script>
<script src="<c:url value="/resources/js/cdb/architect.js"/>"></script>
</body>
