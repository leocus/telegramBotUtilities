package org.altervista.leocus.telegrambotutilities;

import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONWriter;

class InlineQueryResultCached extends InlineQueryResult {
	String file_id;
	String description; // optional
	String caption; // max 200 char., optional
	InlineKeyboardMarkup reply_markup; // optional
	InputMessageContent input_message_content; // optional

	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
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

	public InlineKeyboardMarkup getReply_markup() {
		return reply_markup;
	}

	public void setReply_markup(InlineKeyboardMarkup reply_markup) {
		this.reply_markup = reply_markup;
	}

	public InputMessageContent getInput_message_content() {
		return input_message_content;
	}

	public void setInput_message_content(InputMessageContent input_message_content) {
		this.input_message_content = input_message_content;
	}

	public JSONObject toJSONObject() {
		JSONWriter w = new JSONStringer();
		w.key("type");
		w.value(getType());
		w.key("id");
		w.value(getId());

		w.key(getType() + "_file_id");
		w.value(file_id);

		if (getTitle() != null) {
			w.key("title");
			w.value(getTitle());
		}

		if (description != null) {
			w.key("description");
			w.value(description);
		}
		if (caption != null) {
			w.key("caption");
			w.value(caption);
		}

		if (reply_markup != null) {
			w.key("reply_markup");
			w.value(reply_markup.toJSONString());
		}

		if (input_message_content != null) {
			w.key("input_message_content");
			w.value(input_message_content.toJSONObject());
		}

		return new JSONObject(w.toString());
	}

	public InlineQueryResultCached(String type, String id, String file_id) {
		super(type, id);
		this.file_id = file_id;
	}

	public InlineQueryResultCached(String type, String id, String thumb_url, String title, String file_id,
			String description, String caption, InlineKeyboardMarkup reply_markup,
			InputMessageContent input_message_content) {
		super(type, file_id, thumb_url, title, input_message_content);
		this.file_id = file_id;
		this.description = description;
		this.caption = caption;
		this.reply_markup = reply_markup;
		this.input_message_content = input_message_content;
	}

}
