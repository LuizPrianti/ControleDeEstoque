package view;

import java.util.List;

import javax.swing.JOptionPane;

import controle.Estoque;
import modelo.Produto;

public class Venda {
	public Venda(Estoque estoque) {
		int codigo = 0;
		try {
			codigo = Integer.parseInt(JOptionPane.showInputDialog("C�digo do produto: "));
		} catch (Exception NumberFormatException) {
			System.out.println("Digite um valor v�lido!");
		}

		if (estoque.buscaProduto(codigo) == false) {
			JOptionPane.showMessageDialog(null, "Produto nao encontrado.");
		} else {
			int quantidadeDisponivel = estoque.quantidadeDisponivel(codigo);
			
			List<Produto> produtos = estoque.imprimirEstoque();
			
			
			
			for (Produto produto : produtos) {
				if (produto.getCodigo() == codigo) {
					JOptionPane.showMessageDialog(null, "Nome: " + produto.getNome() + "\nPre�o: " + produto.getPreco()
							+ "\nQunt. dispon�vel: " + produto.getQuantidade());
					int quantidadeVenda = Integer.parseInt(JOptionPane.showInputDialog("Quantidade de venda: "));
					
					if (quantidadeVenda > quantidadeDisponivel) {
						JOptionPane.showMessageDialog(null, "Quantidade nao dispon�vel.");
					} else {
						estoque.venda(codigo, quantidadeVenda);
						int resposta = JOptionPane.showConfirmDialog(null, "Deseja adicionar um coment�rio?", "Opini�o",
								JOptionPane.YES_NO_OPTION);
						if (resposta == JOptionPane.YES_OPTION) {
							String comentario = JOptionPane.showInputDialog("");
							if (produto.getCodigo() == codigo) {
								produto.setComentario(comentario);
							}
						}
						JOptionPane.showMessageDialog(null, "Produto vendido!.");
					}
				}
			}
		}
	}
}
