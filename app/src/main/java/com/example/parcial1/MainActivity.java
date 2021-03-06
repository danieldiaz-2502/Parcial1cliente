package com.example.parcial1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    private Button redBtn, yellowBtn, greenBtn, confirmarBtn, vistaBtn;
    private EditText posxEdit, posyEdit, recordarText;
    private BufferedWriter bwriter;
    private int posx, posy;
    private String mensaje, importancia, confirmacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        greenBtn = findViewById(R.id.greenBtn);
        yellowBtn = findViewById(R.id.yellowBtn);
        redBtn = findViewById(R.id.redBtn);
        confirmarBtn = findViewById(R.id.confirmarBtn);
        vistaBtn = findViewById(R.id.vistaBtn);

        posxEdit = findViewById(R.id.posxEdit);
        posyEdit = findViewById(R.id.posyEdit);
        recordarText = findViewById(R.id.recordarText);

        //aqui se inicia la conexión
        new Thread(
                ()-> {
                    try {
                        Socket socket = new Socket("192.168.1.1", 5000);
                        InputStream is = socket.getInputStream();
                        OutputStream os = socket.getOutputStream();
                        OutputStreamWriter osw = new OutputStreamWriter(os);
                        bwriter = new BufferedWriter(osw);
                        Log.e("mensaje"," "+ bwriter);
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();

        //se le asigna un string a cada color para que en eclipse se pueda poner el recordatorio del color seleccionado
        greenBtn.setOnClickListener(
                (v) -> {
                    importancia = "verde";
                }
        );

        yellowBtn.setOnClickListener(
                (v) -> {
                    importancia = "amarillo";
                }
        );

        redBtn.setOnClickListener(
                (v) -> {
                    importancia = "rojo";
                }
        );

        confirmarBtn.setOnClickListener(
                (v) -> {

                    posx = Integer.parseInt(posxEdit.getText().toString());
                    posy = Integer.parseInt(posyEdit.getText().toString());
                    mensaje = recordarText.getText().toString();
                    //se asigna este string para agregar el recordatorio a un arreglo
                    confirmacion = "confirmar";
                    initClient();

                }
        );

        vistaBtn.setOnClickListener(
                (v) -> {

                    posx = Integer.parseInt(posxEdit.getText().toString());
                    posy = Integer.parseInt(posyEdit.getText().toString());
                    mensaje = recordarText.getText().toString();
                    //este string es para ver el recordatorio, pero aun no se agrega al arreglo
                    confirmacion = "vista";

                    initClient();

                }
        );

    }

    public void initClient() {
        //se envia el recordatorio en un json
        Gson gson = new Gson();
        Recordatorio recordatorio = new Recordatorio(posx, posy, mensaje,importancia,confirmacion);
        String conexion = gson.toJson(recordatorio);

        new Thread(()-> {
            try {
                //se hace el envio como tal
                bwriter.write(conexion+"\n");
                bwriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

}