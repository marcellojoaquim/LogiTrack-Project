package br.com.logitrack.controller;

import br.com.logitrack.service.impl.ManutencaoServiceImpl;
import br.com.logitrack.service.impl.ViagemServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final ViagemServiceImpl viagemService;
    private final ManutencaoServiceImpl manutencaoService;

    public DashboardController(ViagemServiceImpl viagemService, ManutencaoServiceImpl manutencaoService) {
        this.viagemService = viagemService;
        this.manutencaoService = manutencaoService;
    }

    @GetMapping
    public String carregarDashboard(Model model) {

        model.addAttribute("kmTotal", viagemService.kmTotal());

        model.addAttribute("proximasManutencoes", manutencaoService.buscaProxManutencoes());

        model.addAttribute("liderKm", manutencaoService.findVeiculoMaxKm());

        model.addAttribute("financeiro", manutencaoService.projecaoFinanceiraMesAtual());

        return "dashboard/index";
    }
}
