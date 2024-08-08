package br.edu.ifto.aula02.controller;

import br.edu.ifto.aula02.model.dao.PessoaRepository;
import br.edu.ifto.aula02.model.entity.PessoaFisica;
import br.edu.ifto.aula02.model.entity.PessoaJuridica;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Transactional
@Controller
@RequestMapping("pessoajuridica")
public class PessoaJuridicaController {

    @Autowired
    PessoaRepository repository;

    @GetMapping("/form")
    public ModelAndView form(PessoaJuridica pessoaJuridica, ModelMap model){
        model.addAttribute("pessoa", pessoaJuridica);
        return new ModelAndView("pessoa/form");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("pessoa") PessoaJuridica pessoaJuridica){
        repository.saveJuridica(pessoaJuridica);

        return new ModelAndView("redirect:/pessoa/list");
    }


    @PostMapping("/update")
    public ModelAndView update(PessoaJuridica pj) {

        repository.updateJuridica(pj);
        return new ModelAndView("redirect:/pessoa/list");
    }
}
