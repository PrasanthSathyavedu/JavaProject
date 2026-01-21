package com.test.project.bean;
public enum ErrorCodes {
    DUPLICATE_EMAIL("USR_001"),
    DATABASE_ERROR("DB_001"),
    GENERAL_ERROR("GEN_001");

    private final String code;
    ErrorCodes(String code) { this.code = code; }
    public String getCode() { return code; }
}
