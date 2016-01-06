package com.hongjie.web.rest;

import com.hongjie.Application;
import com.hongjie.domain.MenuPage;
import com.hongjie.repository.MenuPageRepository;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the MenuPageResource REST controller.
 *
 * @see MenuPageResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class MenuPageResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAA";
    private static final String UPDATED_NAME = "BBBBB";

    private static final Integer DEFAULT_PAGE_ID = 1;
    private static final Integer UPDATED_PAGE_ID = 2;
    private static final String DEFAULT_URL = "AAAAA";
    private static final String UPDATED_URL = "BBBBB";
    private static final String DEFAULT_DETAIL_INFO = "AAAAA";
    private static final String UPDATED_DETAIL_INFO = "BBBBB";

    private static final Boolean DEFAULT_ACTIVE = false;
    private static final Boolean UPDATED_ACTIVE = true;

    @Inject
    private MenuPageRepository menuPageRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restMenuPageMockMvc;

    private MenuPage menuPage;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MenuPageResource menuPageResource = new MenuPageResource();
        ReflectionTestUtils.setField(menuPageResource, "menuPageRepository", menuPageRepository);
        this.restMenuPageMockMvc = MockMvcBuilders.standaloneSetup(menuPageResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        menuPage = new MenuPage();
        menuPage.setName(DEFAULT_NAME);
        menuPage.setUrl(DEFAULT_URL);
        menuPage.setDetailInfo(DEFAULT_DETAIL_INFO);
        menuPage.setActive(DEFAULT_ACTIVE);
    }

    @Test
    @Transactional
    public void createMenuPage() throws Exception {
        int databaseSizeBeforeCreate = menuPageRepository.findAll().size();

        // Create the MenuPage

        restMenuPageMockMvc.perform(post("/api/menuPages")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(menuPage)))
                .andExpect(status().isCreated());

        // Validate the MenuPage in the database
        List<MenuPage> menuPages = menuPageRepository.findAll();
        assertThat(menuPages).hasSize(databaseSizeBeforeCreate + 1);
        MenuPage testMenuPage = menuPages.get(menuPages.size() - 1);
        assertThat(testMenuPage.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMenuPage.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testMenuPage.getDetailInfo()).isEqualTo(DEFAULT_DETAIL_INFO);
        assertThat(testMenuPage.getActive()).isEqualTo(DEFAULT_ACTIVE);
    }

    @Test
    @Transactional
    public void getAllMenuPages() throws Exception {
        // Initialize the database
        menuPageRepository.saveAndFlush(menuPage);

        // Get all the menuPages
        restMenuPageMockMvc.perform(get("/api/menuPages"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(menuPage.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].pageId").value(hasItem(DEFAULT_PAGE_ID)))
                .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL.toString())))
                .andExpect(jsonPath("$.[*].detailInfo").value(hasItem(DEFAULT_DETAIL_INFO.toString())))
                .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())));
    }

    @Test
    @Transactional
    public void getMenuPage() throws Exception {
        // Initialize the database
        menuPageRepository.saveAndFlush(menuPage);

        // Get the menuPage
        restMenuPageMockMvc.perform(get("/api/menuPages/{id}", menuPage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(menuPage.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.pageId").value(DEFAULT_PAGE_ID))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL.toString()))
            .andExpect(jsonPath("$.detailInfo").value(DEFAULT_DETAIL_INFO.toString()))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingMenuPage() throws Exception {
        // Get the menuPage
        restMenuPageMockMvc.perform(get("/api/menuPages/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMenuPage() throws Exception {
        // Initialize the database
        menuPageRepository.saveAndFlush(menuPage);

		int databaseSizeBeforeUpdate = menuPageRepository.findAll().size();

        // Update the menuPage
        menuPage.setName(UPDATED_NAME);
        menuPage.setUrl(UPDATED_URL);
        menuPage.setDetailInfo(UPDATED_DETAIL_INFO);
        menuPage.setActive(UPDATED_ACTIVE);

        restMenuPageMockMvc.perform(put("/api/menuPages")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(menuPage)))
                .andExpect(status().isOk());

        // Validate the MenuPage in the database
        List<MenuPage> menuPages = menuPageRepository.findAll();
        assertThat(menuPages).hasSize(databaseSizeBeforeUpdate);
        MenuPage testMenuPage = menuPages.get(menuPages.size() - 1);
        assertThat(testMenuPage.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMenuPage.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testMenuPage.getDetailInfo()).isEqualTo(UPDATED_DETAIL_INFO);
        assertThat(testMenuPage.getActive()).isEqualTo(UPDATED_ACTIVE);
    }

    @Test
    @Transactional
    public void deleteMenuPage() throws Exception {
        // Initialize the database
        menuPageRepository.saveAndFlush(menuPage);

		int databaseSizeBeforeDelete = menuPageRepository.findAll().size();

        // Get the menuPage
        restMenuPageMockMvc.perform(delete("/api/menuPages/{id}", menuPage.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<MenuPage> menuPages = menuPageRepository.findAll();
        assertThat(menuPages).hasSize(databaseSizeBeforeDelete - 1);
    }
}
