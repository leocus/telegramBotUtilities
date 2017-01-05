package org.altervista.leocus.telegrambotutilities;

import org.json.JSONException;
import org.json.JSONObject;

public class InlineQuery {
	private String id;
	private User from;
	private String query;
	private String offset;

	public InlineQuery(String id, User from, String query, String offset) {
		super();
		this.id = id;
		this.from = from;
		this.query = query;
		this.offset = offset;
	}

	public InlineQuery(JSONObject object) {
		try {
			id = object.getString("id");
			from = new User(object.getJSONObject("from"));
			query = object.getString("query");
			offset = object.getString("offset");
		} catch (JSONException e) {
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

}
