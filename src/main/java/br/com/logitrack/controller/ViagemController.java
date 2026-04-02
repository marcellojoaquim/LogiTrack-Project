package br.com.logitrack.controller;

import br.com.logitrack.exception.BusinessException;
import br.com.logitrack.model.Veiculo;
import br.com.logitrack.model.dto.ViagemDTO;
import br.com.logitrack.service.IVeiculoService;
import br.com.logitrack.service.IViagemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.Instant;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/viagens")
public class ViagemController {

    private final IVeiculoService veiculoService;
    private final IViagemService viagemService;

    public ViagemController(IVeiculoService veiculoService, IViagemService viagemService) {
        this.veiculoService = veiculoService;
        this.viagemService = viagemService;
    }

    @GetMapping()
    public String novo(Model model) {
        model.addAttribute("viagem", new ViagemDTO());
        model.addAttribute("veiculos", veiculoService.listarVeiculosParaSelect());
        return "viagens/formulario";
    }

    @GetMapping("/novo")
    public String form(Model model) {
        model.addAttribute("viagem", new ViagemDTO());
        model.addAttribute("veiculos", veiculoService.listarVeiculosParaSelect());
        model.addAttribute("viagens", viagemService.listarTodas());
        return "viagens/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        try {
            ViagemDTO viagem = viagemService.findById(id)
                    .orElseThrow(() -> new BusinessException("Viagem não encontrada."));
            model.addAttribute("viagem", viagem);
            return "viagens/form-viagem";
        } catch (BusinessException e) {
            return "redirect:/viagens?error=" + e.getMessage();
        }
    }

    @PostMapping("/atualizar/{id}")
    public String atualizar(@PathVariable Long id,
                            @ModelAttribute("viagem") ViagemDTO viagemDTO,
                            RedirectAttributes attributes) {
        try {
            viagemService.atualizar(id, viagemDTO);
            attributes.addFlashAttribute("mensagem", "Viagem atualizada com sucesso!");
            return "redirect:/viagens";
        } catch (BusinessException e) {
            attributes.addFlashAttribute("erro", e.getMessage());
            return "redirect:/viagens/editar/" + id;
        }
    }

    @PostMapping("/encerrar/{id}")
    public String encerrar(@PathVariable Long id, RedirectAttributes attributes) {
        try {
            viagemService.encerrar(id, LocalDateTime.now());
            attributes.addFlashAttribute("mensagem", "Viagem encerrada com sucesso!");
        } catch (BusinessException e) {
            attributes.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/viagens";
    }

    @PostMapping("/novo")
    public String novo(@ModelAttribute ViagemDTO dto) {
        viagemService.salvar(dto);
        return "redirect:/viagens/novo";
    }
}
