package com.nexo.app.Server;


public interface IBroadcast {

  public default void sendTo(String id,String msg){

  }  

  default void sendAll(String msg){

  }
} 
