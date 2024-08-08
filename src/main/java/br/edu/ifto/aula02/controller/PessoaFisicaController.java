package br.edu.ifto.aula02.controller;


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

@Transactional
@Controller
@RequestMapping("pessoafisica")
public class PessoaFisicaController {

    @Autowired
    PessoaRepository repository;

    @GetMapping("/form")
    public ModelAndView form(PessoaFisica pessoaFisica, ModelMap model){
        model.addAttribute("pessoa", pessoaFisica);
        return new ModelAndView("pessoa/form");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("pessoa") PessoaFisica pessoaFisica){
        repository.saveFisica(pessoaFisica);

        return new ModelAndView("redirect:/pessoa/list");
    }

    @PostMapping("/update")
    public ModelAndView update(PessoaFisica pf) {

        repository.updateFisica(pf);
        return new ModelAndView("redirect:/pessoa/list");
    }
}
