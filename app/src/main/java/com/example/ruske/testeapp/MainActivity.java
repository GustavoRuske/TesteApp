package com.example.ruske.testeapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends ActionBarActivity {

    Button btnCadastrarAnotacao;
    ListView lista;
    SQLControlador dbconec;
    TextView tv_anotID, tv_anotText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbconec = new SQLControlador(this);
        dbconec.iniciarBanco();
        btnCadastrarAnotacao = (Button) findViewById(R.id.btnCriarAnotacao);
        lista = (ListView) findViewById(R.id.listViewAnotacao);

        //Botão para cadastrar anotacao
        btnCadastrarAnotacao.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iagregar = new Intent(MainActivity.this, Cadastrar.class);
                startActivity(iagregar);
            }
        });


        Cursor cursor = dbconec.lerDados();

        String[] from = new String[] {
                DBhelper.ANOTACAO_ID,
                DBhelper.ANOTACAO_TEXT
        };
        int[] to = new int[] {
                R.id.anotacao_id,
                R.id.anotacao_conteudo
        };

        this.lista = (ListView) findViewById(R.id.listViewAnotacao);
        this.lista.setEmptyView(findViewById(R.id.emptyList));

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                MainActivity.this, R.layout.formato_fila, cursor, from, to);

        adapter.notifyDataSetChanged();
        lista.setAdapter(adapter);


        // ação para quando clicar na anotação, atualizar ou excluir
        lista.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                tv_anotID = (TextView) view.findViewById(R.id.anotacao_id);
                tv_anotText = (TextView) view.findViewById(R.id.anotacao_conteudo);

                String aux_anotId = tv_anotID.getText().toString();
                String aux_anotText = tv_anotText.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), Editar.class);
                modify_intent.putExtra("anotacaoId", aux_anotId);
                modify_intent.putExtra("anotacaoText", aux_anotText);
                startActivity(modify_intent);
            }
        });

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}