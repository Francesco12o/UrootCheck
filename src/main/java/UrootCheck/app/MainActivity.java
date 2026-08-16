package UrootCheck.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainActivity extends Activity {

    private LinearLayout rootLayout;
    private TextView statusText;
    private Button checkButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        rootLayout = (LinearLayout) findViewById(R.id.rootLayout);
        statusText = (TextView) findViewById(R.id.statusText);
        checkButton = (Button) findViewById(R.id.checkButton);

        rootLayout.setBackgroundColor(Color.rgb(100, 100, 100));

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRoot();
            }
        });
    }

    private void checkRoot() {
        checkButton.setEnabled(false);
        statusText.setText("Requesting root access...");

        new Thread(new Runnable() {
            @Override
            public void run() {

                boolean rootSuccess = false;

                try {
                    Process process = Runtime.getRuntime().exec(
                            new String[] {
                                    "su",
                                    "-c",
                                    "id"
                            }
                    );

                    BufferedReader reader =
                            new BufferedReader(
                                    new InputStreamReader(
                                            process.getInputStream()
                                    )
                            );

                    StringBuilder output = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        output.append(line);
                        output.append("\n");
                    }

                    int exitCode = process.waitFor();

                    String result = output.toString();

                    rootSuccess =
                            exitCode == 0 &&
                            result.contains("uid=0");

                } catch (Exception e) {
                    rootSuccess = false;
                }

                final boolean success = rootSuccess;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        checkButton.setEnabled(true);

                        if (success) {
                            rootLayout.setBackgroundColor(
                                    Color.rgb(50, 170, 70)
                            );

                            statusText.setTextColor(
                                    Color.rgb(255, 165, 0)
                            );

                            statusText.setText(
                                    "congratulations! you have properly " +
                                    "installed root into your device, enjoy!"
                            );

                        } else {
                            rootLayout.setBackgroundColor(
                                    Color.rgb(100, 100, 100)
                            );

                            statusText.setTextColor(
                                    Color.WHITE
                            );

                            statusText.setText(
                                    "Sorry, root its not properly " +
                                    "installed on this device!"
                            );
                        }
                    }
                });
            }
        }).start();
    }
}
