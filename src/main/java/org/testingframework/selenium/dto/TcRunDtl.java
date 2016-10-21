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

import java.util.HashSet;
import java.util.Set;

public class TcRunDtl {

	private long id = 0;
	private long runHdrId = 0;
	private TestCaseDTO testCaseDTO = null;
	
	private String tcCompStatus = null;
	private String tcErrorDtl = null;
	private String screenShotFlag = null;
	private String testMsg = null;
	private Set<TestCaseRunFileDTO> tcFile = new HashSet<TestCaseRunFileDTO>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	public long getRunHdrId() {
		return runHdrId;
	}
	public void setRunHdrId(long runHdrId) {
		this.runHdrId = runHdrId;
	}
	
	public TestCaseDTO getTestCaseDTO() {
		return testCaseDTO;
	}
	public void setTestCaseDTO(TestCaseDTO testCaseDTO) {
		this.testCaseDTO = testCaseDTO;
	}
	public String getTcCompStatus() {
		return tcCompStatus;
	}
	public void setTcCompStatus(String tcCompStatus) {
		this.tcCompStatus = tcCompStatus;
	}
	public String getTcErrorDtl() {
		return tcErrorDtl;
	}
	public void setTcErrorDtl(String tcErrorDtl) {
		this.tcErrorDtl = tcErrorDtl;
	}
	
	
	public String getScreenShotFlag() {
		return screenShotFlag;
	}
	public void setScreenShotFlag(String screenShotFlag) {
		this.screenShotFlag = screenShotFlag;
	}
	public String getTestMsg() {
		return testMsg;
	}
	public void setTestMsg(String testMsg) {
		this.testMsg = testMsg;
	}
	
	public void addFile(TestCaseRunFileDTO f) {
		this.tcFile.add(f);
	}
	
	
	public Set<TestCaseRunFileDTO> getTcFile() {
		return tcFile;
	}
	public void setTcFile(Set<TestCaseRunFileDTO> tcFile) {
		this.tcFile = tcFile;
	}
	@Override
	public String toString() {
		return "TcRunDtl [id=" + id + ", runHdrId=" + runHdrId + ", testCaseDTO=" + testCaseDTO
				+ ", tcCompStatus=" + tcCompStatus + ", tcErrorDtl=" + tcErrorDtl + ", screenShotFlag="
				+ screenShotFlag + ", testMsg=" + testMsg + ", tcFile=" + tcFile + "]";
	}
	
}
