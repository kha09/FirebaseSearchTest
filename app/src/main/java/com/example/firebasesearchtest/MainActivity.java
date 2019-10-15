package com.example.firebasesearchtest;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private EditText etSearchField;
    private ImageButton ibSearch;
    private RecyclerView rvResultList;
    private DatabaseReference mUserDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUserDatabase = FirebaseDatabase.getInstance().getReference("Users");
        setupUiViews();
        ibSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseUserSearch();
            }
        });
    }
    private void setupUiViews(){
        etSearchField = findViewById(R.id.etOne);
        ibSearch = findViewById(R.id.ibSearch);
        rvResultList = findViewById(R.id.result_list);
        rvResultList.setHasFixedSize(true);
        rvResultList.setLayoutManager(new LinearLayoutManager(this));

    }

    private void firebaseUserSearch() {
        FirebaseRecyclerOptions<Users> options =
                new FirebaseRecyclerOptions.Builder<Users>()
                        .setQuery(mUserDatabase, Users.class)
                        .build();

        FirebaseRecyclerAdapter<Users, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Users, UsersViewHolder>(options) {

            @NonNull
            @Override
            public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                return null;
            }

            @Override
            protected void onBindViewHolder(@NonNull UsersViewHolder holder, int position, @NonNull Users model) {
                holder.setDetails(model.getName(),model.getStatus(),model.getImage());
            }

//            protected void populateViewHolder(UsersViewHolder viewHolder, Users model, int position){
//                viewHolder.setDetails(model.getName(),model.getStatus(),model.getImage());
//            }

    };
        rvResultList.setAdapter(firebaseRecyclerAdapter);
    }

    // View holder class
    public class UsersViewHolder extends RecyclerView.ViewHolder{
        View mView;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;
        }
        public void setDetails(String userName, String userStatus, String userImage){
            TextView user_name = mView.findViewById(R.id.tvName);
            TextView user_status = mView.findViewById(R.id.tvStatus);
            ImageView user_image = mView.findViewById(R.id.ivUsers);
            user_name.setText(userName);
            user_status.setText(userStatus);

            Glide.with(getApplicationContext()).load(userImage).into(user_image);
        }

    }
}
