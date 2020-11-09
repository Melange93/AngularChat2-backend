package com.reka.lakatos.angularchatbackend.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NewRoomMemberResponse {

    private final String newMemberName;
    private final String chatRoomName;
}
