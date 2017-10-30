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
                    Log.v(TAG, "Set token successful");
                } else {
                    Log.e(TAG, "Set token unsuccessful : " + task.getException());
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

        recyclerView = (RecyclerView)view.findViewById(R.id.rv_home_fragment);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new OrderAdapter(getContext());
        recyclerView.setAdapter(adapter);

        fetchData();

        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                fetchData();
            }
        });

        fetchProductPicture();

        return view;
    }

    private void fetchProductPicture() {

        //after adapter is created, fetch picture
        firebaseEngine.getProductPicture(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> productChildImage = dataSnapshot.getChildren();

                List<ProductUrlPictureModel> productUrlPictureModels = new ArrayList<>();
                for (DataSnapshot mSnapshot : productChildImage) {
                    ProductUrlPictureModel model = mSnapshot.getValue(ProductUrlPictureModel.class);
                    model.addKey(mSnapshot.getKey());
                    productUrlPictureModels.add(model);
                }

                adapter.addPicture(productUrlPictureModels);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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
        Log.v(TAG, "Previous key : " + previousKey);

        firebaseEngine.getListOrder(startAt,new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                List<OrderNode> list_node = new ArrayList<>();

                Iterable<DataSnapshot> order = dataSnapshot.getChildren();

                for (DataSnapshot mSnapshot: order){

                    OrderNode orderNode = mSnapshot.getValue(OrderNode.class);
                    list_node.add(orderNode);

                    if(!order.iterator().hasNext()){
                        //set value of lastKey
                        startAt = orderNode.getTimestamp();
                        if(previousKey != startAt){
                            //set item
                            adapter.addItem(list_node);

                            //hide progressbar
                            progressBar.setVisibility(View.GONE);
                        }
                        Log.v(TAG, "List size : " + list_node.size() + "\nstartAt : " + startAt);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.v(TAG, "Database error : " + databaseError.getMessage());
            }
        });


    }
}
