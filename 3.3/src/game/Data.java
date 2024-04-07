package game;

public class Data {
    private static Player loginPlayer;

    public static Player getLoginPlayer() {
        return loginPlayer;
    }

    public static void setLoginPlayer(Player loginPlayer) {
        Data.loginPlayer = loginPlayer;
    }
}
