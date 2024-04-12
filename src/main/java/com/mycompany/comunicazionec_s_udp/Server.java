package com.mycompany.comunicazionec_s_udp;

import java.net.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class Server {
    
    int porta;
    boolean connection = true;
    byte[] bufferIN = new byte[1024];
    byte[] bufferOUT = new byte[1024];
    DatagramPacket receivePacket;
    DatagramSocket serverSocket;
    String data;
    
    public Server(int porta) {
    
        this.porta = porta; 
        receivePacket =  new DatagramPacket(bufferIN, bufferIN.length);
      try {
          
        serverSocket = new DatagramSocket(porta);  
        
        } catch (SocketException e) {
        System.out.println("errore nell avvio del server");
        e.printStackTrace();
        } 
    }
    
    public void esegui() {
    while(connection) {
    avvia();
    risposta();
    }
    chiudi();
    }
    
    public void avvia() {
        try {
    System.out.println("Server in fase di ricezione dati");
    serverSocket.receive(receivePacket);
    
    
    data = new String(receivePacket.getData());
    int nCaratteri = receivePacket.getLength();
    data = data.substring(0, nCaratteri);
    System.out.println("Dati Ricevuti :" + data);
    
    if(data.equals("FINE")) {
    System.out.println("Server in chiusura per l'invio della parola FINE, Buona Serata.");
    connection = false;    
    }
    
        } catch (SocketException e) {
        System.out.println("errore nell avvio del server");
        e.printStackTrace();
        } catch (IOException e) {
        System.out.println("Errore generico nell'avvio del server");
        e.printStackTrace();
        }
    }
    
    public void risposta() {
    try {
        InetAddress IPClient = receivePacket.getAddress();
        int portaClient = receivePacket.getPort();
        
        String send = data.toUpperCase();
        bufferOUT = send.getBytes();
        
        DatagramPacket sendPacket = new DatagramPacket(bufferOUT, bufferOUT.length, IPClient, portaClient);
        serverSocket.send(sendPacket);
        
        System.out.println("Dato inviato in risposta :" + send);
        
    } catch (SocketException e) {
        System.out.println("errore nella risposta al server");
        e.printStackTrace();
    } catch (IOException e) {
        System.out.println("errore generico nell'invio della risposta");
    }
    
    }
    
    public void chiudi() {
       serverSocket.close();   
    }
    
}
