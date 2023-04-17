package com.mygdx.bottomsup;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AndroidLauncher extends AndroidApplication implements FireBaseInterface {

	String lobbyCode;
	private DatabaseReference database;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new BottomsUp(new AndroidInterfaceClass()), config);
		database = FirebaseDatabase.getInstance().getReference();



		//DatabaseReference players = database.getReference("lobbies");
		//players.setValue("");

		//players.child("1");
		//players.child("1").child("1").child("name").setValue("Einar 2");
		//players.child("1").child("1").child("blockTower").setValue("1,2,1,4,1");
		//players.child("1").child("1").child("time").setValue("0");

		//DatabaseReference highscores = database.getReference("highscores");
		//highscores.setValue("");
		//highscores.child("16473").child("name").setValue("Einar 2");

		//joinLobby("1", "Hang Celin", "3,2,4,1");

		//hostLobby("Martine", "1,2,3,1,2");
		hostLobby("123", "Elise", "4,4,4,1,2");
		joinLobby("123", "Hang Celin", "3,2,4,1");
		joinLobby("123", "H", "1,2,3,4");
		//joinLobby("12", "Emma", "1,3,3,3");
		//hitBlock("1", "1,1,1,1");
	}

	@Override
	public String hostLobby(String code, String name, String blockTower) {
		final long[] id = new long[1];
		database.child("lobbies").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

			@Override
			public void onComplete(@NonNull Task<DataSnapshot> task) {
				if (!task.isSuccessful()) {
					Log.e("firebase", "Error getting data", task.getException());
				}
				else {
					id[0] = Long.parseLong(String.valueOf(task.getResult().getChildrenCount()));
				}
				database.child("lobbies").child(code).child(String.valueOf(id[0]+1)).child("name").setValue(name);
				database.child("lobbies").child(code).child(String.valueOf(id[0]+1)).child("blockTower").setValue(blockTower);
			}
		});
		return "true";
	}

	@Override
	public int joinLobby(String code, String name, String blockTower) {
		final long[] id = new long[1];
		database.child("lobbies").child(code).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

			@Override
			public void onComplete(@NonNull Task<DataSnapshot> task) {
				if (!task.isSuccessful()) {
					Log.e("firebase", "Error getting data", task.getException());
				}
				else {
					id[0] = Long.parseLong(String.valueOf(task.getResult().getChildrenCount()));
					Log.d("players before joining", String.valueOf(task.getResult().getChildrenCount()));
					database.child("lobbies").child(code).child(String.valueOf(id[0]+1)).child("name").setValue(name);
					database.child("lobbies").child(code).child(String.valueOf(id[0]+1)).child("blockTower").setValue(blockTower);
				}
			}
		});
		return Integer.parseInt(code);
	}

	@Override
	public void endGame() {

	}

	@Override
	public void hitBlock(String id, String blockTower) {

	}

	@Override
	public List<String> updateBlockTowers() {

		return null;
	}

	@Override
	public List<String> getResults() {
		return null;
	}

	@Override
	public void updateHighscore(String time) {

	}
}
