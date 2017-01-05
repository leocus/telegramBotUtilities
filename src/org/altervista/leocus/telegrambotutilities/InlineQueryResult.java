package org.altervista.leocus.telegrambotutilities;

import org.json.JSONObject;

public abstract class InlineQueryResult {
	private String type;
	private String id;
	private String thumb_url;
	private String title;
	private InputMessageContent input_message_content;

	public InlineQueryResult(String type, String id, String thumb_url, String title,
			InputMessageContent input_message_content) {
		super();
		this.type = type;
		this.id = id;
		this.thumb_url = thumb_url;
		this.title = title;
		this.input_message_content = input_message_content;
	}

	public InlineQueryResult(String type, String id) {
		super();
		this.type = type;
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getThumb_url() {
		return thumb_url;
	}

	public void setThumb_url(String thumb_url) {
		this.thumb_url = thumb_url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public InputMessageContent getInput_message_content() {
		return input_message_content;
	}

	public void setInput_message_content(InputMessageContent input_message_content) {
		this.input_message_content = input_message_content;
	}

	/**
	 * Generates a JSON-Object of inlineQueryResult
	 */
	public abstract JSONObject toJSONObject();
}
