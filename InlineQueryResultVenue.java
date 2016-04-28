package org.altervista.leocus.telegrambotutilities;

import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONWriter;

public class InlineQueryResultVenue extends InlineQueryResult {

	Location location;
	String address;
	String foursquare_id; // optional
	InlineKeyboardMarkup reply_markup; // optional
	int thumb_width; // optional
	int thumb_height; // optional

	public InlineQueryResultVenue(String type, String id, String thumb_url, String title, Location location,
			String address, String foursquare_id, InlineKeyboardMarkup reply_markup,
			InputMessageContent input_message_content, int thumb_width, int thumb_height) {
		super(type, foursquare_id, thumb_url, title, input_message_content);
		this.location = location;
		this.address = address;
		this.foursquare_id = foursquare_id;
		this.reply_markup = reply_markup;
		this.thumb_width = thumb_width;
		this.thumb_height = thumb_height;
	}

	public InlineQueryResultVenue(String type, String id, String thumb_url, String title,
			InputMessageContent input_message_content, Location location, String address) {
		super(type, id, thumb_url, title, input_message_content);
		this.location = location;
		this.address = address;
	}

	public InlineQueryResultVenue(String type, String id, String thumb_url, String title, Location location,
			String address) {
		super(type, id, thumb_url, title, null);
		this.location = location;
		this.address = address;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFoursquare_id() {
		return foursquare_id;
	}

	public void setFoursquare_id(String foursquare_id) {
		this.foursquare_id = foursquare_id;
	}

	public InlineKeyboardMarkup getReply_markup() {
		return reply_markup;
	}

	public void setReply_markup(InlineKeyboardMarkup reply_markup) {
		this.reply_markup = reply_markup;
	}

	public int getThumb_width() {
		return thumb_width;
	}

	public void setThumb_width(int thumb_width) {
		this.thumb_width = thumb_width;
	}

	public int getThumb_height() {
		return thumb_height;
	}

	public void setThumb_height(int thumb_height) {
		this.thumb_height = thumb_height;
	}

	public JSONObject toJSONObject() {
		JSONWriter w = new JSONStringer();
		w.object();
		w.key("type");
		w.value(getType());
		w.key("id");
		w.value(getId());
		w.key("longitude");
		w.value(location.getLongitude());
		w.key("latitude");
		w.value(location.getLatitude());
		w.key("title");
		w.value(getTitle());
		if (address != null) {
			w.key("address");
			w.value(address);
		}
		if (foursquare_id != null) {
			w.key("foursquare_id");
			w.value(foursquare_id);
		}
		if (reply_markup != null) {
			w.key("reply_markup");
			w.value(reply_markup.toJSONString());
		}
		if (getInput_message_content() != null) {
			w.key("input_message_content");
			w.value(getInput_message_content().toJSONObject());
		}
		if (getThumb_url() != null) {
			w.key("thumb_url");
			w.value(getThumb_url());
		}
		w.key("thumb_width");
		w.value(thumb_width);
		w.key("thumb_height");
		w.value(thumb_height);
		w.endObject();
		return new JSONObject(w.toString());
	}
}
