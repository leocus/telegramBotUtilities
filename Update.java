package org.altervista.leocus.telegrambotutilities;

/**
 * Represents an update
 * 
 * @author leonardo
 *
 */
public class Update {
	private int id;
	private Message message;
	private InlineQuery query;
	private Chosen_inline_result inl_res;
	private CallbackQuery callbackQuery;

	public Update(int id, Message message, InlineQuery query, Chosen_inline_result inl_res,
			CallbackQuery callbackQuery) {
		super();
		this.id = id;
		this.message = message;
		this.query = query;
		this.inl_res = inl_res;
		this.callbackQuery = callbackQuery;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Chosen_inline_result getInl_res() {
		return inl_res;
	}

	public void setInl_res(Chosen_inline_result inl_res) {
		this.inl_res = inl_res;
	}

	public InlineQuery getQuery() {
		return query;
	}

	public void setQuery(InlineQuery query) {
		this.query = query;
	}

	public CallbackQuery getCallbackQuery() {
		return callbackQuery;
	}

	public void setCallbackQuery(CallbackQuery callbackQuery) {
		this.callbackQuery = callbackQuery;
	}

}
