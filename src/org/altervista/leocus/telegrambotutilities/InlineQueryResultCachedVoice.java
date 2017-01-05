package org.altervista.leocus.telegrambotutilities;

public class InlineQueryResultCachedVoice extends InlineQueryResultCached {

	public InlineQueryResultCachedVoice(String id, String thumb_url, String file_id, InlineKeyboardMarkup reply_markup,
			InputMessageContent input_message_content) {
		super("voice", id, thumb_url, null, file_id, null, null, reply_markup, input_message_content);
	}

	public InlineQueryResultCachedVoice(String id, String thumb_url, String file_id) {
		super("voice", id, thumb_url, null, file_id, null, null, null, null);
	}
}
