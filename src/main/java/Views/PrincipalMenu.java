package Views;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PrincipalMenu extends JFrame{
    private JButton newGameButton;
    private JPanel MainPanel;
    private JButton mediumButton;
    private JButton hardButton;
    private JButton easyImportButton;
    private JButton mediumImportButton;
    private JButton hardImportButton;

    public PrincipalMenu() {
        setContentPane(MainPanel);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);

        newGameButton.addActionListener(this::newGameListener);
    }

    private void newGameListener(ActionEvent actionEvent) {
        System.out.println("Click on newGameListener");

        this.setVisible(false);

        GameWindow gameWindow = new GameWindow(9);
        gameWindow.setLocationRelativeTo(this);
        gameWindow.setVisible(true);

        dispose();
    }

    public static void main(String[] args) {
        new PrincipalMenu();
    }
}
