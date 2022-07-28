package com.metroid.metroid.admin.feedback;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class FeedbackGetRequest {
    private String subject;
    private String content;
    private LocalDateTime date;
    private float rate;

}
