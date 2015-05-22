package dao.impl;

import java.sql.SQLException;
import java.util.List;

import model.Fangchan;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.FangchanDao;









public class FangchanDaoImpl extends HibernateDaoSupport implements  FangchanDao{


	public void deleteBean(Fangchan Fangchan) {
		this.getHibernateTemplate().delete(Fangchan);
		
	}

	public void insertBean(Fangchan Fangchan) {
		this.getHibernateTemplate().save(Fangchan);
		
	}

	@SuppressWarnings("unchecked")
	public Fangchan selectBean(String where) {
		List<Fangchan> list = this.getHibernateTemplate().find("from Fangchan " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Fangchan "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Fangchan> selectBeanList(final int start,final int limit,final String where) {
		return (List<Fangchan>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Fangchan> list = session.createQuery("from Fangchan "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Fangchan Fangchan) {
		this.getHibernateTemplate().update(Fangchan);
		
	}
	
	
}
