package Views;

import Classes.Difficulty;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PrincipalMenu extends JFrame{
    private JPanel MainPanel;
    private JButton easyButton;
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
        setResizable(false);

        easyButton.addActionListener(this::newGameEasyListener);
        mediumButton.addActionListener(this::newGameMediumListener);
    }

    private void newGameMediumListener(ActionEvent actionEvent) {
        System.out.println("Click on newGameMediumListener");

        GameWindow gameWindow = new GameWindow(Difficulty.MEDIUM);
        gameWindow.setLocationRelativeTo(this);
        gameWindow.setVisible(true);

        this.setVisible(false);
        dispose();
    }

    private void newGameEasyListener(ActionEvent actionEvent) {
        System.out.println("Click on newGameEasyListener");

        GameWindow gameWindow = new GameWindow(Difficulty.EASY);
        gameWindow.setLocationRelativeTo(this);
        gameWindow.setVisible(true);

        this.setVisible(false);
        dispose();
    }

    public static void main(String[] args) {
        new PrincipalMenu();
    }
}
