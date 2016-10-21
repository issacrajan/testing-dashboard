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
import java.util.List;

public class TcExeResultDTO extends BaseDTO{

	private Date fromDt = null;
	private Date thruDt = null;
	private String fromDtStr = null;
	private String thruDtStr = null;
	private Long tcRunHdrId = null;
	private TcRunHdr tcRunHdr = null;
	private String pageAction = null;
	private List<TcRunHdr> list = null;
	private List<TestRunDetail> listDtl = null;

	public String getPageAction() {
		return pageAction;
	}

	public void setPageAction(String pageAction) {
		this.pageAction = pageAction;
	}

	public Date getFromDt() {
		return fromDt;
	}

	public void setFromDt(Date fromDt) {
		this.fromDt = fromDt;
	}

	public Date getThruDt() {
		return thruDt;
	}

	public void setThruDt(Date thruDt) {
		this.thruDt = thruDt;
	}

	public List<TcRunHdr> getList() {
		return list;
	}

	public void setList(List<TcRunHdr> list) {
		this.list = list;
	}

	public Long getTcRunHdrId() {
		return tcRunHdrId;
	}

	public void setTcRunHdrId(Long tcRunHdrId) {
		this.tcRunHdrId = tcRunHdrId;
	}

	public TcRunHdr getTcRunHdr() {
		return tcRunHdr;
	}

	public void setTcRunHdr(TcRunHdr tcRunHdr) {
		this.tcRunHdr = tcRunHdr;
	}

	public List<TestRunDetail> getListDtl() {
		return listDtl;
	}

	public void setListDtl(List<TestRunDetail> listDtl) {
		this.listDtl = listDtl;
	}

	public String getFromDtStr() {
		return fromDtStr;
	}

	public void setFromDtStr(String fromDtStr) {
		this.fromDtStr = fromDtStr;
	}

	public String getThruDtStr() {
		return thruDtStr;
	}

	public void setThruDtStr(String thruDtStr) {
		this.thruDtStr = thruDtStr;
	}

	@Override
	public String toString() {
		return "TcExeResultDTO [fromDt=" + fromDt + ", thruDt=" + thruDt + ", fromDtStr=" + fromDtStr
				+ ", thruDtStr=" + thruDtStr + ", tcRunHdrId=" + tcRunHdrId + ", tcRunHdr=" + tcRunHdr
				+ ", pageAction=" + pageAction + ", list=" + list + ", listDtl=" + listDtl + "]";
	}

	
}
