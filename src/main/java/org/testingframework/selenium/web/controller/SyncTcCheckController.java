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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.testingframework.selenium.dto.UserInfo;
import org.testingframework.selenium.service.UserService;

@Controller
public class SyncTcCheckController {

	@Autowired
	private UserService userService = null;
	static int cnt = 0;
	@RequestMapping(value = "/synctccheck", method = RequestMethod.GET)
	public String sayHello(ModelMap model) {
		// check if the sync is completed,
		// if so, redirect to list of use case newly added page
		// if not, display the same page
		List<UserInfo> list = userService.getAllUsers();
		model.addAttribute("list", list);
		cnt++;
		if (cnt > 14){
		return "newtcs";
		} else {
			return "redirect:synctccheck";
		}
	}

}