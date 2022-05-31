package com.gov.edu.gelephuHSS.GHSS.contractor;

import com.gov.edu.gelephuHSS.GHSS.contractor.dto.ContractorTrainingDTO;
import com.gov.edu.gelephuHSS.base.BaseDao;
import com.gov.edu.gelephuHSS.GHSS.contractor.dto.CategoryDTO;
import com.gov.edu.gelephuHSS.GHSS.contractor.dto.FeeStructureDTO;
import com.gov.edu.gelephuHSS.lib.DropdownDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
public class ContractorDao extends BaseDao {


    @Transactional(readOnly = true)
    public List gFeeStructure(String category) {
        sqlQuery = properties.getProperty("ContractorDao.gFeeStructure");
        return hibernateQuery(sqlQuery, FeeStructureDTO.class).setParameter("category",category).list();
    }

    @Transactional(readOnly = true)
    public List gContractCategory() {
        sqlQuery = properties.getProperty("ContractorDao.gContractCategory");
        return hibernateQuery(sqlQuery, CategoryDTO.class).list();
    }

    @Transactional(readOnly = true)
    public List gClassification() {
        sqlQuery = properties.getProperty("ContractorDao.gClassification");
        return hibernateQuery(sqlQuery, DropdownDTO.class).list();
    }

    public void saveUpdate(Object object) {
        saveOrUpdate(object);
    }

    @Transactional(readOnly = true)
    public List gEquipment() {
        sqlQuery = properties.getProperty("ContractorDao.gEquipment");
        return hibernateQuery(sqlQuery, DropdownDTO.class).list();
    }

    @Transactional(readOnly = true)
    public Boolean isEmailUnique(String email) {
        sqlQuery = properties.getProperty("ContractorDao.isEmailUnique");
        return hibernateQuery(sqlQuery).setParameter("email",email).list().isEmpty();
    }

    @Transactional(readOnly = true)
    public List getTrainingDtl(String cidNo) {
        sqlQuery = properties.getProperty("ContractorDao.getTrainingDtl");
        return hibernateQuery(sqlQuery, ContractorTrainingDTO.class).setParameter("cidNo",cidNo).list();
    }
}
