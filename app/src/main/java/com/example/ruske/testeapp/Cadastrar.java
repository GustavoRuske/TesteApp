package com.example.ruske.testeapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Cadastrar extends Activity implements OnClickListener {
    EditText et;
    Button btnCad, read_bt;
    SQLControlador dbconec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.criar_anotacao);
        et = (EditText) findViewById(R.id.anotacao_id);
        btnCad = (Button) findViewById(R.id.btnAnotacaoId);

        dbconec = new SQLControlador(this);
        dbconec.iniciarBanco();
        btnCad.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnAnotacaoId:
                String name = et.getText().toString();
                dbconec.inserirDados(name);
                Intent main = new Intent(Cadastrar.this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                break;

            default:
                break;
        }
    }
}