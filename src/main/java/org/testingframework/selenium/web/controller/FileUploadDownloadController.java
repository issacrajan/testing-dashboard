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

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.testingframework.selenium.dto.TestCaseDTO;
import org.testingframework.selenium.service.TestCaseService;
import org.testingframework.selenium.utils.TestConfig;
import org.testingframework.selenium.utils.Utils;
import org.testingframework.selenium.web.validator.FileValidator;

@Controller
public class FileUploadDownloadController {

	@Autowired
	TestCaseService testCaseService = null;

	@Autowired
	FileValidator fileValidator;

	@InitBinder("fileBucket")
	protected void initBinderFileBucket(WebDataBinder binder) {
		binder.setValidator(fileValidator);
	}

	@RequestMapping(value = "uploadfilesuccess", method = RequestMethod.GET)
	public String getHomePage(ModelMap model) {
		return "uploadfilesuccess";
	}

	@RequestMapping(value = "/uploadfile/{id}", method = RequestMethod.GET)
	public String getSingleUploadPage(@PathVariable("id") String id, ModelMap model) {
		FileBucket fileModel = new FileBucket();
		fileModel.setId(id);
		model.addAttribute("fileBucket", fileModel);

		return "uploadfile";
	}

	@RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
	public String singleFileUpload(FileBucket fileBucket, BindingResult result, ModelMap model)
			throws IOException {

		if (result.hasErrors()) {
			System.out.println("validation errors");
			return "singleFileUploader";
		} else {
			System.out.println("Fetching file");
			MultipartFile multipartFile = fileBucket.getFile();

			String saveFileName = fileBucket.getId() + "_" + fileBucket.getFile().getOriginalFilename();
			testCaseService.updateTestCaseSupportFileName(fileBucket.getId(), saveFileName);

			FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File(TestConfig.getInstance()
					.getUsecaseSupportFileFolder() + File.separator + saveFileName));

			String fileName = multipartFile.getOriginalFilename();
			model.addAttribute("fileName", fileName);
			return "uploadfilesuccess";
		}
	}

	@RequestMapping(value = "/files/{id}", method = RequestMethod.GET)
	public void getFile(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
		// try {
		// // get your file as InputStream
		// InputStream is = new FileInputStream(new
		// File(TestConfig.getInstance()
		// .getUsecaseSupportFileFolder() + File.separator + fileName));
		// // copy it to response's OutputStream
		// org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
		// response.flushBuffer();
		// } catch (IOException ex) {
		// ex.printStackTrace();
		// throw new RuntimeException("IOError writing file to output stream");
		// }
		if (!Utils.isNumber(id)) {
			writeErrMsg("Invalid Test Case Id " + id, response);
			return;
		}
		TestCaseDTO dto = testCaseService.getOneTestCase(Long.valueOf(id));
		if (dto == null) {
			writeErrMsg("Invalid Test Case Id " + id, response);
			return;
		}

		File file = new File(TestConfig.getInstance().getUsecaseSupportFileFolder() + File.separator
				+ dto.getSupportFileName());

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

		/*
		 * "Content-Disposition : inline" will show viewable types [like
		 * images/text/pdf/anything viewable by browser] right on browser while
		 * others(zip e.g) will be directly downloaded [may provide save as
		 * popup, based on your browser setting.]
		 */
		response.setHeader("Content-Disposition",
				String.format("inline; filename=\"" + file.getName() + "\""));

		/*
		 * "Content-Disposition : attachment" will be directly download, may
		 * provide save as popup, based on your browser setting
		 */
		// response.setHeader("Content-Disposition",
		// String.format("attachment; filename=\"%s\"", file.getName()));

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
