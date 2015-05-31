package mtype.nofluxgiven.science;

import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;

/**
 * Created by Tim on 5/9/2015.
 */

public class TapListener implements View.OnTouchListener{
    private static String message = "";
    private static long prev = System.currentTimeMillis(); // Timestamp of previous character
    private static long cur = System.currentTimeMillis(); // Timestamp of current character
    private static String decoded = "";
    private static long down = System.currentTimeMillis(); // Timestamp of press tap
    private static long up = System.currentTimeMillis(); // Timestamp of release tap

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        prev = cur;
        cur = System.currentTimeMillis();
        if ((cur - prev) > 1000) {
            decoded += hash(message);
            decoded += " ";
            message = "";
        }
        else if ((cur - prev) > 500) {
            decoded += hash(message);
            message = "";
        }
        else if (message.length() >= 5){
            decoded += hash(message);
            message = "";
        }


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                down = System.currentTimeMillis();
                return true;
            case MotionEvent.ACTION_UP:
                up = System.currentTimeMillis();
                if ((up - down) > 300) {
                    message = message + "-";
                    update();
                } else if ((up - down) > 50) {
                    message = message + ".";
                    update();
                }
                return true;
        }
        update();
        return false;

    }
    public static String hash(String morseCode){
        message = "";

        HashMap<String,Character> alphabet = new HashMap <> ();

        //Letters
        alphabet.put(".-",'a');
        alphabet.put("-...",'b');
        alphabet.put("-.-.",'c');
        alphabet.put("-..",'d');
        alphabet.put(".",'e');
        alphabet.put("..-.",'f');
        alphabet.put("--.",'g');
        alphabet.put("....",'h');
        alphabet.put("..",'i');
        alphabet.put(".---",'j');
        alphabet.put("-.-",'k');
        alphabet.put(".-..",'l');
        alphabet.put("--",'m');
        alphabet.put("-.",'n');
        alphabet.put("---",'o');
        alphabet.put(".--.",'p');
        alphabet.put("--.-",'q');
        alphabet.put(".-.",'r');
        alphabet.put("...",'s');
        alphabet.put("-",'t');
        alphabet.put("..-",'u');
        alphabet.put("...-",'v');
        alphabet.put(".--",'w');
        alphabet.put("-..-",'x');
        alphabet.put("-.--",'y');
        alphabet.put("--..",'z');

        //Numbers
        alphabet.put("-----",'0');
        alphabet.put(".----",'1');
        alphabet.put("..---",'2');
        alphabet.put("...--",'3');
        alphabet.put("....-",'4');
        alphabet.put(".....",'5');
        alphabet.put("-....",'6');
        alphabet.put("--...",'7');
        alphabet.put("---..",'8');
        alphabet.put("---.",'9');

        if (alphabet.get(morseCode) == null)
            return "";

        message = ""+alphabet.get(morseCode);

        return message;
    }
    public void clearMessage (){
        message = "";
        decoded = "";
        update();
    }
    private void update(){
        MainActivity.updateDisplay(decoded +"\n"+ message);
        MainActivity.updateMessage(decoded );
    }
    public void backSpace () {
        if(decoded.length() > 1 ){
            message = "";
            decoded = decoded.substring(0, decoded.length() - 1);
            update();
        }
    }
}
