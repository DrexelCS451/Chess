package com.chess.Layouts;

import javax.swing.*;

/**
 * Created by tomer on 3/7/16.
 */
public class LobbyView {
    JPanel p;
    public LobbyView()
    {
        p = new JPanel();
        JList list = new JList();
    }

    public JPanel getView()
    {
        return p;
    }



}
