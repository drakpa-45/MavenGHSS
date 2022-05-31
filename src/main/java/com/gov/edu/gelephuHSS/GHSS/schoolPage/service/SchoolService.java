package com.gov.edu.gelephuHSS.GHSS.schoolPage.service;

import com.gov.edu.gelephuHSS.GHSS.schoolPage.dao.SchoolDao;
import com.gov.edu.gelephuHSS.GHSS.schoolPage.dto.StaffProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by USER on 5/14/2020.
 */
@Service
public class SchoolService {
    @Autowired
    private SchoolDao dao;

    @Transactional
    public List<StaffProfileDTO> populateStaffDetails(String type) {
            return dao.populateStaffDetails(type);
    }
}
