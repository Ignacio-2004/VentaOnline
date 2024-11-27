package com.dam2.ventaonline;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.dam2.ventaonline.exception.EmptyTxtException;
import com.dam2.ventaonline.exception.MismatchTxtException;
import com.dam2.ventaonline.managers.XMLMng;

import java.util.Locale;

public class LogInLaunched extends AppCompatActivity implements SensorEventListener {

    private XMLMng xmlMngUsr;
    private TextView txtUsr;
    private TextView txtPass;

    private SensorManager sensorManager;
    private Sensor lightSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        /*En una aplicacion real lo dejaria segun el default del dispositivo*/
        setLngEn();

        setContentView(R.layout.activity_log_in_launched);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        xmlMngUsr = new XMLMng(this,getString(R.string.XMLusrprefPref));

        initUsr();

    }

    public void logInBttAct(View view){

        try {
            txtUsr = findViewById(R.id.LogInETxtName);
            txtPass = findViewById(R.id.LogInETxtPassword);

            if (txtPass.getText().toString().isEmpty() || txtUsr.getText().toString().isEmpty()){
                throw new EmptyTxtException();
            }

            if (xmlMngUsr.get(txtUsr.getText().toString().toLowerCase(),"null").equals("null")){
                throw new MismatchTxtException();
            }

            if (!xmlMngUsr.get(txtUsr.getText().toString().toLowerCase(),"null").equals(txtPass.getText().toString().toLowerCase())){
                throw new MismatchTxtException();
            }

            xmlMngUsr.set(getString(R.string.activeXMLTag),txtUsr.getText().toString().toLowerCase());

            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
            finish();

        }catch (EmptyTxtException ete){

            Toast.makeText(this, getString(R.string.LogInETxtExceptionMsg),Toast.LENGTH_SHORT).show();

        } catch (MismatchTxtException e) {

            Toast.makeText(this, getString(R.string.LogInTxtExceptionEmpty) ,Toast.LENGTH_SHORT).show();

        }
    }

    private void setLngEn(){

        Locale locale = new Locale("en");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);

        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

    }

    private void initUsr(){

        xmlMngUsr.set("nacho","12345");
        xmlMngUsr.set("alberto","67890");

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {

            float lightValue = sensorEvent.values[0];

            if (lightValue < 10) {

                findViewById(R.id.main).setBackgroundColor(Color.BLACK);

            } else {

                findViewById(R.id.main).setBackgroundColor(Color.WHITE);

            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {}

    @Override
    protected void onResume() {
        super.onResume();
        if (sensorManager != null && lightSensor != null) {
            sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}