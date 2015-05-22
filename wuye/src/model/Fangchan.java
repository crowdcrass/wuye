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
@Table(name="t_Fangchan")
public class Fangchan implements Serializable{

	private static final long serialVersionUID = -7141419035239709511L;

	private long id;
	
	private int fangchanlock;
	
	private String zhuzhi;//住址
	
	private String fangwujiegou;//房屋结构
	
	private String shebei;//设备
	
	private String mianji;//房屋面积
	
	private String beizhu;//备注
	
	private Zhuhu zhuhu;
	
	private String menpaihao;//门牌号
	
	private Date createtime;
	
	
	

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getMenpaihao() {
		return menpaihao;
	}

	public void setMenpaihao(String menpaihao) {
		this.menpaihao = menpaihao;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getFangchanlock() {
		return fangchanlock;
	}

	public void setFangchanlock(int fangchanlock) {
		this.fangchanlock = fangchanlock;
	}

	public String getZhuzhi() {
		return zhuzhi;
	}

	public void setZhuzhi(String zhuzhi) {
		this.zhuzhi = zhuzhi;
	}

	public String getFangwujiegou() {
		return fangwujiegou;
	}

	public void setFangwujiegou(String fangwujiegou) {
		this.fangwujiegou = fangwujiegou;
	}

	public String getShebei() {
		return shebei;
	}

	public void setShebei(String shebei) {
		this.shebei = shebei;
	}

	public String getMianji() {
		return mianji;
	}

	public void setMianji(String mianji) {
		this.mianji = mianji;
	}



	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	@ManyToOne
	@JoinColumn(name="zhuhuid")
	public Zhuhu getZhuhu() {
		return zhuhu;
	}

	public void setZhuhu(Zhuhu zhuhu) {
		this.zhuhu = zhuhu;
	}

	

	
	
	
}
