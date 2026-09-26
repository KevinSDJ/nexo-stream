package com.nexo.app.Server;

public interface IBroadcast<A extends String,T> {
  
  public default void sendTo(String id,String msg){

  }  

  default void sendAll(String msg){
    
  }
} 
