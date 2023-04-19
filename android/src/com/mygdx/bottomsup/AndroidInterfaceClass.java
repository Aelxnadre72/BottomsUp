package com.mygdx.bottomsup;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AndroidInterfaceClass implements FireBaseInterface{
    FirebaseDatabase database;
    DatabaseReference myRef;

    String lobbyId;
    long playerCount = 0;

    List<String> playerList;

    List<List<Integer>> opponentTowers;

    public AndroidInterfaceClass() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
    }
    @Override
    public String hostLobby(String name, String blockTower) {
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

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("her:");
        System.out.println(lobbyId);
        return lobbyId;
    }

    private void handleId(Task<DataSnapshot> task) {
        this.lobbyId = String.valueOf(task.getResult().getChildrenCount()+1);
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
                    handleJoinSuccess(task);
                    if(lobbyId[0] < 4) {
                        myRef.child("lobbies").child(code).child(String.valueOf(lobbyId[0]+1)).child("name").setValue(name);
                        myRef.child("lobbies").child(code).child(String.valueOf(lobbyId[0]+1)).child("blockTower").setValue(blockTower);
                    }
                }
            }
        });

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if(playerCount > 3) {
            return "false";
        }
        else {
            return "true";
        }
    }

    private void handleJoinSuccess(Task<DataSnapshot> task) {
        this.playerCount = task.getResult().getChildrenCount();
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
    public void updateBlockTower(String code, String playerId, String blockTower) {
        myRef.child("lobbies").child(code).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    myRef.child("lobbies").child(code).child(playerId).child("blockTower").setValue(blockTower);
                }
            }
        });
    }

    @Override
    public List<List<Integer>> updateOthers(String code, String playerId) {
        myRef.child("lobbies").child(code).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    getOpponentTowers(task, playerId);
                }
            }
        });

        try {
            TimeUnit.MILLISECONDS.sleep(333);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return opponentTowers;
    }

    private void getOpponentTowers(Task<DataSnapshot> task, String playerId) {
        List<List<Integer>> towers = new ArrayList<>();
        long playerCount = task.getResult().getChildrenCount();
        for(int i = 1; i <= playerCount; i++) {
            if(playerId != String.valueOf(i)) {
                String tower = task.getResult().child(String.valueOf(i)).child("blockTower").getValue().toString();
                tower = tower.replaceAll("[\\D]", "");
                List<Integer> towerList = new ArrayList<>();
                for(int j = 0; j < tower.length(); j++) {
                    towerList.add(Integer.valueOf(tower.charAt(j)));
                }
                towers.add(towerList);
            }
        }
        opponentTowers = towers;
    }


        @Override
    public List<String> getResults() {
        return null;
    }

    @Override
    public void updateHighscore(String time) {
    }

    @Override
    public List<String> updatePlayerList(String code) {
        myRef.child("lobbies").child(code).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    handlePlayerList(task);
                }
            }
        });

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return playerList;
    }

    private void handlePlayerList(Task<DataSnapshot> task) {
        List<String> localPlayers = new ArrayList<>();
        long playerCount = task.getResult().getChildrenCount();
        for(int i = 1; i <= playerCount; i++) {
            String child = task.getResult().child(String.valueOf(i)).child("name").getValue().toString();
            localPlayers.add(child);
        }
        playerList = localPlayers;
    }
}
