package com.pipe.www.pipe.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pipe.www.pipe.R;
import com.pipe.www.pipe.adapter.GeneralForumRecycAdapter;
import com.pipe.www.pipe.model.IsContain;
import com.pipe.www.pipe.model.Post;

import java.util.ArrayList;
import java.util.HashSet;

public class GeneralForumFragment extends Fragment {

    Context context;
    private RecyclerView generalrecycler;
    private FirebaseUser currentuser;
    private DatabaseReference postbase;
    private ArrayList<Post> postarray;
    private IsContain iscontain;
    private GeneralForumRecycAdapter forumRecycAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_general_forum,container,false);


       generalrecycler = (RecyclerView)v.findViewById(R.id.general_forum_recycler);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        postbase  = FirebaseDatabase.getInstance().getReference().child(context.getString(R.string.post));
        currentuser = FirebaseAuth.getInstance().getCurrentUser();


        postarray = new ArrayList<>();
        iscontain = new IsContain();

        forumRecycAdapter = new GeneralForumRecycAdapter(context,postarray);
        generalrecycler.setAdapter(forumRecycAdapter);


        fillpost();









    }

    private void fillpost(){

        postbase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    Post post = (Post) snapshot.getValue();

                    if(!iscontain.isPostContain(postarray,post)){
                        postarray.add(post);
                        forumRecycAdapter.notifyDataSetChanged();

                    }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }



}
