package org.altervista.leocus.telegrambotutilities;

import org.json.JSONObject;

public class MessageEntity {
	String type;
	String url; // optional
	// FIXME: gli int seguenti sono in utf-16... cambia qualcosa?!?
	int offset;
	int length;

	public MessageEntity(JSONObject obj) {
		type = obj.getString("type");
		url = obj.getString("url");
		offset = obj.getInt("offset");
		length = obj.getInt("length");
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}
