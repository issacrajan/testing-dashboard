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

public class TcRunHdr {

	private long id = 0;
	private Date runDate = null;
	private String runUser = null;
	private String runGroupId = null;
	private Date finishedDate = null;
	private int successCnt = 0;
	private int failureCnt = 0;
	private String remarks = null;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getRunDate() {
		return runDate;
	}
	public String getRunDateAsStr() {
		return Utils.fmtDate(runDate);
	}
	
	public void setRunDate(Date runDate) {
		this.runDate = runDate;
	}
	public String getRunUser() {
		return runUser;
	}
	public void setRunUser(String runUser) {
		this.runUser = runUser;
	}
	public String getRunGroupId() {
		return runGroupId;
	}
	public void setRunGroupId(String runGroupId) {
		this.runGroupId = runGroupId;
	}
	public Date getFinishedDate() {
		return finishedDate;
	}
	public void setFinishedDate(Date finishedDate) {
		this.finishedDate = finishedDate;
	}
	public int getSuccessCnt() {
		return successCnt;
	}
	public void setSuccessCnt(int successCnt) {
		this.successCnt = successCnt;
	}
	public int getFailureCnt() {
		return failureCnt;
	}
	public void setFailureCnt(int failureCnt) {
		this.failureCnt = failureCnt;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public String getRunDateFmt(){
		return Utils.fmtDate(runDate);
	}
	@Override
	public String toString() {
		return "TcRunHdr [id=" + id + ", runDate=" + runDate + ", runUser=" + runUser + ", runGroupId="
				+ runGroupId + ", finishedDate=" + finishedDate + ", successCnt=" + successCnt
				+ ", failureCnt=" + failureCnt + ", remarks=" + remarks + "]";
	}
	
	
}
