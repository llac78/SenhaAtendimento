package com.llac.senha.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llac.senha.model.SenhaGerada;
import com.llac.senha.repository.SenhaGeradaRepository;

@Service
@Transactional
public class SenhaGeradaServiceImpl implements SenhaGeradaService {

	@Autowired
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
