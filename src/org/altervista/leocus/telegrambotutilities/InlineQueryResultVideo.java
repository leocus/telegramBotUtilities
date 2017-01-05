package org.altervista.leocus.telegrambotutilities;

import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONWriter;

public class InlineQueryResultVideo extends InlineQueryResult {
	private String video_url;
	private String mime_type;
	private int video_width;
	private int video_height;
	private int video_duration;
	private String description;

	public InlineQueryResultVideo(String id, String thumb_url, String title, InputMessageContent input_message_content,
			String video_url, String mime_type, int video_width, int video_height, int video_duration,
			String description) {
		super("video", video_url, thumb_url, title, input_message_content);
		this.video_url = video_url;
		this.mime_type = mime_type;
		this.video_width = video_width;
		this.video_height = video_height;
		this.video_duration = video_duration;
		this.description = description;
	}

	public InlineQueryResultVideo(String id, String video_url, String mime_type, String thumb_url, String title) {
		super("video", id);
		this.video_url = video_url;
		this.mime_type = mime_type;
		super.setThumb_url(thumb_url);
		super.setTitle(title);
	}

	public String getVideo_url() {
		return video_url;
	}

	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}

	public String getMime_type() {
		return mime_type;
	}

	public void setMime_type(String mime_type) {
		this.mime_type = mime_type;
	}

	public int getVideo_width() {
		return video_width;
	}

	public void setVideo_width(int video_width) {
		this.video_width = video_width;
	}

	public int getVideo_height() {
		return video_height;
	}

	public void setVideo_height(int video_height) {
		this.video_height = video_height;
	}

	public int getVideo_duration() {
		return video_duration;
	}

	public void setVideo_duration(int video_duration) {
		this.video_duration = video_duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		if (getInput_message_content() != null) {
			writer.key("input_message_content");
			writer.value(getInput_message_content().toJSONObject());
		}
		writer.key("video_url");
		writer.value(getVideo_url());
		writer.key("mime_type");
		writer.value(getMime_type());
		if (getDescription() != null) {
			writer.key("description");
			writer.value(getDescription());
		}
		writer.key("thumb_url");
		writer.value(getThumb_url());
		writer.key("video_width");
		writer.value(getVideo_width());
		writer.key("video_height");
		writer.value(getVideo_height());
		writer.key("video_duration");
		writer.value(getVideo_duration());
		writer.endObject();
		return new JSONObject(writer.toString());
	}
}
