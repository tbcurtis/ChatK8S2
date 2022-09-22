package demo;

import java.io.IOException;

import io.kubemq.sdk.basic.ServerAddressNotSuppliedException;
import io.kubemq.sdk.event.Channel;
import io.kubemq.sdk.event.Event;
import io.kubemq.sdk.event.Result;
import io.kubemq.sdk.tools.Converter;

public class EventStore {
	private static String channelName = "testing_event_channel";
	private static String clientID = "hello-world-sender";
    private static String kubeMQServerAddress = "localhost:50000";
			
	public void send(String message, Channel channel) throws IOException, ServerAddressNotSuppliedException {
		Event event = new Event();
		
		event.setBody(Converter.ToByteArray(message));
		event.setEventId("event-Store-1");
		
		Result rs = channel.SendEvent(event);
		System.out.println("Message sent " + rs.isSent());
	}
	

	public static void main(String[] args) {
		EventStore es = new EventStore();
		
		Channel channel = new Channel(channelName, clientID, false,	kubeMQServerAddress);
		
		try {
			es.send("test", channel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServerAddressNotSuppliedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
