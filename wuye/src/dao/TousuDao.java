package dao;

import java.util.List;

import model.Tousu;


public interface TousuDao  {
	
	
	
	public void insertBean(Tousu Tousu);
	
	public void deleteBean(Tousu Tousu);
	
	public void updateBean(Tousu Tousu);

	public Tousu selectBean(String where);
	
	public List<Tousu> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
