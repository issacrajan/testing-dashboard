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

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.testingframework.selenium.dao.TestCaseDao;
import org.testingframework.selenium.dto.TcRunHdr;
import org.testingframework.selenium.dto.TestCaseDTO;
import org.testingframework.selenium.dto.UseCaseDTO;
import org.testingframework.selenium.dto.UserInfo;


@Transactional
public class TestCaseService {
	
	private TestCaseDao testCaseDao = null;
	
	public List<TestCaseDTO> allTC() {
		return testCaseDao.allTC();
	}

	public List<TestCaseDTO> selectForModuleName(String moduleName) {
		return testCaseDao.selectForModuleName(moduleName);
	}

	
	public List<TestCaseDTO> selectForUseCase(Long ucid) {
		return testCaseDao.selectForUseCase(ucid);
	}

	
	public List<TestCaseDTO> getDuplicateTCByClassMethod() {
		return testCaseDao.getDuplicateTCByClassMethod();
	}

	
	public TestCaseDTO selectTC(TestCaseDTO dto) {
		return testCaseDao.selectTC(dto);
	}

	public void insert(TestCaseDTO dto) {
		testCaseDao.insert(dto);
	}

	public void update(TestCaseDTO dto) {
		testCaseDao.update(dto);
	}
	
	public void updateTestCaseExecutionOrder(Long tcId, Integer exeOrder) {
		testCaseDao.updateTestCaseExecutionOrder(tcId, exeOrder);
	}
	
	public void updateTestCaseSupportFileName(String id, String fileName) {
		testCaseDao.updateTestCaseSupportFileName(id, fileName);
	}
	
	public TestCaseDTO getOneTestCase(Long id){
		return testCaseDao.getOneTestCase(id);
	}
	public List<TestCaseDTO> getTestCaseForUseCase(Long ucid){
		return testCaseDao.getTestCaseForUseCase(ucid);
	}
	
	public List<TestCaseDTO> getTestCaseForDashboard(Date dt){
		return testCaseDao.getTestCaseForDashboard(dt);
	}
	public List<TestCaseDTO> getTestCaseForDashboardForUser(Date dt, String userId){
		return testCaseDao.getTestCaseForDashboardForUser(dt, userId);
	}
	public void updateTestCaseDesc(TestCaseDTO dto){
		testCaseDao.update(dto);
	}
	public Long countTc(Date dt){
		return testCaseDao.countTc(dt);
	}
	public Long countUserTc(TestCaseDTO dto) { 
		return testCaseDao.countUserTc(dto);
	}
	public Long getTestCaseCountForUseCase(Long ucId){
		return testCaseDao.getTestCaseCountForUseCase(ucId);
		
	}
	
	public List<UseCaseDTO> moduleWiseCnt() {
		return testCaseDao.moduleWiseCnt();
	}
	public List<TcRunHdr> selectLatestExecutionDtl() {
		return testCaseDao.selectLatestExecutionDtl();
	}

	public List<TcRunHdr> selectLatestExecutionDtl(UserInfo u) {
		return testCaseDao.selectLatestExecutionDtl(u);
	}
	
	
	public TestCaseDao getTestCaseDao() {
		return testCaseDao;
	}

	public void setTestCaseDao(TestCaseDao testCaseDao) {
		this.testCaseDao = testCaseDao;
	}
	
	
}
