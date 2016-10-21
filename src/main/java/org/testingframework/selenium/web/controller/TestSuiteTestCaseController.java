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


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.testingframework.selenium.dto.ModuleDTO;
import org.testingframework.selenium.dto.TestCaseDTO;
import org.testingframework.selenium.dto.TestSuiteAddTcDTO;
import org.testingframework.selenium.dto.TestSuiteDtlDTO;
import org.testingframework.selenium.dto.TestSuiteHdrDTO;
import org.testingframework.selenium.dto.UseCaseDTO;
import org.testingframework.selenium.service.ModuleService;
import org.testingframework.selenium.service.TestCaseService;
import org.testingframework.selenium.service.TestSuiteService;
import org.testingframework.selenium.service.UseCaseService;
import org.testingframework.selenium.utils.Constants;
import org.testingframework.selenium.utils.Utils;

@Controller
public class TestSuiteTestCaseController {

	@Autowired
	private TestSuiteService tsService = null;

	@Autowired
	private UseCaseService ucService = null;
	
	@Autowired
	private TestCaseService tcService = null;
	
	@Autowired
	private ModuleService mService = null;
	
	@RequestMapping(value = "/testsuiteaddtestcase", method = RequestMethod.GET)
	public String showTestSuiteAddTestCase(Model model, HttpServletRequest request) {
		//first time only tsid will be present
		//now fetch only module and display the page
		
		//2nd time tsid and module name will be present
		//now fetch module list and use case list 
		
		//3rd time tsid and ucid will be presnet
		//now fetch module name, use case list and test case NOT yet associated with the test suite will be displayed
		
		String tsId = request.getParameter("tsid");
		String moduleName = request.getParameter("m");
		String ucId = request.getParameter("ucid");
		String start = request.getParameter("start");
		
		if (!Utils.isNumber(tsId) || 
				(Utils.hasContent(ucId) && !Utils.isNumber(ucId)) ||
				(Utils.hasContent(start) && !Utils.isNumber(start))
				){
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "Test Suite not found - invalid request");
			return "testsuiteaddtestcase";
		}
		
		TestSuiteAddTcDTO ts = new TestSuiteAddTcDTO();
		model.addAttribute("ts", ts);
		
		//add test suite hdr
		Long testSuitId = Long.valueOf(tsId);
		TestSuiteHdrDTO tsHdr = tsService.getTestSuite(testSuitId);
		ts.setTsHdr(tsHdr);
		
		//request to fetch Use Case List 
		if (Utils.hasContent(moduleName)){
			ts.setModuleName(moduleName);
			//populate UC request
			addUseCaseToModel(ts, model);
			return "testsuiteaddtestcase";
		}
		
		//request to fetch Test Cases
		if (Utils.hasContent(ucId) && Utils.isNumber(ucId)){
			ts.setUcid(ucId);
			UseCaseDTO useCaseDTO = ucService.findUseCaseById(Long.valueOf(ucId));
			if (useCaseDTO == null){
				model.addAttribute("css", "danger");
				model.addAttribute("msg", "Test Suite/Use Case not found - invalid request");
			} else {
				ts.setStart(start);
				moduleName = useCaseDTO.getModuleName();
				ts.setModuleName(moduleName);
				addUseCaseToModel(ts, model);
		
				addTestCaseToModel(ts);
			}
			
		}
		return "testsuiteaddtestcase";
	}

	@RequestMapping(value = "/testsuiteaddtestcase", method = RequestMethod.POST)
	public String submitTestSuiteAddTestCase(@ModelAttribute("ts") @Validated TestSuiteAddTcDTO ts,
			Model model, RedirectAttributes redirectAttributes) {
		String pageAction = ts.getPageAction();
		String tsId = String.valueOf(ts.getTsHdr().getId());
		String ucId = ts.getUcid();
		if ("SAVE".equals(pageAction)) {
			//addUseCaseToModel(ts, model);
			saveTc(ts);
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "test suite changes successfully saved !!");
			return "redirect:testsuiteaddtestcase?tsid=" + tsId + "&ucid=" + ucId;
		} else {
			throw new RuntimeException("invalid page action " + pageAction);
		}
	}

	private void addUseCaseToModel(TestSuiteAddTcDTO ts, Model model) {
		// select UC
		String moduleName = ts.getModuleName();
		model.addAttribute("allUC", ucService.getUseCaseForModule(moduleName));
	}

	private void addTestCaseToModel(TestSuiteAddTcDTO ts) {

		// fetch all Test Cases for the Selected Use Case
		String startStr = ts.getStart();
		int start = 0;
		if (Utils.isNumber(startStr)) {
			start = Integer.parseInt(startStr);
		}
		int end = start + (int) Constants.PAGE_SIZE;
		List<TestCaseDTO> tcList = tcService.getTestCaseForUseCase(Long.valueOf(ts.getUcid()));
		List<TestCaseDTO> notSelectedList = new ArrayList<TestCaseDTO>();
		int cnt = -1;
		TestSuiteDtlDTO suiteDtl = new TestSuiteDtlDTO();
		TestSuiteDtlDTO suiteDtlUpdate = null;
		suiteDtl.setSuiteId(ts.getTsHdr().getId());
		for (TestCaseDTO dto : tcList) {
			suiteDtl.setTcId(dto.getId());
			suiteDtlUpdate = tsService.getTestCaseInTestSuite(suiteDtl);
			
			if (suiteDtlUpdate == null) {
				cnt++;
				if (cnt <start){
					continue;
				}
				if (cnt >= end){
					continue;
				}
				//test case not already added
				notSelectedList.add(dto);
			}
		}
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		cnt++;
		String tmp = String.valueOf(cnt);
		session.setAttribute(Constants.TOTAL_COUNT_KEY, Long.valueOf(tmp));
		ts.setTcList(notSelectedList);

	}

	private void saveTc(TestSuiteAddTcDTO ts) {
		Long testSuitId = ts.getTsHdr().getId();
		TestSuiteDtlDTO suiteDtl = new TestSuiteDtlDTO();
		suiteDtl.setSuiteId(testSuitId);
		TestSuiteDtlDTO suiteDtlUpdate = null;
		List<TestCaseDTO> list = ts.getTcList();
		for (TestCaseDTO dto : list) {
			suiteDtl.setTcId(dto.getId());
			suiteDtl.setExecutionOrder(9999);
			suiteDtlUpdate = tsService.getTestCaseInTestSuite(suiteDtl);
			
			if ("Y".equals(dto.getSelected())) {
				
				if (suiteDtlUpdate == null) {
					// insert
					tsService.insertTestSuiteDtl(suiteDtl);
				} else {
					// update order
					if (suiteDtlUpdate.getExecutionOrder() != dto.getExecutionOrder()) {
						suiteDtlUpdate.setExecutionOrder(dto.getExecutionOrder());
						tsService.updateExeOrderTestSuiteDtl(suiteDtlUpdate);
					}
				}
			} else {
				if (suiteDtlUpdate != null) {
					// delete
					tsService.deleteTestCaseInTestSuite(suiteDtlUpdate);
				}
			}
		}
	}

	@ModelAttribute("allModule")
	public List<ModuleDTO> getAllModule() {
		return mService.getAllModule();
	}
	
	
	@RequestMapping(value = "/updatets_tc_exe_order")
	public @ResponseBody
	String getSearchResultViaAjax(HttpServletRequest request) {
		String tsid = request.getParameter("tsid");
		String tcid = request.getParameter("tcid");
		String newOrder = request.getParameter("neworder");
		if (!Utils.isNumber(tcid) || 
				!Utils.isNumber(tsid) ||
				!Utils.isNumber(newOrder)
				) {
			return "xx";
		}
		TestSuiteDtlDTO suiteDtl = new TestSuiteDtlDTO();
		suiteDtl.setSuiteId(Long.valueOf(tsid));
		suiteDtl.setTcId(Long.valueOf(tcid));
	
		
		TestSuiteDtlDTO suiteDtlUpdate = tsService.getTestCaseInTestSuite(suiteDtl);
		suiteDtlUpdate.setExecutionOrder(Integer.valueOf(newOrder));
		tsService.updateExeOrderTestSuiteDtl(suiteDtlUpdate);
	
		return newOrder;

	}
}