package com.llac.senha.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.llac.senha.model.SenhaGerada;
import com.llac.senha.service.SenhaGeradaService;

@Controller
public class SenhaGeradaController {

	private SenhaGeradaService senhaGeradaService;

	@Autowired
	public SenhaGeradaController(SenhaGeradaService senhaService) {
		this.senhaGeradaService = senhaService;
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@GetMapping("/index")
	public ModelAndView index() {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");

		SenhaGerada senha = new SenhaGerada();
		mv.addObject("form_senha", senha);

		return mv;
	}
	
	@GetMapping("/painel_atendimento")
	public ModelAndView painel_atendimento() {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("painel_atendimento");
		
		Long ultimaSenha = this.senhaGeradaService.pesquisarMaximoId();
		System.out.println(" ---------- " + ultimaSenha);
		mv.addObject("ultimaSenha", ultimaSenha);

		return mv;
	}

	@PostMapping()
	public ModelAndView salvar(@Valid @ModelAttribute("form_senha") SenhaGerada senha, BindingResult result) throws IOException, DocumentException {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");

		if (result.hasErrors()) {

			return mv;

		} else {

			Date dataGeracao = new Date();
			senha.setDataGeracao(dataGeracao);

			senhaGeradaService.salvar(senha);

			Long ultimaSenha = senha.getId();
			mv.addObject("ultimaSenha", ultimaSenha);
			
			this.criarDocumento(senha);

			return mv;
		}
	}
	
	public void criarDocumento(SenhaGerada senha) throws FileNotFoundException, DocumentException {
		
        Document documento = new Document();

        try {

            PdfWriter.getInstance(documento, new FileOutputStream("D:\\Senha_" + senha.getId().toString() +".pdf"));
            documento.open();

            documento.add(new Paragraph("Senha: " + senha.getId().toString()));
            
            documento.add(new Paragraph(this.getDataGeracaoFormatada(senha.getDataGeracao()), FontFactory.getFont(FontFactory.COURIER, 12)));

        } catch(DocumentException de) {
            System.err.println(de.getMessage());
        } catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        } finally {
        	documento.close();
        }
	}
	
	public String getDataGeracaoFormatada(Date data) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String dataFormatada = sdf.format(data);
		
		return dataFormatada;
	}

}
