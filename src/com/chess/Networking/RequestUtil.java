package com.chess.Networking;

import com.chess.Models.NetworkModels.UserID;
import com.chess.Models.NetworkModels.UserName;
import com.chess.Models.User;
import com.google.gson.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by tomer on 2/10/16.
 */
public class RequestUtil {
    public static String baseUrl = "http://localhost:8080/myapp/";

    public static String getUserId()
    {
        return User.id;
    }

    public static void lookupUser(final String name, final Listener listener)
    {
        String p = "";
        try{
            p = URLEncoder.encode(name, "UTF-8");
        }catch (Exception e){}

        makeGetRequest("user?username=" + p,listener);
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

    public static void replyRequest(String oppId, Listener listener) {
        makePutRequest("", "request?userId=" + getUserId() + "&opponentId=" + oppId, listener);
    }

    private static boolean checkingRequests = false;
    public static void startCheckingForRequests(final Listener listener)
    {
        checkingRequests = true;
        new Runnable(){
            @Override
            public void run() {
                while (checkingRequests) {
                    checkRequests(new Listener() {
                        @Override
                        public void responce(JsonElement e) {
                            if (e.getAsJsonArray().size() != 0) {
                                listener.responce(e);
                            }
                        }
                    });
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.run();

    }

    public static void stopCheckingForRequests()
    {
        checkingRequests = false;
    }




    private static boolean checkingAcceptedRequests = false;
    public static void startCheckingForAcceptedRequests(final Listener listener)
    {
        checkingAcceptedRequests = true;
        new Runnable(){
            @Override
            public void run() {
                while (checkingAcceptedRequests) {
                    checkAcceptedRequests(new Listener() {
                        @Override
                        public void responce(JsonElement e) {
                            if (e.getAsJsonArray().size() != 0) {
                                listener.responce(e);
                            }
                        }
                    });
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.run();

    }

    public static void stopCheckingForAcceptedRequests()
    {
        checkingAcceptedRequests = false;
    }





    public static void makePostRequest(final String json,final String urlEnd, final Listener listener)
    {
        new Runnable(){
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
                }
            }
        }.run();
    }


    public static void makePutRequest(final String json,final String urlEnd, final Listener listener)
    {
        new Runnable(){
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
                }
            }
        }.run();
    }
    public static void makeGetRequest(final String urlEnd, final Listener listener)
    {
        new Runnable(){
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
                }
            }
        }.run();
    }

}
