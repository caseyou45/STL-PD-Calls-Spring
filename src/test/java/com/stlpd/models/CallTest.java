package com.stlpd.models;

import com.stlpd.model.Call;
import com.stlpd.model.Location;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallTest {

    @Test
    public void testCallConstructor() {
        Location locationEntity = new Location();
        locationEntity.setId(1L);

        LocalDateTime datetime = LocalDateTime.now();
        String eventID = "123";
        String location = "Original Location";
        String modifiedLocation = "Modified Location";
        String type = "Emergency";

        Call call = new Call(datetime, eventID, location, modifiedLocation, type, locationEntity);

        assertEquals(datetime, call.getDatetime());
        assertEquals(eventID, call.getEventID());
        assertEquals(location, call.getLocation());
        assertEquals(modifiedLocation, call.getModifiedLocation());
        assertEquals(type, call.getType());
        assertEquals(locationEntity, call.getLocationEntity());
    }

}
