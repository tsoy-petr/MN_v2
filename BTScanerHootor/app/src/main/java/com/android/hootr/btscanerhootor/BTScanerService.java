package com.android.hootr.btscanerhootor;

import android.annotation.SuppressLint;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class BTScanerService extends Service {

    public String btAdress;
    public String baseName;

    private BluetoothAdapter btAdapter;
    private BluetoothSocket btSocket;

    // формируем UID данного приложения для идентификации
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    public Handler handler;
    public static final int RECIEVE_MESSAGE = 1;
    public StringBuilder sb = new StringBuilder();
    public ConnectedThread connectedThread;
    public OutputStream outStream;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressLint("HandlerLeak")
    @Override
    public void onCreate() {
        super.onCreate();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                switch (msg.what) {
                    case RECIEVE_MESSAGE:

                        byte[] readBuf = (byte[]) msg.obj;

                        String strIncom = new String(readBuf, 0, msg.arg1);
                        sb.append(strIncom);

                        // формируем параметры для передачи в 1С
                        Intent intentFor1C = new Intent("com.google.android.c2dm.intent.RECEIVE");
                        intentFor1C.putExtra("text", "SenderBarcode");
                        intentFor1C.putExtra("title", "1C");
                        intentFor1C.putExtra("data", strIncom);
                        intentFor1C.putExtra("base", baseName);
                        // отправляем уведомление
                        sendBroadcast(intentFor1C);


                        //пока не понятно для чего.....
                        // формируем строку
                        int endOfLineIndex = sb.indexOf("\r\n"); // определяем символы конца строки
                        if (endOfLineIndex > 0) {// если встречаем конец строки,
                            String sbprint = sb.substring(0, endOfLineIndex); // то извлекаем строку
                            sb.delete(0, sb.length()); // и очищаем sb
                        }

                        break;

                }

            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        baseName = intent.getStringExtra(Consatans.KEY_BASENAME);
        btAdress = intent.getStringExtra(Consatans.KEY_BTADRESS);

        btAdapter = BluetoothAdapter.getDefaultAdapter();

        if (btAdapter == null) {
            // отсутствует поддержка работы с блютуз
            Toast.makeText(getBaseContext(), "Отсутствует поддержка работы с bluetooth", Toast.LENGTH_SHORT).show();
        }

        if (btAdapter.isEnabled()) {
            // Bluetooth включен. Работаем.
        } else {
            // Bluetooth выключен. Предложим пользователю включить его.
            Toast.makeText(getBaseContext(), "Bluetooth выключен", Toast.LENGTH_SHORT).show();
            stopSelf();
        }

        try {
            btSocket.connect();
            Toast.makeText(getBaseContext(), "...Соединение установлено и готово к передачи данных...", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "Ошибка соединения", Toast.LENGTH_SHORT).show();
        }

        connectedThread = new ConnectedThread(btSocket);
        connectedThread.start();

        try {
            outStream = btSocket.getOutputStream();
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), "Ошибка запуска потокового чтения", Toast.LENGTH_SHORT).show();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private class ConnectedThread extends Thread {

        private BluetoothSocket socket;
        private InputStream inStream;
        private OutputStream outStream;

        public ConnectedThread(BluetoothSocket socket) {
            this.socket = socket;

            try {
                inStream = socket.getInputStream();
                outStream = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        @Override
        public void run() {
//            super.run();

            byte[] buffer = new byte[1024];
            int bytes;

            while (true) {

                try {
                    bytes = inStream.read(buffer);
                    handler.obtainMessage(RECIEVE_MESSAGE, bytes, -1, buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }

        }

        public void cancel() {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

