package com.feed.save.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feed.save.dto.FeedDto;
import com.feed.save.exception.NoResultsReturned;
import com.feed.save.repository.FeedRepository;

@Service
public class FeedService {

	private FeedRepository feedRepository;

	@Autowired
	public FeedService(FeedRepository feedRepository) {
		this.feedRepository = feedRepository;
	}

	public List<FeedDto> getFeeds() throws NoResultsReturned {

		List<FeedDto> feedList = feedRepository.getFeeds();
		if (feedList.size() == 0) {
			throw new NoResultsReturned();
		}
		return feedList;

	}

	public List<FeedDto> getLast10Feeds() throws NoResultsReturned {

		List<FeedDto> feedList = feedRepository.getLast10Feeds();
		if (feedList.size() == 0) {
			throw new NoResultsReturned();
		}
		return feedList;
	}

}
