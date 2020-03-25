package com.lemospadilha.curso.boot.services;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lemospadilha.curso.boot.domain.ItemPedido;
import com.lemospadilha.curso.boot.domain.PagamentoComBoleto;
import com.lemospadilha.curso.boot.domain.Pedido;
import com.lemospadilha.curso.boot.domain.enums.EstadoPagamento;
import com.lemospadilha.curso.boot.repositories.ItemPedidoRepository;
import com.lemospadilha.curso.boot.repositories.PagamentoRepository;
import com.lemospadilha.curso.boot.repositories.PedidoRepository;
import com.lemospadilha.curso.boot.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	BoletoService boletoService;

	public Pedido findById(Integer id) {
		Optional<Pedido> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	@Transactional
	public Pedido insert(@Valid Pedido pedido) {
		pedido.setId(null);
		pedido.setInstante(new Date());
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		if (pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
			boletoService.preencheDataDeValidade(pagto, pedido.getInstante());
		}
		
		repo.save(pedido);
		pagamentoRepository.save(pedido.getPagamento());
		for(ItemPedido ip : pedido.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.findById(ip.getProduto().getId()).getPreco());
			ip.setPedido(pedido);
		}
		itemPedidoRepository.saveAll(pedido.getItens());
		return pedido;
	}

}
