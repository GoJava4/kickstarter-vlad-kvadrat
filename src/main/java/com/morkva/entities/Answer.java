package com.morkva.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    private String answer;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Answer() {
    }

    public User getUser() {
        return user;
    }

    public Question getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getDate() {
        return date;
    }
}
