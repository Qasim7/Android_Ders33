package az.onayacademy.android_ders33;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.List;

public class App extends Application {

    private BackgroundService backgroundService;

    private List<ServiceConnection> serviceConnectedListeners;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {

            if (binder instanceof BackgroundService.MyBinder) {
                backgroundService = ((BackgroundService.MyBinder) binder).getService();

                for (int i=0; i<serviceConnectedListeners.size();i++) {
                    ServiceConnection listener = serviceConnectedListeners.get(i);

                    if (listener != null)
                        listener.onServiceConnected(name, binder);
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            for (ServiceConnection listener : serviceConnectedListeners) {
                if (listener != null)
                    listener.onServiceDisconnected(name);
            }
        }
    };

    public void register(ServiceConnection listener) {
        serviceConnectedListeners.add(listener);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        serviceConnectedListeners = new ArrayList<>();
        Intent i = new Intent(this, BackgroundService.class);
        bindService(i, connection, BIND_AUTO_CREATE);
    }

    public BackgroundService getBackgroundService() {
        return backgroundService;
    }

    interface ServiceConnectedListener {
        void onServiceConnected(BackgroundService backgroundService);
    }
}
