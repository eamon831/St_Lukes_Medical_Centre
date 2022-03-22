package com.example.stlukesmedicalcentre;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.stlukesmedicalcentre.Database.AppDatabase;
import com.example.stlukesmedicalcentre.Database.Model;
import com.example.stlukesmedicalcentre.Database.UserDao;
import com.example.stlukesmedicalcentre.Hospital.Doctor;
import com.example.stlukesmedicalcentre.Hospital.Repository;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import papaya.in.sendmail.SendMail;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.WordViewHolder> {

    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView name,dept,time;
        CardView card;

        private WordViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            dept= itemView.findViewById(R.id.department);
            time=itemView.findViewById(R.id.time);
            card=itemView.findViewById(R.id.root);


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
            holder.name.setText(current.getName());
            holder.dept.setText(current.getDept());
            holder.time.setText(current.getTime());
            
            holder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // Toast.makeText(holder.card.getContext(), "yess", Toast.LENGTH_SHORT).show();
                    Dialog d;
                    d=new Dialog(holder.card.getContext());
                    TextView tex;
                    d.setContentView(R.layout.confirm);
                    tex=d.findViewById(R.id.mes);
                    tex.setText("Enter Your NHI Number For Book");

                    Button ok=d.findViewById(R.id.add);
                    d.show();

                    ok.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void onClick(View view) {
                            EditText tittle=d.findViewById(R.id.tittle);
                            EditText mail=d.findViewById(R.id.email);
                            String nhi=tittle.getText().toString();
                            AppDatabase db= Room.databaseBuilder(holder.card.getContext(),AppDatabase.class,"tbuser_dd").allowMainThreadQueries().build();
                            UserDao userDao=db.userDao();
                            if (!Patterns.EMAIL_ADDRESS.matcher(mail.getText().toString()).matches()) {
                                mail.setError("Invalid Mail");
                                mail.requestFocus();
                                return;
                            }
                            if(userDao.exist(nhi))
                            {

                                Model model=userDao.singleitem(nhi);
                                Toast.makeText(holder.card.getContext(), "Booking Successfull", Toast.LENGTH_SHORT).show();
                                 ViewModel viewModel;

                                viewModel=new ViewModelProvider((ViewModelStoreOwner) holder.card.getContext()).get(ViewModel.class);
                                try{
                                SendMail mai = new SendMail("chulchul831@gmail.com", "chulchul",
                                        mail.getText().toString(),
                                        "Booking",
                                        "Your Booking is confirm\n  "+Data.get(position).toString());
                                mai.execute();}catch (Exception e){

                                    Toast.makeText(holder.card.getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                                }
                                viewModel.delete(Data.get(position));





                                ArrayList<String>
                                        list=model.v;

                                list.add(Data.get(position).toString());

                                list=removeDuplicates(list);
                                model.v=list;
                                userDao.update(model);






                                delete(position);
                                d.dismiss();
                                return;
                            }
                            else
                            {
                                Toast.makeText(holder.card.getContext(), "Please Complete Registration\nIf you are not Registered", Toast.LENGTH_SHORT).show();
                                tittle.setError("Invalid NHI ");
                                tittle.requestFocus();
                            }
                        }
                    });

                }
            });

        } else {
            // Covers the case of data not being ready yet.
            holder.name.setText("No Word");
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
    void delete(int position)
    {
        Data.remove(position);
        notifyDataSetChanged();
    }
    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list)
    {

        // Create a new LinkedHashSet
        Set<T> set = new LinkedHashSet<>();

        // Add the elements to set
        set.addAll(list);

        // Clear the list
        list.clear();

        // add the elements of set
        // with no duplicates to the list
        list.addAll(set);

        // return the list
        return list;
    }
}
