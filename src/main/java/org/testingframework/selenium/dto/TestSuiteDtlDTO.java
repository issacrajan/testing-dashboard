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

import java.io.Serializable;


public class TestSuiteDtlDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6336431113199415913L;
	
	private Long suiteId = null;
	private Long tcId = null;
	private Integer executionOrder;
	
	public Long getSuiteId() {
		return suiteId;
	}
	public void setSuiteId(Long suiteId) {
		this.suiteId = suiteId;
	}
	public Long getTcId() {
		return tcId;
	}
	public void setTcId(Long tcId) {
		this.tcId = tcId;
	}
	public Integer getExecutionOrder() {
		return executionOrder;
	}
	public void setExecutionOrder(Integer executionOrder) {
		this.executionOrder = executionOrder;
	}
	
}
