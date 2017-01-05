package org.altervista.leocus.telegrambotutilities;

import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONWriter;

public class InputVenueMessageContent implements InputMessageContent {
	private float latitude;
	private float longitude;
	private String title;
	private String address;
	private String foursquare_id;

	public InputVenueMessageContent(float latitude, float longitude, String title, String address,
			String foursquare_id) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.title = title;
		this.address = address;
		this.foursquare_id = foursquare_id;
	}

	public InputVenueMessageContent(float latitude, float longitude, String title, String address) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.title = title;
		this.address = address;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public JSONObject toJSONObject() {
		JSONWriter w = new JSONStringer();

		w.object();
		w.key("latitude");
		w.value(latitude);
		w.key("longitude");
		w.value(longitude);
		w.key("title");
		w.value(title);
		w.key("address");
		w.value(address);
		w.key("foursquare_id");
		w.value(foursquare_id);
		w.endObject();

		return new JSONObject(w.toString());
	}

}
