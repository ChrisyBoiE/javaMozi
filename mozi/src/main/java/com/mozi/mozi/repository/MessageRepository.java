package com.mozi.mozi.repository;

import com.mozi.mozi.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByOrderByCreatedAtDesc(); // Fordított időrendi sorrend
}
