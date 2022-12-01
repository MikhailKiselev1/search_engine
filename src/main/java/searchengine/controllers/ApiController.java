package searchengine.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import searchengine.model.dto.statistics.ErrorRs;
import searchengine.model.dto.statistics.StatisticsRs;
import searchengine.services.IndexService;
import searchengine.services.StatisticsService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {

    private final StatisticsService statisticsService;
    private final IndexService indexService;


    @GetMapping("/statistics")
    public ResponseEntity<StatisticsRs> statistics() {
        return ResponseEntity.ok(statisticsService.getStatistics());
    }

    @GetMapping("/startIndexing")
    public ResponseEntity<ErrorRs> startIndexing() {
        return ResponseEntity.ok(indexService.getStartIndexing());
    }
}
