package com.alphakiwi.mareu.reunion_list;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.alphakiwi.mareu.Model.Reunion;
import com.alphakiwi.mareu.R;
import com.alphakiwi.mareu.events.DeleteReunionEvent;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;


public class MyReunionRecyclerViewAdapter extends RecyclerView.Adapter<MyReunionRecyclerViewAdapter.ViewHolder> {

    private final List<Reunion> mReunions;


    public MyReunionRecyclerViewAdapter(List<Reunion> items) {
        mReunions = items;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_reunion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position ) {
        final Reunion reunion = mReunions.get(position);

        holder.mReunionName.setText(reunion.getLieu() + " - " + reunion.getHour() + " - " + reunion.getSubject());

        String adresses = reunion.getAdressesMail().get(0) + ", " + reunion.getAdressesMail().get(1) ;


        switch (holder.mReunionAvatar.getResources().getConfiguration().orientation) {
            case Configuration.ORIENTATION_PORTRAIT: adresses = adresses.substring(0, 22) + "..."; break;
            case Configuration.ORIENTATION_LANDSCAPE:  adresses = adresses + "... " ; break;
        }

        holder.mReunionAdresse.setText(adresses);
        Glide.with(holder.mReunionAvatar.getContext())
                .load(reunion.getImageUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mReunionAvatar);

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteReunionEvent(reunion));

            }
        });



        holder.mReunionName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent newPage = new Intent(holder.mReunionAvatar.getContext(), DetailReunionActivity.class);
                newPage.putExtra("sujet",reunion.getSubject());
                newPage.putExtra("image",reunion.getImageUrl());
                newPage.putExtra("lieu",reunion.getLieu());
                newPage.putExtra("dateAnnee",reunion.getDateAnnee());
                newPage.putExtra("dateMois",reunion.getDateMois());
                newPage.putExtra("dateJour",reunion.getDateJour());
                newPage.putExtra("heure",reunion.getHour());
                newPage.putExtra("nbAdresses",reunion.getAdressesMail().size());

                String adresse;



                for(int i = 0 ; i < reunion.getAdressesMail().size() ; i++) {
                    adresse = "adresse" + i ;
                    newPage.putExtra(adresse, reunion.getAdressesMail().get(i));
                }

                holder.mReunionAvatar.getContext().startActivity(newPage);



            }
        });



    }

    @Override
    public int getItemCount() {
        return mReunions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar)
        public ImageView mReunionAvatar;
        @BindView(R.id.item_list_name)
        public TextView mReunionName;
        @BindView(R.id.item_list_adresse)
        public TextView mReunionAdresse;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;



        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
