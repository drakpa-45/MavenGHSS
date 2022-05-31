package com.gov.edu.gelephuHSS.GHSS.common;

import com.gov.edu.gelephuHSS.auth.LoginDTO;
import com.gov.edu.gelephuHSS.base.BaseDao;
import com.gov.edu.gelephuHSS.GHSS.common.dto.FileDetailDTO;
import com.gov.edu.gelephuHSS.commonDto.TasklistDto;
import com.gov.edu.gelephuHSS.global.enu.ApplicationStatus;
import com.gov.edu.gelephuHSS.lib.DropdownDTO;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * ==================================================================================
 * Created by user on 9/29/2019.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
@Repository
public class CommonDao extends BaseDao {
    @Transactional(readOnly = true)
    public List gCountryList() {
        sqlQuery = properties.getProperty("CommonDao.gCountryList");
        hQuery = hibernateQuery(sqlQuery, DropdownDTO.class);
        return hQuery.list();
    }

    @Transactional(readOnly = true)
    public List gDzongkhagList() {
        sqlQuery = properties.getProperty("CommonDao.gDzongkhagList");
        hQuery = hibernateQuery(sqlQuery, DropdownDTO.class);
        return hQuery.list();
    }

    @Transactional(readOnly = true)
    public List gCmnListItem(String cmnListId) {
        sqlQuery = properties.getProperty("CommonDao.gCmnListItem");
        hQuery = hibernateQuery(sqlQuery, DropdownDTO.class).setParameter("cmnListId", cmnListId);
        return hQuery.list();
    }

    @Transactional(readOnly = true)
    public List getGewogList(String dzongkhagId) {
        sqlQuery = properties.getProperty("CommonDao.getGewogList");
        hQuery = hibernateQuery(sqlQuery, DropdownDTO.class).setParameter("dzongkhagId",dzongkhagId);
        return hQuery.list();
    }

    @Transactional(readOnly = true)
    public List getVillageList(String gewogId) {
        sqlQuery = properties.getProperty("CommonDao.getVillageList");
        hQuery = hibernateQuery(sqlQuery, DropdownDTO.class).setParameter("gewogId",gewogId);
        return hQuery.list();
    }

    @Transactional(readOnly = true)
    public Object getNextID(String tblName, String colName) {
        sqlQuery = "select ("+colName+")+1 from "+tblName+" order by "+colName + " desc";
        hQuery = hibernateQuery(sqlQuery);
        return hQuery.list() == null?1:hQuery.list().get(0);
    }

    @Transactional(readOnly = true)
    public Object getValue(String tblName, String colName, String filterCol, String filterVal) {
        sqlQuery = "select "+colName+" from "+tblName+" where "+filterCol+ "='"+filterVal+"'";
        hQuery = hibernateQuery(sqlQuery);List obj = hQuery.list();
        return obj.isEmpty()?null:obj.get(0);
    }

    @Transactional(readOnly = true)
    public FileDetailDTO getAttachmentDetail(String tableName,String filterCol,String filterVal) {
        //sqlQuery = properties.getProperty("CommonDao.getAttachmentDetail");
        sqlQuery = "SELECT a.DocumentName fileName,a.DocumentPath filePath,a.FileType fileType FROM "+tableName+" a WHERE "+filterCol+"=:filterVal";
        hQuery = hibernateQuery(sqlQuery,FileDetailDTO.class)
                //.setParameter("tableName",tableName)
                .setParameter("filterVal", filterVal);
        List obj = hQuery.list();
        return obj.isEmpty()?null:(FileDetailDTO)obj.get(0);
    }
    @Transactional(readOnly = true)
    public TasklistDto populateCount(String type,String registrationtype) {
        TasklistDto dto = new TasklistDto();
        if(type.equalsIgnoreCase("crparchitect")){
            String resubmitquery=" SELECT COUNT(a.`ReferenceNo`)appcount FROM crparchitect a WHERE a.`CmnApplicationRegistrationStatusId`=? ";
            Query resubmitgroup = sqlQuery(resubmitquery).setParameter(1, registrationtype);
            if(resubmitgroup.list().size()>0)
                dto.setGroupTaskCount((BigInteger) resubmitgroup.list().get(0));

            String registrationquery=" SELECT COUNT(a.`ReferenceNo`)appcount FROM crparchitect a LEFT JOIN crparchitectappliedservice s ON s.`CrpArchitectId`=a.`Id` WHERE a.`CmnApplicationRegistrationStatusId`=? AND s.`CmnServiceTypeId`=?";
            Query registrationqueryes = sqlQuery(registrationquery).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.REGISTRATION.getCode());
            if(registrationqueryes.list().size()>0)
                dto.setRegistration((BigInteger) registrationqueryes.list().get(0));

            String renwalquery=" SELECT COUNT(a.`ReferenceNo`)appcount FROM crparchitect a LEFT JOIN crparchitectappliedservice s ON s.`CrpArchitectId`=a.`Id` WHERE a.`CmnApplicationRegistrationStatusId`=? AND s.`CmnServiceTypeId`=?";
            Query renwalquerye = sqlQuery(renwalquery).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.RENEWAL.getCode());
            if(renwalquerye.list().size()>0)
                dto.setRenewal((BigInteger) renwalquerye.list().get(0));

            String cancellaitonquery=" SELECT COUNT(a.`ReferenceNo`)appcount FROM crparchitect a LEFT JOIN crparchitectappliedservice s ON s.`CrpArchitectId`=a.`Id` WHERE a.`CmnApplicationRegistrationStatusId`=? AND s.`CmnServiceTypeId`=?";
            Query cancellaitonquerye = sqlQuery(cancellaitonquery).setParameter(1, registrationtype).setParameter(2, ApplicationStatus.CANCELLATION.getCode());
            if(cancellaitonquerye.list().size()>0)
                dto.setCancellation((BigInteger) cancellaitonquerye.list().get(0));

        }
        return dto;
    }

    @Transactional
    public String getAppType(LoginDTO loginDTO) {
        String cdbNo="";
        String architectquery="SELECT f.`ARNo` cdbNo FROM sysuser s LEFT JOIN crparchitectfinal f ON f.`SysUserId`=s.`Id` WHERE s.`username`=? ";
        Query arNo = sqlQuery(architectquery).setParameter(1, loginDTO.getUsername());
        if(arNo.list().size()>0)
            cdbNo="Architect999"+ arNo.list().get(0);
        if(cdbNo==""){
            //get cdbno for others
        }
        return cdbNo;
    }

    @Transactional
    public List<TasklistDto> getdashboardDetails(String type) {
        /*MessageFor field
				 1=CRPS,2=Etool,3=CiNet,4=Contractors,5=Consultant,6=Architects,7=Engineers,8=Specilazed Trade Users*/
        if(type.equalsIgnoreCase("Architect")){
            type="6";
        }
        List<TasklistDto> retval=new ArrayList<TasklistDto>();
        try {
            sqlQuery="SELECT Message messages FROM `sysnewsandnotification` WHERE MessageFor=? ";
            Query queryre = sqlQuery(sqlQuery, TasklistDto.class).setParameter(1, type);
            if(queryre.list().size()>0){
                retval=queryre.list();
            }
        } catch (Exception e) {
            System.out.print("Exception in ArchitectDao # getdashboardDetails: " + e);
            e.printStackTrace();
        }
        return retval;
    }

    @Transactional
    public List<TasklistDto> populaterejectedApplications(String cdbdet) {
        List<TasklistDto> retval=new ArrayList<TasklistDto>();
        try {
            if(cdbdet.split("999")[0].equalsIgnoreCase("Architect")){
                sqlQuery="SELECT a.`ReferenceNo` mytaskCount,a.`ApplicationDate` appDate,a.`ARNo` cdbNo,a.`RejectedDate` rejectedDate,a.`RemarksByRejector` remarks FROM crparchitect a WHERE a.`CmnApplicationRegistrationStatusId`='de662a61-b049-11e4-89f3-080027dcfac6' AND a.`ARNo`=? ";
            }
            Query queryre = sqlQuery(sqlQuery, TasklistDto.class).setParameter(1, cdbdet.split("999")[1]);
            if(queryre.list().size()>0){
                retval=queryre.list();
            }
        } catch (Exception e) {
            System.out.print("Exception in ArchitectDao # getdashboardDetails: " + e);
            e.printStackTrace();
        }
        return retval;
    }
}


