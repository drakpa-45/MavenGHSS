<%--
Created by IntelliJ IDEA.
User: Pema Drakpa
Date: 3/13/2020
Time: 11:40 PM
To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%
    String cidNo = (String) request.getAttribute("cidNo");
%>
<div id="loadResponseContent">
    <div class="row form-group" style="margin-left:-25px; margin-right:-25px;">
        <div class="col-md-12 col-sm-12 col-lg-12 col-xs-12">
            <div class="card tab4">
                <div class="card-header text-white"style="background-color:maroon">
                    <h4 class="mt-3">View Result&nbsp;</h4><%-->>&nbsp;Please enter CID number to get --%>
                </div>
                <div class="card-body" style="margin-top:-10px;">
                    <div class="card">
                        <div class="card-body">
                            <form action="/ruralTimber/cidDetail" id="application"
                                  class="form-horizontal" id="CID_Number">
                                <div class="form-group" style="font-size:14px">
                                    <label class="col-lg-1 control-label"></label>
                                    <label class="col-lg-3 control-label mt-3"><h5>Enrollment Number:
                                        <a href="#" data-toggle="tooltip" data-placement="top"
                                           title='Enter your student enrollment number to view result.'
                                           class="tooltipCSSSelector">
                                            <img src="<c:url value="/resources/img/questionMark.png"/>"
                                                 class="user-image" width="20px">
                                        </a><span class="text-danger">*</span></h5></label>

                                    <div class="col-lg-8">
                                        <label class="col-lg-4 control-label">
                                            <input type="text" name='studentNo' placeholder="<%=cidNo%>" class="form-control"  id="cid"/>
                                        </label>
                                        <label class="col-lg-2 control-label">
                                            <button class="btn btn-outline-primary btn-sm-2" id="btn_submit" type="submit"><i class="fa fa-eye"></i>&nbsp;View
                                            </button>
                                        </label>
                                    </div>
                                </div>

                                <%--<div class="form-group" id="contract_section">
                                    <%if(!cidNo.equals("")){%>
                                    <table class="table table-striped table-bordered table-hover table-responsive-lg text-nowrap" id="report_Data">
                                        <thead class="thead-dark">
                                        <tr>
                                            <th>subjects</th>
                                            <th>Name</th>
                                            <th>Construction Type</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="dis" items="${cidDetails}" varStatus="counter">
                                            <tr>
                                                <td>
                                                    <a href="#" id="appDetails" onclick="applicationStatus('${dis.application_Number}')"> ${dis.application_Number}
                                                    </a></td>
                                                <td>${dis.name}</td>
                                                <td>${dis.cons_Type}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    <%}%>
                                </div>--%>
                                <%--<div class="form-group">
                                    <div class="table-responsive text-nowrap text-center">
                                        <button type="button" class="btn btn-outline-primary" id="printReportId" name="print" onclick="getReciept('<%=dtls.get(1).getApplication_Number()%>')">Generate Receipt</button>
                                    </div>
                                </div>--%>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script language="javascript" type="text/javascript">
    function getReciept(val) {
        var url = '${pageContext.request.contextPath}/ruralTimber/loadpagetoemptylayout/printDetails?application_Number=' + val;
        var options = {
            target: '#loadResponseContent',
            url: url,
            enctype: 'form-data',
            data: $('#application').serialize()
        };
        $("#application").ajaxSubmit(options);
    }

    function applicationStatus(appNo) {
        alert(appNo);
        var url = '${pageContext.request.contextPath}/ruralTimber/loadpagetoemptylayout/statusDetail?appNo=' + appNo;
        var options = {
            target: '#contract_section',
            url: url,
            type: 'GET',
            enctype: 'form-data',
            data: $('#application').serialize()
        };
        $("#application").ajaxSubmit(options);
    }

    $('[data-toggle="tooltip"]').tooltip();
    $(document).ready(function () {
        $('#reportOverAllUsageGloMed').DataTable({
            responsive: true
        });
    });
    function doExportItem(selector, params) {
        var options = {
            //ignoreRow: [1,11,12,-2],
            //ignoreColumn: [0,-1],
            //pdfmake: {enabled: true},
            tableName: 'reportOverAllUsageGloMed',
            worksheetName: 'report',
            fileName: 'report'
        };
        $.extend(true, options, params);
        $(selector).tableExport(options);
    }
</script>
</body>
</html>

