package com.kanbansimulator.webapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by lucascolucci on 12/2/16.
 */
public class Orchestrator {

    public static String run(List<String> columnNames, List<Integer> wipLimits, List<List<Integer>> leadTimeCandidatesPerColumn, List<String> namesOfColumnsAffectedByGlobalWIPLimit,Integer globalWIPLimit)
    {

        List<Integer> leadTimes = new ArrayList<Integer>();
        for(int i = 0; i < 5000; i++){
            Simulator simulator = new Simulator(columnNames, wipLimits, leadTimeCandidatesPerColumn, namesOfColumnsAffectedByGlobalWIPLimit, globalWIPLimit);
            simulator.initializeBoard();
            leadTimes.add(simulator.run());
        }

       return leadTimes.toString();
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
}