import java.util.ArrayList;
import java.util.List;

public class Team {
    private final int id;
    private final String name;
    private final int ranking;
    private final String flagPath;
    private final List<Player> players;

    public Team(int id, String name, int ranking, String flagPath) {
        this.id = id;
        this.name = name;
        this.ranking = ranking;
        this.flagPath = flagPath;
        this.players = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRanking() {
        return ranking;
    }

    public String getFlagPath() {
        return flagPath;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Player getPlayerByNumber(int number) {
        for (Player player : players) {
            if (player.getNumber() == number) {
                return player;
            }
        }
        return null;
    }

    public void removePlayer(int number) {
        players.removeIf(player -> player.getNumber() == number);
    }
}