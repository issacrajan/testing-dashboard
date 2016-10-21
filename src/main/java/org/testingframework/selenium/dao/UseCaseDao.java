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
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.testingframework.selenium.dto.UseCaseClsDTO;
import org.testingframework.selenium.dto.UseCaseDTO;

public class UseCaseDao extends HibernateDaoSupport{

	public List<UseCaseDTO> getAllUseCase(){
		DetachedCriteria dc = DetachedCriteria.forClass(UseCaseDTO.class);
		dc = dc.addOrder(Order.asc("moduleName")).addOrder(Order.asc("useCaseName"));
		List list = getHibernateTemplate().findByCriteria(dc);
		return list;
	}
	public List<UseCaseDTO> getUseCaseForModule(String moduleName){
		DetachedCriteria dc = DetachedCriteria.forClass(UseCaseDTO.class);
		dc = dc.add(Restrictions.eq("moduleName", moduleName)) .addOrder(Order.asc("useCaseName"));
		List list = getHibernateTemplate().findByCriteria(dc);
		return list;
	}
	
	public void insertUseCase(UseCaseDTO dto){
		getHibernateTemplate().save(dto);
	}
	public void updateUseCase(UseCaseDTO dto){ 
		getHibernateTemplate().update(dto);
	}
	
	public void updateUseCaseRemarks(UseCaseDTO dto){ 
		UseCaseDTO updDTO = getHibernateTemplate().load(UseCaseDTO.class, dto.getId());
		updDTO.setRemarks(dto.getRemarks());
		updDTO.setModifiedBy(dto.getModifiedBy());
		updDTO.setModifiedDt(dto.getModifiedDt());
		getHibernateTemplate().update(updDTO);
	}
	
	public UseCaseDTO findUseCaseByName(UseCaseDTO u){
		DetachedCriteria dc = DetachedCriteria.forClass(UseCaseDTO.class);
		dc = dc.add(Restrictions.eq("moduleName", u.getModuleName()))
				.add(Restrictions.eq("useCaseName", u.getUseCaseName()));

		List list = getHibernateTemplate().findByCriteria(dc);
		if (list == null || list.isEmpty()){
			return null;
		}
		return (UseCaseDTO) list.get(0);
	}
	public UseCaseDTO findUseCaseById(Long id){
		return (UseCaseDTO) getHibernateTemplate().get(UseCaseDTO.class, id);
	}
	
	//************** USE CASE - CLASSES************
	public void insertUseCaseClass(UseCaseClsDTO dto){
		getHibernateTemplate().save(dto);
	}
	public void updateUseCaseClass(UseCaseClsDTO dto){
		getHibernateTemplate().update(dto);
	}
	public UseCaseClsDTO selectOneClsName(String clsName){
		return (UseCaseClsDTO) getHibernateTemplate().load(UseCaseClsDTO.class, clsName);
	}
}
