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

package org.testingframework.selenium.service;

import org.testingframework.selenium.dao.RunTestDao;
import org.testingframework.selenium.dto.TcExeResultDTO;
import org.testingframework.selenium.dto.TcRunDtl;
import org.testingframework.selenium.dto.TcRunHdr;
import org.testingframework.selenium.dto.TestCaseRunFileDTO;

public class RunTestService {

	private RunTestDao runTestDao = null;
	
	public void insertHdr(TcRunHdr dto) {
		runTestDao.insertHdr(dto);
	}
	public void updateHdr(TcRunHdr dto) {
		runTestDao.updateHdr(dto);
	}
	
	public void insertDtl(TcRunDtl dto) {
		runTestDao.insertDtl(dto);
	}
	
	public void insertDtlImage(TestCaseRunFileDTO dto) {
		runTestDao.insertDtlImage(dto);
	}
	public RunTestDao getRunTestDao() {
		return runTestDao;
	}
	public void setRunTestDao(RunTestDao runTestDao) {
		this.runTestDao = runTestDao;
	}
		
	
	public void searchTcRunHdr(TcExeResultDTO dto){
		this.runTestDao.searchTcRunHdr(dto);
	}
	
	public void searchTcRunDtl(TcExeResultDTO dto){
		this.runTestDao.searchTcRunDtlNew(dto);
	}
	public Long tcRunDtlCount(TcExeResultDTO dto) {
		return runTestDao.tcRunDtlCount(dto);
	}
	
	public TcRunDtl getTcRunDtl(Long id){
		return runTestDao.getTcRunDtl(id);
	}
	
	public TestCaseRunFileDTO getOneTestCaseRunFile(Long id){
		return runTestDao.getOneTestCaseRunFile(id);
	}
}
