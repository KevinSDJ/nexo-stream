package com.nexo.app.Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.nexo.app.Server.Connection.Connection;

public class Server {
    private Map<String,Connection> connections= new ConcurrentHashMap<>();
    private ExecutorService pool = Executors.newFixedThreadPool(10);
    private int port = 4000;
    public Server( int port){
        this.port=port;
    }
    public Server(){}
    
    public void run(){
         try (ServerSocket ssocket= new ServerSocket(port)) {
            log("Server run on port: " + port);
            log("Waiting for connections"); 
            while (true) {
                Socket cliente = ssocket.accept();
                log("connection:: "+ cliente.getInetAddress());
                // creo un id unico
                String shortHash = UUID.randomUUID().toString().replace("-", "");
                //guardo el cliente conectado
                connections.put(shortHash, new Connection(shortHash,cliente));
                //ejecuto el hilo del cliente
                pool.execute(new HandleClient(connections.get(shortHash),this::removeClient));
            }
            
        } catch (Exception e) {
            log("Error: " + e.getMessage());
            pool.shutdown();
        }
    }
    public void log(String str){
        System.out.println(str);
    }

    public void removeClient(String id){
        connections.remove(id);
    }
}


