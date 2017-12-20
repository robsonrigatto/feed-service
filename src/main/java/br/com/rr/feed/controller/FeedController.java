package br.com.rr.feed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.rr.feed.model.Feed;
import br.com.rr.feed.service.XMLParser;

@RestController
@RequestMapping("feeds")
public class FeedController {
	
	@Autowired
	private XMLParser xmlReader;

	@ResponseBody
	@GetMapping(path = "autoesporte")
	public ResponseEntity<Feed> autoesporte() {
		try {
			Feed feed = this.xmlReader.parseToJSON("http://revistaautoesporte.globo.com/rss/ultimas/feed.xml");
			return new ResponseEntity<Feed>(feed, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
