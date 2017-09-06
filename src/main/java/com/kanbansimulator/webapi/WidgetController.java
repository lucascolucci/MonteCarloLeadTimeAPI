package com.kanbansimulator.webapi;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/api/**")
@RestController
public class WidgetController {

    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<String> index() {
        List<String> columnNames = new ArrayList<String>();
        ArrayList<Integer> leadTimes = new ArrayList<Integer>();
        List<Integer> wipLimits = new ArrayList<Integer>();
        List<List<Integer>> leadTimeCandidatesPerColumn = new ArrayList<List<Integer>>();
        List<String> namesOfColumnsAffectedByGlobalWIPLimit = new ArrayList<String>();
        Integer globalWipLimit = 9;

        columnNames.add("----BACKLOG----");
        columnNames.add(" --DEVELOPING--");
        columnNames.add("--CODE REVIEW--");
        columnNames.add("-READY TO TEST-");
        columnNames.add("----TESTING----");
        columnNames.add("-----DONE------");

        // Columns that will be affected by the global wip limit
        namesOfColumnsAffectedByGlobalWIPLimit.add(" --DEVELOPING--");
        namesOfColumnsAffectedByGlobalWIPLimit.add("--CODE REVIEW--");
        namesOfColumnsAffectedByGlobalWIPLimit.add("-READY TO TEST-");
        namesOfColumnsAffectedByGlobalWIPLimit.add("----TESTING----");

        // Number of Items still on our backlog
        wipLimits.add(87);
        // Max number of items allowed at Developing
        wipLimits.add(9);
        // Max number of items allowed at Developing
        wipLimits.add(9);
        // Max number of items allowed at Developing
        wipLimits.add(9);
        // Max number of items allowed at Developing
        wipLimits.add(9);
        // Same number as the backlog
        wipLimits.add(87);

        // Backlog
        leadTimeCandidatesPerColumn.add(new ArrayList<Integer>(Arrays.asList(0)));
        // Developing
        leadTimeCandidatesPerColumn.add(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 3, 0, 1, 0, 3, 2, 1, 1, 0, 1, 1, 4, 0, 0, 0, 0, 1, 5, 1, 1, 1, 2, 0, 0, 2, 0, 0, 1, 0, 0, 0, 0, 19, 0)));
        // Code Review
        leadTimeCandidatesPerColumn.add(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 3, 0, 0, 1, 0, 0, 2, 3, 0, 4, 1, 2, 3, 3, 2, 1, 7, 3, 1, 1, 3, 0, 1, 1, 1, 2, 2, 6, 3, 2, 9, 0, 9)));
        // Ready to Test
        leadTimeCandidatesPerColumn.add(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 6, 2, 0, 3, 5, 2, 0, 5, 1, 2, 0, 4, 0, 0, 0, 0, 2, 0, 2, 5, 0, 0, 1, 11, 14, 14)));
        // Testing
        leadTimeCandidatesPerColumn.add(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 1, 1, 3, 0, 0, 2, 1, 0, 3, 0, 0, 0, 0, 0, 2, 4, 4, 0, 2)));
        // Done
        leadTimeCandidatesPerColumn.add(new ArrayList<Integer>(Arrays.asList(0)));


        List<String> results = new ArrayList<>();
        results.add(columnNames.toString());
        results.add(namesOfColumnsAffectedByGlobalWIPLimit.toString());
        results.add(wipLimits.toString());
        results.add(leadTimeCandidatesPerColumn.toString());
        results.add(globalWipLimit.toString());
        return results;
    }
}