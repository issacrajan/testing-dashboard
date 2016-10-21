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

public class UseCaseDTO extends BaseDTO  {

	private Long id = null;
	private String moduleName = null;
	private String useCaseName = null;
	private String remarks = null;
	private String modifiedBy = null;
	private Date modifiedDt = null;
	private Long tcCnt = null;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getUseCaseName() {
		return useCaseName;
	}
	public void setUseCaseName(String useCaseName) {
		this.useCaseName = useCaseName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getModifiedDt() {
		return modifiedDt;
	}
	public String getModifiedDtFmt() {
		return Utils.fmtDate(modifiedDt);
	}
	public void setModifiedDt(Date modifiedDt) {
		this.modifiedDt = modifiedDt;
	}
	
	public Long getTcCnt() {
		return tcCnt;
	}
	public void setTcCnt(Long tcCnt) {
		this.tcCnt = tcCnt;
	}
	@Override
	public String toString() {
		return "UseCaseDTO [id=" + id + ", moduleName=" + moduleName + ", useCaseName=" + useCaseName
				+ ", remarks=" + remarks + ", modifiedBy=" + modifiedBy + ", modifiedDt=" + modifiedDt
				+ ", tcCnt=" + tcCnt + "]";
	}
	
	
}
