package org.altervista.leocus.telegrambotutilities;

import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONWriter;

public class InlineQueryResultGif extends InlineQueryResult {
	private String gif_url;
	private int gif_width;
	private int gif_height;
	private String caption;

	public InlineQueryResultGif(String id, String thumb_url, String title, InputMessageContent input_message_content,
			String gif_url, int gif_width, int gif_height, String caption, boolean disable_web_page_preview) {
		super("gif", id, thumb_url, title, input_message_content);
		this.gif_url = gif_url;
		this.gif_width = gif_width;
		this.gif_height = gif_height;
		this.caption = caption;
	}

	public InlineQueryResultGif(String id, String gif_url, String thumb_url) {
		super("gif", id);
		this.gif_url = gif_url;
		super.setThumb_url(thumb_url);
	}

	public String getGif_url() {
		return gif_url;
	}

	public void setGif_url(String gif_url) {
		this.gif_url = gif_url;
	}

	public int getGif_width() {
		return gif_width;
	}

	public void setGif_width(int gif_width) {
		this.gif_width = gif_width;
	}

	public int getGif_height() {
		return gif_height;
	}

	public void setGif_height(int gif_height) {
		this.gif_height = gif_height;
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
		if (getType() != null) {
			writer.key("type");
			writer.value(getType());
		}
		if (getId() != null) {
			writer.key("id");
			writer.value(getId());
		}
		if (getTitle() != null) {
			writer.key("title");
			writer.value(getTitle());
		}
		if (getInput_message_content() != null) {
			writer.key("input_message_content");
			writer.value(getInput_message_content().toJSONObject());
		}

		writer.key("gif_url");
		writer.value(getGif_url());
		if (getCaption() != null) {
			writer.key("caption");
			writer.value(getCaption());
		}
		writer.key("thumb_url");
		writer.value(getThumb_url());
		if (getGif_width() > 0) {
			writer.key("gif_width");
			writer.value(getGif_width());
		}
		if (getGif_height() > 0) {
			writer.key("gif_height");
			writer.value(getGif_height());
		}
		writer.endObject();

		return new JSONObject(writer.toString());
	}

}
