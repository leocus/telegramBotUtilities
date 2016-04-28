package org.altervista.leocus.telegrambotutilities;

import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONWriter;

public class InputContactMessageContent implements InputMessageContent {
	private String phone_number;
	private String first_name;
	private String last_name;

	public InputContactMessageContent(String phone_number, String first_name, String last_name) {
		super();
		this.phone_number = phone_number;
		this.first_name = first_name;
		this.last_name = last_name;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
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

	@Override
	public JSONObject toJSONObject() {
		JSONWriter w = new JSONStringer();

		w.object();
		w.key("phone_number");
		w.value(phone_number);
		w.key("first_name");
		w.value(first_name);
		w.key("last_name");
		w.value(last_name);
		w.endObject();

		return new JSONObject(w.toString());
	}

}
