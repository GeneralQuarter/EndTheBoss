package virus.endtheboss;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import virus.endtheboss.Modele.Score;

/**
 * Created by Quentin Gangler on 15/03/2016.
 */
public class ScoreHelper {
    private Context context;

    public ScoreHelper(Context context){
        this.context = context;
    }

    private List<Score> scores;

    private final String FILENAME = "scores";
    private final String SCORES = "SCORES";

    public void addScore(Score score){
        if(scores == null){
            scores = getScores();
        }
        Collections.reverse(scores);
        scores.add(score);
        SharedPreferences prefs = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        try {
            editor.putString(SCORES, serialize((Serializable) scores));
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.apply();
    }

    public void resetScores(){
        scores = new ArrayList<>();
        SharedPreferences prefs = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        try {
            editor.putString(SCORES, serialize((Serializable) scores));
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.apply();
    }

    public List<Score> getScores(){
        scores = new ArrayList<>();
        SharedPreferences prefs = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        try {
            scores = (ArrayList<Score>) deserialize(prefs.getString(SCORES, serialize(new ArrayList<Score>())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.reverse(scores);

        return scores;
    }

    private String serialize(Serializable obj) throws IOException {
        if (obj == null) return "";
        try {
            ByteArrayOutputStream serialObj = new ByteArrayOutputStream();
            ObjectOutputStream objStream = new ObjectOutputStream(serialObj);
            objStream.writeObject(obj);
            objStream.close();
            return encodeBytes(serialObj.toByteArray());
        } catch (Exception e) {
            Log.e("Serialization", "Erreur : " + e.getMessage());
        }

        return "";
    }

    private Object deserialize(String str) throws IOException {
        if (str == null || str.length() == 0) return null;
        try {
            ByteArrayInputStream serialObj = new ByteArrayInputStream(decodeBytes(str));
            ObjectInputStream objStream = new ObjectInputStream(serialObj);
            return objStream.readObject();
        } catch (Exception e) {
            Log.e("Serialization", "Erreur : " + e.getMessage());
        }

        return "";
    }

    private static String encodeBytes(byte[] bytes) {
        StringBuffer strBuf = new StringBuffer();

        for (int i = 0; i < bytes.length; i++) {
            strBuf.append((char) (((bytes[i] >> 4) & 0xF) + ((int) 'a')));
            strBuf.append((char) (((bytes[i]) & 0xF) + ((int) 'a')));
        }

        return strBuf.toString();
    }

    private static byte[] decodeBytes(String str) {
        byte[] bytes = new byte[str.length() / 2];
        for (int i = 0; i < str.length(); i+=2) {
            char c = str.charAt(i);
            bytes[i/2] = (byte) ((c - 'a') << 4);
            c = str.charAt(i+1);
            bytes[i/2] += (c - 'a');
        }
        return bytes;
    }
}
