package mo.hfad.com.chapter_2_intent;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateMessageActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_message);
  }

  public void onSendMessage(View view) {
    Intent intent = new Intent(this, ReceiveMessageActivity.class);
    String messageText = getMessageText();
    intent.putExtra(ReceiveMessageActivity.EXTRA_MESSAGE,messageText);
    startActivity(intent);
  }

  @NonNull private String getMessageText() {
    EditText messageView = (EditText) findViewById(R.id.message);
    return messageView.getText().toString();
  }
}
