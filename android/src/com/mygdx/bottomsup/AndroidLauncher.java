package com.mygdx.bottomsup;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new BottomsUp(), config);
		FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference players = database.getReference("players");
		players.setValue("");
		players.child("1");
		players.child("1").child("name").setValue("Einar");
		players.child("1").child("blockTower").setValue("1,2,1,4,1");
		players.child("1").child("time").setValue("0");

		players.child("2");
		players.child("2").child("name").setValue("Jan Adrian");
		players.child("2").child("blockTower").setValue("1,2,1,3,4");
		players.child("2").child("time").setValue("0");

		DatabaseReference highscores = database.getReference("highscores");
		highscores.setValue("");
		highscores.child("16473").child("name").setValue("Einar");
	}
}
