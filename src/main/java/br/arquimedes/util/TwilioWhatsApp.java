package br.arquimedes.util;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class TwilioWhatsApp {
	private static final String ACCOUNT_SID = "AC6e86eaef74ab270eff5f090a9c3c6031";
	private static final String AUTH_TOKEN = "03b87b91ec9ca647970c0b4da86ca197";
	private static final String DEFAULT_NUMBER = "+14155238886";
	
	public static Message enviar(String de, String para, String mensagem) throws Exception {
		if (de==null) {
			de = DEFAULT_NUMBER;
		}
		
		if (mensagem==null) {
			throw new Exception("TwilioConfig: Campo de mensagem vazio");
		}
		
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Message msg = Message.creator(
				new PhoneNumber("whatsapp:" + para),
				new PhoneNumber("whatsapp:" + de),
				mensagem
		).create();
		
		return msg;
	}
}
