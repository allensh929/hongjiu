package com.hongjie.web.rest;

import com.hongjie.Application;
import com.hongjie.domain.Gift;
import com.hongjie.repository.GiftRepository;

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
 * Test class for the GiftResource REST controller.
 *
 * @see GiftResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class GiftResourceIntTest {

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
    private GiftRepository giftRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restGiftMockMvc;

    private Gift gift;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        GiftResource giftResource = new GiftResource();
        ReflectionTestUtils.setField(giftResource, "giftRepository", giftRepository);
        this.restGiftMockMvc = MockMvcBuilders.standaloneSetup(giftResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        gift = new Gift();
        gift.setName(DEFAULT_NAME);
        gift.setTitle(DEFAULT_TITLE);
        gift.setPrice(DEFAULT_PRICE);
        gift.setImage(DEFAULT_IMAGE);
        gift.setDescription(DEFAULT_DESCRIPTION);
        gift.setDetailInfo(DEFAULT_DETAIL_INFO);
        gift.setActive(DEFAULT_ACTIVE);
        gift.setOrderTag(DEFAULT_ORDER_TAG);
        gift.setPlaceholder1(DEFAULT_PLACEHOLDER1);
        gift.setPlaceholder2(DEFAULT_PLACEHOLDER2);
    }

    @Test
    @Transactional
    public void createGift() throws Exception {
        int databaseSizeBeforeCreate = giftRepository.findAll().size();

        // Create the Gift

        restGiftMockMvc.perform(post("/api/gifts")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(gift)))
                .andExpect(status().isCreated());

        // Validate the Gift in the database
        List<Gift> gifts = giftRepository.findAll();
        assertThat(gifts).hasSize(databaseSizeBeforeCreate + 1);
        Gift testGift = gifts.get(gifts.size() - 1);
        assertThat(testGift.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testGift.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testGift.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testGift.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testGift.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testGift.getDetailInfo()).isEqualTo(DEFAULT_DETAIL_INFO);
        assertThat(testGift.getActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testGift.getOrderTag()).isEqualTo(DEFAULT_ORDER_TAG);
        assertThat(testGift.getPlaceholder1()).isEqualTo(DEFAULT_PLACEHOLDER1);
        assertThat(testGift.getPlaceholder2()).isEqualTo(DEFAULT_PLACEHOLDER2);
    }

    @Test
    @Transactional
    public void getAllGifts() throws Exception {
        // Initialize the database
        giftRepository.saveAndFlush(gift);

        // Get all the gifts
        restGiftMockMvc.perform(get("/api/gifts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(gift.getId().intValue())))
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
    public void getGift() throws Exception {
        // Initialize the database
        giftRepository.saveAndFlush(gift);

        // Get the gift
        restGiftMockMvc.perform(get("/api/gifts/{id}", gift.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(gift.getId().intValue()))
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
    public void getNonExistingGift() throws Exception {
        // Get the gift
        restGiftMockMvc.perform(get("/api/gifts/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGift() throws Exception {
        // Initialize the database
        giftRepository.saveAndFlush(gift);

		int databaseSizeBeforeUpdate = giftRepository.findAll().size();

        // Update the gift
        gift.setName(UPDATED_NAME);
        gift.setTitle(UPDATED_TITLE);
        gift.setPrice(UPDATED_PRICE);
        gift.setImage(UPDATED_IMAGE);
        gift.setDescription(UPDATED_DESCRIPTION);
        gift.setDetailInfo(UPDATED_DETAIL_INFO);
        gift.setActive(UPDATED_ACTIVE);
        gift.setOrderTag(UPDATED_ORDER_TAG);
        gift.setPlaceholder1(UPDATED_PLACEHOLDER1);
        gift.setPlaceholder2(UPDATED_PLACEHOLDER2);

        restGiftMockMvc.perform(put("/api/gifts")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(gift)))
                .andExpect(status().isOk());

        // Validate the Gift in the database
        List<Gift> gifts = giftRepository.findAll();
        assertThat(gifts).hasSize(databaseSizeBeforeUpdate);
        Gift testGift = gifts.get(gifts.size() - 1);
        assertThat(testGift.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testGift.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testGift.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testGift.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testGift.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testGift.getDetailInfo()).isEqualTo(UPDATED_DETAIL_INFO);
        assertThat(testGift.getActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testGift.getOrderTag()).isEqualTo(UPDATED_ORDER_TAG);
        assertThat(testGift.getPlaceholder1()).isEqualTo(UPDATED_PLACEHOLDER1);
        assertThat(testGift.getPlaceholder2()).isEqualTo(UPDATED_PLACEHOLDER2);
    }

    @Test
    @Transactional
    public void deleteGift() throws Exception {
        // Initialize the database
        giftRepository.saveAndFlush(gift);

		int databaseSizeBeforeDelete = giftRepository.findAll().size();

        // Get the gift
        restGiftMockMvc.perform(delete("/api/gifts/{id}", gift.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Gift> gifts = giftRepository.findAll();
        assertThat(gifts).hasSize(databaseSizeBeforeDelete - 1);
    }
}
