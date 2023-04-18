package com.mygdx.bottomsup;

import java.util.List;

public class CoreInterfaceClass implements FireBaseInterface{
    @Override
    public void hostLobby(String name, String blockTower) {

    }

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
    public String getLobbyCode() {
        return null;
    }

    @Override
    public void updatePlayerList(String code) {

    }

    @Override
    public List<String> getPlayerList() {
        return null;
    }
}
