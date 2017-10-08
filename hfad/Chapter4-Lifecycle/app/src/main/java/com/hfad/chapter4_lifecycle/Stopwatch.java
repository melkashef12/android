package com.hfad.chapter4_lifecycle;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.w3c.dom.Text;

public class Stopwatch extends Activity {

  private int seconds;
  private boolean running;


  @Override protected void onCreate(Bundle bundle) {
    super.onCreate(bundle);
    setContentView(R.layout.activity_stopwatch);
    restoreStateIfNeeded(bundle);
    runTimer();
  }


  public void onClickStart(View view) {
    running = true;
  }

  public void onClickStop(View view) {
    running = false;
  }

  public void onClickReset(View view) {
    running = false;
    seconds = 0;
  }

  @Override protected void onSaveInstanceState(Bundle bundle) {
    super.onSaveInstanceState(bundle);
    bundle.putInt("seconds",seconds);
    bundle.putBoolean("running",running);
  }


  private void restoreStateIfNeeded(Bundle bundle) {
    if(bundle != null){
      this.seconds = bundle.getInt("seconds");
      this.running = bundle.getBoolean("running");
    }
  }

  private void runTimer() {
    final TextView timeView = findViewById(R.id.time_view);

    final Handler handler = new Handler();

    handler.post(new Runnable() {
      @Override public void run() {
        String formattedTime = formatTime();
        timeView.setText(formattedTime);
        if (running) {
          seconds++;
        }
        handler.postDelayed(this,1000);
      }
    });


  }

  private String formatTime(){
    int hours = seconds / 3600;
    int minutes = (seconds % 3600) /60;
    int secs = seconds % 60;

    return String.format("%d:%02d:%02d",hours,minutes,secs);

  }
}
