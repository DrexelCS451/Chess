package com.chess.Layouts;

import com.chess.Helpers.CellListener;
import com.chess.Helpers.ImageHelper;
import com.chess.Models.Board;
import com.chess.Models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by tomer on 2/10/16.
 */
public class ChessView {

    JPanel p;
    JPanel[][] pieces= new JPanel[8][8];
    Button forfeit,b1,b2;
    JPanel board;
    Label label;
    CellListener listener;

    public ChessView()
    {
        p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        label =new Label(User.isWhite?"Your Turn":"Opponents turn");
        label.setAlignment(Label.CENTER);
        label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getMinimumSize().height));
        p.add(label);

        board = new JPanel();
        board.setLayout(new GridLayout(8, 8));
        p.add(board);

        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.X_AXIS));
        JPanel col1 = new JPanel();
        col1.setLayout(new BoxLayout(col1, BoxLayout.Y_AXIS));

        forfeit = new Button("forfeit");
        //b1.addActionListener(forfeit);
        forfeit.setMaximumSize(new Dimension(Integer.MAX_VALUE, forfeit.getMinimumSize().height));
        col1.add(forfeit);

        //b1 = new Button("");
        //b1.setEnabled(false);
        // b1.setMaximumSize(new Dimension(Integer.MAX_VALUE, b1.getMinimumSize().height));
        col1.add(new JPanel());
        col1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        panelButtons.add(col1);

        panelButtons.add(Box.createHorizontalGlue());

        col1 = new JPanel();
        col1.setLayout(new BoxLayout(col1, BoxLayout.Y_AXIS));

        b1 = new Button("");
        b1.setEnabled(false);
        //b1.setMaximumSize(new Dimension(Integer.MAX_VALUE, b1.getMinimumSize().height));
        col1.add(b1);

        b2 = new Button("");
        b2.setEnabled(false);
        //b1.setMaximumSize(new Dimension(Integer.MAX_VALUE, b1.getMinimumSize().height));
        col1.add(b2);

        col1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        panelButtons.add(col1);
        p.add(panelButtons);
    }

    public void setLabel(String newlabel)
    {
        label.setText(newlabel);
    }
    public JPanel getView()
    {
        return p;
    }

    public void setCellClickListner(CellListener listner)
    {
        this.listener = listner;
    }

    public void cellClicked(int i, int j)
    {
        pieces[i][j].setBackground(Color.YELLOW);
    }

    public void resetCellColor()
    {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                pieces[i][j].setBackground(((i + j) % 2 == 0) ? new Color(50, 50, 50) : new Color(255, 255, 255));
            }
        }
    }

    public void show2Buttons(String s1, String s2)
    {
        b1.setLabel(s1);
        b1.setEnabled(true);
        b2.setLabel(s2);
        b2.setEnabled(true);
    }

    public void remove2Buttons()
    {
        b1.setLabel("");
        b1.setEnabled(false);
        b2.setLabel("");
        b2.setEnabled(false);
    }

    public void setForfeitListener(ActionListener listener)
    {
        forfeit.addActionListener(listener);
    }

    public void setB1Listener(ActionListener listener)
    {
        b1.addActionListener(listener);
    }

    public void setB2Listener(ActionListener listener)
    {
        b2.addActionListener(listener);
    }


    public void setBoard(Board boardModel)
    {
        board.removeAll();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JPanel square = new JPanel();
                square.setBackground(((i + j) % 2 == 0) ? new Color(50, 50, 50) : new Color(255, 255, 255));

                JLabel piece = new JLabel(ImageHelper.getPeiceIcon(boardModel.getCell(j, i).getCellState()));
                piece.setFont(new Font("Serif", Font.BOLD, 40));
                piece.setForeground(Color.BLACK);
                pieces[j][i] = square;
                square.add(piece);
                final int x = j;
                final int y = i;
                square.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent mouseEvent) {
                        listener.actionPerformed(x,y);
                    }

                    @Override
                    public void mousePressed(MouseEvent mouseEvent) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent mouseEvent) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent mouseEvent) {
                    }

                    @Override
                    public void mouseExited(MouseEvent mouseEvent) {
                    }
                });
                board.add(square);

            }
        }
        board.revalidate();
    }

}
