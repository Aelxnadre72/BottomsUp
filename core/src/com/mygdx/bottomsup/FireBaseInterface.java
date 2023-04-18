package com.mygdx.bottomsup;

import java.util.List;

public interface FireBaseInterface {
    public void hostLobby(String name, String blockTower);
    public String joinLobby(String code, String name, String blockTower);
    public void endGame();
    public void updateBlockTower(String id, String blockTower);
    public List<String> updateOthers();

    public List<String> getResults();

    public void updateHighscore(String time);

    public String getLobbyCode();

    public void updatePlayerList(String code);

    public List<String> getPlayerList();

}
