/**
 * Testing Framework Dashboard
 *
 *  Copyright 2016 by ISSAC NEWTON RAJAN <issac.rajan@gmail.com>
 *  
 * 
 * Some open source application is free software: you can redistribute 
 * it and/or modify it under the terms of the GNU General Public 
 * License as published by the Free Software Foundation, either 
 * version 3 of the License, or (at your option) any later version.
 * 
 * Some open source application is distributed in the hope that it will 
 * be useful, but WITHOUT ANY WARRANTY; without even the implied warranty 
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @license GPL-3.0+ <http://spdx.org/licenses/GPL-3.0+>
 */


package org.testingframework.selenium.dto;

import java.util.Date;

import org.testingframework.selenium.utils.Utils;

public class TestCaseDTO  extends BaseDTO{
	private long id =0;
	private long ucid = 0;
	private String tcName = null;
	private String tcDesc = null;
	private String testCaseDetail = null;
	private String tcJvClassName = null;
	private String tcJvMethod = null;  
	private String tcStatus = null;
	private Date tcCreationDate = null;
	private String tcCreatedBy = null;
	private Date lastExecutedDate = null;
	private String supportFileName = null;
	private String tcModifiedBy=null;
	private Date tcModifiedDate = null;
	
	private Integer executionOrder;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUcid() {
		return ucid;
	}
	public void setUcid(long ucid) {
		this.ucid = ucid;
	}
	public String getTcName() {
		return tcName;
	}
	public void setTcName(String tcName) {
		this.tcName = tcName;
	}
	public String getTcDesc() {
		if (tcDesc == null){
			return "";
		}
		return tcDesc;
	}
	public void setTcDesc(String tcDesc) {
		this.tcDesc = tcDesc;
	}
	public String getTestCaseDetail() {
		return testCaseDetail;
	}
	public void setTestCaseDetail(String testCaseDetail) {
		this.testCaseDetail = testCaseDetail;
	}
	public String getTcJvClassName() {
		return tcJvClassName;
	}
	public void setTcJvClassName(String tcJvClassName) {
		this.tcJvClassName = tcJvClassName;
	}
	public String getTcJvMethod() {
		return tcJvMethod;
	}
	public void setTcJvMethod(String tcJvMethod) {
		this.tcJvMethod = tcJvMethod;
	}
	public Date getTcCreationDate() {
		return tcCreationDate;
	}
	public String getTcCreationDateFmt() {
		return Utils.fmtDate(tcCreationDate);
	}
	public void setTcCreationDate(Date tcCreationDate) {
		this.tcCreationDate = tcCreationDate;
	}
	public String getTcCreatedBy() {
		return tcCreatedBy;
	}
	public void setTcCreatedBy(String tcCreatedBy) {
		this.tcCreatedBy = tcCreatedBy;
	}
	
	public String getTcModifiedBy() {
		return tcModifiedBy;
	}
	public void setTcModifiedBy(String tcModifiedBy) {
		this.tcModifiedBy = tcModifiedBy;
	}
	public Date getTcModifiedDate() {
		return tcModifiedDate;
	}
	public void setTcModifiedDate(Date tcModifiedDate) {
		this.tcModifiedDate = tcModifiedDate;
	}
	
	
	public String getTcStatus() {
		return tcStatus;
	}
	public void setTcStatus(String tcStatus) {
		this.tcStatus = tcStatus;
	}
	public Date getLastExecutedDate() {
		return lastExecutedDate;
	}
	public void setLastExecutedDate(Date lastExecutedDate) {
		this.lastExecutedDate = lastExecutedDate;
	}
	
	public Integer getExecutionOrder() {
		return executionOrder;
	}
	public String getExecutionOrderStr() {
		if (executionOrder == null){
			return "9999";
		}
		return String.valueOf(executionOrder);
	}
	public void setExecutionOrder(Integer executionOrder) {
		this.executionOrder = executionOrder;
	}
	public String getSupportFileName() {
		return supportFileName;
	}
	public void setSupportFileName(String supportFileName) {
		this.supportFileName = supportFileName;
	}
	@Override
	public String toString() {
		return "TestCaseDTO [id=" + id + ", ucid=" + ucid + ", tcName=" + tcName + ", tcDesc=" + tcDesc
				+ ", testCaseDetail=" + testCaseDetail + ", tcJvClassName=" + tcJvClassName + ", tcJvMethod="
				+ tcJvMethod + ", tcStatus=" + tcStatus + ", tcCreationDate=" + tcCreationDate
				+ ", tcCreatedBy=" + tcCreatedBy + ", lastExecutedDate=" + lastExecutedDate
				+ ", supportFileName=" + supportFileName + ", tcModifiedBy=" + tcModifiedBy
				+ ", tcModifiedDate=" + tcModifiedDate + ", executionOrder=" + executionOrder + "]";
	}
	
}
