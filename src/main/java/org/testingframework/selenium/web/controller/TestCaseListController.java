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

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.testingframework.selenium.dto.TestCaseDTO;
import org.testingframework.selenium.dto.UseCaseDTO;
import org.testingframework.selenium.service.TestCaseService;
import org.testingframework.selenium.service.UseCaseService;
import org.testingframework.selenium.utils.Utils;

@Controller
public class TestCaseListController {

	@Autowired
	private TestCaseService tcService = null;

	@Autowired
	private UseCaseService ucService = null;

	@RequestMapping(value = "/tclist/{id}", method = RequestMethod.GET)
	public String showUsecase(@PathVariable("id") String ucid, ModelMap model, HttpServletRequest request) {
		Calendar cal = Calendar.getInstance();
		boolean monthWkDateFound = false;
		List<TestCaseDTO> list = null;
		String userFlag = request.getParameter("user");
		boolean forUser = false;
		if ("Y".equals(userFlag)) {
			forUser = true;
		}
		if ("M".equals(ucid)) {
			// month
			cal.set(Calendar.DAY_OF_MONTH, 1);
			monthWkDateFound = true;
		} else if ("W".equals(ucid)) {
			cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			monthWkDateFound = true;
		} else if ("D".equals(ucid)) {
			monthWkDateFound = true;
		}

		if (monthWkDateFound) {
			if (forUser) {
				list = tcService.getTestCaseForDashboardForUser(cal.getTime(), LoginController.loginUserId());
			} else {
				list = tcService.getTestCaseForDashboard(cal.getTime());
			}

		} else if (ucid != null) {
			// validate input
			if (!Utils.isNumber(ucid)) {
				model.addAttribute("msg", "Invalid Request... Not Test Cases Found");
				return "tclist";
			}

			Long lngUcId = Long.valueOf(ucid);

			list = tcService.getTestCaseForUseCase(lngUcId);
			UseCaseDTO useCaseDTO = ucService.findUseCaseById(lngUcId);
			if (useCaseDTO == null) {
				model.addAttribute("msg", "Invalid Request... Not Test Cases Found");
			} else {
				model.addAttribute("usecase", useCaseDTO);
			}
		}
		model.addAttribute("list", list);
		if (list == null || list.isEmpty()) {
			model.addAttribute("msg2", "Not Test Cases Found");
		}
		return "tclist";
	}

	@RequestMapping(value = "/tcview/{id}", method = RequestMethod.GET)
	public String showTestCase(@PathVariable("id") String tcid, ModelMap model) {
		// validate input
		if (!Utils.isNumber(tcid)) {
			model.addAttribute("msg", "Invalid Request... Not Test Cases Found");
			return "tcview";
		}

		if (tcid != null) {
			Long lngtcid = Long.valueOf(tcid);

			TestCaseDTO tc = tcService.getOneTestCase(lngtcid);
			model.addAttribute("tc", tc);

			UseCaseDTO useCaseDTO = ucService.findUseCaseById(tc.getUcid());
			model.addAttribute("usecase", useCaseDTO);
		}

		return "tcview";
	}

	@RequestMapping(value = "/updatetc_exe_order")
	public @ResponseBody
	String getSearchResultViaAjax(HttpServletRequest request) {

		String tcid = request.getParameter("tcid");
		String newOrder = request.getParameter("neworder");
		if (!Utils.isNumber(tcid)) {
			return "xx";
		}
		if (!Utils.isNumber(newOrder)) {
			return "xxx";
		}

		Long lngTcId = Long.parseLong(tcid);
		Integer intExeOrder = Integer.parseInt(newOrder);
		tcService.updateTestCaseExecutionOrder(lngTcId, intExeOrder);

		return newOrder;

	}
}