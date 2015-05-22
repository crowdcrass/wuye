package dao;

import java.util.List;

import model.Zhuhu;


public interface ZhuhuDao  {
	
	
	
	public void insertBean(Zhuhu Zhuhu);
	
	public void deleteBean(Zhuhu Zhuhu);
	
	public void updateBean(Zhuhu Zhuhu);

	public Zhuhu selectBean(String where);
	
	public List<Zhuhu> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
