package org.altervista.leocus.telegrambotutilities;

import org.json.JSONStringer;
import org.json.JSONWriter;

public class ForceReply implements ReplyMarkup {
	private boolean force_reply = true;
	private boolean selective;

	/**
	 * Upon receiving a message with this object, Telegram clients will display
	 * a reply interface to the user (act as if the user has selected the bot‘s
	 * message and tapped ’Reply'). This can be extremely useful if you want to
	 * create user-friendly step-by-step interfaces without having to sacrifice
	 * privacy mode.
	 * 
	 * @param selective
	 *            Optional. Use this parameter if you want to force reply from
	 *            specific users only. Targets: 1) users that are @mentioned in
	 *            the text of the Message object; 2) if the bot's message is a
	 *            reply (has reply_to_message_id), sender of the original
	 *            message.
	 */
	public ForceReply(boolean selective) {
		super();
		this.selective = selective;
	}

	public boolean isForce_reply() {
		return force_reply;
	}

	public void setForce_reply(boolean force_reply) {
		this.force_reply = force_reply;
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
		s.key("force_reply");
		s.value(force_reply);
		s.key("selective");
		s.value(selective);
		s.endObject();
		return s.toString();
	}

}
