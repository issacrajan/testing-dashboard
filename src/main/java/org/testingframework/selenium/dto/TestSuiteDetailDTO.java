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

public class TestSuiteDetailDTO extends BaseDTO{

	List<TestSuiteDetail> list = null;
	Long recCount = null;
	
	public List<TestSuiteDetail> getList() {
		return list;
	}

	public void setList(List<TestSuiteDetail> list) {
		this.list = list;
	}

	public Long getRecCount() {
		return recCount;
	}

	public void setRecCount(Long recCount) {
		this.recCount = recCount;
	}

	
}
