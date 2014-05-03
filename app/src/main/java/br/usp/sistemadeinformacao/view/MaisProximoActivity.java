package br.usp.sistemadeinformacao.view;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import br.usp.sistemadeinformacao.R;

public class MaisProximoActivity extends Activity {

    private ListView listProximo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maisproximo);

        listProximo = (ListView) findViewById(R.id.listProximo);
    }


}
