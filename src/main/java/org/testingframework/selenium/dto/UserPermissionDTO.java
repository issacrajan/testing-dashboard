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

public class UserPermissionDTO {

	private String userId = null;
	private String userName = null;
	
	private List<UseCaseDTO> ucList = null;
	
	private List<TestSuiteHdrDTO> tsList = null;


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

	public List<UseCaseDTO> getUcList() {
		return ucList;
	}

	public void setUcList(List<UseCaseDTO> ucList) {
		this.ucList = ucList;
	}

	public List<TestSuiteHdrDTO> getTsList() {
		return tsList;
	}

	public void setTsList(List<TestSuiteHdrDTO> tsList) {
		this.tsList = tsList;
	}

	@Override
	public String toString() {
		return "UserPermissionDTO [userId=" + userId + ", userName=" + userName + ", ucList=" + ucList
				+ ", tsList=" + tsList + "]";
	}
}
