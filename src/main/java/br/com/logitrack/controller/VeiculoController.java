package br.com.logitrack.controller;

import br.com.logitrack.model.dto.VeiculoDTO;
import br.com.logitrack.service.IVeiculoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/veiculos")
public class VeiculoController {

    private final IVeiculoService veiculoService;

    public VeiculoController(IVeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @GetMapping
    public String listVeiculos(Model model, @PageableDefault(size = 10) Pageable pageable) {
        Page<VeiculoDTO> listaVeiculos = veiculoService.listarVeiculos(pageable);

        model.addAttribute("veiculos", listaVeiculos.getContent());
        model.addAttribute("page", listaVeiculos);
        model.addAttribute("Veiculos", "Frota LogiTrack");

        return "veiculos/lista";
    }
}
