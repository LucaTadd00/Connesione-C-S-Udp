/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.comunicazionec_s_udp;

/**
 *
 * @author asuf507zu4lp054w
 */
public class MainServer {
    
    public static void main(String[] args) {
       Server server = new Server(4002);
      System.out.println("-------------------------------");
      System.out.println("---------SERVER ATTIVO---------");
      System.out.println("-------------------------------");
       server.esegui();
      
    }
    
}
