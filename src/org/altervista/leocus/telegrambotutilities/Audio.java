package org.altervista.leocus.telegrambotutilities;

import org.json.JSONObject;

public class Audio {
	private String id;
	private int duration;
	private String performer;
	private String title;
	private String mime_type;
	private int size;

	public Audio(String id, int duration, String performer, String title, String mime_type, int size) {
		super();
		this.id = id;
		this.duration = duration;
		this.performer = performer;
		this.title = title;
		this.mime_type = mime_type;
		this.size = size;
	}

	public Audio(String id) {
		super();
		this.id = id;
	}

	public Audio(JSONObject object) {
		id = object.getString("file_id");
		duration = object.getInt("duration");
		performer = object.getString("performer");
		title = object.getString("title");
		mime_type = object.getString("mime_type");
		size = object.getInt("size");
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

	public String getPerformer() {
		return performer;
	}

	public void setPerformer(String performer) {
		this.performer = performer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
