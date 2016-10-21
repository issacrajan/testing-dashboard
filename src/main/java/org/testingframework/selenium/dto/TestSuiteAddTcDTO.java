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

import java.util.List;


public class TestSuiteAddTcDTO extends BaseDTO {

	private TestSuiteHdrDTO tsHdr = null;
	private String moduleName = null;
	private String ucid = null;
	
	private List<TestCaseDTO> tcList = null;

	public TestSuiteHdrDTO getTsHdr() {
		return tsHdr;
	}

	public void setTsHdr(TestSuiteHdrDTO tsHdr) {
		this.tsHdr = tsHdr;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	
	public String getUcid() {
		return ucid;
	}

	public void setUcid(String ucid) {
		this.ucid = ucid;
	}

	public List<TestCaseDTO> getTcList() {
		return tcList;
	}

	public void setTcList(List<TestCaseDTO> tcList) {
		this.tcList = tcList;
	}
	
}
