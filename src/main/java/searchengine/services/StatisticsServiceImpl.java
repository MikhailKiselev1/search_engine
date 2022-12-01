package searchengine.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import searchengine.config.Site;
import searchengine.config.SitesList;
import searchengine.model.dto.statistics.DetailedStatisticsItem;
import searchengine.model.dto.statistics.StatisticsData;
import searchengine.model.dto.statistics.StatisticsRs;
import searchengine.model.dto.statistics.TotalStatistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final Random random = new Random();
    private final SitesList sites;

    @Override
    public StatisticsRs getStatistics() {
        String[] statuses = { "INDEXED", "FAILED", "INDEXING" };
        String[] errors = {
                "Ошибка индексации: главная страница сайта не доступна",
                "Ошибка индексации: сайт не доступен",
                ""
        };

        TotalStatistics total = new TotalStatistics();
        total.setSites(sites.getSites().size());
        total.setIndexing(true);

        List<DetailedStatisticsItem> detailed = new ArrayList<>();
        List<Site> sitesList = sites.getSites();
        for(int i = 0; i < sitesList.size(); i++) {
            Site site = sitesList.get(i);

            int pages = random.nextInt(1_000);
            int lemmas = pages * random.nextInt(1_000);

            DetailedStatisticsItem item = DetailedStatisticsItem.builder()
                    .name(site.getName())
                    .url(site.getUrl())
                    .pages(pages)
                    .lemmas(lemmas)
                    .status(statuses[i % 3])
                    .error(errors[i % 3])
                    .statusTime(System.currentTimeMillis() - (random.nextInt(10_000)))
                    .pages(total.getPages() + pages)
                    .lemmas(total.getLemmas() + lemmas)
                    .build();

            detailed.add(item);
        }

        StatisticsRs response = new StatisticsRs();
        StatisticsData data = new StatisticsData();
        data.setTotal(total);
        data.setDetailed(detailed);
        response.setStatistics(data);
        response.setResult(true);
        return response;
    }
}
