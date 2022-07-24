package Views;

import Classes.Difficulty;
import Classes.SquareMatrix;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private JPanel MainPanel;
    private JPanel GamePanel;

    private JTextField[] textFields;

    public GameWindow(int nLines){
        setContentPane(MainPanel);
        pack();

        GamePanel.setLayout(new GridLayout(nLines, nLines));

        textFields = new JTextField[nLines*nLines];

        SquareMatrix squareMatrix = new SquareMatrix(Difficulty.EASY, 3);
        int[][] matrix = squareMatrix.getMatrix();

        for (int i = 0; i < nLines; i++) {
            for (int j = 0; j < nLines; j++) {
                JTextField textField = new JTextField(Integer.toString(matrix[i][j]));
                textField.setHorizontalAlignment(SwingConstants.CENTER);
                textFields[nLines*i + j] = textField;
                GamePanel.add(textField);
            }
        }
    }
}
