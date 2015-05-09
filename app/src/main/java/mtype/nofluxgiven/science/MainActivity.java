package mtype.nofluxgiven.science;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
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
    private static long down = System.currentTimeMillis();
    private static long up = System.currentTimeMillis();
    private static String id;
    //private static String message;
    private static String recepient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout layout = (LinearLayout)findViewById(R.id.layout);
        displayMessage = (TextView)findViewById(R.id.displayMessage);
        layout.setOrientation(LinearLayout.VERTICAL);
       // Button dot = (Button)findViewById(R.id.dot);

        /*
        dot.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                prev = cur;
                cur = System.currentTimeMillis();
                if ((cur - prev) > 1000) {
                    decrypted += hash(message);
                    decrypted += "   ";
                    message = "";
                }
                else if ((cur - prev) > 500) {
                    decrypted += hash(message);
                    message = "";
                }

                message = message + ".";
                updateMessage(decrypted+message);
            }
        }); */


        //Button space = (Button)findViewById(R.id.space);
        ///space.setOnClickListener(new View.OnClickListener(){
           // @Override
            //public void onClick(View v){
              //  message = message + " ";
               // updateMessage(message);
           // }
        //});
        Button getMessage = (Button)findViewById(R.id.getMessage);
        getMessage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                //decrypted = hash(message);
                //updateMessage(decrypted);
                message = "";
                decrypted = "";
            }
        });
        Button dash = (Button)findViewById(R.id.dash);
        dash.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                prev = cur;
                cur = System.currentTimeMillis();
                if ((cur - prev) > 1000) {
                    decrypted += hash(message);
                    decrypted += "   ";
                    message = "";
                }
                else if ((cur - prev) > 400) {
                    decrypted += hash(message);
                    message = "";
                }


                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        down = System.currentTimeMillis();
                        return true;
                    case MotionEvent.ACTION_UP:
                        up = System.currentTimeMillis();
                        if ((up - down)>200) {
                            message = message + "-";
                            updateMessage(decrypted + message);
                        }
                        else if ((up - down) > 50) {
                            message = message + ".";
                            updateMessage(decrypted + message);
                        }
                        return true;
                }
                updateMessage(decrypted + message);
                return false;

            }
        });
    }
    private static void updateMessage(String message)
    {
        if (message != null)
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

/*
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
*/
        if (alphabet.get(morseCode) == null)
            return "";

        message = ""+alphabet.get(morseCode);

        return message;
    }
/*
    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            person Person = new person();
            Person.id(id.getText().toString());
            Person.message(message.getText().toString());
            Person.recepient(recepient.getText().toString());

            return POST(urls[0],Person);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_LONG).show();
        }
    }

    public static String POST(String url, person Person){
        InputStream inputStream = null;
        String result = "";
        try {

            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            String json = "";

            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("name", id);
            jsonObject.accumulate("message", message);
            jsonObject.accumulate("recepient", recepient);

            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();

            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person);

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // 10. convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        return result;
    }

    */
}
