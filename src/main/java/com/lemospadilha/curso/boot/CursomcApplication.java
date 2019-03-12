package com.lemospadilha.curso.boot;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lemospadilha.curso.boot.domain.Categoria;
import com.lemospadilha.curso.boot.domain.Cidade;
import com.lemospadilha.curso.boot.domain.Cliente;
import com.lemospadilha.curso.boot.domain.Endereco;
import com.lemospadilha.curso.boot.domain.Estado;
import com.lemospadilha.curso.boot.domain.ItemPedido;
import com.lemospadilha.curso.boot.domain.Pagamento;
import com.lemospadilha.curso.boot.domain.PagamentoComBoleto;
import com.lemospadilha.curso.boot.domain.PagamentoComCartao;
import com.lemospadilha.curso.boot.domain.Pedido;
import com.lemospadilha.curso.boot.domain.Produto;
import com.lemospadilha.curso.boot.domain.enums.EstadoPagamento;
import com.lemospadilha.curso.boot.domain.enums.TipoCliente;
import com.lemospadilha.curso.boot.repositories.CategoriaRepository;
import com.lemospadilha.curso.boot.repositories.CidadeRepository;
import com.lemospadilha.curso.boot.repositories.ClienteRepository;
import com.lemospadilha.curso.boot.repositories.EnderecoRepository;
import com.lemospadilha.curso.boot.repositories.EstadoRepository;
import com.lemospadilha.curso.boot.repositories.PagamentoRepository;
import com.lemospadilha.curso.boot.repositories.PedidoRepository;
import com.lemospadilha.curso.boot.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
	@Autowired 
	private CategoriaRepository categoryRepository;
	
	@Autowired 
	private ProdutoRepository produtoRepository;
	
	@Autowired 
	private CidadeRepository cidadeRepository;
	
	@Autowired 
	private ClienteRepository clienteRepository;
	
	@Autowired 
	private EstadoRepository estadoRepository;
	
	@Autowired 
	private EnderecoRepository enderecoRepository;
	
	@Autowired 
	PedidoRepository pedidoRepository;
	
	@Autowired 
	PagamentoRepository pagamentoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(1, "Informática");
		Categoria cat2 = new Categoria(2, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 500.00);
		Produto p3 = new Produto(null, "Mouse", 70.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade cid1 = new Cidade(null,"Uberlândia", est1);
		Cidade cid2 = new Cidade(null,"São Paulo", est2);
		Cidade cid3 = new Cidade(null,"Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2,cid3));
		
		categoryRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(cid1,cid2,cid3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("37363323", "95958789"));
		
		Endereco end1 = new Endereco(null, "Rua das Flores", "300", "Apto 345", "Fazenda Velha", "89359125", cli1, cid1);
		Endereco end2 = new Endereco(null, "Rua Alagoas", "52", "Apto 89", "Costeira", "8777125", cli1, cid1);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1,end2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/12/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, end2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 23:59"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		//ItemPedido ip1 = new ItemPedido(ped, produto, desconto, quantidade, preco)
	}

}

