package com.mygdx.bottomsup;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AndroidInterfaceClass implements FireBaseInterface{
    FirebaseDatabase database;
    DatabaseReference myRef;

    String lobbyId;

    public AndroidInterfaceClass() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
    }
    @Override
    public void hostLobby(String name, String blockTower) {
        myRef.child("lobbies").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    long lobbyNum = task.getResult().getChildrenCount();
                    handleId(task);
                    myRef.child("lobbies").child(String.valueOf(lobbyNum+1)).child("1");
                    myRef.child("lobbies").child(String.valueOf(lobbyNum+1)).child("1").child("name").setValue(name);
                    myRef.child("lobbies").child(String.valueOf(lobbyNum+1)).child("1").child("blockTower").setValue(blockTower);
                }
            }
        });
    }

    private void handleId(Task<DataSnapshot> task) {
        this.lobbyId = String.valueOf(task.getResult().getChildrenCount()+1);
        System.out.println(lobbyId);
    }

    @Override
    public String joinLobby(String code, String name, String blockTower) {
        long[] lobbyId = new long[1];

        myRef.child("lobbies").child(code).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    lobbyId[0] = task.getResult().getChildrenCount();
                    myRef.child("lobbies").child(code).child(String.valueOf(lobbyId[0]+1)).child("name").setValue(name);
                    myRef.child("lobbies").child(code).child(String.valueOf(lobbyId[0]+1)).child("blockTower").setValue(blockTower);
                }
            }
        });
        return String.valueOf(lobbyId[0]+1);
    }

    @Override
    public void endGame() {

        myRef.child("lobbies").child("lobbyCode").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            long[] id = new long[1];
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    id[0] = task.getResult().getChildrenCount();
                    if (id[0] == 1000) {
                        myRef.child("lobbies").removeValue();
                        return;
                    }
                    for(int i = 1; i <= id[0]; i++) {
                        myRef.child("lobbies").child("lobbyCode").child(String.valueOf(i)).setValue("");
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

    @Override
    public String getLobbyCode() {
        return lobbyId;
    }
}
