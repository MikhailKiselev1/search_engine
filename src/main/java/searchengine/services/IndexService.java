package searchengine.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import searchengine.config.SitesList;
import searchengine.model.dto.statistics.ErrorRs;
import searchengine.model.entity.Site;
import searchengine.model.enums.Status;
import searchengine.repository.PageRepository;
import searchengine.repository.SiteRepository;
import searchengine.services.other.SiteParser;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class IndexService{

    private final SitesList sites;
    private final PageRepository pageRepository;
    private final SiteRepository siteRepository;


    public ErrorRs getStartIndexing() {

        sites.getSites().forEach(site -> {

            //deleted old information
            siteRepository.findByUrl(site.getUrl()).ifPresent( currentSite -> {
                currentSite.getPages().forEach(pageRepository::delete);
                siteRepository.delete(currentSite);
            });

            //Create new site
            Site currentSite = createSite(site);
            siteRepository.save(currentSite);

            //successes parsing
            try {
                System.out.println("try -> ");
                new SiteParser(new StringBuilder(site.getUrl()), currentSite, pageRepository)
                        .compute().forEach(pageRepository::save);
                currentSite.setStatus(Status.INDEXED);
                currentSite.setStatusTime(LocalDateTime.now());
                siteRepository.save(currentSite);
            }
            //failed parsing
            catch (Exception e) {
                currentSite.setStatus(Status.FAILED);
                currentSite.setStatusTime(LocalDateTime.now());
                siteRepository.save(currentSite);
                e.printStackTrace();
            }

        });

        return ErrorRs.builder().result(true).build();
    }

    private Site createSite (searchengine.config.Site configSite){
        Site currentSite = new Site();
        currentSite.setStatus(Status.INDEXING);
        currentSite.setStatusTime(LocalDateTime.now());
        currentSite.setName(configSite.getName());
        currentSite.setUrl(configSite.getUrl());
        return currentSite;
    }

}
