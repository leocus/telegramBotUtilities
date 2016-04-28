package org.altervista.leocus.telegrambotutilities;

import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONWriter;

public class InputTextMessageContent implements InputMessageContent {
	private String message_text;
	private String parse_mode;
	private boolean disable_web_page_preview;

	public InputTextMessageContent(String message_text, String parse_mode, boolean disable_web_page_preview) {
		super();
		this.message_text = message_text;
		this.parse_mode = parse_mode;
		this.disable_web_page_preview = disable_web_page_preview;
	}

	public String getMessage_text() {
		return message_text;
	}

	public void setMessage_text(String message_text) {
		this.message_text = message_text;
	}

	public String getParse_mode() {
		return parse_mode;
	}

	public void setParse_mode(String parse_mode) {
		this.parse_mode = parse_mode;
	}

	public boolean isDisable_web_page_preview() {
		return disable_web_page_preview;
	}

	public void setDisable_web_page_preview(boolean disable_web_page_preview) {
		this.disable_web_page_preview = disable_web_page_preview;
	}

	@Override
	public JSONObject toJSONObject() {
		JSONWriter w = new JSONStringer();
		w.object();
		w.key("message_text");
		w.value(message_text);
		w.key("parse_mode");
		w.value(parse_mode);
		w.key("disable_web_page_preview");
		w.value(disable_web_page_preview);
		w.endObject();

		return new JSONObject(w.toString());
	}

}
