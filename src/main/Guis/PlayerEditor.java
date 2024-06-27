import javax.swing.*;
import java.awt.*;

public class PlayerEditor extends JFrame {
    private JTextField nameField;
    private JTextField positionField;
    private Player player;
    private Team team;

    public PlayerEditor(Team team, Player player) {
        this.team = team;
        this.player = player;
        setTitle("Editar Jugador: " + player.getName());
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initializeComponents();
    }

    private void initializeComponents() {
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Nombre:"));
        nameField = new JTextField(player.getName());
        add(nameField);

        add(new JLabel("Posición:"));
        positionField = new JTextField(player.getPosition().toString());
        add(positionField);

        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(e -> saveChanges());
        add(saveButton);
    }

    private void saveChanges() {
        player.setName(nameField.getText());
        player.setPosition(Position.valueOf(positionField.getText().toUpperCase()));
        DataPlayer.savePlayerData(player);
        dispose();
    }
}