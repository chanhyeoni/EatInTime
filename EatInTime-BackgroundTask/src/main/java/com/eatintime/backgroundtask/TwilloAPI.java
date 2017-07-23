package com.eatintime.backgroundtask;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class TwilloAPI {
  // Find your Account Sid and Token at twilio.com/user/account
  public String ACCOUNT_SID;
  public String AUTH_TOKEN;

  public TwilloAPI(String account_sid, String auth_token){
    ACCOUNT_SID = account_sid;
    AUTH_TOKEN = auth_token;
  }

  public void sendMessage(String msg) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    Message message = Message.creator( 
      new PhoneNumber("+12019127154"), new PhoneNumber("+16307934139"), 
      msg).create();

    System.out.println(message.getSid());
  }
}