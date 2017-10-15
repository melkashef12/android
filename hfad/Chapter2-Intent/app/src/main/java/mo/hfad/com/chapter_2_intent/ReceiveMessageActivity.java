package mo.hfad.com.chapter_2_intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ReceiveMessageActivity extends AppCompatActivity {

  public static final String EXTRA_MESSAGE = "message";

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_receive_message);
    String messageText = extractMessage();
    TextView messageView = (TextView) findViewById(R.id.message);
    messageView.setText(messageText);
  }

  private String extractMessage() {
    return getIntent().getStringExtra(EXTRA_MESSAGE);
  }
}
