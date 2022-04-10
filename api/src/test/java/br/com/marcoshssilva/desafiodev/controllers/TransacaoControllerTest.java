package br.com.marcoshssilva.desafiodev.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@WebMvcTest(TransacaoController.class)
public class TransacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TransacaoController transacaoController;

    MockMultipartFile fileValid = new MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE, "3201903010000014200096206760174753****3153153453JOÃO MACEDO   BAR DO JOÃO       ".getBytes());
    MockMultipartFile file422 = new MockMultipartFile("file", "error.pdf", MediaType.APPLICATION_PDF_VALUE, "Hello, World!".getBytes());

    @Test
    public void shouldReturnAllTransacoesAndStatus200() throws Exception {
        this.mockMvc
                .perform(
                        get("/transacoes"))
                .andExpect(
                        MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void shouldReturnAllLojasAndStatus200() throws Exception {
        this.mockMvc
                .perform(
                        get("/transacoes/lojas"))
                .andExpect(
                        MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void shouldExecutePerfectlyAndReturnStatusOk() throws Exception {
        Mockito.when(this.transacaoController.uploadTransacoesByFile(file422))
                .thenCallRealMethod();

        this.mockMvc
                .perform(
                        multipart("/transacoes/upload").file(fileValid))
                .andExpect(
                        MockMvcResultMatchers.status().is2xxSuccessful()
                );
    }

    @Test
    public void shouldNotAcceptFileAndReturnValidationErrorMessage() throws Exception {
        Mockito.when(this.transacaoController.uploadTransacoesByFile(file422))
                .thenCallRealMethod();

        this.mockMvc
                .perform(
                        multipart("/transacoes/upload").file(file422))
                .andExpect(
                        MockMvcResultMatchers.status().isUnprocessableEntity()
                );
    }

}
