package mn.edu.num.icd10_service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mn.edu.num.icd10_service.domain.Chapter;
import mn.edu.num.icd10_service.exception.ICD10NotFoundException;
import mn.edu.num.icd10_service.repository.ICD10Repository;
import mn.edu.num.icd10_service.service.ICD10Service;

@ExtendWith(MockitoExtension.class)
class ICD10ServiceTest {

    @Mock
    private ICD10Repository repository;

    private ICD10Service service;
    private Chapter chapterOne;
    private Chapter chapterTwo;

    @BeforeEach
    void setUp() {
        service = new ICD10Service(repository);
        chapterOne = new Chapter("I", "Халдварт ба шимэгчит зарим өвчин", "A00-B99", List.of());
        chapterTwo = new Chapter("II", "Хавдар", "C00-D48", List.of());
    }

    @Test
    void getAllCodes_shouldReturnEverythingFromRepository() {
        when(repository.findAll()).thenReturn(List.of(chapterOne, chapterTwo));
        assertThat(service.getAllCodes()).hasSize(2).containsExactly(chapterOne, chapterTwo);
    }

    @Test
    void findByCode_shouldReturnMatchingChapter_whenCodeExists() {
        when(repository.findAll()).thenReturn(List.of(chapterOne, chapterTwo));
        assertThat(service.findByCode("II")).isEqualTo(chapterTwo);
    }

    @Test
    void findByCode_shouldThrowNotFoundException_whenCodeDoesNotExist() {
        when(repository.findAll()).thenReturn(List.of(chapterOne, chapterTwo));
        assertThatThrownBy(() -> service.findByCode("ZZZ"))
                .isInstanceOf(ICD10NotFoundException.class)
                .hasMessageContaining("ZZZ");
    }

    @Test
    void findByCode_shouldThrowNotFoundException_whenRepositoryIsEmpty() {
        when(repository.findAll()).thenReturn(List.of());
        assertThatThrownBy(() -> service.findByCode("I")).isInstanceOf(ICD10NotFoundException.class);
    }
}