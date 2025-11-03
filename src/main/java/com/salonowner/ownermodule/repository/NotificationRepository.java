package com.salonowner.ownermodule.repository;

import com.salonowner.ownermodule.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByReceiverId(Long receiverId);
    List<Notification> findBySenderId(Long senderId);
}
