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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.testingframework.selenium.dto.UserInfo;
import org.testingframework.selenium.service.UserService;
import org.testingframework.selenium.utils.CryptoUtils;
import org.testingframework.selenium.utils.Utils;
import org.testingframework.selenium.web.validator.LoginFormValidator;

@Controller
public class LoginController {

	@Autowired
	LoginFormValidator loginValidator;

	@Autowired
	private UserService userService = null;

	// Set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(loginValidator);
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String initPage(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		UserInfo user = new UserInfo();

		// new login, do not use token to validate
		String newLogin = request.getParameter("nl");

		// try login using remember me token
		String token = getCookieValue(request, "JV_TOKEN");
		if (Utils.hasContent(token) && !"Y".equals(newLogin)) {
			user.setRememberMe(token);
			user = userService.findByRememberMe(user);

			if (user != null) {
				// token is valid
				placeUserInSession(user, request);

				user.setLastLogingTime(new Date());
				userService.updatePostLogin(user);
				return "redirect:mydashboard";
			}
		}
		user = new UserInfo();
		model.addAttribute("login", user);
		return "login";
	}

	private String getCookieValue(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("login") @Validated UserInfo u, BindingResult result,
			SessionStatus status, HttpServletRequest request, HttpServletResponse response) {
		if (result.hasErrors()) {
			return "login";
		}

		placeUserInSession(u, request);

		// update last login time and token
		String token = null;
		u.setLastLogingTime(new Date());
		if ("Y".equals(u.getRememberMe())) {
			// create cookie and update response
			token = CryptoUtils.encrypt(u.getUserId() + "_ISSAC_" + u.getUserPwd());
			Cookie tokenCookie = new Cookie("JV_TOKEN", token);
			tokenCookie.setMaxAge(60 * 60 * 24);
			response.addCookie(tokenCookie);
			u.setRememberMe(token);
		} else {
			u.setRememberMe(null);
		}
		userService.updatePostLogin(u);

		return "redirect:mydashboard";
	}

	private void placeUserInSession(UserInfo u, HttpServletRequest request) {
		String userid = u.getUserId();
		String userrole = u.getUserRole();
		request.getSession().setAttribute("jvuserid", userid);
		request.getSession().setAttribute("jvuserrole", userrole);
	}

	public static boolean isAdminRole() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();

		if (session == null) {
			return false;
		}
		String role = (String) session.getAttribute("jvuserrole");
		return "A".equalsIgnoreCase(role);
	}

	public static String loginUserId() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();

		if (session == null) {
			return null;
		}
		return (String) session.getAttribute("jvuserid");
	}

	public static String unAuthLogout(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		request.getSession().invalidate();

		// Add message to flash scope
		redirectAttributes.addFlashAttribute("css", "danger");
		redirectAttributes.addFlashAttribute("msg", "Un authorized Access... You Are logged out!!");

		return "redirect:login?nl=Y";
	}
}