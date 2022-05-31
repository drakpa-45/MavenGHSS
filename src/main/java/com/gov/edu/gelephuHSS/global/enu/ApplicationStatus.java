package com.gov.edu.gelephuHSS.global.enu;

/**
 * ==================================================================================
 * Created by user on 2/12/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
public enum ApplicationStatus {
    //region enum
    UNDER_PROCESS("262a3f11-adbd-11e4-99d7-080027dcfac6"),
    VERIFIED("36f9627a-adbd-11e4-99d7-080027dcfac6"),
    APPROVED("463c2d4c-adbd-11e4-99d7-080027dcfac6"),
    APPROVED_FOR_PAYMENT("6195664d-c3c5-11e4-af9f-080027dcfac6"),
    DEREGISTERED("b1fa6607-b1dd-11e4-89f3-080027dcfac6"),
    REJECTED("de662a61-b049-11e4-89f3-080027dcfac6"),
    BLACKLISTED("f89a6f55-b1dd-11e4-89f3-080027dcfac6"),
    REVOKED("f89a6f55-b1dd-aac4-89f3-080027dcfac6"),
    SUSPENDED("f89a6f55-b1dd-xvid-89f3-080027dcfac6"),
    DEBARRED("nomedia5-b1dd-xvid-89f3-080027dcfac6"),
    REGISTRATION("55a922e1-cbbf-11e4-83fb-080027dcfac6"),
    RENEWAL("45bc628b-cbbe-11e4-83fb-080027dcfac6"),
    CANCELLATION("acf4b324-cbbe-11e4-83fb-080027dcfac6"),
    SURRENDERED("nomedia5-b1dd-xvid-89f3-080027dcfac6");
    //endregion
//region private variables
    private final String code;
    //endregion

    //region method
    private ApplicationStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    //endregion
}
