package mtype.nofluxgiven.science;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.TableOperationCallback;

import java.net.MalformedURLException;

/**
 * Created by Jhony Guan on 5/9/2015.
 */
public class ServerHandler {
    private MobileServiceClient mClient;

    public ServerHandler (MainActivity a){
        // REST Client
        try {
            mClient = new MobileServiceClient(
                    "https://ecetitanic-mtype.azure-mobile.net/",
                    "YTuapDdxHvjrxaoUaQqVcHrduOibvW98",
                    a
            );
        }
        catch (MalformedURLException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void SendMessage(String text, String sender, String receiver) {
        Item item = new Item();
        item.Text = text;
        item.Receiver = sender;
        item.Sender = receiver;
        if(item.Text != null && !item.Text.isEmpty()) {
            mClient.getTable(Item.class).insert(item, new TableOperationCallback<Item>() {
                public void onCompleted(Item entity, Exception exception, ServiceFilterResponse response) {
                    if (exception == null) {
                        // Insert succeeded

                    } else {
                        // Insert failed
                    }
                }
            });
        }
    }

    public void ReceiveMessages() {

    }
}
