package com.metroid.metroid.admin.feedback;

import lombok.*;



@Getter
@Setter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class FeedbackRequest {

    private String subject;
    private String content;
    private float  rate;
    private long userId;
}
