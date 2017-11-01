package joke.hfad.com.joke;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;

public class DelayedMessageService extends IntentService {

  public static final String EXTRA_MESSAGE = "message";
  public static final int NOTIFICATION_ID= 5453;


  public DelayedMessageService() {
    super("DelayedMessageService");
  }


  @Override protected void onHandleIntent(Intent intent) {
    synchronized (this){
      try {
        wait(10000);
      } catch(InterruptedException e){
        e.printStackTrace();
      }
    }
    String text = extractText(intent);
    showText(text);
  }

  private String extractText(Intent intent) {
    return intent.getExtras().getString(EXTRA_MESSAGE);
  }

  private void showText(final String text) {
    Intent intent = new Intent(this,MainActivity.class);
    TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
    taskStackBuilder.addParentStack(MainActivity.class);
    taskStackBuilder.addNextIntent(intent);
    PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0,PendingIntent.FLAG_CANCEL_CURRENT);

    Notification notification = new Notification.Builder(this)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setContentTitle(getString(R.string.app_name))
        .setAutoCancel(true)
        .setPriority(Notification.PRIORITY_MAX)
        .setDefaults(Notification.DEFAULT_VIBRATE)
        .setContentIntent(pendingIntent)
        .setContentText(text)
        .build();

    NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    nm.notify(NOTIFICATION_ID,notification);

  }
}
