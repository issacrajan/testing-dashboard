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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.testingframework.selenium.dto.UserInfo;
import org.testingframework.selenium.service.UserService;
import org.testingframework.selenium.utils.Utils;

@Component 
public class UserFormValidator implements Validator {

	@Autowired
	private UserService userService = null;

	@Override
	public boolean supports(Class<?> claz) {
		return UserInfo.class.equals(claz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserInfo user = (UserInfo) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId", "id.required", "User ID is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "name.required",
				"User Name is required");

		if (user.isNew()) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userPwd", "id.required",
					"Password is required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userPwdConfirm", "name.required",
					"Confirm Password is required");
		}
		if (errors.hasErrors()) {
			return;
		}

		if (user.getUserId().length() < 3) {
			errors.rejectValue("userId", "user.userid.length", "minimum length of user id is 3");
		} else {
			boolean validId = isValiduserIdName(user.getUserId());
			if (!validId){
				errors.rejectValue("userId", "user.userid.invalid", "Invalid User Id. 0-9a-z , . _ are valid");
			}
		}
		
		if (user.isNew()) {
			if (Utils.isEmpty(user.getUserPwd())) {
				errors.rejectValue("userPwd", "error.pwd.required", "Password is Required");
			} else if (user.getUserPwd().length() < 3) {
				errors.rejectValue("userPwd", "user.userpwd.length", "minimum length of password is 3");
			}
			if (Utils.isEmpty(user.getUserPwdConfirm())) {
				errors.rejectValue("userPwdConfirm", "error.pwd.required", "Confirm Password is Required");
			}
			if (Utils.hasContent(user.getUserPwd()) && Utils.hasContent(user.getUserPwdConfirm())) {
				if (!user.getUserPwd().equals(user.getUserPwdConfirm())) {
					errors.rejectValue("userPwd", "error.password.notmatch",
							"Password and Confirm Password do not match");
				}
			}
		}

		if (errors.hasErrors()) {
			return;
		}
		if (Utils.hasContent(user.getUserEmail())){
			if (!isValidEmailAddress(user.getUserEmail())){
				errors.rejectValue("userEmail", "error.invalid.email", "Invalid Email Address");
			}
		}
		if (user.isNew()) {
			// check if already exists
			UserInfo oldUser = userService.getUser(user.getUserId());
			if (oldUser != null) {
				errors.rejectValue("userId", "id.already.present",
						"User ID already exists. please enter different ID");
			}
		}
	}

	public boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}
	public boolean isValiduserIdName(String id){
		String ePattern = "^[a-zA-Z0-9_.]*$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(id);
		return m.matches();
		
	}
	public void changePwd(Object target, Errors errors) {
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
