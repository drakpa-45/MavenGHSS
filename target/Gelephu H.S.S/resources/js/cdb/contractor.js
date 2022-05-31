//region functions callable from jsp

function addRow(tableId) {
    var $tableBody = $('#' + tableId).find("tbody");
    var isValid = true;
    $tableBody.find(':input').each(function () {
        if (isValid == true) {
            isValid = $('#contractorForm').validate().element(this);
        } else {
            $('#contractorForm').validate().element(this);
        }

    });
    if (isValid == true) {
        var $trLast = $tableBody.find("tr:last");
        var $trNew = $trLast.clone();
        $trNew.find('input:text').val('');
        $trLast.after($trNew);
    }
}
function removeRow(tableId) {
    $('#' + tableId + ' tr:last').remove();
}


function saveAndPreview(presentClass, nextClass) {
    var content = '<h3 class="pt-3 text-center">Fee Structure</h3>' + $("#fees_structure >.div-actual").html() +
        '<h3 class="pt-3 text-center">General Information</h3>' + $("#general_Information >.div-actual").html() +
        '<h3 class="pt-3 text-center">Category Details</h3>' + $("#category_details >.div-actual").html()
        + '<h3 class="pt-3 text-center">Human Resource</h3>' + $("#human_resource_criteria >.div-actual").html() +
        '<h3 class="pt-3 text-center">Consultant Equipment Details</h3>' + $("#equipment_details >.div-actual").html();

    $("." + presentClass + "").addClass('bg-blue-light text-white');
    $('.tab-pane').removeClass("active").addClass("active");
    $('.tab-content').removeClass("active").addClass("active");
    $("." + nextClass).addClass("active");
    $("." + presentClass + ">a").append("<i class='fa fa-check ml-1'></i>");

    //$("#" + nextClass).prepend(content);


}

function getModalData(tableId, prefix, totalCol) {
    var td = "";
    var modal = $('#'+prefix+'1').closest('.modal');
    if(modal.find(':input').valid() == false){
        return false;
    }

    for (var i = 1; i <= totalCol; i++) {
        var $this = $("#" + prefix + i);

        var text = '',value = '',name = '';

        var input_type = $this.prop('type');
        if(~input_type.indexOf("select")){
            value =$this.val();
            text = $this.find('option:selected').html();
            name = $this.prop('name');
        }else{
            value =$this.val();
            text = value;
            name = $this.prop('name');
        }

        var tdVal = "<input type='hidden' name='"+name+"' value='"+value+"'/>"+text;
        td = td + "<td>" + tdVal + "</td>";
    }
    td = td + "<td><input type='file' name='contractorHRs[0].contractorHRAs[0].attachment' class='form-control-file' required=''></td>";

    var tr = "<tr>" + td + "<td><a class='p-2'><i class='fa fa-pencil text-green'></i></a><a class='p-2'><i class='fa fa-trash text-danger'></i></a></td></tr>";
    $("#" + tableId).find(".noRecord").hide();
    $('#' + tableId).append(tr);

    modal.find(":input").val('');
    modal.modal('hide');

}


function nextTab(presentClass, nextClass) {

    $("." + presentClass + ">a").addClass('bg-blue-light text-white');
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


var inicount = 0;

//endregion


var contractor = (function () {
    "use strict";
    //var formID = "#commissionForm";
    var isSubmitted = false;

    function _baseURL() {
        return cdbGlobal.baseURL() + "/contractor";
    }

    function validate() {
        validate_gInfo();
        validate_cc();
        validate_hr();
        validate_eq();
    }

    function validate_gInfo() {
        $('#btnValGINext').on('click', function (e) {
            var isValid = true;
            $('#general_Information').find(':input').each(function () {
                if (isValid == true) {
                    isValid = $('#contractorForm').validate().element(this);
                } else {
                    $('#contractorForm').validate().element(this);
                }
            });
            //var isValid = $('#contractorForm').validate().element('#gInfo :input');
            if (isValid == true) {
                nextTab('general_Information', 'category_details')
            }
        });
    }

    function validate_cc() {
        $('#btnValCCNext').on('click', function (e) {
            var isValid = true;
            $('#contractorCCTbl').find(':input').each(function () {
                if (isValid == true) {
                    isValid = $('#contractorForm').validate().element(this);
                } else {
                    $('#contractorForm').validate().element(this);
                }

            });
            //var isValid = $('#contractorForm').validate().element('#gInfo :input');
            if (isValid == true) {
                nextTab('category_details', 'human_resource_criteria')
            }
        });
    }

    function validate_hr() {
        $('#btnValHRNext').on('click', function (e) {
            var isValid = true;
            var hrAdded = false;
            $('#hrDtlsTable').find(':input').each(function () {
                hrAdded = true;
                if (isValid == true) {
                    isValid = $('#contractorForm').validate().element(this);
                } else {
                    $('#contractorForm').validate().element(this);
                }

            });
            if(hrAdded == false && $('.appliedClassID').val() != 'ef832830-c3ea-11e4-af9f-080027dcfac6'){ // only small category no need of HR
                isValid = false;
                warningMsg("Add at least one HR personal.")
            }
            if (isValid == true) {
                nextTab('human_resource_criteria', 'equipment_details')
            }
        });
    }

    function validate_eq() {
        $('#btnValEqNext').on('click', function (e) {
            var isValid = true;
            $('#eqdatatable').find(':input').each(function () {
                if (isValid == true) {
                    isValid = $('#contractorForm').validate().element(this);
                } else {
                    $('#contractorForm').validate().element(this);
                }

            });
            if (isValid == true) {
                saveAndPreview('equipment_details', 'saveAndPreview')
            }
        });
    }


    function save() {
        $('#btnSubmit').on('click', function (e) {
            cdbGlobal.formIndexing($('#certificateTbl').find('tbody'));
            cdbGlobal.formIndexing($('#partnerDtls').find('tbody'));
            cdbGlobal.formIndexing($('#eqdatatable').find('tbody'));
            cdbGlobal.formIndexing($('#hrDtlsTable').find('tbody'));
            $("#addHRModal").find(":input").prop('disabled',true);
            $('#contractorForm').validate({
                submitHandler: function (form) {
                    alert('ldldld');
                    $.ajax({
                        url: _baseURL() + '/save',
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
                },
                errorPlacement: function (error, element) {
                    alert(error.text());
                }
            });
            alert("slsl")
        })
    }


    function getGewogList() {
        $('#pDzongkhagId').on('change', function (e) {
            $.ajax({
                url: _baseURL() + '/getGewogList',
                type: 'GET',
                async:false,
                data: {dzongkhagId: $(this).val()},
                success: function (res) {
                    if (res.status == '1') {
                        cdbGlobal.loadDropDown($('#pGewogId'), res.dto);
                    }
                }
            });
        });
    }

    function getVillageList() {
        $('#pGewogId').on('change', function (e) {
            $.ajax({
                url: _baseURL() + '/getVillageList',
                type: 'GET',
                async:false,
                data: {gewogId: $(this).val()},
                success: function (res) {
                    if (res.status == '1') {
                        cdbGlobal.loadDropDown($('#pVillageId'), res.dto);
                    }
                }
            });
        })
    }

    function showFileSize() {
        $('#certificateTbl').on('change', '.file', function () {
            //this.files[0].size gets the size of your file.
            var _size = this.files[0].size;
            var fSExt = ['Bytes', 'KB', 'MB', 'GB'],
                i = 0;
            while (_size > 900) {
                _size /= 1024;
                i++;
            }
            var exactSize = (Math.round(_size * 100) / 100) + ' ' + fSExt[i];
            $(this).closest('tr').find('.file-size').text(exactSize);
            //alert(this.files[0].size);
        });
    }

    function getTrainingDtl(cidNo){
        if(!cidNo){
            return;
        }
        $.ajax({
            url: _baseURL() + '/getTrainingDtl',
            type: 'GET',
            data: {cidNo: cidNo},
            success: function (res) {
                var trainingTbl = $('#inductionCourseDtl');
                var tr = '';
                for(var i in res){
                    tr = tr + "<tr><td>"+(parseInt(i)+1)+"</td>" +
                    "<td>" + res[i].tType + "</td>" +
                    "<td>" + (formatAsDate(res[i].fromDate) + ' to '+ formatAsDate(res[i].toDate) ) + "</td>" +
                    "<td>"+res[i].tModule+"</td>" +
                    "<td>"+res[i].participant+"</td>"+
                    "<td>"+res[i].cidNo+"</td></tr>";
                }
                trainingTbl.find('tbody').html(tr);
            }
        });
    }

    var cert = "<tr><td></td>" +
        "<td><input type='text' class='form-control' name='cAttachments[0].documentName'/> </td>"+
        "<td><input type='file' name='cAttachments[0].attachment' class='form-control-file file'></td>" +
        "<td class='file-size'></td>" +
        "<td><a class='p-2'><i class='fa fa-pencil text-green'></i></a><a class='p-2'><i class='fa fa-trash text-danger'></i></a></td>" +
        "</tr>";

    function sCertIncorporation() {
        $('#ownershipList').on('change', function (e) {
            var option = $(this).find("option:selected").html();
            var certificateTbl = $('#certificateTbl').find('tbody');
            if (~option.indexOf("Incorporated")) {
                $('#cIncorporation').removeClass('hide');
                certificateTbl.append(cert);
            }else{
                $('#cIncorporation').addClass('hide');
                certificateTbl.empty();
            }
        });
    }

    function addMoreCert(){
        $('#addMoreCert').on('click',function(e){
            var certificateTbl = $('#certificateTbl').find('tbody').append(cert);
            /*var row = certificateTbl.find('tr:eq(0)').html();
            certificateTbl.append('<tr>'+row.find(':input').val('')+'</tr>');*/
        });
    }

    function enableRegistrationNo(){
        $('.equipmentId').on('change',function(e){
            var isRegistration = $(this).find("option:selected").hasClass("1");
            if(isRegistration === true){
                $(this).closest('tr').find('.registrationNo').prop('disabled',false);
            }else{
                $(this).closest('tr').find('.registrationNo').prop('disabled',true);
            }
        })
    }

    function enableClassCategory(){
        $('.categoryCheck').on('click',function(e){
            if($(this).is(':checked')){
                $(this).closest('tr').find('.appliedClassID').prop('disabled',false);
            }else{
                $(this).closest('tr').find('.appliedClassID').val('').prop('disabled',true);
            }
        })
    }

    function getPersonalInfo(){
        $('#partnerDtls').on('change','.hr-cid', function (e) {
            var $this = $(this);
            var country = $this.closest('tr').find('.country #countryList').val();
            if(country == '8f897032-c6e6-11e4-b574-080027dcfac6') { //if bhutanese fetch from DCRC
                $.ajax({
                    url: _baseURL() + '/getPersonalInfo',
                    type: 'GET',
                    data: {cidNo: $this.val()},
                    success: function (res) {
                        if (res.status == '1') {
                            var dto = res.dto;
                            var index = $this.closest("tr").index();
                            $this.closest('tr').find('.name').val(dto.fullName).prop('readonly', true);
                            $this.closest('tr').find('.sex').val(dto.sex).prop('readonly', true);
                            if (parseInt(index) == 0) {
                                $('#pDzongkhagId').val(dto.dzongkhagId).change();
                                $('#pGewogId').val(dto.gowegId).change();
                                $('#pVillageId').val(dto.villageId);
                            }

                        }
                    }
                });
            }

            getTrainingDtl($this.val());
        })
    }

    function isEmailUnique(){
        $('#regEmail').on('change',function(){
            var $this = $('#regEmail');
            $.ajax({
                url: _baseURL() + '/isEmailUnique',
                type: 'GET',
                data: {email: $this.val()},
                success: function (res) {
                    if(res == true){
                        //$this.val()
                    }else{
                        $this.val('').focus();
                        warningMsg("This email has already been registered.");
                        $this.val('').focus();
                    }
                }
            });
        });
    }

    function addMoreFile(){
        $('.hrFile').on('change',function(e){
            var uplTbl = $('#hrUploadTbl').find('tbody');
            var $this = $(this), $clone = $this.clone();

            var tr = "<tr><td><input type='text' class='form-control' name='documentName'/> </td>" +
                "<td class='file'></td><td></td><td></td></tr>";
            uplTbl.append(tr);
            $this.after($clone).appendTo(uplTbl.find('.file'));


        });
    }

    function init() {
        save();
        getGewogList();
        getVillageList();
        showFileSize();
        validate();
        sCertIncorporation();
        addMoreCert();
        enableRegistrationNo();
        enableClassCategory();
        getPersonalInfo();
        isEmailUnique();
        addMoreFile();
    }

    return {
        init: init
    };
})();

$(document).ready(function () {
        contractor.init();
    }
);