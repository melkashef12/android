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
    String messageText = getMessageText();
    String chooserTitle  = getString(R.string.chooser);

    Intent intent = new Intent(Intent.ACTION_SEND);
    intent.setType("text/plain");
    intent.putExtra(Intent.EXTRA_TEXT,messageText);

    Intent chooserIntent = Intent.createChooser(intent,chooserTitle);

    startActivity(chooserIntent);
  }

  @NonNull private String getMessageText() {
    EditText messageView = (EditText) findViewById(R.id.message);
    return messageView.getText().toString();
  }
}
