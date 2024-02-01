package com.stlpd.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            for (Element el : trs) {
                String callDateTimeString = el.select("td").get(0).text();
                String callIDString = el.select("td").get(1).text();
                String callLocationString = el.select("td").get(2).text();
                String callTypeString = el.select("td").get(3).text();

                Optional<Call> existingCall = callRepository.findByEventID(callIDString);

                if (!existingCall.isPresent()) {
                    Call call = new Call();
                    LocalDateTime localDateTime = LocalDateTime.parse(callDateTimeString, formatter);
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
}
