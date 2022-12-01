package searchengine.services.other;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import searchengine.model.entity.Page;
import searchengine.model.entity.Site;
import searchengine.repository.PageRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.RecursiveTask;

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

            doc.selectXpath(linkXPath).forEach(element -> {

                String link = element.attributes().get("href");
                if (link.matches("/.+ ")) {
                    System.out.println("link ->" + url);
                    pageSet.addAll(new SiteParser(url.append(link), site, pageRepository).compute());

                }
            });
        }
        System.out.println(pageSet.size());
        pageSet.forEach(System.out::println);
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
