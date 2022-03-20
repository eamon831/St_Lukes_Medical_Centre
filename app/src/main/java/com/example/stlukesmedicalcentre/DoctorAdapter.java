package com.example.stlukesmedicalcentre;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.stlukesmedicalcentre.Hospital.Doctor;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.WordViewHolder> {

    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView trade_name,trade_dos,company,composition,generic_name,pack_size,comments,details;

        private WordViewHolder(View itemView) {
            super(itemView);
            trade_name = itemView.findViewById(R.id.trade_name);
            trade_dos= itemView.findViewById(R.id.trade_dos);
            company=itemView.findViewById(R.id.company);
            composition=itemView.findViewById(R.id.composition);
            generic_name=itemView.findViewById(R.id.generic_name);
            pack_size=itemView.findViewById(R.id.pack_size);
            comments=itemView.findViewById(R.id.comments);
            details=itemView.findViewById(R.id.details);
        }
    }

    private final LayoutInflater mInflater;
    private List<Doctor> Data; // Cached copy of words

    DoctorAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.vetdatashow_recycler_row, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        if (Data != null) {
            Doctor current = Data.get(position);
            holder.trade_name.setText(current.getTrade_name());
            holder.composition.setText(current.getComposition());
            holder.trade_dos.setText(current.getTrade_dose());
            holder.company.setText("");
            holder.generic_name.setText("");
            holder.pack_size.setText("");
            holder.details.setText("");
            holder.comments.setText("");
        } else {
            // Covers the case of data not being ready yet.
            holder.trade_name.setText("No Word");
        }
    }

    void setWords(List<Doctor> words){
        Data = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (Data != null)
            return Data.size();
        else return 0;
    }
}
