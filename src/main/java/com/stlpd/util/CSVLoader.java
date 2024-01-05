package com.stlpd.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stlpd.respository.CallRepository;
import com.stlpd.model.Call;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class CSVLoader {

    private final CallRepository callRepository;

    @Autowired
    public CSVLoader(CallRepository callRepository) {
        this.callRepository = callRepository;

        // loadCSVData();
    }

    public void loadCSVData() {

        try (BufferedReader reader = new BufferedReader(
                new FileReader("/Users/CWilson/Desktop/Python/pd/updated_stl.csv"))) {
            String line;

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                String callDateTimeString = parts[0].trim();
                String callIDString = parts[1].trim();
                String callLocationString = parts[2].trim();
                String callTypeString = parts[3].trim();

                Optional<Call> existingCall = callRepository.findByEventID(callIDString);

                if (!existingCall.isPresent()) {
                    Call call = new Call();
                    LocalDateTime localDateTime = LocalDateTime.parse(callDateTimeString, formatter);
                    call.setDatetime(localDateTime);
                    call.setEventID(callIDString);
                    call.setLocation(callLocationString);
                    call.setType(callTypeString);

                    callRepository.save(call);
                }
            }

        } catch (Exception e) {
            System.out.println("CSV " + e.getMessage());
        }
    }
}
