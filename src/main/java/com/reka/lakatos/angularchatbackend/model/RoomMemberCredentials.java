package com.reka.lakatos.angularchatbackend.model;

import lombok.Data;

@Data
public class RoomMemberCredentials {
    private long chatRoomId;
    private String userName;
}
