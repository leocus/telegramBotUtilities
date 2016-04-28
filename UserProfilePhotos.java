package org.altervista.leocus.telegrambotutilities;

public class UserProfilePhotos {
	int total_count;
	PhotoSize[] photos;

	public UserProfilePhotos(int total_count, PhotoSize[] photos) {
		super();
		this.total_count = total_count;
		this.photos = photos;
	}

	public int getTotal_count() {
		return total_count;
	}

	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}

	public PhotoSize[] getPhotos() {
		return photos;
	}

	public void setPhotos(PhotoSize[] photos) {
		this.photos = photos;
	}

}
