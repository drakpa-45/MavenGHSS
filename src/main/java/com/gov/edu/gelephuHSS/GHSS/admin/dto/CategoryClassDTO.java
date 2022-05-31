package com.gov.edu.gelephuHSS.GHSS.admin.dto;

import java.math.BigDecimal;

/**
 * ==================================================================================
 * Created by user on 2/19/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
public class CategoryClassDTO {
    private String id;
    private String contractorId;
    private String categoryId;
    private String aClassId;
    private String vClassId;
    private String apClassId;

    private String categoryName;
    private String aClassName;
    private String vClassName;
    private String apClassName;

    private BigDecimal feeAmount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContractorId() {
        return contractorId;
    }

    public void setContractorId(String contractorId) {
        this.contractorId = contractorId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getaClassId() {
        return aClassId;
    }

    public void setaClassId(String aClassId) {
        this.aClassId = aClassId;
    }

    public String getvClassId() {
        return vClassId;
    }

    public void setvClassId(String vClassId) {
        this.vClassId = vClassId;
    }

    public String getApClassId() {
        return apClassId;
    }

    public void setApClassId(String apClassId) {
        this.apClassId = apClassId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getaClassName() {
        return aClassName;
    }

    public void setaClassName(String aClassName) {
        this.aClassName = aClassName;
    }

    public String getvClassName() {
        return vClassName;
    }

    public void setvClassName(String vClassName) {
        this.vClassName = vClassName;
    }

    public String getApClassName() {
        return apClassName;
    }

    public void setApClassName(String apClassName) {
        this.apClassName = apClassName;
    }

    public BigDecimal getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
    }
}
