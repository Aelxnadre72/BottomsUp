package com.mygdx.bottomsup;

import java.util.List;

public class CoreInterfaceClass implements FireBaseInterface{
    @Override
    public String hostLobby(String name, String blockTower) {return "0"; }

    @Override
    public String joinLobby(String code, String name, String blockTower) {
        return "0";
    }

    @Override
    public void endGame() {

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
    public List<String> updatePlayerList(String code) {return null; }

    }
