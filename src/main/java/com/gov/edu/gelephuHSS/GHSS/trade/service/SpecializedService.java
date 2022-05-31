package com.gov.edu.gelephuHSS.GHSS.trade.service;

import bt.gov.ditt.sso.client.dto.UserSessionDetailDTO;
import com.gov.edu.gelephuHSS.GHSS.common.CommonService;
import com.gov.edu.gelephuHSS.GHSS.trade.dao.SpecializedDao;
import com.gov.edu.gelephuHSS.GHSS.trade.dto.TradeDto;
import com.gov.edu.gelephuHSS.GHSS.trade.dto.TradeFeesDto;
import com.gov.edu.gelephuHSS.GHSS.trade.entity.CrpspecializedtradeEntity;
import com.gov.edu.gelephuHSS.GHSS.trade.entity.ServicespecializedtradeEntity;
import com.gov.edu.gelephuHSS.GHSS.trade.entity.SpecializedTradeCategory;
import com.gov.edu.gelephuHSS.GHSS.trade.entity.TradeDocument;
import com.gov.edu.gelephuHSS.global.enu.ApplicationStatus;
import com.gov.edu.gelephuHSS.lib.ResponseMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Created by USER on 5/5/2020.
 */
@Service
public class SpecializedService {
    @Autowired
    SpecializedDao dao;
    @Autowired
    private CommonService commonService;

    @Transactional(readOnly = true)
    public List getFeesDetals(String trade) {
        return dao.gFeeStructure(trade);
    }

    @Transactional(readOnly = true)
    public List category() {
        return dao.category();
    }

    @Transactional(readOnly = false)
    public ResponseMessage saveSpecializedTrade(TradeDto dto, UserSessionDetailDTO user) {
       String generateID = commonService.getRandomGeneratedId();
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            CrpspecializedtradeEntity specialized = convertDtoToEntity(dto, user);
            specialized.setId(generateID);
            specialized.setCrpSpecializedTradeId(generateID);
            dao.save(specialized);

            ServicespecializedtradeEntity specializedtradeserviceEntity = convertToServicespecializedtradeEntity(user);
            specializedtradeserviceEntity.setCrpSpecializedTradeId(generateID);
            dao.saveAservies(specializedtradeserviceEntity);
            responseMessage.setStatus(1);
            dto.setReferenceNo(new BigInteger(specialized.getReferenceNo()));
            dto.setCrpSpecializedTradeId(generateID);
            responseMessage.setDto(dto);

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return responseMessage;
    }
    private CrpspecializedtradeEntity convertDtoToEntity(TradeDto dto, UserSessionDetailDTO user){
        CrpspecializedtradeEntity entity=new CrpspecializedtradeEntity();
        entity.setApplicationDate(new Date());
        entity.setInitialDate(new Date());
        entity.setReferenceNo(getMaxId().toString());
        entity.setCIDNo(dto.getCidNo());
        entity.setName(dto.getFullname());
        entity.setCmnSalutationId(dto.getSalutation());
        entity.setCmnDzongkhagId(dto.getDzongkhagId());
        entity.setGewog(dto.getGewog());
        entity.setVillage(dto.getVillage());
        entity.setHasNotification("0");
        entity.setCmnServiceSectorTypeId(dto.getServiceSectorType());
        entity.setCmnCountryId(dto.getCountryId());
        //entity.setTPN(dto.getTrade());//trade field is not there in db
        entity.setCmnApplicationRegistrationStatusId(ApplicationStatus.UNDER_PROCESS.getCode());

        entity.setEmail(dto.getEmail());
        entity.setMobileNo(dto.getMobileNo());
        entity.setTelephoneNo(dto.getTelephoneNo());
        entity.setTPN(dto.getTpn());
        entity.setEmployerName(dto.getEmployeeName());
        entity.setEmployerAddress(dto.getEmployeeAddress());
        if(user==null){
            entity.setCreatedBy("000000");
        }
        else{
            entity.setCreatedBy(user.getCid());
        }
        entity.setCreatedOn(new Date());
        return  entity;
    }
    private ServicespecializedtradeEntity convertToServicespecializedtradeEntity(UserSessionDetailDTO loggedInUser) {
        String generateID = commonService.getRandomGeneratedId();
        ServicespecializedtradeEntity specializedService = new ServicespecializedtradeEntity();
        specializedService.setId(generateID);
        specializedService.setCmnServiceTypeId("55a922e1-cbbf-11e4-83fb-080027dcfac6");//new registration
        specializedService.setCreatedBy(null);
        specializedService.setEditedBy(null);
        specializedService.setCreatedOn(new Date());
        specializedService.setEditedOn(new Date());
        return specializedService;
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
                TradeDocument docdet= new TradeDocument();
                docdet.setId(generateID);
                docdet.setCrpSpecializedTradeId(referenceNo);
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
    public List getTaskList(String status,String type,String userId,String servicetype) {
        return dao.getTaskList(status, type, userId, servicetype);
    }
    public void assignMyTask(String appNo, String userID) {
        dao.assignMyTask(appNo, userID);
    }

    public List<TradeDto> getPrintList() {
        return dao.getPrintList();
    }

    @Transactional
    public TradeDocument getDocumentDetailsByDocId(String uploadDocId) {
        TradeDocument dto=dao.getDocumentDetailsByDocId(uploadDocId);
        return dto;
    }
    @Transactional
    public String saveWrkClassification(TradeDto dto, TradeFeesDto tradeDto, String loggedInUser) {
        String  status="";
        //String randomSpecializedTradeId = commonService.getRandomGeneratedId();
        String crpspecializedtradeid=dao.getCrpspecializedtradeid(dto);
        if (!CollectionUtils.isEmpty(dto.getTerms())) {
            //SpecializedTradeCategory catEntity = convertToTradeEntity(tradeDto);
            for (TradeFeesDto terms : dto.getTerms()) {
                String randomID = commonService.getRandomGeneratedId();
                SpecializedTradeCategory catEntity = convertToTradeEntity(tradeDto);
               // SpecializedTradeCategory catEntity = new SpecializedTradeCategory();
                catEntity.setAppliedCategoryId(terms.getAppliedCategoryId());
                catEntity.setId(randomID);
                catEntity.setCrpSpecializedTradeId(crpspecializedtradeid);
                catEntity.setCreatedBy(loggedInUser);
                catEntity.setEditedBy(loggedInUser);
                catEntity.setCreatedOn(new Date());
                status = dao.saveWrkClassification(catEntity);
            }
            //status = dao.saveWrkClassification(catEntity);

        }else {
            TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
        }
            return status;
    }

    @Transactional
    private SpecializedTradeCategory convertToTradeEntity(TradeFeesDto tradeDto) {
        SpecializedTradeCategory en = new SpecializedTradeCategory();
        BeanUtils.copyProperties(tradeDto, en);
        return en;
    }
    @Transactional
    public TradeDto fetchdtls(TradeDto appNo) {
        return dao.fetchdtls(appNo);
    }

    public TradeDto getTradeDetails(String appNo) {
            TradeDto dto=dao.getTradeDetails(appNo);
            if(dto.getServiceTypeId().equalsIgnoreCase("New Registration") ){
                  //generate GHSS nunber
                    String specializedTradeNo=dao.generatespecializedTradeNo(dto);
                    dto.setCdbNo(specializedTradeNo);
            }
            List<TradeDocument> doc=dao.getdocumentList(dto.getCrpSpecializedTradeId());
        dto.setDoc(doc);
            return dto;
        }

    public TradeDto updateVerification(TradeDto dto, String userID, HttpServletRequest request) {
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

    public TradeDto updateReject(TradeDto dto, String userID, HttpServletRequest request) {
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

    public TradeDto approveSpTradeRegistration(TradeDto dto, String userID, HttpServletRequest request) {
       TradeDto dto1=dao.getspcializedTradeapplicationdetails(dto);
        if(dto.getServiceTypeId().equalsIgnoreCase("cancel")){
            //update cancellation
            dto1.setCdbNo(dto.getCdbNo());
            dto1.setRemarks(dto.getRemarks());
            //dao.updateCancellationDetails(dto1, userID);
            dto.setUpdateStatus("Success");
        }
        else {
           // if(dto.getServiceSectorType().equalsIgnoreCase("Goverment")){
                dto1.setRemarks(dto.getRemarks());
                String insert="";
                if(dto.getServiceTypeId().equalsIgnoreCase("registration")){
                    insert=dao.insertuserDetails(dto1, userID, request);
                    if(!insert.equalsIgnoreCase("Insert_Fail")){
                        dto1.setCdbNo(dto.getCdbNo());
                        dto1.setRemarks(dto.getRemarks());
                        String password=insert.split("/")[1];
                        insert=dao.insertspTradeFinalDetails(dto1, userID, insert.split("/")[0]);
                    }
                }
              /*  else if(dto.getServiceTypeId().equalsIgnoreCase("renewal")){
                    dto1.setCdbNo(dto.getCdbNo());
                    insert=dao.updateRenewalDetails(dto1, userID, 5);
                }*/
                if(insert.equalsIgnoreCase("Success")){
                    dto= dao.approveSpTradeRegistration(dto, userID,request);//will update application table to for both government and private architect
                    dto.setUpdateStatus("Success");
                    //send notification
                }
          //  }
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
}