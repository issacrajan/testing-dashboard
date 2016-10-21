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


public class Constants {
	public static final String TEST_CONFIG_FILE = "TEST_CONFIG_FILE";
	public static final String TEST_CASE_PACKAGE_PREFIX_PROP = "test.case.package.prefix";
	public static final String SCREEN_PATH = "test.result.screen.path";
	public static final String TEST_CASE_FILE_PATH = "test.case.file.path";
	public static final String BROWSER_TYPE="browser.type";
	public static final long PAGE_SIZE = 10;
	public static final String TOTAL_COUNT_KEY = "tot_count";
	public static final String MODULE_MFM="MFM";
	public static final String MFM_BLOB="Blob";
	public static final String MFM_CLIENT="Client";
	
//	public static void takeScreenShot(WebDriver driver, String desc){
//		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		// Now you can do whatever you need to do with it, for example copy somewhere
//		try {
//			FileUtils.copyFile(scrFile, new File("screenshot.png"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
