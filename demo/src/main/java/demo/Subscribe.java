package demo;

import java.io.IOException;

import javax.net.ssl.SSLException;

import io.grpc.stub.StreamObserver;
import io.kubemq.sdk.basic.ServerAddressNotSuppliedException;
import io.kubemq.sdk.event.EventReceive;
import io.kubemq.sdk.event.Subscriber;
import io.kubemq.sdk.subscription.SubscribeRequest;
import io.kubemq.sdk.subscription.SubscribeType;
import io.kubemq.sdk.tools.Converter;


public class Subscribe  {
	private static String channelName = "testing_event_channel";
	private static String clientID = "hello-world-sender";
    private static String kubeMQServerAddress = "localhost:50000";
    
    private final static Object lock = new Object();

	public static void main(String[] args)  {

        Subscriber subscriber = new Subscriber(kubeMQServerAddress);
        SubscribeRequest subscribeRequest = new SubscribeRequest();
        subscribeRequest.setChannel(channelName);
        subscribeRequest.setClientID(clientID);
        subscribeRequest.setSubscribeType(SubscribeType.Events);

        StreamObserver<EventReceive> streamObserver = new StreamObserver<EventReceive>() {


            public void onNext(EventReceive value) {
                try {
                    System.out.printf("Event Received: EventID: %s, Channel: %s, Metadata: %s, Body: %s",
                            value.getEventId(), value.getChannel(), value.getMetadata(),
                            Converter.FromByteArray(value.getBody()));
                } catch (ClassNotFoundException e) {
                    System.out.printf("ClassNotFoundException: %s", e.getMessage());
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.printf("IOException:  %s", e.getMessage());
                    e.printStackTrace();
                }

            }


            public void onError(Throwable t) {
                System.out.printf("Event Received Error: %s", t.getMessage());
                onCompleted();
            }


            public void onCompleted() {
                synchronized (lock) {
                    lock.notify();
                }
            }
        };

       
        try {
            Subscribe(subscriber, subscribeRequest, streamObserver);
        } catch (ServerAddressNotSuppliedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    private static void Subscribe(Subscriber subscriber,SubscribeRequest subscribeRequest, StreamObserver<EventReceive> streamObserver)
            throws InterruptedException, SSLException, ServerAddressNotSuppliedException {
        synchronized (lock) {
                   
                subscriber.SubscribeToEvents(subscribeRequest, streamObserver);
                lock.wait();
                Subscribe(subscriber, subscribeRequest, streamObserver);
           


        }
    }
}
