package com.mygdx.bottomsup;

import java.util.List;

public interface FireBaseInterface {
    public String hostLobby(String name, String blockTower);
    public String joinLobby(String code, String name, String blockTower);
    public void endGame();
    public void updateBlockTower(String code, String playerId, String blockTower);
    public List<List<Integer>> updateOthers(String code, String playerId);

    public List<String> getResults(String code);

    public void setResult(String code, String playerId, String value);
    public void hostStartGame(String code);

    public void updateHighscore(String time);

    public List<String> updatePlayerList(String code);

}
