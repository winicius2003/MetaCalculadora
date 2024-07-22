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
        double metaMargemLegado
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

        // Valores para comissão (zerados se abaixo de 70%)
        double resultadoMetaNovoComissao = resultadoMetaNovo < 70 ? 0 : resultadoMetaNovo * 0.10;
        double resultadoMetaLegadoComissao = resultadoMetaLegado < 70 ? 0 : resultadoMetaLegado * 0.15;
        double resultadoMargemNovoComissao = resultadoMargemNovo < 70 ? 0 : resultadoMargemNovo * 0.375;
        double resultadoMargemLegadoComissao = resultadoMargemLegado < 70 ? 0 : resultadoMargemLegado * 0.375;

        double comissaoProjetada = resultadoMetaNovoComissao + resultadoMetaLegadoComissao +
                                    resultadoMargemNovoComissao + resultadoMargemLegadoComissao;

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

        return response;
    }
}
