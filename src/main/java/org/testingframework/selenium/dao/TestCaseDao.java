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
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.testingframework.selenium.dto.TcExeResultDTO;
import org.testingframework.selenium.dto.TcRunDtl;
import org.testingframework.selenium.dto.TcRunHdr;
import org.testingframework.selenium.dto.TestCaseDTO;
import org.testingframework.selenium.dto.UseCaseDTO;
import org.testingframework.selenium.dto.UserInfo;

public class TestCaseDao extends HibernateDaoSupport {

	public List<TestCaseDTO> allTC() {
		DetachedCriteria dc = DetachedCriteria.forClass(TestCaseDTO.class);
		dc = dc.addOrder(Order.asc("tcName"));
		List list = getHibernateTemplate().findByCriteria(dc);
		return list;
	}

	public List<TestCaseDTO> selectForModuleName(String moduleName) {
		List list = getHibernateTemplate().findByNamedQuery("select-tc-for-module", moduleName);

		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TestCaseDTO> selectForUseCase(Long ucid) {
		DetachedCriteria dc = DetachedCriteria.forClass(TestCaseDTO.class);
		dc = dc.add(Restrictions.eq("ucid", ucid)).addOrder(Order.asc("executionOrder"));
		List list = getHibernateTemplate().findByCriteria(dc);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TestCaseDTO> getDuplicateTCByClassMethod() {
		StringBuilder sb = new StringBuilder("SELECT tcJvClassName, tcJvMethod ");
		sb.append("FROM TestCaseDTO ");
		sb.append("WHERE tcStatus='A' ");
		sb.append("GROUP by tcjvclassname, tcjvmethod ");
		sb.append("HAVING count(*)>1 ");
		List dupList = getHibernateTemplate().find(sb.toString(), null);

		List<TestCaseDTO> finalList = new ArrayList<TestCaseDTO>();
		TestCaseDTO dto = null;
		for (int i = 0; i < dupList.size(); i++) {
			Object[] o = (Object[]) dupList.get(i);
			dto = new TestCaseDTO();
			dto.setTcJvClassName((String) o[0]);
			dto.setTcJvMethod((String) o[1]);
			finalList.add(dto);
		}

		return finalList;
	}

	@SuppressWarnings("unchecked")
	public TestCaseDTO selectTC(TestCaseDTO dto) {
		// ucid=#{ucid} AND tcJvClassName=#{tcJvClassName} AND
		// tcJvMethod=#{tcJvMethod}
		DetachedCriteria dc = DetachedCriteria.forClass(TestCaseDTO.class);
		dc = dc.add(Restrictions.eq("ucid", dto.getUcid()))
				.add(Restrictions.eq("tcJvClassName", dto.getTcJvClassName()))
				.add(Restrictions.eq("tcJvMethod", dto.getTcJvMethod()));
		List list = getHibernateTemplate().findByCriteria(dc);
		if (list == null || list.isEmpty()) {
			return null;
		}
		return (TestCaseDTO) list.get(0);
	}

	public void insert(TestCaseDTO dto) {
		getHibernateTemplate().save(dto);
	}

	public void update(TestCaseDTO dto) {
		getHibernateTemplate().update(dto);
	}
	public void updateTestCaseExecutionOrder(Long tcId, Integer exeOrder){
		TestCaseDTO tc = getHibernateTemplate().load(TestCaseDTO.class, tcId);
		tc.setExecutionOrder(exeOrder);
		getHibernateTemplate().update(tc);
	}
	public TestCaseDTO getOneTestCase(Long id) {
		return getHibernateTemplate().load(TestCaseDTO.class, id);
	}

	public List<TestCaseDTO> getTestCaseForUseCase(Long ucid) {
		return selectForUseCase(ucid);
	}

	public List<TestCaseDTO> getTestCaseForDashboard(Date dt) {
		DetachedCriteria dc = DetachedCriteria.forClass(TestCaseDTO.class);
		dc = dc.add(Restrictions.ge("tcCreationDate", dt));
		List list = getHibernateTemplate().findByCriteria(dc);
		return list;
	}

	public List<TestCaseDTO> getTestCaseForDashboardForUser(Date dt, String userId) {
		DetachedCriteria dc = DetachedCriteria.forClass(TestCaseDTO.class);
		dc = dc.add(Restrictions.ge("tcCreationDate", dt));
		dc = dc.add(Restrictions.eq("tcCreatedBy", userId));
		List list = getHibernateTemplate().findByCriteria(dc);
		return list;
	}

	public void updateTestCaseDesc(TestCaseDTO t) {
		getHibernateTemplate().update(t);
	}

	public void updateTestCaseSupportFileName(String id, String fileName) {
		TestCaseDTO dto = getHibernateTemplate().load(TestCaseDTO.class, Long.valueOf(id));
		dto.setSupportFileName(fileName);
		getHibernateTemplate().update(dto);
	}
	
	public Long getTestCaseCountForUseCase(Long ucId){
		DetachedCriteria dc = DetachedCriteria.forClass(TestCaseDTO.class);
		dc.add(Restrictions.eq("ucid", ucId));
		dc.setProjection(Projections.rowCount());
		List list = getHibernateTemplate().findByCriteria(dc);
		if (list != null && !list.isEmpty()) {
			return (Long) list.get(0);
		}
		return 0L;
	}
	public Long countTc(Date dt) {
		List list = getHibernateTemplate().find(
				"select count(*) from TestCaseDTO where tccreationdate>=? and tcstatus='A'", dt);

		Long cnt = null;
		for (int i = 0; i < list.size(); i++) {
			cnt = (Long) list.get(i);
		}
		return cnt;
	}

	public Long countUserTc(TestCaseDTO dto) {
		StringBuilder sb = new StringBuilder();
		sb.append("select count(*) from TestCaseDTO ");
		sb.append("where tccreationdate>=? ");
		sb.append("and tcstatus='A' ");
		sb.append("and tcCreatedBy=?");
		Object[] key = { dto.getTcCreationDate(), dto.getTcCreatedBy() };
		List list = getHibernateTemplate().find(sb.toString(), key);
		Long cnt = null;
		for (int i = 0; i < list.size(); i++) {
			cnt = (Long) list.get(i);
		}
		return cnt;
	}

	public List<UseCaseDTO> moduleWiseCnt() {
		StringBuilder sb = new StringBuilder();
		sb.append("select b.moduleName,b.useCaseName, count(*) ");
		sb.append("from TestCaseDTO a, UseCaseDTO b ");
		sb.append("where a.ucid = b.id and ");
		sb.append("a.tcStatus='A' ");
		sb.append("group by b.moduleName, b.useCaseName ");

		List list = getHibernateTemplate().find(sb.toString(), null);

		List<UseCaseDTO> finalList = new ArrayList<UseCaseDTO>();
		UseCaseDTO dto = null;
		for (int i = 0; i < list.size(); i++) {
			Object[] o = (Object[]) list.get(i);
			dto = new UseCaseDTO();
			dto.setModuleName((String) o[0]);
			dto.setUseCaseName((String) o[1]);
			dto.setTcCnt((Long) o[2]);
			finalList.add(dto);
		}
		return finalList;
	}

	public List<TcRunHdr> selectLatestExecutionDtl() {
		DetachedCriteria dc = DetachedCriteria.forClass(TcRunHdr.class);
		dc = dc.addOrder(Order.desc("id"));
		List list = getHibernateTemplate().findByCriteria(dc, 0, 10);
		return list;
	}

	public List<TcRunHdr> selectLatestExecutionDtl(UserInfo u) {
		DetachedCriteria dc = DetachedCriteria.forClass(TcRunHdr.class);
		dc = dc.add(Restrictions.eq("runUser", u.getUserId()));
		dc = dc.addOrder(Order.desc("id"));
		List list = getHibernateTemplate().findByCriteria(dc, 0, 10);
		return list;
	}

	// @Select("Select * FROM ut_tcrunhdr where date(rundate) BETWEEN #{fromDt} AND #{thruDt}")
	public List<TcRunHdr> getTCExectionHdrBetween(TcExeResultDTO dto) {
		DetachedCriteria dc = DetachedCriteria.forClass(TcRunHdr.class);
		dc = dc.add(Restrictions.between("runDate", dto.getFromDt(), dto.getThruDt()));
		dc = dc.addOrder(Order.asc("id"));
		List list = getHibernateTemplate().findByCriteria(dc);
		return list;
	}

	// @Select("Select * from ut_tcrundtl where runrunid=#{tcRunHdrId}")
	List<TcRunDtl> getTcExecutionDtl(TcExeResultDTO dto) {
		DetachedCriteria dc = DetachedCriteria.forClass(TcRunHdr.class);
		dc = dc.add(Restrictions.eq("runHdrId", dto.getTcRunHdrId()));
		dc = dc.addOrder(Order.asc("id"));
		List list = getHibernateTemplate().findByCriteria(dc);
		return list;
	}

}
