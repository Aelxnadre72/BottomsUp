package com.mygdx.bottomsup;

import java.util.List;

public interface FireBaseInterface {
    public String hostLobby(String name, String blockTower);
    public int joinLobby(String code, String name, String blockTower);
    public void endGame();
    public void hitBlock(String id, String blockTower);
    public List<String> updateBlockTowers();

    public List<String> getResults();

    public void updateHighscore(String time);
}
