package com.example.coolweather.app.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coolweather.app.model.City;
import com.example.coolweather.app.model.County;
import com.example.coolweather.app.model.Province;

public class CoolWeatherDB {
	//数据库名
	public static final String DB_NAME = "cool_weather";
	//数据库的版本
	public static final int VERSION = 1;
	
	private static CoolWeatherDB coolWeatherDB;
	private SQLiteDatabase db;
	
	private CoolWeatherDB(Context context){
		CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context, DB_NAME, null, VERSION);
		db = dbHelper.getWritableDatabase();
	}
	
	//获取数据库实例。
	public synchronized static CoolWeatherDB getInstance(Context context){
		if(coolWeatherDB != null){
			return coolWeatherDB;
		}
		return coolWeatherDB;
	}
	//将province实例保存到数据库中。
	public void saveProvince(Province province){
		if(province != null){
			ContentValues values = new ContentValues();
			values.put("province_name", province.getProvinceName());
			values.put("province_code", province.getProvinceCode());
			db.insert("Province", null, values);
		}
	}
	
	/**
	 * 从数据库读取全国所有省份信息。
	 */
	public List<Province> loadProvinces(){
		List<Province> provinceList = new ArrayList<Province>();
		Cursor cursor = db.query("Province", null, null, null, null, null, null);
		while(cursor.moveToNext()){
			String province_name = cursor.getString(cursor.getColumnIndex("province_name"));
			String province_code = cursor.getString(cursor.getColumnIndex("province_code"));
			Province province = new Province(province_name, province_code);
			provinceList.add(province);
		}
		return provinceList;
	}
	
	
	/**
	 * 将CITY实例存储到数据库。
	 */
	public void saveCity(City city){
		if(city != null){
			ContentValues values = new ContentValues();
			values.put("city_name", city.getCityName());
			values.put("city_code", city.getCityCode());
			values.put("province_id", city.getProvinceId());
			db.insert("City", null, values);
		}
	}
	/**
	 * 从数据库中读取所有城市信息。
	 */
	public List<City> loadCities(int provinceId){
		List<City> cityList = new ArrayList<City>();
		Cursor cursor = db.query("City", null, "province_id = ?", new String[]{" " + provinceId}, null, null, null);
		while(cursor.moveToNext()){
			String city_name = cursor.getString(cursor.getColumnIndex("city_name"));
			String city_code = cursor.getString(cursor.getColumnIndex("city_code"));
			City city = new City(city_name, city_code, provinceId);
			cityList.add(city);
		}
		return cityList;
	}
	
	
	/**
	 * 将county的实例存储到数据库。
	 */
	public void saveCounty(County county){
		if(county != null){
			ContentValues values = new ContentValues();
			values.put("county_Name", county.getCountyName());
			values.put("county_code", county.getCountyCode());
			values.put("city_id", county.getCityId());
			db.insert("County", null, values);
		}
	}
	/**
	 * 从数据库读取某个城市下所有的县的信息。
	 * @param cityId
	 * @return
	 */
	public List<County> loadCounties(int cityId){
		List<County> countyList = new ArrayList<County>();
		Cursor cursor = db.query("County", null, "cityId = ?", new String[]{"" + cityId}, null, null, null);
		if(cursor != null){
			if(cursor.moveToFirst()){
				do{
					String countyName = cursor.getString(cursor.getColumnIndex("county_name"));
					String countyCode = cursor.getString(cursor.getColumnIndex("county_code"));
					County county = new County(countyName, countyCode, cityId);
					countyList.add(county);
				}while(cursor.moveToNext());
			}
		}
		return countyList;
	}
}
