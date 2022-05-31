package com.gov.edu.gelephuHSS.GHSS.schoolPage.dto;

import com.gov.edu.gelephuHSS.GHSS.architect.entity.ArchitectDocument;

import java.sql.Date;
import java.util.List;

/**
 * Created by USER on 3/23/2020.
 */
public class StaffProfileDTO {
    private String staffId;
    private String cidNo;
    private String fullname;
    private String dzongkhagId;
    private String gewog;
    private String village;
    private String serviceTypeId;
    private String subjectName;
    private String serviceSectorType;
    private String trade;
    private String email;
    private Integer mobileNo;
    private String employeeName;
    private String employeeAddress;
    private String qualificationId;
    private String graduationYear;
    private Date graduationyr;
    private String universityName;
    private String universityCountry;
    private List<ArchitectDocument> doc;
    private String remarks;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getCidNo() {
        return cidNo;
    }

    public void setCidNo(String cidNo) {
        this.cidNo = cidNo;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDzongkhagId() {
        return dzongkhagId;
    }

    public void setDzongkhagId(String dzongkhagId) {
        this.dzongkhagId = dzongkhagId;
    }

    public String getGewog() {
        return gewog;
    }

    public void setGewog(String gewog) {
        this.gewog = gewog;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(String serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getServiceSectorType() {
        return serviceSectorType;
    }

    public void setServiceSectorType(String serviceSectorType) {
        this.serviceSectorType = serviceSectorType;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Integer mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public String getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(String qualificationId) {
        this.qualificationId = qualificationId;
    }

    public String getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear;
    }

    public Date getGraduationyr() {
        return graduationyr;
    }

    public void setGraduationyr(Date graduationyr) {
        this.graduationyr = graduationyr;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getUniversityCountry() {
        return universityCountry;
    }

    public void setUniversityCountry(String universityCountry) {
        this.universityCountry = universityCountry;
    }

    public List<ArchitectDocument> getDoc() {
        return doc;
    }

    public void setDoc(List<ArchitectDocument> doc) {
        this.doc = doc;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
