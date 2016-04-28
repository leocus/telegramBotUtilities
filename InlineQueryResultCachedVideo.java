package org.altervista.leocus.telegrambotutilities;

public class InlineQueryResultCachedVideo extends InlineQueryResultCached {

	public InlineQueryResultCachedVideo(String id, String thumb_url, String title, String file_id, String description,
			String caption, InlineKeyboardMarkup reply_markup, InputMessageContent input_message_content) {
		super("video", id, thumb_url, title, file_id, description, caption, reply_markup, input_message_content);
	}

	public InlineQueryResultCachedVideo(String id, String thumb_url, String title, String file_id) {
		super("video", id, thumb_url, title, file_id, null, null, null, null);
	}
}
