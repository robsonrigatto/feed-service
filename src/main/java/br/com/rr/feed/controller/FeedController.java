package br.com.rr.feed.controller;

import br.com.rr.feed.service.FeedService;
import br.com.rr.feed.vo.FeedVO;
import br.com.rr.feed.vo.ItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("feeds")

public class FeedController {
	
	@Autowired
	private FeedService feedService;

	@ResponseBody
	@GetMapping
	public ResponseEntity<FeedVO> getFeed(@RequestHeader(value = "Authorization") String authorization) {
		try {
			FeedVO feed = this.feedService.getFeed();
			return new ResponseEntity<FeedVO>(feed, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

    @PostMapping(value = "add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addItem(@RequestBody ItemVO itemVO, @RequestHeader(value = "Authorization") String authorization) {
        try {
            this.feedService.addItem(itemVO);
            return new ResponseEntity<Void>(HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
