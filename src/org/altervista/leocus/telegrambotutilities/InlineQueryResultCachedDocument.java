package org.altervista.leocus.telegrambotutilities;

public class InlineQueryResultCachedDocument extends InlineQueryResultCached {

	public InlineQueryResultCachedDocument(String id, String thumb_url, String title, String file_id,
			String description, String caption, InlineKeyboardMarkup reply_markup,
			InputMessageContent input_message_content) {
		super("document", id, thumb_url, title, file_id, description, caption, reply_markup, input_message_content);
	}

	public InlineQueryResultCachedDocument(String id, String thumb_url, String title, String file_id) {
		super("document", id, thumb_url, title, file_id, null, null, null, null);
	}
}
