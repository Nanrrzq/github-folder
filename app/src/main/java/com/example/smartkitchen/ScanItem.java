package com.example.smartkitchen;

public class ScanItem {
    public static String device_name;
    public static String device_mac;

    public void User (String device_name, String device_mac){
        this.device_name = device_name;
        this.device_mac = device_mac;
    }
}
