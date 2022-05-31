<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2/21/2020
  Time: 1:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<body>
<div class="row">
    <div class="col-12">
        <div class="card">
            <hr />
            <div id="filterTasklistId">
                <div class="row">
                    <div class="col-12">
                        <div class="col-lg-2">
                        </div>
                        <div class="col-lg-8">
                            <div class="row">
                                        <div class="card tab2">
                                            <div class="bg-yellow card-status card-status-left"></div>
                                            <div class="card-header text-white" style="background-color:maroon">
                                                <h3 class="card-title">Personal Details</h3>
                                            </div>
                                            <div class="card-body">
                                                <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                        <label>Citizen Identity Number: <span class="text-danger">*</span></label>
                                                        <input id="cid" type="email" class="form-control"onclick="remove_err('email_err')" name="email">
                                                    </div>
                                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                        <label>Name: <span class="text-danger">*</span></label>
                                                        <input type="text" class=" form-control number" onclick="remove_err('mobileNo_err')" id="mobileNo" name="mobileNo"  maxlength="8">
                                                    </div>
                                                </div>
                                                <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                        <label>Age:<span class="text-danger">*</span></label>
                                                        <input id="age" type="email" class="form-control"onclick="remove_err('email_err')" name="email">
                                                    </div>
                                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                        <label>Date of Birth: <span class="text-danger">*</span></label>
                                                        <input type="text" class=" form-control number" onclick="remove_err('mobileNo_err')" id="mobileNo" name="mobileNo"  maxlength="8">
                                                    </div>
                                                </div>
                                                <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                        <label>Email: <span class="text-danger">*</span></label>
                                                        <input id="email" type="email" class="form-control"onclick="remove_err('email_err')" name="email">
                                                        <span id="email_err" class="text-danger"></span>
                                                    </div>
                                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                        <label>Mobile No: <span class="text-danger">*</span></label>
                                                        <input type="text" class=" form-control number" onclick="remove_err('mobileNo_err')" id="mobileNo" name="mobileNo"  maxlength="8">
                                                        <span id="mobileNo_err" class="text-danger"></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                <div class="card tab2">
                                    <div class="bg-yellow card-status card-status-left"></div>
                                    <div class="card-header text-white" style="background-color:maroon">
                                        <h3 class="card-title">Previous School Details</h3>
                                    </div>
                                    <div class="card-body">
                                        <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                <label>School Name: <span class="text-danger">*</span></label>
                                                <input id="cid" type="email" class="form-control"onclick="remove_err('email_err')" name="email">
                                            </div>
                                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                <label>Grade: <span class="text-danger">*</span></label>
                                                <input type="text" class=" form-control number" onclick="remove_err('mobileNo_err')" id="mobileNo" name="mobileNo"  maxlength="8">
                                            </div>
                                        </div>
                                        <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                <label>Dzongkhag:<span class="text-danger">*</span></label>
                                                <input id="age" type="email" class="form-control"onclick="remove_err('email_err')" name="email">
                                            </div>
                                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                <label>Village<span class="text-danger">*</span></label>
                                                <input type="text" class=" form-control number" onclick="remove_err('mobileNo_err')" id="mobileNo" name="mobileNo"  maxlength="8">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="card tab2">
                                    <div class="bg-yellow card-status card-status-left"></div>
                                    <div class="card-header text-white" style="background-color:maroon">
                                        <h3 class="card-title">Permanent Address</h3>
                                    </div>
                                    <div class="card-body">
                                        <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                <label>Dzongkhag: <span class="text-danger">*</span></label>
                                                <input id="cid" type="email" class="form-control"onclick="remove_err('email_err')" name="email">
                                            </div>
                                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                <label>Gewog: <span class="text-danger">*</span></label>
                                                <input type="text" class=" form-control number" onclick="remove_err('mobileNo_err')" id="mobileNo" name="mobileNo"  maxlength="8">
                                            </div>
                                        </div>
                                        <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                <label>Village:<span class="text-danger">*</span></label>
                                                <input id="age" type="email" class="form-control"onclick="remove_err('email_err')" name="email">
                                            </div>
                                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                <label>Village<span class="text-danger">*</span></label>
                                                <input type="text" class=" form-control number" onclick="remove_err('mobileNo_err')" id="mobileNo" name="mobileNo"  maxlength="8">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="card tab2">
                                    <div class="bg-yellow card-status card-status-left"></div>
                                    <div class="card-header text-white" style="background-color:maroon">
                                        <h3 class="card-title">Guidance Address</h3>
                                    </div>
                                    <div class="card-body">
                                        <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                <label>Name: <span class="text-danger">*</span></label>
                                                <input id="cid" type="email" class="form-control"onclick="remove_err('email_err')" name="email">
                                            </div>
                                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                <label>Phone Number: <span class="text-danger">*</span></label>
                                                <input type="text" class=" form-control number" onclick="remove_err('mobileNo_err')" id="mobileNo" name="mobileNo"  maxlength="8">
                                            </div>
                                        </div>
                                        <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                <label>Email Address:<span class="text-danger">*</span></label>
                                                <input id="age" type="email" class="form-control"onclick="remove_err('email_err')" name="email">
                                            </div>
                                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 form-group">
                                                <label>Relationship<span class="text-danger">*</span></label>
                                                <input type="text" class=" form-control number" onclick="remove_err('mobileNo_err')" id="mobileNo" name="mobileNo"  maxlength="8">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="text-center">
                                        <button class="btn bg-warning text-blue"
                                                value="personal" type="button">
                                            <b>Download</b>&nbsp;<i class="fa fa-download"></i></button>
                                        </div>
                                </div>
                            </div>
                            </div>
                            <div class="col-lg-2">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="content_main_div"></div>
        </div>
        </div>
    </div>
        <script>

        </script>
</body>
