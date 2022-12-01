package searchengine.model.dto.statistics;

import lombok.Data;

@Data
public class StatisticsRs {
    private boolean result;
    private StatisticsData statistics;
}
