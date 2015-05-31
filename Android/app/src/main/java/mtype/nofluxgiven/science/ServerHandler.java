package mtype.nofluxgiven.science;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.TableOperationCallback;

import java.net.MalformedURLException;

/**
 * Created by Jhony Guan on 5/9/2015.
 */
public class ServerHandler {
    private MobileServiceClient mClient;
    public MobileServiceList<Messages> messageList;
    public Boolean finished = false;

    public ServerHandler (Activity a){
        // REST Client
        try {
            mClient = new MobileServiceClient(
                    "https://ecetitanic-mtype.azure-mobile.net/api/mtype",
                    "YTuapDdxHvjrxaoUaQqVcHrduOibvW98",
                    a
            );
        }
        catch (MalformedURLException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean isFinished (){
        return finished;
    }

    public void SendMessage(String text, String sender, String receiver) {
        Messages item = new Messages();
        item.Text = text;
        item.Receiver = sender;
        item.Sender = receiver;
        if(item.Text != null && !item.Text.isEmpty()) {
            mClient.getTable(Messages.class).insert(item, new TableOperationCallback<Messages>() {
                public void onCompleted(Messages entity, Exception exception, ServiceFilterResponse response) {
                    if (exception == null) {
                        // Insert succeeded
                        Log.i("UPLOAD", "Message Succeeded.");
                    } else {
                        // Insert failed
                        Log.i("UPLOAD", "Message Failed.");
                    }
                }
            });
        }
    }

    private void setMessages (MobileServiceList<Messages> data) {
        messageList = data;
        Log.i("PROGRESS", "Still Loading." );
        finished = true;
    }

    public MobileServiceList<Messages> getMessages () {
        if (messageList != null) {
            return messageList;
        }
        return null;
    }

    public void ReceiveMessages() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    final MobileServiceList<Messages> result =
                            mClient.getTable(Messages.class).where().field("Receiver").eq("Pebble_Tim").execute().get();
                    for (Messages item : result) {
                        Log.i("MESSAGES", "Message to Jack: " + item.Text);
                    }
                    setMessages(result);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                    //e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }
}
