package com.lemospadilha.curso.boot;

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
import com.lemospadilha.curso.boot.domain.Produto;
import com.lemospadilha.curso.boot.domain.enums.TipoCliente;
import com.lemospadilha.curso.boot.repositories.CategoriaRepository;
import com.lemospadilha.curso.boot.repositories.CidadeRepository;
import com.lemospadilha.curso.boot.repositories.ClienteRepository;
import com.lemospadilha.curso.boot.repositories.EnderecoRepository;
import com.lemospadilha.curso.boot.repositories.EstadoRepository;
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
	}

}

