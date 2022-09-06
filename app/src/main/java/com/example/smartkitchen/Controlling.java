package com.example.smartkitchen;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class Controlling extends Activity {
        private static final String TAG = "BlueTest5-Controlling";
        private int mMaxChars = 50000;//Default//change this to string..........
        private UUID mDeviceUUID;
        private BluetoothSocket mBTSocket;
        private ReadInput mReadThread = null;
        String mac_id;

    BluetoothAdapter btAdapter;

        private boolean mIsUserInitiatedDisconnect = false;
        private boolean mIsBluetoothConnected = false;


        private Button mBtnDisconnect;
        private BluetoothDevice mDevice;

        private ProgressDialog progressDialog;

// untuk menentukan data yang akan diteruskan ke alat
        final static String tebal_1 = "101";
        final static String tebal_2 = "102";
        final static String tebal_3 = "103";
        final static String tebal_4 = "104";
        final static String tebal_5 = "105";
        final static String banyak_1 = "201";
        final static String banyak_2 = "202";
        final static String banyak_3 = "203";
        final static String banyak_4 = "204";
        final static String banyak_5 = "205";
        final static String banyak_6 = "206";
        final static String banyak_7 = "207";
        final static String banyak_8 = "208";
        final static String banyak_9 = "209";
        final static String banyak_10 = "210";

        Button cm1;
        Button cm2;
        Button cm3;
        Button cm4;
        Button cm5;
        Button b1;
        Button b2;
        Button b3;
        Button b4;
        Button b5;
        Button b6;
        Button b7;
        Button b8;
        Button b9;
        Button b10;

        TextView btnBack;


        @SuppressLint("MissingPermission")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_smart_kitchen);

                cm1 = findViewById(R.id.cm1);
                cm2 = findViewById(R.id.cm2);
                cm3 = findViewById(R.id.cm3);
                cm4 = findViewById(R.id.cm4);
                cm5 = findViewById(R.id.cm5);
                b1 = findViewById(R.id.buah1);
                b2 = findViewById(R.id.buah2);
                b3 = findViewById(R.id.buah3);
                b4 = findViewById(R.id.buah4);
                b5 = findViewById(R.id.buah5);
                b6 = findViewById(R.id.buah6);
                b7 = findViewById(R.id.buah7);
                b8 = findViewById(R.id.buah8);
                b9 = findViewById(R.id.buah9);
                b10 = findViewById(R.id.buah10);
                btnBack = findViewById(R.id.btnBack2);

            final String[] banyak = {"0"};
            final String[] tebal = {"0"};


//          buat ngambil data dari activity sebelumnya
            Intent intent = getIntent();
            Bundle b = intent.getExtras();

//          penyimpanan data yang di dapat dari activity sebelumnya
            mDevice = b.getParcelable(ScanningDevicesActivity.DEVICE_EXTRA);
            mDeviceUUID = UUID.fromString(b.getString(ScanningDevicesActivity.DEVICE_UUID));
            mMaxChars = b.getInt(ScanningDevicesActivity.BUFFER_SIZE);
            mac_id = intent.getStringExtra("mac_id");

//          inisiasi bluetooth
            btAdapter = BluetoothAdapter.getDefaultAdapter();
            mDevice = btAdapter.getRemoteDevice(mac_id);
            Log.d(TAG, "Ready");

            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Controlling.this, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });
            cm1.setOnClickListener(new View.OnClickListener(){


                @Override
                public void onClick(View view) {
                    try {
                        mBTSocket.getOutputStream().write(tebal_1.getBytes());
                        tebal[0] = tebal_1;
                    } catch (IOException e){
                        // TODO Auto-generated catch block
                        e.printStackTrace();

                    }
                    Toast.makeText(getApplicationContext(), "1 CM", Toast.LENGTH_SHORT).show();

                }
            });
            cm2.setOnClickListener(new View.OnClickListener(){


                @Override
                public void onClick(View view) {
                    try {
                        mBTSocket.getOutputStream().write(tebal_2.getBytes());
                        tebal[0] = tebal_2;

                    } catch (IOException e){
                        // TODO Auto-generated catch block
                        e.printStackTrace();

                    }
                    Toast.makeText(getApplicationContext(), "2 CM", Toast.LENGTH_SHORT).show();

                }
            });
            cm3.setOnClickListener(new View.OnClickListener(){


                @Override
                public void onClick(View view) {
                    try {
                        mBTSocket.getOutputStream().write(tebal_3.getBytes());

                        tebal[0] = tebal_3;
                    } catch (IOException e){
                        // TODO Auto-generated catch block
                        e.printStackTrace();

                    }
                    Toast.makeText(getApplicationContext(), "3 CM", Toast.LENGTH_SHORT).show();

                }
            });
            cm4.setOnClickListener(new View.OnClickListener(){


                @Override
                public void onClick(View view) {
                    try {
                        mBTSocket.getOutputStream().write(tebal_4.getBytes());

                        tebal[0] = tebal_4;
                    } catch (IOException e){
                        // TODO Auto-generated catch block
                        e.printStackTrace();

                    }
                    Toast.makeText(getApplicationContext(), "4 CM", Toast.LENGTH_SHORT).show();

                }
            });
            cm5.setOnClickListener(new View.OnClickListener(){


                @Override
                public void onClick(View view) {
                    try {
                        mBTSocket.getOutputStream().write(tebal_5.getBytes());

                        tebal[0] = tebal_5;
                    } catch (IOException e){
                        // TODO Auto-generated catch block
                        e.printStackTrace();

                    }
                    Toast.makeText(getApplicationContext(), "5 CM", Toast.LENGTH_SHORT).show();

                }
            });

            b1.setOnClickListener(new View.OnClickListener(){


                @Override
                public void onClick(View view) {
                    try {
                        mBTSocket.getOutputStream().write(banyak_1.getBytes());

                        banyak[0] = banyak_1;
                    } catch (IOException e){
                        // TODO Auto-generated catch block
                        e.printStackTrace();

                    }
                    Toast.makeText(getApplicationContext(), "1 Buah", Toast.LENGTH_SHORT).show();

                }
            });
            b2.setOnClickListener(new View.OnClickListener(){


                @Override
                public void onClick(View view) {
                    try {
                        mBTSocket.getOutputStream().write(banyak_2.getBytes());

                        banyak[0] = banyak_2;
                    } catch (IOException e){
                        // TODO Auto-generated catch block
                        e.printStackTrace();

                    }
                    Toast.makeText(getApplicationContext(), "2 Buah", Toast.LENGTH_SHORT).show();

                }
            });
            b3.setOnClickListener(new View.OnClickListener(){


                @Override
                public void onClick(View view) {
                    try {
                        mBTSocket.getOutputStream().write(banyak_3.getBytes());

                        banyak[0] = banyak_3;
                    } catch (IOException e){
                        // TODO Auto-generated catch block
                        e.printStackTrace();

                    }
                    Toast.makeText(getApplicationContext(), "3 Buah", Toast.LENGTH_SHORT).show();

                }
            });
            b4.setOnClickListener(new View.OnClickListener(){


                @Override
                public void onClick(View view) {
                    try {
                        mBTSocket.getOutputStream().write(banyak_4.getBytes());

                        banyak[0] = banyak_4;
                    } catch (IOException e){
                        // TODO Auto-generated catch block
                        e.printStackTrace();

                    }
                    Toast.makeText(getApplicationContext(), "4 Buah", Toast.LENGTH_SHORT).show();

                }
            });
            b5.setOnClickListener(new View.OnClickListener(){


                @Override
                public void onClick(View view) {
                    try {
                        mBTSocket.getOutputStream().write(banyak_5.getBytes());

                        banyak[0] = banyak_5;
                    } catch (IOException e){
                        // TODO Auto-generated catch block
                        e.printStackTrace();

                    }
                    Toast.makeText(getApplicationContext(), "5 Buah", Toast.LENGTH_SHORT).show();

                }
            });
            b6.setOnClickListener(new View.OnClickListener(){


                @Override
                public void onClick(View view) {
                    try {
                        mBTSocket.getOutputStream().write(banyak_6.getBytes());

                        banyak[0] = banyak_6;
                    } catch (IOException e){
                        // TODO Auto-generated catch block
                        e.printStackTrace();

                    }
                    Toast.makeText(getApplicationContext(), "6 Buah", Toast.LENGTH_SHORT).show();

                }
            });
            b7.setOnClickListener(new View.OnClickListener(){


                @Override
                public void onClick(View view) {
                    try {
                        mBTSocket.getOutputStream().write(banyak_7.getBytes());

                        banyak[0] = banyak_7;
                    } catch (IOException e){
                        // TODO Auto-generated catch block
                        e.printStackTrace();

                    }
                    Toast.makeText(getApplicationContext(), "7 Buah", Toast.LENGTH_SHORT).show();

                }
            });
            b8.setOnClickListener(new View.OnClickListener(){


                @Override
                public void onClick(View view) {
                    try {
                        mBTSocket.getOutputStream().write(banyak_8.getBytes());

                        banyak[0] = banyak_8;
                    } catch (IOException e){
                        // TODO Auto-generated catch block
                        e.printStackTrace();

                    }
                    Toast.makeText(getApplicationContext(), "8 Buah", Toast.LENGTH_SHORT).show();

                }
            });
            b9.setOnClickListener(new View.OnClickListener(){


                @Override
                public void onClick(View view) {
                    try {
                        mBTSocket.getOutputStream().write(banyak_9.getBytes());

                        banyak[0] = banyak_9;
                    } catch (IOException e){
                        // TODO Auto-generated catch block
                        e.printStackTrace();

                    }
                    Toast.makeText(getApplicationContext(), "9 Buah", Toast.LENGTH_SHORT).show();

                }
            });
            b10.setOnClickListener(new View.OnClickListener(){


                @Override
                public void onClick(View view) {
                    try {
                        mBTSocket.getOutputStream().write(banyak_10.getBytes());

                        banyak[0] = banyak_10;
                    } catch (IOException e){
                        // TODO Auto-generated catch block
                        e.printStackTrace();

                    }
                    Toast.makeText(getApplicationContext(), "10 Buah", Toast.LENGTH_SHORT).show();
                }
            });
        if (banyak[0] != "0" && tebal[0] != "0"){
            Toast.makeText(this, "Proses dijalankan", Toast.LENGTH_SHORT).show();
            banyak[0] = "0";
            tebal[0] = "0";
        }

        }

        private class ReadInput implements Runnable {

            private boolean bStop = false;
// lopping yang berjalan di background
            private Thread t;

            public ReadInput() {
                t = new Thread(this, "Input Thread");
                t.start();
            }

            public boolean isRunning() {
                return t.isAlive();
            }

            @SuppressLint("MissingPermission")
            @Override
            public void run() {
                InputStream inputStream;
                try {
                    mBTSocket = mDevice.createRfcommSocketToServiceRecord(mDeviceUUID);
                    mBTSocket.connect();
                    inputStream = mBTSocket.getInputStream();
                    while (!bStop) {
                        byte[] buffer = new byte[256];
                        if (inputStream.available() > 0) {
                            inputStream.read(buffer);
                            int i = 0;
                            /*
                             * This is needed because new String(buffer) is taking the entire buffer i.e. 256 chars on Android 2.3.4 http://stackoverflow.com/a/8843462/1287554
                             */
                            for (i = 0; i < buffer.length && buffer[i] != 0; i++) {
                            }
                            final String strInput = new String(buffer, 0, i);

                            /*
                             * If checked then receive text, better design would probably be to stop thread if unchecked and free resources, but this is a quick fix
                             */


                        }
                        Thread.sleep(500);
                    }
                } catch (IOException e) {
// TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InterruptedException e) {
// TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

            public void stop() {
                bStop = true;
            }

        }

        private class DisConnectBT extends AsyncTask<Void, Void, Void> {

            @Override
            protected void onPreExecute() {
            }

            @Override
            protected Void doInBackground(Void... params) {//cant inderstand these dotss

                if (mReadThread != null) {
                    mReadThread.stop();
                    while (mReadThread.isRunning())
                        ; // Wait until it stops
                    mReadThread = null;

                }

                try {
                    mBTSocket.close();
                } catch (IOException e) {
// TODO Auto-generated catch block
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                mIsBluetoothConnected = false;
                if (mIsUserInitiatedDisconnect) {
                    finish();
                }
            }

        }

        private void msg(String s) {
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPause() {
            if (mBTSocket != null && mIsBluetoothConnected) {
                new DisConnectBT().execute();
            }
            Log.d(TAG, "Paused");
            super.onPause();
        }

        @Override
        protected void onResume() {
            if (mBTSocket == null || !mIsBluetoothConnected) {
                new ConnectBT().execute();
            }
            Log.d(TAG, "Resumed");
            super.onResume();
        }

        @Override
        protected void onStop() {
            Log.d(TAG, "Stopped");
            super.onStop();
        }

        @Override
        protected void onSaveInstanceState(Bundle outState) {
// TODO Auto-generated method stub
            super.onSaveInstanceState(outState);
        }

        private class ConnectBT extends AsyncTask<Void, Void, Void> {
            private boolean mConnectSuccessful = true;

            @Override
            protected void onPreExecute() {

                progressDialog = ProgressDialog.show(com.example.smartkitchen.Controlling.this, "Hold on", "Connecting");// http://stackoverflow.com/a/11130220/1287554

            }

            @Override
            protected Void doInBackground(Void... devices) {

                try {
                    if (mBTSocket == null || !mIsBluetoothConnected) {
                        if (ActivityCompat.checkSelfPermission(com.example.smartkitchen.Controlling.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            mBTSocket = mDevice.createInsecureRfcommSocketToServiceRecord(mDeviceUUID);
                            BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                            mBTSocket.connect();
                        }
                    }
                } catch (IOException e) {
                    // Unable to connect to device`
                    // e.printStackTrace();
                    mConnectSuccessful = false;


                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);

                if (!mConnectSuccessful) {
                    Toast.makeText(getApplicationContext(), "Could not connect to device.Please turn on your Hardware", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    msg("Connected to device");
                    mIsBluetoothConnected = true;
                    mReadThread = new ReadInput(); // Kick off input reader
                }

                progressDialog.dismiss();
            }

        }

        @Override
        protected void onDestroy() {
            // TODO Auto-generated method stub
            super.onDestroy();
        }
    }
