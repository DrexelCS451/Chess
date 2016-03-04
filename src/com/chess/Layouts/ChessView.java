package com.chess.Layouts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by tomer on 2/10/16.
 */
public class ChessView {
    public static JPanel getChessView(ActionListener forfeit)
    {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        Label l =new Label("Your Turn\nPlaying Bobby");
        l.setAlignment(Label.CENTER);
        l.setMaximumSize(new Dimension(Integer.MAX_VALUE, l.getMinimumSize().height));
        p.add(l);

        JPanel board = new JPanel();
        board.setLayout(new GridLayout(8, 8));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JPanel square = new JPanel();
                square.setBackground(((i + j) % 2 == 0) ? new Color(50, 50, 50) : new Color(255, 255, 255));
                if(i==0 || i == 1 || i == 6 || i == 7)
                {
                    JLabel piece = new JLabel("♚");
                    piece.setFont(new Font("Serif", Font.BOLD, 40));
                    piece.setForeground(Color.BLACK);
                    square.add(piece);
                }

                board.add(square);
            } 
        }

        p.add(board);

        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.X_AXIS));
        JPanel col1 = new JPanel();
        col1.setLayout(new BoxLayout(col1, BoxLayout.Y_AXIS));

        Button b1 = new Button("forfeit");
        b1.addActionListener(forfeit);
        b1.setMaximumSize(new Dimension(Integer.MAX_VALUE, b1.getMinimumSize().height));
        col1.add(b1);

        //b1 = new Button("");
        //b1.setEnabled(false);
       // b1.setMaximumSize(new Dimension(Integer.MAX_VALUE, b1.getMinimumSize().height));
        col1.add(new JPanel());
        col1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        panelButtons.add(col1);

        panelButtons.add(Box.createHorizontalGlue());

        col1 = new JPanel();
        col1.setLayout(new BoxLayout(col1, BoxLayout.Y_AXIS));

        b1 = new Button("Confirm");
        //b1.setMaximumSize(new Dimension(Integer.MAX_VALUE, b1.getMinimumSize().height));
        col1.add(b1);

        b1 = new Button("Cancel");
        //b1.setMaximumSize(new Dimension(Integer.MAX_VALUE, b1.getMinimumSize().height));
        col1.add(b1);

        col1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        panelButtons.add(col1);
        p.add(panelButtons);
        return p;
    }
}
