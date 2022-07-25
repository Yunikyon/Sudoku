package Views;

import Classes.Difficulty;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PrincipalMenu extends JFrame{
    private JButton easyButton;
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

        easyButton.addActionListener(this::newGameListener);
    }

    private void newGameListener(ActionEvent actionEvent) {
        System.out.println("Click on newGameListener");

        GameWindow gameWindow = new GameWindow(9, Difficulty.EASY);
        gameWindow.setLocationRelativeTo(this);
        gameWindow.setVisible(true);

        this.setVisible(false);
        dispose();
    }

    public static void main(String[] args) {
        new PrincipalMenu();
    }
}
