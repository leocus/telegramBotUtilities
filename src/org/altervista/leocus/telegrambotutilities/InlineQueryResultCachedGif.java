package org.altervista.leocus.telegrambotutilities;

public class InlineQueryResultCachedGif extends InlineQueryResultCached {

	public InlineQueryResultCachedGif(String id, String thumb_url, String title, String file_id, String caption,
			InlineKeyboardMarkup reply_markup, InputMessageContent input_message_content) {
		super("gif", id, thumb_url, title, file_id, null, caption, reply_markup, input_message_content);
	}

	public InlineQueryResultCachedGif(String id, String thumb_url, String file_id) {
		super("gif", id, thumb_url, null, file_id, null, null, null, null);
	}
}
