package com.covid19v2mx.coronavirus;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.covid19v2mx.coronavirus.model.CountrysReport;
import com.covid19v2mx.coronavirus.model.OverallReport;
import com.covid19v2mx.coronavirus.network.ApiCountrysReport;
import com.covid19v2mx.coronavirus.network.ApiOverallReport;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.global_info, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        ApiOverallReport apiOverallReport = new ApiOverallReport(MainActivity.this) {
            @Override
            public void onComplete(OverallReport overallReport) {
                Log.i(TAG, "onComplete: "+ overallReport.toString());
            }

            @Override
            public void onFailure(String msg) {
                Log.i(TAG, "onFailure: "+ msg);
            }
        };
        apiOverallReport.startwork();
        ApiCountrysReport apiCountrysReport = new ApiCountrysReport(MainActivity.this) {
            @Override
            public void onComplete(List<CountrysReport> countrysReportList) {
                Log.i(TAG, "onComplete: "+ countrysReportList.toString());
            }

            @Override
            public void onFailure(String msg) {
                Log.i(TAG, "onFailure: "+ msg);
            }
        };
        apiCountrysReport.startwork();

    }

}
