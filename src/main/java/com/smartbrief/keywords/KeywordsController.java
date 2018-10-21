package com.smartbrief.keywords;

import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KeywordsController {
	private final KeywordsApp keywordsApp;
		
	public KeywordsController(KeywordsApp keywordsApp) {
		this.keywordsApp = keywordsApp;
	}

	@RequestMapping("/{id}")
	WebResult home(@PathVariable("id") UUID id) throws Exception {
		return keywordsApp.runForId(id, new WebResultHandler());
	}
}