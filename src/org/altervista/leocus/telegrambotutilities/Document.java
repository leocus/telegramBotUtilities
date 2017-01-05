package org.altervista.leocus.telegrambotutilities;

import org.json.JSONException;
import org.json.JSONObject;

public class Document {
	private String id;
	private PhotoSize thumb;
	private String name;
	private String mime_type;
	private int size;

	public Document(String id, PhotoSize thumb, String name, String mime_type, int size) {
		super();
		this.id = id;
		this.thumb = thumb;
		this.name = name;
		this.mime_type = mime_type;
		this.size = size;
	}

	public Document(String id) {
		super();
		this.id = id;
	}

	public Document(JSONObject object) {
		try {
			id = object.getString("file_id");
			thumb = new PhotoSize(object.getJSONObject("thumb"));
			name = object.getString("name");
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

	public PhotoSize getThumb() {
		return thumb;
	}

	public void setThumb(PhotoSize thumb) {
		this.thumb = thumb;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
