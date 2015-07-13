package com.morkva.entities;

import javax.persistence.*;

@Entity
@Table(name = "payment_bonuses")
public class PaymentBonus {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(name = "min_money")
    private int minMoney;

    @OneToOne
    @JoinColumn(name = "bonus_full_description_id")
    private FullDescription fullDescription;

    @Column(name = "bonuses_left")
    private int bonusesLeft;

    public PaymentBonus() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public int getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(int minMoney) {
        this.minMoney = minMoney;
    }

    public FullDescription getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(FullDescription fullDescription) {
        this.fullDescription = fullDescription;
    }

    public int getBonusesLeft() {
        return bonusesLeft;
    }

    public void setBonusesLeft(int bonusesLeft) {
        this.bonusesLeft = bonusesLeft;
    }

    public void decreaseBonusesLeft() {
        this.bonusesLeft -= 1;
    }
}
