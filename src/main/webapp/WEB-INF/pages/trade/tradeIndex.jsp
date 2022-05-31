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
                    <form action="#" id="specializedTradeForm" method="post" enctype="multipart/form-data">
                    <%--<form id="architectForm" action="#" method="post" enctype="multipart/form-data">--%>
                        <div class="card-header">
                            <h3 class="card-title font-weight-bold">Registration of Specialized Trade</h3>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                    <div class="nav-tabs-custom">
                                        <ul class="m-0 nav nav-tabs">
                                            <li class="feesStructure tab-pane active">
                                                <a href="#" class="border text-white" data-toggle="tab" data-placement="top">
                                                <i class="fa fa-bookmark mr-1"></i>Fee Structure</a>
                                            </li>
                                            <li class="tab-pane personalInformation">
                                                <a href="#" class=" border" data-toggle="tab" data-placement="top">
                                                <i class="fa fa-users mr-1"></i>Personal Information</a>
                                            </li>
                                            <li class="tab-pane categoryDtls">
                                                <a href="#" class="border" data-toggle="tab" data-placement="top">
                                                <i class="fa fa-mobile mr-1"></i>Category Details</a>
                                            </li>
                                            <li class="tab-pane saveAndPreview">
                                                <a href="#" class="border" data-toggle="tab" data-placement="top">
                                                <i class="fa fa-file mr-1"></i>Attachments</a>
                                            </li>
                                        </ul>
                                        <div class="tab-content border p-3 col-lg-12">
                                            <div class="tab-pane active feesStructure" id="feesStructure">
                                                <div class="form-group ">
                                                    <table id="csa"
                                                           class="table table-striped table-bordered table-hover">
                                                        <thead>
                                                        <tr>
                                                            <th>Validity (yrs)</th>
                                                            <th>Registration Fees(Nu.)</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach var="fee" items="${fee_details}" varStatus="count">
                                                                <tr>
                                                                    <td>${fee.validaty}</td>
                                                                    <td>${fee.registrationFee}</td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                                                        <label>The validity of the registration certificate will be for 3 years and the registration fee is not applicable for first time applicant. However, the first renewal fee is Nu. 1000/- and Nu. 500/- thereafter.</label>
                                                    </div>
                                                </div>
                                                <hr />

                                                <div class="form-group row">
                                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 ">
                                                        <label>Enter you valid Cid Number:<span class="text-danger">*</span></label>
                                                        <input type="number" onclick="remove_err('app_çid_err')" name="app_çid" class="form-control" id="app_çid">
                                                        <span id="app_çid_err" class="text-danger"></span>
                                                    </div>
                                                </div>
                                                <div class="form-group row pull-right">
                                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                                                        <button type="button"  onclick="nextTab('feesStructure','personalInformation')" class="btn btn-primary">
                                                            Next  <i class="fa fa-arrow-right"></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane personalInformation" id="personalInformation">
                                                <div class="">
                                                    <div class="card tab2">
                                                        <div class="bg-blue card-status card-status-left"></div>
                                                        <div class="card-header">
                                                            <h3 class="card-title">Personal Details</h3>
                                                        </div>
                                                        <div class="card-body">
                                                            <div class="col-lg-12 col-lg-md col-sm-12 col-xs-12">
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <div class="form-group row">
                                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Salutation<span class="text-danger">*</span>:</label>
                                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                                            <form:select path="salutationList"  name="salutation" onclick="remove_err('salutation_err')" id="salutation" class="form-control">
                                                                                <form:option value="">Select</form:option>
                                                                                <form:options items="${salutationList}" itemValue="value" itemLabel="text"></form:options>
                                                                            </form:select>
                                                                            <span id="salutation_err" class="text-danger"></span>
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group row">
                                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Cid No:</label>
                                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                                            <input type="text" readonly id="cidNo" name="cidNo" class="form-control number">
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group row">
                                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Name:</label>
                                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                                            <input type="text" readonly  name="fullname" maxlength="100" id="name" class="form-control">
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group row">
                                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Dzongkhag:</label>
                                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                                            <input type="text" readonly maxlength="100" id="dzongkhag" class="form-control">
                                                                            <input type="hidden"  name="dzongkhagId" id="dzongkhagId">
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group row">
                                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Gewog:</label>
                                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                                            <input type="text" readonly maxlength="100" id="gewog" name="gewog" class="form-control">
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group row">
                                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Village:</label>
                                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                                            <input type="text" readonly maxlength="100" id="village" name="village" class="form-control">
                                                                            <input type="hidden"  name="villageId" id="villageId">
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <span id="imageId"></span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- Box Open -->
                                                    <div class="card tab2">
                                                        <div class="bg-blue card-status card-status-left"></div>
                                                        <div class="card-header">
                                                            <h3 class="card-title">Contact Details</h3>
                                                        </div>
                                                        <div class="card-body">
                                                            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label>Email: <span class="text-danger">*</span></label>
                                                                    <input id="email" type="email" class="form-control"onclick="remove_err('email_err')" name="email" placeholder="Type valid email">
                                                                    <span id="email_err" class="text-danger"></span>
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label>Mobile No: <span class="text-danger">*</span></label>
                                                                    <input type="text" class=" form-control number" onclick="remove_err('mobileNo_err')" id="mobileNo" name="mobileNo"  maxlength="8">
                                                                    <span id="mobileNo_err" class="text-danger"></span>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label>Telephone Number:</label>
                                                                    <input id="TphoneNo" type="text" class="form-control" name="telephoneNo" placeholder="enter telephone number">
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label>Tpn No:</label>
                                                                    <input type="text" class=" form-control number" id="tpn" name="tpn"  maxlength="8">

                                                                </div>
                                                            </div>
                                                            <div class="col-lg-12">
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label>Office/Employer Name</label>
                                                                    <input type="text" class="form-control" name="employeeName" placeholder="Employer Name">
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label>Office/Employer Address</label>
                                                                    <textarea class="form-control" name="employeeAddress" placeholder="Office/Employer Address"></textarea>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <hr />
                                                <div class="form-group row pull-right">
                                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                                                        <button type="button" onclick="nextTab('personalInformation','categoryDtls')"  class="btn btn-primary">
                                                            Next  <i class="fa fa-arrow-right"></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane categoryDtls" id="contact_detail">
                                                <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12 form-group">
                                                    <div class="card tab2">
                                                        <div class="bg-blue card-status card-status-left"></div>
                                                        <div class="card-header">
                                                            <h3 class="card-title">Category Information</h3> >> <i>Please tick the checkbox to select a category</i>
                                                        </div>
                                                        <div class="card-body">
                                                            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                                                <table id="specializedTrade" class="table table-bordered table-hover">
                                                                    <thead style="background-color: #F2F2F2">
                                                                    <tr>
                                                                        <th></th>
                                                                        <th>Catagory</th>
                                                                    </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                    <c:forEach items="${categoryList}" var="category" varStatus="i">
                                                                        <tr>
                                                                                <%--${category.arrayId}--%>
                                                                                <%--${i.index}--%>
                                                                            <td><input class="form-control categoryCheck" type="checkbox"
                                                                                           name="terms[${i.index}].appliedCategoryId" value="${category.id}"
                                                                                           style="width: 17px; height: 17px;"></td>
                                                                            <td>${category.code}-${category.name}</td>
                                                                        </tr>
                                                                    </c:forEach>
                                                                    </tbody>
                                                                    </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <hr />
                                                    <div class="form-group row pull-right">
                                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                                                            <button type="button" onclick="previousTab('personalInformation','categoryDtls')"  class="btn btn-success">
                                                                <i class="fa fa-arrow-left"></i>  Previous
                                                            </button>
                                                            <button type="button" onclick="nextTab('categoryDtls','saveAndPreview')"  class="btn btn-primary">
                                                                Next  <i class="fa fa-arrow-right"></i>
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane saveAndPreview" id="saveAndPreview">
                                                <div id="submitSection" style="">
                                                    <div class="panel panel-default">
                                                        <div class="panel-body">
                                                            <div class="form-group row">
                                                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                                    <label><b>Attach your supoting documents:<span class="text-danger">*</span></b>( Please click on add more document button to add more attachments)</label><br />
                                                                </div>
                                                            </div>
                                                            <div class="form-group row">
                                                                <div class="col-lg-10 col-md-10 col-sm-10 col-xs-12">
                                                                    <input type="file" name="files" id="file1" class="alert badge-danger" onchange="validateAttachment(this.value,'file1','filecheck1')">
                                                                </div>
                                                                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
                                                                    <button class="btn btn-success fa-pull-right" type="button" onclick="addmoreattachemnts()"><i class="fa fa-plus"> Add More Documents</i></button>
                                                                </div>
                                                            </div>
                                                            <span id="fileadd"></span>
                                                            <br/>
                                                            <input type="hidden" name="addedeqCount" id="addedeqCount">
                                                        </div>
                                                    </div>
                                                </div>
                                                <hr />
                                                <div class="form-group row pull-right">
                                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                                                        <button type="button" onclick="previousTab('categoryDtls','saveAndPreview')"  class="btn btn-success">
                                                            <i class="fa fa-arrow-left"></i>  Previous
                                                        </button>
                                                        <button type="button" onclick="submitRegistrationForm()"  class="btn btn-primary" id="submitbtn">
                                                            <i class="fa fa-save"></i> Submit
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1"  class="modal in" id="concirmationModel">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <span><b>Confirmation!</b></span>
                                    </div>
                                    <div class="modal-body form-horizontal">
                                        <div class="alert alert-info">
                                            <div class="row">
                                                <div class="col-md-12 col-sm-12 col-lg-12 col-xs-12">
                                                    <span id="messages"></span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-success" onclick="SubmitApplicationDetials()">Yes</button>
                                        <button type="button" class="btn btn-warning" onclick="closemodel('concirmationModel')"><span class="fa fa-times"></span> No</button>
                                    </div>
                                </div>
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
</div>
</body>
</html>




