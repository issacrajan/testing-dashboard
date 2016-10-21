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

import org.apache.commons.codec.binary.Base64;

public class CryptoUtils {

	public static String encrypt(String input) {
		byte[] encoded = Base64.encodeBase64(input.getBytes());
		return new String(encoded);
	}

	public static String decrypt(String input) {
		byte[] decoded = Base64.decodeBase64(input);
		return new String(decoded);
	}

	public static void main(String[] args)  {
		String out = CryptoUtils.encrypt("admin");

		System.out.println(out);
		String ret = CryptoUtils.decrypt(out);
		System.out.println(ret);
	}
}
