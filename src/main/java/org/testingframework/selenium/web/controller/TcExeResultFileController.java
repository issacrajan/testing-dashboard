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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.testingframework.selenium.dto.TcRunDtl;
import org.testingframework.selenium.dto.TestCaseRunFileDTO;
import org.testingframework.selenium.service.RunTestService;
import org.testingframework.selenium.utils.TestConfig;
import org.testingframework.selenium.utils.Utils;

@Controller
public class TcExeResultFileController {

	@Autowired
	private RunTestService runTestService = null;

	@RequestMapping(value = "/tc_execution_dtl_file", method = RequestMethod.GET)
	public String openTestCaseExectionFileList(ModelMap model, HttpServletRequest request) {
		String testResultId = request.getParameter("id");
		if (!Utils.isNumber(testResultId)) {
			model.addAttribute("msg", "Invalid Request Inputs");
			return "tc_execution_dtl_file";
		}
		Long id = Long.parseLong(testResultId);
		TcRunDtl dtl = runTestService.getTcRunDtl(id);

		model.addAttribute("r", dtl);
		return "tc_execution_dtl_file";
	}

	@RequestMapping(value = "/tc_execution_file_view/{id}", method = RequestMethod.GET)
	public void openTestCaseExectionScreenFile(@PathVariable("id") String id, HttpServletResponse response)
			throws IOException {
		if (!Utils.isNumber(id)) {
			writeErrMsg("Invalid Test Case Screen shot file Id " + id, response);
			return; 
		}
		TestCaseRunFileDTO dto = runTestService.getOneTestCaseRunFile(Long.valueOf(id));
		if (dto == null) {
			writeErrMsg("Invalid Test Case Screen shot file Id " + id, response);
			return;
		}

		File file = new File(TestConfig.getInstance().getScreenShotFolder() + File.separator
				+ dto.getFileName());

		if (!file.exists()) {
			String errorMessage = "Sorry. The file does not exist";
			System.out.println(errorMessage);
			OutputStream outputStream = response.getOutputStream();
			outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
			outputStream.close();
			return;
		}

		String mimeType = URLConnection.guessContentTypeFromName(file.getName());
		if (mimeType == null) {
			System.out.println("mimetype is not detectable, will take default");
			mimeType = "application/octet-stream";
		}

		System.out.println("mimetype : " + mimeType);

		response.setContentType(mimeType);

		response.setHeader("Content-Disposition",
				String.format("inline; filename=\"" + file.getName() + "\""));

		response.setContentLength((int) file.length());

		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

		FileCopyUtils.copy(inputStream, response.getOutputStream());
	}

	private void writeErrMsg(String errorMessage, HttpServletResponse response) throws IOException {
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
		outputStream.close();
	}
}
