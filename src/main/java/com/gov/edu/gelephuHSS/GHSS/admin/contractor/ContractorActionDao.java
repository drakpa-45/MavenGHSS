package com.gov.edu.gelephuHSS.GHSS.admin.contractor;

import com.gov.edu.gelephuHSS.GHSS.admin.dto.CategoryClassDTO;
import com.gov.edu.gelephuHSS.GHSS.admin.dto.EquipmentDTO;
import com.gov.edu.gelephuHSS.GHSS.contractor.model.Contractor;
import com.gov.edu.gelephuHSS.base.BaseDao;
import com.gov.edu.gelephuHSS.GHSS.admin.dto.ApplicationHistoryDTO;
import com.gov.edu.gelephuHSS.GHSS.admin.dto.TasksDTO;
import com.gov.edu.gelephuHSS.GHSS.contractor.dto.ContractorHrDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.util.List;

/**
 * ==================================================================================
 * Created by user on 2/14/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
@Repository
public class ContractorActionDao extends BaseDao{

    @Transactional(readOnly = true)
    public List gGroupTaskList(String status) {
        sqlQuery = properties.getProperty("ContractorActionDao.gGroupTaskList");
        return hibernateQuery(sqlQuery, TasksDTO.class).setParameter("status", status).list();
    }
    @Transactional(readOnly = true)
    public List gMyTaskList(String userId) {
        sqlQuery = properties.getProperty("ContractorActionDao.gMyTaskList");
        return hibernateQuery(sqlQuery, TasksDTO.class).setParameter("userId", userId).list();
    }

    @Transactional(readOnly = true)
    public Contractor getContractor(BigInteger referenceNo){
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Contractor> cQuery = builder.createQuery(Contractor.class);
        Root<Contractor> root = cQuery.from(Contractor.class);
        cQuery.select(root).where(builder.equal(root.get("referenceNo"), referenceNo));
        return getCurrentSession().createQuery(cQuery).getSingleResult();
    }

    /*@Transactional(readOnly = true)
    public List<ContractorHR> getContractorHRs(String contractorId){
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<ContractorHR> cQuery = builder.createQuery(ContractorHR.class);
        Root<ContractorHR> root = cQuery.from(ContractorHR.class);
        cQuery.select(root).where(builder.equal(root.get("contractorID"), contractorId));
        return getCurrentSession().createQuery(cQuery).list();
    }*/

    @Transactional(readOnly = true)
    public List<ContractorHrDTO> getContractorHRs(String contractorId) {
        sqlQuery = properties.getProperty("ContractorActionDao.getContractorHRs");
        return hibernateQuery(sqlQuery, ContractorHrDTO.class).setParameter("contractorId", contractorId).list();

    }

    @Transactional(readOnly = true)
    public List<CategoryClassDTO> getCategoryClass(String contractorId) {
        sqlQuery = properties.getProperty("ContractorActionDao.getCategoryClass");
        return hibernateQuery(sqlQuery, CategoryClassDTO.class).setParameter("contractorId", contractorId).list();

    }

    @Transactional(readOnly = true)
    public List<CategoryClassDTO> getFeeCategoryClass(String contractorId) {
        sqlQuery = properties.getProperty("ContractorActionDao.getFeeCategoryClass");
        return hibernateQuery(sqlQuery, CategoryClassDTO.class).setParameter("contractorId", contractorId).list();

    }


    @Transactional(readOnly = true)
    public List<EquipmentDTO> getEquipment(String contractorId) {
        sqlQuery = properties.getProperty("ContractorActionDao.getEquipment");
        return hibernateQuery(sqlQuery, EquipmentDTO.class).setParameter("contractorId", contractorId).list();

    }

    @Transactional(readOnly = true)
    public String getNextCDBNo() {
        sqlQuery = properties.getProperty("ContractorActionDao.getNextCDBNo");
        List resultList = hibernateQuery(sqlQuery).list();
        return resultList.isEmpty()?"1":Integer.toString(((Double) resultList.get(0)).intValue());

    }

    @Transactional(readOnly = false)
    public void verify(String contractorId, String userID, String vRemarks) {
        sqlQuery = properties.getProperty("ContractorActionDao.verify");

        hibernateQuery(sqlQuery)
                .setParameter("contractorId", contractorId)
                .setParameter("vUserId",userID)
                .setParameter("vRemarks",vRemarks).executeUpdate();
    }

    @Transactional(readOnly = true)
    public List<ApplicationHistoryDTO> getAppHistoryDtl(String contractorId) {
        sqlQuery = properties.getProperty("ContractorActionDao.getAppHistoryDtl");
        return (List<ApplicationHistoryDTO>)hibernateQuery(sqlQuery, ApplicationHistoryDTO.class).setParameter("contractorId", contractorId).list();
    }

    @Transactional(readOnly = false)
    public void approve(String contractorId, String userID, String aRemarks) {
        sqlQuery = properties.getProperty("ContractorActionDao.approve");

        hibernateQuery(sqlQuery)
                .setParameter("contractorId", contractorId)
                .setParameter("aUserId",userID)
                .setParameter("aRemarks",aRemarks).executeUpdate();
    }

    @Transactional(readOnly = false)
    public void reject(String contractorId, String userID, String remarks,String rejectedApplicationStatusId) {
        sqlQuery = properties.getProperty("ContractorActionDao.approve");

        hibernateQuery(sqlQuery)
                .setParameter("contractorId", contractorId)
                .setParameter("userId",userID)
                .setParameter("remarks",remarks)
                .setParameter("applicationStatusId",rejectedApplicationStatusId).executeUpdate();
    }

    @Transactional(readOnly = false)
    public void send2MyOrGroupTask(String appNo, String lockUserId) {
        sqlQuery = properties.getProperty("ContractorActionDao.send2MyOrGroupTask");
        hibernateQuery(sqlQuery)
                .setParameter("appNo", appNo)
                .setParameter("lockUserId", lockUserId).executeUpdate();

    }

    @Transactional(readOnly = false)
    public void paymentUpdate(String contractorId,String userId,String appStatusId,String createdBy) {
        sqlQuery = properties.getProperty("ContractorActionDao.paymentUpdate");

        hibernateQuery(sqlQuery)
                .setParameter("contractorId", contractorId)
                .setParameter("userId",userId)
                .setParameter("appStatusId",appStatusId)
                .setParameter("createdBy",createdBy)
                .executeUpdate();

    }
}
