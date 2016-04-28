package org.altervista.leocus.telegrambotutilities;

import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONWriter;

public class InlineQueryResultMpeg4Gif extends InlineQueryResult {
	private String mpeg4_url;
	private int mpeg4_width;
	private int mpeg4_height;
	private String caption;

	public InlineQueryResultMpeg4Gif(String id, String thumb_url, String title,
			InputMessageContent input_message_content, String mpeg4_url, int mpeg4_width, int mpeg4_height,
			String caption) {
		super("mpeg4Gif", id, thumb_url, title, input_message_content);
		this.mpeg4_url = mpeg4_url;
		this.mpeg4_width = mpeg4_width;
		this.mpeg4_height = mpeg4_height;
		this.caption = caption;
	}

	public InlineQueryResultMpeg4Gif(String id, String mpeg4_url, String thumb_url) {
		super("mpeg4gif", id);
		this.mpeg4_url = mpeg4_url;
		super.setThumb_url(thumb_url);
	}

	public String getMpeg4_url() {
		return mpeg4_url;
	}

	public void setMpeg4_url(String mpeg4_url) {
		this.mpeg4_url = mpeg4_url;
	}

	public int getMpeg4_width() {
		return mpeg4_width;
	}

	public void setMpeg4_width(int mpeg4_width) {
		this.mpeg4_width = mpeg4_width;
	}

	public int getMpeg4_height() {
		return mpeg4_height;
	}

	public void setMpeg4_height(int mpeg4_height) {
		this.mpeg4_height = mpeg4_height;
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
		writer.key("mpeg4_url");
		writer.value(getMpeg4_url());
		if (caption != null) {
			writer.key("caption");
			writer.value(getCaption());
		}
		writer.key("thumb_url");
		writer.value(getThumb_url());
		writer.key("mpeg4_width");
		writer.value(getMpeg4_width());
		writer.key("mpeg4_height");
		writer.value(getMpeg4_height());
		writer.endObject();
		return new JSONObject(writer.toString());
	}

}
