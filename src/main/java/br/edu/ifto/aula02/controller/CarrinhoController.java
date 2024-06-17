package br.edu.ifto.aula02.controller;

import br.edu.ifto.aula02.model.dao.ProdutoRepository;
import br.edu.ifto.aula02.model.entity.ItemVenda;
import br.edu.ifto.aula02.model.entity.Produto;
import br.edu.ifto.aula02.model.entity.Venda;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Controller
@RequestMapping("carrinho")
public class CarrinhoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/add/{id}")
    public ModelAndView addProduto(@PathVariable("id") Long id, HttpSession session) {
        Produto produto = produtoRepository.produto(id);
        if (produto == null) {
            return new ModelAndView("redirect:/produto/list");
        }

        List<ItemVenda> carrinho = (List<ItemVenda>) session.getAttribute("carrinho");
        if (carrinho == null) {
            carrinho = new ArrayList<>();
        }

        boolean encontrado = false;
        for (ItemVenda item : carrinho) {
            if (item.getProduto().getId().equals(id)) {
                item.setQuantidade(item.getQuantidade() + 1);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            carrinho.add(new ItemVenda(1.0, produto, null));
        }

        session.setAttribute("carrinho", carrinho);
        return new ModelAndView("redirect:/produto/list");
    }

    @GetMapping
    public String viewCarrinho(ModelMap model, HttpSession session) {
        List<ItemVenda> carrinho = (List<ItemVenda>) session.getAttribute("carrinho");
        model.addAttribute("carrinho", carrinho);

        double total = 0.0;
        if (carrinho != null) {
            for (ItemVenda item : carrinho) {
                total += item.getQuantidade() * item.getProduto().getValor();
            }
        }
        model.addAttribute("total", total);

        return "/carrinho/view";
    }

    @GetMapping("/remove/{id}")
    public ModelAndView removeProduto(@PathVariable("id") Long id, HttpSession session) {
        List<ItemVenda> carrinho = (List<ItemVenda>) session.getAttribute("carrinho");
        if (carrinho != null) {
            carrinho.removeIf(item -> item.getProduto().getId().equals(id));
        }
        session.setAttribute("carrinho", carrinho);
        return new ModelAndView("redirect:/carrinho");
    }

    @PostMapping("/finalizar")
    public ModelAndView finalizarVenda(HttpSession session) {
        List<ItemVenda> carrinho = (List<ItemVenda>) session.getAttribute("carrinho");
        if (carrinho == null || carrinho.isEmpty()) {
            return new ModelAndView("redirect:/carrinho");
        }

        Venda venda = new Venda();
        venda.setItens(carrinho);
        for (ItemVenda item : carrinho) {
            item.setVenda(venda);
        }

        session.removeAttribute("carrinho");
        // Supondo que tenha um VendaRepository para salvar a venda
        // vendaRepository.save(venda);

        return new ModelAndView("redirect:/venda/list");
    }
}
