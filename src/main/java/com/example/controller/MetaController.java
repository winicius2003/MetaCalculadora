package com.example.meta_calculadora.controller;

import com.example.meta_calculadora.model.MetaResponse;
import com.example.meta_calculadora.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MetaController {

    @Autowired
    private MetaService metaService;

    @GetMapping("/calcular-meta")
    public String calcularMeta(
        @RequestParam double tpvNovo,
        @RequestParam int diasRealizados,
        @RequestParam double metaTpvNovo,
        @RequestParam double tpvLegado,
        @RequestParam double metaTpvLegado,
        @RequestParam double margemNovo,
        @RequestParam double metaMargemNovo,
        @RequestParam double margemLegado,
        @RequestParam double metaMargemLegado,
        Model model
    ) {
        MetaResponse metaResponse = metaService.calcularMeta(
            tpvNovo,
            diasRealizados,
            metaTpvNovo,
            tpvLegado,
            metaTpvLegado,
            margemNovo,
            metaMargemNovo,
            margemLegado,
            metaMargemLegado
        );

        model.addAttribute("metaResponse", metaResponse);
        return "resultado";
    }
}
