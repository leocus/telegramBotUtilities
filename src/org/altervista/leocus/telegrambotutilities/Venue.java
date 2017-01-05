package org.altervista.leocus.telegrambotutilities;

import org.json.JSONObject;

public class Venue {
	Location location;
	String title;
	String address;
	String foursquare_id; // optional

	public Venue(JSONObject obj) {
		location = new Location(obj.getJSONObject("location"));
		title = obj.getString("title");
		address = obj.getString("address");
		foursquare_id = obj.getString("foursquare_id");
	}

	public Venue(Location location, String title, String address, String foursquare_id) {
		super();
		this.location = location;
		this.title = title;
		this.address = address;
		this.foursquare_id = foursquare_id;
	}

	public Venue(Location location, String title, String address) {
		super();
		this.location = location;
		this.title = title;
		this.address = address;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
	
	
}