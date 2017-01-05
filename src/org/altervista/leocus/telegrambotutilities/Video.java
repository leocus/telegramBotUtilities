package org.altervista.leocus.telegrambotutilities;

import org.json.JSONException;
import org.json.JSONObject;

public class Video {
	private String id;
	private int width;
	private int height;
	private int duration;
	private PhotoSize thumb;
	private String mime_type;
	private int size;

	public Video(String id, int width, int height, int duration) {
		super();
		this.id = id;
		this.width = width;
		this.height = height;
		this.duration = duration;
	}

	public Video(String id, int width, int height, int duration, PhotoSize thumb, String mime_type, int size) {
		super();
		this.id = id;
		this.width = width;
		this.height = height;
		this.duration = duration;
		this.thumb = thumb;
		this.mime_type = mime_type;
		this.size = size;
	}

	public Video(JSONObject object) {
		try {
			id = object.getString("file_id");
			width = object.getInt("width");
			height = object.getInt("height");
			duration = object.getInt("duration");
			thumb = new PhotoSize(object.getJSONObject("thumb"));
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public PhotoSize getThumb() {
		return thumb;
	}

	public void setThumb(PhotoSize thumb) {
		this.thumb = thumb;
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
