package com.feed.save.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feed.save.dto.FeedDto;
import com.feed.save.exception.NoResultsReturned;
import com.feed.save.service.FeedService;

@RestController
public class FeedController {

	private FeedService feedService;

	@Autowired
	public FeedController(FeedService feedService) {
		this.feedService = feedService;
	}

	@RequestMapping("/feeds")
	public List<FeedDto> getFeeds() throws NoResultsReturned {
		return feedService.getFeeds();
	}
	
	@RequestMapping("/last10Feeds")
	public List<FeedDto> last10Feeds() throws NoResultsReturned {
		return feedService.getLast10Feeds();
	}

}
