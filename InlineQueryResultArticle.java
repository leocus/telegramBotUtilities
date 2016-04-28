package org.altervista.leocus.telegrambotutilities;

import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONWriter;

public class InlineQueryResultArticle extends InlineQueryResult {
	private String url; // optional
	private boolean hide_url; // optional
	private String description; // optional
	private int thumb_width; // optional
	private int thumb_height; // optional

	public InlineQueryResultArticle(String id, String thumb_url, String title,
			InputMessageContent input_message_content, String url, boolean hide_url, String description,
			int thumb_width, int thumb_height) {
		super("article", id, thumb_url, title, input_message_content);
		this.url = url;
		this.hide_url = hide_url;
		this.description = description;
		this.thumb_width = thumb_width;
		this.thumb_height = thumb_height;
	}

	public InlineQueryResultArticle(String id, String title, InputMessageContent input_message_content) {
		super("article", id);
		super.setTitle(title);
		super.setInput_message_content(input_message_content);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isHide_url() {
		return hide_url;
	}

	public void setHide_url(boolean hide_url) {
		this.hide_url = hide_url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getThumb_width() {
		return thumb_width;
	}

	public void setThumb_width(int thumb_width) {
		this.thumb_width = thumb_width;
	}

	public int getThumb_height() {
		return thumb_height;
	}

	public void setThumb_height(int thumb_height) {
		this.thumb_height = thumb_height;
	}

	@Override
	public JSONObject toJSONObject() {
		JSONWriter writer = new JSONStringer();

		writer.object();
		writer.key("type");
		writer.value(getType());
		writer.key("id");
		writer.value(getId());
		writer.key("title");
		writer.value(getTitle());
		if (url != null) {
			writer.key("url");
			writer.value(getUrl());
		}
		writer.key("hide_url");
		writer.value(isHide_url());

		if (getInput_message_content() != null) {
			writer.key("input_message_content");
			writer.value(getInput_message_content().toJSONObject());
		}

		if (description != null) {
			writer.key("description");
			writer.value(getDescription());
		}
		if (getThumb_url() != null) {
			writer.key("thumb_url");
			writer.value(getThumb_url());
		}
		if (getThumb_width() > 0) {
			writer.key("thumb_width");
			writer.value(getThumb_width());
		}
		if (getThumb_height() > 0) {
			writer.key("thumb_height");
			writer.value(getThumb_height());
		}
		writer.endObject();
		return new JSONObject(writer.toString());
	}

}
