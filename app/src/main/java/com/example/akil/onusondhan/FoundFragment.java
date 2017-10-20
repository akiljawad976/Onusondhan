package com.example.akil.onusondhan;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;



public class FoundFragment extends Fragment {

    private Button postFoundButton;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    List<Found> imgList;
    private ListView lv;
    private FoundAdapter adapter;
    private ProgressDialog progressDialog;


    public FoundFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_found, container, false);
        postFoundButton = (Button)view.findViewById(R.id.post2);
        firebaseAuth = FirebaseAuth.getInstance();
        postFoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(firebaseAuth.getCurrentUser()!=null){
                    Intent post = new Intent(getActivity(),FoundProfile.class);
                    startActivity(post);
                }else{
                    Intent post = new Intent(getActivity(),SignInActivity.class);
                    startActivity(post);
                    Toast.makeText(getActivity(), "Please Signin First or Signup to Post", Toast.LENGTH_SHORT).show();
                }
            }
        });
        imgList = new ArrayList<>();
        lv = (ListView) view.findViewById(R.id.lv1);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        databaseReference = FirebaseDatabase.getInstance().getReference("Found");
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(getActivity(), FoundPerson.class);
//                startActivity(intent);
//            }
//        });
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot Snapshot :dataSnapshot.getChildren())
                {
                    Found img = Snapshot.getValue(Found.class);
                    imgList.add(img);
                }

                FoundAdapter adapter = new FoundAdapter(getActivity(),imgList);
                lv.setAdapter(adapter);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });
    }

}
