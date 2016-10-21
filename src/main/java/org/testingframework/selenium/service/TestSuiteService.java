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

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.testingframework.selenium.dao.TestSuiteDao;
import org.testingframework.selenium.dto.TestSuiteDetailDTO;
import org.testingframework.selenium.dto.TestSuiteDtlDTO;
import org.testingframework.selenium.dto.TestSuiteHdrDTO;

@Transactional
public class TestSuiteService {

	private TestSuiteDao testSuiteDao = null;

	public List<TestSuiteHdrDTO> selectAllTestSuite() {
		List<TestSuiteHdrDTO> list = testSuiteDao.selectAllTestSuiteHdr();
		for (TestSuiteHdrDTO d : list){
			d.setCnt(testSuiteDao.tcCountForTesSuite(d.getId()));
		}
		return list;
	}

	
	public List<TestSuiteHdrDTO> selectAllTestSuiteHdr(){
		return testSuiteDao.selectAllTestSuiteHdr();
	}
	
	public TestSuiteHdrDTO getTestSuite(Long id){
		return testSuiteDao.getTestSuite(id);
	}
	public boolean isTestSuiteExists(String tsName){
		return testSuiteDao.getTestSuiteByName(tsName) != null;
	}
	public TestSuiteHdrDTO getTestSuiteByName(String tsName){
		return testSuiteDao.getTestSuiteByName(tsName);
	}
	public void saveTestSuite(TestSuiteHdrDTO dto){
		if (dto.isNew()){
			insertTestSuite(dto);
		} else {
			updateTestSuite(dto);
		}
	}

	public void insertTestSuite(TestSuiteHdrDTO dto){
		testSuiteDao.insertTestSuite(dto);
	}

	
	public void updateTestSuite(TestSuiteHdrDTO dto){
		testSuiteDao.updateTestSuite(dto);
	}

	public TestSuiteDetailDTO getTestCaseDetilForTestSuite(Long id, int start){
		return testSuiteDao.getTestCaseDetilForTestSuite(id, start);
	}

	public TestSuiteDtlDTO getTestCaseInTestSuite(TestSuiteDtlDTO dto){
		return testSuiteDao.getTestCaseInTestSuite(dto);
	}

	public int checkTestCaseInTestSuite(TestSuiteDtlDTO dto){
		return testSuiteDao.checkTestCaseInTestSuite(dto);
	}

	public void deleteTestCaseInTestSuite(TestSuiteDtlDTO dto){
		testSuiteDao.deleteTestCaseInTestSuite(dto);
	}

	public void insertTestSuiteDtl(TestSuiteDtlDTO dtl){
		testSuiteDao.insertTestSuiteDtl(dtl);
	}

	public void updateExeOrderTestSuiteDtl(TestSuiteDtlDTO dtl){
		testSuiteDao.updateExeOrderTestSuiteDtl(dtl);
	}
	
	public TestSuiteDao getTestSuiteDao() {
		return testSuiteDao;
	}

	public void setTestSuiteDao(TestSuiteDao testSuiteDao) {
		this.testSuiteDao = testSuiteDao;
	}

	
}
