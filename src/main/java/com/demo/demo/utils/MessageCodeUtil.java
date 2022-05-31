package com.demo.demo.utils;

public enum MessageCodeUtil {
    ST_001("ST_001", "Station code is existed"),
    ST_002("ST_002", "Station not found"),
    US_001("US_001", "User not found"),
    US_002("US_002", "Username is existed"),
    US_003("US_003", "Email is existed"),
    R_001("R_001", "Role not found"),
    R_002("R_002", "Role name is existed"),
    ET_001("ET_001", "Equipment type not found"),
    ET_002("ET_002", "Equipment name is existed"),
    E_001("E_001", "Equipment not found"),
    E_002("E_002", "Equipment serial is existed"),
    EP_001("EP_001", "Environmental parameter not found"),
    EP_002("EP_002", "Environmental parameter code is existed"),
    FCM_001("FCM_001", "FCM not found"),
    NT_001("NT_001", "Notification not found"),
    CB_001("CB_001", "List rule not empty"),
    PW_001("PW_001", "Password wrong"),
    PW_002("PW_002", "Password not match"),
    AT_001("AT_001", "Username or Password not match"),
    T_001("T_001", "Cron name is existed"),
    T_002("T_002", "Cron not found"),
    P_001("P_001", "Parameter is existed"),
    P_002("P_002", "Parameter not found"),
    CM_001("CM_001", "Server error");

    private String errorCode;
    private String errorMessage;

    /**
     * constructor
     *
     * @param errorCode
     * @param errorMessage
     */
    private MessageCodeUtil(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
