package ru.mccarl.test.url.shortener;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.mccarl.url.shortener.Application;
import ru.mccarl.url.shortener.config.Config;
import ru.mccarl.url.shortener.model.UrlModel;
import ru.mccarl.url.shortener.service.UrlShortenerService;
import ru.mccarl.url.shortener.storage.UrlRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = {Application.class, Config.class})
public class ApiTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UrlRepository urlRepository;
    @Autowired
    private UrlShortenerService urlShortenerService;

    @Before
    public void init(){
        UrlModel urlModel = new UrlModel();
        urlModel.setShortUrl("http://localhost:8080/url/1");
        urlModel.setUrlKey("http://apple.com");
        urlModel.setUrlId(1L);
        List<UrlModel> list = new ArrayList<>();
        list.add(urlModel);
        when(urlRepository.findByUrlKey(anyString())).thenReturn(list);
        when(urlRepository.findByShortUrl(anyString())).thenReturn(list);
        when(urlRepository.save(any(UrlModel.class))).thenReturn(urlModel);
    }

    @Test
    public void getShortUrlTest() throws Exception {
        MvcResult result = mockMvc.perform(post("/url/")
                .content("http://apple.com1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().equals("http://localhost:8080/url/1"));
    }

    @Test
    public void useShortLinkTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/url/1"))
                .andDo(print())
                .andExpect(status().is(302))
                .andReturn();
        assertTrue(result.getResponse().getRedirectedUrl().equals("http://apple.com"));
    }
    @Test
    public void unknowShortUrlTest() throws Exception {
        when(urlRepository.findByShortUrl(anyString())).thenReturn(null);
        try{
            MvcResult result = mockMvc.perform(get("/url/1"))
                    .andDo(print())
                    .andExpect(status().is(302))
                    .andReturn();
            assertTrue(false);
        }catch (Exception e){
            assertTrue("Cant find short url".equals(e.getCause().getMessage()));
        }

    }
}
