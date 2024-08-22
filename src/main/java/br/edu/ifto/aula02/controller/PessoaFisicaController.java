package br.edu.ifto.aula02.controller;


import br.edu.ifto.aula02.model.dao.PessoaFisicaRepository;
import br.edu.ifto.aula02.model.dao.RoleRepository;
import br.edu.ifto.aula02.model.entity.PessoaFisica;
import br.edu.ifto.aula02.model.entity.Role;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Transactional
@Controller
@RequestMapping("pessoafisica")
public class PessoaFisicaController {

    @Autowired
    PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/form")
    public ModelAndView form(PessoaFisica pessoaFisica, ModelMap model){
        model.addAttribute("pessoa", pessoaFisica);
        return new ModelAndView("pessoa/form");
    }

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        List<PessoaFisica> pessoas = pessoaFisicaRepository.pessoasFisica();

        System.out.println("antes de buscar pessoas e adicionar no model");
        model.addAttribute("pessoas", pessoas);
        System.out.println("depois de adicionar no model");
        return new ModelAndView("pessoa/listfisica");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("pessoa")@Valid PessoaFisica pessoaFisica,BindingResult result){
        if(result.hasErrors())
            return form(pessoaFisica,new ModelMap());

        Role r = roleRepository.role("ROLE_USER");
        pessoaFisica.getUsuario().getRoles().add(r);

        System.out.println("Antes do saveFisica");
        pessoaFisicaRepository.saveFisica(pessoaFisica);
        System.out.println("depois do savefisica");
        return new ModelAndView("redirect:/pessoafisica/list");
    }

    @PostMapping("/update")
    public ModelAndView update(PessoaFisica pf) {
        pessoaFisicaRepository.updateFisica(pf);
        return new ModelAndView("redirect:/pessoafisica/list");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        pessoaFisicaRepository.remove(id);
        return new ModelAndView("redirect:/pessoafisica/list");
    }

}
