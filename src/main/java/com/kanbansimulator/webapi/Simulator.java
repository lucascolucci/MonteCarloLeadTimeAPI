package com.kanbansimulator.webapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by lucascolucci on 12/1/16.
 */
public class Simulator {
    private List<String> columnNames;
    private List<Integer> wipLimits;
    private List<List<Integer>> leadTimeCandidatesPerColumn;
    private List<String> namesOfColumnsAffectedByGlobalWIPLimit;
    private int globalWIPLimit;
    private Board board;

    public Simulator(List<String> columnNames, List<Integer> wipLimits, List<List<Integer>> leadTimeCandidatesPerColumn, List<String> namesOfColumnsAffectedByGlobalWIPLimit, int globalWIPLimit) {
        this.columnNames = columnNames;
        this.wipLimits = wipLimits;
        this.leadTimeCandidatesPerColumn = leadTimeCandidatesPerColumn;
        this.namesOfColumnsAffectedByGlobalWIPLimit = namesOfColumnsAffectedByGlobalWIPLimit;
        this.globalWIPLimit = globalWIPLimit;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }

    public List<Integer> getWipLimits() {
        return wipLimits;
    }

    public void setWipLimits(List<Integer> wipLimits) {
        this.wipLimits = wipLimits;
    }

    public void initializeBoard(){
        board = new Board();

        for(int i = 0; i < columnNames.size(); i++){
            Column column = new Column(columnNames.get(i), wipLimits.get(i), leadTimeCandidatesPerColumn.get(i));
            board.addColumn(column);
        }

        for(int i = 0; i < wipLimits.get(0); i++) {
            Card card = new Card();
            board.getColumns().get(0).addCard(card);
        }
    }

    public void printBoard() {
        List<Column> columns = board.getColumns();
        int maxSize = -1;
        for(Column c : columns){
            if(c.getCards().size()>maxSize)
                maxSize = c.getCards().size();
        }

        for(int i = -2; i < maxSize; i++){
            for(Column column : columns){
                if(i == -2) {
                    String name = column.getName();
                    if(name.length() < 18) {
                        for (int j = 0; j < (18 - name.length()); j++) {
                            if(j%2 == 0)
                                name = "-" + name;
                            else
                                name = name + "-";
                        }
                    }
                    System.out.print(name + "   ");
                }
                else if(i == -1) {
                    if(columns.indexOf(column) == 0)
                        System.out.print("    (" + column.getWipLimit() + ")");
                    else
                        System.out.print("             (" + column.getWipLimit() + ")");
                }
                else if(i >= column.getCards().size())
                    System.out.print("                  ");
                else
                    column.getCards().get(i).printCard();
            }
            System.out.println();
        }
    }

    public int run(){
        int totalDays;
        //printBoard();

        for(totalDays = 0; !board.allDone(); totalDays++){
            runDailySimulation();
            //printBoard();
        }
        //System.out.println("Total Elapsed Time - " + totalDays);
        return totalDays-1;
    }

    private void runDailySimulation() {
        for(int index = board.getColumns().size()-2; index >=0; index--){
            if(index == 0 && globalWipLimitReached(index))
                continue;
            //System.out.println(board.numberOfCardsInColumns(namesOfColumnsAffectedByGlobalWIPLimit)>=globalWIPLimit);
            moveCardsInColumn(index);
        }
    }

    private void moveCardsInColumn(int index) {
        Column column = board.getColumns().get(index);


        List<Card> cardsToBeRemoved = new ArrayList<Card>();
        for(Card card : column.getCards()){
            moveCardForward(index, column, cardsToBeRemoved, card);
        }
        column.getCards().removeAll(cardsToBeRemoved);
    }

    private void moveCardForward(int index, Column column, List<Card> cardsToBeRemoved, Card card) {
        if(index > 0) {
            card.incrementTotalLeadTime();
            card.decreaseTimeLeft();
        }
        Column thisColumn = column;
        Column nextColumn = board.getColumns().get(index + 1);
        for(int i = 1;
            nextColumn.hasSpace() && card.getTimeLeft() == 0 && index + i  < board.getColumns().size();
            i++) {
            if(index == 0 && globalWipLimitReached(index))
                break;
            card.setTimeLeft(simulateColumnLeadTime(nextColumn.getLeadTimeCandidates()));
            nextColumn.addCard(card);

            if(i > 1)
                thisColumn.getCards().remove(card);

            cardsToBeRemoved.add(card);

            thisColumn = nextColumn;
            if(index+i+1 == board.getColumns().size())
                continue;
            nextColumn = board.getColumns().get(index + i + 1);
        }
    }

    private boolean globalWipLimitReached(int index) {
        return board.numberOfCardsInColumns(namesOfColumnsAffectedByGlobalWIPLimit) >= globalWIPLimit;
    }

    private int simulateColumnLeadTime(List<Integer> leadTimeCandidates) {
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(leadTimeCandidates.size());
        Integer item = leadTimeCandidates.get(index);
        return item;
    }

}
