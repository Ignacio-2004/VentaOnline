package com.dam2.ventaonline;

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
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.dam2.ventaonline.managers.LenguageMng;
import com.dam2.ventaonline.managers.ProductMng;
import com.dam2.ventaonline.managers.XMLMng;
import com.dam2.ventaonline.objects.Product;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private TextView txtNum;
    private TextView txt;
    private final ProductMng PM = new ProductMng();
    private XMLMng xmlMngBkst;
    private LenguageMng lm;
    private XMLMng xmlMngUsr;

    private SensorManager sensorManager;
    private Sensor lightSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        lm = new LenguageMng(this,getString(R.string.languageXMLName));
        xmlMngUsr = new XMLMng(this,getString(R.string.XMLusrprefPref));

        lm.setLng(lm.getLng(),this,true);

        setContentView(R.layout.activity_main);
        setUsr();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.MainBttOffe), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        xmlMngBkst = new XMLMng(this,getString(R.string.basketcontentXML));

        /*Initialise the view of amount of products to add*/
        textAmount("0");

        /*Initialise the products*/

        PM.addProduct( new Product("CC00001",getString(R.string.creatina_creature),25.50));
        PM.addProduct( new Product("CV00011",getString(R.string.creatina_vegana),20.99));
        PM.addProduct( new Product("PI00001",getString(R.string.proteina_isoweight),40.99));
        PM.addProduct( new Product("PC00011",getString(R.string.proteina_clearweight),50.50));
        PM.addProduct( new Product("PV00101",getString(R.string.proteina_vegana),38.99));
        PM.addProduct( new Product("PW00001",getString(R.string.preworkout_monstrack),45.00));
        PM.addProduct( new Product("PW00006",getString(R.string.preworkout_black_blood),30.99));

        initProdTxt();

    }


    public void plus(View view){

        int num =Integer.parseInt(textAmount());

        if (num<99){  num++;  }

        textAmount(String.valueOf(num));

    }

    public void substr (View view){

        int num =Integer.parseInt(textAmount());

        if (num>0){  num--;  }

        textAmount(String.valueOf(num));


    }

    public void addBkt(View view){

        if (!textAmount().equals("0")){
            ArrayList<Integer>bttId=new ArrayList<>();

            bttId.add(R.id.MainBttAdd1);
            bttId.add(R.id.MainBttAdd2);
            bttId.add(R.id.MainBttAdd3);
            bttId.add(R.id.MainBttAdd4);
            bttId.add(R.id.MainBttAdd5);
            bttId.add(R.id.MainBttAdd6);
            bttId.add(R.id.MainBttAdd7);

            for (int i =0;i<bttId.size();i++){

                if (view.getId()==bttId.get(i)){

                    Toast.makeText(this,getString(R.string.msgButtonPress)+"( "+ PM.getProduct(i).getId()+" )",Toast.LENGTH_LONG).show();

                    xmlMngBkst.set(PM.getProduct(i).getId(),textAmount());

                }

            }

            textAmount("0");

        }else{
            Toast.makeText(this,getString(R.string.ErrorAddBkt0),Toast.LENGTH_SHORT).show();
        }

    }

    public void initBasket(View view){

        Intent i = new Intent(this,Basket.class);
        startActivity(i);
        finish();

    }

    public void changeLanguage(View view){

        if (lm.getLng().equals("es")){

            lm.setLng("en",this);

        }else{

            lm.setLng("es",this);

        }

    }

    private void initProdTxt(){

        ArrayList<Product>products = PM.listProducts();

        txt = findViewById(R.id.MainTxtProd1);
        txt.setText(products.get(0).getName()+" ("+products.get(0).getCost()+getString(R.string.moneyIcon)+" )");

        txt = findViewById(R.id.MainTxtProd2);
        txt.setText(products.get(1).getName()+" ("+products.get(1).getCost()+getString(R.string.moneyIcon)+" )");

        txt = findViewById(R.id.MainTxtProd3);
        txt.setText(products.get(2).getName()+" ("+products.get(2).getCost()+getString(R.string.moneyIcon)+" )");

        txt = findViewById(R.id.MainTxtProd4);
        txt.setText(products.get(3).getName()+" ("+products.get(3).getCost()+getString(R.string.moneyIcon)+" )");

        txt = findViewById(R.id.MainTxtProd5);
        txt.setText(products.get(4).getName()+" ("+products.get(4).getCost()+getString(R.string.moneyIcon)+" )");

        txt = findViewById(R.id.MainTxtProd6);
        txt.setText(products.get(5).getName()+" ("+products.get(5).getCost()+getString(R.string.moneyIcon)+" )");

        txt = findViewById(R.id.MainTxtProd7);
        txt.setText(products.get(6).getName()+" ("+products.get(6).getCost()+getString(R.string.moneyIcon)+" )");

    }

    private void textAmount (String txt){
        txtNum = findViewById(R.id.MainTxtAmount);

        txtNum.setText(txt);
    }

    private String textAmount (){
        return (String) txtNum.getText();
    }

    private void   setUsr(){

        String user = xmlMngUsr.get(getString(R.string.activeXMLTag),getString(R.string.DefNameUsr));

        txt = findViewById(R.id.MainTxtUser);

        txt.setText(getString(R.string.txtUsr,user.toLowerCase()));

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {

            float lightValue = sensorEvent.values[0];

            if (lightValue < 10) {

                findViewById(R.id.MainBttOffe).setBackgroundColor(Color.BLACK);

            } else {

                findViewById(R.id.MainBttOffe).setBackgroundColor(Color.WHITE);

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