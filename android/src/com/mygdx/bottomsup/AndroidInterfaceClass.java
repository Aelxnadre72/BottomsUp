package com.mygdx.bottomsup;

import java.util.List;

public class AndroidInterfaceClass implements FireBaseInterface{
    @Override
    public String hostLobby(String name, String blockTower) {
        return null;
    }

    @Override
    public int joinLobby(String code, String name, String blockTower) {
        return 0;
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
