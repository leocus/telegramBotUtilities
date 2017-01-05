package org.altervista.leocus.telegrambotutilities;

public class InlineQueryResultCachedSticker extends InlineQueryResultCached {

	public InlineQueryResultCachedSticker(String id, String thumb_url, String file_id,
			InlineKeyboardMarkup reply_markup, InputMessageContent input_message_content) {
		super("sticker", id, thumb_url, null, file_id, null, null, reply_markup, input_message_content);
	}

	public InlineQueryResultCachedSticker(String id, String thumb_url, String file_id) {
		super("sticker", id, thumb_url, null, file_id, null, null, null, null);
	}
}