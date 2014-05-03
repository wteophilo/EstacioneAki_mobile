package br.usp.sistemadeinformacao.view;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import br.usp.sistemadeinformacao.R;

public class MaisBaratoActivity extends Activity {

    private ListView maisBarato;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maisbarato);

        maisBarato = (ListView)findViewById(R.id.listBarato);
    }


}
