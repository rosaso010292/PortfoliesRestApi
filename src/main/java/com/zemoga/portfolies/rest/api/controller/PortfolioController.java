package com.zemoga.portfolies.rest.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zemoga.portfolies.rest.api.configuration.TwitterApiConfiguration;
import com.zemoga.portfolies.rest.api.dto.TweetsResponse;
import com.zemoga.portfolies.rest.api.entity.model.Portfolio;
import com.zemoga.portfolies.rest.api.exceptions.ResourceNotFoundException;
import com.zemoga.portfolies.rest.api.service.PortfolioService;
import com.zemoga.portfolies.rest.api.util.Util;

import lombok.extern.log4j.Log4j;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

@Log4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
@Validated
public class PortfolioController {
	@Autowired
	private PortfolioService service;
	@Autowired
	private TwitterApiConfiguration twitterApi;

	@GetMapping("/portfolies")
	public ResponseEntity<List<Portfolio>> getPortfolies() {
		log.info("Init Get List of Portfolies....");
		List<Portfolio> lstPorfolies = new ArrayList<>();
		service.findAll().forEach(portfolio -> {
			lstPorfolies.add(portfolio);
		});

		if (lstPorfolies.isEmpty()) {
			log.info("Response: Records not found");
			return ResponseEntity.notFound().build();
		}
		log.info("Response: \n" + Util.getListPorfoliosToJSON(lstPorfolies));
		log.info("End Get List of Portfolies....");
		return ResponseEntity.ok().body(lstPorfolies);
	}

	@GetMapping("/portfolio/{id}")
	public ResponseEntity<Portfolio> getPortfolioById(@PathVariable Long id) {
		log.info("Init Get Portfolio By Id....");
		log.info("Request: {Id: " + id + "}");
		return service.findById(id).map(portfolio -> {
			log.info("Response: \n" + Util.getPorfolioToJSON(portfolio));
			log.info("End Get Portfolio By Id....");
			return ResponseEntity.ok().body(portfolio);
		}).orElseThrow(() -> new ResourceNotFoundException(Util.getMessageNotFound(id)));
	}

	@GetMapping("/portfolio")
	public ResponseEntity<List<Portfolio>> getPortfolioByTittle(
			@RequestParam(value = "tittle", required = true) String tittle) {
		log.info("Init Get Portfolio By Tittle....");
		log.info("Request: {tittle: " + tittle + "}");
		List<Portfolio> lstPorfolies = new ArrayList<>();
		service.findByTittle(tittle).forEach(portfolio -> {
			lstPorfolies.add(portfolio);
		});
		if (lstPorfolies.isEmpty()) {
			log.info("Response: Records not found");
			return ResponseEntity.notFound().build();
		}
		log.info("Response: \n" + Util.getListPorfoliosToJSON(lstPorfolies));
		log.info("End Get Portfolio By Tittle....");
		return ResponseEntity.ok(lstPorfolies);
	}

	@PostMapping("/portfolio")
	public ResponseEntity<Portfolio> createPortfolio(@RequestBody Portfolio portfolio) {
		log.info("Init Create Portfolio....");
		log.info("Request: \n" + Util.getPorfolioToJSON(portfolio));

		portfolio = service.save(portfolio);

		log.info("Response: \n" + Util.getPorfolioToJSON(portfolio));
		log.info("End Create Portfolio....");
		return ResponseEntity.status(HttpStatus.CREATED).body(portfolio);
	}

	@PutMapping("/portfolio/{id}")
	public ResponseEntity<Portfolio> updatePortfolio(@RequestBody Portfolio portfolio, @PathVariable Long id) {
		log.info("Init Update Portfolio...");
		log.info("Request: \n" + Util.getPorfolioToJSON(portfolio) + ", Id: " + id);
		return service.findById(id).map(portfoliodb -> {
			portfoliodb.setDescription(portfolio.getDescription());
			portfoliodb.setExperienceSummary(portfolio.getExperienceSummary());
			portfoliodb.setImageUrl(portfolio.getImageUrl());
			portfoliodb.setLastNames(portfolio.getLastNames());
			portfoliodb.setNames(portfolio.getNames());
			portfoliodb.setTittle(portfolio.getTittle());
			portfoliodb.setTwitterUserId(portfolio.getTwitterUserId());
			portfoliodb.setTwitterUserName(portfolio.getTwitterUserName());
			portfoliodb.setUserId(portfolio.getUserId());
			portfoliodb.setAddress(portfolio.getAddress());
			portfoliodb.setEmail(portfolio.getEmail());
			portfoliodb.setExperience(portfolio.getExperience());
			portfoliodb.setImagePath(portfolio.getImagePath());
			portfoliodb.setName(portfolio.getName());
			portfoliodb.setPhone(portfolio.getPhone());
			portfoliodb.setTwitterUser(portfolio.getTwitterUser());
			portfoliodb.setZipCode(portfolio.getZipCode());

			portfoliodb = service.save(portfoliodb);

			log.info("Response: \n" + Util.getPorfolioToJSON(portfoliodb));
			log.info("End Update Portfolio...");
			return ResponseEntity.status(HttpStatus.CREATED).body(portfoliodb);
		}).orElseThrow(() -> new ResourceNotFoundException(Util.getMessageNotFound(id)));

	}

	@GetMapping("/portfolio/tweets")
	public ResponseEntity<?> getTweetsByUserName(@RequestParam(value = "userName", required = true) String userName) {
		log.info("Init Get Tweets By UserName....");
		log.info("Request: {userName:" + userName + "}");
		Twitter twitter = twitterApi.getTwitterInstance();
		List<TweetsResponse> tweets = new ArrayList<>();
		ResponseList<Status> response;
		try {
			response = twitter.getUserTimeline(userName);
			if (!response.isEmpty()) {
				IntStream.range(0, 5).forEach(index -> {
					Status status = response.get(index);
					tweets.add(new TweetsResponse(status.getUser().getName(), status.getUser().getScreenName(),
							status.getText(), status.getUser().getProfileImageURL()));
					index++;
				});
			}
		} catch (TwitterException e) {
			log.info("Response: Error: " + e);
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}

		if (tweets.isEmpty()) {
			log.info("Response: Records not found");
			return ResponseEntity.notFound().build();
		}
		log.info("Response: \n" + Util.getListTweetToJSON(tweets));
		log.info("End Get Tweets By UserName....");
		return ResponseEntity.ok().body(tweets);
	}
}
