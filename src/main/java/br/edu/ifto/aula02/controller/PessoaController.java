package br.edu.ifto.aula02.controller;

import br.edu.ifto.aula02.model.dao.PessoaRepository;
import br.edu.ifto.aula02.model.dao.ProdutoRepository;
import br.edu.ifto.aula02.model.entity.Pessoa;
import br.edu.ifto.aula02.model.entity.Pessoafisica;
import br.edu.ifto.aula02.model.entity.Produto;
import br.edu.ifto.aula02.model.entity.Venda;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
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
    public ModelAndView form(Pessoafisica pessoa) {
        return new ModelAndView("/pessoa/form");
    }

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("pessoas", repository.pessoas());
        return new ModelAndView("/pessoa/list");
    }

   /* @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("pessoa")@Valid Pessoa pessoa, BindingResult result) {
        if(result.hasErrors())
            return form(pessoa);

        repository.save(pessoa);
        return new ModelAndView("redirect:/pessoa/list");
    }*/

    @PostMapping("/savepf")
    public ModelAndView savepf(@ModelAttribute("pessoafisica")@Valid Pessoafisica pf, BindingResult result) {
        if(result.hasErrors())
            return form(pf);

        repository.saveFisica(pf);
        return new ModelAndView("redirect:/pessoa/list");
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
