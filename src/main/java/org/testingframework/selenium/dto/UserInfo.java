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

public class UserInfo {

	private String userId = null;
	private String userName = null;
	private String userPwd = null;
	private String userPwdConfirm = null;
	private String userRole = null;
	private String userStatus = null;
	private String userEmail = null;
	private String oldPwd = null;
	private Date lastLogingTime = null; 
	private String rememberMe = null;
	
	//supporting vars
	private String editMode = null;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getOldPwd() {
		return oldPwd;
	}
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	public Date getLastLogingTime() {
		return lastLogingTime;
	}
	public void setLastLogingTime(Date lastLogingTime) {
		this.lastLogingTime = lastLogingTime;
	}
	public String getUserPwdConfirm() {
		return userPwdConfirm;
	}
	public void setUserPwdConfirm(String userPwdConfirm) {
		this.userPwdConfirm = userPwdConfirm;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
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
	public String getRememberMe() {
		return rememberMe;
	}
	public void setRememberMe(String rememberMe) {
		this.rememberMe = rememberMe;
	}
	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", userName=" + userName + ", userPwd=" + userPwd
				+ ", userPwdConfirm=" + userPwdConfirm + ", userRole=" + userRole + ", userStatus="
				+ userStatus + ", userEmail=" + userEmail + ", oldPwd=" + oldPwd + ", lastLogingTime="
				+ lastLogingTime + ", rememberMe=" + rememberMe + ", editMode=" + editMode + "]";
	}
	
	
}
