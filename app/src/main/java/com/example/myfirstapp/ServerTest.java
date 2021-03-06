package com.example.myfirstapp;

import android.provider.ContactsContract;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;

public class ServerTest implements Runnable {

    public static String convertmatno(String matno){
        System.out.println(matno);
        System.out.println("juhu");
        String result ="das ist ein test";

        return result;
    }


    public void run(){

        try {
            System.out.println("Hallo");
            ServerSocket welcomeSocket = new ServerSocket(53212);
            while(true) {

                Socket connectionSocket = welcomeSocket.accept();
                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                System.out.println("Verbindung aufgebaut");

                System.out.println(inFromClient.readLine());
                String response = convertmatno(inFromClient.readLine().toString()) + '\n';
                outToClient.writeBytes(response);
            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
