package mn.edu.num.icd10_service;

import static org.mockito.Mockito.when;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import mn.edu.num.icd10_service.controller.ICD10Controller;
import mn.edu.num.icd10_service.domain.Chapter;
import mn.edu.num.icd10_service.exception.ICD10NotFoundException;
import mn.edu.num.icd10_service.service.ICD10Service;

@WebMvcTest(ICD10Controller.class)
class ICD10ControllerTest {

    @Autowired
    private MockMvcTester mvc;

    @MockitoBean
    private ICD10Service service;

    @Test
    void getAllCodes_shouldReturn200AndList() {
        Chapter chapter = new Chapter("I", "Халдварт өвчин", "A00-B99", List.of());
        when(service.getAllCodes()).thenReturn(List.of(chapter));

        mvc.get().uri("/api/icd10")
                .assertThat().hasStatusOk()
                .bodyJson().extractingPath("$[0].chapter").isEqualTo("I");
    }

    @Test
    void getByCode_shouldReturn200AndChapter_whenFound() {
        Chapter chapter = new Chapter("II", "Хавдар", "C00-D48", List.of());
        when(service.findByCode("II")).thenReturn(chapter);

        mvc.get().uri("/api/icd10/{code}", "II")
                .assertThat().hasStatusOk()
                .bodyJson().extractingPath("$.name").isEqualTo("Хавдар");
    }

    @Test
    void getByCode_shouldReturn404_whenNotFound() {
        when(service.findByCode("ZZZ")).thenThrow(new ICD10NotFoundException("ZZZ"));

        mvc.get().uri("/api/icd10/{code}", "ZZZ")
                .assertThat().hasStatus4xxClientError()
                .bodyJson().extractingPath("$.status").isEqualTo(404);
    }
}