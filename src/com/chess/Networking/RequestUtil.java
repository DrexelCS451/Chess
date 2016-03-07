package com.chess.Networking;

import com.chess.Models.NetworkModels.UserID;
import com.chess.Models.NetworkModels.UserName;
import com.google.gson.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by tomer on 2/10/16.
 */
public class RequestUtil {
    public static String baseUrl = "http://localhost:8080/myapp/";

    public static String getUserId()
    {
        return "";
    }

    public static void lookupUser(final String name, final Listener listener)
    {
        makeGetRequest("user?username=" + name,listener);
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
        Gson gson = new Gson();
        String json = gson.toJson(new UserID(getUserId()));
        makePostRequest(json, "lobby", listener);
    }

    public static void checkRequests(Listener listener)
    {
        makeGetRequest("request?userId=" + getUserId(), listener);
    }


    public static void sendRequest(String oppId, Listener listener)
    {
        makePostRequest("","request?userId=" + getUserId() + "&opponentId=" + oppId, listener);
    }

    public static void replyRequest(String oppId, Listener listener)
    {
        makePutRequest("","request?userId=" + getUserId() + "&opponentId=" + oppId, listener);
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
                    JsonObject rootobj = root.getAsJsonObject(); // may be Json Array if it's an array, or other type if a primitive
                    listener.responce(rootobj);
                }catch (Exception e){
                    listener.responce(null);
                }
            }
        }.run();
    }

}
