package com.alphakiwi.mareu.reunion_list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alphakiwi.mareu.Model.Reunion;
import com.alphakiwi.mareu.di.DI;
import com.alphakiwi.mareu.events.AddReunionEvent;
import com.alphakiwi.mareu.events.DeleteReunionEvent;
import com.alphakiwi.mareu.events.TriReunionEvent;
import com.alphakiwi.mareu.service.ReunionApiService;
import com.alphakiwi.mareu.R;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;


public class ReunionFragment extends Fragment {

    private ReunionApiService mApiService;
    private List<Reunion> mReunions;
    private RecyclerView mRecyclerView;


    /**
     * Create and return a new instance
     * @return @{@link ReunionFragment}
     */
    public static ReunionFragment newInstance() {
        ReunionFragment fragment = new ReunionFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getReunionApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reunion_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        initList();
        return view;
    }

    /**
     * Init the List of Reunions
     */
    private void initList() {
        mReunions = mApiService.getReunions();
        mRecyclerView.setAdapter(new MyReunionRecyclerViewAdapter(mReunions));
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     * @param event
     */


    @Subscribe
    public void onDeleteReunion(DeleteReunionEvent event) {
        mApiService.deleteReunion(event.reunion);
        initList();
    }


    @Subscribe
    public void onAddReunionEvent(AddReunionEvent event) {
        mApiService.addReunion(event.reunion);
        initList();
    }

    @Subscribe
    public void onTriReunionEvent(TriReunionEvent event) {
        mApiService.reunionTri(event.salle);
        initList();
    }





}
