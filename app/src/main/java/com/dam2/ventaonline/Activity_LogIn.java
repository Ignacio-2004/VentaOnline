package com.dam2.ventaonline;

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
import com.dam2.ventaonline.managers.LenguageMng;
import com.dam2.ventaonline.managers.XMLMng;

public class Activity_LogIn extends AppCompatActivity {

    private LenguageMng lm ;
    private XMLMng xmlMngUsr;
    private TextView txtUsr;
    private TextView txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        lm = new LenguageMng(this,getString(R.string.languageXMLName));

        lm.setLng(lm.getLng("es"),this,true);

        setContentView(R.layout.activity_log_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        xmlMngUsr = new XMLMng(this,getString(R.string.XMLusrprefPref));

    }

    public void logInBttAct(View view){

        try {
            txtUsr = findViewById(R.id.LogInETxtName);
            txtPass = findViewById(R.id.LogInETxtPassword);

            if (txtPass.toString().isEmpty() || txtUsr.toString().isEmpty()){
                throw new EmptyTxtException();
            }

            if (xmlMngUsr.get(txtUsr.toString(),"null").equals("null")){
                throw new MismatchTxtException();
            }

        }catch (EmptyTxtException ete){

            Toast.makeText(this, getString(R.string.LogInETxtExceptionMsg),Toast.LENGTH_SHORT).show();

        } catch (MismatchTxtException e) {

            Toast.makeText(this, getString(R.string.LogInTxtExceptionEmpty) ,Toast.LENGTH_SHORT).show();

        }

    }
}