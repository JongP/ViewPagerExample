package com.example.viewpagerexample;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.viewpagerexample.adapters.CurrencyRVAdapter;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FragDoge extends Fragment {
    private View view;
    private EditText searchEdit;
    private RecyclerView currenciesRV;
    private ProgressBar loadingPB;
    private ArrayList<CurrencyModel> currencyModelArrayList;
    private CurrencyRVAdapter currencyRVAdapter;

    //상태 저장하기
    public static FragDoge newInstance() {
        FragDoge fragDoge = new FragDoge();
        return fragDoge;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_doge, container, false);

        searchEdit =  view.findViewById(R.id.idEdtSearch);
        currenciesRV = view.findViewById(R.id.idRVCurrencies);
        loadingPB = view.findViewById(R.id.idPBLoading);
        currencyModelArrayList = new ArrayList<>();
        currencyRVAdapter = new CurrencyRVAdapter(currencyModelArrayList,getContext());
        currenciesRV.setLayoutManager(new LinearLayoutManager(getContext()));
        currenciesRV.setAdapter(currencyRVAdapter);

        searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filterCurrencies(s.toString());
            }
        });


        return view;
    }


    @Override
    public void onResume() {
        currencyModelArrayList.clear();
        getCurrencyData();
        super.onResume();
    }

    private  void filterCurrencies(String currency){
        ArrayList<CurrencyModel> filterList = new ArrayList<>();
        for(CurrencyModel item : currencyModelArrayList){
            if(item.getName().toLowerCase().contains(currency.toLowerCase())){
                filterList.add(item);
            }
        }
        if (filterList.isEmpty()){
            Toast.makeText(getContext(),"No currency found for searched query",Toast.LENGTH_SHORT).show();
        }
        else {
            currencyRVAdapter.filterList(filterList);
        }

    }



    private void getCurrencyData(){
        loadingPB.setVisibility(View.VISIBLE);
        String url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                loadingPB.setVisibility(View.GONE);
                try {
                    JSONArray dataArray = response.getJSONArray("data");
                    for(int i=0;i<dataArray.length();i++){
                        double price = 0;
                        JSONObject dataObj = dataArray.getJSONObject(i);
                        String name = dataObj.getString("name");
                        String symbol = dataObj.getString("symbol");
                        JSONObject quote = dataObj.getJSONObject("quote");
                        JSONObject USD = quote.getJSONObject("USD");
                        price = USD.getDouble("price");
                        currencyModelArrayList.add(new CurrencyModel(name,symbol,price));
                    }
                    currencyRVAdapter.notifyDataSetChanged();
                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loadingPB.setVisibility(View.GONE);
                Toast.makeText(getContext(),"Fail to get the data",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("X-CMC_PRO_API_KEY","d74f1274-7f85-4810-8f94-7199824b0a50");

                return headers;
            }
        };
        requestQueue.add(jsonObjectRequest);



    }
}
