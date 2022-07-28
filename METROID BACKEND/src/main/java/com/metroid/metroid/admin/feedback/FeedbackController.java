package com.metroid.metroid.admin.feedback;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/home/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    @PostMapping
    public Map<String,String> sendFeedback(@RequestBody FeedbackRequest feedbackRequest){
        return  feedbackService.sendFeedback(feedbackRequest);
    }

    @GetMapping
    public List<FeedbackGetRequest> getFeedback(){
        return  feedbackService.getFeedback();
    }
}
