package com.morkva.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by vladyslav on 02.05.15.
 */

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @Column(name = "short_description")
    private String shortDescr;

    @Column(name = "current_money")
    private int currentMoney;

    @Column(name = "need_money")
    private int needMoney;

    @Column(name = "adding_date")
    @Temporal(TemporalType.DATE)
    private Date addingDate;

    @Column(name = "ending_date")
    @Temporal(TemporalType.DATE)
    private Date endingDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "full_description_id")
    private FullDescription fullDescription;

    @Column(name = "successfully_finished")
    private boolean successfullyFinished;

    private Project(Builder builder) {
        id = builder.id;
        name = builder.name;
        shortDescr = builder.shortDescr;
        currentMoney = builder.currentMoney;
        needMoney = builder.needMoney;
        addingDate = builder.addingDate;
        endingDate = builder.endingDate;
        category = builder.category;
        user = builder.user;
        fullDescription = builder.fullDescription;
        successfullyFinished = builder.successfullyFinished;
    }

    public Project() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortDescr() {
        return shortDescr;
    }

    public int getCurrentMoney() {
        return currentMoney;
    }

    public int getNeedMoney() {
        return needMoney;
    }

    public Date getAddingDate() {
        return addingDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public Category getCategory() {
        return category;
    }

    public User getUser() {
        return user;
    }

    public FullDescription getFullDescription() {
        return fullDescription;
    }

    public boolean isSuccessfullyFinished() {
        return successfullyFinished;
    }

    public static class Builder {
        private Integer id;
        private String name;
        private String shortDescr;
        private int currentMoney;
        private int needMoney;
        private Date addingDate;
        private Date endingDate;
        private Category category;
        private User user;
        private FullDescription fullDescription;
        private boolean successfullyFinished;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setShortDescr(String shortDescr) {
            this.shortDescr = shortDescr;
            return this;
        }

        public Builder setCurrentMoney(int currentMoney) {
            this.currentMoney = currentMoney;
            return this;
        }

        public Builder setNeedMoney(int needMoney) {
            this.needMoney = needMoney;
            return this;
        }

        public Builder setAddingDate(Date addingDate) {
            this.addingDate = addingDate;
            return this;
        }

        public Builder setEndingDate(Date endingDate) {
            this.endingDate = endingDate;
            return this;
        }

        public Builder setCategory(Category category) {
            this.category = category;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setFullDescription(FullDescription fullDescription) {
            this.fullDescription = fullDescription;
            return this;
        }

        public Builder setSuccessfullyFinished(boolean successfullyFinished) {
            this.successfullyFinished = successfullyFinished;
            return this;
        }

        public Project build() {
            return new Project(this);
        }
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShortDescr(String shortDescr) {
        this.shortDescr = shortDescr;
    }

    public void setCurrentMoney(int currentMoney) {
        this.currentMoney = currentMoney;
    }

    public void setNeedMoney(int needMoney) {
        this.needMoney = needMoney;
    }

    public void setAddingDate(Date addingDate) {
        this.addingDate = addingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFullDescription(FullDescription fullDescription) {
        this.fullDescription = fullDescription;
    }

    public void setSuccessfullyFinished(boolean successfullyFinished) {
        this.successfullyFinished = successfullyFinished;
    }
}
