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

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.testingframework.selenium.dto.UserInfo;
import org.testingframework.selenium.dto.UserTestSuiteDTO;
import org.testingframework.selenium.dto.UserUseCaseDTO;

public class UserDao extends HibernateDaoSupport {

	public List<UserInfo> getAllUser() {
		DetachedCriteria dc = DetachedCriteria.forClass(UserInfo.class);
		dc = dc.addOrder(Order.asc("userName"));
		List list = getHibernateTemplate().findByCriteria(dc);
		return list;
	}
	
	public void insertUser(UserInfo user) {
		getHibernateTemplate().save(user);
	}

	public void updateUser(UserInfo user) {
		UserInfo u = getHibernateTemplate().load(UserInfo.class, user.getUserId());
		u.setUserName(user.getUserName());
		u.setUserEmail(user.getUserEmail());
		u.setUserStatus(user.getUserStatus());
		u.setUserRole(user.getUserRole());
		getHibernateTemplate().update(u);
	}

	public void updatePwd(UserInfo user) {
		UserInfo u = getHibernateTemplate().load(UserInfo.class, user.getUserId());
		u.setOldPwd(u.getUserPwd());
		u.setUserPwd(user.getUserPwd());
	}

	public void updatePostLogin(UserInfo user) {
		UserInfo u = getHibernateTemplate().load(UserInfo.class, user.getUserId());
		u.setLastLogingTime(user.getLastLogingTime());
		u.setRememberMe(user.getRememberMe());
		getHibernateTemplate().update(u);
	}

	@SuppressWarnings("rawtypes")
	public UserInfo find(String userId) {
		List list = getHibernateTemplate().find("from UserInfo where userId=?", userId);
		if (list == null || list.isEmpty()) {
			return null;
		}
		return (UserInfo) list.get(0);
	}

	public UserInfo findByRememberMe(String rm) {
		List list = getHibernateTemplate().find("from UserInfo where rememberme=?", rm);
		if (list == null || list.isEmpty()) {
			return null;
		}
		return (UserInfo) list.get(0);
	}

	public List<UserInfo> getAllActiveUser() {
		DetachedCriteria dc = DetachedCriteria.forClass(UserInfo.class);
		dc = dc.add(Property.forName("userStatus").eq("A")).addOrder(Order.asc("userName"));
		List list = getHibernateTemplate().findByCriteria(dc);
		return list;
	}

	// @Select("select count(*) from ut_user_usecase where userid=#{userId} and ucid=#{ucid}")
	public Long countUserUserCase(UserUseCaseDTO dto) {
		Object[] key = { dto.getUserId(), dto.getUcid() };
		List list = getHibernateTemplate().find(
				"select count(*) from UserUseCaseDTO where userId=? and ucid=?", key);

		Long cnt = null;
		for (int i = 0; i < list.size(); i++) {
			cnt = (Long) list.get(i);
		}
		return cnt;
	}

	//@Select("select * from ut_user_usecase where userid=#{userId} and ucid=#{ucid}")
	public UserUseCaseDTO getOneUserUserCase(UserUseCaseDTO dto){
		return (UserUseCaseDTO) getHibernateTemplate().get(UserUseCaseDTO.class, dto);
	}

	//@Insert("INSERT INTO ut_user_usecase(userid, ucid, createddt) VALUES (#{userId},#{ucid}, #{createdDt})")
	public void insertUserUseCase(UserUseCaseDTO dto){
		getHibernateTemplate().save(dto);
	}

	//@Delete("delete from ut_user_usecase where userid=#{userId} and ucid=#{ucid}")
	public void deleteOneUserUserCase(UserUseCaseDTO dto){
		getHibernateTemplate().delete(dto);
	}

	//@Select("select * from ut_user_testsuite where userid=#{userId} and suiteid=#{suiteid}")
	public UserTestSuiteDTO getOneUserTestSuite(UserTestSuiteDTO dto){
		return (UserTestSuiteDTO) getHibernateTemplate().get(UserTestSuiteDTO.class, dto);
	}

	//@Select("select count(*) from ut_user_testsuite where userid=#{userId} and suiteid=#{suiteid}")
	public int countUserTestSuite(UserTestSuiteDTO dto){
		UserTestSuiteDTO tmp = (UserTestSuiteDTO) getHibernateTemplate().get(UserTestSuiteDTO.class, dto);
		if (tmp == null){
			return 0;
		} else {
			return 1;
		}
	}

	//@Insert("INSERT INTO ut_user_testsuite(userid, suiteid, createddt) VALUES (#{userId},#{suiteid}, #{createdDt})")
	public void insertUserTestSuite(UserTestSuiteDTO dto){
		getHibernateTemplate().save(dto);
	}

	//@Delete("delete from ut_user_testsuite where userid=#{userId} and suiteid=#{suiteid}")
	public void deleteOneUserTestSuite(UserTestSuiteDTO dto){
		getHibernateTemplate().delete(dto);
	}

}
