package com.example.coolweather.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CoolWeatherOpenHelper extends SQLiteOpenHelper {
	/**
	 * Province��Ĵ�����䡣
	 */
	private static final String CREATE_PROVINCE = "create table Province("
													+ "id integer primary key autoincrement"
													+ "province_name text"
													+ "province_code text"
													+ ")";
	/**
	 * City��Ĵ�����䡣
	 */
	private static final String CREATE_CITY = "create table City("
												 + "id integer primary key autoincrement"
												 + "city_name text"
												 + "city_code text"
												 + "province_id integer"
												 +	")";
	
	/**
	 * County��Ĵ�����䡣
	 */
	private static final String CREATE_COUNTY = "create table County("
												  + "id integer primayr key autoincrement"
												  + "county_name text"
												  + "county_id text"
												  + "province_id integer"
												  + ")";
	
	
	public CoolWeatherOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_PROVINCE);
		db.execSQL(CREATE_CITY);
		db.execSQL(CREATE_COUNTY);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
