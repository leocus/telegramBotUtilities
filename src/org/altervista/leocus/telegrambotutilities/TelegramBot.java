package org.altervista.leocus.telegrambotutilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONWriter;

public class TelegramBot {
	private String url;
	private String token;

	public TelegramBot(String token) {
		url = "https://api.telegram.org/bot" + token;
		this.token = token;
	}

	/**
	 * Returns bot's updates. Insert a value less or equal to 0 to ignore one or
	 * more parameters;
	 * 
	 * @param offset
	 *            the update_id from which start update array
	 * @param limit
	 *            maximum number of updates
	 * @param timeout
	 * @return
	 * @throws GettingUpdatesException
	 *             Generic Exception thrown when the "ok" field in getUpdates is
	 *             set to false
	 * @throws IOException
	 * @throws EmptyUpdatesException
	 *             Thrown if there are no updates for your bot.
	 */
	public Update[] getUpdates(int offset, int limit, int timeout)
			throws GettingUpdatesException, IOException, EmptyUpdatesException {
		Update[] updates = null;
		String temp;
		String inputLine = "";
		JSONObject result;
		JSONArray updatesArray;
		JSONObject object;
		JSONObject element;

		// Creates an url with desired parameters
		URL updateUrl = new URL(url + "/getUpdates?" + (offset < 0 ? "" : "offset=" + offset)
				+ (limit < 0 ? "" : "&limit=" + limit) + (timeout < 0 ? "" : "timeout=" + timeout));
		BufferedReader in = new BufferedReader(new InputStreamReader(updateUrl.openStream()));

		// create the results string
		while ((temp = in.readLine()) != null) {
			inputLine += temp;
		}

		in.close();
		if (inputLine.compareTo("") == 0) {
			throw new EmptyUpdatesException();
		}
		result = new JSONObject(inputLine);

		// if the "ok" parameter in JSON results is set to false, it throws an
		// exception
		if (!result.getBoolean("ok")) {
			throw new GettingUpdatesException();
		}

		updatesArray = result.getJSONArray("result");

		updates = new Update[updatesArray.length()];

		for (int i = 0; i < updates.length; i++) {

			Message msg = null;
			InlineQuery inlQuery = null;
			Chosen_inline_result inl_res = null;
			element = updatesArray.getJSONObject(i);
			CallbackQuery callbackQuery = null;
			try {
				object = element.getJSONObject("message");
				msg = new Message(object);
			} catch (JSONException e) {
				// msg = null;
			}

			try {
				// I use again the "object" reference
				object = element.getJSONObject("inline_query");
				inlQuery = new InlineQuery(object);
			} catch (JSONException e) {
				// inlQuery = null;
			}

			try {
				object = element.getJSONObject("chosen_inline_result");
				inl_res = new Chosen_inline_result(object);
			} catch (JSONException e) {
				// inl_res = null;
			}

			try {
				object = element.getJSONObject("callback_query");
				callbackQuery = new CallbackQuery(object);
			} catch (JSONException e) {

			}
			updates[i] = new Update((int) element.get("update_id"), msg, inlQuery, inl_res, callbackQuery);
		}
		return updates;
	}

	/**
	 * Sends a simple message.
	 * 
	 * @param chat_id
	 *            Recipient's id or channel's id
	 * @param text
	 *            The string to send.
	 * @return On success, the sent Message is returned
	 * @throws IOException
	 */
	public Message sendMessage(String chat_id, String text) throws IOException {

		URL updateUrl = new URL(url + "/sendMessage?chat_id=" + chat_id + "&text=" + URLEncoder.encode(text, "utf-8"));
		JSONObject respObject;
		String temp, response = "";
		BufferedReader in = new BufferedReader(new InputStreamReader(updateUrl.openStream()));
		// create the results string
		while ((temp = in.readLine()) != null) {
			response += temp;
		}

		in.close();

		respObject = new JSONObject(response);
		if (respObject.getBoolean("ok")) {
			return new Message(respObject.getJSONObject("result"));
		} else {
			return null;
		}
	}

	/**
	 * Sends a simple message.
	 * 
	 * @param chat_id
	 *            Recipient's id or channel's id.
	 * @param text
	 *            The text to send.
	 * @param parse_mode
	 *            Markdown or HTML, used for formatting text
	 * @param disable_web_page_preview
	 * @param disable_notification
	 *            true: silent notifications
	 * @param reply_to_message_id
	 * @param reply_markup
	 *            OPTIONAL, set it to null if you don't want to use it
	 * @return
	 *         <ul>
	 *         <li>if operation is successful, the sent Message</li>
	 *         <li>else null</li>
	 *         </ul>
	 * @throws IOException
	 */
	public Message sendMessage(String chat_id, String text, String parse_mode, boolean disable_web_page_preview,
			boolean disable_notification, int reply_to_message_id, ReplyMarkup reply_markup) throws IOException {
		JSONObject respObject;
		URL updateUrl = new URL(url + "/sendMessage?chat_id=" + chat_id + "&text=" + URLEncoder.encode(text, "utf-8")
				+ (parse_mode != null ? "&parse_mode=" + parse_mode : "")
				+ (disable_web_page_preview != false
						? "&disable_web_page_preview=" + Boolean.toString(disable_web_page_preview) : "")
				+ (disable_notification != false ? "&disable_notification=" + Boolean.toString(disable_notification)
						: "")
				+ (reply_to_message_id > 0 ? "&reply_to_message_id=" + reply_to_message_id : "") + (reply_markup != null
						? "&reply_markup=" + URLEncoder.encode(reply_markup.toJSONString(), "utf-8") : ""));
		String temp, response = "";

		BufferedReader in = new BufferedReader(new InputStreamReader(updateUrl.openStream()));
		// create the results string
		while ((temp = in.readLine()) != null) {
			response += temp;
		}

		in.close();

		respObject = new JSONObject(response);
		if (respObject.getBoolean("ok")) {
			return new Message(respObject.getJSONObject("result"));
		} else {
			return null;
		}
	}

	// TODO: RISCRIVERE DOCUMENTAZIONE
	/**
	 * Sends a file.
	 * 
	 * @param chat_id
	 *            Recipient's id or channel's id
	 * @param file
	 *            the file to send
	 * @param type
	 *            the file type, allowed types:
	 *            <ul>
	 *            <li>photo</li>
	 *            <li>document</li>
	 *            <li>audio</li>
	 *            <li>video</li>
	 *            <li>sticker</li>
	 *            <li>voice</li>
	 *            </ul>
	 * @throws IOException
	 * 
	 */
	public String sendFile(String chat_id, File file, String type) throws IOException {

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.addPart("chat_id", new StringBody(chat_id, ContentType.DEFAULT_TEXT));
		builder.addPart(type, new FileBody(file, ContentType.DEFAULT_BINARY));
		HttpEntity entity = builder.build();
		HttpPost post = new HttpPost(url + "/send" + type);

		post.setEntity(entity);

		CloseableHttpClient httpclient = HttpClients.createMinimal();
		HttpResponse response = httpclient.execute(post);

		return response.toString();

	}

	/**
	 * Sends a file.
	 * 
	 * @param chat_id
	 *            Recipient's id or channel's id
	 * @param file_id
	 *            file's id to send
	 * @param type
	 *            the file type, allowed types:
	 *            <ul>
	 *            <li>photo</li>
	 *            <li>document</li>
	 *            <li>audio</li>
	 *            <li>video</li>
	 *            <li>sticker</li>
	 *            <li>voice</li>
	 *            </ul>
	 * @throws IOException
	 *             thrown when there is an exception executing the request
	 */
	public String sendFile(String chat_id, String file_id, String type) throws IOException {
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.addPart("chat_id", new StringBody(chat_id, ContentType.DEFAULT_TEXT));
		builder.addPart(type, new StringBody(file_id, ContentType.DEFAULT_TEXT));
		HttpEntity entity = builder.build();
		HttpPost post = new HttpPost(url + "/send" + type);

		post.setEntity(entity);

		CloseableHttpClient httpclient = HttpClients.createMinimal();
		HttpResponse response = httpclient.execute(post);

		return response.toString();

	}

	private String sendFile(String chat_id, File file, String type, String caption, boolean disable_notification,
			int reply_to_message_id, ReplyMarkup reply_markup, int duration, String performer, String title, int width,
			int height) throws IOException {

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.addPart("chat_id", new StringBody(chat_id, ContentType.DEFAULT_TEXT));
		if (caption != null)
			builder.addPart("caption", new StringBody(caption, ContentType.DEFAULT_TEXT));
		if (disable_notification != false)
			builder.addPart("disable_notification",
					new StringBody(Boolean.toString(disable_notification), ContentType.DEFAULT_TEXT));
		if (reply_to_message_id > 0)
			builder.addPart("reply_to_message_id",
					new StringBody(Integer.toString(reply_to_message_id), ContentType.DEFAULT_TEXT));
		if (reply_markup != null)
			builder.addPart("reply_markup",
					new StringBody(URLEncoder.encode(reply_markup.toJSONString(), "utf-8"), ContentType.DEFAULT_TEXT));
		if (duration > 0)
			builder.addPart("duration", new StringBody(Integer.toString(duration), ContentType.DEFAULT_TEXT));
		if (performer != null)
			builder.addPart("performer", new StringBody(performer, ContentType.DEFAULT_TEXT));
		if (title != null)
			builder.addPart("title", new StringBody(title, ContentType.DEFAULT_TEXT));
		if (width > 0)
			builder.addPart("width", new StringBody(Integer.toString(width), ContentType.DEFAULT_TEXT));
		if (height > 0)
			builder.addPart("height", new StringBody(Integer.toString(height), ContentType.DEFAULT_TEXT));
		builder.addPart(type, new FileBody(file, ContentType.DEFAULT_BINARY));
		HttpEntity entity = builder.build();
		HttpPost post = new HttpPost(url + "/send" + type);

		post.setEntity(entity);

		CloseableHttpClient httpclient = HttpClients.createMinimal();
		HttpResponse response = httpclient.execute(post);

		return response.toString();

	}

	private String sendFile(String chat_id, String file_id, String type, String caption, boolean disable_notification,
			int reply_to_message_id, ReplyMarkup reply_markup, int duration, String performer, String title, int width,
			int height) throws IOException {

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.addPart("chat_id", new StringBody(chat_id, ContentType.DEFAULT_TEXT));
		if (caption != null)
			builder.addPart("caption", new StringBody(caption, ContentType.DEFAULT_TEXT));
		if (disable_notification != false)
			builder.addPart("disable_notification",
					new StringBody(Boolean.toString(disable_notification), ContentType.DEFAULT_TEXT));
		if (reply_to_message_id > 0)
			builder.addPart("reply_to_message_id",
					new StringBody(Integer.toString(reply_to_message_id), ContentType.DEFAULT_TEXT));
		if (reply_markup != null)
			builder.addPart("reply_markup",
					new StringBody(URLEncoder.encode(reply_markup.toJSONString(), "utf-8"), ContentType.DEFAULT_TEXT));
		if (duration > 0)
			builder.addPart("duration", new StringBody(Integer.toString(duration), ContentType.DEFAULT_TEXT));
		if (performer != null)
			builder.addPart("performer", new StringBody(performer, ContentType.DEFAULT_TEXT));
		if (title != null)
			builder.addPart("title", new StringBody(title, ContentType.DEFAULT_TEXT));
		if (width > 0)
			builder.addPart("width", new StringBody(Integer.toString(width), ContentType.DEFAULT_TEXT));
		if (height > 0)
			builder.addPart("height", new StringBody(Integer.toString(height), ContentType.DEFAULT_TEXT));
		builder.addPart(type, new StringBody(file_id, ContentType.DEFAULT_TEXT));
		HttpEntity entity = builder.build();
		HttpPost post = new HttpPost(url + "/send" + type);

		post.setEntity(entity);

		CloseableHttpClient httpclient = HttpClients.createMinimal();
		HttpResponse response = httpclient.execute(post);

		return response.toString();

	}

	/**
	 * Sends a photo from a file
	 * 
	 * @param chat_id
	 *            Recipient's id or channel's id
	 * @param file
	 *            the photo to send
	 * @param caption
	 * @param disable_notification
	 * @param reply_to_message_id
	 * @param reply_markup
	 *            OPTIONAL, set it to null if you don't want to use it
	 * @return A String containing the response
	 * @throws IOException
	 */
	public String sendPhoto(String chat_id, File file, String caption, boolean disable_notification,
			int reply_to_message_id, ReplyMarkup reply_markup) throws IOException {
		return sendFile(chat_id, file, "photo", caption, disable_notification, reply_to_message_id, reply_markup, -1,
				null, null, -1, -1);
	}

	/**
	 * Sends an audio from file
	 * 
	 * @param chat_id
	 *            Recipient's id or channel's id.
	 * @param file
	 *            The file containing the audio.
	 * @param duration
	 * @param performer
	 * @param title
	 * @param disable_notification
	 * @param reply_to_message_id
	 * @param reply_markup
	 *            OPTIONAL, set it to null if you don't want to use it
	 * @return A String containing the response
	 * @throws IOException
	 */
	public String sendAudio(String chat_id, File file, int duration, String performer, String title,
			boolean disable_notification, int reply_to_message_id, ReplyMarkup reply_markup) throws IOException {
		return sendFile(chat_id, file, "audio", null, disable_notification, reply_to_message_id, reply_markup, duration,
				performer, title, -1, -1);
	}

	/**
	 * Sends a document from a file
	 * 
	 * @param chat_id
	 *            Recipient's id or channel's id
	 * @param file
	 *            The file containing the document.
	 * @param caption
	 * @param disable_notification
	 * @param reply_to_message_id
	 * @param reply_markup
	 *            OPTIONAL, set it to null if you don't want to use it
	 * @return A String containing the response
	 * @throws IOException
	 */
	public String sendDocument(String chat_id, File file, String caption, boolean disable_notification,
			int reply_to_message_id, ReplyMarkup reply_markup) throws IOException {
		return sendFile(chat_id, file, "document", caption, disable_notification, reply_to_message_id, reply_markup, -1,
				null, null, -1, -1);
	}

	/**
	 * Sends a sticker from a file
	 * 
	 * @param chat_id
	 *            Recipient's id or channel's id
	 * @param file
	 * @param disable_notification
	 * @param reply_to_message_id
	 * @param reply_markup
	 *            OPTIONAL, set it to null if you don't want to use it
	 * @return A String containing the response
	 * @throws IOException
	 */
	public String sendSticker(String chat_id, File file, boolean disable_notification, int reply_to_message_id,
			ReplyMarkup reply_markup) throws IOException {
		return sendFile(chat_id, file, "sticker", null, disable_notification, reply_to_message_id, reply_markup, -1,
				null, null, -1, -1);
	}

	/**
	 * Sends a video from a file
	 * 
	 * @param chat_id
	 *            Recipient's id or channel's id
	 * @param file
	 *            the video to send
	 * @param caption
	 * @param disable_notification
	 * @param reply_to_message_id
	 * @param reply_markup
	 *            OPTIONAL, set it to null if you don't want to use it
	 * @return A String containing the response
	 * @throws IOException
	 */
	public String sendVideo(String chat_id, File file, int duration, int width, int height, String caption,
			boolean disable_notification, int reply_to_message_id, ReplyMarkup reply_markup) throws IOException {
		return sendFile(chat_id, file, "video", caption, disable_notification, reply_to_message_id, reply_markup,
				duration, null, null, width, height);
	}

	/**
	 * Sends a vocal audio from a file.
	 * 
	 * @param chat_id
	 *            Recipient's id or channel's id
	 * @param file
	 *            the file containing the audio.
	 * @param duration
	 * @param disable_notification
	 * @param reply_to_message_id
	 * @param reply_markup
	 *            OPTIONAL, set it to null if you don't want to use it
	 * @return A String containing the response
	 * @throws IOException
	 */
	public String sendVoice(String chat_id, File file, int duration, boolean disable_notification,
			int reply_to_message_id, ReplyMarkup reply_markup) throws IOException {
		return sendFile(chat_id, file, "voice", null, disable_notification, reply_to_message_id, reply_markup, duration,
				null, null, -1, -1);
	}

	/**
	 * Sends a photo from a previously sent or received file
	 * 
	 * @param chat_id
	 *            Recipient's id or channel's id
	 * @param file
	 *            the file_id of the photo to send
	 * @param caption
	 * @param disable_notification
	 * @param reply_to_message_id
	 * @param reply_markup
	 *            OPTIONAL, set it to null if you don't want to use it
	 * @return A String containing the response
	 * @throws IOException
	 */
	public String sendPhoto(String chat_id, String file, String caption, boolean disable_notification,
			int reply_to_message_id, ReplyMarkup reply_markup) throws IOException {
		return sendFile(chat_id, file, "photo", caption, disable_notification, reply_to_message_id, reply_markup, -1,
				null, null, -1, -1);
	}

	/**
	 * Sends an audio from a previously sent or received file
	 * 
	 * @param chat_id
	 *            Recipient's id or channel's id.
	 * @param file
	 *            The file_id of the the audio.
	 * @param duration
	 * @param performer
	 * @param title
	 * @param disable_notification
	 * @param reply_to_message_id
	 * @param reply_markup
	 *            OPTIONAL, set it to null if you don't want to use it
	 * @return A String containing the response
	 * @throws IOException
	 */

	public String sendAudio(String chat_id, String file, int duration, String performer, String title,
			boolean disable_notification, int reply_to_message_id, ReplyMarkup reply_markup) throws IOException {
		return sendFile(chat_id, file, "audio", null, disable_notification, reply_to_message_id, reply_markup, duration,
				performer, title, -1, -1);
	}

	/**
	 * Sends a document from a previously sent or received file
	 * 
	 * @param chat_id
	 *            Recipient's id or channel's id
	 * @param file
	 *            The file_id of the the document.
	 * @param caption
	 * @param disable_notification
	 * @param reply_to_message_id
	 * @param reply_markup
	 *            OPTIONAL, set it to null if you don't want to use it
	 * @return A String containing the response
	 * @throws IOException
	 */

	public String sendDocument(String chat_id, String file, String caption, boolean disable_notification,
			int reply_to_message_id, ReplyMarkup reply_markup) throws IOException {
		return sendFile(chat_id, file, "document", caption, disable_notification, reply_to_message_id, reply_markup, -1,
				null, null, -1, -1);
	}

	/**
	 * Sends a sticker from a previously sent or received file
	 * 
	 * @param chat_id
	 *            Recipient's id or channel's id
	 * @param file
	 *            the file_id of the sticker to send
	 * @param disable_notification
	 * @param reply_to_message_id
	 * @param reply_markup
	 *            OPTIONAL, set it to null if you don't want to use it
	 * @return A String containing the response
	 * @throws IOException
	 */

	public String sendSticker(String chat_id, String file, boolean disable_notification, int reply_to_message_id,
			ReplyMarkup reply_markup) throws IOException {
		return sendFile(chat_id, file, "sticker", null, disable_notification, reply_to_message_id, reply_markup, -1,
				null, null, -1, -1);
	}

	/**
	 * Sends a video from a previously sent or received file
	 * 
	 * @param chat_id
	 *            Recipient's id or channel's id
	 * @param file
	 *            the file_id of the video to send
	 * @param caption
	 * @param disable_notification
	 * @param reply_to_message_id
	 * @param reply_markup
	 *            OPTIONAL, set it to null if you don't want to use it
	 * @return A String containing the response
	 * @throws IOException
	 */

	public String sendVideo(String chat_id, String file, int duration, int width, int height, String caption,
			boolean disable_notification, int reply_to_message_id, ReplyMarkup reply_markup) throws IOException {
		return sendFile(chat_id, file, "video", caption, disable_notification, reply_to_message_id, reply_markup,
				duration, null, null, width, height);
	}

	/**
	 * Sends a vocal audio from a previously sent or received file.
	 * 
	 * @param chat_id
	 *            Recipient's id or channel's id
	 * @param file
	 *            the file_id of the the audio.
	 * @param duration
	 * @param disable_notification
	 * @param reply_to_message_id
	 * @param reply_markup
	 *            OPTIONAL, set it to null if you don't want to use it
	 * @return A String containing the response
	 * @throws IOException
	 */

	public String sendVoice(String chat_id, String file, int duration, boolean disable_notification,
			int reply_to_message_id, ReplyMarkup reply_markup) throws IOException {
		return sendFile(chat_id, file, "voice", null, disable_notification, reply_to_message_id, reply_markup, duration,
				null, null, -1, -1);
	}

	/**
	 * Returns the urls of media on a message
	 * 
	 * @param msg
	 *            The message
	 * @return an array of {@link TelegramMedia}, with more than one elements if
	 *         the file is a photo (because of the different sizes), else with
	 *         only one element.
	 * @throws IOException
	 */
	public TelegramMedia[] getFileUrl(Message msg) throws IOException {
		TelegramMedia[] files = null;
		String temp, response = "";

		if (msg.getAudio() != null) {
			URL updateUrl = new URL(url + "/getFile?file_id=" + msg.getAudio().getId());
			BufferedReader in = new BufferedReader(new InputStreamReader(updateUrl.openStream()));
			// create the results string
			while ((temp = in.readLine()) != null) {
				response += temp;
			}

			in.close();
			files = new TelegramMedia[1];
			files[0] = new TelegramMedia(
					"https://api.telegram.org/file/bot" + token + "/"
							+ new JSONObject(response.toString()).getJSONObject("result").getString("file_path"),
					new JSONObject(response.toString()).getJSONObject("result").getInt("file_size"));
		}

		if (msg.getVoice() != null) {
			URL updateUrl = new URL(url + "/getFile?file_id=" + msg.getVoice().getId());
			BufferedReader in = new BufferedReader(new InputStreamReader(updateUrl.openStream()));
			// create the results string
			while ((temp = in.readLine()) != null) {
				response += temp;
			}

			in.close();
			files = new TelegramMedia[1];
			files[0] = new TelegramMedia(
					"https://api.telegram.org/file/bot" + token + "/"
							+ new JSONObject(response.toString()).getJSONObject("result").getString("file_path"),
					new JSONObject(response.toString()).getJSONObject("result").getInt("file_size"));
		}

		if (msg.getVideo() != null) {
			URL updateUrl = new URL(url + "/getFile?file_id=" + msg.getVideo().getId());
			BufferedReader in = new BufferedReader(new InputStreamReader(updateUrl.openStream()));
			// create the results string
			while ((temp = in.readLine()) != null) {
				response += temp;
			}

			in.close();
			files = new TelegramMedia[1];
			files[0] = new TelegramMedia(
					"https://api.telegram.org/file/bot" + token + "/"
							+ new JSONObject(response.toString()).getJSONObject("result").getString("file_path"),
					new JSONObject(response.toString()).getJSONObject("result").getInt("file_size"));
		}

		if (msg.getSticker() != null) {
			URL updateUrl = new URL(url + "/getFile?file_id=" + msg.getSticker().getId());
			BufferedReader in = new BufferedReader(new InputStreamReader(updateUrl.openStream()));
			// create the results string
			while ((temp = in.readLine()) != null) {
				response += temp;
			}

			in.close();
			files = new TelegramMedia[1];
			files[0] = new TelegramMedia(
					"https://api.telegram.org/file/bot" + token + "/"
							+ new JSONObject(response.toString()).getJSONObject("result").getString("file_path"),
					new JSONObject(response.toString()).getJSONObject("result").getInt("file_size"));
		}

		if (msg.getDocument() != null) {
			URL updateUrl = new URL(url + "/getFile?file_id=" + msg.getDocument().getId());
			BufferedReader in = new BufferedReader(new InputStreamReader(updateUrl.openStream()));
			// create the results string
			while ((temp = in.readLine()) != null) {
				response += temp;
			}

			in.close();
			files = new TelegramMedia[1];
			files[0] = new TelegramMedia(
					"https://api.telegram.org/file/bot" + token + "/"
							+ new JSONObject(response.toString()).getJSONObject("result").getString("file_path"),
					new JSONObject(response.toString()).getJSONObject("result").getInt("file_size"));
		}

		if (msg.getPhoto() != null) {
			files = new TelegramMedia[msg.getPhoto().length];
			for (int i = 0; i < msg.getPhoto().length; i++) {
				URL updateUrl = new URL(url + "/getFile?file_id=" + msg.getPhoto()[i].getId());
				System.out.println(updateUrl.toString());
				BufferedReader in = new BufferedReader(new InputStreamReader(updateUrl.openStream()));
				// create the results string
				while ((temp = in.readLine()) != null) {
					response += temp;
				}

				in.close();

				files[i] = new TelegramMedia(
						"https://api.telegram.org/file/bot" + token + "/"
								+ new JSONObject(response.toString()).getJSONObject("result").getString("file_path"),
						new JSONObject(response.toString()).getJSONObject("result").getInt("file_size"));
				temp = null;
				response = "";
			}
		}

		return files;
	}

	/**
	 * Sends a Location
	 * 
	 * @param chat_id
	 *            Recipient's id or channel's id
	 * @param location
	 *            the location to send
	 * @param disable_notification
	 *            true: silent notification
	 * @param reply_to_message_id
	 * @param reply_markup
	 *            OPTIONAL, set it to null if you don't want to use the keyboard
	 *            markup.
	 * @return A String containing the response.
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String sendLocation(String chat_id, Location location, boolean disable_notification, int reply_to_message_id,
			ReplyMarkup reply_markup) throws ClientProtocolException, IOException {

		URL updateUrl = new URL(
				url + "/sendLocation?chat_id=" + chat_id + "&latitude=" + location.getLatitude() + "&longitude="
						+ location.getLongitude() + "&disable_notification=" + Boolean.toString(disable_notification)
						+ "&reply_to_message_id=" + reply_to_message_id + "&reply_markup=" + (reply_markup != null
								? "&reply_markup=" + URLEncoder.encode(reply_markup.toJSONString(), "utf-8") : ""));
		String temp, response = "";
		System.out.println(updateUrl.toString());
		BufferedReader in = new BufferedReader(new InputStreamReader(updateUrl.openStream()));
		// create the results string
		while ((temp = in.readLine()) != null) {
			response += temp;
		}

		in.close();

		return response;

	}

	/**
	 * Get the last current update id number.
	 * 
	 * @return the higher update id from the getUpdates page, 0 if there are no
	 *         updates
	 * @throws GettingUpdatesException
	 * @throws IOException
	 */
	public int getCurrentUpdateId() throws GettingUpdatesException, IOException {
		Update[] u;
		int id;
		try {
			u = getUpdates(-1, -1, -1);
			id = u[u.length - 1].getId();
		} catch (EmptyUpdatesException e) {
			id = 0;
		}
		return id;
	}

	/**
	 * Get the smallest current update id number.
	 * 
	 * @return the smallest update id from the getUpdates page, 0 if there are
	 *         no updates.
	 * @throws GettingUpdatesException
	 * @throws IOException
	 */
	public int getSmallestCurrentUpdateId() throws GettingUpdatesException, IOException {
		Update[] u;
		int id;
		try {
			u = getUpdates(-1, -1, -1);
			id = u[0].getId();
		} catch (EmptyUpdatesException e) {
			id = 0;
		}
		return id;
	}

	/**
	 * Forwards a message from a chat to another chat.
	 * 
	 * @param chat_id
	 *            recipient's chat id.
	 * @param from_chat_id
	 *            Original message's chat id.
	 * @param disable_notification
	 * @param message_id
	 *            id of the message to forward.
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String forwardMessage(String chat_id, String from_chat_id, boolean disable_notification, int message_id)
			throws ClientProtocolException, IOException {
		URL updateUrl = new URL(url + "/forwardMessage?chat_id=" + chat_id + "&from_chat_id=" + from_chat_id
				+ "&disable_notification=" + Boolean.toString(disable_notification) + "&message_id=" + message_id);
		String temp, response = "";
		System.out.println(updateUrl.toString());
		BufferedReader in = new BufferedReader(new InputStreamReader(updateUrl.openStream()));
		// create the results string
		while ((temp = in.readLine()) != null) {
			response += temp;
		}

		in.close();
		return response;
	}

	/**
	 * Use this method when you need to tell the user that something is
	 * happening on the bot's side. The status is set for 5 seconds or less
	 * (when a message arrives from your bot, Telegram clients clear its typing
	 * status).
	 * 
	 * @param chat_id
	 *            recipient's id or channel's id
	 * @param chat_action
	 *            Allowed actions:
	 *            <ul>
	 *            <li>typing</li>
	 *            <li>upload_photo</li>
	 *            <li>record_video</li>
	 *            <li>upload_video</li>
	 *            <li>record_audio</li>
	 *            <li>upload_audio</li>
	 *            <li>upload_document</li>
	 *            <li>find_location</li>
	 *            </ul>
	 * @return A String containing the response.
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String sendChatAction(String chat_id, String chat_action) throws ClientProtocolException, IOException {
		URL updateUrl = new URL(url + "/sendChatAction?chat_id=" + chat_id + "&action=" + chat_action);
		String temp, response = "";
		System.out.println(updateUrl.toString());
		BufferedReader in = new BufferedReader(new InputStreamReader(updateUrl.openStream()));
		// create the results string
		while ((temp = in.readLine()) != null) {
			response += temp;
		}

		in.close();
		return response;
	}

	/**
	 * Use this method to get a list of profile pictures for a user. Returns a
	 * {@link UserProfilePhotos} object.
	 * 
	 * @param user_id
	 * @param offset
	 * @param limit
	 * @return UserProfilePhotos object containing user's profile photos.
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public UserProfilePhotos getUserProfilePhotos(int user_id, int offset, int limit)
			throws ClientProtocolException, IOException {
		URL updateUrl = new URL(url + "/getUserProfilePhotos?user_id=" + user_id
				+ (offset > 0 ? "&offset=" + offset : "") + (limit < 100 ? "&limit=" + limit : ""));
		BufferedReader in = new BufferedReader(new InputStreamReader(updateUrl.openStream()));
		String temp, inputLine = "";
		JSONObject obj;
		PhotoSize[] photos;
		int total_count;
		// create the results string
		while ((temp = in.readLine()) != null) {
			inputLine += temp;
		}

		in.close();
		obj = new JSONObject(inputLine);
		total_count = obj.getInt("total_count");
		photos = new PhotoSize[total_count];
		for (int i = 0; i < total_count; i++) {
			photos[i] = new PhotoSize(obj.getJSONArray("photos").getJSONObject(i));
		}

		return new UserProfilePhotos(total_count, photos);
	}

	/**
	 * Answers an inline query.
	 * 
	 * @param inline_query_id
	 * @param results
	 *            An array of {@link InlineQueryResult}.
	 * @return
	 *         <ul>
	 *         <li>true if operation is successful</li>
	 *         <li>false if operation is unsuccessful</li>
	 *         </ul>
	 * @throws IOException
	 */
	public boolean answerInlineQuery(String inline_query_id, InlineQueryResult[] results) throws IOException {

		// creating the JSON-Serialized array
		JSONWriter writer = new JSONStringer();

		writer.array();
		for (int i = 0; i < results.length; i++) {

			writer.value(results[i].toJSONObject());

		}
		writer.endArray();

		URL updateUrl = new URL(url + "/answerInlineQuery?inline_query_id=" + inline_query_id + "&results="
				+ URLEncoder.encode(writer.toString(), "utf-8"));
		String temp, response = "";
		System.out.println(updateUrl.toString());
		BufferedReader in = new BufferedReader(new InputStreamReader(updateUrl.openStream()));
		// create the results string
		while ((temp = in.readLine()) != null) {
			response += temp;
		}

		in.close();

		// if the "ok" parameter in JSON results is set to false, it throws an
		// exception
		return new JSONObject(response).getBoolean("ok") == true ? true : false;
	}

	/**
	 * Use this method to kick a user from a group or a supergroup. In the case
	 * of supergroups, the user will not be able to return to the group on their
	 * own using invite links, etc., unless unbanned first. The bot must be an
	 * administrator in the group for this to work. Returns True on success.
	 * 
	 * @param chat_id
	 * @param user_id
	 * @return
	 *         <ul>
	 *         <li>True: successful operation</li>
	 *         <li>False: unsuccessful operation</li>
	 *         </ul>
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public boolean kickChatMember(String chat_id, String user_id) throws ClientProtocolException, IOException {
		URL updateUrl = new URL(url + "/kickChatMember?chat_id=" + chat_id + "&user_id=" + user_id);
		String temp, response = "";
		System.out.println(updateUrl.toString());
		BufferedReader in = new BufferedReader(new InputStreamReader(updateUrl.openStream()));
		// create the results string
		while ((temp = in.readLine()) != null) {
			response += temp;
		}

		in.close();
		return new JSONObject(response).getBoolean("ok");
	}

	/**
	 * Use this method to unban a previously kicked user in a supergroup. The
	 * user will not return to the group automatically, but will be able to join
	 * via link, etc. The bot must be an administrator in the group for this to
	 * work. Returns True on success
	 * 
	 * @param chat_id
	 * @param user_id
	 * @return
	 *         <ul>
	 *         <li>True: successful operation</li>
	 *         <li>False: unsuccessful operation</li>
	 *         </ul>
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public boolean unbanChatMember(String chat_id, String user_id) throws ClientProtocolException, IOException {
		URL updateUrl = new URL(url + "/unbanChatMember?chat_id=" + chat_id + "&user_id=" + user_id);
		String temp, response = "";
		System.out.println(updateUrl.toString());
		BufferedReader in = new BufferedReader(new InputStreamReader(updateUrl.openStream()));
		// create the results string
		while ((temp = in.readLine()) != null) {
			response += temp;
		}
		in.close();
		return new JSONObject(response).getBoolean("ok");
	}

	/**
	 * Use this method to send answers to callback queries sent from inline
	 * keyboards. The answer will be displayed to the user as a notification at
	 * the top of the chat screen or as an alert. On success, True is returned.
	 * 
	 * @param callback_query_id
	 * @param text
	 * @param show_alert
	 *            If true, an alert will be shown by the client instead of a
	 *            notification at the top of the chat screen. Defaults to false.
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public boolean answerCallbackQuery(String callback_query_id, String text, boolean show_alert)
			throws ClientProtocolException, IOException {
		URL updateUrl = new URL(url + "/answerCallbackQuery?callback_query_id=" + callback_query_id + "&text=" + text
				+ "&show_alert=" + show_alert);
		String temp, response = "";
		System.out.println(updateUrl.toString());
		BufferedReader in = new BufferedReader(new InputStreamReader(updateUrl.openStream()));
		// create the results string
		while ((temp = in.readLine()) != null) {
			response += temp;
		}
		in.close();
		return new JSONObject(response).getBoolean("ok");
	}

	/**
	 * Use this method to edit text messages sent by the bot or via the bot (for
	 * inline bots). On success, if edited message is sent by the bot, the
	 * edited {@link Message} is returned, otherwise True is returned.
	 * 
	 * @param chat_id
	 * @param message_id
	 * @param inline_message_id
	 * @param text
	 * @param parse_mode
	 *            Html or Markdown
	 * @param disable_web_page_preview
	 * @param reply_markup
	 *            OPTIONAL, set it to null if you don't want to use it
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public JSONObject editMessageText(String chat_id, int message_id, String inline_message_id, String text,
			String parse_mode, boolean disable_web_page_preview, ReplyMarkup reply_markup)
			throws ClientProtocolException, IOException {
		URL updateUrl = new URL(
				url + "/editMessageText?chat_id=" + chat_id + "&message_id=" + message_id + "&inline_message_id="
						+ inline_message_id + "&text=" + text + (parse_mode != null ? "&parse_mode=" + parse_mode : "")
						+ "&disable_web_page_preview=" + disable_web_page_preview
						+ (reply_markup != null ? "&reply_markup=" + reply_markup.toJSONString() : ""));
		String temp, response = "";
		System.out.println(updateUrl.toString());
		BufferedReader in = new BufferedReader(new InputStreamReader(updateUrl.openStream()));
		// create the results string
		while ((temp = in.readLine()) != null) {
			response += temp;
		}
		in.close();
		return new JSONObject(response);
	}

	/**
	 * Use this method to edit captions of messages sent by the bot or via the
	 * bot (for inline bots). On success, if edited message is sent by the bot,
	 * the edited {@link Message} is returned, otherwise True is returned.
	 * 
	 * @param chat_id
	 * @param message_id
	 * @param inline_message_id
	 * @param caption
	 * @param reply_markup
	 *            OPTIONAL, set it to null if you don't want to use it
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public JSONObject editMessageCaption(String chat_id, int message_id, String inline_message_id, String caption,
			ReplyMarkup reply_markup) throws ClientProtocolException, IOException {
		URL updateUrl = new URL(url + "/editMessageCaption?chat_id=" + chat_id + "&message_id=" + message_id
				+ "&inline_message_id=" + inline_message_id + "&caption=" + caption
				+ (reply_markup != null ? "&reply_markup=" + reply_markup.toJSONString() : ""));
		String temp, response = "";
		System.out.println(updateUrl.toString());
		BufferedReader in = new BufferedReader(new InputStreamReader(updateUrl.openStream()));
		// create the results string
		while ((temp = in.readLine()) != null) {
			response += temp;
		}
		in.close();
		return new JSONObject(response);

	}

	/**
	 * Use this method to edit only the reply markup of messages sent by the bot
	 * or via the bot (for inline bots). On success, if edited message is sent
	 * by the bot, the edited {@link Message} is returned, otherwise True is
	 * returned.
	 * 
	 * @param chat_id
	 * @param message_id
	 * @param inline_message_id
	 * @param reply_markup
	 *            OPTIONAL, set it to null if you don't want to use it
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public JSONObject editMessageReplyMarkup(String chat_id, int message_id, String inline_message_id,

			ReplyMarkup reply_markup) throws ClientProtocolException, IOException {
		URL updateUrl = new URL(url + "/editMessageReplyMarkup?chat_id=" + chat_id + "&message_id=" + message_id
				+ "&inline_message_id=" + inline_message_id
				+ (reply_markup != null ? "&reply_markup=" + reply_markup.toJSONString() : ""));
		String temp, response = "";
		System.out.println(updateUrl.toString());
		BufferedReader in = new BufferedReader(new InputStreamReader(updateUrl.openStream()));
		// create the results string
		while ((temp = in.readLine()) != null) {
			response += temp;
		}
		in.close();
		return new JSONObject(response);
	}

	/**
	 * Use this method to send information about a venue. On success, the sent
	 * Message is returned.
	 * 
	 * @param chat_id
	 * @param venue
	 * @param disable_notification
	 * @param reply_to_message_id
	 * @param reply_markup
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public Message sendVenue(String chat_id, Venue venue, boolean disable_notification, int reply_to_message_id,
			ReplyMarkup reply_markup) throws ClientProtocolException, IOException {
		URL updateUrl = new URL(url + "/sendVenue?chat_id=" + chat_id + "&latitude=" + venue.getLocation().getLatitude()
				+ "&longitude=" + venue.getLocation().getLongitude() + "&title=" + venue.getTitle() + "&address="
				+ venue.getAddress()
				+ (venue.getFoursquare_id() != null ? "&foursquare_id=" + venue.getFoursquare_id() : "")
				+ (disable_notification == true ? "&disable_notification=true" : "")
				+ (reply_to_message_id > 0 ? "&reply_to_message_id=" + reply_to_message_id : "")
				+ (reply_markup != null ? "&reply_markup=" + reply_markup : ""));
		String temp, response = "";
		System.out.println(updateUrl.toString());
		BufferedReader in = new BufferedReader(new InputStreamReader(updateUrl.openStream()));
		// create the results string
		while ((temp = in.readLine()) != null) {
			response += temp;
		}
		in.close();
		return new Message(new JSONObject(response));
	}

}
