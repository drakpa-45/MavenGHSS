package com.gov.edu.gelephuHSS.GHSS.contractor;

import com.gov.edu.gelephuHSS.GHSS.contractor.model.*;
import com.gov.edu.gelephuHSS.base.BaseService;
import com.gov.edu.gelephuHSS.GHSS.common.CommonService;
import com.gov.edu.gelephuHSS.GHSS.contractor.dto.ContractorDTO;
import com.gov.edu.gelephuHSS.GHSS.contractor.dto.FeeStructureDTO;
import com.gov.edu.gelephuHSS.global.enu.ApplicationStatus;
import com.gov.edu.gelephuHSS.lib.LoggedInUser;
import com.gov.edu.gelephuHSS.lib.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * ==================================================================================
 * Created by user on 9/29/2019.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
@Service
public class ContractorService extends BaseService{
    private String UPLOAD_LOC;

    @Autowired
    private ContractorDao contractorDao;

    @Autowired
    private CommonService commonService;


    /**
     * To get the fee structure of the contractor based on category.
     * @param category -- contractor category. If passed null would get fee structure of all category
     * @return List
     */
    @Transactional(readOnly = true)
    public List gFeeStructure(String category){
        return contractorDao.gFeeStructure(category);
    }


    /**
     * To get the fee structure of the contractor based on category.
     * @return List
     */
    @Transactional(readOnly = true)
    public List gContractCategory(){
        return contractorDao.gContractCategory();
    }

    @Transactional(readOnly = true)
    public List gClassification(){
        return contractorDao.gClassification();
    }

    @Transactional(readOnly = true)
    public List gEquipment(){
        return contractorDao.gEquipment();
    }


    /**
     * The main save method for contractor which calls specific save methods
     * @param contractorDTO   --  ContractorDTO
     * @param loggedInUser    --  Current logged in user
     * @return ResponseMessage  -- response message
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public ResponseMessage save(ContractorDTO contractorDTO, LoggedInUser loggedInUser) throws Exception {
        Contractor contractor = contractorDTO.getContractor();
        List<ContractorHR> contractorHRs = contractorDTO.getContractorHRs();
        List<ConCategory> categories = contractorDTO.getCategories();
        List<ContractorEQ> equipments = contractorDTO.getEquipments();

        Boolean isEmailUnique = isEmailUnique(contractor.getRegEmail());
        if(!isEmailUnique){
            responseMessage.reset();
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("This email has been already registered.");
            return responseMessage;
        }

        String contractorID = saveGI(contractor, loggedInUser);
        //region save attachment
        if(contractorDTO.getcAttachments() != null) {
            for (ContractorAttachment attachment : contractorDTO.getcAttachments()){
                attachment.setContractorId(contractorID);
                saveAttachment(attachment,loggedInUser);
            }

        }
        //endregion
        //region Save Contractor HR
        int j = 0;
        for(ContractorHR contractorHR:contractorHRs){
            String hrId = commonService.getRandomGeneratedId();
            contractorHR.setId(hrId);
            contractorHR.setContractorID(contractorID);
            contractorHR.setIsPartnerOrOwner(FALSE_INT);
            saveHR(contractorHR,loggedInUser);

            //Save Human resource attachment
           /* ContractorHRAttachment contractorHRA = contractorHR.getContractorHRA();
            contractorHRA.setContractorHrId(hrId);
            saveHRA(contractorHRA,j++,loggedInUser);*/
        }
        //endregion

        //region save contractor category
        categories.stream().filter(c->c.getProjectCateID()!= null).forEach(c->{
                    c.setContractorID(contractorID);
                    saveCC(c,loggedInUser);
                    //set Payment values
                    ContractorRegPayment contractorPayment = new ContractorRegPayment();
                    contractorPayment.setContractorId(contractorID);
                    contractorPayment.setCategoryId(c.getProjectCateID());
                    contractorPayment.setAppliedClassId(c.getAppliedClassID());
                    savePayment(contractorPayment, loggedInUser);
                }
        );
        //endregion

        Integer i = 0;
        for(ContractorEQ contractorEQ : equipments){
            i++;
            String contractorEQId = commonService.getRandomGeneratedId();
            contractorEQ.setId(contractorEQId);
            contractorEQ.setContractorId(contractorID);
            contractorEQ.setSerialNo(i.toString());
            saveEQ(contractorEQ,loggedInUser);

            //Save Human resource attachment
            String equipmentName = (String)commonService.getValue("cmnequipment","Name","Id",contractorEQ.getEquipmentId());
            ContractorEQAttachment contractorEQA = contractorEQ.getContractorEQA();
            contractorEQA.setDocumentName(equipmentName);
            contractorEQA.setEquipmentId(contractorEQId);
            saveEQA(contractorEQA,i,loggedInUser);
        }

        //region save applied service
        ContractorAppliedS contractorAS = new ContractorAppliedS();
        contractorAS.setContractorId(contractorID);
        saveAppliedService(contractorAS,loggedInUser);
        //endregion


        responseMessage.reset();
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Saved successfully.");
        return responseMessage;
    }

    /**
     * To save contractor general information
     * @param contractor    --  Contractor Entity
     * @param loggedInUser  --  Current logged in user
     * @return String  -- contractorID
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public String saveGI(Contractor contractor, LoggedInUser loggedInUser) throws Exception {

        //region Save contractor
        String contractorID = commonService.getRandomGeneratedId();
        String referenceNo = commonService.getNextID("crpcontractor", "ReferenceNo").toString();
        UPLOAD_LOC = "Contractor//"+referenceNo;
        contractor.setApplicationDate(loggedInUser.getServerDate());
        contractor.setWaiveOffLateFee(FALSE_INT);
        contractor.setHasNotification("0");
        contractor.setRegStatus("0");
        contractor.setAppStatusId(ApplicationStatus.UNDER_PROCESS.getCode());
        contractor.setCreatedBy(loggedInUser.getUserID());
        contractor.setCreatedOn(loggedInUser.getServerDate());
        contractor.setId(contractorID);
        contractor.setContractorId(contractorID);
        contractor.setReferenceNo(referenceNo);
        contractorDao.saveUpdate(contractor);
        //endregion

        //region Save Contractor HR
        for(ContractorHR contractorHR:contractor.getContractorHRs()){
            String hrId = commonService.getRandomGeneratedId();
            contractorHR.setId(hrId);
            contractorHR.setContractorID(contractorID);
            contractorHR.setIsPartnerOrOwner(TRUE_INT);
            saveHR(contractorHR, loggedInUser);
        }
        //endregion

        return contractorID;
    }


    @Transactional(readOnly = false)
    public void saveAttachment(ContractorAttachment cAttachment,LoggedInUser loggedInUser) throws Exception {
        MultipartFile attachment = cAttachment.getAttachment();
        String docName = cAttachment.getDocumentName()+commonService.getFileEXT(attachment);
        String docPath = commonService.uploadDocument(attachment, UPLOAD_LOC, docName);
        String attachmentId = commonService.getRandomGeneratedId();
        cAttachment.setId(attachmentId);
        cAttachment.setDocumentPath(docPath);
        cAttachment.setDocumentName(docName);
        cAttachment.setFileType(attachment.getContentType());
        cAttachment.setCreatedBy(loggedInUser.getUserID());
        cAttachment.setCreatedOn(loggedInUser.getServerDate());
        contractorDao.saveUpdate(cAttachment);
    }

    /**
     * To save contractor human resource
     * @param contractorHR  -- ContractorHR entity
     * @param loggedInUser  -- Current logged in user
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public void saveHR(ContractorHR contractorHR,LoggedInUser loggedInUser) throws Exception {
        if(contractorHR.getSiCertificate() == null){
            contractorHR.setSiCertificate(0);
        }
        contractorHR.setVerified(FALSE_INT);
        contractorHR.setApproved(FALSE_INT);
        contractorHR.setCreatedBy(loggedInUser.getUserID());
        contractorHR.setCreatedOn(loggedInUser.getServerDate());
        contractorDao.saveUpdate(contractorHR);
    }

    /**
     * to save contractor human resource attachment
     * @param contractorHRA -- ContractorHRAttachment
     * @param loggedInUser  -- Current logged in user
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public void saveHRA(ContractorHRAttachment contractorHRA,int i, LoggedInUser loggedInUser) throws Exception{
        MultipartFile attachment = contractorHRA.getAttachment();
        String documentName = contractorHRA.getDocumentName();
        if(documentName == null || documentName.isEmpty()){
            contractorHRA.setDocumentName("CV_UT_AT_"+i);
        }
        String docNameUpload = contractorHRA.getDocumentName()+commonService.getFileEXT(attachment);
        String docPath = commonService.uploadDocument(attachment, UPLOAD_LOC, docNameUpload);
        String hrAttachmentID = commonService.getRandomGeneratedId();
        contractorHRA.setId(hrAttachmentID);
        contractorHRA.setDocumentPath(docPath);
        contractorHRA.setDocumentName(docNameUpload);
        contractorHRA.setFileType(attachment.getContentType());
        contractorHRA.setCreatedBy(loggedInUser.getUserID());
        contractorHRA.setCreatedOn(loggedInUser.getServerDate());
        contractorDao.saveUpdate(contractorHRA);
    }

    /**
     * To save contractor category and classification
     * @param conCategory  --  ConCategory entity
     * @param loggedInUser  -- Current Logged in user
     */
    @Transactional(readOnly = false)
    public void saveCC(ConCategory conCategory, LoggedInUser loggedInUser) {
        String contractorCCId = commonService.getRandomGeneratedId();
        conCategory.setId(contractorCCId);
        conCategory.setCreatedBy(loggedInUser.getUserID());
        conCategory.setCreatedOn(loggedInUser.getServerDate());
        contractorDao.saveUpdate(conCategory);

    }

    @Transactional(readOnly = false)
    public void savePayment(ContractorRegPayment contractorRegPayment, LoggedInUser loggedInUser) {
        FeeStructureDTO feeDTO = (FeeStructureDTO)contractorDao.gFeeStructure(contractorRegPayment.getAppliedClassId()).get(0);
        String id = commonService.getRandomGeneratedId();
        contractorRegPayment.setId(id);
        contractorRegPayment.setAppliedAmount(feeDTO.getRegistrationFee());
        contractorRegPayment.setCreatedBy(loggedInUser.getUserID());
        contractorRegPayment.setCreatedOn(loggedInUser.getServerDate());
        contractorDao.saveUpdate(contractorRegPayment);
    }
    /**
     * To save contractor equipment details
     * @param contractorEQ  -- ContractorEQ entity
     * @param loggedInUser  -- Current Logged in user
     */
    @Transactional(readOnly = false)
    public void saveEQ(ContractorEQ contractorEQ, LoggedInUser loggedInUser) {
        if(contractorEQ.getApproved() == null){
            contractorEQ.setApproved(FALSE_INT);
        }
        if(contractorEQ.getVerified() == null){
            contractorEQ.setVerified(FALSE_INT);
        }

        contractorEQ.setCreatedBy(loggedInUser.getUserID());
        contractorEQ.setCreatedOn(loggedInUser.getServerDate());
        contractorDao.saveUpdate(contractorEQ);
    }

    @Transactional(readOnly = false)
    public void saveAppliedService(ContractorAppliedS contractorAppliedS, LoggedInUser loggedInUser) {
        String id = commonService.getRandomGeneratedId();
        String appliedServiceId = (String)commonService.getValue("crpservice","Id","ReferenceNo","1");
        contractorAppliedS.setId(id);
        contractorAppliedS.setServiceTypeId(appliedServiceId);
        contractorAppliedS.setCreatedBy(loggedInUser.getUserID());
        contractorAppliedS.setCreatedOn(loggedInUser.getServerDate());

        contractorDao.saveUpdate(contractorAppliedS);
    }




    /**
     * to save contractor human resource attachment
     * @param contractorEQA -- ContractorHRAttachment
     * @param loggedInUser  -- Current logged in user
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public void saveEQA(ContractorEQAttachment contractorEQA, int i,LoggedInUser loggedInUser) throws Exception{
        MultipartFile attachment = contractorEQA.getAttachment();

        String documentName = contractorEQA.getDocumentName();
        if(documentName == null || documentName.isEmpty()){
            contractorEQA.setDocumentName("CV_UT_AT_"+i);
        }
        String docName = contractorEQA.getDocumentName()+commonService.getFileEXT(attachment);
        String docPath = commonService.uploadDocument(attachment,UPLOAD_LOC,docName);
        String eqAttachmentID = commonService.getRandomGeneratedId();
        contractorEQA.setId(eqAttachmentID);
        contractorEQA.setDocumentPath(docPath);
        contractorEQA.setDocumentName(docName);
        contractorEQA.setFileType(attachment.getContentType());
        contractorEQA.setCreatedBy(loggedInUser.getUserID());
        contractorEQA.setCreatedOn(loggedInUser.getServerDate());
        contractorDao.saveUpdate(contractorEQA);
    }

    @Transactional(readOnly = true)
    public List getTrainingDtl(String cidNo){
        return contractorDao.getTrainingDtl(cidNo);
    }

    @Transactional(readOnly = true)
    public Boolean isEmailUnique(String email) {
        return contractorDao.isEmailUnique(email);
    }
}
