package com.hongjie.web.rest;

import com.hongjie.Application;
import com.hongjie.domain.WineSide;
import com.hongjie.repository.WineSideRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the WineSideResource REST controller.
 *
 * @see WineSideResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class WineSideResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAA";
    private static final String UPDATED_NAME = "BBBBB";
    private static final String DEFAULT_TITLE = "AAAAA";
    private static final String UPDATED_TITLE = "BBBBB";

    private static final BigDecimal DEFAULT_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRICE = new BigDecimal(2);
    private static final String DEFAULT_IMAGE = "AAAAA";
    private static final String UPDATED_IMAGE = "BBBBB";
    private static final String DEFAULT_DESCRIPTION = "AAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBB";
    private static final String DEFAULT_DETAIL_INFO = "AAAAA";
    private static final String UPDATED_DETAIL_INFO = "BBBBB";

    private static final Boolean DEFAULT_ACTIVE = false;
    private static final Boolean UPDATED_ACTIVE = true;
    private static final String DEFAULT_ORDER_TAG = "AAAAA";
    private static final String UPDATED_ORDER_TAG = "BBBBB";
    private static final String DEFAULT_PLACEHOLDER1 = "AAAAA";
    private static final String UPDATED_PLACEHOLDER1 = "BBBBB";
    private static final String DEFAULT_PLACEHOLDER2 = "AAAAA";
    private static final String UPDATED_PLACEHOLDER2 = "BBBBB";

    @Inject
    private WineSideRepository wineSideRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restWineSideMockMvc;

    private WineSide wineSide;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        WineSideResource wineSideResource = new WineSideResource();
        ReflectionTestUtils.setField(wineSideResource, "wineSideRepository", wineSideRepository);
        this.restWineSideMockMvc = MockMvcBuilders.standaloneSetup(wineSideResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        wineSide = new WineSide();
        wineSide.setName(DEFAULT_NAME);
        wineSide.setTitle(DEFAULT_TITLE);
        wineSide.setPrice(DEFAULT_PRICE);
        wineSide.setImage(DEFAULT_IMAGE);
        wineSide.setDescription(DEFAULT_DESCRIPTION);
        wineSide.setDetailInfo(DEFAULT_DETAIL_INFO);
        wineSide.setActive(DEFAULT_ACTIVE);
        wineSide.setOrderTag(DEFAULT_ORDER_TAG);
        wineSide.setPlaceholder1(DEFAULT_PLACEHOLDER1);
        wineSide.setPlaceholder2(DEFAULT_PLACEHOLDER2);
    }

    @Test
    @Transactional
    public void createWineSide() throws Exception {
        int databaseSizeBeforeCreate = wineSideRepository.findAll().size();

        // Create the WineSide

        restWineSideMockMvc.perform(post("/api/wineSides")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(wineSide)))
                .andExpect(status().isCreated());

        // Validate the WineSide in the database
        List<WineSide> wineSides = wineSideRepository.findAll();
        assertThat(wineSides).hasSize(databaseSizeBeforeCreate + 1);
        WineSide testWineSide = wineSides.get(wineSides.size() - 1);
        assertThat(testWineSide.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testWineSide.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testWineSide.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testWineSide.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testWineSide.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testWineSide.getDetailInfo()).isEqualTo(DEFAULT_DETAIL_INFO);
        assertThat(testWineSide.getActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testWineSide.getOrderTag()).isEqualTo(DEFAULT_ORDER_TAG);
        assertThat(testWineSide.getPlaceholder1()).isEqualTo(DEFAULT_PLACEHOLDER1);
        assertThat(testWineSide.getPlaceholder2()).isEqualTo(DEFAULT_PLACEHOLDER2);
    }

    @Test
    @Transactional
    public void checkPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = wineSideRepository.findAll().size();
        // set the field null
        wineSide.setPrice(null);

        // Create the WineSide, which fails.

        restWineSideMockMvc.perform(post("/api/wineSides")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(wineSide)))
                .andExpect(status().isBadRequest());

        List<WineSide> wineSides = wineSideRepository.findAll();
        assertThat(wineSides).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllWineSides() throws Exception {
        // Initialize the database
        wineSideRepository.saveAndFlush(wineSide);

        // Get all the wineSides
        restWineSideMockMvc.perform(get("/api/wineSides"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(wineSide.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
                .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.intValue())))
                .andExpect(jsonPath("$.[*].image").value(hasItem(DEFAULT_IMAGE.toString())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
                .andExpect(jsonPath("$.[*].detailInfo").value(hasItem(DEFAULT_DETAIL_INFO.toString())))
                .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())))
                .andExpect(jsonPath("$.[*].orderTag").value(hasItem(DEFAULT_ORDER_TAG.toString())))
                .andExpect(jsonPath("$.[*].placeholder1").value(hasItem(DEFAULT_PLACEHOLDER1.toString())))
                .andExpect(jsonPath("$.[*].placeholder2").value(hasItem(DEFAULT_PLACEHOLDER2.toString())));
    }

    @Test
    @Transactional
    public void getWineSide() throws Exception {
        // Initialize the database
        wineSideRepository.saveAndFlush(wineSide);

        // Get the wineSide
        restWineSideMockMvc.perform(get("/api/wineSides/{id}", wineSide.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(wineSide.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.intValue()))
            .andExpect(jsonPath("$.image").value(DEFAULT_IMAGE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.detailInfo").value(DEFAULT_DETAIL_INFO.toString()))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.orderTag").value(DEFAULT_ORDER_TAG.toString()))
            .andExpect(jsonPath("$.placeholder1").value(DEFAULT_PLACEHOLDER1.toString()))
            .andExpect(jsonPath("$.placeholder2").value(DEFAULT_PLACEHOLDER2.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingWineSide() throws Exception {
        // Get the wineSide
        restWineSideMockMvc.perform(get("/api/wineSides/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWineSide() throws Exception {
        // Initialize the database
        wineSideRepository.saveAndFlush(wineSide);

		int databaseSizeBeforeUpdate = wineSideRepository.findAll().size();

        // Update the wineSide
        wineSide.setName(UPDATED_NAME);
        wineSide.setTitle(UPDATED_TITLE);
        wineSide.setPrice(UPDATED_PRICE);
        wineSide.setImage(UPDATED_IMAGE);
        wineSide.setDescription(UPDATED_DESCRIPTION);
        wineSide.setDetailInfo(UPDATED_DETAIL_INFO);
        wineSide.setActive(UPDATED_ACTIVE);
        wineSide.setOrderTag(UPDATED_ORDER_TAG);
        wineSide.setPlaceholder1(UPDATED_PLACEHOLDER1);
        wineSide.setPlaceholder2(UPDATED_PLACEHOLDER2);

        restWineSideMockMvc.perform(put("/api/wineSides")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(wineSide)))
                .andExpect(status().isOk());

        // Validate the WineSide in the database
        List<WineSide> wineSides = wineSideRepository.findAll();
        assertThat(wineSides).hasSize(databaseSizeBeforeUpdate);
        WineSide testWineSide = wineSides.get(wineSides.size() - 1);
        assertThat(testWineSide.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testWineSide.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testWineSide.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testWineSide.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testWineSide.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testWineSide.getDetailInfo()).isEqualTo(UPDATED_DETAIL_INFO);
        assertThat(testWineSide.getActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testWineSide.getOrderTag()).isEqualTo(UPDATED_ORDER_TAG);
        assertThat(testWineSide.getPlaceholder1()).isEqualTo(UPDATED_PLACEHOLDER1);
        assertThat(testWineSide.getPlaceholder2()).isEqualTo(UPDATED_PLACEHOLDER2);
    }

    @Test
    @Transactional
    public void deleteWineSide() throws Exception {
        // Initialize the database
        wineSideRepository.saveAndFlush(wineSide);

		int databaseSizeBeforeDelete = wineSideRepository.findAll().size();

        // Get the wineSide
        restWineSideMockMvc.perform(delete("/api/wineSides/{id}", wineSide.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<WineSide> wineSides = wineSideRepository.findAll();
        assertThat(wineSides).hasSize(databaseSizeBeforeDelete - 1);
    }
}
