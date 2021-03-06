package com.example.myfirstapp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        String sentece;
        String modifiedSentence;
        BufferedReader inFormUser = new BufferedReader(new InputStreamReader(System.in));

        Socket clientSocket = new Socket("\tse2-isys.aau.at", 53212);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        sentece = inFormUser.readLine();
        outToServer.writeBytes(sentece+'\n');
        modifiedSentence = inFromServer.readLine();
        System.out.println("Vom Server erhalten:" +modifiedSentence);

        clientSocket.close();
    }


}
