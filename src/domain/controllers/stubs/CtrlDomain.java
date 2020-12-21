package domain.controllers.stubs;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import data.*;
import domain.classes.*;
import domain.classes.Exceptions.PlayerExists;
import domain.classes.Exceptions.WrongPasswordException;
import domain.controllers.CtrlPlay;
import domain.controllers.CtrlPlayer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CtrlDomain extends domain.controllers.CtrlDomain {

    private Gson gson;

    public  CtrlDomain() {

    };

    @Override
    public JsonReader getUser(String username) throws FileNotFoundException {
        JsonReader test = new JsonReader(new FileReader("data/juegos_prueba/usuarios/" + username + ".json"));
        return test;
    }
    @Override
    public void saveNewPlayer(String username, String playerJSON) throws PlayerExists {
        System.out.println("OK");
    }
    @Override
    public JsonReader[] getListOfPlayers() throws NullPointerException {
        JsonReader test = null;
        JsonReader test2 = null;
        try {
            test = new JsonReader(new FileReader("data/juegos_prueba/usuarios/" + "1234" + ".json"));
            test2 = new JsonReader(new FileReader("data/juegos_prueba/usuarios/" + "polFeo" + ".json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JsonReader [] testV = new JsonReader[2];
        testV[0] = test;
        testV[1] = test2;

        return testV;
    }

    public Player getCurrentPlayer() {
        JsonReader reader = null;
        try {
            reader = getUser("1234");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Player p = gson.fromJson(reader, Player.class);
        return p;
    }
}
