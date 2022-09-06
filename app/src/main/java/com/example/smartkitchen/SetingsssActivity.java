package com.example.smartkitchen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class SetingsssActivity extends HomeActivity {

    Button btnOn;
    Button btnOff;
    Button btnShowPaired;
    Button btnScan;
    Button btnConnect2;
    ListView lstvw;
    TextView btnBack;
    TextView Clicked_Devices2;
    BluetoothAdapter BA;

    ArrayList<String> stringArrayList = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;

    Intent btEnable;
    int reqCode;

    BluetoothDevice device_data;
    String mac_id;

    private static final int BT_ENABLE_REQUEST = 10; // This is the code we use for BT Enable
    private static final int SETTINGS = 20;
    private UUID mDeviceUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private int mBufferSize = 50000; //Default
    public static final String DEVICE_EXTRA = "com.example.lightcontrol.SOCKET";
    public static final String DEVICE_UUID = "com.example.lightcontrol.uuid";
    private static final String DEVICE_LIST = "com.example.lightcontrol.devicelist";
    private static final String DEVICE_LIST_SELECTED = "com.example.lightcontrol.devicelistselected";
    public static final String BUFFER_SIZE = "com.example.lightcontrol.buffersize";
    private static final String TAG = "BlueTest5-MainActivity";


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
        Clicked_Devices2 = findViewById(R.id.tv_click2);
        btnConnect2 = findViewById(R.id.btnConnect2);


        BA = BluetoothAdapter.getDefaultAdapter();
        btEnable = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        reqCode = 1;


        bluetoothOnMetode();
        bluetoothOffMetode();
        showpaired();

        btnConnect2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                BluetoothDevice device = device_data;
                Intent intent = new Intent(getApplicationContext(), Controlling.class);
                intent.putExtra(DEVICE_EXTRA, device);
                intent.putExtra(DEVICE_UUID, mDeviceUUID.toString());
                intent.putExtra("mac_id",mac_id);
                intent.putExtra(BUFFER_SIZE, mBufferSize);
                startActivity(intent);
            }
        });

        IntentFilter intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(Receiver, intentFilter);

        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, stringArrayList);
        lstvw.setAdapter(arrayAdapter);
        lstvw.setClickable(true);
        lstvw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = lstvw.getItemAtPosition(i).toString();
                Clicked_Devices2.setText(text);
                device_data = (BluetoothDevice) lstvw.getSelectedItem();
            }
        });


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

    BroadcastReceiver Receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
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
                mac_id = device.getAddress();
                arrayAdapter.notifyDataSetChanged();

            }
//            Toast.makeText(getApplicationContext(), "Scanning Devices", Toast.LENGTH_SHORT).show();
        }
    };
    public static boolean isEmpty(BluetoothDevice obj) {
        return obj == null;
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