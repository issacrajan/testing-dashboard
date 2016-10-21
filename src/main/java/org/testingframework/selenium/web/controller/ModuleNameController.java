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

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.testingframework.selenium.dto.ModuleDTO;
import org.testingframework.selenium.service.ModuleService;
import org.testingframework.selenium.web.validator.ModuleFormValidator;

@Controller
public class ModuleNameController {

	@Autowired
	private ModuleService mService = null;
	@Autowired
	ModuleFormValidator validator;

	// Set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(value = "/modulelist", method = RequestMethod.GET)
	public String moduleListPage(ModelMap model) {
		List<ModuleDTO> list = mService.getAllModule();
		model.addAttribute("list", list);

		return "modulelist";
	}

	@RequestMapping(value = "/moduleedit", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("module") @Validated ModuleDTO m, BindingResult result,
			SessionStatus status, final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "moduleedit";
		}
		mService.updateModuleDesc(m);

		// Add message to flash scope
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Module Desc updated successfully!");

		return "redirect:moduleedit?id=" + m.getModuleName();
	}

	@RequestMapping(value = "/moduleedit", method = RequestMethod.GET)
	public String showUser( Model model, HttpServletRequest request) {
		String id = request.getParameter("id");
	
		ModuleDTO m = mService.getModuleInfo(id);

		if (m == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "Module not found");
		}
		model.addAttribute("module", m);

		return "moduleedit";

	}
}