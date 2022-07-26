package Views;

import Classes.Difficulty;
import Classes.HorizontalSplit;
import Classes.SquareMatrix;
import Classes.VerticalSplit;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class GameWindow extends JFrame {
    private JPanel MainPanel;
    private JPanel GamePanel;

    private JTextField[] textFields;
    private JLabel[] labels;

    public GameWindow(Difficulty difficulty){
        setContentPane(MainPanel);
        setResizable(false);

        initPuzzlePanel(difficulty);

        pack();
    }

    private void initPuzzlePanel(Difficulty difficulty) {
        GamePanel.setLayout(new GridBagLayout());

        int nLines = 9;

        // Create the sudoku level
        SquareMatrix squareMatrix = new SquareMatrix(difficulty, 3);
        int[][] matrix = squareMatrix.getUnsolvedPuzzle();

//        textFields = new JTextField[squareMatrix.getRemovedNumbers()];
        textFields = new JTextField[nLines*nLines];

//        labels = new JLabel[(nLines*nLines) - squareMatrix.getRemovedNumbers()];
        labels = new JLabel[nLines*nLines];

        // Set configurations for split lines
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridy = 0;

        GridBagConstraints horizontalSplit = new GridBagConstraints();
        horizontalSplit.fill = GridBagConstraints.BOTH;
        horizontalSplit.weightx = 1;
        horizontalSplit.gridx = 0;
        horizontalSplit.gridwidth = GridBagConstraints.REMAINDER;

        GridBagConstraints verticalSplit = new GridBagConstraints();
        verticalSplit.fill = GridBagConstraints.BOTH;
        verticalSplit.weighty = 1;
        verticalSplit.gridy = 0;
        verticalSplit.gridheight = GridBagConstraints.REMAINDER;

        //Draw matrix
        for (int rows = 0; rows < nLines; rows++) { //rows
            gbc.gridy++;

            for (int columns = 0; columns < nLines; columns++) { //columns

                gbc.gridx = columns;

                // Create a textField
                JTextField textField = new JTextField("");
                textField.setHorizontalAlignment(SwingConstants.CENTER); //center text
                textField.setBorder(BorderFactory.createLineBorder(Color.gray));
                textFields[nLines * rows + columns] = textField;

                JLabel label = new JLabel(Integer.toString(matrix[rows][columns]));
                label.setHorizontalAlignment(SwingConstants.CENTER); //center text
                label.setBorder(BorderFactory.createLineBorder(Color.gray));
//                label.setBackground(Color.pink);
//                label.setOpaque(true);
                labels[nLines * rows + columns] = label;

                if(matrix[rows][columns]==0) {
                    GamePanel.add(textField, gbc);
                }else {
                    GamePanel.add(label, gbc);
                }

                // Draw vertical split lines
                if ((columns + 1) % 3 == 0 && columns<nLines-1) {
                    System.out.println(" Vertical Split");
                    verticalSplit.gridx = columns+1;
                    verticalSplit.gridy = gbc.gridy;
                    JPanel separator = new VerticalSplit(columns);
                    GamePanel.add(separator, verticalSplit);
                }

            }

            // Draw horizontal split lines
            if ((rows + 1) % 3 == 0 && rows<nLines-1) {
                System.out.println("Split");
                horizontalSplit.gridy = gbc.gridy + 1;
                gbc.gridy+=2;
                JPanel separator = new HorizontalSplit();
                GamePanel.add(separator, horizontalSplit);
            }

        }
    }
}
