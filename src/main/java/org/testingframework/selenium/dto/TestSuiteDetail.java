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


public class TestSuiteDetail {

	private Long suiteid = null;
	private Long tcid = null;
	private String tcname = null;
	private String modulename = null;
	private String usecasename = null;
	private int executionOrder;
	
	public Long getSuiteid() {
		return suiteid;
	}
	public void setSuiteid(Long suiteid) {
		this.suiteid = suiteid;
	}
	public Long getTcid() {
		return tcid;
	}
	public void setTcid(Long tcid) {
		this.tcid = tcid;
	}
	public String getTcname() {
		return tcname;
	}
	public void setTcname(String tcname) {
		this.tcname = tcname;
	}
	public String getModulename() {
		return modulename;
	}
	public void setModulename(String modulename) {
		this.modulename = modulename;
	}
	public String getUsecasename() {
		return usecasename;
	}
	public void setUsecasename(String usecasename) {
		this.usecasename = usecasename;
	}
	public int getExecutionOrder() {
		return executionOrder;
	}
	public void setExecutionOrder(int executionOrder) {
		this.executionOrder = executionOrder;
	}
	
	
	
}
