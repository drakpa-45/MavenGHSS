package com.gov.edu.gelephuHSS.GHSS.schoolPage.dao;

import com.gov.edu.gelephuHSS.GHSS.schoolPage.dto.StaffProfileDTO;
import com.gov.edu.gelephuHSS.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 5/14/2020.
 */
@Repository
public class SchoolDao extends BaseDao{

    public List<StaffProfileDTO> populateStaffDetails(String type) {
        List<StaffProfileDTO> dto= new ArrayList<StaffProfileDTO>();
        String sqlQuery="SELECT sm.First_Name fullname, sm.Phone_Number mobileNo,dl.Department_Name serviceTypeId,qm.Qualification qualificationId,\n" +
                "qm.University_Name universityName, sub.Subject_Name subjectName FROM t_staff_master sm LEFT JOIN t_department_lookup dl ON sm.Department_Id=dl.Department_Id\n" +
                "LEFT JOIN t_qualification_master qm ON qm.Staff_Id = sm.Staff_Id LEFT JOIN t_subject_master sub \n" +
                "ON sub.Subject_Id=sm.Subject_Id WHERE sm.Department_Id=?";
        org.hibernate.query.Query hQuery = hibernateQuery(sqlQuery,StaffProfileDTO.class).setParameter(1,type);
        dto= hQuery.list();
        return dto;
    }
}
