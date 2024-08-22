package br.edu.ifto.aula02.controller;

import br.edu.ifto.aula02.model.dao.PessoaRepository;
import br.edu.ifto.aula02.model.entity.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Transactional
@Scope("request")
@Controller
@RequestMapping("pessoa")
public class PessoaController {

    @Autowired
    PessoaRepository pessoaRepository;

    public PessoaController(){
        pessoaRepository = new PessoaRepository();
    }


    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        pessoaRepository.remove(id);
        return new ModelAndView("redirect:/pessoa/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("pessoa", pessoaRepository.pessoa(id));
        return new ModelAndView("/pessoa/form", model);
    }



    @GetMapping("/filtrarnome")
    public ModelAndView filtrarNome(String nome, Model model) {

        System.out.println("chegou aqui ");

        List<Pessoa> pessoas = pessoaRepository.findByName(nome);

        System.out.println("chegou aqui 3, tamanho lista: " + pessoas.size());

        model.addAttribute("pessoas", pessoas);
        return new ModelAndView("listfisica");
    }


}
