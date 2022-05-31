package com.gov.edu.gelephuHSS.GHSS.architect.services;

import bt.gov.ditt.sso.client.dto.UserSessionDetailDTO;
import com.gov.edu.gelephuHSS.GHSS.architect.dao.ArchitectDao;
import com.gov.edu.gelephuHSS.GHSS.architect.dto.ArchitectDto;
import com.gov.edu.gelephuHSS.GHSS.architect.entity.CrparchitectEntity;
import com.gov.edu.gelephuHSS.GHSS.architect.entity.ServiceEntity;
import com.gov.edu.gelephuHSS.GHSS.common.CommonService;
import com.gov.edu.gelephuHSS.global.enu.ApplicationStatus;
import com.gov.edu.gelephuHSS.lib.ResponseMessage;
import com.gov.edu.gelephuHSS.GHSS.architect.entity.ArchitectDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by USER on 3/19/2020.
 */
@Service
public class ArchitectServices {
    @Autowired
    private ArchitectDao dao;
    @Autowired
    private CommonService commonService;

    @Transactional(readOnly = true)
    public List getFeesDetals(String architect) {
        return dao.gFeeStructure(architect);
    }
    @Transactional(readOnly = false)
    public ResponseMessage saveArchitect(ArchitectDto dto, UserSessionDetailDTO user) {
        String generateID = commonService.getRandomGeneratedId();
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            CrparchitectEntity architect = convertDtoToEntity(dto, user);
            architect.setId(generateID);
            architect.setCrpArchitectId(generateID);
            dao.save(architect);

            ServiceEntity serviceEntity = convertEngineerToServiceEntity(user);
            serviceEntity.setArchitectId(generateID);
            dao.saveAservies(serviceEntity);
            responseMessage.setStatus(1);
            dto.setReferenceNo(new BigInteger(architect.getReferenceNo()));
            dto.setCrpArchitectId(generateID);
            responseMessage.setDto(dto);

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return responseMessage;
    }
    private CrparchitectEntity convertDtoToEntity(ArchitectDto dto, UserSessionDetailDTO user){
        CrparchitectEntity entity=new CrparchitectEntity();
        entity.setApplicationDate(new Date());
        entity.setInitialDate(new Date());
        entity.setReferenceNo(getMaxId().toString());
        entity.setCIDNo(dto.getCidNo());
        entity.setName(dto.getFullname());
        entity.setCmnSalutationId(dto.getSalutation());
        entity.setCmnDzongkhagId(dto.getDzongkhagId());
        entity.setGewog(dto.getGewog());
        entity.setVillage(dto.getVillage());
        entity.setCmnServiceSectorTypeId(dto.getServiceSectorType());
        entity.setCmnCountryId(dto.getCountryId());
        entity.setTPN(dto.getTrade());//trade field is not there in db
        entity.setCmnApplicationRegistrationStatusId(ApplicationStatus.UNDER_PROCESS.getCode());

        entity.setEmail(dto.getEmail());
        entity.setMobileNo(dto.getMobileNo());
        entity.setEmployerName(dto.getEmployeeName());
        entity.setEmployerAddress(dto.getEmployeeAddress());
        entity.setCmnQualificationId(dto.getQualificationId());
        entity.setGraduationYear(dto.getGraduationYear());
        entity.setCmnUniversityCountryId(dto.getUniversityCountry());
        entity.setNameOfUniversity(dto.getUniversityName());
        if(user==null){
            entity.setCreatedBy("000000");
        }
        else{
            entity.setCreatedBy(user.getCid());
        }
        entity.setCreatedOn(new Date());
        return  entity;
    }
    private ServiceEntity convertEngineerToServiceEntity(UserSessionDetailDTO loggedInUser) {
        String generateID = commonService.getRandomGeneratedId();
        ServiceEntity engineerAppliedServiceEntity = new ServiceEntity();
        engineerAppliedServiceEntity.setId(generateID);
        engineerAppliedServiceEntity.setCmnServiceTypeId("55a922e1-cbbf-11e4-83fb-080027dcfac6");//new registration
        engineerAppliedServiceEntity.setCreatedBy(null);
        engineerAppliedServiceEntity.setEditedBy(null);
        engineerAppliedServiceEntity.setCreatedOn(new Date());
        engineerAppliedServiceEntity.setEditedOn(new Date());
        return engineerAppliedServiceEntity;
    }

    @Transactional(readOnly = false)
    private BigInteger getMaxId() {
        BigInteger referenceNo = dao.getMaxId();
        return (referenceNo.intValue() > 0) ? referenceNo.add(BigInteger.ONE) : BigInteger.ONE;
    }

    @Transactional
    public void saveDoc(MultipartFile[] files, String referenceNo, String service,String loggedInUser) {

        try{
            for (int i=0;i<files.length;i++){
                String generateID = commonService.getRandomGeneratedId();
                MultipartFile file= files[i];
                String docPath = commonService.uploadDocument(file,service,file.getOriginalFilename());
                ArchitectDocument docdet= new ArchitectDocument();
                docdet.setId(generateID);
                docdet.setArchitectid(referenceNo);
                docdet.setDocumentName(file.getOriginalFilename());
                docdet.setFileType(file.getContentType());
                docdet.setDocumentPath(docPath);
                docdet.setCreatedBy(loggedInUser);
                docdet.setEditedBy(loggedInUser);
                docdet.setCreatedOn(new Date());
                docdet.setEditedOn(new Date());
                dao.saveAttchment(docdet);
            }
        }
        catch (Exception e){
            System.out.print(e);
        }
    }

    @Transactional(readOnly = true)
    public List getTaskList(String status,String type,String userId,String servicetype){
        return dao.getTaskList(status,type,userId,servicetype);
    }

    public void assignMyTask(String appNo, String userID) {
        dao.assignMyTask(appNo, userID);
    }

    public ArchitectDto getArchitetDetails(String appNo) {
        ArchitectDto dto=dao.getArchitetDetails(appNo);
        if(dto.getServiceTypeId().equalsIgnoreCase("New Registration") ){
            if(dto.getServiceSectorType().equalsIgnoreCase("Goverment") && dto.getUpdateStatus().equalsIgnoreCase("36f9627a-adbd-11e4-99d7-080027dcfac6")){
                //generate GHSS nunber
                String architectNo=dao.generateArchitectNo(dto.getCountryId(),dto.getServiceSectorType());
                dto.setCdbNo(architectNo);
            }
            else if(dto.getUpdateStatus().equalsIgnoreCase("6195664d-c3c5-11e4-af9f-080027dcfac6")){
                String architectNo=dao.generateArchitectNo(dto.getCountryId(),dto.getServiceSectorType());
                dto.setCdbNo(architectNo);
            }
        }
        List<ArchitectDocument> doc=dao.getdocumentList(dto.getCrpArchitectId());
        dto.setDoc(doc);
       return dto;
    }

    public ArchitectDto updateReject(ArchitectDto dto, String userID, HttpServletRequest request) {
        dto= dao.updateReject(dto, userID,request);
        if(dto.getUpdateStatus().equalsIgnoreCase("Success")){
            if(dto.getUpdateStatus().equalsIgnoreCase("Success")){
                //send sms and email notification
            }
            else{
                dto.setUpdateStatus("Failed to update workflow table for rejecting. ");
            }
        }
        else{
            dto.setUpdateStatus("Failed to update the application table for rejecting. ");
        }
        return dto;
    }

    public ArchitectDto updateVerification(ArchitectDto dto, String userID, HttpServletRequest request) {
        dto= dao.updateVerification(dto, userID,request);
        if(dto.getUpdateStatus().equalsIgnoreCase("Success")){
            if(dto.getUpdateStatus().equalsIgnoreCase("Success")){
                //send sms and email notification
            }
            else{
                dto.setUpdateStatus("Failed to update workflow table for verification. ");
            }
        }
        else{
            dto.setUpdateStatus("Failed to update the application table for verification. ");
        }
        return dto;
    }

    public ArchitectDto approveArchitectRegistration(ArchitectDto dto, String userID, HttpServletRequest request) {
        dto= dao.approveArchitectRegistration(dto, userID,request);//will update application table to for both government and private architect
        ArchitectDto dto1=dao.getarchitectapplicationdetails(dto);
        if(dto.getServiceTypeId().equalsIgnoreCase("cancel")){
            //update cancellation
            dto1.setCdbNo(dto.getCdbNo());
            dto1.setRemarks(dto.getRemarks());
            dao.updateCancellationDetails(dto1, userID);
            dto.setUpdateStatus("Success");
        }
        else{
            if(dto.getServiceSectorType().equalsIgnoreCase("Goverment")){
                dto1.setRemarks(dto.getRemarks());
                String insert="";
                if(dto.getServiceTypeId().equalsIgnoreCase("registration")){
                    insert=dao.insertuserDetails(dto1, userID, request);
                    if(!insert.equalsIgnoreCase("Insert_Fail")){
                        dto1.setCdbNo(dto.getCdbNo());
                        dto1.setRemarks(dto.getRemarks());
                        String password=insert.split("/")[1];
                        insert=dao.insertArchitedtFinalDetails(dto1, userID, insert.split("/")[0]);
                    }
                }
                else if(dto.getServiceTypeId().equalsIgnoreCase("renewal")){
                    dto1.setCdbNo(dto.getCdbNo());
                    insert=dao.updateRenewalDetails(dto1, userID, 5);
                }
                if(insert.equalsIgnoreCase("Success")){
                    dto.setUpdateStatus("Success");
                    //send notification
                }
            }
        }


        if(dto.getUpdateStatus().equalsIgnoreCase("Success")){
            if(dto.getUpdateStatus().equalsIgnoreCase("Success")){
                //send sms and email notification for application approval
            }
            else{
                dto.setUpdateStatus("Failed to update workflow table for approval. ");
            }
        }
        else{
            dto.setUpdateStatus("Failed to update the application table for approval. ");
        }
        return dto;
    }

    public ArchitectDto approveAndGenerateCertificate(ArchitectDto dto, String userID, HttpServletRequest request) {
        dto= dao.updateApplicationForPayment(dto, userID, request);
        String insert="";
        if(dto.getUpdateStatus().equalsIgnoreCase("Success")){
            ArchitectDto dto1=dao.getarchitectapplicationdetails(dto);
            dto1.setRemarks(dto.getRemarks());
            dto1.setPaymentmode(dto.getPaymentmode());
            dto1.setPaymentAmt(dto.getPaymentAmt());
            dto1.setTotalAmt(dto.getTotalAmt());
            dto1.setNoOfDaysAfterGracePeriod(dto.getNoOfDaysAfterGracePeriod());
            dto1.setNoOfDaysLate(dto.getNoOfDaysLate());
            if(dto.getServiceTypeId().equalsIgnoreCase("registration")){
                insert=dao.insertuserDetails(dto1, userID, request);
                if(!insert.equalsIgnoreCase("Insert_Fail")){
                    dto1.setCdbNo(dto.getCdbNo());
                    String password=insert.split("/")[1];
                    insert=dao.insertArchitedtFinalDetails(dto1, userID, insert.split("/")[0]);
                    if(insert.equalsIgnoreCase("Success")){
                        insert=dao.insertInPaymentDetails(dto, userID, request);
                    }
                }
            }
            else if(dto.getServiceTypeId().equalsIgnoreCase("renewal")){
                dto1.setServiceTypeId(ApplicationStatus.RENEWAL.getCode());
                dto1.setCdbNo(dto.getCdbNo());
                dao.insertInPaymentServiceDetails(dto1, userID, request);
                insert=dao.updateRenewalDetails(dto1, userID, 2);
            }
            if(insert.equalsIgnoreCase("Success")){
                dto.setUpdateStatus("Success");
                //send notification
            }
        }
        return dto;
    }


    public List<ArchitectDto> getPrintList() {
        return dao.getPrintList();
    }

    public ArchitectDto checkOngoingApplication(String cdbNo) {
        ArchitectDto dto=dao.checkOngoingApplication(cdbNo);
        return dto;
    }

    @Transactional
    public ResponseMessage saveArchitectRenenwal(ArchitectDto dto, String userID) {
        String generateID = commonService.getRandomGeneratedId();
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            CrparchitectEntity architect = convertDtoToEntityForRenewal(dto, userID);
            architect.setId(generateID);
            architect.setCrpArchitectId(generateID);
            dao.save(architect);

            String generateID1= commonService.getRandomGeneratedId();
            ServiceEntity engineerAppliedServiceEntity = new ServiceEntity();
            engineerAppliedServiceEntity.setId(generateID1);
            engineerAppliedServiceEntity.setCmnServiceTypeId("45bc628b-cbbe-11e4-83fb-080027dcfac6");//Renewal of CDB Certificate
            engineerAppliedServiceEntity.setCreatedBy(userID);
            engineerAppliedServiceEntity.setEditedBy(userID);
            engineerAppliedServiceEntity.setCreatedOn(new Date());
            engineerAppliedServiceEntity.setEditedOn(new Date());
            engineerAppliedServiceEntity.setArchitectId(generateID);
            dao.saveAservies(engineerAppliedServiceEntity);

            responseMessage.setStatus(1);
            dto.setReferenceNo(new BigInteger(architect.getReferenceNo()));
            dto.setCrpArchitectId(generateID);
            responseMessage.setDto(dto);

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return responseMessage;
    }
    private CrparchitectEntity convertDtoToEntityForRenewal(ArchitectDto dto1, String user){
        ArchitectDto dto=dao.getarchitectDetails(dto1.getCdbNo());
        CrparchitectEntity entity=new CrparchitectEntity();
        entity.setApplicationDate(new Date());
        entity.setInitialDate(new Date());
        entity.setReferenceNo(getMaxId().toString());
        entity.setCmnApplicationRegistrationStatusId(ApplicationStatus.UNDER_PROCESS.getCode());
        entity.setARNo(dto.getCdbNo());
        entity.setRegistrationExpiryDate(dto.getRegExpDate());
        entity.setCIDNo(dto.getCidNo());
        entity.setName(dto.getFullname());
        entity.setCmnSalutationId(dto.getSalutation());
        entity.setCmnDzongkhagId(dto.getDzongkhagId());
        entity.setGewog(dto.getGewog());
        entity.setVillage(dto.getVillage());
        entity.setCmnServiceSectorTypeId(dto.getServiceSectorType());
        entity.setCmnCountryId(dto.getCountryId());
        //entity.setTPN(dto.getTrade());//trade field is not there in db
        entity.setEmail(dto.getEmail());
        entity.setMobileNo(dto.getMobileNo());
        entity.setEmployerName(dto.getEmployeeName());
        entity.setEmployerAddress(dto.getEmployeeAddress());
        entity.setCmnQualificationId(dto.getQualificationId());
        entity.setGraduationYear(dto.getGraduationYear());
        entity.setCmnUniversityCountryId(dto.getUniversityCountry());
        entity.setNameOfUniversity(dto.getUniversityName());
        entity.setCreatedOn(new Date());
        entity.setCreatedBy(user);
        return  entity;
    }
    @Transactional
    public ArchitectDocument getDocumentDetailsByDocId(String uploadDocId) {
        ArchitectDocument dto=dao.getDocumentDetailsByDocId(uploadDocId);
        return dto;
    }

    public ResponseMessage saveArchitectcancellation(ArchitectDto dto, String userID) {
        String generateID = commonService.getRandomGeneratedId();
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            CrparchitectEntity architect = convertDtoToEntityForRenewal(dto, userID);
            architect.setId(generateID);
            architect.setCrpArchitectId(generateID);
            dao.save(architect);

            String generateID1= commonService.getRandomGeneratedId();
            ServiceEntity engineerAppliedServiceEntity = new ServiceEntity();
            engineerAppliedServiceEntity.setId(generateID1);
            engineerAppliedServiceEntity.setCmnServiceTypeId(ApplicationStatus.CANCELLATION.getCode());//Renewal of CDB Certificate
            engineerAppliedServiceEntity.setCreatedBy(userID);
            engineerAppliedServiceEntity.setEditedBy(userID);
            engineerAppliedServiceEntity.setCreatedOn(new Date());
            engineerAppliedServiceEntity.setEditedOn(new Date());
            engineerAppliedServiceEntity.setArchitectId(generateID);
            dao.saveAservies(engineerAppliedServiceEntity);

            responseMessage.setStatus(1);
            dto.setReferenceNo(new BigInteger(architect.getReferenceNo()));
            dto.setCrpArchitectId(generateID);
            responseMessage.setDto(dto);

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return responseMessage;
    }
}
