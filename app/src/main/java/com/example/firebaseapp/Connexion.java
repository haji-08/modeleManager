package com.example.firebaseapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firebaseapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class Connexion extends Fragment {

    private View view;
    private EditText login;
    private EditText password;
    private Button cnx_button;
    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;
    public Connexion() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_connexion, container, false);
        this.view = inflater.inflate(R.layout.fragment_connexion, container, false);

        this.login = (EditText)view.findViewById(R.id.login);
        this.password = (EditText)view.findViewById(R.id.password);
        this.cnx_button = (Button)view.findViewById(R.id.connexion);

        this.mAuth = FirebaseAuth.getInstance();


        this.cnx_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(),"Connexion en cours", Toast.LENGTH_LONG);
                startLogin();
            }
        });

        this.mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null) {
                    startActivity(new Intent(view.getContext(), ModeleActivity.class));
                }
            }
        };

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        this.mAuth.addAuthStateListener(this.mAuthListener);
    }

    private void startLogin() {
        final String email = this.login.getText().toString();
        final String password = this.password.getText().toString();

        this.mAuth.signInWithEmailAndPassword(email + "@tech-model.sn", password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                    if(task.isSuccessful()) {
                        startActivity(new Intent(view.getContext(), ModeleActivity.class));
                    } else {

                        Toast.makeText(view.getContext(),"Une erreur est survenu"+task.getException().getMessage()  ,Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(view.getContext(),"Veuillez saisir vos informations",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

}
