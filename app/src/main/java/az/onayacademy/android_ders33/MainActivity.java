package az.onayacademy.android_ders33;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ServiceConnection {

    private App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        app = (App) getApplication();
        app.register(this);

        findViewById(R.id.button_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (app.getBackgroundService() != null) {
                    app.getBackgroundService().nextSong();
                }
            }
        });
    }
    private List<Song> getSongs() {
        return Arrays.asList(
                new Song("Name", "path1"),
                new Song("Name", "path2"),
                new Song("Name", "path3"),
                new Song("Name", "path4")
        );
    }


    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
