package com.alphakiwi.mareu.reunion_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alphakiwi.mareu.Model.Reunion;
import com.alphakiwi.mareu.R;

import com.alphakiwi.mareu.di.DI;
import com.alphakiwi.mareu.service.ReunionApiService;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


public class DetailReunionActivity extends AppCompatActivity {

    private TextView text = null;
    private TextView salle = null;
    private TextView presentation = null;
    private TextView dateEtHeure = null;
    private TextView adresseMailTextView = null;
    private ImageView imageImageView = null;
    private Button back;
    private Reunion reunion = null;
    private ReunionApiService mApiService;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_reunion);

        mApiService = DI.getReunionApiService();



        text = (TextView) findViewById(R.id.text);
        salle = (TextView) findViewById(R.id.reunion);
        presentation = (TextView) findViewById(R.id.description);
        dateEtHeure = (TextView) findViewById(R.id.dateEtHeure);
        adresseMailTextView = (TextView) findViewById(R.id.adresseMail);
        imageImageView = (ImageView) findViewById(R.id.imageAvatar);

        Intent i = getIntent();
        String sujet = i.getStringExtra("sujet");
        int nbAdresses = i.getIntExtra("nbAdresses", 0);
        String image = i.getStringExtra("image");
        String lieu = i.getStringExtra("lieu");
        String dateAnne = i.getStringExtra("dateAnnee");
        String dateMois = i.getStringExtra("dateMois");
        String dateJour = i.getStringExtra("dateJour");
        String heure = i.getStringExtra("heure");

        String adresseMail = "" ;

        String extra;


        for(int j = 0 ; j <  nbAdresses ; j++) {
            extra = "adresse" + j ;
            adresseMail = adresseMail + i.getStringExtra(extra) + ", ";

        }


        configureBack();


        Glide.with(this)
                .load(image)
                .override(420, 350) // resizes the image to these dimensions (in pixel)
                .centerCrop()
                .into(imageImageView);



        text.setText(sujet );
        salle.setText("Salle : " + lieu );
        presentation.setText("Nous allons aborder plus en détail les diffèrentes étapes du projet sur " + sujet);
        dateEtHeure.setText("le " + dateJour + "/" + dateMois + "/" + dateAnne+ " à " + heure);
        adresseMailTextView.setText(adresseMail);
        presentation.setMovementMethod(new ScrollingMovementMethod());

    }




    private void configureBack() {
        back = findViewById(R.id.back);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

    }



}
