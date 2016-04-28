package org.altervista.leocus.telegrambotutilities;

public class InlineQueryResultCachedPhoto extends InlineQueryResultCached {

	public InlineQueryResultCachedPhoto(String id, String file_id) {
		super("photo", id, file_id);
	}

	public InlineQueryResultCachedPhoto(String id, String thumb_url, String title, String file_id,
			String description, String caption, InlineKeyboardMarkup reply_markup,
			InputMessageContent input_message_content) {
		super("photo", id, thumb_url, title, file_id, description, caption, reply_markup, input_message_content);
	}

	
}