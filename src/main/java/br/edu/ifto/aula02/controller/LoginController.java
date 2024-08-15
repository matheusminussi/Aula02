package br.edu.ifto.aula02.controller;


import br.edu.ifto.aula02.model.entity.PessoaFisica;
import br.edu.ifto.aula02.model.entity.PessoaJuridica;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Scope("request")
@Controller
@RequestMapping("login")
public class LoginController {

    @GetMapping()
    public ModelAndView form() {
        return new ModelAndView("/login/login");
    }


}
