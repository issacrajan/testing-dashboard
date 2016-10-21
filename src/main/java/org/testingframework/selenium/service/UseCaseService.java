
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
import org.testingframework.selenium.dao.UseCaseDao;
import org.testingframework.selenium.dto.UseCaseDTO;

@Transactional
public class UseCaseService {

	private UseCaseDao useCaseDao = null;
	
	public List<UseCaseDTO> getAllUseCase(){
		return useCaseDao.getAllUseCase();
	}
	
	public List<UseCaseDTO> getUseCaseForModule(String moduleName){
		return useCaseDao.getUseCaseForModule(moduleName);
	}
	
	public void updateUseCase(UseCaseDTO dto){
		useCaseDao.updateUseCase(dto);
	}
	
	public void updateUseCaseRemarks(UseCaseDTO dto){
		useCaseDao.updateUseCaseRemarks(dto);
	}
	
	public UseCaseDTO findUseCaseById(Long id){
		return useCaseDao.findUseCaseById(id);
	}
	public UseCaseDTO findUseCaseByName(UseCaseDTO dto){
		return useCaseDao.findUseCaseByName(dto);
	}

	public UseCaseDao getUseCaseDao() {
		return useCaseDao;
	}

	public void setUseCaseDao(UseCaseDao useCaseDao) {
		this.useCaseDao = useCaseDao;
	}
	
	
}
