package mtype.nofluxgiven.science;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;


public class MainActivity extends ActionBarActivity {
    private static String message = "";
    private static TextView displayMessage;
    private static long prev = System.currentTimeMillis();
    private static long cur = System.currentTimeMillis();
    private static String decrypted = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout layout = (LinearLayout)findViewById(R.id.layout);
        displayMessage = (TextView)findViewById(R.id.displayMessage);
        layout.setOrientation(LinearLayout.VERTICAL);
        Button dot = (Button)findViewById(R.id.dot);
        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                prev = cur;
                cur = System.currentTimeMillis();
                if ((cur - prev) > 1000)
                    message = message + "    ";
                else if ((cur - prev) > 500)
                    message = message + ' ';

                message = message + ".";
                updateMessage(message);
            }
        });
        Button dash = (Button)findViewById(R.id.dash);
        dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                prev = cur;
                cur = System.currentTimeMillis();
                if ((cur - prev) > 1000)
                    message = message + "    ";
                else if ((cur - prev) > 500)
                    message = message +' ';

                message = message + "-";
                updateMessage(message);

            }
        });
        Button space = (Button)findViewById(R.id.space);
        space.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                message = message + " ";
                updateMessage(message);
            }
        });
        Button getMessage = (Button)findViewById(R.id.getMessage);
        getMessage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                decrypted = hash(message);
                updateMessage(decrypted);
                message = "";
            }
        });

    }
    private static void updateMessage(String message)
    {
        displayMessage.setText(message);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public static String hash(String morseCode){
        String message = "";

        HashMap <String,Character> alphabet = new HashMap <> ();

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


        String [] words = morseCode.split("    ");
        String [] letters;
        for (String word : words) {

                letters = word.split(" ");
                for (String letter : letters) {
                    if (alphabet.get(letter) != null)
                    message = message + alphabet.get(letter);
                }
                message = message + " ";

        }

        return message;
    }
}
