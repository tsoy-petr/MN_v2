package com.android.hootr.myapplication;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static com.android.hootr.myapplication.ForegroundService.CHANNEL_ID;

public class BTScanerService extends Service {


    private static final Object BLOCK = new Object();
    private static final int attempts =10;

    private CompositeDisposable disposable = new CompositeDisposable();

    public ReactivBT reactivBT;

    public String btAdress;
    public String baseName;
    public String uri_1c;

    private BluetoothAdapter btAdapter;
    private BluetoothSocket btSocket;

    // формируем UID данного приложения для идентификации
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    public Handler handler;
    public static final int RECIEVE_MESSAGE = 22;
    public static final int CONNECT_SOCKET_MESSAGE_TRUE = 33;
    public static final int CONNECT_SOCKET_MESSAGE_FALSE = 44;
    public StringBuilder sb = new StringBuilder();
    public ConnectedThread connectedThread;
    public OutputStream outStream;

    private void clearRef() {

        try {
            if (btSocket != null) {
                btSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (connectedThread != null) {
            connectedThread.cancel();
        }

        if (outStream != null) {
            try {
                outStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        handler = null;
    }

    @Override
    public void onDestroy() {

        clearRef();

        if (disposable != null) {
            disposable.dispose();
        }

        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressLint("HandlerLeak")
    @Override
    public void onCreate() {
        super.onCreate();

        reactivBT = new ReactivBT();

        disposable.add(
                Observable.create(reactivBT)
                        .subscribe(dataBT -> {

                            if (dataBT != null
                                    && !dataBT.err) {

                                String bc = new String(dataBT.buffer);
                                Log.i("happy", "bc: " + bc);
                                sendBroadCast(bc);

                            } else {
                                stopSelf();
                            }
                        })
        );

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        baseName = intent.getStringExtra(Consatans.KEY_BASENAME);
        btAdress = intent.getStringExtra(Consatans.KEY_BTADRESS);
        uri_1c = intent.getStringExtra(Consatans.KEY_URI_1C);

        clearRef();

        if (connectedThread == null) {
            btAdapter = BluetoothAdapter.getDefaultAdapter();

            if (uri_1c == null) {
                Toast.makeText(getBaseContext(), "Не верно заданы параметра для работы bluetooth сервиса", Toast.LENGTH_SHORT).show();
                stopSelf();
            }

            if (btAdapter == null) {
                // отсутствует поддержка работы с блютуз
                Toast.makeText(getBaseContext(), "Отсутствует поддержка работы с bluetooth", Toast.LENGTH_SHORT).show();
                stopSelf();
            }

            if (btAdapter.isEnabled()) {
                // Bluetooth включен. Работаем.
            } else {
                // Bluetooth выключен. Предложим пользователю включить его.
                Toast.makeText(getBaseContext(), "Bluetooth выключен", Toast.LENGTH_SHORT).show();
                stopSelf();
            }

            // подключаемся к сканеру путем указания MAC адреса
            BluetoothDevice device = btAdapter.getRemoteDevice(btAdress);

            // создаем сокет для чтения из виртуального порта
            // для создания требуется UID текущего приложения (мы его создали при объявлении переменных)
            try {
                btSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
            } catch (IOException e) {
                Toast.makeText(getBaseContext(), "Ошибка создания подключения", Toast.LENGTH_SHORT).show();
                stopSelf();
            }

            // Discovery is resource intensive.  Make sure it isn't going on
            // when you attempt to connect and pass your message.


//            createConnectionSocket = new CreateConnectionSocket(btSocket, handler);
//            createConnectionSocket.start();
//            try {
//                btSocket.connect();

            Observable<Boolean> value = Observable.create(o -> {

                boolean isOk = true;

                for (int i = 0; i <= attempts; i++) {
                    try {
                        Log.i("happy", "i: " + i);

                        if (i > 0) {
                            try {
                                btSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
                            } catch (IOException e) {
                                o.onNext(false);
                            }
                        }

                        btSocket.connect();
                        isOk = true;
                        break;
                    } catch (Exception e) {
                        Log.i("happy", "e: " + e);
                        isOk = false;
                        e.printStackTrace();
                    }
                }
                o.onNext(isOk);

            });

            disposable.add(value.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(t -> {
                        if (t) {
                            startReadBT();
                        } else {
                            stopSelf();
                        }
                        Log.i("happy", "t: " + t);
                        btAdapter.cancelDiscovery();
                    }));


            createNotificationChannel();
            Intent notificationIntent = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,
                    0, notificationIntent, 0);
            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("Foreground Service")
                    .setContentText("Запушена компонента сканирования")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentIntent(pendingIntent)
                    .build();
            startForeground(1, notification);

//            } catch (IOException e) {
//                e.printStackTrace();
//                Toast.makeText(getBaseContext(), "Ошибка соединения", Toast.LENGTH_SHORT).show();
//                stopSelf();
//            }
        }

        return START_NOT_STICKY;
    }

    private void startReadBT() throws IOException {
        Toast.makeText(getBaseContext(), "...Соединение установлено и готово к передачи данных...", Toast.LENGTH_SHORT).show();

        connectedThread = new ConnectedThread(btSocket);
        connectedThread.start();

        outStream = btSocket.getOutputStream();
    }


    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

    private void sendBroadCast(String br) {
        // формируем параметры для передачи в 1С
        Intent intentFor1C = new Intent(uri_1c);
        intentFor1C.putExtra("text", "SenderBarcode");
        intentFor1C.putExtra("title", "1C");
        intentFor1C.putExtra("data", br);
        intentFor1C.putExtra("base", baseName);
        // отправляем уведомление
        sendBroadcast(intentFor1C);
    }

    private class ConnectedThread extends Thread {

        private volatile BluetoothSocket socket;
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
                    synchronized (ConnectedThread.class) {
                        if (this.socket.isConnected()) {
                            bytes = inStream.read(buffer);
                            reactivBT.sendEmitt(new DataBT(false, buffer));
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    reactivBT.sendEmitt(new DataBT(true, buffer));
                    break;
                }


//                try {
//                    synchronized (BLOCK) {
//                        if (this.socket.isConnected()) {
//                            synchronized (BLOCK) {
//                                if (this.socket.isConnected()) {
//                                    bytes = inStream.read(buffer);
//                                    handler.obtainMessage(RECIEVE_MESSAGE, buffer).sendToTarget();
//                                }
//                            }
//                        }
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    break;
//                }
            }

            cancel();

        }

        public void cancel() {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                outStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

