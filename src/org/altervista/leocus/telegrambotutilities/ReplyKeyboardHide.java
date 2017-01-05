package org.altervista.leocus.telegrambotutilities;

import org.json.JSONStringer;
import org.json.JSONWriter;

public class ReplyKeyboardHide implements ReplyMarkup {
	private boolean hide_keyboard = true;
	private boolean selective;

	/**
	 * Upon receiving a message with this object, Telegram clients will hide the
	 * current custom keyboard and display the default letter-keyboard. By
	 * default, custom keyboards are displayed until a new keyboard is sent by a
	 * bot. An exception is made for one-time keyboards that are hidden
	 * immediately after the user presses a button (see ReplyKeyboardMarkup).
	 * 
	 * @param selective
	 *            Optional. Use this parameter if you want to hide keyboard for
	 *            specific users only. Targets: 1) users that are @mentioned in
	 *            the text of the Message object; 2) if the bot's message is a
	 *            reply (has reply_to_message_id), sender of the original
	 *            message.
	 */
	public ReplyKeyboardHide(boolean selective) {
		super();
		this.selective = selective;
	}

	public boolean isHide_keyboard() {
		return hide_keyboard;
	}

	public void setHide_keyboard(boolean hide_keyboard) {
		this.hide_keyboard = hide_keyboard;
	}

	public boolean isSelective() {
		return selective;
	}

	public void setSelective(boolean selective) {
		this.selective = selective;
	}

	public String toJSONString() {
		JSONWriter s = new JSONStringer();
		s.object();
		s.key("hide_keyboard");
		s.value(hide_keyboard);
		s.key("selective");
		s.value(selective);
		s.endObject();
		return s.toString();
	}

}
