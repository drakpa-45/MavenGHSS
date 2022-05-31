package com.gov.edu.gelephuHSS.auth;


import com.gov.edu.gelephuHSS.base.BaseDao;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class LoginDao extends BaseDao {

    //region private variables
    private Query hQuery;
    //endregion

    //region public method

    /**
     * to get the user information while logging in.
     *
     * @param username username
     * @return UserSetupDTO
     */
    @Transactional(readOnly = true)
    public LoginDTO login(String username) {
        sqlQuery = properties.getProperty("LoginDao.login");
        hQuery = hibernateQuery(sqlQuery, LoginDTO.class).setParameter("username", username);
        return (LoginDTO) (hQuery.getSingleResult());
    }

    @Transactional(readOnly = true)
    public List<UserRoleDTO> getUserRoleList(String userId) {
        sqlQuery = properties.getProperty("LoginDao.getUserRoleList");
        hQuery = hibernateQuery(sqlQuery, UserRoleDTO.class).setParameter("userId", userId);
        return (List<UserRoleDTO>) (hQuery.list());
    }

    //endregion
}
