package br.edu.ifto.aula02.controller;


import br.edu.ifto.aula02.model.dao.PessoaFisicaRepository;
import br.edu.ifto.aula02.model.dao.PessoaRepository;
import br.edu.ifto.aula02.model.entity.Pessoa;
import br.edu.ifto.aula02.model.entity.PessoaFisica;
import br.edu.ifto.aula02.model.entity.Produto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Transactional
@Controller
@RequestMapping("pessoafisica")
public class PessoaFisicaController {

    @Autowired
    PessoaRepository repository;

    @Autowired
    PessoaFisicaRepository PessoaFisicaRepository;

    @GetMapping("/form")
    public ModelAndView form(PessoaFisica pessoaFisica, ModelMap model){
        model.addAttribute("pessoa", pessoaFisica);
        return new ModelAndView("pessoa/form");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("pessoa")@Valid PessoaFisica pessoaFisica,BindingResult result){
        if(result.hasErrors())
            return form(pessoaFisica,new ModelMap());

        System.out.println("Antes do saveFisica");
        repository.saveFisica(pessoaFisica);
        System.out.println("depois do savefisica");
        return new ModelAndView("redirect:/pessoa/list");
    }

    @PostMapping("/update")
    public ModelAndView update(PessoaFisica pf) {

        repository.updateFisica(pf);
        return new ModelAndView("redirect:/pessoa/list");
    }


    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        List<PessoaFisica> pessoas = PessoaFisicaRepository.pessoasFisica();

        System.out.println("antes de buscar pessoas e adicionar no model");
        model.addAttribute("pessoas", pessoas);
        System.out.println("depois de adicionar no model");
        return new ModelAndView("/pessoa/list");
    }
}
