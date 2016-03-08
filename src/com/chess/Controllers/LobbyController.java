package com.chess.Controllers;

import com.chess.Layouts.LobbyView;
import com.chess.Layouts.MainMenuView;
import com.chess.Models.User;
import com.chess.Networking.Listener;
import com.chess.Networking.RequestUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tomer on 3/7/16.
 */
public class LobbyController {
    public LobbyController()
    {
    }

    public void createView(final JFrame frame)
    {
        final LobbyView v = new LobbyView();
        frame.setContentPane(v.getView());
        frame.revalidate();

        RequestUtil.joinLobby(new Listener() {
            @Override
            public void responce(JsonElement e) {

            }
        });

        RequestUtil.getLobby(new Listener() {
            @Override
            public void responce(JsonElement e) {
                JsonArray a = e.getAsJsonArray();
                v.setList(a);
            }
        });

        final Set<Integer> seenRequests = new HashSet<>();
        RequestUtil.startCheckingForRequests(new Listener() {
            @Override
            public void responce(JsonElement e) {

                for (JsonElement r : e.getAsJsonArray()) {
                    if (!seenRequests.contains(r.getAsJsonObject().get("id").getAsInt())) {
                        String n = r.getAsJsonObject().get("player1Name").getAsString();
                        int reply = JOptionPane.showConfirmDialog(null, n + " sent a game request. Would you like to accept?", "Accept?", JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION) {
                            RequestUtil.stopCheckingForRequests();
                            RequestUtil.replyRequest(Integer.toString(e.getAsJsonArray().get(0).getAsJsonObject().get("player1").getAsInt()), new Listener() {
                                @Override
                                public void responce(JsonElement e) {
                                    //start game
                                    ChessScreenController c = new ChessScreenController(e);
                                    c.createView(frame);
                                }
                            });
                            break;
                        } else {
                            seenRequests.add(r.getAsJsonObject().get("id").getAsInt());
                        }
                    }
                }

            }
        });


        RequestUtil.startCheckingForAcceptedRequests(new Listener() {
            @Override
            public void responce(JsonElement e) {
                ChessScreenController c = new ChessScreenController(e);
                c.createView(frame);
            }
        });

        v.setSendRequestListner(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (v.getSelectedUserId() != -1) {
                    RequestUtil.sendRequest(Integer.toString(v.getSelectedUserId()), new Listener() {
                        @Override
                        public void responce(JsonElement e) {

                        }
                    });
                }
            }
        });
    }
}
