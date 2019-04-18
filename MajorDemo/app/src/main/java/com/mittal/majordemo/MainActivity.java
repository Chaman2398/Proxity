package com.mittal.majordemo;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private WifiManager wifiManager;
    private String macid;
    private Context context = this;
    private ArrayList<WifiCardVariable> wifilist = new ArrayList<>();
    private ListView listView;
    private Button buttonScan;
    private int size = 0;
    private List<ScanResult> results;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter adapter;
    private WifiCardAdapter wifiadapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonScan = findViewById(R.id.scanBtn);
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wifiManager.setWifiEnabled(true);
                scanWifi();
                wifiManager.setWifiEnabled(false);
            }
        });


        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
       /* if (!wifiManager.isWifiEnabled()) {
            Toast.makeText(this, "WiFi is disabled ... We need to enable it", Toast.LENGTH_LONG).show();
            wifiManager.setWifiEnabled(true);
        }
        listView = findViewById(R.id.main_list);
        listView.setAdapter(adapter);*/
        //Toast.makeText(context, "no of wifi found:  " + arrayList.size(), Toast.LENGTH_LONG).show();
        wifilist.add(new WifiCardVariable("Mac ", "Title", "Description", R.mipmap.ic_launcher));
        wifiadapter = new WifiCardAdapter(context, wifilist);
        listView = findViewById(R.id.main_list);
        listView.setAdapter(wifiadapter);

        /*if (wifilist != null) {
            WifiCardAdapter adapter = new WifiCardAdapter(context, wifilist);
            listView.setAdapter(adapter);
        } else {
            Toast.makeText(context, " Hmm, no data in Arraylist ", Toast.LENGTH_LONG).show();
        }*/
    }

    private void scanWifi() {
        wifilist.clear();
        arrayList.clear();
        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifiManager.startScan();
        Toast.makeText(this, "Searching Offers ...", Toast.LENGTH_SHORT).show();
    }

    BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            results = wifiManager.getScanResults();
            unregisterReceiver(this);
            String testmac1 = "b8:c7:4a:5d:ce:c1";
            String testmac2 = "50:8f:4c:bd:e5:e3";
            int match = 0;
            for (ScanResult scanResult : results) {
                arrayList.add(scanResult.BSSID);
            }
            for ( String mac : arrayList)
            {  if(mac.equals(testmac1))
                {   match = 1;
                    wifilist.add(new WifiCardVariable(mac, "Proxity", "This is proximity marketing platform", R.mipmap.ic_launcher));
                }
                if(mac.equals(testmac2)) {
                    match = 1;
                    wifilist.add(new WifiCardVariable(mac, "Proxity", "This is proximity marketing platform", R.mipmap.ic_launcher));
                }
            }
            if(match == 0) Toast.makeText(context, "There are no live offers in your vicinity " , Toast.LENGTH_LONG).show();
           // Toast.makeText(context, "BR: items in wifilist:  " + wifilist.size(), Toast.LENGTH_LONG).show();
            wifiadapter.notifyDataSetChanged();
           // Toast.makeText(context, "BR: no of wifi found:  " + arrayList.size(), Toast.LENGTH_LONG).show();
        }

    };
}