package br.com.logitrack.controller;

import br.com.logitrack.service.impl.ManutencaoServiceImpl;
import br.com.logitrack.service.impl.ViagemServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

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
        try {

            var km = viagemService.kmTotal();
            model.addAttribute("kmTotal", km != null ? km : BigDecimal.ZERO);

            model.addAttribute("proximasManutencoes", manutencaoService.buscaProxManutencoes());

            model.addAttribute("liderKm", manutencaoService.findVeiculoMaxKm());

            model.addAttribute("financeiro", manutencaoService.projecaoFinanceiraMesAtual());

            model.addAttribute("volumePesados", viagemService.volumePorCategoria("PESADOS"));

        } catch (Exception e) {
            model.addAttribute("error", "Erro ao carregar métricas: " + e.getMessage());
        }

        return "dashboard/index";
    }
}
