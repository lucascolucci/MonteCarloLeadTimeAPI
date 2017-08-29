package com.kanbansimulator.webapi;

/**
 * Created by lucascolucci on 12/1/16.
 */
public class Card {
    private int totalLeadTime;
    private int timeLeft;

    public Card() {
        totalLeadTime = 0;
        timeLeft = 0;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public int getTotalLeadTime() {
        return totalLeadTime;
    }

    public void setTotalLeadTime(int totalLeadTime) {
        this.totalLeadTime = totalLeadTime;
    }

    public void incrementTotalLeadTime(){
        totalLeadTime++;
    }

    public void decreaseTimeLeft(){
        if(timeLeft > 0)
            timeLeft--;
    }

    public void printCard(){
        System.out.print("       |-- "+this.getTotalLeadTime()+","+this.getTimeLeft()+" --|");
    }
}
