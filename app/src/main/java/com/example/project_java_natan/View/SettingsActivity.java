package com.example.project_java_natan.View;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.project_java_natan.R;
import com.example.project_java_natan.RecyclerViewAdapterColor;
import com.hotmail.or_dvir.easysettings.pojos.EasySettings;
import com.hotmail.or_dvir.easysettings.pojos.SettingsObject;
import com.hotmail.or_dvir.easysettings.pojos.SwitchSettingsObject;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    private final ArrayList<String> ImageUrls = new ArrayList<>();
    private final ArrayList<String> ImageNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("Example", "onCreate");
        getIntent().setAction("Already created");
        super.onCreate(savedInstanceState);
        SettingsActivity.this.setTitle("Setting");
        setContentView(R.layout.easysetting);
        LinearLayout container = findViewById(R.id.settingsContainer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("Settings");
        ArrayList<SettingsObject> mySettingsList = EasySettings.createSettingsArray(
                new SwitchSettingsObject.Builder("darkmode", "Dark Mode", true)
                        .setUseValueAsSummary()
                        .addDivider()
                        .build()
        );
        boolean value = EasySettings.retrieveSettingsSharedPrefs(this).getBoolean("darkmode", false);
        EasySettings.initializeSettings(this, mySettingsList);
        EasySettings.inflateSettingsLayout(this, container, mySettingsList);
        initImages();
    }

    private void initImages(){
        ImageUrls.add("https://i0.wp.com/infojmoderne.com/wp-content/uploads/2019/11/ERAN-ZAHAVI.jpg?fit=448%2C302");
        ImageNames.add("Zahavi");

        ImageUrls.add("https://images.rtl.fr/~c/770v513/rtl/www/1501917-emmanuel-macron-invite-de-rtl-le-8-avril-2022.jpg");
        ImageNames.add("Macron");

        ImageUrls.add("https://s7d2.scene7.com/is/image/Kennametal/History?$MobileBanner$&");
        ImageNames.add("Geography-");

        ImageUrls.add("https://israelvalley.com/wp-content/uploads/2022/01/4bafdc66-ca4a-4568-b342-da3314e31b2a_w.jpg");
        ImageNames.add("Omer Adam");

        initRecycler();
    }
    private void initRecycler(){
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerViewAdapterColor adapter = new RecyclerViewAdapterColor(ImageUrls, ImageNames, this);
        recyclerView.setAdapter(adapter);
    }
}