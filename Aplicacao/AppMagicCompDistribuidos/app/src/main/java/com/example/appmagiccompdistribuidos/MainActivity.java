package com.example.appmagiccompdistribuidos;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView textResultado;
    EditText nomeCarta;
    EditText descricaoCarta;
    EditText forcaCarta;
    EditText agilidadeCarta;
    EditText resistenciaCarta;
    List<Card> listaCard = new ArrayList<Card>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textResultado =  findViewById(R.id.textResult);
        nomeCarta = findViewById(R.id.nomeCarta);
        descricaoCarta = findViewById(R.id.descricaoCarta);
        forcaCarta = findViewById(R.id.forcaCarta);
        agilidadeCarta = findViewById(R.id.agilidadeCarta);
        resistenciaCarta = findViewById(R.id.resistenciaCarta);
    }

    public void adicionarNovaCarta(View view) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8000/cards/";

        JSONObject serieObject = new JSONObject();
        try {
            String nome = nomeCarta.getText().toString();
            String descricao = descricaoCarta.getText().toString();
            int forca = Integer.parseInt(forcaCarta.getText().toString());
            int agilidade = Integer.parseInt(agilidadeCarta.getText().toString());
            int resistencia = Integer.parseInt(resistenciaCarta.getText().toString());

            serieObject.put("card_nome", nome);
            serieObject.put("card_descricao_poder", descricao);
            serieObject.put("card_forca", forca);
            serieObject.put("card_agilidade", agilidade);
            serieObject.put("card_resistencia", resistencia);
        }catch (JSONException e){
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                url,
                serieObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //textResultado.setText(response.toString());
                        textResultado.setText("Você incluiu sua carta ao jogo!");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textResultado.setText(error.getMessage());
                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("X-Api-Key","85dBa1rP.UFGHwYJcNO6VLRcminSAuZLLhxcMhuq3");
                return params;
            }
        };
        queue.add(request);
    }

    public void consultarCartas(View view) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8000/cards/";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //textResultado.setText(response.toString());
                        try {
                            listaCard.clear();
                            JSONArray resultsCards = response.getJSONArray("results");
                            for(int i = 0; i < resultsCards.length();i++){
                                JSONObject card_info = resultsCards.getJSONObject(i);

                                /*card.setNomeCarta(card_info.getString("card_nome"));
                                card.setDescricaoCarta(card_info.getString("card_descricao_poder"));
                                card.setForcaCarta(card_info.getInt("card_forca"));
                                card.setAgilidadeCarta(card_info.getInt("card_agilidade"));
                                card.setResistenciaCarta(card_info.getInt("card_resistencia"));
                                */
                                String carta = card_info.getString("card_nome");
                                String descricaoCarta = card_info.getString("card_descricao_poder");
                                int forca = card_info.getInt("card_forca");
                                int agilidade = card_info.getInt("card_agilidade");
                                int resistencia = card_info.getInt("card_resistencia");
                                Card card = new Card(carta,descricaoCarta,forca,agilidade,resistencia);
                                listaCard.add(card);
                            }
                            mostrarDados();

                        } catch (JSONException error){
                            textResultado.setText(error.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textResultado.setText(error.getMessage());
                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("X-Api-Key","85dBa1rP.UFGHwYJcNO6VLRcminSAuZLLhxcMhuq3");
                return params;
            }
        };
        queue.add(request);
    }

    public void mostrarDados(){
        String resultado = "";
        for (int i = 0; i < listaCard.size(); ++i){
            Card objeto = (Card) listaCard.get(i);

            resultado += "Carta = " + objeto.getNomeCarta() + "\n" +
                    "Descrição = " + objeto.getDescricaoCarta() + "\n" +
                    "Força = " + objeto.getForcaCarta() + "\n" +
                    "Agilidade = " + objeto.getAgilidadeCarta() + "\n" +
                    "Resistência = " + objeto.getResistenciaCarta() + "\n\n";
            textResultado.setText(resultado);
        }
    }
}