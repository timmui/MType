package mtype.nofluxgiven.science;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.microsoft.windowsazure.mobileservices.*;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.TableOperationCallback;

import java.net.MalformedURLException;


public class MainActivity extends ActionBarActivity {
    private static String message = "";
    private static TextView displayMessage;
    private static ServerHandler server;
    //private MobileServiceClient mClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        server = new ServerHandler(this);

        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
        displayMessage = (TextView)findViewById(R.id.displayMessage);
        layout.setOrientation(LinearLayout.VERTICAL);
        Button dot = (Button)findViewById(R.id.dot);
        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = message + ".";
                updateMessage();
            }
        });
        Button dash = (Button)findViewById(R.id.dash);
        dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = message + "-";
                updateMessage();
            }
        });
        Button space = (Button)findViewById(R.id.space);
        space.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                message = message + " ";
                updateMessage();
            }
        });
        Button sendButton = (Button) findViewById(R.id.sendMessage);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                server.SendMessage(message, "JJMO", "Pragash");
            }
        });

    }
    private static void updateMessage()
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
}
