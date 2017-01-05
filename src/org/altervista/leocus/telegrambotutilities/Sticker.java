package org.altervista.leocus.telegrambotutilities;

import org.json.JSONException;
import org.json.JSONObject;

public class Sticker {
	private String id;
	private int width;
	private int height;
	private PhotoSize thumb;
	private int size;

	public Sticker(String id, int width, int height, PhotoSize thumb, int size) {
		super();
		this.id = id;
		this.width = width;
		this.height = height;
		this.thumb = thumb;
		this.size = size;
	}

	public Sticker(String id, int width, int height) {
		super();
		this.id = id;
		this.width = width;
		this.height = height;
	}

	public Sticker(JSONObject object) {
		try {
			id = object.getString("file_id");
			width = object.getInt("width");
			height = object.getInt("height");
			thumb = new PhotoSize(object.getJSONObject("thumb"));
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

	public PhotoSize getThumb() {
		return thumb;
	}

	public void setThumb(PhotoSize thumb) {
		this.thumb = thumb;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
