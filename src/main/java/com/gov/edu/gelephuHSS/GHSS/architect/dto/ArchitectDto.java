package com.gov.edu.gelephuHSS.GHSS.architect.dto;

import com.gov.edu.gelephuHSS.GHSS.architect.entity.ArchitectDocument;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by USER on 3/23/2020.
 */
public class ArchitectDto {
    private String CrpArchitectId;
    private String cdbNo;
    private BigInteger ReferenceNo;
    private String salutation;
    private String cidNo;
    private String fullname;
    private String dzongkhagId;
    private String gewog;
    private String village;
    private String serviceTypeId;
    private String countryId;
    private String serviceSectorType;
    private String trade;
    private String email;
    private String mobileNo;
    private String employeeName;
    private String employeeAddress;
    private String qualificationId;
    private String graduationYear;
    private Date graduationyr;
    private String universityName;
    private String universityCountry;
    private List<ArchitectDocument> doc;
    private String remarks;
    private String verifierremarks;
    private String approiverremarks;
    private Timestamp verifcationdate;
    private Timestamp approvaldate;
    private String updateStatus;
    private Timestamp paymentApprovedate;
    private Date paymentReceiptDate;
    private String paymentReceiptNo;
    private BigDecimal totalAmt;
    private String paymentmode;
    private Date regExpDate;
    private Date applicationDate;
    private Date initialDate;
    private Date registrationApproveDate;
    private List<ArchitectFeesDto> terms;
    private int noOfDaysLate;
    private int noOfDaysAfterGracePeriod;
    private BigDecimal penaltyPerDay;
    private BigDecimal paymentAmt;



    public String getCrpArchitectId() {
        return CrpArchitectId;
    }

    public void setCrpArchitectId(String crpArchitectId) {
        CrpArchitectId = crpArchitectId;
    }

    public BigInteger getReferenceNo() {
        return ReferenceNo;
    }

    public void setReferenceNo(BigInteger referenceNo) {
        ReferenceNo = referenceNo;
    }


    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
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

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
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

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
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

    public Date getGraduationyr() {
        return graduationyr;
    }

    public void setGraduationyr(Date graduationyr) {
        this.graduationyr = graduationyr;
    }

    public List<ArchitectDocument> getDoc() {
        return doc;
    }

    public void setDoc(List<ArchitectDocument> doc) {
        this.doc = doc;
    }

    public String getVerifierremarks() {
        return verifierremarks;
    }

    public void setVerifierremarks(String verifierremarks) {
        this.verifierremarks = verifierremarks;
    }

    public String getApproiverremarks() {
        return approiverremarks;
    }

    public void setApproiverremarks(String approiverremarks) {
        this.approiverremarks = approiverremarks;
    }

    public Timestamp getVerifcationdate() {
        return verifcationdate;
    }

    public void setVerifcationdate(Timestamp verifcationdate) {
        this.verifcationdate = verifcationdate;
    }

    public Timestamp getApprovaldate() {
        return approvaldate;
    }

    public void setApprovaldate(Timestamp approvaldate) {
        this.approvaldate = approvaldate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus;
    }

    public String getCdbNo() {
        return cdbNo;
    }

    public void setCdbNo(String cdbNo) {
        this.cdbNo = cdbNo;
    }

    public Timestamp getPaymentApprovedate() {
        return paymentApprovedate;
    }

    public void setPaymentApprovedate(Timestamp paymentApprovedate) {
        this.paymentApprovedate = paymentApprovedate;
    }

    public Date getPaymentReceiptDate() {
        return paymentReceiptDate;
    }

    public void setPaymentReceiptDate(Date paymentReceiptDate) {
        this.paymentReceiptDate = paymentReceiptDate;
    }

    public String getPaymentReceiptNo() {
        return paymentReceiptNo;
    }

    public void setPaymentReceiptNo(String paymentReceiptNo) {
        this.paymentReceiptNo = paymentReceiptNo;
    }

    public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }

    public String getPaymentmode() {
        return paymentmode;
    }

    public void setPaymentmode(String paymentmode) {
        this.paymentmode = paymentmode;
    }

    public Date getRegExpDate() {
        return regExpDate;
    }

    public void setRegExpDate(Date regExpDate) {
        this.regExpDate = regExpDate;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public Date getRegistrationApproveDate() {
        return registrationApproveDate;
    }

    public void setRegistrationApproveDate(Date registrationApproveDate) {
        this.registrationApproveDate = registrationApproveDate;
    }

    public List<ArchitectFeesDto> getTerms() {
        return terms;
    }

    public void setTerms(List<ArchitectFeesDto> terms) {
        this.terms = terms;
    }

    public int getNoOfDaysLate() {
        return noOfDaysLate;
    }

    public void setNoOfDaysLate(int noOfDaysLate) {
        this.noOfDaysLate = noOfDaysLate;
    }

    public int getNoOfDaysAfterGracePeriod() {
        return noOfDaysAfterGracePeriod;
    }

    public void setNoOfDaysAfterGracePeriod(int noOfDaysAfterGracePeriod) {
        this.noOfDaysAfterGracePeriod = noOfDaysAfterGracePeriod;
    }

    public BigDecimal getPenaltyPerDay() {
        return penaltyPerDay;
    }

    public void setPenaltyPerDay(BigDecimal penaltyPerDay) {
        this.penaltyPerDay = penaltyPerDay;
    }

    public BigDecimal getPaymentAmt() {
        return paymentAmt;
    }

    public void setPaymentAmt(BigDecimal paymentAmt) {
        this.paymentAmt = paymentAmt;
    }
}
