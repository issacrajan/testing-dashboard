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
package org.testingframework.selenium.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.testingframework.selenium.dto.UserInfo;
import org.testingframework.selenium.utils.Utils;

@Component
public class ChangePwdValidator implements Validator {

	@Override
	public boolean supports(Class<?> claz) {
		return UserInfo.class.equals(claz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserInfo user = (UserInfo) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userPwd", "id.required",
				"New Password is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userPwdConfirm", "name.required",
				"Confirm New Password is required");

		if (errors.hasErrors()) {
			return;
		}

		if (user.getUserPwd().length() < 3) {
			errors.rejectValue("userPwd", "user.userpwd.length", "minimum length of password is 3");
		}

		if (Utils.hasContent(user.getUserPwd()) && Utils.hasContent(user.getUserPwdConfirm())) {
			if (!user.getUserPwd().equals(user.getUserPwdConfirm())) {
				errors.rejectValue("userPwd", "error.password.notmatch",
						"Password and Confirm Password do not match");
			}
		}
	}
}
