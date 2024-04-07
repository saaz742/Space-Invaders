package game;

import java.util.ArrayList;

public class Player {
    private static ArrayList<Player> allPlayers = new ArrayList<Player>();
    private String name;
    private int score;

    public Player(String SignUpUsername) {
        name=SignUpUsername;
        allPlayers.add(this);
    }

    public static ArrayList<String> score(){
        ArrayList<String> allScores = new ArrayList<>();
        for(Player player:allPlayers){
            String scoreBoard = player.name + " " + player.score;
            allScores.add(scoreBoard);
        }
        return allScores;
    }
    public static Player getPlayerByName(String name) {
        if (name != null) {
            for (Player player : allPlayers) {
                if (player.name.equals(name)) return player;
            }
        }
        return null;
    }

    public static boolean isTherePlayerWithName(String name) {
        if (name != null) {
            for (Player player : allPlayers) {
                if (player.name.equals(name)) return true;
            }
        }
        return false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
