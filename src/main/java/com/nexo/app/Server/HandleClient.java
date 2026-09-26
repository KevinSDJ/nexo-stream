package com.nexo.app.Server;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.util.function.Consumer;
import com.nexo.app.Server.Connection.Connection;

public class HandleClient implements Runnable{
    private Connection connection;
    private Consumer<String> onCloseSckt;

    public HandleClient(Connection connection,Consumer<String> onCl){
        this.connection= connection;
        onCloseSckt= onCl;
    }
    @Override
    public void run() {
        DataInputStream in;
        try {
            in= new DataInputStream( new BufferedInputStream(connection.getInputStream()));
            String msg="";
            while(!msg.equals("Code: 1")){
                msg= in.readUTF();
                
                System.out.println("Server: Msg: " + msg);
            }
            connection.close();
            in.close();
            onCloseSckt.accept(connection.getId());
            System.out.println("Client: " + connection.getId() + " removed");
        } catch (Exception e) {
            
            System.out.println("Error manejando cliente: " + e.getMessage());
        }
    }
}

