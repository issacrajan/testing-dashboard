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

import java.util.ArrayList;
import java.util.List;


public class TestRunDetail {

	private Long testResultDetId = null;
	private Long runHdrId = null;
	private Long tcId = null;
	private String tcName = null;
	private String tcCompStatus = null;
	private boolean screenShotFlag = false;
	private List<String> msgList = null;
	public Long getTestResultDetId() {
		return testResultDetId;
	}
	public void setTestResultDetId(Long testResultDetId) {
		this.testResultDetId = testResultDetId;
	}
	public Long getTcId() {
		return tcId;
	}
	public void setTcId(Long tcId) {
		this.tcId = tcId;
	}
	public String getTcName() {
		return tcName;
	}
	public void setTcName(String tcName) {
		this.tcName = tcName;
	}
	public String getTcCompStatus() {
		return tcCompStatus;
	}
	public void setTcCompStatus(String tcCompStatus) {
		this.tcCompStatus = tcCompStatus;
	}
	
	public boolean isScreenShotFlag() {
		return screenShotFlag;
	}
	public void setScreenShotFlag(boolean screenShotFlag) {
		this.screenShotFlag = screenShotFlag;
	}
	public List<String> getMsgList() {
		return msgList;
	}
	public void setMsgList(List<String> msgList) {
		this.msgList = msgList;
	}
	public void addMessage(String m){
		if (msgList == null){
			msgList = new ArrayList<String>();
		}
		msgList.add(m);
	}
	public Long getRunHdrId() {
		return runHdrId;
	}
	
	public void setRunHdrId(Long runHdrId) {
		this.runHdrId = runHdrId;
	}
	public boolean getMsgFound(){
		return (msgList != null && !msgList.isEmpty());
	}
	
}
