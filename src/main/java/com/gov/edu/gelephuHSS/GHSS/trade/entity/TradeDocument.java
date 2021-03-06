package com.gov.edu.gelephuHSS.GHSS.trade.entity;

import com.gov.edu.gelephuHSS.base.BaseModel;
import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by USER on 3/23/2020.
 */
@Entity
@Table(name="crpspecializedtradeattachment")
public class TradeDocument extends BaseModel {
    @Id
    @Column(name="Id")
    private String id;

    @Column(name="CrpSpecializedTradeId")
    private String crpSpecializedTradeId;

    @Column(name="DocumentName")
    private String documentName;

    @NotNull
    @Column(name="DocumentPath")
    private String documentPath;

    @NotNull
    @Column(name="FileType")
    private String fileType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCrpSpecializedTradeId() {
        return crpSpecializedTradeId;
    }

    public void setCrpSpecializedTradeId(String crpSpecializedTradeId) {
        this.crpSpecializedTradeId = crpSpecializedTradeId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
