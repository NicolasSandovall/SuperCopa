import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataPlayer {
    private static final Logger LOGGER = Logger.getLogger(DataPlayer.class.getName());

    public static List<Team> loadTeams(String filePath) {
        List<Team> teams = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(DataPlayer.class.getClassLoader().getResourceAsStream(filePath))))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int ranking = Integer.parseInt(parts[2]);
                String flagPath = parts[3];
                Team team = new Team(id, name, ranking, flagPath);
                team.getPlayers().addAll(loadPlayers("data/" + name.substring(0, 3).toLowerCase() + ".txt"));
                teams.add(team);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error loading teams from " + filePath, e);
        } catch (NullPointerException e) {
            LOGGER.log(Level.SEVERE, "File not found: " + filePath, e);
        }
        return teams;
    }

    public static List<Player> loadPlayers(String filePath) {
        List<Player> players = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(DataPlayer.class.getClassLoader().getResourceAsStream(filePath))))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                int number = Integer.parseInt(parts[0]);
                String name = parts[1];
                Position position;
                try {
                    position = Position.valueOf(parts[2]);
                } catch (IllegalArgumentException e) {
                    LOGGER.log(Level.WARNING, "Invalid position value: " + parts[2]);
                    continue; // Skip this player entry
                }
                players.add(new Player(number, name, position));
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error loading players from " + filePath, e);
        } catch (NullPointerException e) {
            LOGGER.log(Level.SEVERE, "File not found: " + filePath, e);
        }
        return players;
    }

    public static void savePlayerData(Player player) {
        String filePath = "resources/data/" + player.getName().substring(0, 3).toLowerCase() + ".txt";
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(player.getNumber() + ";")) {
                    lines.add(player.getNumber() + ";" + player.getName() + ";" + player.getPosition());
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading player data from " + filePath, e);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error writing player data to " + filePath, e);
        }
    }
}