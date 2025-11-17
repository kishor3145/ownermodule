package com.salonowner.ownermodule.services.impl;


import com.salonowner.ownermodule.Entity.Notification;
import com.salonowner.ownermodule.repository.NotificationRepository;
import com.salonowner.ownermodule.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public Notification createNotification(Notification notification) {
        notification.setSentTime(java.time.LocalDateTime.now());
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getNotificationsByReceiver(Long receiverId) {
        return notificationRepository.findByReceiverId(receiverId);
    }

    @Override
    public List<Notification> getNotificationsBySender(Long senderId) {
        return notificationRepository.findBySenderId(senderId);
    }

    @Override
    public Notification markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setReadStatus(true);
        return notificationRepository.save(notification);
    }

    @Override
    public Notification deleteNotification(Long notificationId) {
        return null;
    }

}
