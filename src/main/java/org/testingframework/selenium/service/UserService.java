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
import org.testingframework.selenium.dao.UseCaseDao;
import org.testingframework.selenium.dao.UserDao;
import org.testingframework.selenium.dto.TestSuiteHdrDTO;
import org.testingframework.selenium.dto.UseCaseDTO;
import org.testingframework.selenium.dto.UserInfo;
import org.testingframework.selenium.dto.UserPermissionDTO;
import org.testingframework.selenium.dto.UserTestSuiteDTO;
import org.testingframework.selenium.dto.UserUseCaseDTO;

@Transactional
public class UserService {

	private UserDao userDao = null;
	private UseCaseDao useCaseDao = null;
	private TestSuiteDao testSuiteDao = null;
	
	@Transactional
	public UserInfo getUser(String userId) {
		return this.userDao.find(userId);
	}

	public List<UserInfo> getAllUsers() {
		return this.userDao.getAllUser();
	}

	public void saveUser(UserInfo u) {
		if (u.isNew()) {
			userDao.insertUser(u);
		} else {
			userDao.updateUser(u);
		}
	}

	public void updatePwd(UserInfo u) {
		userDao.updatePwd(u);
	}

	public void updatePostLogin(UserInfo u) {
		userDao.updatePostLogin(u);
	}

	public UserInfo findByRememberMe(UserInfo u) {
		return userDao.findByRememberMe(u.getRememberMe());
	}

	public List<UseCaseDTO> getUserUseCasePermission(String userId){
		List<UseCaseDTO> ucList = useCaseDao.getAllUseCase();
		UserUseCaseDTO userUcDTO = new UserUseCaseDTO();
		userUcDTO.setUserId(userId);
		
		for (UseCaseDTO dto : ucList){
			userUcDTO.setUcid(dto.getId());
			if (userDao.countUserUserCase(userUcDTO) > 0){
				dto.setSelected("Y");
			}
		}
		return ucList;
	}
	public List<TestSuiteHdrDTO> getUserTCPermission(String userId){
		List<TestSuiteHdrDTO> tsList = testSuiteDao.selectAllTestSuiteHdr();
		UserTestSuiteDTO userTcDTO = new UserTestSuiteDTO();
		userTcDTO.setUserId(userId);
		
		for (TestSuiteHdrDTO dto : tsList){
			userTcDTO.setSuiteid(dto.getId());
			if (userDao.countUserTestSuite(userTcDTO) > 0){
				dto.setSelected("Y");
			}
		}
		return tsList;
	}
	
	public boolean saveUserPermission(UserPermissionDTO upDTO){
		String userId = upDTO.getUserId();
		int cnt ;
		boolean anyUpdate = false;
		
		//Update test suite
		List<TestSuiteHdrDTO> tsList = upDTO.getTsList();
		
		for (TestSuiteHdrDTO d : tsList){
			UserTestSuiteDTO userTestSuite = new UserTestSuiteDTO();
			userTestSuite.setUserId(userId);
			userTestSuite.setSuiteid(d.getId());
			cnt = userDao.countUserTestSuite(userTestSuite);
			if ("Y".equals(d.getSelected())){
				//insert if not found
				if (cnt <=0){
					userDao.insertUserTestSuite(userTestSuite);
					anyUpdate = true;
				}
			} else {
				//delete if found
				if (cnt > 0){
					userDao.deleteOneUserTestSuite(userTestSuite);
					anyUpdate = true;
				}
			}
		}
		
		//update test case
		List<UseCaseDTO> ucList = upDTO.getUcList();
		
		for (UseCaseDTO d : ucList){
			UserUseCaseDTO userUseCase = new UserUseCaseDTO();
			userUseCase.setUserId(userId);
			userUseCase.setUcid(d.getId());
			Long recCnt = userDao.countUserUserCase(userUseCase);
			if ("Y".equals(d.getSelected())){
				//insert if not found
				if (recCnt <=0){
					userDao.insertUserUseCase(userUseCase);
					anyUpdate = true;
				}
			} else {
				//delete if found
				if (recCnt > 0){
					userDao.deleteOneUserUserCase(userUseCase);
					anyUpdate = true;
				}
			}
		}
		return anyUpdate;
	}
	
	
	
	//----------------------------------------------------------
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public UseCaseDao getUseCaseDao() {
		return useCaseDao;
	}

	public void setUseCaseDao(UseCaseDao useCaseDao) {
		this.useCaseDao = useCaseDao;
	}

	public TestSuiteDao getTestSuiteDao() {
		return testSuiteDao;
	}

	public void setTestSuiteDao(TestSuiteDao testSuiteDao) {
		this.testSuiteDao = testSuiteDao;
	}
	

}
