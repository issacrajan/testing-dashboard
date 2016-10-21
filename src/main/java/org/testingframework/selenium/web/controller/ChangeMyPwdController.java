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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.testingframework.selenium.dto.UserInfo;
import org.testingframework.selenium.service.UserService;
import org.testingframework.selenium.utils.CryptoUtils;
import org.testingframework.selenium.web.validator.ChangePwdValidator;

@Controller
public class ChangeMyPwdController {

	@Autowired
	private UserService userService = null;
	@Autowired
	ChangePwdValidator Validator;

	// Set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(Validator);
	}

	@RequestMapping(value = "/changemypwd", method = RequestMethod.GET)
	public String showChangePwdForm(Model model) {
		
		String userId = LoginController.loginUserId();

		UserInfo user = userService.getUser(userId);

		if (user == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "User not found");
		}
		model.addAttribute("user", user);

		return "changemypwd";
	}

	@RequestMapping(value = "/changemypwdresult", method = RequestMethod.GET)
	public String showChangeUserResultForm( Model model) {

		UserInfo user = userService.getUser(LoginController.loginUserId());

		if (user == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "User not found");
		}
		model.addAttribute("user", user);

		return "changemypwdresult";
	}

	@RequestMapping(value = "/changemypwd", method = RequestMethod.POST)
	public String changePwd(@ModelAttribute("user") @Validated UserInfo u, BindingResult result,
			SessionStatus status, final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "changemypwd";
		}

		// encrypt the pwd
		String pwd = u.getUserPwd();
		pwd = CryptoUtils.encrypt(pwd);
		u.setUserPwd(pwd);

		userService.updatePwd(u);

		// Add message to flash scope
		redirectAttributes.addFlashAttribute("css", "success");

		redirectAttributes.addFlashAttribute("msg", "My Password changed successfully!");

		return "redirect:changemypwdresult";
	}

}