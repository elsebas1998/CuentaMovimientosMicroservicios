package com.cuenta.moviemiento.microservicio;

import com.cuenta.moviemiento.microservicio.Dto.CuentaRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class CuentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCrearCuenta() throws Exception {
        CuentaRequestDto cuentaRequestDto = new CuentaRequestDto();
        cuentaRequestDto.setNumeroCuenta("100000");
        cuentaRequestDto.setIdentificacionCliente("2060708091");
        cuentaRequestDto.setEstado(true);
        cuentaRequestDto.setTipoCuenta("ahorro");
        String saldoInicial = "500.000";
        BigDecimal num = new BigDecimal(saldoInicial);
        cuentaRequestDto.setSaldoInicial(num);

        mockMvc.perform(post("/cuenta/crear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cuentaRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").value("Se creo la cuenta de forma exitosa"));
    }
}
