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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.testingframework.selenium.dto.TestSuiteDetailDTO;
import org.testingframework.selenium.dto.TestSuiteHdrDTO;
import org.testingframework.selenium.service.TestSuiteService;
import org.testingframework.selenium.utils.Constants;
import org.testingframework.selenium.utils.Utils;

@Controller
public class TestSuiteTestCaseListController {

	@Autowired
	private TestSuiteService tsService = null;

	@RequestMapping(value = "/testsuitetestcaselist", method = RequestMethod.GET)
	public String showTestSuiteTestCaseList(Model model, HttpServletRequest request) {
		String id = request.getParameter("id");
		String start = request.getParameter("start");
		// validate inputs
		boolean validInput = true;
		if (!Utils.isNumber(id)) {
			validInput = false;
		}
		if (Utils.hasContent(start) && !Utils.isNumber(start)) {
			validInput = false;
		}
		if (!validInput) {
			model.addAttribute("msg", "Invalid Request Inputs");
			return "testsuitetestcaselist";
		}

		Long testSuitId = Long.valueOf(id);
		TestSuiteHdrDTO ts = tsService.getTestSuite(testSuitId);

		if (ts == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "Test Suite not found");
		}
		model.addAttribute("ts", ts);
		int intStart = 0;
		if (Utils.isNumber(start)){
			intStart = Integer.parseInt(start);
		}
		TestSuiteDetailDTO tcList = tsService.getTestCaseDetilForTestSuite(testSuitId, intStart);
		
		if (Utils.isEmpty(start)){
			//new request, set count to session
			HttpSession session = request.getSession();
			session.setAttribute(Constants.TOTAL_COUNT_KEY, tcList.getRecCount());
		}
		
		model.addAttribute("tcList", tcList);

		return "testsuitetestcaselist";
	}

}