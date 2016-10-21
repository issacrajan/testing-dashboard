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

package org.testingframework.selenium.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
	private static final SimpleDateFormat sdfYYDDdd = new SimpleDateFormat("yyyyMMdd");
	private static final SimpleDateFormat userInputdt = new SimpleDateFormat("MM/dd/yyyy");
	
	public static void main(String[]args) throws ParseException{
		userInputdt.setLenient(false);
		System.out.println(userInputdt.parse("12/12/2016"));
	}
	public static String fmtDate(Date d){
		if (d == null){
			return "";
		}
		return sdf.format(d);
	}
	public static boolean isEmpty(String s){
		return (s == null || s.isEmpty());
	}
	
	public static boolean hasContent(String s){
		return (s != null && !s.isEmpty());
	}
	
	public static String getYyyyMmDD(){
		return sdfYYDDdd.format(new Date());
	}
	
	public static boolean isValidDt(String d){
		if (isEmpty(d)){
			return false;
		}
		
		boolean valid = true;
		try {
			Date dt = userInputdt.parse(d);
		} catch (ParseException e) {
			valid = false;
		}
		return valid;
	}
	
	public static Date convertToDt(String d){
		Date dt = null;
		if (isEmpty(d)){
			return dt;
		}
		
		try {
			 dt = userInputdt.parse(d);
		} catch (ParseException e) {
			
		}
		return dt;
	}
	
	public static boolean isNumber(String id){
		try {
			Integer.parseInt(id);
		} catch(NumberFormatException pe){
			return false;
		}
		return true;
	}
}
