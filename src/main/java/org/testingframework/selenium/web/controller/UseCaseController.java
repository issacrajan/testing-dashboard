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

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.testingframework.selenium.dto.UseCaseDTO;
import org.testingframework.selenium.service.UseCaseService;
import org.testingframework.selenium.utils.Utils;

@Controller
public class UseCaseController {

	@Autowired
	private UseCaseService useCaseService = null;

	@RequestMapping(value = "/usecaseedit", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("usecase") UseCaseDTO u, BindingResult result,
			SessionStatus status, final RedirectAttributes redirectAttributes) {
		
		String userId = LoginController.loginUserId();
		u.setModifiedBy(userId);
		u.setModifiedDt(new Date());
		useCaseService.updateUseCaseRemarks(u);

		// Add message to flash scope
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Use Case updated successfully!");

		return "redirect:usecaseedit/" + u.getId();
	}

	@RequestMapping(value = "/usecaseedit/{id}", method = RequestMethod.GET)
	public String showUser(@PathVariable("id") String id, Model model) {

		if (!Utils.isNumber(id)){
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "Invalid Request - ");
			return "usecaseedit";
		}

		UseCaseDTO dto = useCaseService.findUseCaseById(Long.valueOf(id));

		if (dto == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "User not found");
		}
		model.addAttribute("usecase", dto);

		return "usecaseedit";
	}
}