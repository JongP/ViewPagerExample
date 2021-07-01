package com.example.viewpagerexample.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewpagerexample.CurrencyModel;
import com.example.viewpagerexample.R;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CurrencyRVAdapter extends RecyclerView.Adapter<CurrencyRVAdapter.ViewHolder> {

    private ArrayList<CurrencyModel> currencyModelArrayList;
    private Context context;
    private  static DecimalFormat df2 = new DecimalFormat("#.##");

    public CurrencyRVAdapter(ArrayList<CurrencyModel> currencyModelArrayList, Context context) {
        this.currencyModelArrayList = currencyModelArrayList;
        this.context = context;
    }

    public void filterList(ArrayList<CurrencyModel> filteredList){
        currencyModelArrayList = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CurrencyRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.currency_item,parent,false);
        return new CurrencyRVAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull  CurrencyRVAdapter.ViewHolder holder, int position) {
        CurrencyModel currencyModel = currencyModelArrayList.get(position);
        holder.currencyNameTV.setText(currencyModel.getName());
        holder.symbolTV.setText(currencyModel.getSymbol());
        holder.rateTV.setText("$ "+df2.format(currencyModel.getPrice()));
    }

    @Override
    public int getItemCount() {
        return currencyModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView currencyNameTV, symbolTV, rateTV;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            currencyNameTV = itemView.findViewById(R.id.idTvCurrencyName);
            symbolTV = itemView.findViewById(R.id.idTVSymbol);
            rateTV = itemView.findViewById(R.id.idTVCurrencyRate);
        }
    }
}
