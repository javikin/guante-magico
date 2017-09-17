package com.thot.guantemagico;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thot.domain.Guante;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements ValueEventListener {

	private static final String TAG = MainActivity.class.getSimpleName();
	private TextView mContentTextView;
	private FloatingActionButton buttonClear;
	private List<String> words = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference myRef = database.getReference("data");
		mContentTextView = (TextView) findViewById(R.id.mContentTextView);
		buttonClear = (FloatingActionButton) findViewById(R.id.buttonClear);
		myRef.addValueEventListener(this);

		buttonClear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				words.clear();
				mContentTextView.setText(paragraph());
			}
		});

	}

	@Override
	public void onDataChange(DataSnapshot dataSnapshot) {
		if (dataSnapshot.getValue() == null ) return;
		JSONObject json = new JSONObject((Map)dataSnapshot.getValue());
		Guante guante = Guante.fromJson(json.toString());
		switch (guante.getAction()){
			case "insertar":
				if(!guante.getWord().isEmpty())
					words.add(guante.getWord());
				break;
			case "salto":
				words.add("\\n");
				break;
			case "borrar":
				words.remove(words.size()-1);
				break;
			default:
				return;
		}
		mContentTextView.setText(paragraph());
	}

	private String paragraph(){
		String paragraph = "";
		for(String word:words){
			paragraph = paragraph + " " + word;
		}
		paragraph = paragraph.replace("\\n",System.getProperty("line.separator"));
		return paragraph;
	}

	@Override
	public void onCancelled(DatabaseError error) {
		Log.w(TAG, "Failed to read value.", error.toException());
	}
}
