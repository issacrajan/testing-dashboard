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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.testingframework.selenium.dto.TcExeResultDTO;
import org.testingframework.selenium.service.RunTestService;
import org.testingframework.selenium.utils.Constants;
import org.testingframework.selenium.utils.Utils;
import org.testingframework.selenium.web.validator.TcExecutionSearchValidator;

@Controller
public class TcExeResultController {

	@Autowired
	private RunTestService runTestService = null;
	@Autowired
	TcExecutionSearchValidator validator;

	// Set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(value = "/tcexeresult", method = RequestMethod.GET)
	public String initPage(ModelMap model) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		TcExeResultDTO dto = new TcExeResultDTO();
		String todayStr = sdf.format(new Date());
		dto.setFromDtStr(todayStr);
		dto.setThruDtStr(todayStr);
		model.addAttribute("r", dto);
		return "tcexeresult";
	}

	@RequestMapping(value = "/tcexeresult", method = RequestMethod.POST)
	public String displayUseCase(@ModelAttribute("r") @Validated TcExeResultDTO u, BindingResult result,
			ModelMap model) {
		if (result.hasErrors()) {
			return "tcexeresult";
		}
		runTestService.searchTcRunHdr(u);
		List list = u.getList();
		if (list == null || list.isEmpty()){
			model.addAttribute("ResultNotFound", "Y");
		}
		model.addAttribute("r", u);
		return "tcexeresult";
	}

	@RequestMapping(value = "/tc_execution_dtl", method = RequestMethod.GET)
	public String showTcExecutionResult(ModelMap model, HttpServletRequest request) {
		
		String testResultId = request.getParameter("id");
		String start = request.getParameter("start");
		
		//validate inputs
		boolean validInput = true;
		if (!Utils.isNumber(testResultId)){
			validInput = false;
		}
		if (Utils.hasContent(start) && !Utils.isNumber(start)){
			validInput = false;
		}
		if (!validInput){
			model.addAttribute("msg", "Invalid Request Inputs");
			return "tc_execution_dtl";
		}
		
		//fetch and store test results
		TcExeResultDTO tc = new TcExeResultDTO();
		tc.setTcRunHdrId(Long.parseLong(testResultId));
		tc.setStart(start);
		if (Utils.isEmpty(start)){
			//new request, calculate count and set to session
			Long cnt = runTestService.tcRunDtlCount(tc);
			HttpSession session = request.getSession();
			session.setAttribute(Constants.TOTAL_COUNT_KEY, cnt);
		}
		
		runTestService.searchTcRunDtl(tc);
		model.addAttribute("r", tc);
		
		return "tc_execution_dtl";
	}
}