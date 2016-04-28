package org.altervista.leocus.telegrambotutilities;

import org.json.JSONException;
import org.json.JSONObject;

public class PhotoSize {
	private String id;
	private int width;
	private int height;
	private int size;

	public PhotoSize(String id, int width, int height, int size) {
		super();
		this.id = id;
		this.width = width;
		this.height = height;
		this.size = size;
	}

	public PhotoSize(String id, int width, int height) {
		super();
		this.id = id;
		this.width = width;
		this.height = height;
	}

	public PhotoSize(JSONObject object) {
		try {
			id = object.getString("file_id");
			width = object.getInt("width");
			height = object.getInt("height");
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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
