/**
 * Created by USER on 3/22/2020.
 */


function _baseURL() {
    return cdbGlobal.baseURL() + "/GHSS/trade";
}
function nextTab(presentClass, nextClass){
    if(presentClass=="feesStructure" && validateFees()){
        var cid = $('#app_çid').val();
        var url= _baseURL() +'/getPersonalInfo';
        $.ajax({
            url:url,
            type: 'GET',
            data: {cid: cid},
            success: function (res) {
                if (res.status == '1') {
                    var dto = res.dto;
                    $('#name').val(dto.fullName).prop('readonly', true);
                    $('#cidNo').val(cid);
                    $('#dzongkhagId').val(dto.dzongkhagId);
                    $('#dzongkhag').val(dto.dzongkhagNmae);
                    $('#gewog').val(dto.gowegName);
                    $('#village').val(dto.villageName);
                    var imagelink='https://www.citizenservices.gov.bt/BtImgWS/ImageServlet?type=PH&cidNo='+cid;
                    $('#imageId').html("<img src='"+imagelink+"'  width='200px'  height='200px' class='pull-right'/>");
                    changeNextTab(presentClass, nextClass)
                }
            }
        });
    }
    else if(presentClass=="personalInformation" && validatepersonalSection()){
        changeNextTab(presentClass, nextClass)
    }
    else if(presentClass=="categoryDtls"){
        changeNextTab(presentClass, nextClass)
    }
    else if(presentClass=="feesStructurerenewal"){
        changeNextTab(presentClass, nextClass)
    }


}
function validateFees(){
    var retutype=true;
    if($('#app_çid').val()==""){
        $('#app_çid_err').html('Please provide you CID');
        retutype=false;
    }
    if($('#app_çid').val()!="" && $('#app_çid').val().length!=11){
        $('#app_çid_err').html('Your CID Number Should be 11 digit');
        retutype=false;
    }
    return retutype;
}

function validatepersonalSection(){
    var retutype=true;
    if($('#salutation').val()==""){
        $('#salutation_err').html('Please select your salutation');
        $('#salutation').focus();
        retutype=false;
    }
    if($('#email').val()==""){
        $('#email_err').html('Please provide your email');
        $('#email').focus();
        retutype=false;
    }
    if($('#mobileNo').val()==""){
        $('#mobileNo_err').html('Please provide your mobile number');
        $('#mobileNo').focus();
        retutype=false;
    }
    return retutype;
}

/*function validateeducaion(){
    var retutype=true;
    if($('#mobileNo').val()==""){
        $('#mobileNo_err').html('Please provide your mobile number');
        $('#mobileNo').focus();
        retutype=false;
    }
    if($('#qualificationId').val()==""){
        $('#qualificationId_err').html('Please select your qualification');
        $('#qualificationId').focus();
        retutype=false;
    }
    if($('#graduationYear').val()==""){
        $('#graduationYear_err').html('Please mention year of graduation');
        $('#graduationYear').focus();
        retutype=false;
    }
    if($('#universityName').val()==""){
        $('#universityName_err').html('Please mention name of your university');
        $('#universityName').focus();
        retutype=false;
    }
    if($('#universityCountry').val()==""){
        $('#universityCountry_err').html('Please select coutry of university');
        $('#universityCountry').focus();
        retutype=false;
    }
    return retutype;
}*/
function remove_err(errId){
    $('#'+errId).html('');
}
function changeNextTab(presentClass, nextClass){
    $("." + presentClass + ">a").addClass('bg-blue text-white');
    $('.tab-pane').removeClass("active");
    $('.tab-content').removeClass("active");
    $("." + nextClass).addClass("active");
    $("." + presentClass + ">a").append("<span id='classppended"+presentClass+"'><i class='fa fa-check ml-1' ></i></span>");
}
function previousTab(previousClass, presentClass){
    $("." + presentClass + ">a").addClass('bg-blue text-white');
    $('.tab-pane').removeClass("active");
    $('.tab-content').removeClass("active");
    $("." + previousClass).addClass("active");
    $("#classppended"+previousClass).remove();
}

function validateAttachment(vl,id,checkId){
    if(vl!=""){
        $('#'+id).prop('class', 'alert badge-info');
    }
    else{
        $('#'+id).prop('class', 'alert badge-danger');
    }
}

var inicount=1;
function addmoreattachemnts(){
    inicount++;
    var fnshow='validateAttachment(this.value,"file'+inicount+'","file_added'+inicount+'")';
    $('#fileadd').append('<div class="form-group row" id="addedfile'+inicount+'"><div class="col-sm-6"><input type="file" class="alert badge-danger" onchange=\''+fnshow+'\' name="files" id="file'+inicount+'"><i id="file_added'+inicount+'"></i></div><div class="col-sm-6"><button class="btn btn-danger fa fa-pull-right mt-4" type="button" onclick="deleteate('+inicount+')"><i class="fa fa-times pr-4"></i>Delete this</button></div></div>');
}
function deleteate(id){
    $('#addedfile'+id).remove();
}
function submitRegistrationForm(){
    $('#concirmationModel').modal('show');
    $('#actiontype').val('submit');
    $('#formId').val('specializedTradeForm');
    $('#targetId').val('acknowledgementmessage');
    $('#url').val(_baseURL() +'/saveSpecializedTrade');
    $('#messages').html('You are about to submit application. Are you sure to proceed ?');
}
function closemodel(modelId){
    $('#'+modelId).modal('hide');
}
function SubmitApproveVerifyApplicationDetials(firmId,targetId){
    var url=_baseURL() +'/saveArchitect';
    var options = {target:'acknowledgementmessage',url:url,type:'POST',enctype: 'multipart/form-data', data: $('#architectForm').serialize()};
    $("#architectForm").ajaxSubmit(options);
    $('#registrtaionFormCard').hide();
    $('#acknowledgementCard').show();
    $('#concirmationModel').modal('hide');

    // var data = new FormData($('#architectForm')[0]);
    /*$.ajax({
     url: url,
     type: 'POST',
     data: $('#architectForm').serialize(),
     enctype: 'multipart/form-data',
     //contentType: false,
     //processData: false,
     success: function (res){
     if (res.status == 1) {
     $("#acknowledgementmessage").html(res.text);
     $("#registrtaionFormCard").hide();
     $("#acknowledgementCard").show();
     } else{
     warningMsg(res.text);
     }
     }
     });*/

}




function updateReject(){
    if(validateReject()){
        var url= '/GHSS/admin_specializedTrade/emptylayout/updatereject';
        var options = {target:'#content_main_div',url:url,type:'GET', data: $('#tradeverificationForm').serialize()};
        $("#tradeverificationForm").ajaxSubmit(options);
    }
}
function validateReject(){
    var returntpe=true;
    if($('#remarks').val()==""){
        $('#remarks_err').html('Please mention remarks');
        $('#remarks').focus();
        returntpe=false;
    }
    return returntpe;
}

function verifyApplication(){
    var url= '/GHSS/admin_specializedTrade/emptylayout/verifySpTradeRegistration';
    var options = {target:'#content_main_div',url:url,type:'GET', data: $('#tradeverificationForm').serialize()};
    $("#tradeverificationForm").ajaxSubmit(options);
}
function approveApplication(type){
    url= '/GHSS/admin_specializedTrade/emptylayout/approveSpTradeRegistration?servicefor='+type;
    var options = {target:'#content_main_div',url:url,type:'GET', data: $('#tradeverificationForm').serialize()};
    $("#tradeverificationForm").ajaxSubmit(options);
}

$('.datepicker').datepicker({
    changeMonth:true,
    changeYear: true,
    autoclose: true,
    todayHighlight: true,
    dateFormat:'yy-mm-dd',
    minDate: +1
}).on('changeDate',function(){
    $('.datepicker').datepicker('hide');
});

function approveAndGenerateCertificate(type){
    if(validateFeesDetails()){
        var url= '/GHSS/admin_architect/emptylayout/approveAndGenerateCertificate?servicefor='+type;
        var options = {target:'#content_main_div',url:url,type:'POST', data: $('#architectverificationForm').serialize()};
        $("#architectverificationForm").ajaxSubmit(options);
    }
}
function validateFeesDetails(){
    var return_type=true;
    if($('#paymentReceiptDate').val()==""){
        $('#paymentReceiptDate_err').html('Please mention payment receipt date');
        $('#paymentReceiptDate').focus();
        return_type=false;
    }
    if($('#paymentReceiptNo').val()==""){
        $('#paymentReceiptNo_err').html('Please mention payment receipt number');
        $('#paymentReceiptNo').focus();
        return_type=false;
    }
    if($('#paymentmode').val()==""){
        $('#paymentmode_err').html('Please select mode of payment');
        $('#paymentmode').focus();
        return_type=false;
    }
    return return_type;
}
$('#architect_table').DataTable({
    responsive: true
});
function printCertificate(arNo){

    /*var url= '/GHSS/admin_architect/emptylayout/printCertificate';
    var options = {target:'#content_main_div',url:url,type:'POST', data: $('#architectverificationForm').serialize()};
    $("#architectverificationForm").ajaxSubmit(options);*/
}
function printInfo(cdbNo){
    var url= '/GHSS/admin_architect/emptylayout/printarchitectInfo?cdbNo='+cdbNo;
    $('#content_main_div_public_user').load(url);
}

function submitForm(){
    $('#concirmationRenewalModel').modal('show');
    $('#messages').html('You are about to submit application. Are you sure to proceed ?');
}
function SubmitApplicationDetials(){
    var url='/GHSS/public_access/emptylayout/saveSpecializedTrade';
    var options = {target:'#registrtaionFormCard',url:url,type:'POST',enctype: 'multipart/form-data', data: $('#specializedTradeForm').serialize()};
    $("#specializedTradeForm").ajaxSubmit(options);
    $('#concirmationModel').modal('hide');
}
function SubmitRenewalApplicationDetials(){
    var url= '/GHSS/public_access/emptylayout/submitRenwalApplication';
    var options = {target:'#content_main_div_public_user',url:url,type:'POST', data: $('#architectrenewalForm').serialize()};
    $("#architectrenewalForm").ajaxSubmit(options);
    $('#concirmationRenewalModel').modal('hide');
}
function viewAttachment(uuid,type,path,name){
    //var url= '${pageContext.request.contextPath}/FileDownloadServlet?uuid='+uuid+'&type='+type;
    var url= '/GHSS/public_access_trade/emptylayout/donwloadFiles?uuid='+uuid+'&type='+type;
    window.open(url,'_blank');
}


function submitcancellation(){
    var url= '/GHSS/public_access/emptylayout/submitcancellation';
    var options = {target:'#content_main_div_public_user',url:url,type:'POST', data: $('#architectrenewalForm').serialize()};
    $("#architectrenewalForm").ajaxSubmit(options);
}

function PrintDiv() {
    var divToPrint = document.getElementById('print');
    var popupWin = window.open('', '_blank', 'width=1000,height=950');
    popupWin.document.open();
    popupWin.document.write('<html><body onload="window.print()">' + divToPrint.innerHTML + '</html>');
    popupWin.document.close();
  //  var url = '${pageContext.request.contextPath}/ruralTimber/afterPrintingApp';
    var options = {
        target: '#registrtaionFormCard',
        url: url,
        type: 'POST',
        enctype: 'form-data',
        data: $('#printingForm').serialize()
    };
    $("#printingForm").submit();
}