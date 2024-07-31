package com.example.meta_calculadora.service;

import com.example.meta_calculadora.model.MetaResponse;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.YearMonth;

@Service
public class MetaService {

    public MetaResponse calcularMeta(
        double tpvNovo,
        int diasRealizados,
        double metaTpvNovo,
        double tpvLegado,
        double metaTpvLegado,
        double margemNovo,
        double metaMargemNovo,
        double margemLegado,
        double metaMargemLegado,
        double remuneracaoFixa,
        double remuneracaoVariavel
    ) {
        LocalDate today = LocalDate.now();
        int daysInMonth = YearMonth.of(today.getYear(), today.getMonth()).lengthOfMonth();

        double tpvPorDiaNovo = tpvNovo / diasRealizados;
        double tpvProjetadoNovo = tpvPorDiaNovo * daysInMonth;
        double resultadoMetaNovo = (tpvProjetadoNovo / metaTpvNovo) * 100;

        double tpvPorDiaLegado = tpvLegado / diasRealizados;
        double tpvProjetadoLegado = tpvPorDiaLegado * daysInMonth;
        double resultadoMetaLegado = (tpvProjetadoLegado / metaTpvLegado) * 100;

        double margemPorDiaNovo = margemNovo / diasRealizados;
        double margemProjetadaNovo = margemPorDiaNovo * daysInMonth;
        double resultadoMargemNovo = (margemProjetadaNovo / metaMargemNovo) * 100;

        double margemPorDiaLegado = margemLegado / diasRealizados;
        double margemProjetadaLegado = margemPorDiaLegado * daysInMonth;
        double resultadoMargemLegado = (margemProjetadaLegado / metaMargemLegado) * 100;

        // Limita os resultados a 150%
        resultadoMetaNovo = Math.min(resultadoMetaNovo, 150);
        resultadoMetaLegado = Math.min(resultadoMetaLegado, 150);
        resultadoMargemNovo = Math.min(resultadoMargemNovo, 150);
        resultadoMargemLegado = Math.min(resultadoMargemLegado, 150);

        // Valores para comissão (zerados se abaixo de 70%)
        double resultadoMetaNovoComissao = resultadoMetaNovo < 70 ? 0 : resultadoMetaNovo * 0.10;
        double resultadoMetaLegadoComissao = resultadoMetaLegado < 70 ? 0 : resultadoMetaLegado * 0.15;
        double resultadoMargemNovoComissao = resultadoMargemNovo < 70 ? 0 : resultadoMargemNovo * 0.375;
        double resultadoMargemLegadoComissao = resultadoMargemLegado < 70 ? 0 : resultadoMargemLegado * 0.375;

        double comissaoProjetada = resultadoMetaNovoComissao + resultadoMetaLegadoComissao +
                                    resultadoMargemNovoComissao + resultadoMargemLegadoComissao;

        // Limita a comissão projetada a no máximo 120%
        comissaoProjetada = Math.min(comissaoProjetada, 120);

        // Calculando a comissão final usando comissaoProjetada
        double baseSalarial = obterBaseSalarial(comissaoProjetada);
        double comissao = baseSalarial * remuneracaoVariavel / 100;

        // Calculando o salário total
        double salarioTotal = comissao + remuneracaoFixa;

        // Formata os resultados para a saída
        DecimalFormat df = new DecimalFormat("#.##");

        MetaResponse response = new MetaResponse();
        response.setResultadoMetaNovo(df.format(resultadoMetaNovo));
        response.setResultadoMetaLegado(df.format(resultadoMetaLegado));
        response.setResultadoMargemNovo(df.format(resultadoMargemNovo));
        response.setResultadoMargemLegado(df.format(resultadoMargemLegado));
        response.setResultadoMetaNovoComissao(df.format(resultadoMetaNovoComissao));
        response.setResultadoMetaLegadoComissao(df.format(resultadoMetaLegadoComissao));
        response.setResultadoMargemNovoComissao(df.format(resultadoMargemNovoComissao));
        response.setResultadoMargemLegadoComissao(df.format(resultadoMargemLegadoComissao));
        response.setComissaoProjetada(df.format(comissaoProjetada));
        response.setProjecaoMetaNovo(df.format(tpvProjetadoNovo));
        response.setProjecaoMetaLegado(df.format(tpvProjetadoLegado));
        response.setProjecaoMargemNovo(df.format(margemProjetadaNovo));
        response.setProjecaoMargemLegado(df.format(margemProjetadaLegado));
        response.setComissao(df.format(comissao));
        response.setSalarioTotal(df.format(salarioTotal)); // Adiciona o salário total na resposta

        // Configura os novos campos
        response.setMetaTpvNovo(df.format(metaTpvNovo));
        response.setMetaTpvLegado(df.format(metaTpvLegado));
        response.setMetaMargemNovo(df.format(metaMargemNovo));
        response.setMetaMargemLegado(df.format(metaMargemLegado));

        return response;
    }

    private double obterBaseSalarial(double percentual) {
        if (percentual < 69) return 0;
        if (percentual < 70) return 2;
        if (percentual < 71) return 4;
        if (percentual < 72) return 6;
        if (percentual < 73) return 8;
        if (percentual < 74) return 10;
        if (percentual < 75) return 12;
        if (percentual < 76) return 14;
        if (percentual < 77) return 17;
        if (percentual < 78) return 19;
        if (percentual < 79) return 22;
        if (percentual < 80) return 24;
        if (percentual < 81) return 27;
        if (percentual < 82) return 30;
        if (percentual < 83) return 33;
        if (percentual < 84) return 36;
        if (percentual < 85) return 39;
        if (percentual < 86) return 43;
        if (percentual < 87) return 46;
        if (percentual < 88) return 50;
        if (percentual < 89) return 53;
        if (percentual < 90) return 57;
        if (percentual < 91) return 61;
        if (percentual < 92) return 65;
        if (percentual < 93) return 69;
        if (percentual < 94) return 73;
        if (percentual < 95) return 77;
        if (percentual < 96) return 81;
        if (percentual < 97) return 86;
        if (percentual < 98) return 90;
        if (percentual < 99) return 95;
        if (percentual < 100) return 100;
        if (percentual < 101) return 100;
        if (percentual < 102) return 101;
        if (percentual < 103) return 101;
        if (percentual < 104) return 102;
        if (percentual < 105) return 102;
        if (percentual < 106) return 104;
        if (percentual < 107) return 106;
        if (percentual < 108) return 108;
        if (percentual < 109) return 110;
        if (percentual < 110) return 112;
        if (percentual < 111) return 116;
        if (percentual < 112) return 119;
        if (percentual < 113) return 123;
        if (percentual < 114) return 126;
        if (percentual < 115) return 130;
        if (percentual < 116) return 134;
        if (percentual < 117) return 139;
        if (percentual < 118) return 143;
        if (percentual < 119) return 148;
        if (percentual < 120) return 152;
        if (percentual < 121) return 152;
        return 0;
    }
}
