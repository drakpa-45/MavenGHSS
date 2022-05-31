<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                                This application is verified and forwarded for approval,.<br>

                                <p>Click on tasklist button to go back to tasklist.</p>
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
                    <div class="card-header">
                        <h3 class="card-title font-weight-bold">Registration of Architect/Engineer</h3>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-12 col-lg-12">
                                <div class="nav-tabs-custom">
                                    <ul class="m-0 nav nav-tabs">
                                        <li class="tab-pane generalInformation tab-pane active">
                                            <a href="#general_Information" class=" border" data-toggle="tab"
                                               data-placement="top">
                                                <i class="fa fa-users mr-1"></i>Personal Information</a>
                                        </li>
                                        <li class="tab-pane categoryDtls">
                                            <a href="#category_details" class="border" data-toggle="tab"
                                               data-placement="top">
                                                <i class="fa fa-mobile mr-1"></i>Contact Details</a>
                                        </li>
                                        <li class="tab-pane saveAndPreview">
                                            <a href="#saveAndPreview" class="border" data-toggle="tab"
                                               data-placement="top">
                                                <i class="fa fa-file mr-1"></i>Attachments</a>
                                        </li>
                                    </ul>
                                    <div class="tab-content border p-3 col-lg-12">
                                        <div class="tab-pane active generalInformation">
                                            <form action="" method="post" class="">
                                                <%--<input type="text" class="hidden" id="applicationId">--%>

                                                <div class="">
                                                    <!-- Box Open -->
                                                    <div class="card tab2">
                                                        <div class="bg-blue card-status card-status-left"></div>
                                                        <div class="card-body">
                                                            <div class="col-lg-12">
                                                                <div class="col-lg-6 col-md-6 form-group">
                                                                    <label class="col-lg-4 form-label">Service for <span
                                                                            class="text-danger">*</span></label>
                                                                    <form:select path="serviceForList"
                                                                                 name="serviceTypeId"
                                                                                 id="serviceTypeId" requird="true"
                                                                                 class="form-control custom-select col-lg-8 select-beast">
                                                                        <form:option value="">Select</form:option>
                                                                        <form:options items="${serviceForList}"
                                                                                      itemValue="value"
                                                                                      itemLabel="text"></form:options>
                                                                    </form:select>
                                                                </div>
                                                                <div class="col-lg-6 col-md-6 form-group">
                                                                    <label class="col-lg-4 form-label">Country <span
                                                                            class="text-danger">*</span></label>
                                                                    <form:select path="countryList"
                                                                                 name="countryId"
                                                                                 id="countryId" requird="true"
                                                                                 class="form-control custom-select col-lg-8 select-beast">
                                                                        <form:option value="">Select</form:option>
                                                                        <form:options items="${countryList}"
                                                                                      itemValue="value"
                                                                                      itemLabel="text"></form:options>
                                                                    </form:select>
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-12">
                                                                <div class="col-lg-6 col-md-6 form-group">
                                                                    <label class="col-lg-4 form-label">Type: <span
                                                                            class="text-danger">*</span></label>
                                                                    <form:select path="typeList"
                                                                                 name="type"
                                                                                 id="type" requird="true"
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
                                                                    <form:select path="tradeList"
                                                                                 name="trade"
                                                                                 id="trade" requird="true"
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
                                                            <h3 class="card-title">Personal Detials</h3>
                                                        </div>
                                                        <div class="card-body">
                                                            <div class="col-lg-12">
                                                                <div class="col-lg-12">
                                                                    <div class="col-lg-6 col-md-6 form-group">
                                                                        <label class="col-lg-4 form-label">Salutation:
                                                                            <span class="text-danger">*</span></label>
                                                                        <form:select path="salutationList"
                                                                                     name="salutation"
                                                                                     id="salutation" requird="true"
                                                                                     class="form-control custom-select col-lg-8 select-beast">
                                                                            <form:option value="">Select</form:option>
                                                                            <form:options items="${salutationList}"
                                                                                          itemValue="value"
                                                                                          itemLabel="text"></form:options>
                                                                        </form:select>
                                                                        <br/><br/>
                                                                        <label class="col-lg-4 form-label">Cid No: <span
                                                                                class="text-danger">*</span></label>
                                                                        <input type="text" name="cid" id="cid"
                                                                               class="form-control custom-select col-lg-8">
                                                                        <br/><br/>
                                                                        <label class="col-lg-4 form-label">Name: <span
                                                                                class="text-danger">*</span></label>
                                                                        <input type="text" id="name" name="name"
                                                                               class="form-control custom-select col-lg-8">
                                                                        <br/><br/>
                                                                        <label class="col-lg-4 form-label">Dzongkhag:
                                                                            <span class="text-danger">*</span></label>
                                                                        <form:select path="dzongkhagList"
                                                                                     name="dzongkhagId"
                                                                                     id="dzongkhagId" requird="true"
                                                                                     class="form-control custom-select col-lg-8 select-beast">
                                                                            <form:option value="">Select</form:option>
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
                                                                        <img src="assets/images/ip.jpeg" width="200px"
                                                                             height="200px" class="pull-right">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <!-- Box Close -->
                                                </div>

                                            </form>
                                            <div class="col-lg-12 form-group">
                                                <button type="button"
                                                        onclick="nextTab('generalInformation','categoryDtls')"
                                                        class="btn btn-primary">
                                                    <i class="fa fa-arrow-right"></i>
                                                    Next
                                                </button>
                                            </div>
                                        </div>
                                        <div class="tab-pane categoryDtls">
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
                                                                <input type="text" class=" form-control col-lg-7"
                                                                       name="email" id="email">
                                                            </div>
                                                            <div class="col-lg-6 col-md-6 form-group">
                                                                <label class="col-lg-5 form-label">Mobile No <span
                                                                        class="text-danger">*</span></label>
                                                                <input type="text" id="mobileNo"
                                                                       class=" form-control col-lg-7"
                                                                       name="mobileNo" placeholder="Text..">
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-12">
                                                            <div class="col-lg-6 col-md-6 form-group">
                                                                <label class="col-lg-5 form-label">Office/Employer
                                                                    Name </label>
                                                                <input type="text" id="employeeName"
                                                                       class="form-control col-lg-7"
                                                                       name="employeeName" placeholder="Text..">
                                                            </div>
                                                            <div class="col-lg-6 col-md-6 form-group">
                                                                <label class="col-lg-5 form-label">Office/Employer
                                                                    Address</label>
                                                                <textarea class="form-control col-lg-7"
                                                                          name="employeeAddress" id="employeeAddress">Address of the employer</textarea>
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
                                                                             id="qualificationId" requird="true"
                                                                             class="form-control custom-select col-lg-7 select-beast">
                                                                    <form:option value="">Select</form:option>
                                                                    <form:options items="${qualificationList}"
                                                                                  itemValue="value"
                                                                                  itemLabel="text"></form:options>
                                                                </form:select>
                                                            </div>
                                                            <div class="col-lg-6 col-md-6 form-group">
                                                                <label class="col-lg-5 form-label">Year of
                                                                    graduation<span class="text-danger">*</span></label>
                                                                <input type="text"
                                                                       class="form-control col-lg-7" id="graduationYear"
                                                                       name="graduationYear" placeholder="year..">
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-12">
                                                            <div class="col-lg-6 col-md-6 form-group">
                                                                <label class="col-lg-5 form-label">Name of
                                                                    University </label>
                                                                <input type="text" class="form-control col-lg-7"
                                                                       id="universityName"
                                                                       name="example-text-input"
                                                                       placeholder="university name">
                                                            </div>
                                                            <div class="col-lg-6 col-md-6 form-group">
                                                                <label class="col-lg-5 form-label">Country </label>
                                                                <form:select path="countryList"
                                                                             name="universityCountryId"
                                                                             id="universityCountryId" requird="true"
                                                                             class="form-control custom-select col-lg-7 select-beast">
                                                                    <form:option value="">Select</form:option>
                                                                    <form:options items="${countryList}"
                                                                                  itemValue="value"
                                                                                  itemLabel="text"></form:options>
                                                                </form:select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <button type="button" onclick="nextTab('categoryDtls','saveAndPreview')"
                                                        class="btn btn-primary">
                                                    <i class="fa fa-arrow-right"></i>
                                                    Next
                                                </button>
                                            </div>
                                        </div>

                                        <div class="tab-pane saveAndPreview">
                                            <div>
                                                <div id="submitSection" style="">
                                                    <div class="panel-body">
                                                        <table class="table card-table table-vcenter"
                                                               id="documentAttachTableId">
                                                            <thead>
                                                            <tr>
                                                                <th width="10%">Sl No.</th>
                                                                <th width="55%">Attachment</th>
                                                                <th>documentPath</th>
                                                                <th colspan="2" width="35%">Action</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            </tbody>
                                                        </table>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-md-12">
                                                            <table class="table table-bordered card-table table-vcenter"
                                                                   id="applicationStatusTableId">
                                                                <thead>
                                                                <tr>
                                                                    <th>Status</th>
                                                                    <th>Action By</th>
                                                                    <th>Action Date</th>
                                                                    <th>Remarks</th>
                                                                </tr>
                                                                </thead>
                                                                <tbody>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                    <br><br><br><br>

                                                    <div class="form-group">
                                                        <label class="col-md-2 required">Remarks: <span
                                                                class="text-danger">*</span></label>

                                                        <div class="col-md-10">
                                                                    <textarea name="verificationremarks"
                                                                              id="remark"
                                                                              class="form-control" required></textarea>
                                                                        <span class="help-block"
                                                                              id="remarkErrorId"></span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-md-12">
                                                <span class="text-danger" id="generalError"></span><br>
                                                <button type="button" class="btn btn-primary"
                                                        id="approveApplication"><i
                                                        class="fa fa-check"></i>&nbsp;&nbsp;Approved
                                                </button>
                                                <button type="button" class="btn btn-primary"
                                                        id="sendBackApplication"><i
                                                        class="fa fa-backward"></i>&nbsp;&nbsp;Send back
                                                </button>
                                                <button type="button" class="btn btn-primary"
                                                <%-- onclick="showAcknowledgement()" --%>
                                                        id="verifyApplication"><i
                                                        class="fa fa-check"></i>&nbsp;&nbsp;Verify
                                                </button>

                                                <button type="button" <%--onclick="displayTab('rejectTab')"--%>
                                                        class="btn btn-danger " id="rejectApplication"><i
                                                        class="fa fa-times mr-1"></i>Reject
                                                </button>
                                                <a href="/NEWCDB/TasklistAccordionAction.do">
                                                    <button type="button" class="btn btn-warning"><i
                                                            class="fa fa-ban"></i> Cancel
                                                    </button>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/resources/js/cdb/EngineerProcess.js"/>"></script>
</body>
