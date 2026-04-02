package br.com.logitrack.controller;

import br.com.logitrack.service.IManutencaoService;
import br.com.logitrack.service.impl.ManutencaoServiceImpl;
import br.com.logitrack.service.impl.ViagemServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final ViagemServiceImpl viagemService;
    private final IManutencaoService manutencaoService;

    public DashboardController(ViagemServiceImpl viagemService, IManutencaoService manutencaoService) {
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
            model.addAttribute("financeiro", Optional.ofNullable(manutencaoService.projecaoFinanceiraMesAtual())
                    .orElse(BigDecimal.ZERO));
            model.addAttribute("volumePesados", viagemService.volumePorCategoria("PESADOS"));
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Erro ao carregar métricas: " + e.getMessage());
        }

        return "dashboard/index";
    }
}
