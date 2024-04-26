package philoarte.jaemin.api.common.service;

import philoarte.jaemin.api.common.domain.Crawler;

import java.io.IOException;
import java.util.List;

public interface CrawlerService {
    List<?> scrapFunding(Crawler crawler) throws IOException;
}
