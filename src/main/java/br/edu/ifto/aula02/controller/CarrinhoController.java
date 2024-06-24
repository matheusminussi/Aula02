package br.edu.ifto.aula02.controller;

import br.edu.ifto.aula02.model.dao.ProdutoRepository;
import br.edu.ifto.aula02.model.dao.VendaRepository;
import br.edu.ifto.aula02.model.entity.*;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Controller
@RequestMapping("carrinho")
public class CarrinhoController {

    @Autowired
    private ProdutoRepository produtoRepository;
    private VendaRepository vendaRepository;

    private List<ItemVenda> itemVendas = new ArrayList<ItemVenda>();

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("produtos", produtoRepository.produtos());
        model.addAttribute("venda", new Venda());
        return new ModelAndView("/compra/list");
    }

    @GetMapping("/adicionarCarrinho/{id}")
    public ModelAndView adicionarCarrinho(@PathVariable Long id) {
        System.out.println("ID PRODUTO: " + id); // Aqui chega
        Produto produto = produtoRepository.produto(id);//acho o produto pelo id
        Venda v = new Venda();
        v.setId(41L);
        v.setData(LocalDateTime.now());

        Pessoafisica pessoafisicaTeste = new Pessoafisica();
        pessoafisicaTeste.setId(41L);
        pessoafisicaTeste.setNome("Pessoa teste");

        v.setPessoa(pessoafisicaTeste);

        ItemVenda item = new ItemVenda();
        item.setProduto(produto);
        item.setQuantidade(1.0);
        item.setVenda(v);
        item.setId(41L);

        itemVendas.add(item);

        ModelAndView mv = new ModelAndView();

        ModelMap mp = new ModelMap();
        mp.addAttribute("carrinho",itemVendas);
        return new ModelAndView("/compra/view", mp);
    }
}
