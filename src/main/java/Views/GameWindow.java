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

    public GameWindow(int nLines, Difficulty difficulty){
        setContentPane(MainPanel);

        GamePanel.setLayout(new GridBagLayout());

        //Set config for lines
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridy = 0;

//        gbc.gridx=0;

//        GridBagConstraints verticalGbc = new GridBagConstraints();
//        verticalGbc.fill = GridBagConstraints.BOTH;
//        verticalGbc.weightx = 1;
//        verticalGbc.weighty = 1;
//        verticalGbc.gridx = 0;

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

        textFields = new JTextField[nLines*nLines];

        SquareMatrix squareMatrix = new SquareMatrix(difficulty, 3);
        int[][] matrix = squareMatrix.getMatrix();

        //Draw matrix
        for (int i = 0; i < nLines; i++) { //rows
            gbc.gridy++;

//            gbc.gridy=i;
            for (int j = 0; j < nLines; j++) { //columns
//                gbc.gridx++;

                gbc.gridx = j;

                JTextField textField = new JTextField(Integer.toString(matrix[i][j]));
                textField.setHorizontalAlignment(SwingConstants.CENTER); //center text
                textFields[nLines*i + j] = textField;
                GamePanel.add(textField, gbc);

                if(difficulty == Difficulty.EASY) {
                    if ((j + 1) % 3 == 0 && j<nLines-1) {
                        System.out.println(" Vertical Split");
                        verticalSplit.gridx = j+1;
                        verticalSplit.gridy = gbc.gridy;
                        JPanel separator = new VerticalSplit(j);
                        GamePanel.add(separator, verticalSplit);
                    }
                }
            }

            if(difficulty == Difficulty.EASY) {
                if ((i + 1) % 3 == 0 && i<nLines-1) {
                    System.out.println("Split");
                    horizontalSplit.gridy = gbc.gridy + 1;
                    gbc.gridy+=2;
//                    JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
                    JPanel separator = new HorizontalSplit();
                    GamePanel.add(separator, horizontalSplit);
                }
            }
        }

        pack();
    }
}
