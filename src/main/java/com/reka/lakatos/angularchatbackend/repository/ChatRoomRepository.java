package com.reka.lakatos.angularchatbackend.repository;

import com.reka.lakatos.angularchatbackend.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
