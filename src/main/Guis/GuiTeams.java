import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class GuiTeams extends JFrame {
    private JComboBox<String> teamComboBox;
    private JTextField rankingField;
    private JLabel flagLabel;
    private final List<Team> teams;

    public GuiTeams(List<Team> teams) {
        this.teams = teams;
        setTitle("Teams");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initializeComponents();
    }

    private void initializeComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Choose team:"), gbc);

        teamComboBox = new JComboBox<>();
        for (Team team : teams) {
            teamComboBox.addItem(team.getName());
        }
        teamComboBox.addActionListener(e -> updateTeamInfo());

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(teamComboBox, gbc);

        flagLabel = new JLabel();
        gbc.gridx = 2;
        gbc.gridy = 0;
        add(flagLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Ranking FIFA:"), gbc);

        rankingField = new JTextField(10);
        rankingField.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(rankingField, gbc);

        JButton playersButton = new JButton("Players");
        playersButton.addActionListener(e -> {
            int selectedIndex = teamComboBox.getSelectedIndex();
            if (selectedIndex != -1) {
                new GuiPlayers(teams.get(selectedIndex)).setVisible(true);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(playersButton, gbc);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> dispose());
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(exitButton, gbc);

        updateTeamInfo();
    }

    private void updateTeamInfo() {
        int selectedIndex = teamComboBox.getSelectedIndex();
        if (selectedIndex != -1) {
            Team selectedTeam = teams.get(selectedIndex);
            rankingField.setText(String.valueOf(selectedTeam.getRanking()));
            flagLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("flags/" + selectedTeam.getFlagPath()))));
        }
    }
}