<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 3/29/2020
  Time: 10:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<body>


<div class="mt-5">
    <div class="container mb-9">
        <div class="row">
            <div class="col-12">
                <div class="page-header mb-0 mt-0 page-header">
                    <h1 class="page-title">
                        Construction Development Board
                    </h1>
                </div>
                <!-- tab open -->
                <div class="card" id="registrtaionFormCard">
                    <form action="#" id="architectverificationForm" >
                        <div class="card-header">
                            <h3 class="card-title font-weight-bold">Cancellation of Architect</h3>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                    <div class="nav-tabs-custom">
                                        <ul class="m-0 nav nav-tabs">
                                            <li class="tab-pane personalInformation active">
                                                <a href="#" class=" border" data-toggle="tab" data-placement="top">
                                                    <i class="fa fa-users mr-1"></i>Personal Information</a>
                                            </li>
                                            <li class="tab-pane categoryDtls">
                                                <a href="#" class="border" data-toggle="tab" data-placement="top">
                                                    <i class="fa fa-mobile mr-1"></i>Contact Details</a>
                                            </li>
                                            <li class="tab-pane saveAndPreview">
                                                <a href="#" class="border" data-toggle="tab" data-placement="top">
                                                    <i class="fa fa-file mr-1"></i>Attachments</a>
                                            </li>
                                        </ul>
                                        <div class="tab-content border p-3 col-lg-12">
                                            <div class="tab-pane active personalInformation" id="personalInformation">
                                                <div class="">
                                                    <div class="card tab2">
                                                        <div class="bg-blue card-status card-status-left"></div>
                                                        <div class="card-header">
                                                            <h3 class="card-title">Certificate Details</h3>
                                                        </div>
                                                        <div class="card-body">
                                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label>CDB Number:</label>
                                                                    <input type="text" value="${appDetails.cdbNo}" readonly id="cdbNo" class="form-control">
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label>Reference/Application Number:</label>
                                                                    <input type="text" value="${appDetails.referenceNo}" readonly id="referenceNo" name="referenceNo" class="form-control">
                                                                </div>
                                                            </div>

                                                            <div class="col-lg-12">
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label>Expiry Date: </label>
                                                                    <input type="text" value="${appDetails.regExpDate}" readonly id="regExpDate"class="form-control">
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label>Applied Date: </label>
                                                                    <input type="text" value="${appDetails.applicationDate}" readonly id="applicationDate" class="form-control">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="card tab2">
                                                        <div class="bg-blue card-status card-status-left"></div>
                                                        <div class="card-header">
                                                            <h3 class="card-title">Personal Details</h3>
                                                        </div>
                                                        <div class="card-body">
                                                            <div class="col-lg-12 col-lg-md col-sm-12 col-xs-12">
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <div class="form-group row">
                                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Salutation:</label>
                                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                                            <input type="text" readonly id="salutation" name="salutation" value="${appDetails.salutation}" class="form-control number">
                                                                            <span id="salutation_err" class="text-danger"></span>
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group row">
                                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Cid No:</label>
                                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                                            <input type="text" readonly id="cidNo" value="${appDetails.cidNo}" name="cidNo" class="form-control number">
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group row">
                                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Name:</label>
                                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                                            <input type="text" readonly  name="fullname" value="${appDetails.fullname}" maxlength="100" id="name" class="form-control">
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group row">
                                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Dzongkhag:</label>
                                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                                            <input type="text" readonly value="${appDetails.dzongkhagId}" maxlength="100" id="dzongkhag" class="form-control">
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group row">
                                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Gewog:</label>
                                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                                            <input type="text" value="${appDetails.gewog}" readonly maxlength="100" id="gewog" name="gewog" class="form-control">
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group row">
                                                                        <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Village:</label>
                                                                        <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                                            <input type="text" value="${appDetails.village}" readonly maxlength="100" id="village" name="village" class="form-control">
                                                                            <input type="hidden"  name="villageId" id="villageId">
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <img src='https://www.citizenservices.gov.bt/BtImgWS/ImageServlet?type=PH&cidNo=${appDetails.cidNo}'  width='200px'  height='200px' class='pull-right'/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- Box Open -->
                                                    <div class="card tab2">
                                                        <div class="bg-blue card-status card-status-left"></div>
                                                        <div class="card-body">
                                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Service for:</label>
                                                                    <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                                        <select class="form-control"  name="serviceTypeId">
                                                                            <option value="030ace8e-24af-11e6-967f-9c2a70cc8e06">Architecture</option>
                                                                        </select>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Country:</label>
                                                                    <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                                        <input type="text" value="${appDetails.countryId}" readonly maxlength="100" id="country" name="country" class="form-control">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-12">
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label class="col-lg-3 col-md-3 col-sm-3 col-xs-12">Type: </label>
                                                                    <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                                                                        <input type="text" value="${appDetails.serviceSectorType}" readonly maxlength="100" id="service" name="serviceSectorType" class="form-control">
                                                                    </div>
                                                                </div>

                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
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
                                                            <h3 class="card-title">Contact Details</h3>
                                                        </div>
                                                        <div class="card-body">
                                                            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label>Email:</label>
                                                                    <input id="email" type="email" value="${appDetails.email}" readonly class="form-control" name="email" placeholder="Type valid email">
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label>Mobile No:</label>
                                                                    <input type="text" class=" form-control number" value="${appDetails.mobileNo}" readonly id="mobileNo" name="mobileNo"  maxlength="8">
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-12">
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label>Office/Employer Name:</label>
                                                                    <input type="text" class="form-control" value="${appDetails.employeeName}" readonly name="employeeName" placeholder="Employer Name">
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label>Office/Employer Address:</label>
                                                                    <textarea class="form-control" readonly name="employeeAddress" placeholder="Office/Employer Address">
                                                                        ${appDetails.employeeAddress}
                                                                    </textarea>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="card tab2">
                                                        <div class="bg-blue card-status card-status-left"></div>
                                                        <div class="card-header">
                                                            <h3 class="card-title">Qualification Details</h3>
                                                        </div>
                                                        <div class="card-body">
                                                            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label>Qualification:</label>
                                                                    <input type="text" class=" form-control number" value="${appDetails.qualificationId}" readonly name="ualification">
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label>Year of graduation:</label>
                                                                    <input type="text" class=" form-control number" value="${fn:substring(appDetails.graduationyr, 0, 4)}" readonly name="graduationYear" >
                                                                </div>
                                                            </div>
                                                            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label>University:</label>
                                                                    <input type="text" class="form-control " name="universityName"value="${appDetails.universityName}" readonly>
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                                    <label>Country of unversity:</label>
                                                                    <input type="text" class=" form-control number" value="${appDetails.universityCountry}" readonly name="graduationYear">
                                                                </div>
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
                                                                    <label><b>Documents:</b></label>
                                                                </div>
                                                            </div>
                                                            <div class="form-group row">
                                                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                                    <div class="table-responsive">
                                                                        <table id="attachment" class="table table-bordered table-hover">
                                                                            <thead>
                                                                            <tr>
                                                                                <td>Sl No#</td>
                                                                                <td>Attachment</td>
                                                                                <td colspan="2">Action</td>
                                                                            </tr>
                                                                            </thead>
                                                                            <tbody>
                                                                            <c:forEach var="att" items="${appDetails.doc}" varStatus="counter">
                                                                                <option value="${dzo.header_id}_${dzo.department}_${dzo.type}">${dzo.header_name}</option>
                                                                                <tr>
                                                                                    <td>${counter.index+1}</td>
                                                                                    <td>${att.documentName}</td>
                                                                                    <td>  <button class="btn btn-primary" type="button" onclick="viewAttachment('${att.id}','view')" target="_blank"><i class="fa fa-eye"></i> View </button> </td>
                                                                                    <td>  <button class="btn btn-primary" type="button" onclick="viewAttachment('${att.id}','download')"><i class="fa fa-download"></i> Download </button> </td>
                                                                                </tr>
                                                                            </c:forEach>
                                                                            </tbody>
                                                                        </table>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <input type="hidden" id="currentStatus" value="${appDetails.updateStatus}">
                                                            <c:if test="${appDetails.updateStatus=='36f9627a-adbd-11e4-99d7-080027dcfac6' || appDetails.updateStatus=='6195664d-c3c5-11e4-af9f-080027dcfac6'}">
                                                                <div class="row pt-4">
                                                                    <div class="col-md-4 col-sm-4 col-lg-4 col-xs-12">
                                                                        <label class="form-label">Date of Verification:</label>
                                                                        <input type="text" name="verificationdate" class="form-control" readonly value="${appDetails.verifcationdate}">
                                                                    </div>
                                                                    <div class="col-md-8 col-sm-8 col-lg-8 col-xs-12">
                                                                        <label class="form-label">Verifier Remarks:</label>
                                                                        <textarea class="form-control" onclick="remove_err('remarks_err')" name="verifierremarks" id="verifierremarks" readonly>${appDetails.verifierremarks}</textarea>
                                                                    </div>
                                                                </div>
                                                            </c:if>

                                                            <input type="hidden" class=" form-control number" value="${appDetails.cdbNo}" readonly name="cdbNo">

                                                            <div class="row pt-4">
                                                                <div class="col-md-12 col-sm-12 col-lg-12 col-xs-12">
                                                                    <label class="form-label">Your Remarks:</label>
                                                                    <textarea class="form-control" onclick="remove_err('remarks_err')" name="remarks" id="remarks"></textarea>
                                                                    <span id="remarks_err" class="text-danger"></span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <hr />
                                                <div class="form-group row pull-right">
                                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                                                        <button type="button" onclick="previousTab('categoryDtls','saveAndPreview')"  class="btn btn-success">
                                                            <i class="fa fa-arrow-left"></i>  Previous
                                                        </button>
                                                        <button class="btn btn-warning" type="button" id="showrejectsection" onclick="updateReject()"><span class="fa fa-times"></span> Reject </button>
                                                        <c:if test="${appDetails.updateStatus=='262a3f11-adbd-11e4-99d7-080027dcfac6'}">
                                                            <button type="button" onclick="verifyApplication()"  class="btn btn-primary">
                                                                <i class="fa fa-save"></i> Verify
                                                            </button>
                                                        </c:if>
                                                        <c:if test="${appDetails.updateStatus=='36f9627a-adbd-11e4-99d7-080027dcfac6'}">
                                                            <button type="button" onclick="approveApplication('cancel')"  class="btn btn-primary">
                                                                <i class="fa fa-save"></i> Approve
                                                            </button>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <jsp:include page="/WEB-INF/pages/architect/confirmationModal.jsp"/>
                    </form>
                    <script type="text/javascript" src="<c:url value="/resources/JqueryAjaxFormSubmit.js"/>"></script>
                    <script type="text/javascript" src="<c:url value="/resources/jquery.form.js"/>"></script>
                    <script src="<c:url value="/resources/js/cdb/architect.js"/>"></script>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
