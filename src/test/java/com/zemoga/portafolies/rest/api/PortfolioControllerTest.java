package com.zemoga.portafolies.rest.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zemoga.portfolies.rest.api.configuration.TwitterApiConfiguration;
import com.zemoga.portfolies.rest.api.controller.PortfolioController;
import com.zemoga.portfolies.rest.api.entity.model.Portfolio;
import com.zemoga.portfolies.rest.api.service.PortfolioService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PortfolioController.class)
@ContextConfiguration(classes = { TwitterApiConfiguration.class })
@ComponentScan(basePackages = "com.zemoga")
public class PortfolioControllerTest {
	@MockBean
	private PortfolioService portfolioService;
	@Autowired
	private MockMvc mockMvc;
	private static Portfolio portfolio;
	private static Optional<Portfolio> opPorfolio;
	@SuppressWarnings("deprecation")
	private Long id = new Long(120);
	private String outputJsonPorfolio = "{\"idPortfolio\":120,\"description\":\"Java Developer\",\"experienceSummary\":\"Im Java Developer\",\"id\":0,\"imageUrl\":\"https://i.blogs.es/53044d/java/1366_2000.jpg\",\"lastNames\":\"Alejandro\",\"names\":\"Guerrero\",\"tittle\":\"Java Developer\",\"twitterUserId\":\"\",\"twitterUserName\":\"juanrocha\",\"userId\":\"\",\"address\":\"\",\"email\":\"\",\"experience\":\"\",\"imagePath\":\"\",\"name\":\"\",\"phone\":\"\",\"twitterUser\":\"\",\"zipCode\":\"\"}";

	@SuppressWarnings("deprecation")
	@BeforeClass
	public static void setUp() {
		portfolio = new Portfolio(new Long(120), "Java Developer", "Im Java Developer", 0,
				"https://i.blogs.es/53044d/java/1366_2000.jpg", "Alejandro", "Guerrero", "Java Developer", "",
				"juanrocha", "", "", "", "", "", "", "", "", "");
		opPorfolio = Optional.of(portfolio);
	}

	@Test
	public void testCreatePortfolio() throws Exception {
		Mockito.when(portfolioService.save(Mockito.any(Portfolio.class))).thenReturn(portfolio);

		mockMvc.perform(post("/api/v1/portfolio").contentType("application/json").content(mapToJson(portfolio)))
				.andExpect(status().isCreated()).andExpect(content().string(outputJsonPorfolio))
				.andExpect(jsonPath("$.description", is("Java Developer"))).andDo(print());
	}

	@Test
	public void testUpdatePortfolio() throws Exception {
		Mockito.when(portfolioService.findById(id)).thenReturn(opPorfolio);

		Mockito.when(portfolioService.save(Mockito.any(Portfolio.class))).thenReturn(portfolio);

		mockMvc.perform(put("/api/v1/portfolio/{id}", id).contentType("application/json").content(mapToJson(portfolio)))
				.andExpect(status().isCreated()).andExpect(content().string(outputJsonPorfolio)).andDo(print());
	}

	@Test
	public void testGetPortfolioById() throws Exception {
		Mockito.when(portfolioService.findById(id)).thenReturn(opPorfolio);

		mockMvc.perform(get("/api/v1/portfolio/{id}", id).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(outputJsonPorfolio)).andDo(print());
	}

	@Test
	public void testGetPortfolies() throws Exception {
		List<Portfolio> lstPortfolies = new ArrayList<>();
		lstPortfolies.add(portfolio);
		Mockito.when(portfolioService.findAll()).thenReturn(lstPortfolies);

		mockMvc.perform(get("/api/v1/portfolies").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string("[" + outputJsonPorfolio + "]")).andDo(print());
	}

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

}
