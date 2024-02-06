package com.stlpd.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import com.stlpd.respository.CallRepository;
import com.stlpd.model.Call;

@Component
public class WebScraper {

    private final CallRepository callRepository;

    public WebScraper(CallRepository callRepository) {
        this.callRepository = callRepository;
    }

    @Scheduled(fixedRate = 60000)
    private void scrape() {
        try {
            String blogUrl = "https://www.slmpd.org/cfs.aspx";
            Document doc = Jsoup.connect(blogUrl).get();
            Elements trs = doc.select("tr");

            for (Element el : trs) {
                String callDateTimeString = el.select("td").get(0).text();
                String callIDString = el.select("td").get(1).text();
                String callLocationString = el.select("td").get(2).text();
                String callTypeString = el.select("td").get(3).text();

                Optional<Call> existingCall = callRepository.findByEventID(callIDString);

                if (!existingCall.isPresent()) {
                    Call call = new Call();
                    LocalDateTime localDateTime = parseDateTime(callDateTimeString);
                    call.setDatetime(localDateTime);
                    call.setEventID(callIDString);
                    call.setLocation(callLocationString);
                    call.setType(callTypeString);

                    callRepository.save(call);
                    System.out.println("Call added");
                }
            }
        } catch (Exception e) {
            System.out.println("Web " + e.getMessage());
        }
    }

    private LocalDateTime parseDateTime(String dateTimeString) {
        String formatTwoHour = "yyyy-MM-dd HH:mm:ss";
        String formatOneHour = "yyyy-MM-dd H:mm:ss";

        try {
            return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern(formatTwoHour));
        } catch (DateTimeParseException e1) {
            try {
                return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern(formatOneHour));
            } catch (DateTimeParseException e2) {
                System.out.println("Failed to parse date-time string: " + dateTimeString);
                return null;
            }
        }
    }
}
