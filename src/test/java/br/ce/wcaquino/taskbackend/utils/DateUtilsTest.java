package br.ce.wcaquino.taskbackend.utils;

import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDate;

public class DateUtilsTest {

    @Test
    public void DeveRetornarTrueParaDatasFuturas() {
        //Mockando uma data futura para o teste
        LocalDate date = LocalDate.of(2030, 01, 01);
        //Realizando o Assert para validar o retorno do método
        Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));
    }
    @Test
    public void DeveRetornarFalseParaDatasFuturas() {
        //Mockando uma data passada para o teste
        LocalDate date = LocalDate.of(2010, 01, 01);
        //Realizando o Assert para validar o retorno do método
        Assert.assertFalse(DateUtils.isEqualOrFutureDate(date));
    }
    @Test
    public void DeveRetornarTrueParaDataAtual() {
        //Mockando uma data atual para o teste
        LocalDate date = LocalDate.now();
        //Realizando o Assert para validar o retorno do método
        Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));
    }
}
