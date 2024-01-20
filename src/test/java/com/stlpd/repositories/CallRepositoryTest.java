package com.stlpd.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.stlpd.model.Call;
import com.stlpd.respository.CallRepository;
import com.stlpd.model.Location;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CallRepositoryTest {

    @Autowired
    private CallRepository callRepository;

    @Test
    @Transactional
    public void testFindByEventID() {
        Call call = createCall("123");
        callRepository.save(call);

        Optional<Call> foundCall = callRepository.findByEventID("123");

        assertTrue(foundCall.isPresent());
        assertEquals("123", foundCall.get().getEventID());
        callRepository.delete(call);
    }

    private Call createCall(String eventID) {
        return new Call(LocalDateTime.now(), eventID, "Location", "Modified Location", "Type", new Location());
    }

}
