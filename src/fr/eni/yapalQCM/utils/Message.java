/**
 * 
 */
package fr.eni.yapalQCM.utils;

/**
 * @author mrault2015
 *
 */
public class Message {
	private String message;
	private String type;

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(MessageType type) {
		switch (type) {
		case error:
			this.type = "error";
			break;

		case information:
			this.type = "information";
			break;

		case success:
			this.type = "success";
			break;

		case warning:
			this.type = "warning";
			break;

		}		
	}
}
