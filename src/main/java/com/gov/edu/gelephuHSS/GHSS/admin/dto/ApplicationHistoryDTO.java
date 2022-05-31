package com.gov.edu.gelephuHSS.GHSS.admin.dto;

import java.util.Date;

/**
 * ==================================================================================
 * Created by user on 2/29/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
public class ApplicationHistoryDTO {
    private  String contractorId;
    private String appStatus;
    private String userId;
    private String userName;
    private Date actionDate;
    private String remarks;

    public String getContractorId() {
        return contractorId;
    }

    public void setContractorId(String contractorId) {
        this.contractorId = contractorId;
    }

    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
