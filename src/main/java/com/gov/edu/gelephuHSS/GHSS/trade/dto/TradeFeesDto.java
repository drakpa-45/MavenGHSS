package com.gov.edu.gelephuHSS.GHSS.trade.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by USER on 3/19/2020.
 */
public class TradeFeesDto {
    private String name;
    private Integer validaty;
    private BigInteger registrationFee;
    private BigDecimal renewalFee;
    private String id;
    private String code;
    private String appliedCategoryId;
    private int arrayId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValidaty() {
        return validaty;
    }

    public void setValidaty(Integer validaty) {
        this.validaty = validaty;
    }

    public BigInteger getRegistrationFee() {
        return registrationFee;
    }

    public void setRegistrationFee(BigInteger registrationFee) {
        this.registrationFee = registrationFee;
    }

    public BigDecimal getRenewalFee() {
        return renewalFee;
    }

    public void setRenewalFee(BigDecimal renewalFee) {
        this.renewalFee = renewalFee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAppliedCategoryId() {
        return appliedCategoryId;
    }

    public void setAppliedCategoryId(String appliedCategoryId) {
        this.appliedCategoryId = appliedCategoryId;
    }

    public int getArrayId() {
        return arrayId;
    }

    public void setArrayId(int arrayId) {
        this.arrayId = arrayId;
    }
}
