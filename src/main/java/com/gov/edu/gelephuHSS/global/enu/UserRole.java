package com.gov.edu.gelephuHSS.global.enu;

/**
 * ==================================================================================
 * Created by user on 3/12/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
public enum UserRole {
    ADMINISTRATOR(37),
    VERIFIER(43),
    APPROVER(44),
    PAYMENT_APPROVER(18),
    CONTRACTOR_USER(2),
    CONSULTANT_USER(3),
    ARCHITECT_USER(4),
    SPTRADE_USER(5),
    ENGINEER_USER(6);

    private final int refNo;

    //region method
    private UserRole(int refNo) {
        this.refNo = refNo;
    }

    public int getRefNo() {
        return refNo;
    }

    //endregion
}
