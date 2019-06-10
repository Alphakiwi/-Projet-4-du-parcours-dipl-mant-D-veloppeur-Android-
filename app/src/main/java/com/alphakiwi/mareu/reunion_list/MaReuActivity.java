package com.alphakiwi.mareu.reunion_list;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.alphakiwi.mareu.Model.Reunion;
import com.alphakiwi.mareu.R;
import com.alphakiwi.mareu.events.AddReunionEvent;
import com.alphakiwi.mareu.events.DeleteReunionEvent;
import com.alphakiwi.mareu.events.TriReunionEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MaReuActivity extends AppCompatActivity {

    // UI Components
    @BindView(R.id.container)
    ViewPager mViewPager;

    ListReunionPagerAdapter mPagerAdapter;
    FloatingActionButton fab;
    int id ;
    String subject;
    String image;
    String reunion;
    String heure;
    String dateAnnee;
    String dateMois;
    String dateJour;
    List<String> adressesMail;
    RadioButton radio2 = null ;
    RadioButton radio3 = null ;
    EditText dateText = null ;
    EditText hourText = null ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ma_reu);
        ButterKnife.bind(this);

        mPagerAdapter = new ListReunionPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);


        id = 3;
        configureFab();

    }


    private void configureFab() {

        fab = findViewById(R.id.fab);


        fab.setOnClickListener(view -> {

            subject = "Bowser";
            id += 1;
            reunion = "Réunion A";
            image = "http://www.arthesis-diffusion.fr/fichiers_site/a1346art/contenu_pages/CIMG3247.JPG";
            heure = "14h00";
            dateAnnee = "2019";
            dateMois = "01";
            dateJour = "01";
            adressesMail = Arrays.asList("maxime@lamzone.com","alex@lamzone.com");



            new AlertDialog.Builder(view.getContext())
                    .setView(R.layout.dialog_choix_sujet)
                    .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                            configureSujet(dialog);
                            configureLieu(dialog);
                            configureDateHeure(dialog);
                            configureAdresse(dialog);




                            EventBus.getDefault().post(new AddReunionEvent(new Reunion(id,subject,image,reunion,heure,dateAnnee,dateMois,dateJour, adressesMail)));




                        }
                    })
                    .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .show();



        });
    }

    private void configureSujet(DialogInterface dialog){

        EditText etSujet = (EditText) ((AlertDialog) dialog).findViewById(R.id.sujet);

        if (etSujet.getText().toString().length()>0) {
            subject = etSujet.getText().toString();
        }


    }

    private void configureLieu(DialogInterface dialog){

        radio2 = (RadioButton) ((AlertDialog) dialog).findViewById(R.id.radio2);
        radio3 = (RadioButton) ((AlertDialog) dialog).findViewById(R.id.radio3);


        if (radio2.isChecked()){
            reunion = "Réunion B";
            image = "http://www.materic.fr/images/realisations/salle-reunion-NSG-materic.jpg";
        }

        if (radio3.isChecked()){
            reunion = "Réunion C";
            image = "http://affaire.terrabotanica.fr/wp-content/uploads/2016/01/IMG_0868.jpg";
        }

    }

    private void configureDateHeure(DialogInterface dialog){

        dateText = (EditText) ((AlertDialog) dialog).findViewById(R.id.dateText);

        if (dateText.getText().toString().length()>0) {
            dateAnnee = dateText.getText().toString().substring(6,10);
            dateMois = dateText.getText().toString().substring(3,5);
            dateJour = dateText.getText().toString().substring(0,2);
        }

        hourText = (EditText) ((AlertDialog) dialog).findViewById(R.id.hourText);

        if (hourText.getText().toString().length()>0) {
            heure = hourText.getText().toString();
        }

    }

    private void configureAdresse(DialogInterface dialog){

        EditText textAdress = (EditText) ((AlertDialog) dialog).findViewById(R.id.textAdress);

        if (textAdress.getText().toString().length()>0) {
            List<String> strings = Arrays.asList(textAdress.getText().toString().split(","));
            List <String> Adresse  = new ArrayList<String>();
            for(int i = 0 ; i < strings.size() ; i++) {
                Adresse.add( strings.get(i).toLowerCase() + "@lamzone.com");
            }
            adressesMail = Adresse;
        }



    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();

        //R.menu.menu est l'id de notre menu

        inflater.inflate(R.menu.menu, menu);

        return true;

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i1 = item.getItemId();
        switch (i1) {
            case R.id.Tri:

                new AlertDialog.Builder(this)
                        .setView(R.layout.dialog_tri)
                        .setPositiveButton("Trier", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                RadioButton triSalle = (RadioButton) ((AlertDialog) dialog).findViewById(R.id.triLieu);
                                Boolean salle = false;

                                if (triSalle.isChecked()){
                                    salle = true;
                                }

                                EventBus.getDefault().post(new TriReunionEvent(salle));

                            }
                        })
                        .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();


                return true;

        }



        return super.onOptionsItemSelected(item);
    }


}

