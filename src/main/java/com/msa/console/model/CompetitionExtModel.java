package com.msa.console.model;

import java.util.Date;

public class CompetitionExtModel extends CompetitionModel{
	private String tenantId;
	private String cpCd;
	private String cpNm;
	private String cpStartDt;
	private String cpEndDt;
	private String cpPlaceNm;
	private String cpScaleCd;
	private String cpTypeCd;
	private int expectUserNum;
	private String crtId;
	private String crtIp;
	private Date adDate;
	private String udtId;
	private String udtIp;
	private Date udtDate;
	
	private Integer page;
    private int rowPerPage;
    private int skipCount;
    private Integer totalCount;
    
    private String contNm;
    private String mgrNm;
    private String mgrTelNo;
	
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getCpCd() {
		return cpCd;
	}
	public void setCpCd(String cpCd) {
		this.cpCd = cpCd;
	}
	public String getCpNm() {
		return cpNm;
	}
	public void setCpNm(String cpNm) {
		this.cpNm = cpNm;
	}
	public String getCpStartDt() {
		return cpStartDt;
	}
	public void setCpStartDt(String cpStartDt) {
		this.cpStartDt = cpStartDt;
	}
	public String getCpEndDt() {
		return cpEndDt;
	}
	public void setCpEndDt(String cpEndDt) {
		this.cpEndDt = cpEndDt;
	}
	public String getCpPlaceNm() {
		return cpPlaceNm;
	}
	public void setCpPlaceNm(String cpPlaceNm) {
		this.cpPlaceNm = cpPlaceNm;
	}
	public String getCpScaleCd() {
		return cpScaleCd;
	}
	public void setCpScaleCd(String cpScaleCd) {
		this.cpScaleCd = cpScaleCd;
	}
	public String getCpTypeCd() {
		return cpTypeCd;
	}
	public void setCpTypeCd(String cpTypeCd) {
		this.cpTypeCd = cpTypeCd;
	}
	public int getExpectUserNum() {
		return expectUserNum;
	}
	public void setExpectUserNum(int expectUserNum) {
		this.expectUserNum = expectUserNum;
	}
	public String getCrtId() {
		return crtId;
	}
	public void setCrtId(String crtId) {
		this.crtId = crtId;
	}
	public String getCrtIp() {
		return crtIp;
	}
	public void setCrtIp(String crtIp) {
		this.crtIp = crtIp;
	}
	public Date getAdDate() {
		return adDate;
	}
	public void setAdDate(Date adDate) {
		this.adDate = adDate;
	}
	public String getUdtId() {
		return udtId;
	}
	public void setUdtId(String udtId) {
		this.udtId = udtId;
	}
	public String getUdtIp() {
		return udtIp;
	}
	public void setUdtIp(String udtIp) {
		this.udtIp = udtIp;
	}
	public Date getUdtDate() {
		return udtDate;
	}
	public void setUdtDate(Date udtDate) {
		this.udtDate = udtDate;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public int getRowPerPage() {
		return rowPerPage;
	}
	public void setRowPerPage(int rowPerPage) {
		this.rowPerPage = rowPerPage;
	}
	public int getSkipCount() {
		return skipCount;
	}
	public void setSkipCount(int skipCount) {
		this.skipCount = skipCount;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public String getContNm() {
		return contNm;
	}
	public void setContNm(String contNm) {
		this.contNm = contNm;
	}
	public String getMgrNm() {
		return mgrNm;
	}
	public void setMgrNm(String mgrNm) {
		this.mgrNm = mgrNm;
	}
	public String getMgrTelNo() {
		return mgrTelNo;
	}
	public void setMgrTelNo(String mgrTelNo) {
		this.mgrTelNo = mgrTelNo;
	}
}
