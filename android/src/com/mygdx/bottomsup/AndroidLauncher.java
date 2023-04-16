package com.mygdx.bottomsup;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

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

		players.child("123");
		players.child("123").child("1").child("name").setValue("Einar 2");
		players.child("123").child("1").child("blockTower").setValue("1,2,1,4,1");
		players.child("123").child("1").child("time").setValue("0");

		DatabaseReference highscores = database.getReference("highscores");
		highscores.setValue("");
		highscores.child("16473").child("name").setValue("Einar 2");

		hostLobby("Martine", "1,2,3,1,2");
		joinLobby("1234", "Hang Celin", "3,2,4,1");
		hitBlock("1", "1,1,1,1");
	}

	@Override
	public String hostLobby(String name, String blockTower) {
		String lobbyNr = "1234"; //Dette tallet må leses ut

		lobbyCode = lobbyNr;
		DatabaseReference lobbies = database.getReference("lobbies");
		lobbies.child(lobbyNr);
		DatabaseReference player = lobbies.child(lobbyNr).child("1");
		//Log.i("Lobbies.get(): ", "hei einar"+lobbies.getDatabase() + lobbies.get().getResult());
		player.child("name").setValue(name);
		player.child("blockTower").setValue(blockTower);
		player.child("time").setValue("0");


		return lobbyNr;
	}

	@Override
	public int joinLobby(String code, String name, String blockTower) {
		int playerNr = 2; //Dette tallet må leses ut

		lobbyCode = code;
		DatabaseReference player = database.getReference("lobbies/"+code+"/"+playerNr);
		//DatabaseReference player = lobbies.child(code).child(""+playerNr);
		player.child("name").setValue(name);
		player.child("blockTower").setValue(blockTower);
		player.child("time").setValue("0");

		return playerNr;
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
