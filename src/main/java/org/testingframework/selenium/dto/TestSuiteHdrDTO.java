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


public class TestSuiteHdrDTO extends BaseDTO {

	private Long id = null;
	private String tsName = null; 
	private String tsDesc = null;
	private String createdBy = null;
	private Date createdDt = null;
	private Long cnt;
	private String editMode = null;
			
			
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTsName() {
		return tsName;
	}
	public void setTsName(String tsName) {
		this.tsName = tsName;
	}
	
	public String getTsDesc() {
		return tsDesc;
	}
	public void setTsDesc(String tsDesc) {
		this.tsDesc = tsDesc;
	}

	public String getEditMode() {
		return editMode;
	}
	public void setEditMode(String editMode) {
		this.editMode = editMode;
	}
	
	public boolean isNew(){
		return !"Y".equals(editMode);
	}
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDt() {
		return createdDt;
	}
	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}
	
	public Long getCnt() {
		return cnt;
	}
	public void setCnt(Long cnt) {
		this.cnt = cnt;
	}
	@Override
	public String toString() {
		return "TestSuiteHdrDTO [id=" + id + ", tsName=" + tsName + ", tsDesc=" + tsDesc + ", createdBy="
				+ createdBy + ", createdDt=" + createdDt + ", cnt=" + cnt + ", editMode=" + editMode + "]";
	}
	
	
}
