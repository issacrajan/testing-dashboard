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
import org.testingframework.selenium.dto.TestSuiteHdrDTO;
import org.testingframework.selenium.service.TestSuiteService;

@Component
public class TestSuiteValidator implements Validator {

	@Autowired
	private TestSuiteService tsService = null;
	
	@Override
	public boolean supports(Class<?> claz) {
		return TestSuiteHdrDTO.class.equals(claz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TestSuiteHdrDTO dto = (TestSuiteHdrDTO) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tsName", "id.required", "Test Suite Name is required");
		
		if (errors.hasErrors()){
			return;
		}
		
		if (dto.isNew()){
			if (tsService.isTestSuiteExists(dto.getTsName())){
				errors.rejectValue("tsName", "tsName.duplicate", "Test Suite Name already exists. Please enter new name");
			}
		}
		
	}

	
}
