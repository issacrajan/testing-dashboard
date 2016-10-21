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
package org.testingframework.selenium.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.testingframework.selenium.dto.UseCaseDTO;
import org.testingframework.selenium.service.ModuleService;
import org.testingframework.selenium.service.TestCaseService;
import org.testingframework.selenium.service.UseCaseService;

@Controller
public class UseCaseListController {

	@Autowired
	private UseCaseService usecaseService = null;
	
	@Autowired
	private ModuleService mService = null;
	
	@Autowired
	private TestCaseService tService = null;
	
	@RequestMapping(value = "/usecaselist", method = RequestMethod.GET)
	public String initPage(ModelMap model) {
		UseCaseDTO u = new UseCaseDTO();
		model.addAttribute("usecase", u);
		model.addAttribute("allModule", mService.getAllModule());
		return "usecaselist";
	}
	
	@RequestMapping(value = "/usecaselist", method = RequestMethod.POST)
	public String displayUseCase(@ModelAttribute("usecase") UseCaseDTO u, ModelMap model) {
		
		String moduleName = u.getModuleName();
		List<UseCaseDTO> list = getUseCaseForModule(moduleName);
		model.addAttribute("allModule", mService.getAllModule());
		model.addAttribute("list", list);
		if (list ==  null || list.isEmpty()){
			model.addAttribute("msg2", "No Use Case Found ");
		}
		return "usecaselist";
	}
	
	
	@RequestMapping(value = "/usecaselist/{id}", method = RequestMethod.GET)
	public String showUsecase(@PathVariable("id") String id, ModelMap model) {
		if (id != null){
			List<UseCaseDTO> list = getUseCaseForModule(id);
			model.addAttribute("list", list);
		}
		UseCaseDTO u = new UseCaseDTO();
		u.setModuleName(id);
		model.addAttribute("usecase", u);
		model.addAttribute("allModule", mService.getAllModule());
		return "usecaselist";
	}
	
	private List<UseCaseDTO> getUseCaseForModule(String moduleName){
		Long tcCnt = null;
		List<UseCaseDTO> list = usecaseService.getUseCaseForModule(moduleName);
		for (UseCaseDTO dto : list){
			tcCnt = tService.getTestCaseCountForUseCase(dto.getId());
			dto.setTcCnt(tcCnt);
		}
		
		return list;
	}
	

	
}