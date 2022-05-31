package com.gov.edu.gelephuHSS.GHSS.engineer;

import com.gov.edu.gelephuHSS.base.BaseDao;
import com.gov.edu.gelephuHSS.GHSS.admin.dto.TasksDTO;
import com.gov.edu.gelephuHSS.GHSS.contractor.dto.FeeStructureDTO;
import com.gov.edu.gelephuHSS.GHSS.engineer.dto.EngineerDTO;
import com.gov.edu.gelephuHSS.GHSS.engineer.model.Engineer;
import com.gov.edu.gelephuHSS.GHSS.engineer.model.EngineerAppliedServiceEntity;
import com.gov.edu.gelephuHSS.GHSS.engineer.model.EngineerAttachment;
import com.gov.edu.gelephuHSS.lib.LoggedInUser;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Dechen  Wangdi on 12/17/2019.
 */

@Repository
public class EngineerDao extends BaseDao {

    @Transactional(readOnly = true)
    public List getFeeStructure(String category) {
        sqlQuery = properties.getProperty("EngineerDao.getFeeStructure");
        return hibernateQuery(sqlQuery, FeeStructureDTO.class).setParameter("category", category).list();
    }

    @Transactional
    public void save(Engineer engineer) {
        saveOrUpdate(engineer);
    }

    @Transactional
    public void saveSEA(EngineerAppliedServiceEntity engineerAppliedServiceEntity) {
        saveOrUpdate(engineerAppliedServiceEntity);
    }

    @Transactional
    public void saveA(EngineerAttachment engineerAttachment) {
        saveOrUpdate(engineerAttachment);
    }

    @Transactional(readOnly = true)
    public List getEngineerList(String applicationNo) {
        sqlQuery = properties.getProperty("EngineerDao.getEngineerList");
        return hibernateQuery(sqlQuery, EngineerDTO.class)
                .setParameter("applicationNo", applicationNo).list();
    }

    @Transactional
    public void verifyApplication(Integer applicationNo, LoggedInUser loggedInUser, String remark) {
        sqlQuery = properties.getProperty("EngineerDao.verifyApplication");
        Query hQuery = hibernateQuery(sqlQuery, EngineerDTO.class)
                .setParameter("applicationNo", applicationNo)
                .setParameter("verifiedBy", loggedInUser.getUserID())
                .setParameter("verifiedDate", loggedInUser.getServerDate())
                .setParameter("remark", remark);
        hQuery.executeUpdate();
    }

    @Transactional
    public void approveApplication(Integer applicationNo, LoggedInUser loggedInUser, String remark) {
        sqlQuery = properties.getProperty("EngineerDao.approveApplication");
        Query hQuery = hibernateQuery(sqlQuery, EngineerDTO.class)
                .setParameter("applicationNo", applicationNo)
                .setParameter("approvedBy", loggedInUser.getUserID())
                .setParameter("remark", remark);
        hQuery.executeUpdate();
    }

    @Transactional
    public void rejectApplication(Integer applicationNo, LoggedInUser loggedInUser, String remark, String rejectionCode) {
        sqlQuery = properties.getProperty("EngineerDao.rejectApplication");
        Query hQuery = hibernateQuery(sqlQuery, EngineerDTO.class)
                .setParameter("applicationNo", applicationNo)
                .setParameter("rejectedBy", loggedInUser.getUserID())
                .setParameter("rejectedDate", loggedInUser.getServerDate())
                .setParameter("rejectedCode", rejectionCode.substring(0, 29))
                .setParameter("remark", remark);
        hQuery.executeUpdate();
    }

    @Transactional(readOnly = true)
    public List<TasksDTO> getTaskList() {
        sqlQuery = properties.getProperty("EngineerDao.getTaskList");
        return hibernateQuery(sqlQuery, TasksDTO.class).list();
    }

    public BigInteger getMaxId() {
        sqlQuery = properties.getProperty("EngineerDao.getMaxId");
        return (BigInteger) hibernateQuery(sqlQuery).uniqueResult();
    }

    @Transactional(readOnly = true)
    public Boolean isEmailUnique(String email) {
        sqlQuery = properties.getProperty("EngineerDao.isEmailUnique");
        return hibernateQuery(sqlQuery).setParameter("email",email).list().isEmpty();
    }
}
