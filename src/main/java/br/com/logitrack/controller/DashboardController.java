package br.com.logitrack.controller;

import br.com.logitrack.service.impl.ViagemServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private ViagemServiceImpl viagemService;
    private Manu

    @GetMapping("/dashboard")
    public String carregarDashboard(Model model) {
        // 1. Quilometragem Total
        model.addAttribute("kmTotal", viagemService.obterKmTotal());

        // 2. Cronograma (Lista de DTOs)
        model.addAttribute("proximasManutencoes", manutencaoService.buscarCronograma());

        // 3. Ranking (Um único DTO ou lista se quiser o Top 5)
        model.addAttribute("liderKm", veiculoService.obterLiderUtilizacao());

        // 4. Financeiro
        model.addAttribute("financeiro", manutencaoService.obterProjecaoMes());

        return "dashboard/index";
    }
}
