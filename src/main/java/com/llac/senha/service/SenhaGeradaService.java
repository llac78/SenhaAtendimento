package com.llac.senha.service;

import com.llac.senha.model.SenhaGerada;

public interface SenhaGeradaService {

	public SenhaGerada salvar(SenhaGerada senha);
	
	public Long pesquisarMaximoId();
	
}
