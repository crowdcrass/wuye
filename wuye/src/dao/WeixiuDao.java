package dao;

import java.util.List;

import model.Weixiu;


public interface WeixiuDao  {
	
	
	
	public void insertBean(Weixiu Weixiu);
	
	public void deleteBean(Weixiu Weixiu);
	
	public void updateBean(Weixiu Weixiu);

	public Weixiu selectBean(String where);
	
	public List<Weixiu> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
