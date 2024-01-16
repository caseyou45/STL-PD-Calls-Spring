package com.stlpd.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stlpd.respository.CallRepository;
import com.stlpd.respository.IncidentRepository;
import com.stlpd.model.Call;
import com.stlpd.model.Incident;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;

@Component
public class CSVLoader {

    private final CallRepository callRepository;
    private final IncidentRepository incidentRepository;

    public CSVLoader(CallRepository callRepository, IncidentRepository incidentRepository) {
        this.callRepository = callRepository;
        this.incidentRepository = incidentRepository;

        // loadCSVCallsData();

    }

    public void loadCSVCallsData() {

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
            System.out.println("Calls CSV " + e.getMessage());
        }

    }

    public void loadIncidentCsvData() {
        try (BufferedReader reader = new BufferedReader(
                new FileReader("/Users/CWilson/Desktop/Data/December2023.csv"))) {
            String line;

            reader.readLine();

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                String[] values = new String[16];

                Arrays.fill(values, "");

                for (int i = 0; i < parts.length && i < values.length; i++) {
                    values[i] = parts[i].trim();
                }

                Optional<Incident> existingIncident = incidentRepository.findByInciId(values[0]);

                if (!existingIncident.isPresent()) {
                    Incident incident = new Incident();

                    incident.setInciId(values[0]);
                    incident.setDateInc(values[1]);
                    incident.setTimeInc(values[2]);
                    incident.setOffense(values[3]);
                    incident.setNibrsGrp(values[4]);
                    incident.setNibrsCode(values[5]);
                    incident.setBeat(values[6]);
                    incident.setDistrict(values[7]);
                    incident.setNeighborhood(values[8]);
                    incident.setLatitude(values[9]);
                    incident.setLongitude(values[10]);
                    incident.setIncidentLocation(values[11]);
                    incident.setLocationStreet2Apt(values[12]);
                    incident.setCity(values[13]);
                    incident.setState(values[14]);
                    incident.setZip(values[15]);

                    incidentRepository.save(incident);

                }
            }

        } catch (Exception e) {

            System.out.println("Incident CSV " + e);
        }
    }
}
