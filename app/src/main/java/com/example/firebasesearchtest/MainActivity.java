package com.example.firebasesearchtest;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {
//    private EditText etSearchField;
    private SearchView searchView;
    private ImageButton ibSearch;
    private ArrayList<Users> list;
    private RecyclerView rvResultList;
    private DatabaseReference mUserDatabase;
    private AdapterClass adapterClass;
    private FirebaseFirestore firebaseFirestore;
    private static final String TAG = "FireLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUserDatabase = FirebaseDatabase.getInstance().getReference("Users");
        setupUiViews();
//        ibSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String searchText = etSearchField.getText().toString();
////                firebaseUserSearch(searchText);
//            }
//        });
        firebaseFirestore.collection("Users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.d(TAG, "Error :" + e.getMessage());
                }
                for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED) {
                        Users users = doc.getDocument().toObject(Users.class);
                        list.add(users);

                        adapterClass.notifyDataSetChanged();
                    }
                }
            }
        });
        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    search(newText);
                    return true;
                }
            });
        }
    }

    private void search(String str) {
        ArrayList<Users> myList = new ArrayList<>();
        for (Users object : list) {
            if (object.getName().toLowerCase().contains(str.toLowerCase())) {
                myList.add(object);
            }
        }
        AdapterClass adapterClass = new AdapterClass(myList);
        rvResultList.setAdapter(adapterClass);
    }


    private void setupUiViews() {
//        etSearchField = findViewById(R.id.etOne);
        ibSearch = findViewById(R.id.ibSearch);
        searchView = findViewById(R.id.searchView);
        rvResultList = findViewById(R.id.result_list);
        list = new ArrayList<>();
        adapterClass = new AdapterClass(list);
        rvResultList.setHasFixedSize(true);
        rvResultList.setLayoutManager(new LinearLayoutManager(this));
        rvResultList.setAdapter(adapterClass);
        firebaseFirestore = FirebaseFirestore.getInstance();

    }


//    private void firebaseUserSearch(String searchText) {
//        Toast.makeText(MainActivity.this, "Started Search", Toast.LENGTH_SHORT).show();
//        Query query = mUserDatabase.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");
//        FirebaseRecyclerOptions<Users> options =
//                new FirebaseRecyclerOptions.Builder<Users>()
//                        .setQuery(query, Users.class)
//                        .build();
//
//        FirebaseRecyclerAdapter<Users, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Users, UsersViewHolder>(options) {
//
//            @NonNull
//            @Override
//            public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent ,false);
//
//                return new UsersViewHolder(view);
//            }
//
//            @Override
//            protected void onBindViewHolder(@NonNull UsersViewHolder holder, int position, @NonNull Users model) {
//                holder.setDetails(getApplicationContext(),model.getName(),model.getStatus(),model.getImage());
//            }
//
////            protected void populateViewHolder(UsersViewHolder viewHolder, Users model, int position){
////                viewHolder.setDetails(getApplicationContext(),model.getName(),model.getStatus(),model.getImage());
////            }
//
//    };
//        rvResultList.setAdapter(firebaseRecyclerAdapter);
//    }
//
//    // View holder class
//    public static class UsersViewHolder extends RecyclerView.ViewHolder{
//        View mView;
//
//        public UsersViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            mView = itemView;
//        }
//        public void setDetails(Context ctx, String userName, String userStatus, String userImage){
//            TextView user_name = mView.findViewById(R.id.tvName);
//            TextView user_status = mView.findViewById(R.id.tvStatus);
//            ImageView user_image = mView.findViewById(R.id.ivUsers);
//            user_name.setText(userName);
//            user_status.setText(userStatus);
//
//            Glide.with(ctx).load(userImage).into(user_image);
//        }
//
//    }
}

