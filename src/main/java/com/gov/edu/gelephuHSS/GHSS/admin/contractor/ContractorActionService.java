package com.gov.edu.gelephuHSS.GHSS.admin.contractor;

import com.gov.edu.gelephuHSS.GHSS.admin.dto.*;
import com.gov.edu.gelephuHSS.GHSS.contractor.model.Contractor;
import com.gov.edu.gelephuHSS.base.BaseService;
import com.gov.edu.gelephuHSS.GHSS.common.CommonService;
import com.gov.edu.gelephuHSS.GHSS.contractor.dto.ContractorHrDTO;
import com.gov.edu.gelephuHSS.global.global.MailSender;
import com.gov.edu.gelephuHSS.lib.LoggedInUser;
import com.gov.edu.gelephuHSS.lib.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;

/**
 * ==================================================================================
 * Created by user on 2/14/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
@Service
public class ContractorActionService extends BaseService{

    @Autowired
    private ContractorActionDao contractorActionDao;
    @Autowired
    private CommonService commonService;

    @Transactional(readOnly = true)
    public List gGroupTaskList(String status){
        return contractorActionDao.gGroupTaskList(status);
    }

    @Transactional(readOnly = true)
    public List gMyTaskList(String userId){
        return contractorActionDao.gMyTaskList(userId);
    }

    @Transactional(readOnly = true)
    public ResponseMessage  getContractorData(BigInteger referenceNo,Character flag){
        ContractorInfoDTO contractorDTO = new ContractorInfoDTO();
        Contractor contractor = contractorActionDao.getContractor(referenceNo);
        contractorDTO.setContractor(contractor);

        if(flag != 'P'){
            List<CategoryClassDTO> categoryClassDTOs = contractorActionDao.getCategoryClass(contractor.getContractorId());
            contractorDTO.setOwnershipTypeTxt(commonService.getValue("cmnlistitem", "Name", "Id", contractor.getOwnershipTypeId()).toString());
            contractorDTO.setCountryTxt(commonService.getValue("cmncountry", "Name", "Id", contractor.getpCountryId()).toString());
            contractorDTO.setpDzongkhagTxt(commonService.getValue("cmndzongkhag", "NameEn", "Id", contractor.getRegDzongkhagId()).toString());
            contractorDTO.setpGewogTxt(commonService.getValue("cmngewog", "Gewog_Name", "Gewog_Id", contractor.getpGewogId()).toString());
            contractorDTO.setpVillageTxt(commonService.getValue("cmnvillage", "Village_Name", "Village_Id", contractor.getpVillageId()).toString());
            contractorDTO.setEstDzongkhagTxt(commonService.getValue("cmndzongkhag", "NameEn", "Id", contractor.getRegDzongkhagId()).toString());

            List<ContractorHrDTO> contractorHRs = contractorActionDao.getContractorHRs(contractor.getContractorId());
            List<EquipmentDTO> equipmentDTOs = contractorActionDao.getEquipment(contractor.getContractorId());
            List<ApplicationHistoryDTO> appHistoryDTOs = contractorActionDao.getAppHistoryDtl(contractor.getContractorId());
            contractorDTO.setContractorHRs(contractorHRs);
            contractorDTO.setEquipments(equipmentDTOs);
            contractorDTO.setAppHistoryDTOs(appHistoryDTOs);
            contractorDTO.setCategories(categoryClassDTOs);
        }else{
            List<CategoryClassDTO> categoryClassDTOs = contractorActionDao.getFeeCategoryClass(contractor.getContractorId());
            contractorDTO.setCategories(categoryClassDTOs);
            String cdbNo = contractorActionDao.getNextCDBNo();
            contractorDTO.setCdbNo(cdbNo);
        }
        contractorDTO.setOwnershipTypeTxt(commonService.getValue("cmnlistitem", "Name", "Id", contractor.getOwnershipTypeId()).toString());
        contractorDTO.setCountryTxt(commonService.getValue("cmncountry", "Name", "Id", contractor.getpCountryId()).toString());

        responseMessage.setDto(contractorDTO);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        return responseMessage;
    }


    @Transactional(readOnly = false)
    public ResponseMessage verify(BigInteger appNo,String vRemarks,LoggedInUser loggedInUser) {
        String contractorId = (String)commonService.getValue("crpcontractor","CrpContractorId","ReferenceNo",appNo.toString());
        contractorActionDao.verify(contractorId,loggedInUser.getUserID(),vRemarks);

        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Contractor application number :"+appNo+" verified successfully.");
        return responseMessage;
    }

    @Transactional(readOnly = false)
    public ResponseMessage approve(BigInteger appNo, String aRemarks, LoggedInUser loggedInUser) {
        String contractorId = (String)commonService.getValue("crpcontractor","CrpContractorId","ReferenceNo",appNo.toString());
        contractorActionDao.approve(contractorId, loggedInUser.getUserID(), aRemarks);

        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Contractor application number :"+appNo+" approved successfully.");
        return responseMessage;
    }

    @Transactional(readOnly = false)
    public ResponseMessage paymentUpdate(PaymentUpdateDTO paymentUpdateDTO, LoggedInUser loggedInUser) throws Exception{
        Contractor contractor = contractorActionDao.getContractor(new BigInteger(paymentUpdateDTO.getAppNo()));
        String approvedApplicationStatusId = (String)commonService.getValue("cmnlistitem","Id","ReferenceNo","12003");
        contractor.setAppStatusId(approvedApplicationStatusId);
        contractor.setRegStatus("3");
        contractor.setCdbNo(paymentUpdateDTO.getCdbNo());
        contractor.setPaymentApprovedDate(loggedInUser.getServerDate());
        contractor.setPaymentApproverUserId(loggedInUser.getUserID());
        contractor.setPaymentApproverRemark(paymentUpdateDTO.getPaymentRemarks());
        contractor.setPaymentReceiptDate(paymentUpdateDTO.getPaymentDate());
        contractor.setPaymentReceiptNo(paymentUpdateDTO.getPaymentReceiptNo());
        contractor.setRegApprovedDate(loggedInUser.getServerDate());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(loggedInUser.getServerDate());
        calendar.add(Calendar.YEAR, 2);
        contractor.setRegExpiryDate(calendar.getTime());
        contractorActionDao.saveOrUpdate(contractor);

        paymentUpdateDTO.setContractorId(contractor.getContractorId());
        contractorActionDao.paymentUpdate(contractor.getContractorId(),loggedInUser.getUserID()
                , approvedApplicationStatusId,contractor.getCreatedBy());
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Contractor application number :"+paymentUpdateDTO.getAppNo()+" Payment Approved");
        MailSender.sendMail(contractor.getRegEmail(),"GHSS@gov.bt",null,"Dear User, Your application is approved","Application approved");
        return responseMessage;
    }

    @Transactional(readOnly = false)
    public ResponseMessage reject(BigInteger appNo, String rRemarks, LoggedInUser loggedInUser) {
        String contractorId = (String)commonService.getValue("crpcontractor","CrpContractorId","ReferenceNo",appNo.toString());
        String rejectedApplicationStatusId = (String)commonService.getValue("cmnlistitem","Id","ReferenceNo","12004");
        contractorActionDao.reject(contractorId, loggedInUser.getUserID(), rRemarks,rejectedApplicationStatusId);

        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Contractor application number :"+appNo+" has been rejected.");
        return responseMessage;
    }

    @Transactional(readOnly = false)
    public void send2MyOrGroupTask(String appNo, String flag,String lockUserId) {
        if(flag.equals("M")){
            lockUserId = null;
        }
        contractorActionDao.send2MyOrGroupTask(appNo,lockUserId);
    }

    public String getApplicationStatus(String appNo) {
        return (String)commonService.getValue("crpcontractor","CmnApplicationRegistrationStatusId","ReferenceNo",appNo);
    }
}
