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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.testingframework.selenium.dto.TestSuiteHdrDTO;
import org.testingframework.selenium.dto.UseCaseDTO;
import org.testingframework.selenium.dto.UserInfo;
import org.testingframework.selenium.dto.UserPermissionDTO;
import org.testingframework.selenium.service.UserService;

@Controller
public class UserPermissionController {

	@Autowired
	private UserService userService = null;

	@RequestMapping(value = "/userpermission", method = RequestMethod.GET)
	public String showUser(Model model, HttpServletRequest request) {
		String id = request.getParameter("id");
		UserInfo user = userService.getUser(id);
		UserPermissionDTO up = new UserPermissionDTO();

		if (user == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "User not found " + id);
		} else {
			up.setUserId(user.getUserId());
			up.setUserName(user.getUserName());

			List<UseCaseDTO> ucList = userService.getUserUseCasePermission(user.getUserId());
			up.setUcList(ucList);

			List<TestSuiteHdrDTO> tsList = userService.getUserTCPermission(user.getUserId());
			up.setTsList(tsList);
		}
		model.addAttribute("up", up);

		return "userpermission";
	}

	@RequestMapping(value = "/userpermission", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("up") UserPermissionDTO u, BindingResult result,
			SessionStatus status, final RedirectAttributes redirectAttributes) {

		boolean anyUpdate = userService.saveUserPermission(u);

		// Add message to flash scope
		redirectAttributes.addFlashAttribute("css", "success");
		if (anyUpdate) {
			redirectAttributes.addFlashAttribute("msg", "User Permission updated successfully!");
		} else {
			redirectAttributes.addFlashAttribute("msg", "No Changes done!!");
		}

		return "redirect:userpermission?id=" + u.getUserId();
	}

}