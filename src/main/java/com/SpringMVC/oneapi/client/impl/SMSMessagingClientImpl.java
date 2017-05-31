package com.SpringMVC.oneapi.client.impl;

import com.SpringMVC.oneapi.client.SMSMessagingClient; 
import com.SpringMVC.oneapi.config.Configuration; 
import com.SpringMVC.oneapi.listener.*; 
import com.SpringMVC.oneapi.model.*; 
import com.SpringMVC.oneapi.model.RequestData.Method; 
import com.SpringMVC.oneapi.model.common.*; 
import com.SpringMVC.oneapi.pushserver.PushServerSimulator; 
import com.SpringMVC.oneapi.retriever.DeliveryReportRetriever; 
import com.SpringMVC.oneapi.retriever.InboundMessageRetriever; 
 
import java.util.ArrayList; 
import java.util.List; 
 
 
public class SMSMessagingClientImpl extends OneAPIBaseClientImpl implements SMSMessagingClient { 
    private static final String SMS_MESSAGING_OUTBOUND_URL_BASE = "/smsmessaging/outbound"; 
    private static final String SMS_MESSAGING_INBOUND_URL_BASE = "/smsmessaging/inbound"; 
 
    private DeliveryReportRetriever deliveryReportRetriever = null; 
    private InboundMessageRetriever inboundMessageRetriever = null; 
    private volatile List<DeliveryReportListener> deliveryReportPullListenerList = null; 
    private volatile List<InboundMessageListener> inboundMessagePullListenerList = null; 
 
    private volatile List<DeliveryStatusNotificationsListener> deliveryStatusNotificationPushListenerList = null; 
    private volatile List<InboundMessageNotificationsListener> inboundMessagePushListenerList = null; 
    private PushServerSimulator dlrStatusPushServerSimulator; 
    private PushServerSimulator inboundMessagesPushServerSimulator; 
 
    //*************************SMSMessagingClientImpl Initialization****************************************************************************************************************************************************** 
    public SMSMessagingClientImpl(Configuration configuration) { 
        super(configuration); 
    } 
 
    //*************************SMSMessagingClientImpl public****************************************************************************************************************************************************** 
 
    /**
     * Send an SMS over OneAPI to one or more mobile terminals using the customized 'SMSRequest' object 
     * 
     * @param smsRequest (mandatory) object containing data needed to be filled in order to send the SMS 
     * @return String Request Id 
     */ 
    @Override 
    public SendMessageResult sendSMS(SMSRequest smsRequest) { 
        RequestData requestData = new RequestData(SMS_MESSAGING_OUTBOUND_URL_BASE + "/" + encodeURLParam(smsRequest.getSenderAddress()) + "/requests", Method.POST, null, smsRequest, JSON_CONTENT_TYPE); 
        return executeMethod(requestData, SendMessageResult.class); 
    } 
 
    /**
     * Send an SMS asynchronously over OneAPI to one or more mobile terminals using the customized 'SMSRequest' object 
     * 
     * @param smsRequest       (mandatory) object containing data needed to be filled in order to send the SMS 
     * @param responseListener (mandatory) method to call after receiving sent SMS response 
     */ 
 
    public void sendSMSAsync(SMSRequest smsRequest, final ResponseListener<SendMessageResult> responseListener) { 
        RequestData requestData = new RequestData(SMS_MESSAGING_OUTBOUND_URL_BASE + "/" + encodeURLParam(smsRequest.getSenderAddress()) + "/requests", Method.POST, null, smsRequest, JSON_CONTENT_TYPE); 
        executeMethodAsync(requestData, SendMessageResult.class, responseListener); 
    } 
 
    /**
     * Query the delivery status over OneAPI for an SMS sent to one or more mobile terminals 
     * 
     * @param senderAddress (mandatory) is the address from which SMS messages are being sent. Do not URL encode this value prior to passing to this function 
     * @param requestId     (mandatory) contains the requestId returned from a previous call to the sendSMS function 
     * @return DeliveryInfoList 
     */ 
    @Override 
    public DeliveryInfoList queryDeliveryStatus(String senderAddress, String requestId) { 
        RequestData requestData = new RequestData(SMS_MESSAGING_OUTBOUND_URL_BASE + "/" + encodeURLParam(senderAddress) + "/requests/" + encodeURLParam(requestId) + "/deliveryInfos", Method.GET, "deliveryInfoList"); 
        return executeMethod(requestData, DeliveryInfoList.class); 
    } 
 
    /**
     * Query the delivery status asynchronously over OneAPI for an SMS sent to one or more mobile terminals 
     * 
     * @param senderAddress    (mandatory) is the address from which SMS messages are being sent. Do not URL encode this value prior to passing to this function 
     * @param requestId        (mandatory) contains the requestId returned from a previous call to the sendSMS function 
     * @param responseListener (mandatory) method to call after receiving delivery status 
     */ 
    public void queryDeliveryStatusAsync(String senderAddress, String requestId, final ResponseListener<DeliveryInfoList> responseListener) { 
        StringBuilder urlBuilder = (new StringBuilder(SMS_MESSAGING_OUTBOUND_URL_BASE)).append("/"); 
        urlBuilder.append(encodeURLParam(senderAddress)); 
        urlBuilder.append("/requests/"); 
        urlBuilder.append(encodeURLParam(requestId)); 
        urlBuilder.append("/deliveryInfos"); 
 
        RequestData requestData = new RequestData(urlBuilder.toString(), Method.GET, "deliveryInfoList"); 
        executeMethodAsync(requestData, DeliveryInfoList.class, responseListener); 
    } 
 
    /**
     * Convert JSON to Delivery Info Notification 
     * 
     * @return DeliveryInfoNotification 
     */ 
    public DeliveryInfoNotification convertJsonToDeliveryInfoNotification(String json) { 
        return convertJSONToObject(json.getBytes(), DeliveryInfoNotification.class, "deliveryInfoNotification"); 
    } 
 
    /**
     * Start subscribing to delivery status notifications over OneAPI for all your sent SMS 
     * 
     * @param subscribeToDeliveryNotificationsRequest (mandatory) contains delivery notifications subscription data 
     * @return String Subscription Id 
     */ 
    @Override 
    public String subscribeToDeliveryStatusNotifications(SubscribeToDeliveryNotificationsRequest subscribeToDeliveryNotificationsRequest) { 
        StringBuilder urlBuilder = new StringBuilder(SMS_MESSAGING_OUTBOUND_URL_BASE).append("/"); 
 
        if (null != subscribeToDeliveryNotificationsRequest.getSenderAddress()) { 
            urlBuilder.append(encodeURLParam(subscribeToDeliveryNotificationsRequest.getSenderAddress())).append("/"); 
        } 
        urlBuilder.append("subscriptions"); 
 
        RequestData requestData = new RequestData(urlBuilder.toString(), Method.POST, "deliveryReceiptSubscription", subscribeToDeliveryNotificationsRequest, JSON_CONTENT_TYPE); 
        DeliveryReceiptSubscription deliveryReceiptSubscription = executeMethod(requestData, DeliveryReceiptSubscription.class); 
        return getIdFromResourceUrl(deliveryReceiptSubscription.getResourceURL()); 
    } 
 
    /**
     * Get delivery notifications subscriptions by sender address 
     * 
     * @return DeliveryReportSubscription[] 
     */ 
    @Override 
    public DeliveryReportSubscription[] getDeliveryNotificationsSubscriptionsBySender(String senderAddress) { 
        StringBuilder urlBuilder = new StringBuilder(SMS_MESSAGING_OUTBOUND_URL_BASE).append("/"); 
        urlBuilder.append(encodeURLParam(senderAddress)); 
        urlBuilder.append("/subscriptions"); 
 
        RequestData requestData = new RequestData(urlBuilder.toString(), Method.GET, "deliveryReceiptSubscriptions"); 
        return executeMethod(requestData, DeliveryReportSubscription[].class); 
    } 
 
    /**
     * Get delivery notifications subscriptions by subscription id 
     * 
     * @return DeliveryReportSubscription 
     */ 
    @Override 
    public DeliveryReportSubscription getDeliveryNotificationsSubscriptionById(String subscriptionId) { 
        StringBuilder urlBuilder = new StringBuilder(SMS_MESSAGING_OUTBOUND_URL_BASE).append("/subscriptions/"); 
        urlBuilder.append(encodeURLParam(subscriptionId)); 
 
        RequestData requestData = new RequestData(urlBuilder.toString(), Method.GET, "deliveryReceiptSubscription"); 
        return executeMethod(requestData, DeliveryReportSubscription.class); 
    } 
 
    /**
     * Get delivery notifications subscriptions by for the current user 
     * 
     * @return DeliveryReportSubscription[] 
     */ 
    @Override 
    public DeliveryReportSubscription[] getDeliveryNotificationsSubscriptions() { 
        RequestData requestData = new RequestData(SMS_MESSAGING_OUTBOUND_URL_BASE + "/subscriptions", Method.GET, "deliveryReceiptSubscriptions"); 
        return executeMethod(requestData, DeliveryReportSubscription[].class); 
    } 
 
    /**
     * Stop subscribing to delivery status notifications over OneAPI for all your sent SMS 
     * 
     * @param subscriptionId (mandatory) contains the subscriptionId of a previously created SMS delivery receipt subscription 
     */ 
    @Override 
    public void removeDeliveryNotificationsSubscription(String subscriptionId) { 
        StringBuilder urlBuilder = new StringBuilder(SMS_MESSAGING_OUTBOUND_URL_BASE).append("/subscriptions/"); 
        urlBuilder.append(encodeURLParam(subscriptionId)); 
 
        RequestData requestData = new RequestData(urlBuilder.toString(), Method.DELETE); 
        executeMethod(requestData); 
    } 
 
    /**
     * Get SMS messages sent to your Web application over OneAPI using default 'maxBatchSize' = 100 
     * 
     * @return InboundSMSMessageList 
     */ 
    @Override 
    public InboundSMSMessageList getInboundMessages() { 
        return this.getInboundMessages(100); 
    } 
 
    /**
     * Get SMS messages sent to your Web application over OneAPI 
     * 
     * @param maxBatchSize (optional) is the maximum number of messages to get in this request 
     * @return InboundSMSMessageList 
     */ 
    @Override 
    public InboundSMSMessageList getInboundMessages(int maxBatchSize) { 
        //Registration ID is obsolete so any string can be put: e.g. INBOUND 
        RequestData requestData = new RequestData(SMS_MESSAGING_INBOUND_URL_BASE + "/registrations/INBOUND/messages" + "?maxBatchSize=" + encodeURLParam(String.valueOf(maxBatchSize)), Method.GET, "inboundSMSMessageList"); 
        return executeMethod(requestData, InboundSMSMessageList.class); 
    } 
 
    /**
     * Get asynchronously SMS messages sent to your Web application over OneAPI 
     * 
     * @param responseListener (mandatory) method to call after receiving inbound messages 
     */ 
    public void getInboundMessagesAsync(final ResponseListener<InboundSMSMessageList> responseListener) { 
        this.getInboundMessagesAsync(100, responseListener); 
    } 
 
    /**
     * Get asynchronously SMS messages sent to your Web application over OneAPI 
     * 
     * @param maxBatchSize     (optional) is the maximum number of messages to get in this request 
     * @param responseListener (mandatory) method to call after receiving inbound messages 
     */ 
    public void getInboundMessagesAsync(int maxBatchSize, final ResponseListener<InboundSMSMessageList> responseListener) { 
        //Registration ID is obsolete so any string can be put: e.g. INBOUND 
        RequestData requestData = new RequestData(SMS_MESSAGING_INBOUND_URL_BASE + "/registrations/INBOUND/messages" + "?maxBatchSize=" + encodeURLParam(String.valueOf(maxBatchSize)), Method.GET, "inboundSMSMessageList"); 
        executeMethodAsync(requestData, InboundSMSMessageList.class, responseListener); 
    } 
 
    /**
     * Convert JSON to Inbound SMS Message Notification 
     * 
     * @return InboundSMSMessageList 
     */ 
    public InboundSMSMessageList convertJsonToInboundSMSMessageNotificationExample(String json) { 
        return convertJSONToObject(json.getBytes(), InboundSMSMessageList.class); 
    } 
 
    /**
     * Start subscribing to notifications of SMS messages sent to your application over OneAPI 
     * 
     * @param subscribeToInboundMessagesRequest (mandatory) contains inbound messages subscription data 
     * @return String Subscription Id 
     */ 
    @Override 
    public String subscribeToInboundMessagesNotifications(SubscribeToInboundMessagesRequest subscribeToInboundMessagesRequest) { 
        RequestData requestData = new RequestData(SMS_MESSAGING_INBOUND_URL_BASE + "/subscriptions", Method.POST, "resourceReference", subscribeToInboundMessagesRequest, JSON_CONTENT_TYPE); 
        ResourceReference resourceReference = executeMethod(requestData, ResourceReference.class); 
        return getIdFromResourceUrl(resourceReference.getResourceURL()); 
    } 
 
    /**
     * Get inbound messages notifications subscriptions for the current user 
     * 
     * @return MoSubscription[] 
     */ 
    @Override 
    public MoSubscription[] getInboundMessagesNotificationsSubscriptions(int page, int pageSize) { 
        RequestData requestData = new RequestData(SMS_MESSAGING_INBOUND_URL_BASE + "/subscriptions" + "?page=" + encodeURLParam(String.valueOf(page)) + "&pageSize=" + encodeURLParam(String.valueOf(pageSize)), Method.GET, "subscriptions"); 
        return executeMethod(requestData, MoSubscription[].class); 
    } 
 
    /**
     * Get inbound messages notifications subscriptions for the current user 
     * 
     * @return MoSubscription[] 
     */ 
    @Override 
    public MoSubscription[] getInboundMessagesNotificationsSubscriptions() { 
        return getInboundMessagesNotificationsSubscriptions(1, 10); 
    } 
 
    /**
     * Stop subscribing to message receipt notifications for all your received SMS over OneAPI 
     * 
     * @param subscriptionId (mandatory) contains the subscriptionId of a previously created SMS message receipt subscription 
     */ 
    @Override 
    public void removeInboundMessagesSubscription(String subscriptionId) { 
        StringBuilder urlBuilder = new StringBuilder(SMS_MESSAGING_INBOUND_URL_BASE).append("/subscriptions/"); 
        urlBuilder.append(encodeURLParam(subscriptionId)); 
 
        RequestData requestData = new RequestData(urlBuilder.toString(), Method.DELETE); 
        executeMethod(requestData); 
    } 
 
    /**
     * Get MO Number Types 
     */ 
    @Override 
    public MoNumberType[] getMoNumberTypes() { 
        StringBuilder urlBuilder = new StringBuilder(SMS_MESSAGING_INBOUND_URL_BASE).append("/numberTypes"); 
 
        RequestData requestData = new RequestData(urlBuilder.toString(), Method.GET, "moNoTypes"); 
        return executeMethod(requestData, MoNumberType[].class); 
    } 
 
    /**
     * Get delivery reports 
     * 
     * @return DeliveryReport[] 
     */ 
    @Override 
    public DeliveryReportList getDeliveryReports(int limit) { 
        StringBuilder urlBuilder = new StringBuilder(SMS_MESSAGING_OUTBOUND_URL_BASE).append("/requests/deliveryReports"); 
        urlBuilder.append("?limit="); 
        urlBuilder.append(encodeURLParam(String.valueOf(limit))); 
 
        RequestData requestData = new RequestData(urlBuilder.toString(), Method.GET); 
        return executeMethod(requestData, DeliveryReportList.class); 
    } 
 
    /**
     * Get delivery reports asynchronously 
     * 
     * @param responseListener (mandatory) method to call after receiving delivery reports 
     */ 
    public void getDeliveryReportsAsync(int limit, final ResponseListener<DeliveryReportList> responseListener) { 
        StringBuilder urlBuilder = (new StringBuilder(SMS_MESSAGING_OUTBOUND_URL_BASE)).append("/requests/deliveryReports"); 
        urlBuilder.append("?limit="); 
        urlBuilder.append(encodeURLParam(String.valueOf(limit))); 
 
        RequestData requestData = new RequestData(urlBuilder.toString(), Method.GET); 
        executeMethodAsync(requestData, DeliveryReportList.class, responseListener); 
    } 
 
    /**
     * Get delivery reports 
     */ 
    @Override 
    public DeliveryReportList getDeliveryReports() { 
        return getDeliveryReports(0); 
    } 
 
    /**
     * Get delivery reports asynchronously 
     * 
     * @param responseListener (mandatory) method to call after receiving delivery reports 
     */ 
    public void getDeliveryReportsAsync(final ResponseListener<DeliveryReportList> responseListener) { 
        this.getDeliveryReportsAsync(0, responseListener); 
    } 
 
    /**
     * Get delivery reports by Request Id 
     * 
     * @return DeliveryReportList 
     */ 
    @Override 
    public DeliveryReportList getDeliveryReportsByRequestId(String requestId, int limit) { 
        StringBuilder urlBuilder = new StringBuilder(SMS_MESSAGING_OUTBOUND_URL_BASE).append("/requests/"); 
        urlBuilder.append(encodeURLParam(requestId)); 
        urlBuilder.append("/deliveryReports"); 
        urlBuilder.append("?limit="); 
        urlBuilder.append(encodeURLParam(String.valueOf(limit))); 
 
        RequestData requestData = new RequestData(urlBuilder.toString(), Method.GET); 
        return executeMethod(requestData, DeliveryReportList.class); 
    } 
 
    /**
     * Get delivery reports by Request Id 
     * 
     * @return DeliveryReportList 
     */ 
    @Override 
    public DeliveryReportList getDeliveryReportsByRequestId(String requestId) { 
        return getDeliveryReportsByRequestId(requestId, 0); 
    } 
 
    /**
     * Add OneAPI PULL 'Delivery Reports' listener 
     * 
     * @param listener - (new DeliveryReportListener) 
     */ 
    @Override 
    public void addPullDeliveryReportListener(DeliveryReportListener listener) { 
        if (listener == null) { 
            return; 
        } 
 
        if (deliveryReportPullListenerList == null) { 
            deliveryReportPullListenerList = new ArrayList<DeliveryReportListener>(); 
        } 
 
        this.deliveryReportPullListenerList.add(listener); 
        this.startDeliveryReportRetriever(); 
    } 
 
    /**
     * Add OneAPI PULL 'INBOUND Messages' listener 
     * Messages are pulled automatically depending on the 'inboundMessagesRetrievingInterval' client configuration parameter 
     * 
     * @param listener - (new InboundMessageListener) 
     */ 
    @Override 
    public void addPullInboundMessageListener(InboundMessageListener listener) { 
        if (listener == null) { 
            return; 
        } 
 
        if (inboundMessagePullListenerList == null) { 
            inboundMessagePullListenerList = new ArrayList<InboundMessageListener>(); 
        } 
 
        this.inboundMessagePullListenerList.add(listener); 
        this.startInboundMessageRetriever(); 
    } 
 
    /**
     * Returns INBOUND Message PULL Listeners list 
     */ 
    @Override 
    public List<InboundMessageListener> getInboundMessagePullListeners() { 
        return inboundMessagePullListenerList; 
    } 
 
    /**
     * Returns Delivery Reports PULL Listeners list 
     */ 
    @Override 
    public List<DeliveryReportListener> getDeliveryReportPullListeners() { 
        return deliveryReportPullListenerList; 
    } 
 
    /**
     * Remove PULL Delivery Reports listeners and stop retriever 
     */ 
    @Override 
    public void removePullDeliveryReportListeners() { 
        stopDeliveryReportRetriever(); 
        deliveryReportPullListenerList = null; 
        if (LOGGER.isInfoEnabled()) { 
            LOGGER.info("PULL Delivery Report Listeners are successfully released."); 
        } 
    } 
 
    /**
     * Remove PULL INBOUND Messages listeners and stop retriever 
     */ 
    @Override 
    public void removePullInboundMessageListeners() { 
        stopInboundMessagesRetriever(); 
        inboundMessagePullListenerList = null; 
        if (LOGGER.isInfoEnabled()) { 
            LOGGER.info("PULL Inbound Message Listeners are successfully released."); 
        } 
    } 
 
    /**
     * Add OneAPI PUSH 'Delivery Status' Notifications listener  and start push server simulator 
     */ 
    public void addPushDeliveryStatusNotificationListener(DeliveryStatusNotificationsListener listener) { 
        if (listener == null) { 
            return; 
        } 
 
        if (deliveryStatusNotificationPushListenerList == null) { 
            deliveryStatusNotificationPushListenerList = new ArrayList<DeliveryStatusNotificationsListener>(); 
        } 
 
        deliveryStatusNotificationPushListenerList.add(listener); 
 
        StartDlrStatusPushServerSimulator(); 
 
        if (LOGGER.isInfoEnabled()) { 
            LOGGER.info("Listener is successfully added, push server is started and is waiting for Delivery Status Notifications"); 
        } 
    } 
 
    /**
     * Add OneAPI PUSH 'INBOUND Messages' Notifications listener and start push server simulator 
     */ 
    public void addPushInboundMessageListener(InboundMessageNotificationsListener listener) { 
        if (listener == null) { 
            return; 
        } 
 
        if (inboundMessagePushListenerList == null) { 
            inboundMessagePushListenerList = new ArrayList<InboundMessageNotificationsListener>(); 
        } 
 
        inboundMessagePushListenerList.add(listener); 
 
        startInboundMessagesPushServerSimulator(); 
 
        if (LOGGER.isInfoEnabled()) { 
            LOGGER.info("Listener is successfully added, push server is started and is waiting for Inbound Messages Notifications"); 
        } 
    } 
 
    /**
     * Returns Delivery Status Notifications PUSH Listeners list 
     */ 
    public List<DeliveryStatusNotificationsListener> getDeliveryStatusNotificationPushListeners() { 
        return deliveryStatusNotificationPushListenerList; 
    } 
 
    /**
     * Returns INBOUND Message Notifications PUSH Listeners list 
     */ 
    public List<InboundMessageNotificationsListener> getInboundMessagePushListeners() { 
        return inboundMessagePushListenerList; 
    } 
 
    /**
     * Remove PUSH Delivery Reports Notifications listeners and stop server 
     */ 
    public void removePushDeliveryStatusNotificationListeners() { 
        stopDlrStatusPushServerSimulator(); 
        deliveryStatusNotificationPushListenerList = null; 
 
        if (LOGGER.isInfoEnabled()) { 
            LOGGER.info("Delivery Status Notification Listeners are successfully removed."); 
        } 
    } 
 
    /**
     * Remove PUSH Delivery Reports Notifications listeners and stop server 
     */ 
    public void removePushInboundMessageListeners() { 
        stopInboundMessagesPushServerSimulator(); 
        inboundMessagePushListenerList = null; 
 
        if (LOGGER.isInfoEnabled()) { 
            LOGGER.info("Inbound Message Listeners are successfully removed."); 
        } 
    } 
 
    //*************************SMSMessagingClientImpl private****************************************************************************************************************************************************** 
 
    /**
     * START DLR Retriever 
     */ 
    private void startDeliveryReportRetriever() { 
        if (this.deliveryReportRetriever != null) { 
            return; 
        } 
 
        this.deliveryReportRetriever = new DeliveryReportRetriever(); 
        int intervalMs = getConfiguration().getDlrRetrievingInterval(); 
        this.deliveryReportRetriever.start(intervalMs, this); 
    } 
 
    /**
     * STOP DLR Retriever and dispose listeners 
     */ 
    private void stopDeliveryReportRetriever() { 
        if (deliveryReportRetriever == null) { 
            return; 
        } 
 
        deliveryReportRetriever.stop(); 
        deliveryReportRetriever = null; 
    } 
 
    /**
     * START INBOUND Messages Retriever 
     */ 
    private void startInboundMessageRetriever() { 
        if (this.inboundMessageRetriever != null) { 
            return; 
        } 
 
        this.inboundMessageRetriever = new InboundMessageRetriever(); 
        int intervalMs = getConfiguration().getInboundMessagesRetrievingInterval(); 
        this.inboundMessageRetriever.start(intervalMs, this); 
    } 
 
    /**
     * STOP INBOUND Messages Retriever and dispose listeners 
     */ 
    private void stopInboundMessagesRetriever() { 
        if (inboundMessageRetriever == null) { 
            return; 
        } 
 
        inboundMessageRetriever.stop(); 
        inboundMessageRetriever = null; 
    } 
 
    private void StartDlrStatusPushServerSimulator() { 
        if (dlrStatusPushServerSimulator == null) { 
            dlrStatusPushServerSimulator = new PushServerSimulator(this, getConfiguration().getDlrStatusPushServerSimulatorPort()); 
            dlrStatusPushServerSimulator.start(); 
        } 
    } 
 
    private void stopDlrStatusPushServerSimulator() { 
        if (dlrStatusPushServerSimulator != null) { 
            dlrStatusPushServerSimulator.stop(); 
        } 
    } 
 
    private void startInboundMessagesPushServerSimulator() { 
        if (inboundMessagesPushServerSimulator == null) { 
            inboundMessagesPushServerSimulator = new PushServerSimulator(this, getConfiguration().getInboundMessagesPushServerSimulatorPort()); 
            inboundMessagesPushServerSimulator.start(); 
        } 
    } 
 
    private void stopInboundMessagesPushServerSimulator() { 
        if (inboundMessagesPushServerSimulator != null) { 
            inboundMessagesPushServerSimulator.stop(); 
        } 
    } 
}