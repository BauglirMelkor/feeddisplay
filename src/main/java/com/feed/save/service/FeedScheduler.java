package com.feed.save.service;

import java.net.URL;
import java.sql.PreparedStatement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.feed.save.config.ConfigProperties;
import com.feed.save.repository.FeedRepository;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

@Service
public class FeedScheduler {

	private FeedRepository feedRepository;
	private ConfigProperties configProperties;

	@Autowired
	public FeedScheduler(FeedRepository feedRepository, ConfigProperties configProperties) {
		this.feedRepository = feedRepository;
		this.configProperties = configProperties;
	}

	@Scheduled(fixedRateString ="${feed.interval}")
	public void scheduleRssReader() {
		try {

			try (XmlReader reader = new XmlReader(new URL(configProperties.getUrl()))) {
				SyndFeed feed = new SyndFeedInput().build(reader);

				for (SyndEntry entry : feed.getEntries()) {
					Object[] params = new Object[] { entry.getTitle().hashCode(), entry.getTitle(), entry.getLink(),
							entry.getDescription().getValue().subSequence(0, 254), entry.getPublishedDate() };
					feedRepository.insert(params);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
