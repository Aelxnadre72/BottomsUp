package com.mygdx.bottomsup;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AndroidLauncher extends AndroidApplication implements FireBaseInterface {

	String lobbyCode;
	FirebaseDatabase database;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new BottomsUp(new AndroidInterfaceClass()), config);
		database = FirebaseDatabase.getInstance();



		DatabaseReference players = database.getReference("lobbies");
		players.setValue("");

		players.child("1");
		players.child("1").child("1").child("name").setValue("Einar 2");
		players.child("1").child("1").child("blockTower").setValue("1,2,1,4,1");
		players.child("1").child("1").child("time").setValue("0");

		DatabaseReference highscores = database.getReference("highscores");
		highscores.setValue("");
		highscores.child("16473").child("name").setValue("Einar 2");

		joinLobby("1", "Hang Celin", "3,2,4,1");

		hostLobby("Martine", "1,2,3,1,2");

		hostLobby("Elise", "4,4,4,1,2");

		joinLobby("12", "Hang Celin", "3,2,4,1");
		joinLobby("12", "Emma", "1,3,3,3");
		hitBlock("1", "1,1,1,1");
	}

	@Override
	public String hostLobby(String name, String blockTower) {
		final String[] lobbyNr = new String[1]; //Dette tallet må leses ut
		lobbyNr[0] = "12";

		database.getReference().child("lobbies").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
			@Override
			public void onComplete(@NonNull Task<DataSnapshot> task) {
				if (!task.isSuccessful()) {
					Log.e("firebase", "Error getting data", task.getException());
				}
				else {
					lobbyNr[0] = "" + task.getResult().getChildrenCount();

					String info = "" + task.getResult().getValue();
					//Log.i("Faen shitface pikk", "" + task.getResult().getValue());
					//lobbyNr[0] = "" + info.split(Pattern.quote("[")).length;
					//Log.i("jaja", lobbyNr[0]);
				}
			}
		});

		lobbyCode = lobbyNr[0];

		DatabaseReference lobbies = database.getReference("lobbies");
		lobbies.child(lobbyNr[0]);
		DatabaseReference player = lobbies.child(lobbyNr[0]).child("1");
		//Log.i("Lobbies.get(): ", "hei einar"+lobbies.getDatabase() + lobbies.get().getResult());
		player.child("name").setValue(name);
		player.child("blockTower").setValue(blockTower);
		player.child("time").setValue("0");


		return lobbyNr[0];
	}

	@Override
	public int joinLobby(String code, String name, String blockTower) {
		final int[] playerNr = new int[1];
		database.getReference().child("lobbies").child(code).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
			@Override
			public void onComplete(@NonNull Task<DataSnapshot> task) {
				if (!task.isSuccessful()) {
					Log.e("firebase", "Error getting data", task.getException());
				}
				else {
					playerNr[0] = (int) task.getResult().getChildrenCount();
					//playerNr[0] = info.split(Pattern.quote("{")).length;
				}
			}
		});

		lobbyCode = code;
		Log.i("jaja2",""+ playerNr[0]);
		DatabaseReference player = database.getReference("lobbies/"+code+"/"+ playerNr[0]);
		player.child("name").setValue(name);
		player.child("blockTower").setValue(blockTower);
		player.child("time").setValue("0");

		return playerNr[0];
	}

	@Override
	public void endGame() {
		DatabaseReference lobby = database.getReference("lobbies/"+lobbyCode);
		lobby.removeValue();

		lobbyCode = "";
	}

	@Override
	public void hitBlock(String id, String blockTower) {
		DatabaseReference player = database.getReference("lobbies/"+lobbyCode+"/"+id);
		player.child("blockTower").setValue(blockTower);
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
		//les gamle highscores

	}
}
