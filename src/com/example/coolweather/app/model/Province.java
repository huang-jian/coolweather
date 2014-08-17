package com.example.coolweather.app.model;
/**
 * 省份实体类
 * @author HuangJian
 *
 */
public class Province {
	private int id;
	private String provinceName;
	private String provinceCode;
	
	public Province(){
		
	}
	public Province( String name, String code) {
		super();
		this.provinceName = name;
		this.provinceCode = code;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String name) {
		this.provinceName = name;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String code) {
		this.provinceCode = code;
	}
	
	
	
}
