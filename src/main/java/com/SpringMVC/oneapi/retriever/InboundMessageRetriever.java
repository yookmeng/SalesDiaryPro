package com.SpringMVC.oneapi.retriever;

import java.util.List; 
import java.util.concurrent.Executors; 
import java.util.concurrent.ScheduledExecutorService; 
import java.util.concurrent.TimeUnit; 
 
import com.SpringMVC.oneapi.client.impl.SMSMessagingClientImpl; 
import com.SpringMVC.oneapi.exception.RequestException; 
import com.SpringMVC.oneapi.listener.InboundMessageListener; 
import com.SpringMVC.oneapi.model.common.InboundSMSMessageList; 
 
 
 public class InboundMessageRetriever { 
 private ScheduledExecutorService fScheduler; 
 
 
 public void start(long interval, SMSMessagingClientImpl smsMessagingImpl) { 
  this.stop(); 
 
  if (interval <= 0) 
   return; 
 
  fScheduler = Executors.newScheduledThreadPool(1); 
 
  // fire first inbound sms pull attempt after 2 sec and then each interval milliseconds 
  Runnable poller = new PollerTask(smsMessagingImpl); 
  fScheduler.scheduleWithFixedDelay(poller, 2000, interval, TimeUnit.MILLISECONDS); 
 } 
 
 public void stop() { 
  if (fScheduler != null) { 
   fScheduler.shutdown(); 
  } 
 } 
 
 private static final class PollerTask implements Runnable { 
  private SMSMessagingClientImpl smsMessagingImpl; 
  
  public PollerTask(SMSMessagingClientImpl smsMessagingImpl) { 
   this.smsMessagingImpl = smsMessagingImpl;   
  } 
  public void run() {  
   List<InboundMessageListener> inboundMsglisteners = this.smsMessagingImpl.getInboundMessagePullListeners(); 
 
   if ((inboundMsglisteners != null) && (inboundMsglisteners.size() > 0)) { 
    // use pull method to gather incoming messages 
    InboundSMSMessageList smsMessageList = new InboundSMSMessageList(); 
    Throwable error = null; 
    try { 
     smsMessageList = smsMessagingImpl.getInboundMessages(); 
    } catch (RequestException e) { 
     error = e.getCause(); 
    }             
     
    if ((smsMessageList != null && smsMessageList.getInboundSMSMessage() != null && smsMessageList.getInboundSMSMessage().length > 0) || (error != null)) { 
     this.fireMessageRetrieved(smsMessageList, inboundMsglisteners, error);  
    }   
   } 
  } 
  
  private void fireMessageRetrieved(InboundSMSMessageList smsMessageList, List<InboundMessageListener> listeners, Throwable error) { 
   for (InboundMessageListener listener : listeners) { 
    listener.onMessageRetrieved(smsMessageList, error); 
   } 
  } 
 } 
}