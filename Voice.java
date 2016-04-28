package org.altervista.leocus.telegrambotutilities;

import org.json.JSONException;
import org.json.JSONObject;

public class Voice {
	private String id;
	private int duration;
	private String mime_type;
	private int size;

	public Voice(String id, int duration, String mime_type, int size) {
		super();
		this.id = id;
		this.duration = duration;
		this.mime_type = mime_type;
		this.size = size;
	}

	public Voice(String id, int duration) {
		super();
		this.id = id;
		this.duration = duration;
	}

	public Voice(JSONObject object) {
		super();
		try {
			id = object.getString("file_id");
			// System.out.println(id);
			duration = object.getInt("duration");
			mime_type = object.getString("mime_type");
			size = object.getInt("size");
		} catch (JSONException e) {
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getMime_type() {
		return mime_type;
	}

	public void setMime_type(String mime_type) {
		this.mime_type = mime_type;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
