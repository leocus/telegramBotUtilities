package org.altervista.leocus.telegrambotutilities;

import org.json.JSONException;
import org.json.JSONObject;

public class Contact {
	private String number;
	private String first_name;
	private String last_name;
	private int user_id;

	public Contact(String number, String first_name) {
		super();
		this.number = number;
		this.first_name = first_name;
	}

	public Contact(String number, String first_name, String last_name, int user_id) {
		super();
		this.number = number;
		this.first_name = first_name;
		this.last_name = last_name;
		this.user_id = user_id;
	}

	public Contact(JSONObject object) {
		try {
			number = object.getString("phone_number");
			first_name = object.getString("first_name");
			last_name = object.getString("last_name");
			user_id = object.getInt("user_id");
		} catch (JSONException e) {
		}
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

}
