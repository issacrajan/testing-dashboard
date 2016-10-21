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

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.testingframework.selenium.dto.TcRunHdr;
import org.testingframework.selenium.dto.TestCaseDTO;
import org.testingframework.selenium.dto.UseCaseDTO;
import org.testingframework.selenium.dto.UserInfo;
import org.testingframework.selenium.service.TestCaseService;

@Controller
@RequestMapping("/mydashboard")
public class MyDashboardController {


	@Autowired
	private TestCaseService testCaseService = null;
	
	@RequestMapping(method = RequestMethod.GET)
    public String sayHello(ModelMap model) {
		
		TestCaseDTO dto = new TestCaseDTO();
		String userId = LoginController.loginUserId();
		dto.setTcCreatedBy(userId);
		
		Calendar cal = Calendar.getInstance();
		//Today
		dto.setTcCreationDate(cal.getTime());
		Long todayCnt = testCaseService.countUserTc(dto); 
		model.addAttribute("todayCnt", todayCnt);
		
		//Week
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		dto.setTcCreationDate(cal.getTime());
		model.addAttribute("weekCnt", testCaseService.countUserTc(dto));
		
		//Month
		cal.set(Calendar.DAY_OF_MONTH, 1);
		dto.setTcCreationDate(cal.getTime());
		model.addAttribute("mthCnt", testCaseService.countUserTc(dto));
		
		//latest execution details
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(userId);
		List<TcRunHdr> list =  testCaseService.selectLatestExecutionDtl();
		model.addAttribute("exList", list);
		
		List<UseCaseDTO> mList = testCaseService.moduleWiseCnt();
		model.addAttribute("mList", mList);
		
        return "mydashboard";
    }
 
    
}