package br.usp.sistemadeinformacao.view;


import android.app.Activity;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
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
    private SearchView searchView;
    private final static float MAX_ZOOM = 13.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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

        maps.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                return false;
            }
        });
    }

    private void buscaEndereco(){
         LatLng position = Converte.emLogintudeELatitude(busca,getApplicationContext());
         maps.addMarker(new MarkerOptions()
                .title("Estou aqui!!!").position(position));
        maps.moveCamera(CameraUpdateFactory.newLatLngZoom(position, MAX_ZOOM));
        new EstacionamentoTask().execute(position);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menudown,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()){
            case R.id.barato:
                startActivity(new Intent(this,MaisBaratoActivity.class));
                return true;
            case R.id.proximo:
                startActivity(new Intent(this,MaisBaratoActivity.class));
                return true;
              default:
                return super.onMenuItemSelected(featureId, item);
        }

    }

    public class EstacionamentoTask extends AsyncTask<LatLng,Void,List<Estacionamento>> {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog = new ProgressDialog(IndexActivity.this);
            dialog.setMessage("Buscando Estacionamentos");
            dialog.show();
        }

        @Override
        protected List<Estacionamento> doInBackground(LatLng... latLngs) {
           try {
               LatLng recebendoPosicao = latLngs[0];
               if (recebendoPosicao == null) {
                   return new ArrayList<Estacionamento>();
               }
               List<Estacionamento>estacionamentos = new ArrayList<Estacionamento>();

               /*String urlEstacioneAki = "https://localhost:8080/EstacioneAki/rest/estacionamentos/proximos?latitude=" + recebendoPosicao.latitude
                       + "&longitude=" + recebendoPosicao.longitude;

               String conteudo = HttpUtils.acessar(urlEstacioneAki);

               JSONObject jsonObject = new JSONObject(conteudo);
               JSONArray resultados = jsonObject.getJSONArray("results");

                //Esperando implementação da parte web
               for(int i = 0; i < resultados.length(); i++){
                   JSONObject resultadoEstacionamentos = resultados.getJSONObject(i);
                   Estacionamento estacionamento = new Estacionamento();
                   estacionamento.setId(resultadoEstacionamentos.getLong("id"));
                   estacionamento.setNome(resultadoEstacionamentos.getString("nomeEstacionamento"));
                   estacionamento.setNumTotalVagas(resultadoEstacionamentos.getInt("numTotalVagas"));
                   LatLng geoposicao = new LatLng(Double.parseDouble(resultadoEstacionamentos.getString("latitude")),
                           Double.parseDouble(resultadoEstacionamentos.getString("longitude")));
                   estacionamento.setGeoLocalizacao(geoposicao);
                   estacionamentos.add(estacionamento);
               }*/

               return estacionamentos;
           }catch (Exception ex){
               Toast toast = Toast.makeText(getApplicationContext(), "Desculpe não foi possível encontrar nenhum estacionamento neste endereço", Toast.LENGTH_SHORT);
               toast.show();
               return null;
           }
        }

        @Override
        protected void onPostExecute(List<Estacionamento> estacionamentos) {
            super.onPostExecute(estacionamentos);
            if((estacionamentos != null)&& (!estacionamentos.isEmpty())){
                  for(Estacionamento estacionamento: estacionamentos){
                      maps.addMarker(new MarkerOptions()
                              .position(estacionamento.getGeoLocalizacao())
                              .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
                  }
            }
            dialog.dismiss();
        }
    }

}
