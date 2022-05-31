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
<form id="contractorForm" name="contractorForm" action="<c:url value="contractor/save"/>" method="POST" class="globalForm"
       enctype="multipart/form-data">
    <div class="card" id="registrtaionFormCard">
        <div class="card-header">
            <h3 class="card-title font-weight-bold">Registration of Contractor</h3>
        </div>
        <div class="card-body">
            <div class="col-md-12 col-lg-12">
                <div class="nav-tabs-custom">
                    <ul class="m-0 nav nav-tabs nav-justified text-white">
                        <%--<ul class="p-3 col-lg-12 nav nav-tabs nav-justified text-white" role="tablist">--%>
                        <li class="nav-item active text-primary fees_structure">

                            <a class="nav-link" href="#fees_structure" role="tab" data-toggle="tab"
                               data-placement="top">
                                <i class="fa fa-bookmark mr-1"></i>Fee Structure</a>
                        </li>
                        <li class="nav-item general_Information">
                            <a class="nav-link border text-primary" href="#general_Information" role="tab"
                               data-toggle="tab" data-placement="top">
                                <i class="fa fa-exclamation-circle mr-1"></i>General Information</a>
                        </li>
                        <li class="nav-item  category_details">
                            <a class="nav-link border text-primary" href="#category_details" role="tab"
                               data-toggle="tab"
                               aria-selected="true" data-placement="top">
                                <i class="fa fa-sitemap mr-1"></i>Category Details</a>
                        </li>
                        <li class="nav-item human_resource_criteria">
                            <a class="nav-link border text-primary" href="#human_resource_criteria" role="tab"
                               data-toggle="tab" aria-selected="true" data-placement="top">
                                <i class="fa fa-users mr-1"></i>Human Resource Criteria</a>
                        </li>
                        <li class="nav-item equipment_details">
                            <a class="nav-link border text-primary" href="#equipment_details" role="tab"
                               data-toggle="tab"
                               aria-selected="true" data-placement="top">
                                <i class="fa fa-truck mr-1"></i>Contractor Equipment Details</a>
                        </li>
                        <li class="nav-item saveAndPreview">
                            <a class="nav-link border text-primary" href="#saveAndPreview" role="tab" data-toggle="tab"
                               aria-selected="true" data-placement="top">
                                <i class="fa fa-file mr-1"></i>Preview</a>
                        </li>
                    </ul>
                    <div class="tab-content border p-3 col-lg-12">
                        <div role="tabpanel" class="tab-pane active" id="fees_structure">
                            <h3 class="pt-3 text-center hide">Fee Structure</h3>
                            <jsp:include page="fee.jsp"/>
                        </div>

                        <div role="tabpanel" class="tab-pane general_Information" id="general_Information">
                            <h3 class="pt-3 text-center hide">General Information</h3>
                            <jsp:include page="gInfo.jsp"/>
                        </div>

                        <div role="tabpanel" class="tab-pane category_details" id="category_details">
                            <h3 class="pt-3 text-center hide">Category Details</h3>
                            <jsp:include page="category.jsp"/>
                        </div>

                        <div role="tabpanel" class="tab-pane human_resource_criteria" id="human_resource_criteria">
                            <h3 class="pt-3 text-center hide">Human Resource Criteria</h3>
                            <jsp:include page="hr.jsp"/>
                        </div>

                        <div role="tabpanel" class="tab-pane equipment_details" id="equipment_details">
                            <h3 class="pt-3 text-center hide">Contractor Equipment Details</h3>
                            <jsp:include page="equipment.jsp"/>

                        </div>

                        <div class="tab-pane saveAndPreview" id="saveAndPreview">
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
                                                                                 name="tnc"
                                                                                 required="true"
                                                                                 style="width:15px;height:15px;"></span>
                                                <span class="bold"> I agree to the above Terms Conditions</span>
                                            </label>
                                            <!-- <br /><span class="text-danger" id="termAndConErrorClass"></span> -->
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-lg-offset-5 text-center">
                                <input type="submit" class="btn btn-primary col-lg-2 mr-2" value="Submit"
                                       id="btnSubmit"/>

                                <input type="reset" class="btn btn-danger col-lg-2" value="Cancel" id="btnReset"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>

<script src="<c:url value="/resources/js/cdb/contractor.js"/>"></script>


</body>
