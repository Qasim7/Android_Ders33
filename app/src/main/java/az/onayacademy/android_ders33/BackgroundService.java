package az.onayacademy.android_ders33;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.List;

public class BackgroundService extends Service {

    private List<Song> songs;

    private int currentSong = 0;

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public void nextSong() {
        if (songs == null)
            return;

        currentSong++;

        if (currentSong >= songs.size())
            currentSong = 0;

        Log.e("BackgroundService",
                "Now is playing " + songs.get(currentSong).getPath());
    }

    class MyBinder extends Binder {
        BackgroundService getService() {
            return BackgroundService.this;
        }
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
