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

	private String lobbyCode;
	private DatabaseReference database;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new BottomsUp(new AndroidInterfaceClass()), config);
		database = FirebaseDatabase.getInstance().getReference();
		
		//hostLobby("Martine", "1,2,3,1,2");
		//hostLobby("", "Elise11", "4,4,4,1,2");
		//joinLobby("11", "Hang Celin", "3,2,4,1");
		//joinLobby("14", "Jan Adrian8", "1,2,3,4");
		//joinLobby("12", "Emma", "1,3,3,3");
		//hitBlock("1", "1,1,1,1");
	}

	@Override
	public String hostLobby(String code, String name, String blockTower) {
		final long[] lobbyId = new long[1];
		database.child("lobbies").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
			@Override
			public void onComplete(@NonNull Task<DataSnapshot> task) {
				if (!task.isSuccessful()) {
					Log.e("firebase", "Error getting data", task.getException());
				}
				else {
					lobbyId[0] = task.getResult().getChildrenCount();
				}
				database.child("lobbies").child(String.valueOf(lobbyId[0]+1)).child("1");
				database.child("lobbies").child(String.valueOf(lobbyId[0]+1)).child("1").child("name").setValue(name);
				database.child("lobbies").child(String.valueOf(lobbyId[0]+1)).child("1").child("blockTower").setValue(blockTower);
			}
		});
		lobbyCode = String.valueOf(lobbyId[0]);
		return String.valueOf(lobbyId[0]);
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
		lobbyCode = code;
		return Integer.parseInt(code);
	}

	@Override
	public void endGame() {

		database.child("lobbies").child(lobbyCode).removeValue();

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
