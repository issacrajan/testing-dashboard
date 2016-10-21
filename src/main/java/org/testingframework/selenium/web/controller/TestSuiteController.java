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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.testingframework.selenium.dto.TestSuiteHdrDTO;
import org.testingframework.selenium.service.TestSuiteService;
import org.testingframework.selenium.web.validator.TestSuiteValidator;

@Controller
public class TestSuiteController {

	@Autowired
	private TestSuiteService ucService = null;
	@Autowired
	TestSuiteValidator tsValidator;

	// Set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(tsValidator);
	}

	@RequestMapping(value="/testsuitelist", method = RequestMethod.GET)
    public String sayHello(ModelMap model) {
       
        List<TestSuiteHdrDTO> list = ucService.selectAllTestSuite();
        model.addAttribute("list", list);
        return "testsuitelist";
    }
	@RequestMapping(value = "/testsuite", method = RequestMethod.GET)
	public String initTestSuitePage(ModelMap model) {
		TestSuiteHdrDTO u = new TestSuiteHdrDTO();
		u.setEditMode("N");
		model.addAttribute("ts", u);
		return "testsuite";
	}

	@RequestMapping(value = "/testsuite", method = RequestMethod.POST)
	public String saveTestSuite(@ModelAttribute("ts") @Validated TestSuiteHdrDTO u, BindingResult result,
			SessionStatus status, final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "testsuite";
		}
		ucService.saveTestSuite(u);

		// Add message to flash scope
		redirectAttributes.addFlashAttribute("css", "success");
		if (u.isNew()) {
			redirectAttributes.addFlashAttribute("msg", "Test Suite added successfully!");
		} else {
			redirectAttributes.addFlashAttribute("msg", "Test Suite updated successfully!");
		}
		return "redirect:testsuite/" + u.getId();
	}

	@RequestMapping(value = "/testsuite/{id}", method = RequestMethod.GET)
	public String showTestSuite(@PathVariable("id") String id, Model model) {

		TestSuiteHdrDTO ts = ucService.getTestSuite(Long.valueOf(id));

		if (ts == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "Test Suite not found");
		} else {
			ts.setEditMode("Y");
		}
		model.addAttribute("ts", ts);

		return "testsuite";
	}
	
}