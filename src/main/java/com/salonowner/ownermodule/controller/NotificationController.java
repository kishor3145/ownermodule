package com.salonowner.ownermodule.controller;


import com.salonowner.ownermodule.Entity.Notification;
import com.salonowner.ownermodule.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin("*")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public Notification createNotification(@RequestBody Notification notification) {
        return notificationService.createNotification(notification);
    }

    @GetMapping("/receiver/{id}")
    public List<Notification> getByReceiver(@PathVariable Long id) {
        return notificationService.getNotificationsByReceiver(id);
    }

    @GetMapping("/sender/{id}")
    public List<Notification> getBySender(@PathVariable Long id) {
        return notificationService.getNotificationsBySender(id);
    }

    @PutMapping("/{id}/read")
    public Notification markAsRead(@PathVariable Long id) {
        return notificationService.markAsRead(id);
    }
}
