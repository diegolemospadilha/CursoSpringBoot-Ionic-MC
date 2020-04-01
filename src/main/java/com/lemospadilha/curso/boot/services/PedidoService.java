package com.lemospadilha.curso.boot.services;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lemospadilha.curso.boot.domain.Cliente;
import com.lemospadilha.curso.boot.domain.ItemPedido;
import com.lemospadilha.curso.boot.domain.PagamentoComBoleto;
import com.lemospadilha.curso.boot.domain.Pedido;
import com.lemospadilha.curso.boot.domain.enums.EstadoPagamento;
import com.lemospadilha.curso.boot.repositories.ItemPedidoRepository;
import com.lemospadilha.curso.boot.repositories.PagamentoRepository;
import com.lemospadilha.curso.boot.repositories.PedidoRepository;
import com.lemospadilha.curso.boot.security.UserSS;
import com.lemospadilha.curso.boot.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private EmailService emailService;

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
		pedido.setCliente(clienteService.findById(pedido.getCliente().getId()));
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		if (pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
			boletoService.preencheDataDeValidade(pagto, pedido.getInstante());
		}

		repo.save(pedido);
		pagamentoRepository.save(pedido.getPagamento());
		for (ItemPedido ip : pedido.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.findById(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(pedido);
		}
		itemPedidoRepository.saveAll(pedido.getItens());
		emailService.sendOrderConfirmationHtmlEmail(pedido);
		return pedido;
	}

	public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

		UserSS user = UserService.authenticated();
		Cliente cliente = clienteService.findById(user.getId());
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findByCliente(cliente, pageRequest);
	}

}
