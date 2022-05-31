<%@ page import="com.gov.edu.gelephuHSS.GHSS.schoolPage.dto.StaffProfileDTO" %>
<%@ page import="java.util.List" %>
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

<% List<StaffProfileDTO> dtls=(List<StaffProfileDTO>) request.getAttribute("App_Details"); %>

<body>
    <div class="card">
        <div class="card-body">
            <div class="card-header rounded" style="background-color:maroon">
                <% if(dtls.get(0).getServiceTypeId().equalsIgnoreCase("Science Department")) {%>
                <h3 class="card-title text-white">Science Department</h3>
                <%}%>
                <% if(dtls.get(0).getServiceTypeId().equalsIgnoreCase("Management")) {%>
                <h3 class="card-title text-white">Management Department</h3>
                <%}%>
                <% if(dtls.get(0).getServiceTypeId().equalsIgnoreCase("English Department")){%>
                <h3 class="card-title text-white">English Department</h3>
                <%}%>
            </div>
            <br/>
            <%for(int i=0;i<dtls.size();i++){%>
            <div class="row form-froup">
               <%-- <div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
                    <img src='https://www.citizenservices.gov.bt/BtImgWS/ImageServlet?type=PH&cidNo=${dtls.cidNo}'  width='200px'  height='200px' class='pull-right'/>
                </div>--%>
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
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
                                                <td><%=dtls.get(i).getFullname()%></td>
                                            </tr>
                                            <tr>
                                                <td><strong>Email: </strong></td>
                                                <td><%=dtls.get(i).getEmail()%></td>
                                            </tr>
                                            <tr>
                                                <td><strong>Contact Number: </strong></td>
                                                <td><%=dtls.get(i).getMobileNo()%></td>
                                            </tr>
                                        </table>
                                    </td>
                                    <td>
                                        <table class="table table-condensed">
                                            <tr>
                                                <td><strong>Qualification: </strong></td>
                                                <td><%=dtls.get(i).getQualificationId()%></td>
                                            </tr>
                                            <tr>
                                                <td><strong>universityName: </strong></td>
                                                <td><%=dtls.get(i).getUniversityName()%></td>
                                            </tr>
                                            <tr>
                                                <td><strong>Department Name: </strong></td>
                                                <td><%=dtls.get(i).getServiceTypeId()%></td>
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
