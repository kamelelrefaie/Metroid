package com.metroid.metroid.admin.feedback;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public Map<String, String> sendFeedback(FeedbackRequest feedbackRequest) {
        Map<String, String> map = new HashMap<>();
        Feedback feedback = Feedback.builder().content(feedbackRequest.getContent())
                .date(LocalDateTime.now())
                .rate(feedbackRequest.getRate())
                .subject(feedbackRequest.getSubject())
                .userId(feedbackRequest.getUserId())
                .build();

        try {
            feedbackRepository.save(feedback);
            map.put("message", "Feedback sent");
        } catch (Exception e) {
            map.put("message", "error");
        }
        return map;
    }

    public List<FeedbackGetRequest> getFeedback() {
        var list = feedbackRepository.findAll();
        var returnList = new ArrayList<FeedbackGetRequest>();
        for (var item :
                list) {
            var feedback = new FeedbackGetRequest(item.getSubject(), item.getContent(), item.getDate(), item.getRate());
            returnList.add(feedback);
        }

        return returnList;

    }
}
