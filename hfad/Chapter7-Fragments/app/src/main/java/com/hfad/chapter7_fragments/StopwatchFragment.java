package com.hfad.chapter7_fragments;

import android.os.Bundle;
import android.os.Handler;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class StopwatchFragment extends Fragment implements View.OnClickListener {

  private int seconds;
  private boolean running;
  private boolean wasRunning;

  public StopwatchFragment() {
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View layout = inflater.inflate(R.layout.fragment_stopwatch, container, false);

    restoreStateIfNeeded(savedInstanceState);

    addOnClickListenersToButtons(layout);

    runTimer(layout);
    return layout;
  }

  @Override public void onPause() {
    super.onPause();
    wasRunning = running;
    running = false;
  }

  @Override public void onResume() {
    super.onResume();
    if(wasRunning){
      running = true;
    }

  }

  @Override public void onSaveInstanceState(Bundle bundle) {
    super.onSaveInstanceState(bundle);
    bundle.putInt("seconds",seconds);
    bundle.putBoolean("running",running);
    bundle.putBoolean("wasRunning",wasRunning);
  }

  @Override public void onClick(View view) {
    if (view != null) {
      switch (view.getId()) {
        case R.id.start_button:
          onClickStart();
          break;
        case R.id.stop_button:
          onClickStop();
          break;
        case R.id.reset_button:
          onClickReset();
          break;
      }
    }
  }

  private void onClickStart() {
    running = true;
  }

  private void onClickStop() {
    running = false;
  }

  private void onClickReset() {
    running = false;
    seconds = 0;
  }


  private void restoreStateIfNeeded(Bundle bundle) {
    if(bundle != null){
      this.seconds = bundle.getInt("seconds");
      this.running = bundle.getBoolean("running");
      this.wasRunning = bundle.getBoolean("wasRunning");
    }
  }

  private void addOnClickListenersToButtons(View layout) {
    addOnClickListener(R.id.start_button,layout);
    addOnClickListener(R.id.stop_button,layout);
    addOnClickListener(R.id.reset_button,layout);
  }

  private void addOnClickListener(int buttonId, View layout){
    Button button = layout.findViewById(buttonId);
    button.setOnClickListener(this);
  }

  private void runTimer(View view) {
    final TextView timeView = view.findViewById(R.id.time_view);

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
