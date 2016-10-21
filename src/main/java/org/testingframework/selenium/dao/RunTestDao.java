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

package org.testingframework.selenium.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.testingframework.selenium.dto.TcExeResultDTO;
import org.testingframework.selenium.dto.TcRunDtl;
import org.testingframework.selenium.dto.TcRunHdr;
import org.testingframework.selenium.dto.TestCaseRunFileDTO;
import org.testingframework.selenium.dto.TestRunDetail;
import org.testingframework.selenium.utils.Constants;
import org.testingframework.selenium.utils.Utils;

public class RunTestDao extends HibernateDaoSupport {

	public void insertHdr(TcRunHdr dto) {
		getHibernateTemplate().save(dto);
	}

	public void updateHdr(TcRunHdr dto) {
		getHibernateTemplate().update(dto);
	}

	public void insertDtl(TcRunDtl dto) {
		getHibernateTemplate().save(dto);
	}

	public void insertDtlImage(TestCaseRunFileDTO dto) {
		getHibernateTemplate().save(dto);
	}

	public List<TestCaseRunFileDTO> getAllFilesForTestCaseRun(Long id) {
		DetachedCriteria dc = DetachedCriteria.forClass(TestCaseRunFileDTO.class);
		dc = dc.add(Restrictions.eq("rundtlid", id));
		dc = dc.addOrder(Order.asc("id"));
		List list = getHibernateTemplate().findByCriteria(dc);
		return list;
	}

	public void searchTcRunHdr(TcExeResultDTO dto) {
		DetachedCriteria dc = DetachedCriteria.forClass(TcRunHdr.class);
		dc = dc.add(Restrictions.between("runDate", dto.getFromDt(), dto.getThruDt()));
		dc = dc.addOrder(Order.desc("id"));
		List list = getHibernateTemplate().findByCriteria(dc);
		dto.setList(list);
	}

	public void searchTcRunDtl(TcExeResultDTO dto) {
		DetachedCriteria dc = DetachedCriteria.forClass(TcRunHdr.class);
		dc = dc.add(Restrictions.eq("id", dto.getTcRunHdrId()));
		List list = getHibernateTemplate().findByCriteria(dc);
		if (list != null && list.size() > 0) {
			dto.setTcRunHdr((TcRunHdr) list.get(0));
		}

		StringBuilder sb = new StringBuilder(
				"select a.id, a.runHdrId, a.tcId, a.tcCompStatus, a.screenShotFlag, a.testMsg, ");
		sb.append("a.tcErrorDtl, b.tcName  ");
		sb.append("FROM  TcRunDtl a, TestCaseDTO b  ");
		sb.append("WHERE a.tcId=b.id and  ");
		sb.append("a.runHdrId=? ");
		sb.append("order by a.id ");
		list = getHibernateTemplate().find(sb.toString(), dto.getTcRunHdrId());

		List<TestRunDetail> finalList = new ArrayList<TestRunDetail>();
		TestRunDetail dtlDTO = null;
		String testMsg = null;
		String errDel = null;
		String fileAttached = null;
		for (int i = 0; i < list.size(); i++) {
			Object[] o = (Object[]) list.get(i);
			dtlDTO = new TestRunDetail();
			dtlDTO.setTestResultDetId((Long) o[0]);
			dtlDTO.setRunHdrId((Long) o[1]);
			dtlDTO.setTcId((Long) o[2]);
			dtlDTO.setTcCompStatus((String) o[3]);
			fileAttached = (String) o[4];
			if ("Y".equals(fileAttached)) {
				dtlDTO.setScreenShotFlag(true);
			}
			testMsg = (String) o[5];
			if (Utils.hasContent(testMsg)) {
				dtlDTO.addMessage(testMsg);
			}
			errDel = (String) o[6];
			if (Utils.hasContent(errDel)) {
				dtlDTO.addMessage(errDel);
			}
			dtlDTO.setTcName((String) o[7]);
			finalList.add(dtlDTO);
		}

		dto.setListDtl(finalList);
	}

	public void searchTcRunDtlNew(TcExeResultDTO dto) {
		DetachedCriteria dc = DetachedCriteria.forClass(TcRunHdr.class);
		dc = dc.add(Restrictions.eq("id", dto.getTcRunHdrId()));
		List list = getHibernateTemplate().findByCriteria(dc);
		if (list != null && list.size() > 0) {
			dto.setTcRunHdr((TcRunHdr) list.get(0));
		}
		String startStr = dto.getStart();
		int start = 0;
		if (Utils.isNumber(startStr)) {
			start = Integer.parseInt(startStr);
		}
		int end = start + (int) Constants.PAGE_SIZE;
		DetachedCriteria dc2 = DetachedCriteria.forClass(TcRunDtl.class);
		dc2.add(Restrictions.eq("runHdrId", dto.getTcRunHdrId()));
		List list2 = getHibernateTemplate().findByCriteria(dc2, start, end);

		TcRunDtl runDtl = null;
		List<TestRunDetail> finalList = new ArrayList<TestRunDetail>();
		TestRunDetail runDetailDTO = null;
		for (Object o : list2) {
			runDtl = (TcRunDtl) o;
			runDetailDTO = new TestRunDetail();
			runDetailDTO.setTestResultDetId(runDtl.getId());
			runDetailDTO.setTcId(runDtl.getTestCaseDTO().getId());
			runDetailDTO.setRunHdrId(dto.getTcRunHdrId());
			runDetailDTO.setTcName(runDtl.getTestCaseDTO().getTcName());
			runDetailDTO.setTcCompStatus(runDtl.getTcCompStatus());
			if ("Y".equals(runDtl.getScreenShotFlag())) {
				runDetailDTO.setScreenShotFlag(true);
			}

			if (Utils.hasContent(runDtl.getTestMsg())) {
				runDetailDTO.addMessage(runDtl.getTestMsg());
			}
			if (Utils.hasContent(runDtl.getTcErrorDtl())) {
				runDetailDTO.addMessage(runDtl.getTcErrorDtl());
			}
			finalList.add(runDetailDTO);
		}
		dto.setListDtl(finalList);
	}

	public Long tcRunDtlCount(TcExeResultDTO dto) {
		DetachedCriteria dc = DetachedCriteria.forClass(TcRunDtl.class);
		dc.add(Restrictions.eq("runHdrId", dto.getTcRunHdrId()));
		dc.setProjection(Projections.rowCount());
		List list = getHibernateTemplate().findByCriteria(dc);
		if (list != null && !list.isEmpty()) {
			return (Long) list.get(0);
		}
		return 0L;
	}

	public TcRunDtl getTcRunDtl(Long id) {
		DetachedCriteria dc = DetachedCriteria.forClass(TcRunDtl.class);
		dc.add(Restrictions.eq("id", id));
		TcRunDtl dtl = null;
		List list = getHibernateTemplate().findByCriteria(dc);
		if (list != null && !list.isEmpty()) {
			dtl = (TcRunDtl) list.get(0);
			
		}
		return dtl;
	}
	
	public TestCaseRunFileDTO getOneTestCaseRunFile(Long id){
		DetachedCriteria dc = DetachedCriteria.forClass(TestCaseRunFileDTO.class);
		dc.add(Restrictions.eq("id", id));
		TestCaseRunFileDTO dto = null;
		List list = getHibernateTemplate().findByCriteria(dc);
		if (list != null && !list.isEmpty()) {
			dto = (TestCaseRunFileDTO) list.get(0);
		}
		return dto;
	}
}
