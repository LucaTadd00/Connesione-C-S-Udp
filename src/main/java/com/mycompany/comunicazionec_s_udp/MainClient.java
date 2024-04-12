/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.comunicazionec_s_udp;

/**
 *
 * @author asuf507zu4lp054w
 */
public class MainClient {

    public static void main(String[] args) {
        Client client = new Client(4002); 
      System.out.println("-------------------------------");
      System.out.println("---------CLIENT ATTIVO---------");
      System.out.println("-------------------------------");
        client.esegui();
     
    }
}
