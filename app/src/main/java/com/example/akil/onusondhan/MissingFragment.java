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



public class MissingFragment extends Fragment {

    private Button postMissingButton;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    List<Missing> imgList1;
    private ListView lvad;
    //private MissingAdapter adapter;
    private ProgressDialog progressDialog;


    public MissingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vie =  inflater.inflate(R.layout.fragment_missing, container, false);
        postMissingButton = (Button)vie.findViewById(R.id.post1);
        firebaseAuth = FirebaseAuth.getInstance();
        postMissingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(firebaseAuth.getCurrentUser() !=null){
                    Intent post = new Intent(getActivity(),MissingProfile.class);
                    startActivity(post);
                }else{
                    Intent post = new Intent(getActivity(),SignInActivity.class);
                    startActivity(post);
                    Toast.makeText(getActivity(), "Please Signin First or Signup to Post", Toast.LENGTH_SHORT).show();
                }
            }
        });
        imgList1 = new ArrayList<>();
        lvad = (ListView) vie.findViewById(R.id.lv2);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        databaseReference = FirebaseDatabase.getInstance().getReference("Missing");
        lvad.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), MissingPerson.class);
                startActivity(intent);
            }
        });

        return vie;
    }

    @Override
    public void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot Snapshot :dataSnapshot.getChildren())
                {
                    Missing img = Snapshot.getValue(Missing.class);
                    imgList1.add(img);
                }

                MissingAdapter adapter = new MissingAdapter(getActivity(),imgList1);
                lvad.setAdapter(adapter);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

    }
}
