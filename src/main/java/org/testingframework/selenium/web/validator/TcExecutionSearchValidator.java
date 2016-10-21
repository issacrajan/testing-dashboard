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
import org.testingframework.selenium.dto.TcExeResultDTO;
import org.testingframework.selenium.utils.Utils;

@Component 
public class TcExecutionSearchValidator implements Validator {

	
	@Override
	public boolean supports(Class<?> claz) {
		return TcExeResultDTO.class.equals(claz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TcExeResultDTO tc = (TcExeResultDTO) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fromDtStr", "fromDate.required", "From Date is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thruDtStr", "thruDtStr.required", "thru Date is required");

		if (errors.hasErrors()) {
			return;
		}
		if (Utils.isValidDt(tc.getFromDtStr())){
			tc.setFromDt(Utils.convertToDt(tc.getFromDtStr()));
		} else {
			errors.rejectValue("fromDtStr", "invalid.date", "Invalid Date");
		}
		if (Utils.isValidDt(tc.getThruDtStr())){
			tc.setThruDt(Utils.convertToDt(tc.getThruDtStr()));
		} else {
			errors.rejectValue("thruDtStr", "invalid.date", "Invalid Date");
		}
		
	}

}
