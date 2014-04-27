package br.usp.sistemadeinformacao.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import br.usp.sistemadeinformacao.R;


public class ApresentacaoActivity extends Activity implements  Runnable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apresentacao);
        Handler handler = new Handler();
        handler.postDelayed(this,3000);
    }

    @Override
    public void run() {
        startActivity(new Intent(this, IndexActivity.class));
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}

