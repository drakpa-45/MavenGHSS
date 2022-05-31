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
<head>
    <title></title>
</head>
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
                <div class="card" id="acknowledgementCard" style="display: none">
                    <div class="card-header">
                        <h3 class="card-title font-weight-bold">Application Acknowledgement</h3>
                    </div>
                    <div class="card-body" style="">
                        <div class="form-group">
                            <div class="col-lg-12">
                                Your application for <label class="control-label">Registration Of Architecture</label>
                                has been submitted and your application number is <label class="control-label">802_0000160</label>.<br>

                                <p>You will receive an email with the Application summary.</p>
                                <label class="control-label">You can track your application using above Application
                                    Number.</label> <br>
                            </div>

                        </div>

                        <div class="col-lg-12">
                            Thanks You.
                            <div class="col-lg-12 mt-3">
                                <input type="button" class="btn btn-primary" id="btnLogin1" onclick="window.print();"
                                       value="Print">
                            </div>
                        </div>
                    </div>
                </div>


                <div class="card" id="registrtaionFormCard">
                    <form id="engineerForm" action="<c:url value="/engineer"/>" method="post"
                          enctype="multipart/form-data" role="form">
                        <div class="card-header">
                            <h3 class="card-title font-weight-bold">Registration of Architect/Engineer</h3>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-12 col-lg-12">
                                    <div class="nav-tabs-custom">
                                        <ul class="m-0 nav nav-tabs">
                                            <li class="feesStructure tab-pane active">
                                                <a href="#" class="border text-white" data-toggle="tab"
                                                   data-placement="top">
                                                    <i class="fa fa-bookmark mr-1"></i>Fee Structure</a>
                                            </li>
                                            <li class="tab-pane personalInformation">
                                                <a href="#" class=" border" data-toggle="tab"
                                                   data-placement="top">
                                                    <i class="fa fa-users mr-1"></i>Personal Information</a>
                                            </li>
                                            <li class="tab-pane categoryDtls">
                                                <a href="#" class="border" data-toggle="tab"
                                                   data-placement="top">
                                                    <i class="fa fa-mobile mr-1"></i>Contact Details</a>
                                            </li>
                                            <li class="tab-pane saveAndPreview">
                                                <a href="#" class="border" data-toggle="tab"
                                                   data-placement="top">
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
                                                            <th>Type</th>
                                                            <th>Validity (yrs)</th>
                                                            <th>Amount(Nu.)</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>

                                                        <tr>
                                                            <td>
                                                                Architect Goverment
                                                            </td>
                                                            <td>
                                                                5
                                                            </td>
                                                            <td>
                                                                Free
                                                            </td>
                                                        </tr>

                                                        <tr>
                                                            <td>
                                                                Private Architect
                                                            </td>
                                                            <td>
                                                                2
                                                            </td>
                                                            <td>
                                                                2000.00
                                                            </td>
                                                        </tr>

                                                        <tr>
                                                            <td>
                                                                Engineer Goverment
                                                            </td>
                                                            <td>
                                                                5
                                                            </td>
                                                            <td>
                                                                Free
                                                            </td>
                                                        </tr>

                                                        <tr>
                                                            <td>
                                                                Private Engineer
                                                            </td>
                                                            <td>
                                                                2
                                                            </td>
                                                            <td>
                                                                2000.00
                                                            </td>
                                                        </tr>

                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div class="col-lg-12 form-group">
                                                    <div class="col-md-offset-11 col-lg-offset-11 col-xs-offset-11">
                                                        <button type="button"
                                                                onclick="nextTab('feesStructure','personalInformation')"
                                                                class="btn btn-primary">
                                                            <i class="fa fa-arrow-right"></i>
                                                            Next
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane personalInformation" id="personalInformation">
                                                <div class="">
                                                    <!-- Box Open -->
                                                    <div class="card tab2">
                                                        <div class="bg-blue card-status card-status-left"></div>
                                                        <div class="card-body">
                                                            <div class="col-lg-12">
                                                                <div class="col-lg-6 col-md-6 form-group">
                                                                    <label class="col-lg-4 form-label">Service for <span
                                                                            class="text-danger">*</span></label>
                                                                    <select class="form-control custom-select col-lg-8 select-beast"
                                                                            name="serviceTypeId">
                                                                        <option value="">Select</option>
                                                                        <option value="030aa6ae-24af-11e6-967f-9c2a70cc8e06">Engineer</option>
                                                                        <option value="030ace8e-24af-11e6-967f-9c2a70cc8e06">Architecture</option>
                                                                    </select>
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 form-group">
                                                                    <label class="col-lg-4 form-label">Country <span
                                                                            class="text-danger">*</span></label>
                                                                    <form:select path="countryList" name="countryId"
                                                                                 id="countryId"
                                                                                 class="form-control custom-select col-lg-8">
                                                                        <form:option value="">Select</form:option>
                                                                        <form:options items="${countryList}"
                                                                                      itemValue="value"
                                                                                      itemLabel="text"/>
                                                                    </form:select>
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-12">
                                                                <div class="col-lg-6 col-md-6 form-group">
                                                                    <label class="col-lg-4 form-label">Type: <span
                                                                            class="text-danger">*</span></label>
                                                                    <form:select path="typeList"
                                                                                 name="serviceSectorType"
                                                                                 id="serviceSectorType"
                                                                                 class="form-control custom-select col-lg-8 select-beast">
                                                                        <form:option value="">Select</form:option>
                                                                        <form:options items="${typeList}"
                                                                                      itemValue="value"
                                                                                      itemLabel="text"></form:options>
                                                                    </form:select>
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 form-group">
                                                                    <label class="col-lg-4 form-label">Trade: <span
                                                                            class="text-danger">*</span></label>
                                                                    <form:select path="tradeList" name="trade"
                                                                                 id="trade"
                                                                                 class="form-control custom-select col-lg-8 select-beast">
                                                                        <form:option value="">Select</form:option>
                                                                        <form:options items="${tradeList}"
                                                                                      itemValue="value"
                                                                                      itemLabel="text"></form:options>
                                                                    </form:select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <!-- Box Close -->
                                                    <!-- Box Open -->
                                                    <div class="card tab2">
                                                        <div class="bg-blue card-status card-status-left"></div>
                                                        <div class="card-header">
                                                            <h3 class="card-title">Personal Details</h3>
                                                        </div>
                                                        <div class="card-body">
                                                            <div class="col-lg-12">
                                                                <div class="col-lg-12">
                                                                    <div class="col-lg-6 col-md-6 form-group">
                                                                        <label class="col-lg-4 form-label">Salutation:
                                                                            <span class="text-danger">*</span></label>
                                                                        <form:select path="salutationList"
                                                                                     name="salutation"
                                                                                     id="salutation"
                                                                                     class="form-control custom-select col-lg-8 select-beast">
                                                                            <form:option value="">Select</form:option>
                                                                            <form:options items="${salutationList}"
                                                                                          itemValue="value"
                                                                                          itemLabel="text"></form:options>
                                                                        </form:select>
                                                                        <br/><br/>
                                                                        <label class="col-lg-4 form-label">Cid No: <span
                                                                                class="text-danger">*</span></label>
                                                                        <input type="text" maxlength="11" id="cid"
                                                                               placeholder="Enter 11 digit cid no."
                                                                               name="cid"
                                                                               class="form-control custom-select col-lg-8 number">
                                                                        <br/><br/>
                                                                        <label class="col-lg-4 form-label">Name: <span
                                                                                class="text-danger">*</span></label>
                                                                        <input type="text" placeholder="Full Name"
                                                                               name="name" maxlength="100" id="name"
                                                                               class="form-control custom-select col-lg-8">
                                                                        <br/><br/>
                                                                        <label class="col-lg-4 form-label">Dzongkhag:
                                                                            <span class="text-danger">*</span></label>
                                                                        <form:select path="dzongkhagList"
                                                                                     name="dzongkhagId"
                                                                                     id="dzongkhagId"
                                                                                     class="form-control custom-select col-lg-8 select-beast">
                                                                            <form:option
                                                                                    value="">Select</form:option>
                                                                            <form:options items="${dzongkhagList}"
                                                                                          itemValue="value"
                                                                                          itemLabel="text"></form:options>
                                                                        </form:select>
                                                                        <br/><br/>
                                                                        <label class="col-lg-4 form-label">Gewog </label>
                                                                        <select name="gewog"
                                                                                class="form-control custom-select col-lg-8"
                                                                                id="gewog">

                                                                        </select>
                                                                        <br/><br/>
                                                                        <label class="col-lg-4 form-label">Village </label>

                                                                        <select id="village" name="village"
                                                                                class="form-control custom-select col-lg-8">

                                                                        </select>
                                                                    </div>
                                                                    <div class="col-lg-6 col-md-6 form-group">
                                                                        <img src="" width="200px"
                                                                             height="200px" class="pull-right">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- Box Close -->
                                                </div>
                                                <div class="col-lg-12 form-group">
                                                    <button type="button"
                                                            onclick="nextTab('personalInformation','categoryDtls')"
                                                            class="btn btn-primary">
                                                        <i class="fa fa-arrow-right"></i>
                                                        Next
                                                    </button>
                                                </div>
                                            </div>
                                            <div class="tab-pane categoryDtls" id="contact_detail">
                                                <div class="col-lg-12 form-group">
                                                    <div class="card tab2">
                                                        <div class="bg-blue card-status card-status-left"></div>
                                                        <div class="card-header">
                                                            <h3 class="card-title">Contact Details</h3>
                                                        </div>
                                                        <div class="card-body">
                                                            <div class="col-lg-12">
                                                                <div class="col-lg-6 col-md-6 form-group">
                                                                    <label class="col-lg-5 form-label">Email <span
                                                                            class="text-danger">*</span></label>
                                                                    <input id="email" type="email"
                                                                           class=" form-control col-lg-7"
                                                                           name="email" placeholder="Type valid email">
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 form-group">
                                                                    <label class="col-lg-5 form-label">Mobile No <span
                                                                            class="text-danger">*</span></label>
                                                                    <input type="text"
                                                                           class=" form-control col-lg-7 number"
                                                                           id="mobileNo" name="mobileNo"
                                                                           maxlength="8">
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-12">
                                                                <div class="col-lg-6 col-md-6 form-group">
                                                                    <label class="col-lg-5 form-label">Office/Employer
                                                                        Name </label>
                                                                    <input type="text" class="form-control col-lg-7"
                                                                           name="employeeName" placeholder="Text..">
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 form-group">
                                                                    <label class="col-lg-5 form-label">Office/Employer
                                                                        Address</label>
                                                                <textarea class="form-control col-lg-7"
                                                                          name="employeeAddress"></textarea>
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
                                                            <div class="col-lg-12">
                                                                <div class="col-lg-6 col-md-6 form-group">
                                                                    <label class="col-lg-5 form-label">Qualification <span
                                                                            class="text-danger">*</span></label>
                                                                    <form:select path="qualificationList"
                                                                                 name="qualificationId"
                                                                                 id="qualificationId"
                                                                                 class="form-control col-lg-7 custom-select text-left select-beast">
                                                                        <form:option
                                                                                value="">Select</form:option>
                                                                        <form:options items="${qualificationList}"
                                                                                      itemValue="value"
                                                                                      itemLabel="text"></form:options>
                                                                    </form:select>
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 form-group">
                                                                    <label class="col-lg-5 form-label">Year of
                                                                        graduation<span
                                                                                class="text-danger">*</span></label>
                                                                    <input type="text"
                                                                           class=" form-control col-lg-7 number"
                                                                           maxlength="4" id="graduationYear"
                                                                           name="graduationYear" placeholder="year..">
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-12">
                                                                <div class="col-lg-6 col-md-6 form-group">
                                                                    <label class="col-lg-5 form-label">Name of
                                                                        University </label>
                                                                    <input type="text" class="form-control col-lg-7"
                                                                           name="universityName" id="universityName"
                                                                           placeholder="university name">
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 form-group">
                                                                    <label class="col-lg-5 form-label">Country </label>

                                                                    <form:select path="countryList"
                                                                                 name="universityCountry"
                                                                                 id="universityCountry"
                                                                                 class="form-control custom-select col-lg-7">
                                                                        <form:option value="">Select</form:option>
                                                                        <form:options items="${countryList}"
                                                                                      itemValue="value"
                                                                                      itemLabel="text"></form:options>
                                                                    </form:select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <button type="button"
                                                            onclick="nextTab('categoryDtls','saveAndPreview')"
                                                            class="btn btn-primary">
                                                        <i class="fa fa-arrow-right"></i>
                                                        Next
                                                    </button>
                                                </div>
                                            </div>

                                            <div class="tab-pane saveAndPreview" id="saveAndPreview">
                                                <div>
                                                    <div id="submitSection" style="">
                                                        <div class="panel panel-default">
                                                            <div class="panel-body">
                                                                <div class="form-group">
                                                                    <label class="col-lg-2">Attachments :</label>

                                                                    <div class="col-lg-4">
                                                                        <input type="file" name="attachmentFile"
                                                                               required="true"
                                                                               id="attachmentFile">
                                                                    </div>
                                                                </div>
                                                                <br/>

                                                                <div class="form-froup">
                                                                    <div class="col-lg-12">
                                                                        <strong>Terms and Condition</strong>

                                                                        <div class="form-group">
                                                                            <div class="col-lg-12" id="">
                                                                                1. As provided in clause 2.1.1.2 and
                                                                                2.3.1
                                                                                of Procurement Rules and Regulations
                                                                                2009,
                                                                                the holder of this Certificate is
                                                                                qualified
                                                                                to participate in public procurement
                                                                                procedure.

                                                                                <br> 2. The issuance of CDB Registration
                                                                                Certificate will be based largely on the
                                                                                fulfillment of the minimum criteria set
                                                                                against classification of
                                                                                Contractor/Consultant and Categorization
                                                                                of
                                                                                Works and upon certification by
                                                                                competent
                                                                                authority for construction
                                                                                professionals.

                                                                                <br> 3. All the registered contractors
                                                                                should comply with 'Code of ethics for
                                                                                Contractors'.

                                                                                <br> 4. CDB will not be accountable for
                                                                                any
                                                                                false/fabricated submission that could
                                                                                have
                                                                                led to the fulfillment of the criteria
                                                                                and
                                                                                subsequent issue of CDB Registration
                                                                                Certificate.

                                                                                <br> 5. CDB Registration Certificate
                                                                                once
                                                                                issued would not relieve the certificate
                                                                                holder of any relaxation on the minimum
                                                                                requirements for registration.

                                                                                <br> 6. Notwithstanding the provisions
                                                                                of
                                                                                Companies Act of Bhutan, the certificate
                                                                                issued is non-transferable even if the
                                                                                promoters separate and establish similar
                                                                                companies.

                                                                                <br> 7. CDB Certificate cannot be leased
                                                                                or
                                                                                subleased to any individual or another
                                                                                firm.

                                                                                <br> 8. Certificate is valid during the
                                                                                period for which it was issued provided
                                                                                it
                                                                                has not been cancelled, suspended or
                                                                                revoked
                                                                                by CDB or any other competent authority.

                                                                                <br> 9. Failing to renew within the
                                                                                expiry
                                                                                date will lead to penalty of Nu.100 per
                                                                                day.

                                                                                <br> 10. Failing to pay the fees for
                                                                                approved online application within 30
                                                                                days
                                                                                will lead to cancellation of the
                                                                                application.

                                                                                <br> 11. All registered construction
                                                                                firm
                                                                                must attend the mandatory refresher
                                                                                course
                                                                                in order to apply for renewal.

                                                                                <br> 12. No Contractors can submit bid,
                                                                                participate in bidding or be on the
                                                                                contention for award if the registration
                                                                                has
                                                                                expired.

                                                                                <br> 13. No Contractors can
                                                                                undertake/implement works which is not
                                                                                within the scope of the registration.

                                                                                <br> 14. CDB may verify the resources
                                                                                committed for the projects as and when
                                                                                desires.

                                                                                <br> 15. The registration is subject to
                                                                                verification whenever the CDB so
                                                                                desires.
                                                                                CDB will inspect the minimum mandatory
                                                                                requirement of manpower and equipment of
                                                                                Large and Medium contractors and the
                                                                                during
                                                                                the time of monitoring, every firm must
                                                                                extend necessary support and cooperation
                                                                                to
                                                                                CDB Officials.

                                                                                <br> 16. Large and Medium Contractors
                                                                                must
                                                                                have Office established with Signboard
                                                                                (requirements of office and signboard as
                                                                                determined by CDB)

                                                                                <br> 17. Registered firms are required
                                                                                to
                                                                                inform the CDB of any changes in their
                                                                                address, contact details or any
                                                                                pertinent
                                                                                particulars within one month.

                                                                                <br> 18. The CDB Registration
                                                                                Certificate
                                                                                can be revoked, downgraded, suspended or
                                                                                cancelled at any given time if the:
                                                                                <br> a. Holder undertakes unlawful
                                                                                participation in the procurement
                                                                                process;
                                                                                <br> b. Entity does not possess the
                                                                                minimum
                                                                                requirements during the physical
                                                                                verification process (at the discretion
                                                                                of
                                                                                CDB);
                                                                                <br> c. Entity has obtained the same due
                                                                                to
                                                                                false submissions;
                                                                                <br> d. Entity becomes bankrupt or winds
                                                                                up;
                                                                                or
                                                                                <br> e. Entity has been charged by the
                                                                                court
                                                                                for penal offence.
                                                                                <br> This Terms of Condition is hereby
                                                                                endorsed and enforced with immediate
                                                                                effect.
                                                                                <br> (Phub Rinzin)
                                                                                <br> Director
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="col-lg-12">I/We declare and confirm
                                                                        that:- </label>

                                                                    <div id="termsAndCon">
                                                                        <ul>
                                                                            <li>All information and attachments with
                                                                                this
                                                                                application are true and correct;
                                                                            </li>
                                                                            <li>I am/we are aware that any false
                                                                                information
                                                                                provided herein will result in rejection
                                                                                of
                                                                                my application and suspension of any
                                                                                registered granted;
                                                                            </li>
                                                                            <li>I/We shall not make refund claims of
                                                                                expenditure incurred in processing this
                                                                                application;
                                                                            </li>
                                                                            <li>I/We have read and understood the 'Code
                                                                                of
                                                                                Ethics' and shall perform in line with
                                                                                Code
                                                                                of Ethics and any other legislation in
                                                                                force. Failure to comply, will be
                                                                                subject to
                                                                                the penalities provided for in the
                                                                                applicable legislation of the country.
                                                                            </li>
                                                                            <li>I/We hereby declare that issue of CDB
                                                                                certificate does not in anyway
                                                                                constitute an
                                                                                obligation on the part of CDB or any
                                                                                other
                                                                                Goverment agency to provide contract
                                                                                works.
                                                                            </li>
                                                                        </ul>
                                                                    </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="col-lg-12">
                                                                    <span><input type="checkbox" id="submitcheckbox"
                                                                                 name="tnc" class="required"
                                                                                 style="width:15px;height:15px;"></span>
                                                                        <span class="bold"> I agree to the above Terms Conditions</span>
                                                                    </label>
                                                                    <!-- <br /><span class="text-danger" id="termAndConErrorClass"></span> -->
                                                                </div>
                                                                <input type="hidden" name="addedeqCount"
                                                                       id="addedeqCount">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-offset-5 text-center">
                                                    <input type="submit" class="btn btn-primary col-lg-2 mr-2"
                                                           id="btnSave" value="Save">
                                                    <%--onclick="showAcknowledgement()"--%>

                                                    <input class="btn btn-danger col-lg-2" id="btnCancel" type="button"
                                                           value="Cancel">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <script src="<c:url value="/resources/js/cdb/engineer.js"/>"></script>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>




