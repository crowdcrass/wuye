package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_Jiaofei")
public class Jiaofei implements Serializable{

	private static final long serialVersionUID = -7141419035239709511L;

	private long id;
	
	private int jiaofeilock;
	
	private Date createtime;
	
	private Fangchan fangchan;
	
	private String leixing;//水费，电费，宽带，垃圾清运费，物业管理费
	
	private String shoufeizhuangtail;//未缴费，已缴费

	private Date shoufeishijian;
	
	private String beizhu;
	
	private String feiyong;
	
	private String feiyongzhouqi;
	
	private Zhuhu zhuhu;
	
	
	@ManyToOne
	@JoinColumn(name="zhuhuid")
	public Zhuhu getZhuhu() {
		return zhuhu;
	}

	public void setZhuhu(Zhuhu zhuhu) {
		this.zhuhu = zhuhu;
	}

	public String getFeiyong() {
		return feiyong;
	}

	public void setFeiyong(String feiyong) {
		this.feiyong = feiyong;
	}

	public String getFeiyongzhouqi() {
		return feiyongzhouqi;
	}

	public void setFeiyongzhouqi(String feiyongzhouqi) {
		this.feiyongzhouqi = feiyongzhouqi;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getJiaofeilock() {
		return jiaofeilock;
	}

	public void setJiaofeilock(int jiaofeilock) {
		this.jiaofeilock = jiaofeilock;
	}

	@ManyToOne
	@JoinColumn(name="fangchanid")
	public Fangchan getFangchan() {
		return fangchan;
	}

	public void setFangchan(Fangchan fangchan) {
		this.fangchan = fangchan;
	}

	public String getLeixing() {
		return leixing;
	}

	public void setLeixing(String leixing) {
		this.leixing = leixing;
	}

	public String getShoufeizhuangtail() {
		return shoufeizhuangtail;
	}

	public void setShoufeizhuangtail(String shoufeizhuangtail) {
		this.shoufeizhuangtail = shoufeizhuangtail;
	}

	public Date getShoufeishijian() {
		return shoufeishijian;
	}

	public void setShoufeishijian(Date shoufeishijian) {
		this.shoufeishijian = shoufeishijian;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	
	
	
	
}
