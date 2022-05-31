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
            <div class="card">
                <div class="card-header text-white" style="background-color:maroon">
                    <h4 class="mt-3">Downloads&nbsp;</h4><%-->>&nbsp;Please enter CID number to get --%>
                </div>
                <div class="card-body" style="margin-top:-10px;">
                    <div class="card">
                        <div class="card-body">
                            <form action="#" id="application"
                                  class="form-horizontal" id="CID_Number">
                                <div class="form-group" style="font-size:14px">
                                <label class="col-lg-3 control-label mt-3"><h5>Grade:
                                    <a href="#" data-toggle="tooltip" data-placement="top"
                                       title='Select your grade to get past question paper.'
                                       class="tooltipCSSSelector">
                                        <img src="<c:url value="/resources/img/questionMark.png"/>"
                                             class="user-image" width="20px">
                                    </a><span class="text-danger">*</span></h5></label>

                                <label class="col-lg-6 control-label">
                                    <select class="form-control" onclick="remove_err('paymentmode_err')"
                                            name="paymentmode" id="paymentmode">
                                        <option value="one">Select</option>
                                        <option value="one">XII</option>
                                        <option value="one Thimphu">XI</option>
                                        <option value="one Phuntsholing">X</option>
                                        <option value="one Gelephu">IX</option>
                                        <option value="one Mongar">VIII</option>
                                    </select>
                                </label>
                                <label class="col-lg-2 control-label">
                                </label>
                            </div>
                                <div class="form-group" style="font-size:14px">
                                    <label class="col-lg-3 control-label mt-3"><h5>Section:
                                        <a href="#" data-toggle="tooltip" data-placement="top"
                                           title='Select your Section to get past question paper.'
                                           class="tooltipCSSSelector">
                                            <img src="<c:url value="/resources/img/questionMark.png"/>"
                                                 class="user-image" width="20px">
                                        </a><span class="text-danger">*</span></h5></label>

                                    <label class="col-lg-6 control-label">
                                        <select class="form-control" onclick="remove_err('paymentmode_err')"
                                                name="paymentmode" id="paymentmode">
                                            <option value="one">Select</option>
                                            <option value="one">A</option>
                                            <option value="one Thimphu">B</option>
                                            <option value="one Phuntsholing">C</option>
                                            <option value="one Gelephu">D</option>
                                            <option value="one Mongar">E</option>
                                        </select>
                                    </label>
                                    <label class="col-lg-2 control-label">
                                        <button class="btn btn-outline-primary btn-sm-2" type="submit"
                                                onclick="show('one')"><i class="fa fa-eye"></i>&nbsp;Submit
                                        </button>
                                    </label>
                                </div>
                                <table id="attachment" class="table table-bordered table-hover table-responsive"
                                       style="display: none">
                                    <thead>
                                    <tr>
                                        <th>Sl No#</th>
                                        <th>Module Name</th>
                                        <th colspan="2">Action</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <%--<c:forEach var="att" items="${appDetails.doc}" varStatus="counter">--%>
                                    <%--<option value="${dzo.header_id}_${dzo.department}_${dzo.type}">${dzo.header_name}</option>--%>
                                    <tr>
                                        <td>1<%--${counter.index+1}--%></td>
                                        <td>Document name</td>
                                        <td>
                                            <button class="btn btn-primary" type="button"
                                                    onclick="viewAttachment('view')" target="_blank"><i
                                                    class="fa fa-eye"></i> View
                                            </button>
                                        </td>
                                        <td>
                                            <button class="btn btn-primary" type="button"
                                                    onclick="viewAttachment('download')"><i class="fa fa-download"></i>
                                                Download
                                            </button>
                                        </td>
                                    </tr>
                                    <%--</c:forEach>--%>
                                    </tbody>
                                </table>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script language="javascript" type="text/javascript">
    function show(val) {
        //alert(val);
        // $('#documentId').show();
        if (val == 'one') {
            document.getElementById('attachment').style.display = "block";
        } else {
            document.getElementById('attachment').style.display = "none";
        }
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

