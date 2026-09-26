package com.nexo.app.Server.Connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Connection {
    String _id;
    Socket socket;
    
    public Connection(String id, Socket socket) {
        this._id = id;
        this.socket = socket;
    }

    public String getId() {
        return _id;
    }
    public InputStream getInputStream() throws IOException{
        return isActive() ? socket.getInputStream(): null;
    }
    public boolean isActive(){
        return !socket.isClosed();
    }
    public OutputStream getOutputStream(){
        return isActive() ? getOutputStream(): null;
    }
    public void close() {
        try {
            socket.close();
        } catch (Exception e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }

}
