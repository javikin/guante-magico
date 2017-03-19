package com.thot.guantemagico;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thot.domain.Guante;

import org.json.JSONObject;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements ValueEventListener {

	private static final String TAG = MainActivity.class.getSimpleName();
	private TextView mContentTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference myRef = database.getReference("user1");
		mContentTextView = (TextView) findViewById(R.id.mContentTextView);
		myRef.addValueEventListener(this);

	}

	@Override
	public void onDataChange(DataSnapshot dataSnapshot) {
		JSONObject json = new JSONObject((Map)dataSnapshot.getValue());
		Guante guante = Guante.fromJson(json.toString());
		mContentTextView.setText(guante.getContent());
	}

	@Override
	public void onCancelled(DatabaseError error) {
		Log.w(TAG, "Failed to read value.", error.toException());
	}
}
