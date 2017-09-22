package com.example.akil.onusondhan;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MissingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MissingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button postMissingButton;
    private FirebaseAuth firebaseAuth;


    public MissingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MissingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MissingFragment newInstance(String param1, String param2) {
        MissingFragment fragment = new MissingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_missing, container, false);
        postMissingButton = (Button)view.findViewById(R.id.post1);
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
        return view;
    }

}
