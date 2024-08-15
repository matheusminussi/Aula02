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

import java.util.ArrayList;
import java.util.List;

@Transactional
@Scope("request")
@Controller
@RequestMapping("pessoa")
public class PessoaController {

    @Autowired
    PessoaRepository repository;

    public PessoaController(){
        repository = new PessoaRepository();
    }

    @GetMapping("/form")
    public ModelAndView form(PessoaJuridica pessoajuridica, PessoaFisica pessoafisica) {
        return new ModelAndView("/pessoa/form");
    }

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        List<Pessoa> pessoas = repository.pessoas();

        System.out.println("antes de buscar pessoas e adicionar no model");
        model.addAttribute("pessoas", pessoas);
        System.out.println("depois de adicionar no model");
        return new ModelAndView("/pessoa/list");
    }


    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        repository.remove(id);
        return new ModelAndView("redirect:/pessoa/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("pessoa", repository.pessoa(id));
        return new ModelAndView("/pessoa/form", model);
    }



    @GetMapping("/filtrarnome")
    public ModelAndView filtrarNome(String nome, Model model) {

        System.out.println("chegou aqui ");

        List<Pessoa> pessoas = repository.findByName(nome);

        System.out.println("chegou aqui 3, tamanho lista: " + pessoas.size());

        model.addAttribute("pessoas", pessoas);
        return new ModelAndView("/pessoa/list");
    }


}
