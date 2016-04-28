package org.altervista.leocus.telegrambotutilities;

import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONWriter;

public class InputLocationMessageContent implements InputMessageContent {
	private float latitude;
	private float longitude;

	public InputLocationMessageContent(float latitude, float longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
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

	@Override
	public JSONObject toJSONObject() {
		JSONWriter w = new JSONStringer();
		w.object();
		w.key("latitude");
		w.value(latitude);
		w.key("longitude");
		w.value(longitude);
		w.endObject();

		return new JSONObject(w.toString());
	}

}
