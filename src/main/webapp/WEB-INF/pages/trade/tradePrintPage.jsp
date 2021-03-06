<%@ page import="bt.gov.ditt.sso.client.dto.UserSessionDetailDTO" %>
<%@ page import="bt.gov.ditt.sso.client.SSOClientConstants" %>
<%--
  Created by IntelliJ IDEA.
  User: Dechen Wangdi
  Date: 12/17/2019
  Time: 11:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<div id="print">
<div class="mt-5">
    <div class="container mb-9">
        <div class="card" id="registrtaionFormCard">
            <form action="#" id="printingForm" method="post" enctype="form-data">
                <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                    <br />
                        <div class="card tab2">
                            <div class="bg-blue card-status card-status-left"></div>
                            <div class="card-header">
                                <label class='control-label'>Your application for Registration Of SpecializedTrade has been submitted and your application number is <i> ${cidDetails.referenceNo} </i><br>You will receive an email as well as sms notification once take further action.You can track your application using above Application Number.</label>
                            </div>
                            <div class="card-body">
                                <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                        <label>Applicant Name: <i>${cidDetails.fullname}</i></label>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                        <label>Mobile No: <i>${cidDetails.mobileNo}</i></label>
                                    </div>
                                </div>
                                <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                        <label>Cid Number: <i>${cidDetails.cidNo}</i></label>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                        <label>Date Of Submission: <i>${cidDetails.initialDate}</i></label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    <div class="text-center">
                        <button type="button" class="btn btn-primary" onclick="PrintDiv()">Print</button>
                    </div>
                </div>
            </form>
            <script type="text/javascript" src="<c:url value="/resources/JqueryAjaxFormSubmit.js"/>"></script>
            <script type="text/javascript" src="<c:url value="/resources/jquery.form.js"/>"></script>
            <script src="<c:url value="/resources/js/cdb/specialized.js"/>"></script>
        </div>
    </div>
</div>
</div>
</html>




