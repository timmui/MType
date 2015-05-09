package mtype.nofluxgiven.science;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
    private static TapListener tapListener;
    private static TextView displayMessage;
    private static String decoded = "";

    //SERVER
    private ServerHandler server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        server = new ServerHandler(this);

        RelativeLayout layout = (RelativeLayout)findViewById(R.id.layout);
        displayMessage = (TextView)findViewById(R.id.displayMessage);

        // Tap Listener
        tapListener = new TapListener();

        // Send Message Button
        Button sendMessageButton = (Button)findViewById(R.id.sendMessage);
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                server.SendMessage(decoded, "T23SU", "JJMO");
                Toast.makeText(getApplicationContext(), "Message Sent.", Toast.LENGTH_SHORT).show();
                tapListener.clearMessage();
            }
        });
        Button tap = (Button)findViewById(R.id.tap);
        tap.setOnTouchListener(tapListener);

        Button back = (Button) findViewById(R.id.backspace);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v) {
                tapListener.backSpace();
            }
        });
    }
    public static void updateMessage(String message)
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
}
