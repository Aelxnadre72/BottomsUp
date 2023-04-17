package com.mygdx.bottomsup;

import java.util.List;

public class AndroidInterfaceClass implements FireBaseInterface{
    AndroidLauncher aL = new AndroidLauncher();
    @Override
    public String hostLobby(String name, String blockTower) {
        return aL.hostLobby(name, blockTower);
    }

    @Override
    public String joinLobby(String code, String name, String blockTower) {
        return aL.joinLobby(code, name, blockTower);
    }

    @Override
    public void endGame() {
        aL.endGame();
    }

    @Override
    public void updateBlockTower(String id, String blockTower) {
        aL.updateBlockTower(id, blockTower);
    }

    @Override
    public List<String> updateOthers() {
        return aL.updateOthers();
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
