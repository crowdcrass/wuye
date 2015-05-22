package dao.impl;

import java.sql.SQLException;
import java.util.List;

import model.Zhuhu;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.ZhuhuDao;









public class ZhuhuDaoImpl extends HibernateDaoSupport implements  ZhuhuDao{


	public void deleteBean(Zhuhu Zhuhu) {
		this.getHibernateTemplate().delete(Zhuhu);
		
	}

	public void insertBean(Zhuhu Zhuhu) {
		this.getHibernateTemplate().save(Zhuhu);
		
	}

	@SuppressWarnings("unchecked")
	public Zhuhu selectBean(String where) {
		List<Zhuhu> list = this.getHibernateTemplate().find("from Zhuhu " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Zhuhu "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Zhuhu> selectBeanList(final int start,final int limit,final String where) {
		return (List<Zhuhu>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Zhuhu> list = session.createQuery("from Zhuhu "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Zhuhu Zhuhu) {
		this.getHibernateTemplate().update(Zhuhu);
		
	}
	
	
}
