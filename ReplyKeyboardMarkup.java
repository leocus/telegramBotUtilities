package org.altervista.leocus.telegrambotutilities;

import org.json.JSONStringer;
import org.json.JSONWriter;

public class ReplyKeyboardMarkup implements ReplyMarkup {
	private String[][] keyboard;
	private boolean resize_keyboard;
	private boolean one_time_keyboard;
	private boolean selective;

	public ReplyKeyboardMarkup(String[][] keyboard, boolean resize_keyboard, boolean one_time_keyboard,
			boolean selective) {
		super();
		this.keyboard = keyboard;
		this.resize_keyboard = resize_keyboard;
		this.one_time_keyboard = one_time_keyboard;
		this.selective = selective;
	}

	/**
	 * Creates a Keyboard Markup with the String array and setting all boolean
	 * to false
	 * 
	 * @param keyboard
	 */
	public ReplyKeyboardMarkup(String[][] keyboard) {
		super();
		this.keyboard = keyboard;
		resize_keyboard = false;
		one_time_keyboard = false;
		selective = false;
	}

	public String[][] getKeyboard() {
		return keyboard;
	}

	public void setKeyboard(String[][] keyboard) {
		this.keyboard = keyboard;
	}

	public boolean isResize_keyboard() {
		return resize_keyboard;
	}

	public void setResize_keyboard(boolean resize_keyboard) {
		this.resize_keyboard = resize_keyboard;
	}

	public boolean isOne_time_keyboard() {
		return one_time_keyboard;
	}

	public void setOne_time_keyboard(boolean one_time_keyboard) {
		this.one_time_keyboard = one_time_keyboard;
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
		s.key("keyboard");
		s.array();
		for (int i = 0; i < keyboard.length; i++) {
			s.array();
			for (int j = 0; j < keyboard[i].length; j++) {
				s.value(keyboard[i][j]);
			}
			s.endArray();
		}
		s.endArray();
		s.key("resize_keyboard");
		s.value(resize_keyboard);
		s.key("one_time_keyboard");
		s.value(one_time_keyboard);
		s.key("selective");
		s.value(selective);
		s.endObject();
		return s.toString();
	}

}
