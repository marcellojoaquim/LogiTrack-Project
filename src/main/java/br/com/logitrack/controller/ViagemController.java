package br.com.logitrack.controller;

import br.com.logitrack.model.dto.ViagemDTO;
import br.com.logitrack.service.IVeiculoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/viagens")
public class ViagemController {

    IVeiculoService veiculoService;

    public ViagemController(IVeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("Viagem", new ViagemDTO());
        model.addAttribute("veiculos", veiculoService.listarVeiculosParaSelect());
        return "viagens/formulario";
    }
}
