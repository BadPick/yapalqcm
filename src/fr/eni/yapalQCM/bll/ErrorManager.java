package fr.eni.yapalQCM.bll;

import fr.eni.yapalQCM.utils.Message;
import fr.eni.yapalQCM.utils.MessageType;

public class ErrorManager {
	
	public static Message getMessage(Exception e){
		Message message = new Message();
		message.setMessage(e.getMessage());
		message.setType(MessageType.error);
		return message;
	}
	
	public static Message getMessage(String message, MessageType type){
		Message msg = new Message();
		msg.setMessage(message);
		msg.setType(type);
		return msg;
	}
}
