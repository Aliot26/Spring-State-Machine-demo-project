package com.cloudservices.testtask.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationControllerTest {
//    private MockMvc mockMvc;
//
//    private ObjectMapper objectMapper;
//
//    private ApplicationRepository applicationRepository;
//
//    private HistoryRepository historyRepository;
//
//    @Autowired
//    public ApplicationControllerTest(MockMvc mockMvc, ObjectMapper objectMapper, ApplicationRepository applicationRepository, HistoryRepository historyRepository) {
//        this.mockMvc = mockMvc;
//        this.objectMapper = objectMapper;
//        this.applicationRepository = applicationRepository;
//        this.historyRepository = historyRepository;
//    }
//
//    @Test
//    @Transactional
//    void getSingleApplicationTest() throws Exception {
//        // given
//        Application newApplication = new Application();
//        newApplication.setTitle("Title");
//        newApplication.setContent("Content");
//        newApplication.setStatus(EStatus.CREATED);
//        applicationRepository.save(newApplication);
//
//        History newHistory = new History();
//        newHistory.setStatus(EStatus.CREATED);
//        newHistory.setAppId(newApplication.getId());
//        newHistory.setDateOfChange(LocalDateTime.now());
//        historyRepository.save(newHistory);
//
//        newApplication.getAppHistoryList().add(newHistory);
//        applicationRepository.save(newApplication);
//
//        // when
//        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/applications/" + newApplication.getId()))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().is(200))
//                .andReturn();
//
//        // then
//        Application application = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Application.class);
//        assertThat(application).isNotNull();
//        assertThat(application.getId()).isEqualTo(newApplication.getId());
//        assertThat(application.getTitle()).isEqualTo(newApplication.getTitle());
//        assertThat(application.getContent()).isEqualTo(newApplication.getContent());
//        assertThat(application.getAppHistoryList().size()).isEqualTo(1);
//    }

}
