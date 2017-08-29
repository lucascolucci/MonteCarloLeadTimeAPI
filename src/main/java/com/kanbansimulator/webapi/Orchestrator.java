package com.kanbansimulator.webapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by lucascolucci on 12/2/16.
 */
public class Orchestrator {

    public static void run()
    {
        GoogleSheetsConnection googleSheetsConnection = new GoogleSheetsConnection();
                List<String> columnNames = new ArrayList<String>();
        List<Integer> wipLimits = new ArrayList<Integer>();
        List<List<Integer>> leadTimeCandidatesPerColumn = new ArrayList<List<Integer>>();
        List<String> namesOfColumnsAffectedByGlobalWIPLimit = new ArrayList<String>();
        Integer globalWIPLimit = initializeVariablesAndReturnGlobalWipLimit(columnNames, wipLimits, leadTimeCandidatesPerColumn, namesOfColumnsAffectedByGlobalWIPLimit);
        List<Integer> leadTimes = new ArrayList<Integer>();
        for(int i = 0; i < 5000; i++){
            Simulator simulator = new Simulator(columnNames, wipLimits, leadTimeCandidatesPerColumn, namesOfColumnsAffectedByGlobalWIPLimit, globalWIPLimit);
            simulator.initializeBoard();
            leadTimes.add(simulator.run());
        }

        //printArrayLineByLine(leadTimes);
        try {
            googleSheetsConnection.writeResults(leadTimes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Average LT is: " + average(leadTimes));
    }

    private static void printArrayLineByLine(List<Integer> values){
        for(Integer value : values){
            System.out.println(value);
        }
    }

    private static Double average(List<Integer> list){
        Double sum = 0.0;
        for(Integer i : list){
            sum += i;
        }
        return sum / list.size();
    }


    private static int initializeVariablesAndReturnGlobalWipLimit(List<String> columnNames, List<Integer> wipLimits, List<List<Integer>> leadTimeCandidatesPerColumn, List<String> namesOfColumnsAffectedByGlobalWIPLimit) {

        GoogleSheetsConnection googleSheetsConnection = new GoogleSheetsConnection();
        try {
            List<List<Object>> values = googleSheetsConnection.getLTValues();

            for(int i = 0; i < values.get(0).size(); i++){
                String columnName = values.get(0).get(i).toString();
                String globalLimit = values.get(1).get(i).toString();
                String wipLimit = values.get(2).get(i).toString();
                String leadTimeCandidates = values.get(3).get(i).toString();
                ArrayList<Integer> leadTimes = new ArrayList<Integer>();

                if(!columnName.equals("Column") && !columnName.equals("")) {
                    columnNames.add(columnName);
                    wipLimits.add(Integer.valueOf(wipLimit));
                    for(String s: Arrays.asList(leadTimeCandidates.split("\\s*,\\s*")))
                        leadTimes.add(Integer.valueOf(s));
                    leadTimeCandidatesPerColumn.add(leadTimes);
                }
                if(globalLimit.equals("yes"))
                    namesOfColumnsAffectedByGlobalWIPLimit.add(columnName);

            }

            int globalWIPLimit = Integer.valueOf(values.get(4).get(0).toString());

            //System.out.println("columnNames: " + columnNames);
            //System.out.println("namesOfColumnsAffectedByGlobalWIPLimit: " + namesOfColumnsAffectedByGlobalWIPLimit);
            //System.out.println("wipLimits: " + wipLimits);
            //System.out.println("leadTimes: " + leadTimeCandidatesPerColumn);
            //System.out.println("globalWipLimit: " + globalWIPLimit);

            //Global Wip Limit
            return globalWIPLimit;
        } catch (IOException e){

        }
//        columnNames.add("----BACKLOG----");
//        columnNames.add(" --DEVELOPING--");
//        columnNames.add("--CODE REVIEW--");
//        columnNames.add("-READY TO TEST-");
//        columnNames.add("----TESTING----");
//        columnNames.add("-----DONE------");
//
//        // Columns that will be affected by the global wip limit
//        namesOfColumnsAffectedByGlobalWIPLimit.add(" --DEVELOPING--");
//        namesOfColumnsAffectedByGlobalWIPLimit.add("--CODE REVIEW--");
//        namesOfColumnsAffectedByGlobalWIPLimit.add("-READY TO TEST-");
//        namesOfColumnsAffectedByGlobalWIPLimit.add("----TESTING----");
//
//        // Number of Items still on our backlog
//        wipLimits.add(87);
//        // Max number of items allowed at Developing
//        wipLimits.add(9);
//        // Max number of items allowed at Developing
//        wipLimits.add(9);
//        // Max number of items allowed at Developing
//        wipLimits.add(9);
//        // Max number of items allowed at Developing
//        wipLimits.add(9);
//        // Same number as the backlog
//        wipLimits.add(87);
//
//        // Backlog
//        leadTimeCandidatesPerColumn.add(new ArrayList<Integer>(Arrays.asList(0)));
//        // Developing
//        leadTimeCandidatesPerColumn.add(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 3, 0, 1, 0, 3, 2, 1, 1, 0, 1, 1, 4, 0, 0, 0, 0, 1, 5, 1, 1, 1, 2, 0, 0, 2, 0, 0, 1, 0, 0, 0, 0, 19, 0)));
//        // Code Review
//        leadTimeCandidatesPerColumn.add(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 3, 0, 0, 1, 0, 0, 2, 3, 0, 4, 1, 2, 3, 3, 2, 1, 7, 3, 1, 1, 3, 0, 1, 1, 1, 2, 2, 6, 3, 2, 9, 0, 9)));
//        // Ready to Test
//        leadTimeCandidatesPerColumn.add(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 6, 2, 0, 3, 5, 2, 0, 5, 1, 2, 0, 4, 0, 0, 0, 0, 2, 0, 2, 5, 0, 0, 1, 11, 14, 14)));
//        // Testing
//        leadTimeCandidatesPerColumn.add(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 1, 1, 3, 0, 0, 2, 1, 0, 3, 0, 0, 0, 0, 0, 2, 4, 4, 0, 2)));
//        // Done
//        leadTimeCandidatesPerColumn.add(new ArrayList<Integer>(Arrays.asList(0)));
    return 0;
    }
}