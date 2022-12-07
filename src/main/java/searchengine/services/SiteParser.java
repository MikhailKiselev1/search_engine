package searchengine.services;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import searchengine.model.entity.Page;
import searchengine.model.entity.Site;
import searchengine.repository.PageRepository;

import java.util.concurrent.RecursiveTask;

import java.util.HashSet;
import java.util.Set;


public class SiteParser extends RecursiveTask<Set<Page>> {

    private String linkXPath = "//*/a";

    private Document doc;
    private final StringBuilder url;
    private final Site site;
    private final PageRepository pageRepository;

    public SiteParser(StringBuilder url, Site site, PageRepository pageRepository) {
        this.url = url;
        this.site = site;
        this.pageRepository = pageRepository;
    }

    @SneakyThrows
    @Override
    public Set<Page> compute() {

        Set<Page> pageSet = new HashSet<>();

        doc = Jsoup.connect(url.toString())
                .userAgent("search engine")
                .referrer("http://www.google.com")
                .get();

        if (pageRepository.findByPath(url.toString()).isEmpty()) {

            pageSet.add(createPage());
            Set<SiteParser> taskSet = new HashSet<>();

            doc.selectXpath(linkXPath).forEach(element -> {

                String link = element.attributes().get("href");
                if (link.matches("/.+ ")) {
                    SiteParser siteParser = new SiteParser(url.append(link), site, pageRepository);
                    siteParser.fork();
                    taskSet.add(siteParser);

                }
                for (SiteParser task : taskSet) {
                    pageSet.addAll(task.join());
                }
            });
        }
        return pageSet;
    }

    private Page createPage() {
        Page page = new Page();
        page.setSite(site);
        page.setPath(url.toString());
        page.setCode(doc.connection().response().statusCode());
        page.setContext(doc.body().text());
        return page;
    }
}
