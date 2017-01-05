package org.altervista.leocus.telegrambotutilities;

public class InlineKeyboardButton {
	String text;
	String url; // optional
	String callback_data; // optional
	String switch_inline_query; // optional

	/**
	 * Constructor, InlineKeyboardButton should have text != null and exactly
	 * one of the other fields != null
	 * 
	 * @param text
	 * @param url
	 * @param callback_data
	 * @param switch_inline_query
	 */
	public InlineKeyboardButton(String text, String url, String callback_data, String switch_inline_query) {
		super();
		this.text = text;
		this.url = url;
		this.callback_data = callback_data;
		this.switch_inline_query = switch_inline_query;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCallback_data() {
		return callback_data;
	}

	public void setCallback_data(String callback_data) {
		this.callback_data = callback_data;
	}

	public String getSwitch_inline_query() {
		return switch_inline_query;
	}

	public void setSwitch_inline_query(String switch_inline_query) {
		this.switch_inline_query = switch_inline_query;
	}
}
