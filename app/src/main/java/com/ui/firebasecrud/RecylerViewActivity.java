package com.ui.firebasecrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

public class RecylerViewActivity extends AppCompatActivity {
RecyclerView recyclerView;
UsersAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyler_view);
        recyclerView=findViewById(R.id.rview);
        FirebaseApp.initializeApp(RecylerViewActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<User> options =
                new FirebaseRecyclerOptions.Builder<User>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Users"), User.class)
                        .build();
        adapter=new UsersAdapter(options,getApplicationContext());
        recyclerView.setAdapter(adapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();

}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {  //inflate search bar
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem menuItem=menu.findItem(R.id.search);
        SearchView searchView=(SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Txtearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Txtearch(newText);

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
    private  void Txtearch(String str){ // Firebase Recyler view Search
        FirebaseRecyclerOptions<User> options =
                new FirebaseRecyclerOptions.Builder<User>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Users").orderByChild("name").startAt(str).endAt(str+"~"), User.class)
                        .build();
        adapter=new UsersAdapter(options,getApplicationContext()); //init adapter
        adapter.startListening();
        recyclerView.setAdapter(adapter); //set adapter in recyler view
    }
}