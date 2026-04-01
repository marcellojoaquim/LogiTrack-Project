package br.com.logitrack.service;

import br.com.logitrack.model.dto.ViagemDTO;

public interface IViagemService {

    ViagemDTO salvar(ViagemDTO viagemDTO);

    ViagemDTO findByIdVeiculo(Integer idVeiculo);

}
