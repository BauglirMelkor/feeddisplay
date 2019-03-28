package com.feed.save;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.feed.save.controller.FeedController;
import com.feed.save.dto.FeedDto;
import com.feed.save.service.FeedService;

@RunWith(SpringRunner.class)
@WebMvcTest(FeedController.class)
public class FeedDisplayerRestTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private FeedService service;

	@Test
	public void getFeedList() throws Exception {

		FeedDto feedDto = new FeedDto();
		feedDto.setTitle("Java");
		List<FeedDto> lastFeedList = Arrays.asList(feedDto);
		Mockito.when(service.getLast10Feeds()).thenReturn(lastFeedList);

		mvc.perform(get("/last10Feeds").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].title", is(feedDto.getTitle())));
	}
}
