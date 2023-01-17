package DTO;

import java.sql.Date;

public class RESERVATION {
	private String PT_NO;
	private String CUST_NO;
	private String CUST_NAME;
	private String CUST_PHONE;
	private String AL_NAME;
	private String START_COUNTRY;
	private String END_COUNTRY;
	private Date START_DAY;
	private Date END_DAY;
	private int PT_PRICE;
	
	
	
	
	public String getPT_NO() {
		return PT_NO;
	}
	public void setPT_NO(String pT_NO) {
		PT_NO = pT_NO;
	}
	public String getCUST_NO() {
		return CUST_NO;
	}
	public void setCUST_NO(String cUST_NO) {
		CUST_NO = cUST_NO;
	}
	public String getCUST_NAME() {
		return CUST_NAME;
	}
	public void setCUST_NAME(String cUST_NAME) {
		CUST_NAME = cUST_NAME;
	}
	public String getCUST_PHONE() {
		return CUST_PHONE;
	}
	public void setCUST_PHONE(String cUST_PHONE) {
		CUST_PHONE = cUST_PHONE;
	}
	public String getAL_NAME() {
		return AL_NAME;
	}
	public void setAL_NAME(String aL_NAME) {
		AL_NAME = aL_NAME;
	}
	public String getSTART_COUNTRY() {
		return START_COUNTRY;
	}
	public void setSTART_COUNTRY(String sTART_COUNTRY) {
		START_COUNTRY = sTART_COUNTRY;
	}
	public String getEND_COUNTRY() {
		return END_COUNTRY;
	}
	public void setEND_COUNTRY(String eND_COUNTRY) {
		END_COUNTRY = eND_COUNTRY;
	}
	public Date getSTART_DAY() {
		return START_DAY;
	}
	public void setSTART_DAY(Date sTART_DAY) {
		START_DAY = sTART_DAY;
	}
	public Date getEND_DAY() {
		return END_DAY;
	}
	public void setEND_DAY(Date eND_DAY) {
		END_DAY = eND_DAY;
	}
	public int getPT_PRICE() {
		return PT_PRICE;
	}
	public void setPT_PRICE(int pT_PRICE) {
		PT_PRICE = pT_PRICE;
	}
	
	
	
	
}
