package com.gov.edu.gelephuHSS.GHSS.engineer;

import com.gov.edu.gelephuHSS.base.BaseService;
import com.gov.edu.gelephuHSS.GHSS.admin.dto.TasksDTO;
import com.gov.edu.gelephuHSS.GHSS.common.CommonService;
import com.gov.edu.gelephuHSS.GHSS.engineer.dto.EngineerDTO;
import com.gov.edu.gelephuHSS.GHSS.engineer.model.Engineer;
import com.gov.edu.gelephuHSS.GHSS.engineer.model.EngineerAppliedServiceEntity;
import com.gov.edu.gelephuHSS.lib.ResponseMessage;
import com.gov.edu.gelephuHSS.GHSS.engineer.model.EngineerAttachment;
import com.gov.edu.gelephuHSS.lib.LoggedInUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;


/**
 * Created by Dechen  Wangdi on 12/17/2019.
 */

@Service
public class EngineerService extends BaseService {

    /**
     * Size of a byte buffer to read/write file
     */
    private static final int BUFFER_SIZE = 4096;
    private String UPLOAD_LOC = "Engineer";

    /**
     * Path of the file to be downloaded, relative to application's directory
     */
    private String filePath = "/downloads/SpringProject.zip";

    @Autowired
    private CommonService commonService;

    @Autowired
    private EngineerDao engineerDao;

    //region public methods.
    @Transactional(readOnly = false)
    public ResponseMessage save(EngineerDTO engineerDTO, LoggedInUser loggedInUser) {
        String generateID = commonService.getRandomGeneratedId();

        try {
            Engineer engineer = convertEngineerToEntity(engineerDTO, loggedInUser);
            engineer.setId(generateID);
            engineer.setEngineerId(generateID);
            engineerDao.save(engineer);

            //To save service engineer applied.
            EngineerAppliedServiceEntity serviceEntity = convertEngineerToServiceEntity(loggedInUser);
            serviceEntity.setEngineerId(generateID);
            serviceEntity.setCmnServiceTypeId(engineerDTO.getServiceTypeId());
            engineerDao.saveSEA(serviceEntity);

            //To save engineer's attachment document.
            EngineerAttachment engineerAttachment = convertEngineerToEngineerAttachment(engineerDTO.getAttachmentFile(),
                    loggedInUser);
            engineerAttachment.setEngineerId(generateID);
            engineerDao.saveA(engineerAttachment);

        } catch (Exception ex) {
            responseMessage.setStatus(UNSUCCESSFUL_STATUS);
            responseMessage.setText("Could not save successfully.");
            return responseMessage;
        }

        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Data saved successfully.");
        return responseMessage;
    }

    public List getEngineerList(String applicationNo) {
        return engineerDao.getEngineerList(applicationNo);
    }
    //endregion

    //region private methods.
    private Engineer convertEngineerToEntity(EngineerDTO engineerDTO, LoggedInUser loggedInUser) {
        Engineer engineer = new Engineer();
        engineer.setReferenceNo(getMaxId());
        engineer.setInitialDate(new Date());//currentUser.
        engineer.setApplicationDate(new Date());//currentUser.
        engineer.setCDBNO(BigInteger.ONE);//TODO to change.
        engineer.setCmnServiceSectorTypeId(engineerDTO.getServiceSectorType());
        engineer.setTradeId(engineerDTO.getTrade());
        engineer.setCID(engineerDTO.getCid());
        engineer.setSalutationId(engineerDTO.getSalutation());
        engineer.setName(engineerDTO.getName());
        engineer.setCountryId(engineerDTO.getCountryId());
        engineer.setDzongkhagId(engineerDTO.getDzongkhagId());
        engineer.setGewog(engineerDTO.getGewog());
        engineer.setVillage(engineerDTO.getVillage());
        engineer.setEmail(engineerDTO.getEmail());
        engineer.setMobileNo(engineerDTO.getMobileNo());
        engineer.setEmployeeName(engineerDTO.getEmployeeName());
        engineer.setEmployeeAddress(engineerDTO.getEmployeeAddress());
        engineer.setTPN(engineerDTO.getTPN());
        engineer.setQualification(engineerDTO.getQualificationId());
        engineer.setGraduationYear(engineerDTO.getGraduationYear());
        engineer.setUniversityName(engineerDTO.getUniversityName());
        engineer.setUniversityCountry(engineerDTO.getUniversityCountry());
        engineer.setRegistrationStatus(engineerDTO.getRegistrationStatus());
        engineer.setCmnApplicationRegistrationStatusId(engineerDTO.getApplicationRegistrationStatusId());//TODO nee save 
        engineer.setDeregisteredBlacklistedRemarks(engineerDTO.getDeregisteredBlacklistedRemarks());
        engineer.setSysLockedByUserId(engineerDTO.getSysLockedByUserId());
//        engineer.setHasNotification(engineerDTO.getHasNotification());
        engineer.setHasNotification(1);
//        engineer.setWaiveOffLateFee(engineerDTO.getWaiveOffLateFee());
        engineer.setWaiveOffLateFee(1);
        engineer.setNewLateFeeAmount(engineerDTO.getNewLateFeeAmount());
        engineer.setDocumentName(engineerDTO.getAttachmentFile().getOriginalFilename());
        engineer.setCreatedBy(loggedInUser.getUserID());
        engineer.setCreatedOn(loggedInUser.getServerDate());
        engineer.setEditedOn(loggedInUser.getServerDate());
        engineer.setEditedBy(loggedInUser.getUserID());
        return engineer;
    }

    private BigInteger getMaxId() {
        BigInteger referenceNo = engineerDao.getMaxId();
        return (referenceNo.intValue() > 0) ? BigInteger.ONE : referenceNo.add(BigInteger.ONE);
    }

    private EngineerAppliedServiceEntity convertEngineerToServiceEntity(LoggedInUser loggedInUser) {
        String generateID = commonService.getRandomGeneratedId();
        EngineerAppliedServiceEntity engineerAppliedServiceEntity = new EngineerAppliedServiceEntity();
        engineerAppliedServiceEntity.setId(generateID);
        engineerAppliedServiceEntity.setCreatedBy(loggedInUser.getUserID());
        engineerAppliedServiceEntity.setEditedBy(loggedInUser.getUserID());
        engineerAppliedServiceEntity.setCreatedOn(loggedInUser.getServerDate());
        engineerAppliedServiceEntity.setEditedOn(loggedInUser.getServerDate());
        return engineerAppliedServiceEntity;
    }

    private EngineerAttachment convertEngineerToEngineerAttachment(MultipartFile document,
                                                                   LoggedInUser loggedInUser) throws Exception {
        String docPath = commonService.uploadDocument(document,UPLOAD_LOC,document.getOriginalFilename());
        EngineerAttachment engineerAttachment = new EngineerAttachment();
        String generatedID = commonService.getRandomGeneratedId();
        engineerAttachment.setId(generatedID);
        engineerAttachment.setDocumentName(document.getOriginalFilename());
        engineerAttachment.setDocumentPath(docPath);
        engineerAttachment.setFileType(document.getContentType());
        engineerAttachment.setCreatedBy(loggedInUser.getUserID());
        engineerAttachment.setEditedBy(loggedInUser.getUserID());
        engineerAttachment.setCreatedOn(loggedInUser.getServerDate());
        engineerAttachment.setEditedOn(loggedInUser.getServerDate());
        return engineerAttachment;
    }

    public List getFeeStructure(String category) {
        return engineerDao.getFeeStructure(category);
    }

    public ResponseMessage verifyApplication(Integer applicationNo, LoggedInUser loggedInUser, String remark) {
        engineerDao.verifyApplication(applicationNo, loggedInUser, remark);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Application is verified successfully.");
        return responseMessage;
    }

    public ResponseMessage approveApplication(Integer applicationNo, LoggedInUser loggedInUser, String remark) {
        engineerDao.approveApplication(applicationNo, loggedInUser, remark);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Application is approved successfully.");
        return responseMessage;
    }

    public ResponseMessage rejectApplication(Integer applicationNo, LoggedInUser loggedInUser, String remark) {
        String rejectionCode = commonService.getRandomGeneratedId();
        engineerDao.rejectApplication(applicationNo, loggedInUser, remark, rejectionCode);
        responseMessage.setStatus(SUCCESSFUL_STATUS);
        responseMessage.setText("Application is reject successfully.");
        return responseMessage;
    }

    public ResponseMessage download(HttpServletRequest request, HttpServletResponse response, String documentPath) throws IOException {

        //Authorized user will download the file

        // get absolute path of the application
        /*ServletContext context = request.getContextPath();
        String appPath = context.getRealPath("");
        System.out.println("appPath = " + appPath);

        // construct the complete absolute path of the file
        String fullPath = appPath + filePath;
        File downloadFile = new File(fullPath);
        FileInputStream inputStream = new FileInputStream(downloadFile);

        // get MIME type of the file
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);

        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        // get output stream of the response
        OutputStream outStream = response.getOutputStream();

        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;

        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outStream.close();*/

        return null;
    }

    public List<TasksDTO> getTaskList() {
        return engineerDao.getTaskList();
    }
    //endregion
}
