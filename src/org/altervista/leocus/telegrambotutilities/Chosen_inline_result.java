package org.altervista.leocus.telegrambotutilities;

import org.json.JSONException;
import org.json.JSONObject;

public class Chosen_inline_result {
	private String result_id;
	private User from;
	private String query;

	public Chosen_inline_result(String result_id, User from, String query) {
		super();
		this.result_id = result_id;
		this.from = from;
		this.query = query;
	}

	public Chosen_inline_result(JSONObject object) {
		try {
			result_id = object.getString("result_id");
			from = new User(object.getJSONObject("from"));
			query = object.getString("query");
		} catch (JSONException e) {
		}
	}

	public String getResult_id() {
		return result_id;
	}

	public void setResult_id(String result_id) {
		this.result_id = result_id;
	}

	public User getFrom() {
		return from;
	}

	public void setFrom(User from) {
		this.from = from;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

}
