package com.gov.edu.gelephuHSS.GHSS.admin.dto;

import com.gov.edu.gelephuHSS.GHSS.contractor.model.Contractor;
import com.gov.edu.gelephuHSS.GHSS.contractor.model.ContractorHRAttachment;
import com.gov.edu.gelephuHSS.GHSS.contractor.dto.ContractorHrDTO;

import java.util.List;

/**
 * ==================================================================================
 * Created by user on 2/19/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
public class ContractorInfoDTO {
    private Contractor contractor;
    private List<ContractorHrDTO> contractorHRs;
    private List<ContractorHRAttachment> contractorHRAs;
    private List<CategoryClassDTO> categories;
    private List<EquipmentDTO> equipments;
    private List<ApplicationHistoryDTO> appHistoryDTOs;

    private String ownershipTypeTxt;
    private String countryTxt;
    private String pDzongkhagTxt;
    private String pGewogTxt;
    private String pVillageTxt;
    private String estDzongkhagTxt;

    private String cdbNo;

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public List<ContractorHrDTO> getContractorHRs() {
        return contractorHRs;
    }

    public void setContractorHRs(List<ContractorHrDTO> contractorHRs) {
        this.contractorHRs = contractorHRs;
    }

    public List<ContractorHRAttachment> getContractorHRAs() {
        return contractorHRAs;
    }

    public void setContractorHRAs(List<ContractorHRAttachment> contractorHRAs) {
        this.contractorHRAs = contractorHRAs;
    }

    public List<CategoryClassDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryClassDTO> categories) {
        this.categories = categories;
    }

    public List<EquipmentDTO> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<EquipmentDTO> equipments) {
        this.equipments = equipments;
    }

    public List<ApplicationHistoryDTO> getAppHistoryDTOs() {
        return appHistoryDTOs;
    }

    public void setAppHistoryDTOs(List<ApplicationHistoryDTO> appHistoryDTOs) {
        this.appHistoryDTOs = appHistoryDTOs;
    }

    public String getOwnershipTypeTxt() {
        return ownershipTypeTxt;
    }

    public void setOwnershipTypeTxt(String ownershipTypeTxt) {
        this.ownershipTypeTxt = ownershipTypeTxt;
    }

    public String getCountryTxt() {
        return countryTxt;
    }

    public void setCountryTxt(String countryTxt) {
        this.countryTxt = countryTxt;
    }

    public String getpDzongkhagTxt() {
        return pDzongkhagTxt;
    }

    public void setpDzongkhagTxt(String pDzongkhagTxt) {
        this.pDzongkhagTxt = pDzongkhagTxt;
    }

    public String getpGewogTxt() {
        return pGewogTxt;
    }

    public void setpGewogTxt(String pGewogTxt) {
        this.pGewogTxt = pGewogTxt;
    }

    public String getpVillageTxt() {
        return pVillageTxt;
    }

    public void setpVillageTxt(String pVillageTxt) {
        this.pVillageTxt = pVillageTxt;
    }

    public String getEstDzongkhagTxt() {
        return estDzongkhagTxt;
    }

    public void setEstDzongkhagTxt(String estDzongkhagTxt) {
        this.estDzongkhagTxt = estDzongkhagTxt;
    }

    public String getCdbNo() {
        return cdbNo;
    }

    public void setCdbNo(String cdbNo) {
        this.cdbNo = cdbNo;
    }
}
