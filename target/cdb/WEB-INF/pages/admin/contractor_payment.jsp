<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<body class="">
<div class="mt-5">
    <div class="container mb-9">
        <div class="row">
            <div class="col-12">
                <div class="page-header mb-0 mt-0 page-header">
                    <h1 class="page-title">
                        Construction Development Board
                    </h1>
                </div>

                <div class="card" id="registrtaionFormCard">
                    <div class="card-header">
                        <h3 class="card-title font-weight-bold">Registration of Contractor</h3>
                    </div>
                    <div class="card-body">
                        <div class="row">

                            <div class="tab-content border p-3 col-lg-12">
                                <form action="" method="post" id="contractorPaymentForm">
                                    <input type="hidden" id="appNoPayment"  value="${appNo}" name="appNo"/>

                                    <div class="">
                                        <div class="card tab2">
                                            <div class="bg-blue card-status card-status-left"></div>
                                            <div class="card-body">
                                                <div class="col-lg-12">
                                                    <div class="col-lg-6 col-md-6 form-group">
                                                        <label class="col-lg-4 form-label">Ownership Type <span
                                                                class="text-danger">*</span></label>

                                                        <label class="col-lg-8 form-label" id="ownershipType"></label>
                                                    </div>
                                                    <div class="col-lg-6 col-md-6 form-group">
                                                        <label class="col-lg-4 form-label">Country <span
                                                                class="text-danger">*</span></label>
                                                        <label class="col-lg-8 form-label" id="country"></label>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="col-lg-6 col-md-6 form-group">
                                                        <label class="col-lg-4 form-label">Trade License No</label>
                                                        <label class="col-lg-8 form-label" id="tradeLicenseNo"></label>
                                                    </div>
                                                    <div class="col-lg-6 col-md-6 form-group">
                                                        <label class="col-lg-4 form-label">Proposed Firm Name <span
                                                                class="text-danger">*</span></label>
                                                        <label class="col-lg-8 form-label" id="firmName"></label>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="col-lg-6 col-md-6 form-group">
                                                        <label class="col-lg-4 form-label">TPN Number</label>
                                                        <label class="col-lg-8 form-label" id="tpn"></label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="card tab2">
                                            <div class="bg-blue card-status card-status-left"></div>
                                            <div class="card-header">
                                                <h3 class="card-title">Category Details</h3>
                                            </div>
                                            <div class="card-body">


                                                <div class="col-lg-12">
                                                    <table id="contractorCCTbl"
                                                           class="table table-bordered table-hover">
                                                        <thead style="background-color: #F2F2F2">
                                                        <tr>
                                                            <th></th>
                                                            <th>Category</th>
                                                            <th>Apply for Class</th>
                                                            <th>Fee</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>

                                                        </tbody>
                                                        <tfoot>

                                                        </tfoot>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="card tab2">
                                            <div class="bg-blue card-status card-status-left"></div>
                                            <div class="card-header">
                                                <h3 class="card-title">Fees Applied</h3>
                                            </div>
                                            <div class="card-body">

                                                <div class="col-lg-12">
                                                    <div class="panel-body">
                                                        <div class="form-group">
                                                            <label class="col-lg-2 control-label">
                                                                CDB Number :<font color="red">*</font>
                                                            </label>

                                                            <div class="col-lg-4" id="CDBNumberclass">
                                                                <input type="text" name="cdbNo"
                                                                        id="cdbNo" class="form-control"
                                                                        readonly="readonly">
                                                            </div>
                                                            <label class="col-lg-2 control-label">
                                                                Payment Date :<font color="red">*</font>
                                                            </label>

                                                            <div class="col-lg-4" id="dateclass">

                                                                <div class="input-group margin-bottom-sm">
                                                                                <span class="input-group-addon pr-5"><i
                                                                                        class="fa fa-calendar"></i></span>
                                                                    <input type="date" name="paymentDate"
                                                                           value="" id="paymentDate"
                                                                           class="form-control datepicker">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-lg-2 control-label">
                                                                Receipt No :<font color="red">*</font>
                                                            </label>

                                                            <div class="col-lg-4 ">
                                                                <input type="text" name="paymentReceiptNo"
                                                                        id="paymentReceiptNo"
                                                                       class="form-control">
                                                            </div>
                                                            <label class="col-lg-2 control-label">
                                                                Remarks :
                                                            </label>

                                                            <div class="col-lg-4">
                                                                <input type="text" name="paymentRemarks"
                                                                       value="" id="paymentRemarks"
                                                                       class="form-control">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-lg-2 control-label">
                                                                Amount Paid:
                                                            </label>

                                                            <div class="col-lg-4" >
                                                                <input type="text" name="paymentAmount"
                                                                       value="" readonly="readonly"
                                                                       id="paymentAmount" class="form-control">
                                                            </div>
                                                            <label class="col-lg-2 control-label">
                                                                Mode of Payment:
                                                            </label>

                                                            <div class="col-lg-4">
                                                                <select name="modeOfPayment"
                                                                        id="contractorType"
                                                                        class="form-control chzn-select">
                                                                    <option value="select">-Select-</option>

                                                                    <option value="CDb-Cash">CDB-Cash
                                                                    </option>

                                                                    <option value="RRCO-Thimphu">
                                                                        RRCO-Thimphu
                                                                    </option>

                                                                    <option value="RRCO- Phuntsholing">RRCO-
                                                                        Phuntsholing
                                                                    </option>

                                                                    <option value="RRCO-Gelephu">
                                                                        RRCO-Gelephu
                                                                    </option>

                                                                    <option value="RRCO-Mongar">
                                                                        RRCO-Mongar
                                                                    </option>

                                                                    <option value="RRCO-Wangdiphodrang">
                                                                        RRCO-Wangdiphodrang
                                                                    </option>

                                                                    <option value="RRCO-Samdrupjongkhar">
                                                                        RRCO-Samdrupjongkhar
                                                                    </option>
                                                                </select>
                                                                <span class="help-block" id=""></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Box Close -->
                                    </div>

                                    <div class="col-lg-12 form-group">
                                        <button type="submit" class="btn btn-primary" id="btnSave">
                                            <i class="fa fa-file-text mr-1"></i>Save and Generate Certificate</button>
                                        <a href="/admin/contractor" class="btn btn-orange">
                                            <i class="fa fa-ban"></i>
                                            Cancel
                                        </a>
                                    </div>

                                </form>

                            </div>

                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <script src="<c:url value="/resources/js/cdb/contractor_action.js"/>"></script>
</div>

</body>
</html>