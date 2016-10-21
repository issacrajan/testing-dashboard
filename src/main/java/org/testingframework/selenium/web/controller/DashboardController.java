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
import org.testingframework.selenium.dto.UseCaseDTO;
import org.testingframework.selenium.service.TestCaseService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	
	@Autowired
	private TestCaseService testCaseService = null;
	
	@RequestMapping(method = RequestMethod.GET)
    public String sayHello(ModelMap model) {
		
		Calendar cal = Calendar.getInstance();
		//Today
		Long todayCnt = testCaseService.countTc(cal.getTime());
		model.addAttribute("todayCnt", todayCnt);
		
		//Week
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		System.out.println(cal.getTime());
		model.addAttribute("weekCnt", testCaseService.countTc(cal.getTime()));
		
		//Month
		cal.set(Calendar.DAY_OF_MONTH, 1);
		System.out.println(cal.getTime());
		model.addAttribute("mthCnt", testCaseService.countTc(cal.getTime()));
		
		//latest execution details
		List<TcRunHdr> list =  testCaseService.selectLatestExecutionDtl();
		model.addAttribute("exList", list);
		List<UseCaseDTO> mList = testCaseService.moduleWiseCnt();
		model.addAttribute("mList", mList);
		
        return "dashboard";
    }
 
    
}