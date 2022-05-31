/**
 * Created by user on 2/14/2020.
 */

var contractor_action = (function () {
    "use strict";
    var isSubmitted = false;

    function _baseURL() {
        return cdbGlobal.baseURL() + "/admin/contractor/action";
    }

    function getContractorInfo() {
        var applicationNo = $('#appNoVA').val();
        if (applicationNo) {
            $.ajax({
                url: _baseURL() + '/getContractorInfo',
                type: 'GET',
                data: {appNo: applicationNo,flag:'V'},
                success: function (res) {
                    if (res.status == '1') {
                        var contractorDTO = res.dto;
                        var contractor = contractorDTO.contractor;
                        $('#ownershipType').text(contractorDTO.ownershipTypeTxt);
                        $('#country').text(contractor.countryTxt);
                        $('#tradeLicenseNo').text(contractor.tradeLicenseNo);
                        $('#firmName').text(contractor.firmName);
                        $('#tpn').text(contractor.tpn);
                        $('#pDzongkhag').text(contractorDTO.pDzongkhagTxt);
                        $('#pGewog').text(contractorDTO.pGewogTxt);
                        $('#pVillage').text(contractorDTO.pVillageTxt);
                        $('#estAddress').text(contractor.estAddress);
                        $('#estDzongkhag').text(contractorDTO.estDzongkhagTxt);
                        $('#regEmail').text(contractor.regEmail);
                        $('#regMobileNo').text(contractor.regMobileNo);
                        $('#regPhoneNo').text(contractor.regPhoneNo);
                        $('#regFaxNo').text(contractor.regFaxNo);

                        var contractorHrs = contractorDTO.contractorHRs;
                        var partnerHrTr = "";
                        var hrTr = "";
                        var m = 0, n = 0;
                        var openModal = "openModal('CheckModal')";
                        for (var i in contractorHrs) {
                            if (contractorHrs[i].isPartnerOrOwner == '1') {
                                m++;
                                partnerHrTr = partnerHrTr + "<tr><td>" + m + "</td>" +
                                "<td>" + contractorHrs[i].countryName + "</td>" +
                                "<td>" + contractorHrs[i].cidNo + "</td>" +
                                "<td>" + contractorHrs[i].salutationName + "</td>" +
                                "<td>" + contractorHrs[i].name + "</td>" +
                                "<td>" + contractorHrs[i].sex + "</td>" +
                                "<td>" + contractorHrs[i].designationName + "</td>" +
                                "<td>" + contractorHrs[i].siCertificate + "</td>" +
                                "<td><input type='button' name='humanResource' value='Check for this CID' id='checkCid' class='btn btn-success' onclick="+openModal+"></td>" +
                                "<td><input type='checkbox' style='zoom:1.6' name='verifyowner0' value='1' id='checkver1'></td></tr>";

                            } else {
                                n++;
                                var href = _baseURL() + "/viewDownload?tableName=crpcontractorhumanresourceattachment&filterCol=CrpContractorHumanResourceId&filterVal="+contractorHrs[i].id;
                                hrTr = hrTr + "<tr>" +
                                "<td>" + n + "<input type='hidden' class='contractorHRid' value='"+contractorHrs[i].id +"' </td>" +
                                "<td>" + contractorHrs[i].countryName + "</td>" +
                                "<td>" + contractorHrs[i].cidNo + "</td>" +
                                "<td>" + contractorHrs[i].salutationName + "</td>" +
                                "<td>" + contractorHrs[i].name + "</td>" +
                                "<td>" + contractorHrs[i].sex + "</td>" +
                                "<td>" + contractorHrs[i].designationName + "</td>" +
                                "<td>" + contractorHrs[i].qualificationName + "</td>" +
                                "<td>" + contractorHrs[i].tradeName + "</td>" +
                                "<td>" + contractorHrs[i].joiningDate + "</td>" +
                                "<td>" + contractorHrs[i].serviceTypeName + "</td>" +
                                //"<td><a href='javascript:void(0);' class='vAttachment'>View/Download</a> </td>" +
                                "<td><a href="+href+" target='_blank'>View/Download</a> </td>" +
                                "<td><input type='button'  value='Check for this CID' id='checkCid' class='btn btn-success' onclick="+openModal+"></td>" +
                                "<td><input type='checkbox' style='zoom:1.6' name='verifyowner0' value='1' id='checkver1'></td></tr>";
                            }
                        }
                        $('#partnerDtls').find('tbody').html(partnerHrTr);
                        $('#hrTbl').find('tbody').html(hrTr);

                        var categoryClassDTOs = contractorDTO.categories;
                        var ccTr = "";
                        for (var i in categoryClassDTOs) {
                            ccTr = ccTr + "<tr><td><input class='form-control' type='checkbox' checked='checked' disabled style='width: 17px; height: 17px;'></td>" +
                            "<td>" + categoryClassDTOs[i].categoryName + "</td>" +
                            "<td><select disabled class='form-control'><option>" + categoryClassDTOs[i].aClassName + "</option></select></td></tr>";
                        }
                        $('#contractorCCTbl').find('tbody').html(ccTr);

                        var equipments = contractorDTO.equipments;
                        var eqTr = "";
                        for (var i in equipments) {
                            var href = _baseURL() + "/viewDownload?tableName=crpcontractorequipmentattachment&filterCol=CrpContractorEquipmentId&filterVal="+equipments[i].id;
                            eqTr = eqTr +
                            "<tr><td>" + (parseInt(i) + 1) + "</td>" +
                            "<td>" + equipments[i].equipmentName + "</td>" +
                            "<td></td>" +
                            "<td>" + equipments[i].registrationNo + "</td>" +
                            "<td></td>" +
                            "<td>" + equipments[i].quantity + "</td>" +
                            "<td><a href="+href+" target='_blank'>View/Download</a> </td>" +
                            "<td><input type='button' name='humanResource' value='Check for this CID' id='checkCid' class='btn btn-success'></td>" +
                            "<td><input type='checkbox' style='zoom:1.6' name='verifyowner0' value='1' id='checkver1'></td></tr>";

                        }
                        $('#equipmentTbl').find('tbody').html(eqTr);

                        var appHistoryDTOs = contractorDTO.appHistoryDTOs;

                        var appHistoryTr = "";
                        for (var i in appHistoryDTOs) {
                            appHistoryTr = appHistoryTr +
                            "<tr><td>" + appHistoryDTOs[i].appStatus + "</td>" +
                            "<td>" + appHistoryDTOs[i].userName + "</td>" +
                            "<td>" + formatAsDate(appHistoryDTOs[i].actionDate) + "</td>" +
                            "<td>"+ appHistoryDTOs[i].remarks +"</td></tr>";

                        }
                        $('#appStatusTbl').find('tbody').html(appHistoryTr);

                    } else {
                        warningMsg(res.text);
                    }
                }
            });
        }
    }

    function viewDownloadAttachment(){
        $('body').on('click','.vAttachment',function(){
            var id = $(this).closest('tr').find('.contractorHRid').val();
            $.ajax({
                url: _baseURL() + '/viewDownload',
                type: 'GET',
                data: {tableName:'crpcontractorhumanresourceattachment',filterCol:'CrpContractorHumanResourceId',filterVal:id}

            });
        });
    }


    function verify() {
        $('#btnVerify').on('click', function (e) {
            $.ajax({
                url: _baseURL() + '/verify',
                type: 'POST',
                data: {appNo:$('#appNoVA').val(),vRemarks:$('#vRemarks').val()},
                success: function (res) {
                    if (res.status == '1') {
                        successMsg(res.text, _baseURL());
                    } else {
                        warningMsg(res.text);
                    }
                }
            });

        })
    }

    function approve() {
        $('#btnApprove').on('click', function (e) {
            $.ajax({
                url: _baseURL() + '/approve',
                type: 'POST',
                data: {appNo:$('#appNoVA').val(),remarks:$('#vRemarks').val()},
                success: function (res) {
                    if (res.status == '1') {
                        successMsg(res.text, _baseURL());
                    } else {
                        warningMsg(res.text);
                    }
                }
            });

        })
    }

    function reject() {
        $('#btnReject').on('click', function (e) {
            $.ajax({
                url: _baseURL() + '/reject',
                type: 'POST',
                data: {appNo:$('#appNoVA').val(),remarks:$('#vRemarks').val()},
                success: function (res) {
                    if (res.status == '1') {
                        successMsg(res.text, _baseURL());
                    } else {
                        warningMsg(res.text);
                    }
                }
            });

        })
    }


    function getContractorInfoForPayment() {
        var applicationNo = $('#appNoPayment').val();
        if (applicationNo) {
            $.ajax({
                url: _baseURL() + '/getContractorInfo',
                type: 'GET',
                data: {appNo: applicationNo,flag:'P'},
                success: function (res) {
                    if (res.status == '1') {
                        var contractorDTO = res.dto;
                        var contractor = contractorDTO.contractor;
                        $('#ownershipType').text(contractorDTO.ownershipTypeTxt);
                        $('#country').text(contractor.countryTxt);
                        $('#tradeLicenseNo').text(contractor.tradeLicenseNo);
                        $('#firmName').text(contractor.firmName);
                        $('#tpn').text(contractor.tpn);

                        var categoryClassDTOs = contractorDTO.categories;
                        var ccTr = "";
                        var tFeeAmount = 0;
                        for (var i in categoryClassDTOs) {
                            tFeeAmount += parseFloat(categoryClassDTOs[i].feeAmount);
                            ccTr = ccTr + "<tr><td><input class='form-control' type='checkbox' checked='checked' disabled style='width: 17px; height: 17px;'></td>" +
                            "<td>" + categoryClassDTOs[i].categoryName + "</td>" +
                            "<td><select disabled class='form-control'><option>" + categoryClassDTOs[i].aClassName + "</option></select></td>" +
                            "<td class='fee'>"+categoryClassDTOs[i].feeAmount+"</td></tr>";
                        }
                        var tfoot = "<tr><td colspan='3' align='right'>Total</td><td>"+tFeeAmount+"</td> ";

                        $('#contractorCCTbl').find('tbody').html(ccTr);
                        $('#contractorCCTbl').find('tfoot').html(tfoot);
                        $('#paymentAmount').val(tFeeAmount);
                        $('#cdbNo').val(contractorDTO.cdbNo);

                    } else {
                        warningMsg(res.text);
                    }
                }
            });
        }
    }

    function paymentUpdate() {
        $('#btnSave').on('click', function (e) {
            $('#contractorPaymentForm').validate({
                submitHandler: function (form) {
                    $.ajax({
                        url: _baseURL() + '/paymentUpdate',
                        type: 'POST',
                        data: $(form).serializeArray(),
                        success: function (res) {
                            if (res.status == '1') {
                                successMsg(res.text, _baseURL());
                            } else {
                                warningMsg(res.text);
                            }
                        }
                    });
                }

        })
    })
    }

    function init(){
        viewDownloadAttachment();
        approve();
        reject();
        getContractorInfoForPayment();
        paymentUpdate();
    }


    return {
        verify: verify,
        getContractorInfo: getContractorInfo,
        init:init
    };
})();

$(document).ready(function () {
        contractor_action.verify();
        contractor_action.getContractorInfo();
        contractor_action.init();
    }
);