package com.salonowner.ownermodule.services;

import com.salonowner.ownermodule.Entity.Notification;

import java.util.List;

public interface NotificationService {
    Notification createNotification(Notification notification);
    List<Notification> getNotificationsByReceiver(Long receiverId);
    List<Notification> getNotificationsBySender(Long senderId);
    Notification markAsRead(Long notificationId);
    Notification deleteNotification(Long notificationId);
}
