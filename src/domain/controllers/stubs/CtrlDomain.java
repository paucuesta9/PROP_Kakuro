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
    private Kakuro currentKakuro;

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
    @Override
    public Player getCurrentPlayer() {
        JsonReader reader = null;
        try {
            reader = getUser("1234");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        Player p = gson.fromJson(reader, Player.class);
        return p;
    }

    @Override
    public String searchKakuro(int difficulty, int kakuroSizeRow, int kakuroSizeColumn) throws IOException, IndexOutOfBoundsException, NumberFormatException {
        return "0\n" +
                "9,9\n" +
                "*,*,C19,C12,*,*,*,C7,C10\n" +
                "*,F14,?,?,C4,C11,C17F4,?,?\n" +
                "*,C7F36,?,?,?,?,?,?,?\n" +
                "F12,?,?,F10,?,?,?,C25,C14\n" +
                "F3,?,?,C20,C11F20,?,?,?,?\n" +
                "F17,?,?,?,?,C8,F6,?,?\n" +
                "*,C11,C7F13,?,?,?,C4F10,?,?\n" +
                "F28,?,?,?,?,?,?,?,*\n" +
                "F6,?,?,*,*,F8,?,?,*";
    }


    @Override
    public void setKakuro(Kakuro kakuro) {
        currentKakuro = kakuro;
    }

    @Override
    public int getGameId() {
        return 0;
    }

    @Override
    public String getKakuro(String filePath) throws IOException, IndexOutOfBoundsException, NumberFormatException {
        return "9,9\n" +
                "*,*,C19,C12,*,*,*,C7,C10\n" +
                "*,F14,5,9,C4,C11,C17F4,1,3\n" +
                "*,C7F36,2,3,1,8,9,6,7\n" +
                "F12,4,8,F10,3,2,5,C25,C14\n" +
                "F3,2,1,C20,C11F20,1,3,9,7\n" +
                "F17,1,3,9,4,C8,F6,1,5\n" +
                "*,C11,C7F13,7,1,5,C4F10,8,2\n" +
                "F28,7,5,4,6,3,1,2,*\n" +
                "F6,4,2,*,*,F8,3,5,*";
    }

    @Override
    public void updateStatsPlayer() {
        System.out.println("Update stats");
    }
}
