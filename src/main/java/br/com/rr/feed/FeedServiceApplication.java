package br.com.rr.feed;

import br.com.rr.feed.service.FeedService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FeedServiceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(FeedServiceApplication.class, args);
		FeedService feedService = applicationContext.getBean(FeedService.class);
		feedService.initFeed("feed.xml");
	}
}
