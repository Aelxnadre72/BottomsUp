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
		initialize(new BottomsUp(new AndroidInterfaceClass()), config);
		FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference players = database.getReference("lobbies");
		players.setValue("");
		players.child("123");
		players.child("123").child("1").child("name").setValue("Einar");
		players.child("123").child("1").child("blockTower").setValue("1,2,1,4,1");
		players.child("123").child("1").child("time").setValue("0");

		DatabaseReference highscores = database.getReference("highscores");
		highscores.setValue("");
		highscores.child("16473").child("name").setValue("Einar");
	}
}
