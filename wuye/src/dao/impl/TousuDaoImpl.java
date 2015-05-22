package dao.impl;

import java.sql.SQLException;
import java.util.List;

import model.Tousu;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.TousuDao;









public class TousuDaoImpl extends HibernateDaoSupport implements  TousuDao{


	public void deleteBean(Tousu Tousu) {
		this.getHibernateTemplate().delete(Tousu);
		
	}

	public void insertBean(Tousu Tousu) {
		this.getHibernateTemplate().save(Tousu);
		
	}

	@SuppressWarnings("unchecked")
	public Tousu selectBean(String where) {
		List<Tousu> list = this.getHibernateTemplate().find("from Tousu " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Tousu "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Tousu> selectBeanList(final int start,final int limit,final String where) {
		return (List<Tousu>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Tousu> list = session.createQuery("from Tousu "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Tousu Tousu) {
		this.getHibernateTemplate().update(Tousu);
		
	}
	
	
}
