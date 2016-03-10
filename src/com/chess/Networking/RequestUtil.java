package com.chess.Networking;

import com.chess.Models.Board;
import com.chess.Models.Chess;
import com.chess.Models.NetworkModels.UserID;
import com.chess.Models.NetworkModels.UserName;
import com.chess.Models.User;
import com.google.gson.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by tomer on 2/10/16.
 */
public class RequestUtil {
    public static String baseUrl = "http://10.246.251.85:80/myapp/";

    public static String getUserId()
    {
        return User.id;
    }

    public static void lookupUser(final String name, final Listener listener)
    {
        String p = "";
        try{
            p = URLEncoder.encode(name, "UTF-8");
        }catch (Exception e){
            Logger.getLogger("error").log(Level.WARNING, e.getStackTrace().toString());
        }

        makeGetRequest("user?username=" + p, listener);
    }

    public static void CreateUser(String name,Listener listener)
    {
        Gson gson = new Gson();
        String json = gson.toJson(new UserName(name));
        makePostRequest(json, "user", listener);
    }

    public static void getLobby(Listener listener)
    {
        makeGetRequest("lobby", listener);
    }

    public static void joinLobby(Listener listener)
    {
        makePostRequest("", "lobby?userId=" + getUserId(), listener);
    }

    public static void checkRequests(Listener listener)
    {
        makeGetRequest("request?userId=" + getUserId(), listener);
    }

    public static void checkAcceptedRequests(Listener listener)
    {
        makeGetRequest("game?userId=" + getUserId(), listener);
    }


    public static void sendRequest(String oppId, Listener listener) {
        makePostRequest("", "request?userId=" + getUserId() + "&opponentId=" + oppId, listener);
    }

    public static void replyRequest(String repId, boolean accecpt, Listener listener) {
        makePutRequest("", "request?requestId=" + repId + "&accept=" + ((accecpt) ? "true" : "false"), listener);
    }

    private static boolean checkingRequests = false;
    public static void startCheckingForRequests(final Listener listener)
    {
        checkingRequests = true;
        new Thread(new Runnable(){
            @Override
            public void run() {
                while (checkingRequests) {
                    checkRequests(new Listener() {
                        @Override
                        public void responce(JsonElement e) {
                            if (e!= null && e.isJsonArray() && e.getAsJsonArray().size() != 0) {
                                listener.responce(e);
                            }
                        }
                    });
                    try {
                        Thread.sleep(3000);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Logger.getLogger("error").log(Level.WARNING, e.getStackTrace().toString());
                    }
                }
            }
        }).start();
    }

    public static void stopCheckingForRequests()
    {
        checkingRequests = false;
    }

    public static void deleteGames(Listener listener)
    {
        makeDeleteRequest("game?userId=" + getUserId(),listener);
    }

    public static void makeMove(int gameid, Board b, Listener listener)
    {
        String board = "";
        try{
            board = URLEncoder.encode(b.toString(), "UTF-8");
        }catch (Exception e){
            Logger.getLogger("test").log(Level.WARNING, e.getStackTrace().toString());

        }
        makePostRequest("", "game?gameId=" + gameid + "&board=" + board + "&boardState=" + b.getBoardState().name(), listener);
    }


    private static boolean checkingForMove = false;
    public static void startCheckingForMoves(final Listener listener)
    {
        checkingForMove = true;
        new Thread(new Runnable(){
            @Override
            public void run() {
                while (checkingForMove) {
                    checkAcceptedRequests(new Listener() {
                        @Override
                        public void responce(JsonElement e) {
                            if(e!= null && ((e.getAsJsonObject().get("state").getAsString().startsWith("BLACK") && !User.isWhite) || (e.getAsJsonObject().get("state").getAsString().startsWith("WHITE") && User.isWhite)))
                            {
                                listener.responce(e);
                            }
                        }
                    });
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        Logger.getLogger("test").log(Level.WARNING, e.getStackTrace().toString());
                    }
                }
            }
        }).start();

    }

    public static void stopCheckingForMoves()
    {
        checkingForMove = false;
    }


    public static void leaveLobby()
    {
        stopCheckingForRequests();
        stopCheckingForAcceptedRequests();
        stopRefreshingLobby();
        makeDeleteRequest("lobby?userId=" + getUserId(), new Listener() {
            @Override
            public void responce(JsonElement e) {

            }
        });
    }


    private static boolean checkingAcceptedRequests = false;
    public static void startCheckingForAcceptedRequests(final Listener listener)
    {
        checkingAcceptedRequests = true;
        new Thread(new Runnable(){
            @Override
            public void run() {
                while (checkingAcceptedRequests) {
                    checkAcceptedRequests(new Listener() {
                        @Override
                        public void responce(JsonElement e) {
                            if (e!= null && !e.getAsJsonObject().has("status")) {
                                listener.responce(e);
                            }
                        }
                    });
                    try {
                        Thread.sleep(3000);
                    } catch (Exception e) {
                        Logger.getLogger("test").log(Level.WARNING, e.getStackTrace().toString());
                    }
                }
            }
        }).start();

    }

    public static void stopCheckingForAcceptedRequests()
    {
        checkingAcceptedRequests = false;
    }




    private static boolean refreshingLobby = false;
    public static void startRefreshingLobby(final Listener listener)
    {
        refreshingLobby = true;
        new Thread(new Runnable(){
            @Override
            public void run() {
                while (refreshingLobby) {
                    getLobby(listener);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        Logger.getLogger("test").log(Level.WARNING, e.getStackTrace().toString());
                    }
                }
            }
        }).start();

    }

    public static void stopRefreshingLobby()
    {
        refreshingLobby = false;
    }




    public static void makePostRequest(final String json,final String urlEnd, final Listener listener)
    {
        new Thread(new Runnable(){
            @Override
            public void run() {
                try{
                    URL url = new URL(baseUrl + urlEnd);
                    HttpURLConnection request = (HttpURLConnection) url.openConnection();
                    request.setRequestMethod("POST");
                    request.setDoOutput(true);
                    OutputStream os = request.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    writer.write(json);
                    writer.flush();
                    writer.close();
                    os.close();
                    request.connect();
                    JsonParser jp = new JsonParser();
                    JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
                    JsonObject rootobj = root.getAsJsonObject(); // may be Json Array if it's an array, or other type if a primitive
                    listener.responce(rootobj);
                }catch (Exception e){
                    listener.responce(null);
                    Logger.getLogger("test").log(Level.WARNING, e.getStackTrace().toString());
                }
            }
        }).start();
    }

    public static void makePutRequest(final String json,final String urlEnd, final Listener listener)
    {
        new Thread(new Runnable(){
            @Override
            public void run() {
                try{
                    URL url = new URL(baseUrl + urlEnd);
                    HttpURLConnection request = (HttpURLConnection) url.openConnection();
                    request.setRequestMethod("PUT");
                    request.setDoOutput(true);
                    OutputStream os = request.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    writer.write(json);
                    writer.flush();
                    writer.close();
                    os.close();
                    request.connect();
                    JsonParser jp = new JsonParser();
                    JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
                    JsonObject rootobj = root.getAsJsonObject(); // may be Json Array if it's an array, or other type if a primitive
                    listener.responce(rootobj);
                }catch (Exception e){
                    listener.responce(null);
                    Logger.getLogger("test").log(Level.WARNING, e.getStackTrace().toString());
                }
            }
        }).start();
    }
    public static void makeGetRequest(final String urlEnd, final Listener listener)
    {
        new Thread(new Runnable(){
            @Override
            public void run() {
                try{
                    URL url = new URL(baseUrl + urlEnd);
                    HttpURLConnection request = (HttpURLConnection) url.openConnection();
                    request.setRequestMethod("GET");
                    request.connect();
                    JsonParser jp = new JsonParser();
                    JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
                    listener.responce(root);
                }catch (Exception e){
                    listener.responce(null);
                    Logger.getLogger("test").log(Level.WARNING, e.getStackTrace().toString());
                }
            }
        }).start();
    }

    public static void makeDeleteRequest(final String urlEnd, final Listener listener)
    {
        new Thread(new Runnable(){
            @Override
            public void run() {
                try{
                    URL url = new URL(baseUrl + urlEnd);
                    HttpURLConnection request = (HttpURLConnection) url.openConnection();
                    request.setRequestMethod("DELETE");
                    request.connect();
                    JsonParser jp = new JsonParser();
                    JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
                    listener.responce(root);
                }catch (Exception e){
                    listener.responce(null);
                    Logger.getLogger("test").log(Level.WARNING, e.getStackTrace().toString());
                }
            }
        }).start();
    }

}
