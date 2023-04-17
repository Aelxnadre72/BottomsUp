package com.mygdx.bottomsup;

import java.util.List;

public class AndroidInterfaceClass implements FireBaseInterface{
    AndroidLauncher aL = new AndroidLauncher();
    @Override
    public String hostLobby(String code, String name, String blockTower) {
        return aL.hostLobby(code, name, blockTower);
    }

    @Override
    public int joinLobby(String code, String name, String blockTower) {
        return aL.joinLobby(code, name, blockTower);
    }

    @Override
    public void endGame() {
        aL.endGame();
    }

    @Override
    public void hitBlock(String id, String blockTower) {
        aL.hitBlock(id, blockTower);
    }

    @Override
    public List<String> updateBlockTowers() {
        return aL.updateBlockTowers();
    }

    @Override
    public List<String> getResults() {
        return aL.getResults();
    }

    @Override
    public void updateHighscore(String time) {
        aL.updateHighscore(time);
    }
}
