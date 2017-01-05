package org.altervista.leocus.telegrambotutilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Message {
	private int id;
	private User from;
	private int date;
	private Chat chat;
	private User forward_from;
	private int forward_date;
	private Message reply_to_message;
	private String text;
	private Audio audio;
	private Document document;
	private PhotoSize[] photo;
	private Sticker sticker;
	private Video video;
	private Voice voice;
	private String caption;
	private Contact contact;
	private Location location;
	private User new_chat_participant;
	private User left_chat_participant;
	private String new_chat_title;
	private PhotoSize[] new_chat_photo;
	private boolean delete_chat_photo;
	private boolean group_chat_created;
	private boolean supergroup_chat_created;
	private boolean channel_chat_created;
	private int migrate_to_chat_id;
	private int migrate_from_chat_id;

	/**
	 * Create a new Message Object with only mandatory parameters
	 * 
	 * @param id
	 *            Message id
	 * @param date
	 *            Message date (int, unix format)
	 * @param chat
	 *            Conversation the message belongs to
	 */
	public Message(int id, int date, Chat chat) {
		super();
		this.id = id;
		this.date = date;
		this.chat = chat;
	}

	public Message(JSONObject object) {
		JSONObject temp;
		JSONArray tempArr;
		id = object.getInt("message_id");
		// "from" field
		try {
			temp = object.getJSONObject("from");
			from = new User(temp);
		} catch (JSONException e) {
//			from = null;
		}

		try {
			temp = object.getJSONObject("chat");
			chat = new Chat(temp);
		} catch (JSONException e) {
//			chat = null;
		}

		date = object.getInt("date");
		try {
			temp = object.getJSONObject("forward_from");
			forward_from = new User(temp);
		} catch (JSONException e) {
//			forward_from = null;
		}

		try {
			forward_date = object.getInt("forward_date");
		} catch (JSONException e) {
//			forward_date = -1;
		}

		try {
			temp = object.getJSONObject("reply_to_message");
			reply_to_message = new Message(temp);
		} catch (JSONException e) {
//			reply_to_message = null;
		}

		try {
			text = object.getString("text");
		} catch (JSONException e) {
//			text = null;
		}

		try {
			temp = object.getJSONObject("audio");
			audio = new Audio(temp);
		} catch (JSONException e) {
//			audio = null;
		}

		try {
			temp = object.getJSONObject("document");
			document = new Document(temp);
		} catch (JSONException e) {
//			document = null;
		}

		try {
			tempArr = object.getJSONArray("photo");
			photo = new PhotoSize[tempArr.length()];
			for (int i = 0; i < tempArr.length(); i++) {
				photo[i] = new PhotoSize(tempArr.getJSONObject(i));
			}
		} catch (JSONException e) {
//			photo = null;
		}

		try {
			temp = object.getJSONObject("sticker");
			sticker = new Sticker(temp);
		} catch (JSONException e) {
//			sticker = null;
		}

		try {
			temp = object.getJSONObject("video");
			video = new Video(temp);
		} catch (JSONException e) {
//			video = null;
		}

		try {
			temp = object.getJSONObject("voice");
			voice = new Voice(temp);
		} catch (JSONException e) {
//			System.out.println(e.getMessage());
//			voice = null;
		}

		try {
			caption = object.getString("caption");
		} catch (JSONException e) {
//			caption = null;
		}

		try {
			temp = object.getJSONObject("contact");
			contact = new Contact(temp);
		} catch (JSONException e) {
//			contact = null;
		}

		try {
			temp = object.getJSONObject("location");
			location = new Location(temp);
		} catch (JSONException e) {
//			location = null;
		}

		try {
			temp = object.getJSONObject("new_chat_participant");
			new_chat_participant = new User(temp);
		} catch (JSONException e) {
//			new_chat_participant = null;
		}

		try {
			temp = object.getJSONObject("left_chat_participant");
			left_chat_participant = new User(temp);
		} catch (JSONException e) {
//			left_chat_participant = null;
		}

		try {
			new_chat_title = object.getString("new_chat_title");
		} catch (JSONException e) {
//			new_chat_title = null;
		}

		try {
			tempArr = object.getJSONArray("new_chat_photo");
			new_chat_photo = new PhotoSize[tempArr.length()];
			for (int i = 0; i < tempArr.length(); i++) {
				new_chat_photo[i] = new PhotoSize(tempArr.getJSONObject(i));
			}
		} catch (JSONException e) {
//			new_chat_photo = null;
		}

		try {
			delete_chat_photo = object.getBoolean("delete_chat_photo");
		} catch (JSONException e) {
			delete_chat_photo = false;
		}

		try {
			group_chat_created = object.getBoolean("group_chat_created");
		} catch (JSONException e) {
			group_chat_created = false;
		}

		try {
			supergroup_chat_created = object.getBoolean("supergroup_chat_created");
		} catch (JSONException e) {
			supergroup_chat_created = false;
		}

		try {
			channel_chat_created = object.getBoolean("channel_chat_created");
		} catch (JSONException e) {
			channel_chat_created = false;
		}

		try {
			migrate_to_chat_id = object.getInt("migrate_to_chat_id");
		} catch (JSONException e) {
			migrate_to_chat_id = -1;
		}

		try {
			migrate_from_chat_id = object.getInt("migrate_from_chat_id");
		} catch (JSONException e) {
			migrate_from_chat_id = -1;
		}
	}

	public Message(int id, User from, int date, Chat chat, User forward_from, int forward_date,
			Message reply_to_message, String text, Audio audio, Document document, PhotoSize[] photo, Sticker sticker,
			Video video, Voice voice, String caption, Contact contact, Location location, User new_chat_participant,
			User left_chat_participant, String new_chat_title, PhotoSize[] new_chat_photo, boolean delete_chat_photo,
			boolean group_chat_created, boolean supergroup_chat_created, boolean channel_chat_created,
			int migrate_to_chat_id, int migrate_from_chat_id) {
		super();
		this.id = id;
		this.from = from;
		this.date = date;
		this.chat = chat;
		this.forward_from = forward_from;
		this.forward_date = forward_date;
		this.reply_to_message = reply_to_message;
		this.text = text;
		this.audio = audio;
		this.document = document;
		this.photo = photo;
		this.sticker = sticker;
		this.video = video;
		this.voice = voice;
		this.caption = caption;
		this.contact = contact;
		this.location = location;
		this.new_chat_participant = new_chat_participant;
		this.left_chat_participant = left_chat_participant;
		this.new_chat_title = new_chat_title;
		this.new_chat_photo = new_chat_photo;
		this.delete_chat_photo = delete_chat_photo;
		this.group_chat_created = group_chat_created;
		this.supergroup_chat_created = supergroup_chat_created;
		this.channel_chat_created = channel_chat_created;
		this.migrate_to_chat_id = migrate_to_chat_id;
		this.migrate_from_chat_id = migrate_from_chat_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getFrom() {
		return from;
	}

	public void setFrom(User from) {
		this.from = from;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	public User getForward_from() {
		return forward_from;
	}

	public void setForward_from(User forward_from) {
		this.forward_from = forward_from;
	}

	public int getForward_date() {
		return forward_date;
	}

	public void setForward_date(int forward_date) {
		this.forward_date = forward_date;
	}

	public Message getReply_to_message() {
		return reply_to_message;
	}

	public void setReply_to_message(Message reply_to_message) {
		this.reply_to_message = reply_to_message;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Audio getAudio() {
		return audio;
	}

	public void setAudio(Audio audio) {
		this.audio = audio;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public PhotoSize[] getPhoto() {
		return photo;
	}

	public void setPhoto(PhotoSize[] photo) {
		this.photo = photo;
	}

	public Sticker getSticker() {
		return sticker;
	}

	public void setSticker(Sticker sticker) {
		this.sticker = sticker;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public Voice getVoice() {
		return voice;
	}

	public void setVoice(Voice voice) {
		this.voice = voice;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public User getNew_chat_participant() {
		return new_chat_participant;
	}

	public void setNew_chat_participant(User new_chat_participant) {
		this.new_chat_participant = new_chat_participant;
	}

	public User getLeft_chat_participant() {
		return left_chat_participant;
	}

	public void setLeft_chat_participant(User left_chat_participant) {
		this.left_chat_participant = left_chat_participant;
	}

	public String getNew_chat_title() {
		return new_chat_title;
	}

	public void setNew_chat_title(String new_chat_title) {
		this.new_chat_title = new_chat_title;
	}

	public PhotoSize[] getNew_chat_photo() {
		return new_chat_photo;
	}

	public void setNew_chat_photo(PhotoSize[] new_chat_photo) {
		this.new_chat_photo = new_chat_photo;
	}

	public boolean isDelete_chat_photo() {
		return delete_chat_photo;
	}

	public void setDelete_chat_photo(boolean delete_chat_photo) {
		this.delete_chat_photo = delete_chat_photo;
	}

	public boolean isGroup_chat_created() {
		return group_chat_created;
	}

	public void setGroup_chat_created(boolean group_chat_created) {
		this.group_chat_created = group_chat_created;
	}

	public boolean isSupergroup_chat_created() {
		return supergroup_chat_created;
	}

	public void setSupergroup_chat_created(boolean supergroup_chat_created) {
		this.supergroup_chat_created = supergroup_chat_created;
	}

	public boolean isChannel_chat_created() {
		return channel_chat_created;
	}

	public void setChannel_chat_created(boolean channel_chat_created) {
		this.channel_chat_created = channel_chat_created;
	}

	public int getMigrate_to_chat_id() {
		return migrate_to_chat_id;
	}

	public void setMigrate_to_chat_id(int migrate_to_chat_id) {
		this.migrate_to_chat_id = migrate_to_chat_id;
	}

	public int getMigrate_from_chat_id() {
		return migrate_from_chat_id;
	}

	public void setMigrate_from_chat_id(int migrate_from_chat_id) {
		this.migrate_from_chat_id = migrate_from_chat_id;
	}

}
