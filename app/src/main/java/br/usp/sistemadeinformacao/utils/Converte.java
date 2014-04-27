package br.usp.sistemadeinformacao.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;


import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Classe para conversão de coordenadas dos googlemaps
 * Created by Willian on 18/04/2014.
 */
public class Converte{

    /**
     * Converte o endereço em Latitude e Longitude
     * @param endereco
     * @return
     * @see android.location.Geocoder
     */
    public static LatLng emLogintudeELatitude(String endereco,Context context){
        List<Address> list = null;
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            list = geocoder.getFromLocationName(endereco,1);
        }catch (final IOException ex){
            System.out.println(ex);
        }
        if ( list == null){
            return null;
        }
        return new LatLng(list.get(0).getLatitude(),list.get(0).getLongitude());

    }


    /**
     * Converte Longitude e Latitude em Endereço
     * @param lat
     * @param longi
     * @return
     */
    public static String emEndereco(String lat,String longi,Context context){
        List<Address> list = null;
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try{
            list = geocoder.getFromLocation(Double.parseDouble(lat), Double.parseDouble(longi),1);
        }catch (final IOException ex){
            System.out.println(ex);
        }

        if ( list == null){
            return "";
        }

        String endereco = criaEndereco(list.get(0));
        return endereco;
    }

    /**
     * Monta endereço de acordo com a quantidade de linhas e as concatena.
     * @param address
     * @return
     */
    private static String criaEndereco(Address address) {
        StringBuilder montaEnd = new StringBuilder();
        for( int i= 0 ; i< address.getMaxAddressLineIndex();i++){
            montaEnd.append(address.getAddressLine(i));
        }
        return montaEnd.toString();
    }
}
