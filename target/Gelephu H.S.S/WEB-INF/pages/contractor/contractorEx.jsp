<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 9/22/2019
  Time: 11:00 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
</head>
<body>
<div class="page-header">
    <h1 class="page-title">
        Construction Development Board
    </h1>
</div>

<div class="card" id="registrtaionFormCard">
    <div class="card-header">
        <h3 class="card-title font-weight-bold">Registration of Contractor</h3>
    </div>
    <div class="card-body">

        <ul class="nav nav-tabs nav-fill text-white" role="tablist">

            <li class="nav-item active fees_structure">
                <a class="nav-link active" href="#fees_structure" role="tab" data-toggle="tab"
                   data-placement="top">
                    <i class="fa fa-bookmark mr-1"></i>Fee Structure</a>
            </li>
            <li class="nav-item general_Information">
                <a class="nav-link text-primary" href="#general_Information" role="tab" data-toggle="tab"
                   data-placement="top">
                    <i class="fa fa-exclamation-circle mr-1"></i>General Information</a>
            </li>
            <li class="nav-item  category_details">
                <a class="nav-link text-primary" href="#category_details" role="tab" data-toggle="tab"
                   aria-selected="true" data-placement="top">
                    <i class="fa fa-sitemap mr-1"></i>Category Details</a>
            </li>
            <li class="nav-item border human_resource_criteria">
                <a class="nav-link text-primary" href="#human_resource_criteria" role="tab" data-toggle="tab"
                   aria-selected="true" data-placement="top">
                    <i class="fa fa-users mr-1"></i>Human Resource Criteria</a>
            </li>
            <li class="nav-item border equipment_details">
                <a class="nav-link text-primary" href="#equipment_details" role="tab" data-toggle="tab"
                   aria-selected="true" data-placement="top">
                    <i class="fa fa-truck mr-1"></i>Consultant Equipment Details</a>
            </li>
            <li class="nav-item border saveAndPreview">
                <a class="nav-link text-primary" href="#saveAndPreview" role="tab" data-toggle="tab"
                   aria-selected="true" data-placement="top">
                    <i class="fa fa-file mr-1"></i>Preview</a>
            </li>
        </ul>
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active fees_structure" id="fees_structure">
                <jsp:include page="fee.jsp"/>
            </div>

            <div role="tabpanel" class="tab-pane general_Information" id="general_Information">
                <jsp:include page="gInfo.jsp"/>
            </div>

            <div role="tabpanel" class="tab-pane category_details" id="category_details">
                <form name="contractorCCForm" action="" method="post" class="globalForm" id="contractorCCForm">
                    <table id="largeTableId" class="table table-bordered table-hover">
                        <thead style="background-color: #F2F2F2">
                        <tr>
                            <td></td>
                            <td>Category</td>
                            <td>Apply for Class</td>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${categoryList}" var="category" varStatus="i">

                            <tr>
                                <td><input class="form-control" type="checkbox"
                                           name="categories[${i.index}].projectCateID" value="${category.id}"
                                           onclick="EnableDisableTextBox(this)"
                                           style="width: 17px; height: 17px;"></td>
                                <td>${category.code}-${category.name} </td>

                                <td><select name="categories[${i.index}].appliedClassID" class="form-control">
                                    <option value="">-Select-</option>

                                    <c:forEach items="${classification}" var="c">
                                        <c:choose>
                                            <c:when test="${(category.code eq 'W2') && (c.value eq  '0c14ebea-c3eb-11e4-af9f-080027dcfac6')}">
                                                <option value="${c.value}">${c.text}</option>
                                            </c:when>
                                            <c:when test="${(category.code ne 'W2') && (c.value ne '0c14ebea-c3eb-11e4-af9f-080027dcfac6')}">
                                                <option value="${c.value}">${c.text}</option>
                                            </c:when>
                                        </c:choose>
                                    </c:forEach>

                                </select></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="col-lg-12 form-group">
                        <button type="button"
                                onclick="nextTab('generalInformation','categoryDtls')"
                                class="btn btn-azure col-lg-offset-9">
                            <i class="fa fa-arrow-left"></i>
                            Back
                        </button>
                        <input type="submit" id="btnContractorCC"
                        <%--onclick="nextTab('categoryDtls','humanResourceCriteria')"--%>
                               class="btn btn-primary" value="Save & Next">

                    </div>

                </form>

            </div>

            <div role="tabpanel" class="tab-pane human_resource_criteria" id="human_resource_criteria">

                <form action="" method="post" class="">
                    <div class="">
                        <div class="row">
                            <div class="col-md-12 col-lg-12">
                                <!-- Box Open -->
                                <div class="card tab4">
                                    <div class="bg-blue card-status card-status-left"></div>
                                    <div class="card-header">
                                        <h3 class="card-title">Human Resource Criteria</h3>
                                    </div>
                                    <div class="card-body">
                                        <h4>Requirements for different Category and
                                            Classification</h4>

                                        <div class="col-sm-5">
                                            <h6>Note for Civil Engineering Services</h6>
                                            <table class="table table-bordered">
                                                <thead class="badge-default">
                                                <tr>
                                                    <th>Employees</th>
                                                    <th>C1&C4</th>
                                                    <th>C2</th>
                                                    <th>C3</th>
                                                    <th>C5&C6</th>
                                                    <th>C7</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <td>Manager</td>
                                                    <td>1</td>
                                                    <td>1</td>
                                                    <td>1</td>
                                                    <td>1</td>
                                                    <td>1</td>
                                                </tr>
                                                <tr>
                                                    <td>Engineer (Degree)</td>
                                                    <td>1</td>
                                                    <td>1*</td>
                                                    <td>1**</td>
                                                    <td>-</td>
                                                    <td>1</td>
                                                </tr>
                                                <tr>
                                                    <td>Engineer (Diploma)</td>
                                                    <td>-</td>
                                                    <td>-</td>
                                                    <td>-</td>
                                                    <td>1</td>
                                                    <td>-</td>
                                                </tr>
                                                <tr>
                                                    <td>Surveyor</td>
                                                    <td>-</td>
                                                    <td>-</td>
                                                    <td>-</td>
                                                    <td>1</td>
                                                    <td>-</td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="col-sm-7 table-responsive">
                                            <h6>Note for Electrical Engineering
                                                Services</h6>
                                            <table class="table table-bordered">
                                                <thead class="badge-default">
                                                <tr>
                                                    <th>Employees</th>
                                                    <th>E1&E2</th>
                                                    <th>E3&E4</th>
                                                    <th>E5&E6</th>
                                                    <th>E7</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <td>Manager</td>
                                                    <td>1</td>
                                                    <td>1</td>
                                                    <td>1</td>
                                                    <td>1</td>
                                                </tr>
                                                <tr>
                                                    <td>Engineer (Degree)</td>
                                                    <td>
                                                        1(civil),1(Elect),1(Mech),1(Hydrologiest)
                                                    </td>
                                                    <td>1(civil),1(Elect)</td>
                                                    <td>1(Elect)</td>
                                                    <td>-</td>
                                                </tr>
                                                <tr>
                                                    <td>Engineer (Diploma)</td>
                                                    <td>-</td>
                                                    <td>1(civil)</td>
                                                    <td>1(civil)</td>
                                                    <td>1(Elect)</td>
                                                </tr>
                                                </tbody>
                                            </table>
                                            <h6>Note for Architectural Services</h6>
                                            <table class="table table-bordered">
                                                <thead class="badge-default">
                                                <tr>
                                                    <th>Employees</th>
                                                    <th>A1</th>
                                                    <th>A2</th>
                                                    <th>A3</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <td>Architect(Bhutanese)Master/Degree
                                                    </td>
                                                    <td>1</td>
                                                    <td>1</td>
                                                    <td>1</td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <!-- Box Close -->
                                <!-- Box Open -->
                                <div class="card tab4">
                                    <div class="bg-blue card-status card-status-left"></div>
                                    <div class="card-header">
                                        <h3 class="card-title">Human Resource Form</h3>
                                    </div>
                                    <div class="card-body">
                                        <div class="col-sm-12">
                                            <table class="table table-bordered table-center"
                                                   id="hrDtlsTable">
                                                <thead class="badge-default">
                                                <tr>
                                                    <th>Salutation</th>
                                                    <th>Name</th>
                                                    <th>ID/Work Permit No.</th>
                                                    <th>sex</th>
                                                    <th>Country</th>
                                                    <th>Designation</th>
                                                    <th>Qualification</th>
                                                    <th>Trade/Fields</th>
                                                    <th>Service Type</th>
                                                    <th>Attachments (CV/UT/AT)</th>
                                                    <th>Action</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <!--  <tr class="noRecord">
                                                   <td colspan="11">No Record Entered. Please click on 'Add More HR' button to enter details</td>
                                                 </tr> -->
                                                </tbody>
                                            </table>
                                            <div class="col-lg-12 text-right">
                                                <button type="button"
                                                        class="btn btn-info btn-sm"
                                                        title="Payment" data-toggle="modal"
                                                        data-target="#updatePaymentModal"><i
                                                        class="fa fa-plus"></i> Add More HR
                                                </button>

                                                <!-- HR modal open -->
                                                <div aria-hidden="true"
                                                     aria-labelledby="myModalLabel"
                                                     role="dialog" tabindex="-1"
                                                     class="modal fade in"
                                                     id="updatePaymentModal">
                                                    <div class="modal-dialog modal-lg">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h4 id="myModalLabel"
                                                                    class="modal-title">Add
                                                                    Human Resource</h4>
                                                                <button aria-hidden="true"
                                                                        data-dismiss="modal"
                                                                        class="close"
                                                                        type="button">
                                                                    <span></span></button>
                                                            </div>
                                                            <div class="modal-body form-horizontal">
                                                                <div class="">
                                                                    <div class="form-group">
                                                                        <label class="col-lg-2">Nationality
                                                                            <span class="text-danger">*</span>:</label>

                                                                        <div class="col-lg-4">
                                                                            <select name="beast"
                                                                                    id="hr5"
                                                                                    class="form-control custom-select text-left select-beast">
                                                                                <option value="1">
                                                                                    Select
                                                                                    Nationality
                                                                                </option>
                                                                                <option value="afghan">
                                                                                    Afghan
                                                                                </option>
                                                                                <option value="albanian">
                                                                                    Albanian
                                                                                </option>
                                                                                <option value="belgian">
                                                                                    Belgian
                                                                                </option>
                                                                            </select>
                                                                        </div>
                                                                        <label class="col-lg-2">CID/Work
                                                                            Permit No <span
                                                                                    class="text-danger">*</span>:</label>

                                                                        <div class="col-lg-4">
                                                                            <div class="input-icon">
                                                                  <span class="input-icon-addon">
                                                                    <i class="fa fa-address-card-o"></i>
                                                                  </span>
                                                                                <input type="text"
                                                                                       class="form-control"
                                                                                       id="hr3"
                                                                                       placeholder="CID/Work Permit No">
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label class="col-lg-2">Salutation
                                                                            <span class="text-danger">*</span>:</label>

                                                                        <div class="col-lg-4">
                                                                            <select name="beast"
                                                                                    id="hr1"
                                                                                    class="form-control custom-select text-left select-beast">
                                                                                <option value="1">
                                                                                    Select
                                                                                    Salutation
                                                                                </option>
                                                                                <option value="2">
                                                                                    Ashi
                                                                                </option>
                                                                                <option value="3">
                                                                                    Dasho
                                                                                </option>
                                                                                <option value="4">
                                                                                    Lynpo
                                                                                </option>
                                                                                <option value="5">
                                                                                    Mr.
                                                                                </option>
                                                                                <option value="6">
                                                                                    Mrs.
                                                                                </option>
                                                                                <option value="7">
                                                                                    Ms.
                                                                                </option>
                                                                            </select>
                                                                        </div>
                                                                        <label class="col-lg-2">Name
                                                                            :</label>

                                                                        <div class="col-lg-4">
                                                                            <div class="input-icon">
                                                                  <span class="input-icon-addon">
                                                                    <i class="fe fe-user"></i>
                                                                  </span>
                                                                                <input type="text"
                                                                                       id="hr2"
                                                                                       class="form-control"
                                                                                       placeholder="Name">
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label class="col-lg-2">Gender
                                                                            :</label>

                                                                        <div class="col-lg-4">
                                                                            <select name="beast"
                                                                                    id="hr4"
                                                                                    class="form-control custom-select text-left select-beast">
                                                                                <option value="1">
                                                                                    Select
                                                                                    Gender
                                                                                </option>
                                                                                <option value="2">
                                                                                    Male
                                                                                </option>
                                                                                <option value="3">
                                                                                    Female
                                                                                </option>
                                                                            </select>
                                                                        </div>
                                                                        <label class="col-lg-2">Designation
                                                                            <span class="text-danger">*</span>:</label>

                                                                        <div class="col-lg-4">
                                                                            <select name="beast"
                                                                                    id="hr6"
                                                                                    class="form-control custom-select text-left select-beast">
                                                                                <option value="1">
                                                                                    Select
                                                                                    Designation
                                                                                </option>
                                                                                <option value="Site Supervisor">
                                                                                    Site
                                                                                    Supervisor
                                                                                </option>
                                                                                <option value="Accountant">
                                                                                    Accountant
                                                                                </option>
                                                                                <option value="Accountant">
                                                                                    Engineer
                                                                                </option>
                                                                                <option value="Manager">
                                                                                    Manager
                                                                                </option>
                                                                                <option value="Proprietor">
                                                                                    Proprietor
                                                                                    cum
                                                                                    Engineer
                                                                                </option>
                                                                                <option value="Project">
                                                                                    Project
                                                                                    Manager
                                                                                </option>
                                                                                <option value="Store">
                                                                                    Store
                                                                                    Keeper
                                                                                </option>
                                                                                <option value="Welder">
                                                                                    Welder
                                                                                    II
                                                                                </option>
                                                                            </select>
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label class="col-lg-2">Qualification
                                                                            <span class="text-danger">*</span>:</label>

                                                                        <div class="col-lg-4">
                                                                            <select name="beast"
                                                                                    id="hr7"
                                                                                    class="form-control custom-select text-left select-beast">
                                                                                <option value="1">
                                                                                    Select
                                                                                    Qualification
                                                                                </option>
                                                                                <option value="Certificate">
                                                                                    Certificate
                                                                                </option>
                                                                                <option value="Degree">
                                                                                    Degree
                                                                                </option>
                                                                                <option value="Diploma">
                                                                                    Diploma
                                                                                </option>
                                                                                <option value="VTI">
                                                                                    VTI
                                                                                </option>
                                                                            </select>
                                                                        </div>
                                                                        <label class="col-lg-2">Trade
                                                                            <span class="text-danger">*</span>:</label>

                                                                        <div class="col-lg-4">
                                                                            <select name="beast"
                                                                                    id="hr8"
                                                                                    class="form-control custom-select text-left select-beast">
                                                                                <option value="1">
                                                                                    Select
                                                                                    Trade
                                                                                </option>
                                                                                <option value="Class XII">
                                                                                    Class
                                                                                    XII
                                                                                </option>
                                                                                <option value="Class VIII">
                                                                                    Class
                                                                                    VIII
                                                                                </option>
                                                                                <option value="Civil">
                                                                                    Civil
                                                                                </option>
                                                                                <option value="Class X">
                                                                                    Class X
                                                                                </option>
                                                                                <option value="Mechanical">
                                                                                    Mechanical
                                                                                </option>
                                                                            </select>
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label class="col-lg-2">Service
                                                                            Type :</label>

                                                                        <div class="col-lg-4">
                                                                            <select name="beast"
                                                                                    id="hr9"
                                                                                    class="form-control custom-select text-left select-beast">
                                                                                <option value="1">
                                                                                    Select
                                                                                    Type
                                                                                </option>
                                                                                <option value="Probation">
                                                                                    Probation
                                                                                </option>
                                                                                <option value="Regular">
                                                                                    Regular
                                                                                </option>
                                                                                <option value="Contract">
                                                                                    Contract
                                                                                </option>
                                                                            </select>
                                                                        </div>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label class="col-lg-2">CV/UT/AT
                                                                            :</label>

                                                                        <div class="col-lg-4">
                                                                            <input type="file"
                                                                                   name=""
                                                                                   id="hr10"
                                                                                   value="attahcment">
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button data-dismiss="modal"
                                                                        class="btn btn-primary"
                                                                        onclick="getModalData('hrDtlsTable','hr',10)"
                                                                        type="button">Add
                                                                </button>
                                                                <button data-dismiss="modal"
                                                                        class="btn btn-warning"
                                                                        type="button">Close
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- HR modal close -->
                                            </div>

                                        </div>
                                    </div>
                                </div>
                                <!-- Box Close -->
                            </div>

                        </div>
                    </div>
                </form>
                <div class="col-lg-12 form-group">
                    <button type="button"
                            onclick="nextTab('feesStructure','humanResourceCriteria')"
                            class="btn btn-azure col-lg-offset-9">
                        <i class="fa fa-arrow-left"></i>
                        Back
                    </button>
                    <button type="button"
                            onclick="nextTab('humanResourceCriteria','consultantEquipmentDtls')"
                            class="btn btn-primary">
                        <i class="fa fa-arrow-right"></i>
                        Save & Next
                    </button>
                </div>
            </div>

            <div role="tabpanel" class="tab-pane equipment_details" id="equipment_details">
                <form action="" method="post" class="">
                    <div id="consultantEquipment" style="">
                        <i><strong>Consultant Equipment Details</strong></i>

                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered table-striped"
                                           id="eqdatatable">
                                        <thead>
                                        <tr>
                                            <th>Equipment Name</th>
                                            <th>Registration Number</th>
                                            <th>Quantity</th>
                                            <th>Attachment</th>
                                            <th>Action</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                    <button type="button" class="btn btn-info btn-sm"
                                            title="Payment" data-toggle="modal"
                                            data-target="#equipmentModal"><i
                                            class="fa fa-plus"></i> Add More HR
                                    </button>
                                    <!-- Equipment modal open -->
                                    <div aria-hidden="true" aria-labelledby="myModalLabel"
                                         role="dialog" tabindex="-1"
                                         class="modal fade in" id="equipmentModal">
                                        <div class="modal-dialog modal-lg">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h4 id="myModalLabel"
                                                        class="modal-title">Add
                                                        Equipment</h4>
                                                    <button aria-hidden="true"
                                                            data-dismiss="modal"
                                                            class="close" type="button">
                                                        <span></span></button>
                                                </div>
                                                <div class="modal-body form-horizontal">
                                                    <div class="">
                                                        <div class="form-group">
                                                            <label class="col-lg-2">Equipment
                                                                <span class="text-danger">*</span>:</label>

                                                            <div class="col-lg-4">
                                                                <select name="beast"
                                                                        id="eq1"
                                                                        class="form-control custom-select text-left select-beast">
                                                                    <option value="1">Select
                                                                        Equipment
                                                                    </option>
                                                                    <option value="Gas Cutting">
                                                                        Gas Cutting
                                                                    </option>
                                                                    <option value="Truck">
                                                                        Truck
                                                                    </option>
                                                                    <option value="Tractor">
                                                                        Tractor
                                                                    </option>
                                                                </select>
                                                            </div>
                                                            <label class="col-lg-3">Registration
                                                                No <span
                                                                        class="text-danger">*</span>:</label>

                                                            <div class="col-lg-3">
                                                                <div class="input-icon">
                                                                    <input type="text"
                                                                           class="form-control"
                                                                           id="eq2">
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">

                                                            <label class="col-lg-2">Quantity
                                                                <span class="text-danger">*</span>:</label>

                                                            <div class="col-lg-4">
                                                                <div class="input-icon">
                                                                    <input type="text"
                                                                           class="form-control"
                                                                           id="eq3">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-lg-4">Attach
                                                                Supporting Documents
                                                                :</label>

                                                            <div class="col-lg-4">
                                                                <input type="file" name=""
                                                                       id="eq4"
                                                                       value="attahcment">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button data-dismiss="modal"
                                                            class="btn btn-primary"
                                                            onclick="getModalData('eqdatatable','eq',4)"
                                                            type="button">Add
                                                    </button>
                                                    <button data-dismiss="modal"
                                                            class="btn btn-warning"
                                                            type="button">Close
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Equipment modal close -->
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
                <div class="col-lg-12 form-group">
                    <button type="button"
                            onclick="nextTab('feesStructure','humanResourceCriteria')"
                            class="btn btn-azure col-lg-offset-9">
                        <i class="fa fa-arrow-left"></i>
                        Back
                    </button>
                    <button type="button"
                            onclick="saveAndPreview('consultantEquipmentDtls','saveAndPreview')"
                            class="btn btn-primary">
                        <i class="fa fa-arrow-right"></i>
                        Save & Preview
                    </button>
                </div>
            </div>

            <div class="tab-pane saveAndPreview" id="saveAndPreview">

                <div>
                    <div id="submitSection" style="">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="form-froup">
                                    <div class="col-lg-12">
                                        <strong>Terms and Condition</strong>

                                        <div class="form-group">
                                            <div class="col-lg-12" id="">
                                                1. As provided in clause 2.1.1.2 and 2.3.1
                                                of Procurement Rules and Regulations 2009,
                                                the holder of this Certificate is qualified
                                                to participate in public procurement
                                                procedure.

                                                <br> 2. The issuance of CDB Registration
                                                Certificate will be based largely on the
                                                fulfillment of the minimum criteria set
                                                against classification of
                                                Contractor/Consultant and Categorization of
                                                Works and upon certification by competent
                                                authority for construction professionals.

                                                <br> 3. All the registered contractors
                                                should comply with 'Code of ethics for
                                                Contractors'.

                                                <br> 4. CDB will not be accountable for any
                                                false/fabricated submission that could have
                                                led to the fulfillment of the criteria and
                                                subsequent issue of CDB Registration
                                                Certificate.

                                                <br> 5. CDB Registration Certificate once
                                                issued would not relieve the certificate
                                                holder of any relaxation on the minimum
                                                requirements for registration.

                                                <br> 6. Notwithstanding the provisions of
                                                Companies Act of Bhutan, the certificate
                                                issued is non-transferable even if the
                                                promoters separate and establish similar
                                                companies.

                                                <br> 7. CDB Certificate cannot be leased or
                                                subleased to any individual or another firm.

                                                <br> 8. Certificate is valid during the
                                                period for which it was issued provided it
                                                has not been cancelled, suspended or revoked
                                                by CDB or any other competent authority.

                                                <br> 9. Failing to renew within the expiry
                                                date will lead to penalty of Nu.100 per day.

                                                <br> 10. Failing to pay the fees for
                                                approved online application within 30 days
                                                will lead to cancellation of the
                                                application.

                                                <br> 11. All registered construction firm
                                                must attend the mandatory refresher course
                                                in order to apply for renewal.

                                                <br> 12. No Contractors can submit bid,
                                                participate in bidding or be on the
                                                contention for award if the registration has
                                                expired.

                                                <br> 13. No Contractors can
                                                undertake/implement works which is not
                                                within the scope of the registration.

                                                <br> 14. CDB may verify the resources
                                                committed for the projects as and when
                                                desires.

                                                <br> 15. The registration is subject to
                                                verification whenever the CDB so desires.
                                                CDB will inspect the minimum mandatory
                                                requirement of manpower and equipment of
                                                Large and Medium contractors and the during
                                                the time of monitoring, every firm must
                                                extend necessary support and cooperation to
                                                CDB Officials.

                                                <br> 16. Large and Medium Contractors must
                                                have Office established with Signboard
                                                (requirements of office and signboard as
                                                determined by CDB)

                                                <br> 17. Registered firms are required to
                                                inform the CDB of any changes in their
                                                address, contact details or any pertinent
                                                particulars within one month.

                                                <br> 18. The CDB Registration Certificate
                                                can be revoked, downgraded, suspended or
                                                cancelled at any given time if the:
                                                <br> a. Holder undertakes unlawful
                                                participation in the procurement process;
                                                <br> b. Entity does not possess the minimum
                                                requirements during the physical
                                                verification process (at the discretion of
                                                CDB);
                                                <br> c. Entity has obtained the same due to
                                                false submissions;
                                                <br> d. Entity becomes bankrupt or winds up;
                                                or
                                                <br> e. Entity has been charged by the court
                                                for penal offence.
                                                <br> This Terms of Condition is hereby
                                                endorsed and enforced with immediate effect.

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
                                            <li>All information and attachments with this
                                                application are true and correct;
                                            </li>
                                            <li>I am/we are aware that any false information
                                                provided herein will result in rejection of
                                                my application and suspension of any
                                                registered granted;
                                            </li>
                                            <li>I/We shall not make refund claims of
                                                expenditure incurred in processing this
                                                application;
                                            </li>
                                            <li>I/We have read and understood the 'Code of
                                                Ethics' and shall perform in line with Code
                                                of Ethics and any other legislation in
                                                force. Failure to comply, will be subject to
                                                the penalities provided for in the
                                                applicable legislation of the country.
                                            </li>
                                            <li>I/We hereby declare that issue of CDB
                                                certificate does not in anyway constitute an
                                                obligation on the part of CDB or any other
                                                Goverment agency to provide contract works.
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
                                <input type="hidden" name="addedeqCount" id="addedeqCount">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-offset-5 text-center">
                    <button class="btn btn-primary col-lg-2 mr-2" type="button"
                            onclick="showAcknowledgement()">Submit
                    </button>
                    <button class="btn btn-danger col-lg-2" type="button">Cancel</button>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="<c:url value="/resources/js/cdb/contractor.js"/>"></script>
<script type="text/javascript">
    $('#fees_structure').prop('class', 'tab-pane active');
</script>

</body>
