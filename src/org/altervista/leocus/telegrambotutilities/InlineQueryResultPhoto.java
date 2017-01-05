package org.altervista.leocus.telegrambotutilities;

import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONWriter;

public class InlineQueryResultPhoto extends InlineQueryResult {
	private String photo_url;
	private int photo_width;
	private int photo_height;
	private String description;
	private String caption;

	public InlineQueryResultPhoto(String id, String thumb_url, String title, InputMessageContent input_message_content,
			String photo_url, int photo_width, int photo_height, String description, String caption) {
		super("photo", id, thumb_url, title, input_message_content);
		this.photo_url = photo_url;
		this.photo_width = photo_width;
		this.photo_height = photo_height;
		this.description = description;
		this.caption = caption;
	}

	public InlineQueryResultPhoto(String id, String photo_url, String thumb_url) {
		super("photo", id);
		this.photo_url = photo_url;
		super.setThumb_url(thumb_url);
	}

	public String getPhoto_url() {
		return photo_url;
	}

	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}

	public int getPhoto_width() {
		return photo_width;
	}

	public void setPhoto_width(int photo_width) {
		this.photo_width = photo_width;
	}

	public int getPhoto_height() {
		return photo_height;
	}

	public void setPhoto_height(int photo_height) {
		this.photo_height = photo_height;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	@Override
	public JSONObject toJSONObject() {
		JSONWriter writer = new JSONStringer();

		writer.object();
		writer.key("type");
		writer.value(getType());
		writer.key("id");
		writer.value(getId());
		if (getTitle() != null) {
			writer.key("title");
			writer.value(getTitle());
		}
		if (getInput_message_content() != null) {
			writer.key("input_message_content");
			writer.value(getInput_message_content().toJSONObject());
		}
		writer.key("photo_url");
		writer.value(getPhoto_url());
		if (caption != null) {
			writer.key("caption");
			writer.value(getCaption());
		}
		if (getDescription() != null) {
			writer.key("description");
			writer.value(getDescription());
		}
		writer.key("thumb_url");
		writer.value(getThumb_url());
		writer.key("photo_width");
		writer.value(getPhoto_width());
		writer.key("photo_height");
		writer.value(getPhoto_height());
		writer.endObject();
		return new JSONObject(writer.toString());
	}
}
