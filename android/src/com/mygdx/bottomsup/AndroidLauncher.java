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

	private static DatabaseReference database;
	private String lobbyCode;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new BottomsUp(new AndroidInterfaceClass()), config);
		database = FirebaseDatabase.getInstance().getReference();
	}

	@Override
	public String hostLobby(String name, String blockTower) {
		final long[] lobbyId = new long[1];
		database.child("lobbies").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
			@Override
			public void onComplete(@NonNull Task<DataSnapshot> task) {
				if (!task.isSuccessful()) {
					Log.e("firebase", "Error getting data", task.getException());
				}
				else {
					lobbyId[0] = task.getResult().getChildrenCount();
					lobbyCode = String.valueOf(lobbyId[0]+1);
				}
				database.child("lobbies").child(String.valueOf(lobbyId[0]+1)).child("1");
				database.child("lobbies").child(String.valueOf(lobbyId[0]+1)).child("1").child("name").setValue(name);
				database.child("lobbies").child(String.valueOf(lobbyId[0]+1)).child("1").child("blockTower").setValue(blockTower);
			}
		});
		return lobbyCode;
	}

	@Override
	public String joinLobby(String code, String name, String blockTower) {
		final long[] id = new long[1];
		final String[] lobbyCode = new String[1];

		database.child("lobbies").child(code).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

			@Override
			public void onComplete(@NonNull Task<DataSnapshot> task) {
				if (!task.isSuccessful()) {
					Log.e("firebase", "Error getting data", task.getException());
				}
				else {
					id[0] = task.getResult().getChildrenCount();
					lobbyCode[0] = String.valueOf(id[0]+1);
					Log.d("players before joining", String.valueOf(task.getResult().getChildrenCount()));
					database.child("lobbies").child(code).child(String.valueOf(id[0]+1)).child("name").setValue(name);
					database.child("lobbies").child(code).child(String.valueOf(id[0]+1)).child("blockTower").setValue(blockTower);
				}
			}
		});
		return lobbyCode[0];
	}

	@Override
	public void endGame() {

		database.child("lobbies").child("lobbyCode").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
			final long[] id = new long[1];
			@Override
			public void onComplete(@NonNull Task<DataSnapshot> task) {
				if (!task.isSuccessful()) {
					Log.e("firebase", "Error getting data", task.getException());
				}
				else {
					id[0] = task.getResult().getChildrenCount();
					if (id[0] == 1000) {
						database.child("lobbies").removeValue();
						return;
					}
					for(int i = 1; i <= id[0]; i++) {
						database.child("lobbies").child("lobbyCode").child(String.valueOf(i)).setValue("");
					}
				}
			}
		});
	}

	@Override
	public void updateBlockTower(String id, String blockTower) {

	}

	@Override
	public List<String> updateOthers() {

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
