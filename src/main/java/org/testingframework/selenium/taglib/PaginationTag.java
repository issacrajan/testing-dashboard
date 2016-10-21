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


package org.testingframework.selenium.taglib;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.testingframework.selenium.utils.Constants;
import org.testingframework.selenium.utils.Utils;

public class PaginationTag extends SimpleTagSupport {
	private String uri;
	private int start;

	private String previous = "&laquo;";
	private String next = "&raquo;";

	private Writer getWriter() {
		JspWriter out = getJspContext().getOut();
		return out;
	}

	@Override
	public void doTag() throws JspException {
		Long count;
		Long steps = Constants.PAGE_SIZE;
		Writer out = getWriter();
		PageContext pageContext = (PageContext) getJspContext();
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		HttpSession session = request.getSession();
		Long totCnt = (Long) session.getAttribute(Constants.TOTAL_COUNT_KEY);

		if (totCnt == null) {
			System.err.println("total count is missing in session var tot_cnt");
			count = Constants.PAGE_SIZE;
		} else {
			count = totCnt;
		}
		if (count <= steps) {
			return;
		}
		long iterations = count / steps;
		if (count % steps != 0) {
			iterations++;
		}

		try {
			out.write("<nav>");
			out.write("<ul class=\"pagination\">");

			if (start < steps)
				out.write(constructLink(1, previous, "disabled", true));
			else
				out.write(constructLink(start - steps, previous, null, false));
			long rowStartval;
			for (long i = 1; i <= iterations; i++) {
				rowStartval = steps * i;
				if (start == (rowStartval - steps)) {
					out.write(constructLink((rowStartval - steps), String.valueOf(i), "active", true));
				} else {
					out.write(constructLink((rowStartval - steps), String.valueOf(i), null, false));
				}
			}

			if (start + steps >= count)
				out.write(constructLink(start + steps, next, "disabled", true));
			else
				out.write(constructLink(start + steps, next, null, false));

			out.write("</ul>");
			out.write("</nav>");
		} catch (java.io.IOException ex) {
			throw new JspException("Error in Paginator tag", ex);
		}
	}

	private String constructLink(long page, String text, String className, boolean disabled) {
		StringBuilder link = new StringBuilder("<li");
		if (className != null) {
			link.append(" class=\"");
			link.append(className);
			link.append("\"");
		}
		if (disabled)
			link.append(">").append("<a href=\"#\">" + text + "</a></li>");
		else
			link.append(">").append("<a href=\"" + getHref(page) + "\">" + text + "</a></li>");

		return link.toString();
	}

	private String getHref(long page) {
		String href = uri;
		if (uri.contains("=")) {
			href = href + "&start=" + page;
		} else {
			href = href + "?start=" + page;
		}
		return href;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getStart() {
		return String.valueOf(start);
	}

	public void setStart(String s) {
		if (Utils.isNumber(s)) {
			this.start = Integer.parseInt(s);
		} else {
			System.err.println("invalid argument for start attribute");
			this.start=0;
		}
	}

}
