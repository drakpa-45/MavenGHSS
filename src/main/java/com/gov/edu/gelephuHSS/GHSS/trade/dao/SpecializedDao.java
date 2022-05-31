package com.gov.edu.gelephuHSS.GHSS.trade.dao;

import com.gov.edu.gelephuHSS.base.BaseDao;
import com.gov.edu.gelephuHSS.GHSS.architect.dto.ArchitectFeesDto;
import com.gov.edu.gelephuHSS.GHSS.trade.dto.TradeDto;
import com.gov.edu.gelephuHSS.GHSS.trade.entity.SpecializedTradeCategory;
import com.gov.edu.gelephuHSS.GHSS.trade.entity.TradeDocument;
import com.gov.edu.gelephuHSS.global.enu.ApplicationStatus;
import com.gov.edu.gelephuHSS.GHSS.admin.dto.TasksDTO;
import com.gov.edu.gelephuHSS.GHSS.architect.dto.ArchitectDto;
import com.gov.edu.gelephuHSS.GHSS.trade.dto.TradeFeesDto;
import com.gov.edu.gelephuHSS.GHSS.trade.entity.CrpspecializedtradeEntity;
import com.gov.edu.gelephuHSS.GHSS.trade.entity.ServicespecializedtradeEntity;
import org.hibernate.query.Query;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by USER on 5/5/2020.
 */
@Repository
public class SpecializedDao extends BaseDao {

    public List gFeeStructure(String trade) {
        sqlQuery = properties.getProperty("SpecializedDao.gFeeStructure");
        return hibernateQuery(sqlQuery, TradeFeesDto.class).list();
    }
    @Transactional(readOnly = true)
    public List category() {
        List<TradeFeesDto> dtoList=new ArrayList<>();
        sqlQuery = properties.getProperty("SpecializedDao.category");
         dtoList=hibernateQuery(sqlQuery, TradeFeesDto.class).list();
      /*  for(int i=0;i<dtoList.size();i++){
            dtoList.get(i).setArrayId(i);
        }*/
        return dtoList;
    }

    @Transactional
    public BigInteger getMaxId() {
        sqlQuery = properties.getProperty("SpecializedDao.getMaxId");
        return (BigInteger) hibernateQuery(sqlQuery).uniqueResult();
    }
    @Transactional
    public void save(CrpspecializedtradeEntity specialized) {
        saveOrUpdate(specialized);
    }

    @Transactional
    public void saveAservies(ServicespecializedtradeEntity specializedtradeserviceEntity) {
        saveOrUpdate(specializedtradeserviceEntity);
    }
    @Transactional
    public void saveAttchment(TradeDocument spTradeAttachment) {
        saveOrUpdate(spTradeAttachment);
    }

    @Transactional(readOnly = true)
    public List getTaskList(String status,String type,String userId,String servicetype) {
        if(type=="Group"){
            sqlQuery = properties.getProperty("SpecializedDao.getTaskList");
            return hibernateQuery(sqlQuery, TasksDTO.class).setParameter(1, servicetype).setParameter(2, status).list();
        }
        else{
            sqlQuery = properties.getProperty("SpecializedDao.getMyTaskList");
            return hibernateQuery(sqlQuery, TasksDTO.class).setParameter(1, userId).setParameter(2, servicetype).setParameter(3, status).list();
        }

    }

    @Transactional(readOnly = false)
    public void assignMyTask(String appNo, String lockUserId) {
        sqlQuery = properties.getProperty("SpecializedDao.send2MyOrGroupTask");
        hibernateQuery(sqlQuery).setParameter("appNo", appNo) .setParameter("lockUserId", lockUserId).executeUpdate();

    }

    @Transactional(readOnly = false)
    public ArchitectDto getArchitetDetails(String appNo) {
        ArchitectDto dto =new ArchitectDto();
        sqlQuery = properties.getProperty("ArchitectDao.getArchiectDtls");
        dto=(ArchitectDto) hibernateQuery(sqlQuery, ArchitectDto.class).setParameter(1, appNo).list().get(0);
        return dto;
    }

    @Transactional(readOnly = false)
    public List<TradeDocument> getdocumentList(String crptradeId) {
        List<TradeDocument> doclist=new ArrayList<TradeDocument>();
        sqlQuery = properties.getProperty("SpecializedDao.getTradeDoc");
        doclist= hibernateQuery(sqlQuery, TradeDocument.class).setParameter(1, crptradeId).list();
        return doclist;
    }

    @Transactional
    public TradeDto updateReject(TradeDto dto, String userID, HttpServletRequest request) {
        try {
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crpspecializedtrade SET RemarksByRejector=?,RejectedDate= CURRENT_TIMESTAMP,SysRejectorUserId=?,CmnApplicationRegistrationStatusId=? WHERE ReferenceNo =?");
            query1.setParameter(1, dto.getRemarks()).setParameter(2, userID).setParameter(3, ApplicationStatus.REJECTED.getCode()).setParameter(4, dto.getReferenceNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                dto.setUpdateStatus("Success");
            }
        } catch (Exception e) {
            System.out.print("Exception in SpecializedTradeDao # updateReject: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public TradeDto updateVerification(TradeDto dto, String userID, HttpServletRequest request) {
        try {
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crpspecializedtrade SET RemarksByVerifier=?,VerifiedDate= CURRENT_TIMESTAMP,SysVerifierUserId=?,CmnApplicationRegistrationStatusId=?, SysLockedByUserId=? WHERE ReferenceNo =?");
            query1.setParameter(1, dto.getRemarks()).setParameter(2, userID).setParameter(3, ApplicationStatus.VERIFIED.getCode()).setParameter(4, null).setParameter(5, dto.getReferenceNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                dto.setUpdateStatus("Success");
            }
        } catch (Exception e) {
            System.out.print("Exception in SpecializedTradeDao # updateVerification: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public ArchitectDto updateApplicationForPayment(ArchitectDto dto, String userID, HttpServletRequest request) {
        try {
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crparchitect SET RemarksByPaymentApprover=?,PaymentApprovedDate= CURRENT_TIMESTAMP,SysPaymentApproverUserId=?,CmnApplicationRegistrationStatusId=?, SysLockedByUserId=?,PaymentReceiptNo=?,PaymentReceiptDate=? WHERE ReferenceNo =? ");
            query1.setParameter(1, dto.getRemarks()).setParameter(2, userID).setParameter(3, ApplicationStatus.APPROVED.getCode()).setParameter(4, null).setParameter(5, dto.getPaymentReceiptNo()).setParameter(6, dto.getPaymentReceiptDate()).setParameter(7, dto.getReferenceNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                dto.setUpdateStatus("Success");
            }
        } catch (Exception e) {
            System.out.print("Exception in ArchitectDao # approveArchitectRegistration: " + e);
            e.printStackTrace();
        }
        return dto;
    }
    @Transactional
    public String insertInPaymentDetails(ArchitectDto dto, String userID, HttpServletRequest request) {
        String retval="";
        try {
            org.hibernate.query.Query query1 = sqlQuery("INSERT INTO crparchitectregistrationpayment (Id,CrpArchitectFinalId,Amount,CreatedBy,CreatedOn,Mode_Of_Payment) VALUES(?,(SELECT Id FROM crparchitectfinal WHERE  ReferenceNo =?),?,?,CURRENT_TIMESTAMP,?) ");
            query1.setParameter(1, UUID.randomUUID().toString()).setParameter(2, dto.getReferenceNo()).setParameter(3, dto.getTotalAmt()).setParameter(4, userID).setParameter(5, dto.getPaymentmode());
            int save = query1.executeUpdate();
            if (save > 0) {
                retval= "Success";
            }
        } catch (Exception e) {
            System.out.print("Exception in ArchitectDao # insertInPaymentDetails: " + e);
            e.printStackTrace();
        }
        return retval;
    }

    @Transactional
    public String insertuserDetails(TradeDto dto, String userID, HttpServletRequest request) {
        String return_value="Insert_Fail";
        try {
            String generateID = UUID.randomUUID().toString();
            String digit="1234567890";
            StringBuilder salt=new StringBuilder();
            Random rnd=new Random();
            while (salt.length()<4){
                int index=(int) (rnd.nextFloat() * digit.length());
                salt.append(digit.charAt(index));
            }
            String saltString=salt.toString();
            String pw_hash= BCrypt.hashpw(saltString, BCrypt.gensalt());

            org.hibernate.query.Query query1 = sqlQuery("INSERT INTO sysuser (ContactNo,CreatedBy,CreatedOn,Email,FullName,Id,PASSWORD,STATUS,username) VALUES(?,?,CURRENT_TIMESTAMP,?,?,?,?,?,?) ");
            query1.setParameter(1, dto.getMobileNo()).setParameter(2, userID).setParameter(3, dto.getEmail()).setParameter(4, dto.getFullname()).setParameter(5, generateID).setParameter(6, pw_hash).setParameter(7, "1").setParameter(8, dto.getEmail());
            int save = query1.executeUpdate();
            if (save > 0) {
                System.out.print("Password: "+saltString+"("+pw_hash+") is generated against user:"+dto.getEmail());
                return_value= generateID+"/"+saltString;
            }

        } catch (Exception e) {
            System.out.print("Exception in SpecializedTradeDao # insertuserDetails: " + e);
            e.printStackTrace();
        }
        return return_value;
    }

    @Transactional
    public List<TradeDto> getPrintList() {
        sqlQuery = properties.getProperty("SpecializedDao.getPrintList");
        return hibernateQuery(sqlQuery, TradeDto.class).setParameter(1, ApplicationStatus.APPROVED_FOR_PAYMENT.getCode()).list();
    }

    @Transactional
    public ArchitectDto populateApplicantDetails(String cdbNo) {
        ArchitectDto dto = new ArchitectDto();
        try {
            sqlQuery="SELECT f.`ARNo` cdbNo,f.`Id` CrpArchitectId,f.`CIDNo` cidNo,f.`Name` fullname,f.`Village` village,f.`Gewog` gewog,d.`NameEn` dzongkhagId,c.`Name` countryId,s.`Name` salutation,\n" +
                    "i.`Name` updateStatus,f.`Email` email,f.`MobileNo` mobileNo,f.`EmployerName` employeeName,f.`EmployerAddress` employeeAddress,q.`Name` qualificationId,\n" +
                    "f.`GraduationYear` graduationyr,t.`Name` serviceSectorType,f.`NameOfUniversity` universityName,uc.`Name` universityCountry,f.`RegistrationApprovedDate` registrationApproveDate,f.`RegistrationExpiryDate` regExpDate\n" +
                    "FROM crparchitectfinal f \n" +
                    "LEFT JOIN cmnlistitem i ON i.`Id`=f.`CmnApplicationRegistrationStatusId` \n" +
                    "LEFT JOIN cmnlistitem s ON s.`Id`=f.`CmnSalutationId`\n" +
                    "LEFT JOIN cmncountry c ON c.`Id`=f.`CmnCountryId`\n" +
                    "LEFT JOIN cmncountry uc ON uc.`Id`=f.`CmnUniversityCountryId`\n" +
                    "LEFT JOIN cmnlistitem q ON q.`Id`=f.`CmnQualificationId` LEFT JOIN cmnlistitem t ON t.`Id`=f.`CmnServiceSectorTypeId` \n" +
                    "LEFT JOIN cmndzongkhag d ON d.`Id`=f.`CmnDzongkhagId` WHERE f.`ARNo`=? ";
            dto=(ArchitectDto) hibernateQuery(sqlQuery, ArchitectDto.class).setParameter(1, cdbNo).list().get(0);
        } catch (Exception e) {
            System.out.print("Exception in ArchitectDao # populateApplicantDetails: " + e);
            e.printStackTrace();
        }
        return dto;

    }

    @Transactional
    public ArchitectDto checkOngoingApplication(String cdbNo) {
        ArchitectDto dto = new ArchitectDto();
        try {
            sqlQuery="SELECT f.`ARNo` cdbNo,i.`Name` updateStatus,f.`ApplicationDate` applicationDate,f.`ReferenceNo` ReferenceNo FROM crparchitect f LEFT JOIN cmnlistitem i ON i.`Id`=f.`CmnApplicationRegistrationStatusId` WHERE i.`Id` IN ('262a3f11-adbd-11e4-99d7-080027dcfac6','36f9627a-adbd-11e4-99d7-080027dcfac6','6195664d-c3c5-11e4-af9f-080027dcfac6') AND f.`ARNo`=? ";
            //dto=(ArchitectDto) hibernateQuery(sqlQuery, ArchitectDto.class).setParameter(1, cdbNo).list();
            Query queryre = sqlQuery(sqlQuery, ArchitectDto.class).setParameter(1, cdbNo);
            if(queryre.list().size()>0){
                dto=(ArchitectDto) queryre.list().get(0);
            }
        } catch (Exception e) {
            System.out.print("Exception in ArchitectDao # checkOngoingApplication: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public List<ArchitectFeesDto> getundertaking(String type) {
        List<ArchitectFeesDto> dto = new ArrayList<ArchitectFeesDto>();
        try {
            sqlQuery="SELECT u.`Details` name FROM `cmnundertaking` u WHERE u.`Type`=? ";
            Query queryre = sqlQuery(sqlQuery, ArchitectFeesDto.class).setParameter(1, type);
            if(queryre.list().size()>0){
                dto=queryre.list();
            }
        } catch (Exception e) {
            System.out.print("Exception in ArchitectDao # checkOngoingApplication: " + e);
            e.printStackTrace();
        }
        return dto;
    }

    public TradeDocument getDocumentDetailsByDocId(String uploadDocId) {
        TradeDocument dto=new TradeDocument();
        try {
            String GET_DOCUMENT_DTLS_BY_UUID="SELECT a.`DocumentName` documentName,a.`DocumentPath` documentPath,a.`CrpSpecializedTradeId` crpSpecializedTradeId,a.`FileType` fileType FROM crpspecializedtradeattachment a WHERE a.`Id`=? ";
            Query query = sqlQuery(GET_DOCUMENT_DTLS_BY_UUID,TradeDocument.class).setParameter(1, uploadDocId);
            dto = (TradeDocument) query.list().get(0);
        }catch(Exception e){
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public String updateRenewalDetails(ArchitectDto dto1, String userID, int interval) {
        String return_value="";
        try {
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crparchitectfinal s SET s.`RegistrationExpiryDate`=DATE_ADD(CURRENT_DATE, INTERVAL ? YEAR),s.`EditedBy`=?,s.`EditedOn`=CURRENT_TIMESTAMP,s.`ReRegistrationRemarks`=?,s.`ReRegistrationDate`=CURRENT_DATE WHERE s.`ARNo`=? ");
            query1.setParameter(1, interval).setParameter(2, userID).setParameter(3, dto1.getRemarks()).setParameter(4,  dto1.getCdbNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                return_value="Success";
            }
        } catch (Exception e) {
            System.out.print("Exception in ArchitectDao # approveArchitectRegistration: " + e);
            e.printStackTrace();
        }
        return return_value;
    }

    @Transactional
    public String insertInPaymentServiceDetails(ArchitectDto dto, String userID, HttpServletRequest request) {
        String retval="";
        try {
            org.hibernate.query.Query query1 = sqlQuery("INSERT INTO crparchitectservicepayment (Id,CrpArchitectId,CmnServiceTypeId,NoOfDaysLate,NoOfDaysAfterGracePeriod,PenaltyPerDay,PaymentAmount,TotalAmount,CreatedBy,CreatedOn,Mode_Of_Payment) VALUES(?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP,?) ");
            query1.setParameter(1, UUID.randomUUID().toString()).setParameter(2, dto.getCrpArchitectId()).setParameter(3, dto.getServiceTypeId()).setParameter(4, dto.getNoOfDaysLate()).setParameter(5, dto.getNoOfDaysAfterGracePeriod()).setParameter(6, "100").setParameter(7, dto.getPaymentAmt()).setParameter(8, dto.getTotalAmt()).setParameter(9, userID).setParameter(10, dto.getPaymentmode());
            int save = query1.executeUpdate();
            if (save > 0) {
                retval= "Success";
            }
        } catch (Exception e) {
            System.out.print("Exception in ArchitectDao # insertInPaymentDetails: " + e);
            e.printStackTrace();
        }
        return retval;
    }

    @Transactional
    public String updateCancellationDetails(ArchitectDto dto1, String userID) {
        String return_value="";
        try {
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crparchitectfinal s SET s.`CmnApplicationRegistrationStatusId`=?,s.`EditedBy`=?,s.`EditedOn`=CURRENT_TIMESTAMP,s.`DeregisteredRemarks`=?,s.`DeRegisteredDate`=CURRENT_DATE WHERE s.`ARNo`=? ");
            query1.setParameter(1, ApplicationStatus.DEREGISTERED.getCode()).setParameter(2, userID).setParameter(3, dto1.getRemarks()).setParameter(4,  dto1.getCdbNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                return_value="Success";
            }
        } catch (Exception e) {
            System.out.print("Exception in ArchitectDao # approveArchitectRegistration: " + e);
            e.printStackTrace();
        }
        return return_value;
    }

    public String saveWrkClassification(SpecializedTradeCategory catEntity) {
       try{
           saveOrUpdate(catEntity);
           return "success";
       }catch (Exception e){
           System.out.print("exception while inserting into workflow classification"+e);
       }
        return "fail";
    }

    public TradeDto fetchdtls(TradeDto appNo) {
        TradeDto dtls=new TradeDto();
        try {
            sqlQuery = properties.getProperty("SpecializedDao.getDtls");
            dtls=(TradeDto) hibernateQuery(sqlQuery, TradeDto.class).setParameter(1, appNo.getReferenceNo()).list().get(0);
        } catch (Exception e) {
            System.out.print("Exception in specializedDao # getSpecialzedTradeDtls: " + e);
            e.printStackTrace();
        }
        return dtls;
    }

    @Transactional(readOnly = false)
    public TradeDto getTradeDetails(String appNo) {
        TradeDto dto =new TradeDto();
            sqlQuery = properties.getProperty("SpecializedDao.getSpecializedradeDtls");
            dto=(TradeDto) hibernateQuery(sqlQuery, TradeDto.class).setParameter(1, appNo).list().get(0);
            return dto;
        }
    @Transactional
    public TradeDto approveSpTradeRegistration(TradeDto dto, String userID, HttpServletRequest request) {
        try {
            int validity_year=3;
            String applicationstatus="";
           /* if(dto.getServiceTypeId().equalsIgnoreCase("cancel")){
                applicationstatus=ApplicationStatus.APPROVED.getCode();
            }
            else if(dto.getServiceSectorType().equalsIgnoreCase("Goverment")){
                validity_year=5;
                applicationstatus=ApplicationStatus.APPROVED.getCode();
            }
            else{*/
                applicationstatus=ApplicationStatus.APPROVED_FOR_PAYMENT.getCode();
            //}
            org.hibernate.query.Query query1 = sqlQuery("UPDATE crpspecializedtrade SET RemarksByApprover=?,RegistrationApprovedDate= CURRENT_TIMESTAMP,RegistrationApprovedDate=CURRENT_TIMESTAMP,RegistrationExpiryDate=DATE_ADD(CURRENT_DATE, INTERVAL "+validity_year+" YEAR),SysApproverUserId=?,CmnApplicationRegistrationStatusId=?, SysLockedByUserId=? WHERE ReferenceNo =?");
            query1.setParameter(1, dto.getRemarks()).setParameter(2, userID).setParameter(3, applicationstatus).setParameter(4, null).setParameter(5, dto.getReferenceNo());
            int save = query1.executeUpdate();
            if (save > 0) {
                dto.setUpdateStatus("Success");
            }
        } catch (Exception e) {
            System.out.print("Exception in specializedDao # approveSpTradeRegistration: " + e);
            e.printStackTrace();
        }
        return dto;
    }
    @Transactional
    public TradeDto getspcializedTradeapplicationdetails(TradeDto dto) {
        try {
            sqlQuery = properties.getProperty("SpecializedDao.getspcializedTradeDetails");
            dto=(TradeDto) hibernateQuery(sqlQuery, TradeDto.class).setParameter(1, dto.getReferenceNo()).list().get(0);
        } catch (Exception e) {
            System.out.print("Exception in SpecializedDao # getspcializedTradeapplicationdetails: " + e);
            e.printStackTrace();
        }
        return dto;
    }
    @Transactional
    public String generatespecializedTradeNo(TradeDto dto) {
        String spTradeNo="", selectquery="";
        String firstpart="";
        try {
                firstpart="SP-";
                    selectquery="SELECT MAX(SPNo) cdbNo FROM crpspecializedtradefinal";
            String curr_specializedTradeNo=  (String) hibernateQuery(selectquery).list().get(0);
            if(curr_specializedTradeNo==null){
                spTradeNo=firstpart+"001";
            }
            else{
                int num=Integer.parseInt(curr_specializedTradeNo.replaceAll("\\D+",""));
                num++;
                if(String.valueOf(num).length()==1){
                    spTradeNo=firstpart+"00"+num;
                }
                else if(String.valueOf(num).length()==2){
                    spTradeNo=firstpart+"0"+num;
                }
                else{
                    spTradeNo=firstpart+num;
                }
            }

        } catch (Exception e) {
            System.out.print("Exception in SpecializedDao # generatespecializedTradeNo: " + e);
            e.printStackTrace();
        }
        return spTradeNo;
    }
    @Transactional
    public String insertspTradeFinalDetails(TradeDto dto, String userID, String sysuserId) {
        String return_value="";
        String finalId = UUID.randomUUID().toString();
        try {
            org.hibernate.query.Query query1 = sqlQuery("INSERT INTO crpspecializedtradefinal\n" +
                    "(SysUserId,ReferenceNo,ApplicationDate,SPNo,CmnSalutationId,NAME,CmnDzongkhagId,Gewog,Village,Email,MobileNo,TelephoneNo,EmployerName,EmployerAddress,\n" +
                    "TPN,CmnApplicationRegistrationStatusId,RegistrationApprovedDate,RegistrationExpiryDate,CreatedBy,CIDNo,Id,InitialDate,CreatedOn,EditedOn)\n" +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_DATE,CURRENT_DATE,CURRENT_TIMESTAMP)");
            query1.setParameter(1,sysuserId).setParameter(2, dto.getReferenceNo()).setParameter(3, dto.getApplicationDate()).setParameter(4, dto.getCdbNo()).setParameter(5, dto.getSalutation()).setParameter(6, dto.getFullname()).setParameter(7, dto.getDzongkhagId()).setParameter(8, dto.getGewog()).setParameter(9, dto.getVillage())
                    .setParameter(10, dto.getEmail()).setParameter(11, dto.getMobileNo()).setParameter(12, dto.getTelephoneNo()).setParameter(13, dto.getEmployeeName()).setParameter(14, dto.getEmployeeAddress()).setParameter(15, dto.getTpn()).setParameter(16,ApplicationStatus.APPROVED_FOR_PAYMENT.getCode()).setParameter(17, dto.getRegistrationApproveDate()).setParameter(18, dto.getApplicationDate()).setParameter(19, userID)
                    .setParameter(20, dto.getCidNo()).setParameter(21,finalId);
            int save = query1.executeUpdate();
            if(save>0) {
                org.hibernate.query.Query querydoc = sqlQuery("INSERT INTO `crpspecializedtradeattachmentfinal` (`Id`,`CrpSpecializedTradeFinalId`,`DocumentName`,`DocumentPath`,`FileType`,`CreatedBy`,`CreatedOn`) SELECT a.`Id`,a.`CrpSpecializedTradeId`,a.`DocumentName`,a.`DocumentPath`,a.`FileType`,?,CURRENT_TIMESTAMP FROM `crpspecializedtradeattachment` a  LEFT JOIN `crpspecializedtrade` ar ON a.`CrpSpecializedTradeId`=ar.`Id` WHERE ar.`ReferenceNo`=? ");
                querydoc.setParameter(1, userID).setParameter(2, dto.getReferenceNo());
                save = querydoc.executeUpdate();
                if(save>0) {
                    return_value = "Success";
                }else{
                    return_value="fail";
                }
            }else{
                System.out.print("exception while inserting to crpspecializedtradeattachmentfinal");
                return_value = "Fail";
            }
            if(save>0){
                org.hibernate.query.Query querydoc = sqlQuery("\n" +
                        "INSERT INTO crpspecializedtradeworkclassificationfinal(Id,CrpSpecializedTradeFinalId,CmnAppliedCategoryId,CmnVerifiedCategoryId,CmnApprovedCategoryId,CreatedBy,EditedBy,CreatedOn,EditedOn)SELECT a.Id,a.CrpSpecializedTradeId,a.CmnAppliedCategoryId,a.CmnVerifiedCategoryId,a.CmnApprovedCategoryId,?,a.EditedBy,a.CreatedOn,CURRENT_TIMESTAMP FROM crpspecializedtradeworkclassification a LEFT JOIN crpspecializedtrade c ON c.CrpSpecializedTradeId=a.CrpSpecializedTradeId WHERE c.`ReferenceNo`=? ");
                querydoc.setParameter(1, userID).setParameter(2, dto.getReferenceNo());
                save = querydoc.executeUpdate();
                if(save>0) {
                    return_value = "Success";
                }else{
                    return_value="fail";
                }
            }else{
                System.out.print("exception while inserting to crpspecializedtradeworkclassificationfinal");
                return_value = "Fail";
            }
        } catch (Exception e) {
            System.out.print("Exception in SpecializedDao # insertspTradeFinalDetails: " + e);
            e.printStackTrace();
        }
        return return_value;
    }

    public String getCrpspecializedtradeid(TradeDto dto) {
        org.hibernate.query.Query sql=sqlQuery("SELECT c.CrpSpecializedTradeId FROM crpspecializedtrade c WHERE c.ReferenceNo=?");
        return (String) sql.setParameter(1,dto.getReferenceNo()).list().get(0);
    }
}
