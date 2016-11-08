package com.example.ruske.testeapp;

/**
 * Created by Ruske on 06/11/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Editar extends Activity implements OnClickListener {

    EditText et;
    Button btnAtualizar, btnExcluir;

    long anotacao_id;

    SQLControlador dbcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar);

        dbcon = new SQLControlador(this);
        dbcon.iniciarBanco();

        et = (EditText) findViewById(R.id.anotacao_id);
        btnAtualizar = (Button) findViewById(R.id.btnAtualizar);
        btnExcluir = (Button) findViewById(R.id.btnExcluir);

        Intent i = getIntent();
        String anotID = i.getStringExtra("anotacaoId");
        String anotText = i.getStringExtra("anotacaoText");

        anotacao_id = Long.parseLong(anotID);

        et.setText(anotText);

        btnAtualizar.setOnClickListener(this);
        btnExcluir.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnAtualizar:
                String memName_upd = et.getText().toString();
                dbcon.atualizarDados(anotacao_id, memName_upd);
                this.returnHome();
                break;

            case R.id.btnExcluir:
                dbcon.deleteData(anotacao_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {

        Intent home_intent = new Intent(getApplicationContext(),
                MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(home_intent);
    }
}