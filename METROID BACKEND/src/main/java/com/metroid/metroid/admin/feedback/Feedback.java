package com.metroid.metroid.admin.feedback;

import com.metroid.metroid.login_cycle.appuser.AppUser;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Feedback {
    @SequenceGenerator(name = "feedback_sequence", sequenceName = "feedback_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(generator = "feedback_sequence", strategy = GenerationType.SEQUENCE)
    private long id;
    private String subject;
    private String content;
    private LocalDateTime date;
    private float rate;
    private long userId;
}
