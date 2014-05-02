package br.usp.sistemadeinformacao.view;


import android.app.Activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.usp.sistemadeinformacao.R;
import br.usp.sistemadeinformacao.modelo.Estacionamento;
import br.usp.sistemadeinformacao.utils.Converte;
import br.usp.sistemadeinformacao.utils.HttpUtils;

public class IndexActivity extends Activity {

    private GoogleMap maps;
    private String busca;
    private Button btPesquisa;
    private EditText localizacao;
    private final static float MAX_ZOOM = 13.0f;
    private List<Estacionamento> estacionamentos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);
        //Maps
        maps = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        maps.setMyLocationEnabled(true);
        maps.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        this.localizacao = (EditText) findViewById(R.id.localizacao);

        btPesquisa = (Button) findViewById(R.id.btPesquisa);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (localizacao.isFocused()){
            localizacao.setText("");
        }
        btPesquisa.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                busca = localizacao.getText().toString();
                if(!busca.equals("")){
                    buscaEndereco();
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Por favor insira um endereço!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    private void buscaEndereco(){
         LatLng position = Converte.emLogintudeELatitude(busca,getApplicationContext());

        new EstacionamentoTask().execute(position);
       /* maps.addMarker(new MarkerOptions()
                .title("Estou aqui!!!")
                .position(position));
        maps.moveCamera(CameraUpdateFactory.newLatLngZoom(position, MAX_ZOOM));*/

    }
    /* public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuup,menu);

        return true;
    }*/


    public class EstacionamentoTask extends AsyncTask<LatLng,Void,List<Estacionamento>> {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog = new ProgressDialog(IndexActivity.this);
            dialog.show();
        }

        @Override
        protected List<Estacionamento> doInBackground(LatLng... latLngs) {
           return new ArrayList<Estacionamento>();
        }

        @Override
        protected void onPostExecute(List<Estacionamento> estacionamentos) {
            super.onPostExecute(estacionamentos);
            if(estacionamentos != null){

            }

            dialog.dismiss();
        }
    }

}
