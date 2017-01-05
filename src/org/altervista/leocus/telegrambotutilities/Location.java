/**
 * 
 */
package org.altervista.leocus.telegrambotutilities;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author leonardo
 *
 */
public class Location {
	private float longitude;
	private float latitude;

	public Location(float longitude, float latitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public Location(JSONObject object) {
		try {
			longitude = (float) object.getDouble("longitude");
			latitude = (float) object.getDouble("latitude");
		} catch (JSONException e) {
		}
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

}
