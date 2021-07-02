package com.example.demo.entity;

/**
 * @author takaaki
 * 会計情報クラス
 */
public class Account {

	int id;
	private String date;
	private int type;
	private String item;
	private int price;

	public Account() {

	}

	public Account(String date, int type, String item, int price) {
		this.date = date;
		this.type = type;
		this.item = item;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
