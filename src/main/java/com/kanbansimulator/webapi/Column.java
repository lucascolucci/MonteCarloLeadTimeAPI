package com.kanbansimulator.webapi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucascolucci on 12/1/16.
 */
public class Column {
    private String name;
    private int wipLimit;
    private List<Card> cards;
    private List<Integer> leadTimeCandidates;

    public Column(String name, int wipLimit, List<Integer> leadTimeCandidates) {
        this.wipLimit = wipLimit;
        this.name = name;
        cards = new ArrayList<Card>();
        this.leadTimeCandidates = leadTimeCandidates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public int getWipLimit() {
        return wipLimit;
    }

    public void setWipLimit(int wipLimit) {
        this.wipLimit = wipLimit;
    }

    public List<Integer> getLeadTimeCandidates() {
        return leadTimeCandidates;
    }

    public void setLeadTimeCandidates(List<Integer> leadTimeCandidates) {
        this.leadTimeCandidates = leadTimeCandidates;
    }

    public void addCards(List<Card> cards){
        if(this.cards.size() + cards.size() > wipLimit)
            throw new StackOverflowError("Column reached its WIP Limit already! Addition denied.");
        else
            this.cards.addAll(cards);
    }

    public void addCard(Card card){
        if(cards.size() + 1 > wipLimit)
            throw new StackOverflowError("Column reached its WIP Limit already! Addition denied.");
        else
            cards.add(card);
    }

    public int numberOfCards(){
        return cards.size();
    }

    public boolean hasSpace() {
        if(cards.size() < wipLimit)
            return true;
        return false;
    }
}
