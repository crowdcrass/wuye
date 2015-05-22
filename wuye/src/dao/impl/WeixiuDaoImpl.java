package dao.impl;

import java.sql.SQLException;
import java.util.List;

import model.Weixiu;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.WeixiuDao;









public class WeixiuDaoImpl extends HibernateDaoSupport implements  WeixiuDao{


	public void deleteBean(Weixiu Weixiu) {
		this.getHibernateTemplate().delete(Weixiu);
		
	}

	public void insertBean(Weixiu Weixiu) {
		this.getHibernateTemplate().save(Weixiu);
		
	}

	@SuppressWarnings("unchecked")
	public Weixiu selectBean(String where) {
		List<Weixiu> list = this.getHibernateTemplate().find("from Weixiu " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Weixiu "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Weixiu> selectBeanList(final int start,final int limit,final String where) {
		return (List<Weixiu>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Weixiu> list = session.createQuery("from Weixiu "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Weixiu Weixiu) {
		this.getHibernateTemplate().update(Weixiu);
		
	}
	
	
}
