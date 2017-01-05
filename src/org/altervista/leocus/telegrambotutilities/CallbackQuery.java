package org.altervista.leocus.telegrambotutilities;

import org.json.JSONException;
import org.json.JSONObject;

public class CallbackQuery {
	String id;
	User from;
	Message message; // optional
	String inline_message_id; // optional
	String data; // optional

	public CallbackQuery(String id, User from, Message message, String inline_message_id, String data) {
		super();
		this.id = id;
		this.from = from;
		this.message = message;
		this.inline_message_id = inline_message_id;
		this.data = data;
	}

	public CallbackQuery(JSONObject obj) {
		super();
		try {
			id = obj.getString("id");
		} catch (JSONException e) {
		}
		try {
			from = new User(obj.getJSONObject("from"));

		} catch (JSONException e) {
		}
		try {
			message = new Message(obj.getJSONObject("message"));

		} catch (JSONException e) {
		}
		try {
			inline_message_id = obj.getString("inline_message_id");

		} catch (JSONException e) {
		}
		try {
			data = obj.getString("data");

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

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public String getInline_message_id() {
		return inline_message_id;
	}

	public void setInline_message_id(String inline_message_id) {
		this.inline_message_id = inline_message_id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}