package com.brainventory_mgmt.assets.enums;

import lombok.Getter;

@Getter
public enum RoomTypes {
    PRIVATE_OFFICE("private_office"),
    OPEN_PLAN_OFFICE("open_plan_office"),
    CUBICLE("cubicle"),
    MEETING_ROOM("meeting_room"),
    CONFERENCE_ROOM("conference_room"),
    CALL_CENTER("call_center"),
    RECEPTION("reception"),
    WAITING_AREA("waiting_area"),
    CAFETERIA("cafeteria"),
    BREAK_ROOM("break_room"),
    LACTATION_ROOM("lactation_room"),
    SERVER_ROOM("server_room"),
    ARCHIVES("archives"),
    LOUNGE("lounge"),
    GYM("gym"),
    GAME_ROOM("game_room"),
    MEDITATION_ROOM("meditation_room"),
    NAP_PODS("nap_pods"),
    CONTROL_ROOM("control_room"),
    MAINTENANCE_WORKSHOP("maintenance_workshop"),
    ELECTRICAL_ROOM("electrical_room"),
    WAREHOUSE("warehouse"),
    PARKING_LOT("parking_lot");

    private final String roomTypes;

    RoomTypes(String roomTypes){
        this.roomTypes = roomTypes;
    }
}
