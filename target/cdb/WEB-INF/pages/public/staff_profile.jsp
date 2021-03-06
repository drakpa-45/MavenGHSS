<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 4/17/2020
  Time: 11:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<% String nextbtn="true";%>
<body>
    <div class="card">
        <div class="card-body">
            <c:if test = "${fn:contains('Deregistered,Blacklisted,Revoked,Suspended', registrationDetails.updateStatus)}">
                <div class="form-group">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 alert alert-danger text-center">
                        You are not allow to avail this service as your certificate is <b> ${registrationDetails.updateStatus}</b>.
                        <% nextbtn="false2";%>
                    </div>
                </div>
            </c:if>
            <% if(nextbtn=="true"){%>
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                    <strong>
                        Registration Information
                    </strong>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 ">
                    <button class="btn btn-sm btn-primary pull-right" type="button" onclick="printInfo('${App_Details.cdbNo}')"><i class="fa fa-print"></i> Print Information</button> &nbsp;&nbsp;&nbsp;
                    <button class="btn btn-sm btn-success pull-right" type="button" onclick="printCertificate('${App_Details.cdbNo}')"><i class="fa fa-edit"></i> Print Certificate</button>&nbsp;&nbsp;&nbsp;
                </div>
            </div>
            <hr />
            <div class="row form-froup">
                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
                    <img src='https://www.citizenservices.gov.bt/BtImgWS/ImageServlet?type=PH&cidNo=${App_Details.cidNo}'  width='200px'  height='200px' class='pull-right'/>
                </div>
                <div class="col-lg-10 col-md-10 col-sm-10 col-xs-12">
                    <div class="table-responsive">
                        <table class="table">
                            <tbody>
                                <tr>
                                    <td colspan="2" class="font-blue-madison bold warning">Personal Information</td>
                                </tr>
                                <tr>
                                    <td>
                                        <table class="table table-condensed">
                                            <tr>
                                                <td><strong>Full Name: </strong></td>
                                                <td>${App_Details.fullname}</td>
                                            </tr>
                                            <tr>
                                                <td><strong>CID: </strong></td>
                                                <td>${App_Details.cidNo}</td>
                                            </tr>
                                            <tr>
                                                <td><strong>Contact Number: </strong></td>
                                                <td>${App_Details.mobileNo}</td>
                                            </tr>
                                            <tr>
                                                <td><strong>Email Address: </strong></td>
                                                <td>${App_Details.email}</td>
                                            </tr>
                                        </table>
                                    </td>
                                    <td>
                                        <table class="table table-condensed">
                                            <tr>
                                                <td><strong>Village: </strong></td>
                                                <td>${App_Details.village}</td>
                                            </tr>
                                            <tr>
                                                <td><strong>Gewog: </strong></td>
                                                <td>${App_Details.gewog}</td>
                                            </tr>
                                            <tr>
                                                <td><strong>Dzongkhag: </strong></td>
                                                <td>${App_Details.dzongkhagId}</td>
                                            </tr>
                                            <tr>
                                                <td><strong>Country: </strong></td>
                                                <td>${App_Details.countryId}</td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <hr />
                        <div class="table-responsive">
                            <table class="table">
                                <tbody>
                                <tr>
                                    <td colspan="2" class="font-blue-madison bold warning">Qualification Information</td>
                                </tr>
                                <tr>
                                    <td>
                                        <table class="table table-condensed">
                                            <tr>
                                                <td><strong>Qualification: </strong></td>
                                                <td>${App_Details.qualificationId}</td>
                                            </tr>
                                            <tr>
                                                <td><strong>Year of Graduation: </strong></td>
                                                <td>${fn:substring(App_Details.graduationyr, 0, 4)}</td>
                                            </tr>
                                        </table>
                                    </td>
                                    <td>
                                        <table class="table table-condensed">
                                            <tr>
                                                <td><strong>Country of Study: </strong></td>
                                                <td>${App_Details.universityCountry}</td>
                                            </tr>
                                            <tr>
                                                <td><strong>University Name: </strong></td>
                                                <td>${App_Details.universityName}</td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <% }%>
        </div>
    </div>
</body>
