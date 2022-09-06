package com.example.smartkitchen;

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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.UUID;

public class ScanningDevicesActivity extends AppCompatActivity {


    Button btnScanDevices;
    TextView btnBack1;
    BluetoothAdapter BA;
    ListView lstvw1;
    TextView clicked_device;
    Button connect;
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

    ArrayList<String> stringArrayList = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanning_devices);

        BA = BluetoothAdapter.getDefaultAdapter();
        lstvw1 = findViewById(R.id.listview1);
        btnScanDevices = findViewById(R.id.btnScanDevices);
        btnBack1 = findViewById(R.id.btnBack1);
        clicked_device = (TextView) findViewById(R.id.tv_click);
        connect = findViewById(R.id.btnConnect);

//        Button Back
        btnBack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScanningDevicesActivity.this, SetingsssActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


//          Button Connect
            connect.setOnClickListener(new View.OnClickListener(){
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


//      Button Scan Devices
        btnScanDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                BA.startDiscovery();

            }

        });
        IntentFilter intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(Receiver, intentFilter);

        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, stringArrayList);
        lstvw1.setAdapter(arrayAdapter);
        lstvw1.setClickable(true);
        lstvw1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = lstvw1.getItemAtPosition(i).toString();
                clicked_device.setText(text);
                device_data = (BluetoothDevice) lstvw1.getSelectedItem();
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
            Toast.makeText(getApplicationContext(), "Scanning Devices", Toast.LENGTH_SHORT).show();
        }
    };
    public static boolean isEmpty(BluetoothDevice obj) {
        return obj == null;
    }

}