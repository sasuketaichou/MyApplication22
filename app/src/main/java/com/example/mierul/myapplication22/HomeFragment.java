package com.example.mierul.myapplication22;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mierul on 9/20/2017.
 */

public class HomeFragment extends BaseFragment {

    private static final String TAG = HomeFragment.class.getSimpleName();

    private FirebaseEngine firebaseEngine;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private OrderAdapter adapter;

    private long startAt = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        firebaseEngine = new FirebaseEngine();
        firebaseEngine.setToken(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Log.v(TAG,"Set token successfull");
                } else {
                    Log.e(TAG,"Set token unsuccessfull : "+task.getException());
                }
            }
        });

        setTitle("Home");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.view_home,container,false);

        progressBar = (ProgressBar) view.findViewById(R.id.loading_home);

        List<OrderNode> list = new ArrayList<>();

        recyclerView = (RecyclerView)view.findViewById(R.id.rv_home_fragment);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new OrderAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);

        fetchData();

        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                log("inside on load more");
                fetchData();
            }
        });

        return view;
    }

    public static HomeFragment newInstance() {

        HomeFragment fragment = new HomeFragment();
        Bundle arg = new Bundle();
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.home_fragment_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_logout:
                firebaseEngine.signOut(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Log.v(TAG,"Sign out with clear token ");
                        }
                    }
                });
                cleanSwitchFragment(LoginFragment.newInstance());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void fetchData() {

        final long previousKey = startAt;
        log("Previous Key : "+previousKey);

        firebaseEngine.getListOrder(startAt,new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                log("inside on datachange : "+dataSnapshot);

                List<OrderNode> list_node = new ArrayList<>();

                Iterable<DataSnapshot> order = dataSnapshot.getChildren();

                for (DataSnapshot mSnapshot: order){

                    OrderNode orderNode = mSnapshot.getValue(OrderNode.class);
                    list_node.add(orderNode);

                    if(!order.iterator().hasNext()){
                        //set value of lastKey
                        startAt = orderNode.getTimestamp();
                        log("Key : "+mSnapshot.getKey());
                        if(previousKey != startAt){
                            //set item
                            adapter.addItem(list_node);

                            //hide progressbar
                            progressBar.setVisibility(View.GONE);
                        }
                        log("List size : "+list_node.size()+"\nstartAt : "+startAt);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                log("Database error : "+databaseError.getMessage());
            }
        });


    }
}
