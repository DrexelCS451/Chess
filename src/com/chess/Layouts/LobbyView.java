package com.chess.Layouts;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by tomer on 3/7/16.
 */
public class LobbyView {
    JPanel p;
    JList list;
    int selectedUser = -1;
    Button b;
    JsonArray users;
    public LobbyView()
    {
        p = new JPanel(new FlowLayout());
        String[] selections = {};
        list = new JList(selections);
        list.setPreferredSize(new Dimension(400, 400));
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                selectedUser = listSelectionEvent.getFirstIndex();
            }
        });
        b = new Button("Send Request");
        p.add(list);
        p.add(b);
    }

    public void setSendRequestListner(ActionListener listner)
    {
        b.addActionListener(listner);
    }


    public int getSelectedUserId()
    {
        if(selectedUser != -1)
        {
            return users.get(selectedUser).getAsJsonObject().get("userId").getAsInt();
        }
        else
        {
            return -1;
        }
    }

    public void setList(JsonArray users)
    {
        this.users = users;
        list.removeAll();
        Vector<String> names = new Vector<>();
        for(JsonElement e:users)
        {
            names.add(Integer.toString(e.getAsJsonObject().get("userId").getAsInt()));
        }
        list.setListData(names);
    }

    public JPanel getView()
    {
        return p;
    }



}
