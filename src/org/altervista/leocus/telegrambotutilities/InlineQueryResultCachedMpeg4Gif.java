package org.altervista.leocus.telegrambotutilities;

public class InlineQueryResultCachedMpeg4Gif extends InlineQueryResultCached {

	public InlineQueryResultCachedMpeg4Gif(String id, String thumb_url, String title, String file_id, String caption,
			InlineKeyboardMarkup reply_markup, InputMessageContent input_message_content) {
		super("mpeg4Gif", id, thumb_url, title, file_id, null, caption, reply_markup, input_message_content);
	}

	public InlineQueryResultCachedMpeg4Gif(String id, String thumb_url, String file_id) {
		super("mpeg4Gif", id, thumb_url, null, file_id, null, null, null, null);
	}
}