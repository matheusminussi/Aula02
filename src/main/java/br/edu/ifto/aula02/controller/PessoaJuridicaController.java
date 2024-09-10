package br.edu.ifto.aula02.controller;

import br.edu.ifto.aula02.model.dao.PessoaJuridicaRepository;
import br.edu.ifto.aula02.model.dao.PessoaRepository;
import br.edu.ifto.aula02.model.dao.RoleRepository;
import br.edu.ifto.aula02.model.entity.PessoaFisica;
import br.edu.ifto.aula02.model.entity.PessoaJuridica;
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
@RequestMapping("pessoajuridica")
public class PessoaJuridicaController {

    @Autowired
    PessoaJuridicaRepository pessoaJuridicaRepository;

    @Autowired
    RoleRepository roleRepository;


    @GetMapping("/form")
    public ModelAndView form(PessoaJuridica pessoaJuridica, ModelMap model){
        model.addAttribute("pessoa", pessoaJuridica);
        return new ModelAndView("pessoa/form");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("pessoa")@Valid PessoaJuridica pessoaJuridica, BindingResult result){
        if(result.hasErrors())
            return form(pessoaJuridica,new ModelMap());

        Role r = roleRepository.role("ROLE_USER");
        pessoaJuridica.getUsuario().getRoles().add(r);

        pessoaJuridicaRepository.saveJuridica(pessoaJuridica);
        return new ModelAndView("redirect:/pessoajuridica/list");
    }


    @PostMapping("/update")
    public ModelAndView update(PessoaJuridica pj) {
        pessoaJuridicaRepository.updateJuridica(pj);
        return new ModelAndView("redirect:/pessoajuridica/list");
    }

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        List<PessoaJuridica> pessoas = pessoaJuridicaRepository.pessoasJuridica();

        System.out.println("antes de buscar pessoas e adicionar no model");
        model.addAttribute("pessoas", pessoas);
        System.out.println("depois de adicionar no model");
        return new ModelAndView("/pessoa/listJuridica");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        pessoaJuridicaRepository.remove(id);
        return new ModelAndView("redirect:/pessoajuridica/list");
    }
}
