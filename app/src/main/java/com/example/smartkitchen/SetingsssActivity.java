package com.example.smartkitchen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class SetingsssActivity extends HomeActivity {

    Button btnOn;
    Button btnOff;
    Button btnShowPaired;
    Button btnScan;
    ListView lstvw;
    TextView btnBack;
    BluetoothAdapter BA;

    ArrayList<String> stringArrayList = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;

    Intent btEnable;
    int reqCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setingsss);

        btnOn = findViewById(R.id.btnOn);
        btnOff = findViewById(R.id.btnOff);
        btnBack = findViewById(R.id.btnBack);
        btnShowPaired = findViewById(R.id.btnShowPaired);
        btnScan = findViewById(R.id.btnScan);
        lstvw = findViewById(R.id.listview);


        BA = BluetoothAdapter.getDefaultAdapter();
        btEnable = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        reqCode = 1;


        bluetoothOnMetode();
        bluetoothOffMetode();
        showpaired();


//      Button Back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetingsssActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

//      Button Scan
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetingsssActivity.this, ScanningDevicesActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


    }


    private void showpaired() {
        //Button Show Paired
        btnShowPaired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                Set<BluetoothDevice> bt = BA.getBondedDevices();

                if (bt.size() > 0) {
                    for (BluetoothDevice device : bt) {
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        stringArrayList.add(device.getName() + "  " + device.getAddress());
                    }
                    Toast.makeText(getApplicationContext(), "Showing Devices", Toast.LENGTH_SHORT).show();
                    arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,stringArrayList);
                    lstvw.setAdapter(arrayAdapter);
                }

            }
        });
    };

    private void bluetoothOffMetode() {
        //Button Off
        btnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BA.isEnabled()) {
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    BA.disable();
                    Toast.makeText(getApplicationContext(), "Bluetooth Off", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    //Permission untuk mengaktifkan Bluetooth
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == reqCode) {

            if (resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Bluetooth On", Toast.LENGTH_SHORT).show();

            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Bluetooth Canceled", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void bluetoothOnMetode() {
        //Button On
        btnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Jika Bluetooth tidak support
                if (BA==null){
                    Toast.makeText(getApplicationContext(),"Bluetooth Not Support",Toast.LENGTH_SHORT).show();

                }
                else {
                    //Jika Bluetooth support maka bluetooth akan menyala
                    if (!BA.isEnabled()){
                        startActivityForResult(btEnable,reqCode);
                    }
                }

            }
        });
    }


}