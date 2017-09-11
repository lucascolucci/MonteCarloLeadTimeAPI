package com.kanbansimulator.webapi;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/run/**")
@RestController
public class RunSimulation {

    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String index(@RequestParam(value = "columnNames") List<String> columnNames,
                        @RequestParam(value = "wipLimits") List<Integer> wipLimits,
                        @RequestParam(value = "leadTimeCandidatesPerColumn")List<List<Integer>> leadTimeCandidatesPerColumn,
                        @RequestParam(value = "namesOfColumnsAffectedByGlobalWIPLimit")List<String> namesOfColumnsAffectedByGlobalWIPLimit,
                        @RequestParam(value = "globalWipLimit")Integer globalWipLimit) {

        return Orchestrator.run(columnNames, wipLimits, leadTimeCandidatesPerColumn, namesOfColumnsAffectedByGlobalWIPLimit, globalWipLimit);
    }
}