package joke.hfad.com.joke;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

public class DelayedMessageService extends IntentService {

  public static final String EXTRA_MESSAGE = "message";

  private Handler handler;

  public DelayedMessageService() {
    super("DelayedMessageService");
  }

  @Override public int onStartCommand(Intent intent, int flags, int startId) {
    handler = new Handler();
    return super.onStartCommand(intent, flags, startId);
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
    handler.post(new Runnable() {
      @Override public void run() {
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
      }
    });
  }
}
