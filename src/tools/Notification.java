package tools;

import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class Notification {
    public static void NotifSucces(String title,String msg){
        NotificationType type = NotificationType.SUCCESS;
        TrayNotification trayNotification = new TrayNotification();
        trayNotification.setTitle(title);
        trayNotification.setMessage(msg);
        trayNotification.setNotificationType(type);
        trayNotification.showAndDismiss(Duration.seconds(2));

    }
    public static void NotiError(String title,String msg){
        NotificationType type = NotificationType.ERROR;
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(msg);
        tray.setNotificationType(type);
        tray.showAndDismiss(Duration.seconds(2));
    }
}
