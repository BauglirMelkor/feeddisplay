package com.feed.save;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.feed.save.dto.FeedDto;
import com.feed.save.exception.NoResultsReturned;
import com.feed.save.repository.FeedRepository;
import com.feed.save.service.FeedService;

@RunWith(SpringRunner.class)
@JdbcTest 
public class FeedDisplayerServiceTests {

	@Autowired
	private FeedService feedService;
	@TestConfiguration
	static class FeedDisplayerServiceTestConfig {

		@Autowired
		private JdbcTemplate jdbcTemplate;

		@Bean
		public FeedService feedService() {
			return new FeedService(feedRepository());
		}

		@Bean
		public FeedRepository feedRepository() {
			return new FeedRepository(jdbcTemplate);
		}
	}

	@MockBean
	private FeedRepository feedRepository;

	@Test
	public void get10FeedsSuccessfully() throws Exception {

		FeedDto feedDto = new FeedDto();
		feedDto.setTitle("Java");
		List<FeedDto> lastFeedList = Arrays.asList(feedDto);
		Mockito.when(feedRepository.getLast10Feeds()).thenReturn(lastFeedList);
		List<FeedDto> serviceFeedList = feedService.getLast10Feeds();
		assertTrue(lastFeedList.size() == serviceFeedList.size());
		assertTrue(lastFeedList.get(0).getTitle().equals(serviceFeedList.get(0).getTitle()));
	}
	
	@Test(expected=NoResultsReturned.class)
	public void getFeedNoResultException() throws Exception {

		List<FeedDto> serviceFeedList = feedService.getLast10Feeds();
	}
}
