/**
 * Created by Dechen Wangdi on 12/20/2019.
 */

//$('#feesStructure').prop('class', 'tab-pane active');

/*function next(val) {
 if (val == "fees_structure") {
 $('#locationcontent').prop('class', 'tab-pane active');
 $('#personalDetials').removeClass("active");
 $('#personal_tab').removeClass("active");
 $("#personalh").css("color", "white");
 $('#personalcheck').html('<i class="fa fa-check text-white"></i>');
 $("#personalh").css("background-color", "#120f65");
 $("#locationh").css("background-color", "rgb(18, 18, 19)");
 $("#locationh").css("color", "white");
 }
 else if (val == "location" && validatelocation()) {
 $('#attachmentcontent').prop('class', 'tab-pane active');
 $('#locationhead').removeClass("active");
 $('#locationcontent').removeClass("active");
 $('#locationcheck').html('<i class="fa fa-check text-white"></i>');
 $("#personalh").css("color", "white");
 $("#personalh").css("background-color", "#120f65");
 $("#locationh").css("background-color", "#120f65");
 $("#attachmenthead").css("background-color", "rgb(18, 18, 19)");
 $("#attachmenthead").css("color", "white");
 }
 }

 function pretab(val) {
 if (val == "location") {
 $('#personal_tab').prop('class', 'tab-pane active');
 $('#locationhead').removeClass("active");
 $('#locationcontent').removeClass("active");
 $("#personalh").css("background-color", "rgb(18, 18, 19)");
 $("#locationh").css("background-color", "#120f65");
 }
 else if ("attachment" == val) {
 $('#locationcontent').prop('class', 'tab-pane active');
 $('#attachmenthead').removeClass("active");
 $('#attachmentcontent').removeClass("active");
 $("#locationh").css("background-color", "rgb(18, 18, 19)");
 $("#attachmenthead").css("background-color", "#120f65");
 }
 }

 function validatelocation() {
 var retval = true;
 if ($('#estbname').val() == "") {
 $('#estbnameerr').html('Please provide business establishment name');
 retval = false;
 }
 if ($('#location').val() == "") {
 $('#locationerr').html('Please mention your exact location of business setup');
 retval = false;
 }
 if ($('#dzongkhag').val() == "") {
 $('#dzongkhagerr').html('Please select Dzongkhag of your business setup');
 retval = false;
 }
 if ($('#gewogsection').val() == "") {
 $('#gewogsectionerr').html('Please select Gewog of your business setup');
 retval = false;
 }
 if ($('#vilagesection').val() == "") {
 $('#vilagesectionerr').html('Please select Village of your business setup');
 retval = false;
 }
 if ($('#postaladdry').prop('checked') == false && $('#postaladdrn').prop('checked') == false) {
 $('#postalselecterr').html('Please choose how you wish to collect your certificate');
 retval = false;
 }
 if ($('#postaladdry').prop('checked') == true && $('#postalAddr').val() == "") {
 $('#postalAddrerr').html('Please mention your postal address from where you wish to collect certificate');
 retval = false;
 }
 return retval;
 }

 $('#estbname').click(function () {
 $('#estbnameerr').html('');
 });
 $('#location').click(function () {
 $('#locationerr').html('');
 });
 $('#dzongkhag').click(function () {
 $('#dzongkhagerr').html('');
 });
 $('#gewogsection').click(function () {
 $('#gewogsectionerr').html('');
 });
 $('#vilagesection').click(function () {
 $('#vilagesectionerr').html('');
 });
 $('#postaladdry').click(function () {
 $('#postalselecterr').html('');
 });
 $('#postaladdrn').click(function () {
 $('#postalselecterr').html('');
 });
 $('#postalAddr').click(function () {
 $('#postalAddrerr').html('');
 });

 function validatePersonal() {
 var retval = true;
 if ($('#cidNo').val() == "") {
 $('#ciderr').html('CID is required');
 retval = false;
 }
 if ($('#mobile').val() == "") {
 $('#mobileerr').html('Mobile Number is required');
 retval = false;
 }
 else if ($('#mobile').val().length != "8") {
 $('#mobileerr').html('Mobile Number should have 8 digit');
 retval = false;
 }
 return retval;
 }

 function showpostaladdr(val) {
 if (val == "Y") {
 $('#postaladdid').show();
 }
 else {
 $('#postaladdid').hide();
 }
 }

 function getgewog() {
 $('#gewogsection').append("<option value='1'>Chokhor</option><option value='1'>Chumey</option><option value='1'>Chokhor</option><option value='1'>Tang</option><option value='1'>Ura</option><option value='1'>Sampheling/Bhalujora</option>");
 }

 function getvilage() {
 $('#vilagesection').append("<option value='1'>Chokhor</option><option value='1'>Chumey</option><option value='1'>Chokhor</option><option value='1'>Tang</option><option value='1'>Ura</option><option value='1'>Sampheling/Bhalujora</option>");
 }

 var inicount = 0;

 function addmoreattachemnts() {
 inicount++;
 $('#fileadd').append('<div class="form-group" id="addedfile' + inicount + '"><div class="col-sm-6"><input type="file"  name="file1"></div><div class="col-sm-6"><button class="btn btn-danger" type="button" onclick="deleteate(' + inicount + ')"><i class="fa fa-times">Delete</i></button></div></div>');
 }

 function deleteate(id) {
 $('#addedfile' + id).remove();
 }

 require(['jquery', 'selectize'], function ($, selectize) {
 $(document).ready(function () {
 $('#input-tags').selectize({
 delimiter: ',',
 persist: false,
 create: function (input) {
 return {
 value: input,
 text: input
 }
 }
 });

 $('.select-beast').selectize({});

 $('#select-users').selectize({
 render: {
 option: function (data, escape) {
 return '<div>' +
 '<span class="image"><img src="' + data.image + '" alt=""></span>' +
 '<span class="title">' + escape(data.text) + '</span>' +
 '</div>';
 },
 item: function (data, escape) {
 return '<div>' +
 '<span class="image"><img src="' + data.image + '" alt=""></span>' +
 escape(data.text) +
 '</div>';
 }
 }
 });

 $('#select-countries').selectize({
 render: {
 option: function (data, escape) {
 return '<div>' +
 '<span class="image"><img src="' + data.image + '" alt=""></span>' +
 '<span class="title">' + escape(data.text) + '</span>' +
 '</div>';
 },
 item: function (data, escape) {
 return '<div>' +
 '<span class="image"><img src="' + data.image + '" alt=""></span>' +
 escape(data.text) +
 '</div>';
 }
 }
 });
 });
 });*/

function showAcknowledgement() {
    $("#registrtaionFormCard").hide();
    $("#acknowledgementCard").show();
}

function saveAndPreview(presentClass, nextClass) {
    var content = '<h3 class="pt-3 text-center">Fee Structure</h3>' + $(".feesStructure > form").html() +
        '<h3 class="pt-3 text-center">General Information</h3>' + $(".generalInformation > form").html() +
        '<h3 class="pt-3 text-center">Category Details</h3>' + $(".categoryDtls > form").html()
        + '<h3 class="pt-3 text-center">Human Resource</h3>' + $(".humanResourceCriteria > form").html() +
        '<h3 class="pt-3 text-center">Consultant Equipment Details</h3>' + $(".consultantEquipmentDtls > form").html();

    $("." + presentClass + ">a").addClass('bg-blue text-white');
    $('.tab-pane').removeClass("active");
    $('.tab-content').removeClass("active");
    $("." + nextClass).addClass("active");
    $("." + presentClass + ">a").append("<i class='fa fa-check ml-1'></i>");
    $("#" + nextClass).html(content);
}

function getModalData(tableId, prefix, totalCol) {
    var td = "";
    for (var i = 1; i <= totalCol; i++) {
        var rowVal = $("#" + prefix + i).val();
        td = td + "<td>" + rowVal + "</td>";
    }
    var tr = "<tr>" + td + "<td><a class='p-2'><i class='fa fa-pencil text-green'></i></a><a class='p-2'><i class='fa fa-trash text-danger'></i></a></td></tr>";
    $("#" + tableId + ">.noRecord").hide();
    $('#' + tableId).append(tr);
}


function addRow(tableId) {
    var $tableBody = $('#' + tableId).find("tbody"),
        $trLast = $tableBody.find("tr:last"),
        $trNew = $trLast.clone();
    $trLast.after($trNew);
}
function removeRow(tableId) {
    $('#' + tableId + ' tr:last').remove();
}

function nextTab(presentClass, nextClass) {

    /*if (presentClass !== "feesStructure") {
     var massages = validation();
     if (massages !== "") {
     warningMsg(massages);
     return;
     }
     }*/

    $("." + presentClass + ">a").addClass('bg-blue text-white');
    $('.tab-pane').removeClass("active");
    $('.tab-content').removeClass("active");
    $("." + nextClass).addClass("active");
    $("." + presentClass + ">a").append("<i class='fa fa-check ml-1'></i>");
}
function backTab(tabNo) {
    tabNo = tabNo - 1;
    $(".card").hide();
    $(".tab" + tabNo).show();
}

function validation() {
    var message = "";
    if ($('#serviceTypeId').val() == "") {
        message += "Please select service.\n";
    }
    if ($('#countryId').val() == "") {
        message += "Please select country.\n";
    }
    if ($('#serviceSectorType').val() == "") {
        message += "Please select service sector type.\n";
    }
    if ($('#trade').val() == "") {
        message += "Please select trade.\n";
    }
    if ($('#salutation').val() == "") {
        message += "Please select salutation.\n";
    }
    if ($('#cid').val() == "") {
        message += "Please enter CID.\n";
    }
    if ($('#name').val() == "") {
        message += "Please enter name.\n";
    }
    if ($('#dzongkhagId').val() == "") {
        message += "Please select dzongkhag.\n";
    }
    $('#email').val()
    $('#mobileNo').val()
    $('#qualificationId').val()
    $('#graduationYear').val()
    $('#submitcheckbox').val()

    return message;

}

//======================================
var engineer = (function () {
    "use strict";
    var isSubmitted = false;

    function _baseURL() {
        return cdbGlobal.baseURL() + "/engineer";
    }

    /**
     * To get gewog based on dzongkhag id.
     */
    function getGewogList() {
        $('#dzongkhagId').on('change', function () {
            $.ajax({
                url: _baseURL() + '/getGewogList',
                type: 'GET',
                data: {dzongkhagId: $(this).val()},
                async: false,
                success: function (res) {
                    if (res.status == '1') {
                        cdbGlobal.loadDropDown($('#gewog'), res.dto);
                    }
                }
            });
        });
    }

    /**
     *To get village list based on gewog Id.
     */
    function getVillageList() {
        $('#gewog').on('change', function (e) {
            $.ajax({
                url: _baseURL() + '/getVillageList',
                type: 'GET',
                data: {gewogId: $(this).val()},
                async: false,
                success: function (res) {
                    if (res.status == '1') {
                        cdbGlobal.loadDropDown($('#village'), res.dto);
                    }
                }
            });
        })
    }


    function getPersonalInfo() {
        $('#cid').on('blur', function (e) {
            var cid = $(this).val();
            $.ajax({
                url: _baseURL() + '/getPersonalInfo',
                type: 'GET',
                data: {cid: cid},
                success: function (res) {
                    if (res.status == '1') {
                        var dto = res.dto;
                        $('#name').val(dto.fullName).prop('readonly', true);
                        $('#dzongkhagId').val(dto.dzongkhagId).change();
                        $('#gewog').val(dto.gowegId).change();
                        $('#village').val(dto.villageId);
                    }
                }
            });
        })
    }

    function isEmailUnique() {
        $('#email').on('change', function () {
            var email = $(this).val();
            $.ajax({
                url: _baseURL() + '/isEmailUnique',
                type: 'GET',
                data: {email: email},
                success: function (res) {
                    if (res == true) {
                    } else {
                        $(this).val('').focus();
                        warningMsg("This email has already been registered.");
                        $(this).val('').focus();
                    }
                }
            });
        });
    }

    /**
     * To save group information.
     */
    function save() {
        $('#btnSave').on('click', function () {
            $('form[id="engineerForm"]').validate({
                submitHandler: function (form) {
                    var data = new FormData($(form)[0]);
                    //data.append('attachmentFile', $('input[type=file]')[0].files[0]);
                    $.ajax({
                        url: _baseURL() + '/save',
                        type: 'POST',
                        data: data,
                        enctype: 'multipart/form-data',
                        contentType: false,
                        processData: false,
                        success: function (res) {
                            if (res.status == 1) {
                                successMsg(res.text, _baseURL());
                                $("#registrtaionFormCard").hide();
                                $("#acknowledgementCard").show();
                            } else {
                                warningMsg(res.text);
                            }
                        }
                    });
                }
            });
        })
    }

    function cancel() {
        $('#btnCancel').on('click', function (e) {
            window.location.href = _baseURL();
        });
    }

    return {
        getGewogList: getGewogList,
        getVillageList: getVillageList,
        getPersonalInfo: getPersonalInfo,
        isEmailUnique: isEmailUnique,
        save: save,
        cancel: cancel
    };
})();

$(document).ready(function () {
    engineer.getGewogList();
    engineer.getVillageList();
    engineer.getPersonalInfo();
    engineer.isEmailUnique();
    engineer.save();
    engineer.cancel();
});


