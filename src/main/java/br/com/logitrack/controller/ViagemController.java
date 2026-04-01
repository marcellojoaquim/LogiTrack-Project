package br.com.logitrack.controller;

import br.com.logitrack.model.Veiculo;
import br.com.logitrack.model.dto.ViagemDTO;
import br.com.logitrack.service.IVeiculoService;
import br.com.logitrack.service.IViagemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/viagens")
public class ViagemController {

    private final IVeiculoService veiculoService;
    private final IViagemService viagemService;

    public ViagemController(IVeiculoService veiculoService, IViagemService viagemService) {
        this.veiculoService = veiculoService;
        this.viagemService = viagemService;
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("Viagem", new ViagemDTO());
        model.addAttribute("veiculos", veiculoService.listarVeiculosParaSelect());
        return "viagens/formulario";
    }

    @PostMapping("/novo")
    public String novo(ViagemDTO dto) {
        viagemService.salvar(dto);
        return "redirect:/viagens";
    }


}
