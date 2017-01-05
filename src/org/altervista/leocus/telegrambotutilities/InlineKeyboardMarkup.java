package org.altervista.leocus.telegrambotutilities;

import org.json.JSONStringer;
import org.json.JSONWriter;

public class InlineKeyboardMarkup implements ReplyMarkup {
	InlineKeyboardButton[][] buttons;

	public InlineKeyboardMarkup(InlineKeyboardButton[][] buttons) {
		super();
		this.buttons = buttons;
	}

	public InlineKeyboardButton[][] getButtons() {
		return buttons;
	}

	public void setButtons(InlineKeyboardButton[][] buttons) {
		this.buttons = buttons;
	}

	public String toJSONString() {
		JSONWriter w = new JSONStringer();
		w.object();
		w.key("inline_keyboard");
		w.array();

		for (int i = 0; i < buttons.length; i++) {
			w.array();
			for (int j = 0; j < buttons[i].length; j++) {
				w.object();
				w.key("text");
				w.value(buttons[i][j].getText());
				if (buttons[i][j].getUrl() != null) {
					w.key("url");
					w.value(buttons[i][j].getUrl());
				}
				if (buttons[i][j].getCallback_data() != null) {
					w.key("callback_data");
					w.value(buttons[i][j].getCallback_data());
				}
				if (buttons[i][j].getSwitch_inline_query() != null) {
					w.key("switch_inline_query");
					w.value(buttons[i][j].getSwitch_inline_query());
				}
				w.endObject();
			}
			w.endArray();
		}

		w.endArray();
		w.endObject();
		return w.toString();
	}
}
