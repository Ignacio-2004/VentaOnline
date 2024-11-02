package com.dam2.ventaonline;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.dam2.ventaonline.managers.ProductMng;
import com.dam2.ventaonline.managers.XMLMng;
import com.dam2.ventaonline.objects.Product;

import java.util.ArrayList;

public class Basket extends AppCompatActivity {

    private TextView txtProd;
    private ProductMng pm = new ProductMng();
    private XMLMng xmlMng ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_basket);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        xmlMng = new XMLMng(this,getString(R.string.basketcontentXML));

        /*Initialise the products*/

        pm.addProduct( new Product("CC00001",getString(R.string.creatina_creature),25.50));
        pm.addProduct( new Product("CV00011",getString(R.string.creatina_vegana),20.99));
        pm.addProduct( new Product("PI00001",getString(R.string.proteina_isoweight),40.99));
        pm.addProduct( new Product("PC00011",getString(R.string.proteina_clearweight),50.50));
        pm.addProduct( new Product("PV00101",getString(R.string.proteina_vegana),38.99));
        pm.addProduct( new Product("PW00001",getString(R.string.preworkout_monstrack),45.00));
        pm.addProduct( new Product("PW00006",getString(R.string.preworkout_black_blood),30.99));

        initProdTxt();
        initAmountProd();
    }

    public void home (View view){

        Intent i = new Intent();
        startActivity(i);
        finish();

    }

    public void clear (View view){
        ArrayList<Product> products = pm.listProducts();

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

    }

    private void initProdTxt(){

        ArrayList<Product> products = pm.listProducts();

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
        txtProd.setText(products.get(5).getName()+" ("+products.get(5).getCost()+"€ )");

        txtProd= findViewById(R.id.BktTxtProd7);
        txtProd.setText(products.get(6).getName()+" ("+products.get(6).getCost()+"€ )");

    }

    private void initAmountProd(){

        ArrayList<Product> products = pm.listProducts();

        txtProd= findViewById(R.id.BktAmount1);
        txtProd.setText(xmlMng.get(products.get(0).getId(),"0"));

        txtProd= findViewById(R.id.BktAmount2);
        txtProd.setText(xmlMng.get(products.get(1).getId(),"0"));

        txtProd= findViewById(R.id.BktAmount3);
        txtProd.setText(xmlMng.get(products.get(2).getId(),"0"));

        txtProd= findViewById(R.id.BktAmount4);
        txtProd.setText(xmlMng.get(products.get(3).getId(),"0"));

        txtProd= findViewById(R.id.BktAmount5);
        txtProd.setText(xmlMng.get(products.get(4).getId(),"0"));

        txtProd= findViewById(R.id.BktAmount6);
        txtProd.setText(xmlMng.get(products.get(5).getId(),"0"));

        txtProd= findViewById(R.id.BktAmount7);
        txtProd.setText(xmlMng.get(products.get(6).getId(),"0"));

    }
}