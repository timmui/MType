package mtype.nofluxgiven.science;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.microsoft.windowsazure.mobileservices.MobileServiceList;


public class Inbox extends ActionBarActivity {
    //SERVER
    private ServerHandler server;
    private MobileServiceList<Item> inboxList;
    public TableLayout inboxTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

        server = new ServerHandler (this);

        inboxTable = (TableLayout) findViewById(R.id.table);

        Button getButton = (Button) findViewById(R.id.getMessages);
        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                server.ReceiveMessages();

                /* Modify the table
                        TableRow row = new TableRow(inbox.getBaseContext());
                        TextView sender = new TextView(inbox.getBaseContext());
                        TextView receiver = new TextView(inbox.getBaseContext());
                        TextView message = new TextView(inbox.getBaseContext());

                        row.setLayoutParams(lp);
                        sender.setLayoutParams(lp);
                        receiver.setLayoutParams(lp);
                        message.setLayoutParams(lp);

                        message.setText(item.Text);
                        sender.setText(item.Sender);
                        receiver.setText(item.Receiver);

                        row.addView(message);
                        row.addView(sender);
                        row.addView(receiver);

                        inbox.inboxTable.addView(row);
                 */
            }
        });

        Button sendMsgButton = (Button) findViewById(R.id.sendMessages);
        sendMsgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inbox, menu);
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
