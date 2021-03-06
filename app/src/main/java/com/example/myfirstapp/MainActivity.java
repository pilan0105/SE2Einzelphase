package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
   EditText matno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void onClick(View view) throws IOException {
        matno = findViewById(R.id.editTextNumber);
        System.out.println(matno.getText());
        String sentece;
        String modifiedSentence;
        try {
            //Loesung für NetworkOnMainThreadException https://stackoverflow.com/questions/6343166/how-to-fix-android-os-networkonmainthreadexception
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Socket clientSocket = new Socket("se2-isys.aau.at", 53212);

            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            sentece = matno.getText().toString();

            outToServer.writeBytes(sentece+'\n');

            modifiedSentence = inFromServer.readLine();
            System.out.println("Vom Server erhalten:" + modifiedSentence);
            TextView text = findViewById(R.id.textView3);
            text.setText(modifiedSentence);
        clientSocket.close();}
        catch(IOException e){
            e.printStackTrace();
        }
    }
}