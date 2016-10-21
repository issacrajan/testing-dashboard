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
import org.testingframework.selenium.dto.ModuleDTO;

public class ModuleDao extends HibernateDaoSupport {

	public void insertModule(ModuleDTO dto) {
		getHibernateTemplate().save(dto);
	}

	public void updateModule(ModuleDTO dto) {
		getHibernateTemplate().update(dto);
	}
	public ModuleDTO selectByModuleName(String moduleName){
		DetachedCriteria dc = DetachedCriteria.forClass(ModuleDTO.class);
		dc = dc.add(Property.forName("moduleName").eq(moduleName));
		List list = getHibernateTemplate().findByCriteria(dc);
		if (list != null && !list.isEmpty()){
			return (ModuleDTO) list.get(0);
		}
		return null;
	}
	public List<ModuleDTO> selectAll() {
		DetachedCriteria dc = DetachedCriteria.forClass(ModuleDTO.class);
		dc = dc.addOrder(Order.asc("moduleName"));
		List list = getHibernateTemplate().findByCriteria(dc);
		return list;
	}

}
