package com.dam2.ventaonline;

import static com.dam2.ventaonline.R.*;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.dam2.ventaonline.managers.LenguageMng;
import com.dam2.ventaonline.managers.ProductMng;
import com.dam2.ventaonline.managers.XMLMng;
import com.dam2.ventaonline.objects.Product;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;

public class Basket extends AppCompatActivity implements SensorEventListener {

    private TextView txtProd;
    private ProductMng pm = new ProductMng();
    private XMLMng xmlMng ;
    private LenguageMng lm;
    private TextView txt;
    private ArrayList<Product> products;

    private SensorManager sensorManager;
    private Sensor lightSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        lm = new LenguageMng(this,getString(R.string.languageXMLName));

        lm.setLng(lm.getLng(),this,true);

        setContentView(R.layout.activity_basket);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        xmlMng = new XMLMng(this,getString(R.string.basketcontentXML));

        /*Initialise the products*/

        pm.addProduct( new Product("CC00001",getString(R.string.creatina_creature),25.50));
        pm.addProduct( new Product("CV00011",getString(R.string.creatina_vegana),20.99));
        pm.addProduct( new Product("PI00001",getString(R.string.proteina_isoweight),40.99));
        pm.addProduct( new Product("PC00011",getString(R.string.proteina_clearweight),50.50));
        pm.addProduct( new Product("PV00101",getString(R.string.proteina_vegana),38.99));
        pm.addProduct( new Product("PW00001",getString(R.string.preworkout_monstrack),45.00));
        pm.addProduct( new Product("PW00006",getString(R.string.preworkout_black_blood),30.99));

        products = pm.listProducts();

        initProdTxt();
        initAmountProd();
        initBktMsg();

    }

    public void home (View view){

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();

    }

    public void clear (View view){

        if(!isEmpty()){

            rstAmountProd();

        }else{

            Toast.makeText(this, getString(string.MsgBktClrEmpty),Toast.LENGTH_SHORT).show();

        }

    }

    public void changeLanguage(View view){

        if (lm.getLng().equals("es")){

            lm.setLng("en",this);

        }else{

            lm.setLng("es",this);

        }

    }

    public void buy(View view){

        int amount = 0;

        txtProd = findViewById(R.id.BktAmount1);
        amount+=Integer.parseInt(txtProd.getText().toString());

        txtProd = findViewById(R.id.BktAmount2);
        amount+=Integer.parseInt(txtProd.getText().toString());

        txtProd = findViewById(R.id.BktAmount3);
        amount+=Integer.parseInt(txtProd.getText().toString());

        txtProd = findViewById(R.id.BktAmount4);
        amount+=Integer.parseInt(txtProd.getText().toString());

        txtProd = findViewById(R.id.BktAmount5);
        amount+=Integer.parseInt(txtProd.getText().toString());

        txtProd = findViewById(R.id.BktAmount6);
        amount+=Integer.parseInt(txtProd.getText().toString());

        txtProd = findViewById(R.id.BktAmount7);
        amount+=Integer.parseInt(txtProd.getText().toString());

        try{
            if (amount==0){
                throw new EmptyTxtException();
            }

            rstAmountProd();

            Toast.makeText(this, getString(R.string.MsgBuyBkt),Toast.LENGTH_LONG).show();

        }catch (EmptyTxtException ete){
            Toast.makeText(this, getString(R.string.MsgEmptyBktBuy) ,Toast.LENGTH_SHORT).show();
        }

    }

    private void initProdTxt(){

        txtProd= findViewById(R.id.BktTxtProd1);
        txtProd.setText(products.get(0).getName()+" ("+products.get(0).getCost()+getString(R.string.moneyIcon)+" )");

        txtProd= findViewById(R.id.BktTxtProd2);
        txtProd.setText(products.get(1).getName()+" ("+products.get(1).getCost()+getString(R.string.moneyIcon)+" )");

        txtProd= findViewById(R.id.BktTxtProd3);
        txtProd.setText(products.get(2).getName()+" ("+products.get(2).getCost()+getString(R.string.moneyIcon)+" )");

        txtProd= findViewById(R.id.BktTxtProd4);
        txtProd.setText(products.get(3).getName()+" ("+products.get(3).getCost()+getString(R.string.moneyIcon)+" )");

        txtProd= findViewById(R.id.BktTxtProd5);
        txtProd.setText(products.get(4).getName()+" ("+products.get(4).getCost()+getString(R.string.moneyIcon)+" )");

        txtProd= findViewById(R.id.BktTxtProd6);
        txtProd.setText(products.get(5).getName()+" ("+products.get(5).getCost()+getString(R.string.moneyIcon)+" )");

        txtProd= findViewById(R.id.BktTxtProd7);
        txtProd.setText(products.get(6).getName()+" ("+products.get(6).getCost()+getString(R.string.moneyIcon)+" )");

    }

    @SuppressLint("SetTextI18n")
    private void initAmountProd(){

        TextView tc = findViewById(id.BktTotalCost);

        txtProd= findViewById(R.id.BktAmount1);
        txtProd.setText(xmlMng.get(products.get(0).getId(),"0"));

        double atv = Double.parseDouble(tc.getText().toString());
        int ap = Integer.parseInt(xmlMng.get(products.get(0).getId(),"0"));
        double cp = products.get(0).getCost();
        tc.setText(String.valueOf(atv+(ap*cp)).trim());

        txtProd= findViewById(R.id.BktAmount2);
        txtProd.setText(xmlMng.get(products.get(1).getId(),"0"));
        atv = Double.parseDouble(tc.getText().toString());
        ap = Integer.parseInt(xmlMng.get(products.get(1).getId(),"0"));
        cp = products.get(1).getCost();
        tc.setText(String.valueOf(atv+(ap*cp)).trim());

        txtProd= findViewById(R.id.BktAmount3);
        txtProd.setText(xmlMng.get(products.get(2).getId(),"0"));
        atv = Double.parseDouble(tc.getText().toString());
        ap = Integer.parseInt(xmlMng.get(products.get(2).getId(),"0"));
        cp = products.get(2).getCost();
        tc.setText(String.valueOf(atv+(ap*cp)).trim());

        txtProd= findViewById(R.id.BktAmount4);
        txtProd.setText(xmlMng.get(products.get(3).getId(),"0"));
        atv = Double.parseDouble(tc.getText().toString());
        ap = Integer.parseInt(xmlMng.get(products.get(3).getId(),"0"));
        cp = products.get(3).getCost();
        tc.setText(String.valueOf(atv+(ap*cp)).trim());

        txtProd= findViewById(R.id.BktAmount5);
        txtProd.setText(xmlMng.get(products.get(4).getId(),"0"));
        atv = Double.parseDouble(tc.getText().toString());
        ap = Integer.parseInt(xmlMng.get(products.get(4).getId(),"0"));
        cp = products.get(4).getCost();
        tc.setText(String.valueOf(atv+(ap*cp)).trim());

        txtProd= findViewById(R.id.BktAmount6);
        txtProd.setText(xmlMng.get(products.get(5).getId(),"0"));
        atv = Double.parseDouble(tc.getText().toString());
        ap = Integer.parseInt(xmlMng.get(products.get(5).getId(),"0"));
        cp = products.get(5).getCost();
        tc.setText(String.valueOf(atv+(ap*cp)).trim());

        txtProd= findViewById(R.id.BktAmount7);
        txtProd.setText(xmlMng.get(products.get(6).getId(),"0"));
        atv = Double.parseDouble(tc.getText().toString());
        ap = Integer.parseInt(xmlMng.get(products.get(6).getId(),"0"));
        cp = products.get(6).getCost();
        tc.setText(String.valueOf(atv+(ap*cp)).trim());

        tc.setText(tc.getText()+getString(R.string.moneyIcon));

    }

    private void rstAmountProd (){

        txtProd= findViewById(R.id.BktAmount1);
        txtProd.setText("0");
        xmlMng.set(products.get(0).getId(),"0");

        txtProd= findViewById(R.id.BktAmount2);
        txtProd.setText("0");
        xmlMng.set(products.get(1).getId(),"0");

        txtProd= findViewById(R.id.BktAmount3);
        txtProd.setText("0");
        xmlMng.set(products.get(2).getId(),"0");

        txtProd= findViewById(R.id.BktAmount4);
        txtProd.setText("0");
        xmlMng.set(products.get(3).getId(),"0");

        txtProd= findViewById(R.id.BktAmount5);
        txtProd.setText("0");
        xmlMng.set(products.get(4).getId(),"0");

        txtProd= findViewById(R.id.BktAmount6);
        txtProd.setText("0");
        xmlMng.set(products.get(5).getId(),"0");

        txtProd= findViewById(R.id.BktAmount7);
        txtProd.setText("0");
        xmlMng.set(products.get(6).getId(),"0");

        TextView tc = findViewById(id.BktTotalCost);
        tc.setText("0");

    }

    private void initBktMsg(){

        txt = findViewById(R.id.BsktTxtUser);

        txt.setText(string.HiBktMsg);

    }

    private boolean isEmpty(){

        txtProd= findViewById(R.id.BktAmount1);
        if(Integer.parseInt(String.valueOf(txtProd.getText()))!=0)  return false;

        txtProd= findViewById(R.id.BktAmount2);
        if(Integer.parseInt(String.valueOf(txtProd.getText()))!=0)  return false;

        txtProd= findViewById(R.id.BktAmount3);
        if(Integer.parseInt(String.valueOf(txtProd.getText()))!=0)  return false;

        txtProd= findViewById(R.id.BktAmount4);
        if(Integer.parseInt(String.valueOf(txtProd.getText()))!=0)  return false;

        txtProd= findViewById(R.id.BktAmount5);
        if(Integer.parseInt(String.valueOf(txtProd.getText()))!=0)  return false;

        txtProd= findViewById(R.id.BktAmount6);
        if(Integer.parseInt(String.valueOf(txtProd.getText()))!=0)  return false;

        txtProd= findViewById(R.id.BktAmount7);
        if(Integer.parseInt(String.valueOf(txtProd.getText()))!=0)  return false;


        return true;
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