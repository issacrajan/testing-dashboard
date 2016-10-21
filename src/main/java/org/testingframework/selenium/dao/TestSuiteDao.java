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
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.testingframework.selenium.dto.TestSuiteDetail;
import org.testingframework.selenium.dto.TestSuiteDetailDTO;
import org.testingframework.selenium.dto.TestSuiteDtlDTO;
import org.testingframework.selenium.dto.TestSuiteHdrDTO;
import org.testingframework.selenium.utils.Constants;

public class TestSuiteDao extends HibernateDaoSupport {

	public Long tcCountForTesSuite(Long suiteId) {
		StringBuilder sb = new StringBuilder("select count(*) FROM TestSuiteDtlDTO b ");
		sb.append("WHERE b.suiteId =? ");

		List dupList = getHibernateTemplate().find(sb.toString(), suiteId);
		Long cnt = null;
		if (dupList != null && !dupList.isEmpty()){
			return (Long)dupList.get(0);
		}
		
		return cnt;
	}

	public TestSuiteHdrDTO getTestSuiteHdrByName(String name) {
		DetachedCriteria dc = DetachedCriteria.forClass(TestSuiteHdrDTO.class);
		dc = dc.add(Restrictions.eq("tsName", name));
		List list = getHibernateTemplate().findByCriteria(dc);
		if (list == null || list.isEmpty()){
			return null;
		}
		return (TestSuiteHdrDTO) list.get(0);
	}
	
	public List<TestSuiteDtlDTO> getTestSuiteDtl(Long id) {
		DetachedCriteria dc = DetachedCriteria.forClass(TestSuiteDtlDTO.class);
		dc = dc.add(Restrictions.eq("suiteId", id));
		dc = dc.addOrder(Order.asc("executionOrder"));
		List list = getHibernateTemplate().findByCriteria(dc);
		return list;
	}
	// @Select("SELECT * FROM ut_testsuitehdr order by tsname")
	public List<TestSuiteHdrDTO> selectAllTestSuiteHdr() {
		DetachedCriteria dc = DetachedCriteria.forClass(TestSuiteHdrDTO.class);
		dc = dc.addOrder(Order.asc("tsName"));
		List list = getHibernateTemplate().findByCriteria(dc);
		return list;
	}

	// @Select("Select * from ut_testsuitehdr WHERE id=#{id}")
	public TestSuiteHdrDTO getTestSuite(Long id) {
		return getHibernateTemplate().load(TestSuiteHdrDTO.class, id);
	}
	public TestSuiteHdrDTO getTestSuiteByName(String tsName){
		TestSuiteHdrDTO dto = null;
		DetachedCriteria dc = DetachedCriteria.forClass(TestSuiteHdrDTO.class);
		dc = dc.add(Restrictions.eq("tsName", tsName));
		List list = getHibernateTemplate().findByCriteria(dc);
		if (list != null && !list.isEmpty()){
			dto = (TestSuiteHdrDTO) list.get(0);
		}
		return dto;
	}
	public void insertTestSuite(TestSuiteHdrDTO dto) {
		getHibernateTemplate().save(dto);
	}

	public void updateTestSuite(TestSuiteHdrDTO dto) {
		getHibernateTemplate().update(dto);
	}

	// @Select("SELECT * FROM ut_testsuitedtl WHERE suiteid=#{id}")
	// public List<TestSuiteHdrDTO> getTestCaseIdForTestSuite(Long id){
	// return getHibernateTemplate().load(TestSuiteHdrDTO.class, id);
	// }

	// @Select("select suiteid, tcid, tcname, modulename, usecasename, executionorder "
	// + "from ut_testsuitedtl, ut_testcase, ut_usecase "
	// + "where ut_testsuitedtl.tcid=ut_testcase.id and "
	// +
	// "ut_testcase.ucid = ut_usecase.id and suiteid=#{id} order by ut_testsuitedtl.executionorder")
	public TestSuiteDetailDTO getTestCaseDetilForTestSuite(Long id, int start) {
		StringBuilder sb = new StringBuilder(
				"select a.suiteId, a.tcId, b.tcName, c.moduleName, c.useCaseName, ");
		sb.append("a.executionOrder ");
		sb.append("FROM  TestSuiteDtlDTO a, TestCaseDTO b, UseCaseDTO c  ");
		sb.append("WHERE a.tcId=b.id and  ");
		sb.append(" b.ucid = c.id and ");
		sb.append("a.suiteId=? ");
		sb.append("order by a.executionOrder ");
		List list = getHibernateTemplate().find(sb.toString(), id);
		
		int end = start + (int) Constants.PAGE_SIZE;
		TestSuiteDetailDTO finalDTO = new TestSuiteDetailDTO();
		
		List<TestSuiteDetail> finalList = new ArrayList<TestSuiteDetail>();
		TestSuiteDetail dto = null;
		long recCnt = list.size();
		finalDTO.setRecCount( recCnt);
		
		for (int i = 0; i < list.size(); i++) {
			if (i < start){
				continue;
			}
			if (i>=end){
				break;
			}
			Object[] o = (Object[]) list.get(i);
			dto = new TestSuiteDetail();
			dto.setSuiteid((Long) o[0]);
			dto.setTcid((Long) o[1]);
			dto.setTcname((String) o[2]);
			dto.setModulename((String) o[3]);
			dto.setUsecasename((String) o[4]);
			if (o[5] != null) {
				dto.setExecutionOrder((Integer) o[5]);
			}
			finalList.add(dto);
		}
		finalDTO.setStart(String.valueOf(start));
		finalDTO.setList(finalList);
		return finalDTO;
	}

	// @Select("SELECT * FROM ut_testsuitedtl WHERE suiteid=#{suiteId} AND tcid=#{tcId}")
	public TestSuiteDtlDTO getTestCaseInTestSuite(TestSuiteDtlDTO dto) {
		return (TestSuiteDtlDTO) getHibernateTemplate().get(TestSuiteDtlDTO.class, dto);
	}

	// @Select("SELECT count(*) FROM ut_testsuitedtl WHERE suiteid=#{suiteId} AND tcid=#{tcId}")
	public int checkTestCaseInTestSuite(TestSuiteDtlDTO dto) {
		TestSuiteDtlDTO tmp = (TestSuiteDtlDTO) getHibernateTemplate().get(TestSuiteDtlDTO.class, dto);
		if (tmp == null) {
			return 0;
		} else {
			return 1;
		}
	}

	// @Delete("DELETE FROM ut_testsuitedtl WHERE suiteid=#{suiteId} AND tcid=#{tcId}")
	public void deleteTestCaseInTestSuite(TestSuiteDtlDTO dto) {
		getHibernateTemplate().delete(dto);
	}

	// @Insert("INSERT INTO ut_testsuitedtl(suiteid, tcid, executionorder) VALUES (#{suiteId}, #{tcId}, #{executionOrder})")
	public void insertTestSuiteDtl(TestSuiteDtlDTO dtl) {
		getHibernateTemplate().save(dtl);
	}

	// @Insert("UPDATE ut_testsuitedtl SET executionorder=#{executionOrder} WHERE suiteid=#{suiteId} AND tcid=#{tcId}")
	public void updateExeOrderTestSuiteDtl(TestSuiteDtlDTO dtl) {
		getHibernateTemplate().update(dtl);
	}

}
