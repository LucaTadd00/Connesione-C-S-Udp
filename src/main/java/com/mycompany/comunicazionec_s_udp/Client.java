
package com.mycompany.comunicazionec_s_udp;

import java.io.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Client {
    
    int porta;
    boolean connection = true;
    byte[] bufferIN = new byte[1024];
    byte[] bufferOUT = new byte[1024];
    DatagramPacket receivePacket;
    DatagramSocket clientSocket;
    String received;
    
    public Client(int porta) {
    
        this.porta = porta; 
        receivePacket =  new DatagramPacket(bufferIN, bufferIN.length);
        
    }
    
    public void esegui() {
    while(connection) {
    connetti();
    ricevi();
    }
    chiudi();
    }
    
    public void connetti() {
        try {
    InetAddress IPServer = InetAddress.getByName("localhost");
    Scanner s = new Scanner(System.in);
    
    clientSocket = new DatagramSocket();
    System.out.println("client in funzione - inserisci i dati da inviare (FINE per terminare): ");
    String dato = s.nextLine();
    bufferOUT = dato.getBytes();
    
    DatagramPacket sendPacket = new DatagramPacket(bufferOUT, bufferOUT.length, IPServer, porta);
    clientSocket.send(sendPacket);
    
    if(dato.equals("FINE")) {
    System.out.println("Client in chiusura per l'invio della parola FINE, Buona Serata.");
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
    
    public void ricevi() {
    try {
        receivePacket =  new DatagramPacket(bufferIN, bufferIN.length);
        clientSocket.receive(receivePacket);
        received = new String(receivePacket.getData());
        
        int nCaratteri = receivePacket.getLength();
        received = received.substring(0, nCaratteri);
        System.out.println("RISPOSTA DEL SERVER :" + received);
        
    } catch (SocketException e) {
        System.out.println("errore nella risposta al server");
        e.printStackTrace();
    } catch (IOException e) {
        System.out.println("errore generico nell'invio della risposta");
    }
    
    }
    
    public void chiudi() {
       clientSocket.close();   
    }
    
}
