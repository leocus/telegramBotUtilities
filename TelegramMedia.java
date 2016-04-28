package org.altervista.leocus.telegrambotutilities;

public class TelegramMedia {
	String url;
	int size;

	public TelegramMedia(String url, int size) {
		super();
		this.url = url;
		this.size = size;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
