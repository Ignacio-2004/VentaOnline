package com.dam2.ventaonline;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.dam2.ventaonline.managers.ProductMng;
import com.dam2.ventaonline.objects.Product;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView txtNum;
    private TextView txtProd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.MainBttOffe), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        /*Initialise the view of amount of products to add*/
        txtNum = findViewById(R.id.MainTxtAmount);

        txtNum.setText("0");

        /*Initialise the products*/
        ProductMng pm = new ProductMng();

        pm.addProduct( new Product("CC00001","Creatina Creature",25.50));
        pm.addProduct( new Product("CV00011","Creatina Vegana",20.99));
        pm.addProduct( new Product("PI00001","Proteina Isoweight",40.99));
        pm.addProduct( new Product("PC00011","Proteina Clearweight",50.50));
        pm.addProduct( new Product("PV00101","Proteina Vegana",38.99));
        pm.addProduct( new Product("PW00001","Preworkout Monstrack",45.00));
        pm.addProduct( new Product("PW00006","Preworkout Black Blood",30.99));

        initProdTxt(pm);

    }

    public void plus(View view){

        int num =Integer.parseInt((String) txtNum.getText());

        if (num<99){  num++;  }

        txtNum.setText(String.valueOf(num));

    }

    public void substr (View view){

        int num =Integer.parseInt((String) txtNum.getText());

        if (num>0){  num--;  }

        txtNum.setText(String.valueOf(num));


    }

    private void initProdTxt(ProductMng pm){

        ArrayList<Product>products = pm.listProducts();

        txtProd= findViewById(R.id.MainTxtProd1);
        txtProd.setText(products.get(0).getName()+" ("+products.get(0).getCost()+"€ )");

        txtProd= findViewById(R.id.MainTxtProd2);
        txtProd.setText(products.get(1).getName()+" ("+products.get(1).getCost()+"€ )");

        txtProd= findViewById(R.id.MainTxtProd3);
        txtProd.setText(products.get(2).getName()+" ("+products.get(2).getCost()+"€ )");

        txtProd= findViewById(R.id.MainTxtProd4);
        txtProd.setText(products.get(3).getName()+" ("+products.get(3).getCost()+"€ )");

        txtProd= findViewById(R.id.MainTxtProd5);
        txtProd.setText(products.get(4).getName()+" ("+products.get(4).getCost()+"€ )");

        txtProd= findViewById(R.id.MainTxtProd6);
        txtProd.setText(products.get(5).getName()+" ("+products.get(5).getCost()+"€ )");

        txtProd= findViewById(R.id.MainTxtProd7);
        txtProd.setText(products.get(6).getName()+" ("+products.get(6).getCost()+"€ )");

    }
}