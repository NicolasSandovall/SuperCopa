import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GuiPlayers extends JFrame {
    private JTable playerTable;
    private final Team team;

    public GuiPlayers(Team team) {
        this.team = team;
        setTitle("Players");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initializeComponents();
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());

        String[] columnNames = {"Number", "Name", "Position"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Player player : team.getPlayers()) {
            model.addRow(new Object[]{player.getNumber(), player.getName(), player.getPosition()});
        }

        playerTable = new JTable(model);
        add(new JScrollPane(playerTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton editButton = new JButton("Edit player");
        editButton.addActionListener(e -> {
            int selectedRow = playerTable.getSelectedRow();
            if (selectedRow != -1) {
                Player selectedPlayer = team.getPlayers().get(selectedRow);
                new PlayerEditor(team, selectedPlayer).setVisible(true);
            }
        });
        buttonPanel.add(editButton);

        JButton saveButton = new JButton("Save changes");
        saveButton.addActionListener(e -> {
            // Save changes logic
        });
        buttonPanel.add(saveButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> dispose());
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}