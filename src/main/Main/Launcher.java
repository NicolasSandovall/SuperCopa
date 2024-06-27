import java.util.List;

public class Launcher {
    public static void main(String[] args) {
        List<Team> teams = DataPlayer.loadTeams("data/teams.txt");
        GuiTeams guiTeams = new GuiTeams(teams);
        guiTeams.setVisible(true);
    }
}