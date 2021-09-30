
package cl;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import cl.api.processrequests.ProcessRequestApplication;
import cl.api.processrequests.dto.EncuestaDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ProcessRequestApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProcessRequestApplicationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testInsertEncuestaOK() throws Exception {
        String urlController = "http://localhost:8171/api/process_requests/v1/save/";
        EncuestaDto obj = new EncuestaDto().setEmail("jj@gmail.com").setEstiloMusica("ROCK");

        assertNotNull(this.mvc.perform(post(urlController).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(obj)))
                .andReturn());
    }

    @Test
    public void testgetEncuestasOK() throws Exception {
        String urlController = "http://localhost:8171/api/process_requests/v1/list/";

        assert urlController == "http://localhost:8171/api/process_requests/v1/list/" : "El valor debe ser positivo";
        assertNotNull(this.mvc.perform(get(urlController).accept(MediaType.APPLICATION_JSON))
                .andReturn());
    }
}
