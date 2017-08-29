package com.kanbansimulator.webapi;
/**
 * Created by lucascolucci on 12/1/16.
 */
import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Column> columns;

    public Board() {
        this.columns = new ArrayList<Column>();
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public void addColumn(Column column){
        this.columns.add(column);
    }

    public int numberOfCardsInColumns(List<String> columnNames){
        int numberOfCards = 0;
        for(Column column : columns){
            for(String name : columnNames){
                if(name.contentEquals(column.getName())){
                    numberOfCards += column.getCards().size();
                }
            }
        }
        return numberOfCards;
    }


    public boolean allDone(){
        Column lastColumn = columns.get(columns.size()-1);
        if(lastColumn.numberOfCards() >= lastColumn.getWipLimit())
            return true;
        return false;
    }
}
