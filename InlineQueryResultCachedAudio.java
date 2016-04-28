package org.altervista.leocus.telegrambotutilities;

public class InlineQueryResultCachedAudio extends InlineQueryResultCached {

	public InlineQueryResultCachedAudio(String id, String thumb_url, String file_id, InlineKeyboardMarkup reply_markup,
			InputMessageContent input_message_content) {
		super("audio", id, thumb_url, null, file_id, null, null, reply_markup, input_message_content);
	}

	public InlineQueryResultCachedAudio(String id, String thumb_url, String file_id) {
		super("audio", id, thumb_url, null, file_id, null, null, null, null);
	}
}