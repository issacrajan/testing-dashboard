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

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public final class TestConfig {
	private static volatile TestConfig instance = null;

	// private constructor
	private TestConfig() {
	}

	private Properties prop = null;
	private String configFile = null;

	public static TestConfig getInstance() {
		if (instance == null) {
			synchronized (TestConfig.class) {
				if (instance == null) {
					instance = new TestConfig();

					instance.prop = new Properties();

					instance.configFile = System.getenv(Constants.TEST_CONFIG_FILE);
					if (Utils.isEmpty(instance.configFile)) {
						throw new RuntimeException("System property TEST_CONFIG_FILE is not defined");
					}

					File f = new File(instance.configFile);
					if (!f.exists()) {
						throw new RuntimeException("file having connection details not found "
								+ instance.configFile);
					}

					FileReader reader;
					try {
						reader = new FileReader(f);
						instance.prop.load(reader);

					} catch (IOException e) {
						throw new RuntimeException(e);
					}

				}
			}
		}
		return instance;
	}

	public String getPackagePrefix() {
		String testCasePackagePrefix = prop.getProperty(Constants.TEST_CASE_PACKAGE_PREFIX_PROP);
		if (Utils.isEmpty(testCasePackagePrefix)) {
			throw new RuntimeException("test case package prefix property "
					+ Constants.TEST_CASE_PACKAGE_PREFIX_PROP + " is not defined in the file " + configFile);
		}
		testCasePackagePrefix = testCasePackagePrefix.trim();
		return testCasePackagePrefix;
	}

	public String getScreenShotFolder() {
		String screenFolder = prop.getProperty(Constants.SCREEN_PATH);
		if (Utils.isEmpty(screenFolder)) {
			screenFolder = "screens";
		}
		screenFolder = screenFolder.trim();
		return screenFolder;
	}

	public String getUsecaseSupportFileFolder() {
		String screenFolder = prop.getProperty(Constants.TEST_CASE_FILE_PATH);
		if (Utils.isEmpty(screenFolder)) {
			screenFolder = "usecase_files";
		}
		screenFolder = screenFolder.trim();
		return screenFolder;
	}

}
