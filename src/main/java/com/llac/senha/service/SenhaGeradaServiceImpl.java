package com.llac.senha.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.llac.senha.model.SenhaGerada;
import com.llac.senha.repository.SenhaGeradaRepository;

@Service
@Transactional
public class SenhaGeradaServiceImpl implements SenhaGeradaService {

	private SenhaGeradaRepository senhaGeradaRepository;

	@Override
	public SenhaGerada salvar(SenhaGerada senha) {
		return senhaGeradaRepository.save(senha);
	}

	@Override
	public Long pesquisarMaximoId() {
		return senhaGeradaRepository.pesquisarMaximoId();
	}

}
