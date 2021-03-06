package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Arrays;

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
            text.setGravity(Gravity.CENTER);
            text.setText(modifiedSentence);
            String test= converttoasci(sentece);
            System.out.println(test);
            clientSocket.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public String converttoasci(String matno){
        String result="";
        char [] chararray = matno.toCharArray();
        System.out.println(chararray.length);
        if(chararray.length!=8){
            result= "Dies ist keine gültige Matrikelnummer!";
        } else{
        for(int i = 0; i<chararray.length; i = i+2){
            switch(chararray[i]){
                case '0':
                    chararray[i]='a';
                    break;
                case '1':
                    chararray[i]='b';
                    break;
                case '2':
                    chararray[i]='c';
                    break;
                case '3':
                    chararray[i]='d';
                    break;
                case '4':
                    chararray[i]='e';
                    break;
                case '5':
                    chararray[i]='f';
                    break;
                case '6':
                    chararray[i]='g';
                    break;
                case '7':
                    chararray[i]='h';
                    break;
                case '8':
                    chararray[i]='i';
                    break;
                case '9':
                    chararray[i]='j';
                    break;
                default:
                    break;

            }
        }
        result = Arrays.toString(chararray);

        }
        return result;
    }
}