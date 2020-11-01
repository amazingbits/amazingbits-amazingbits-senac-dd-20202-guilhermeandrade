package utils;

import java.text.Normalizer;
import java.text.ParseException;
import java.util.regex.Pattern;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

public class Formatador {
	
	public void mascara(String msk, JFormattedTextField txt) {
		try {
			MaskFormatter mask = new MaskFormatter(msk);
			mask.install(txt);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Erro ao formatar campo", "Erro de Formatação", JOptionPane.ERROR);
		}
	}
	
	public String formatarDataBRLParaSQL(String data) {
		String[] lista = new String[3];
		lista = data.split("/");
		return lista[2] + "-" + lista[1] + "-" + lista[0];
	}
	
	public String removerAcentos(String valor) {
		valor = valor.trim();
		String nfdNormalizedString = Normalizer.normalize(valor, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
	}
	
	public int retornarIndice(String opcao) {
		opcao = opcao.trim();
		String[] lista = new String[2];
		lista = opcao.split("-");
		int indice = Integer.parseInt(lista[0].trim());
		return indice;
	}
	
	public String retornarValor(String opcao) {
		opcao = opcao.trim();
		String[] lista = new String[2];
		lista = opcao.split("-");
		String valor = lista[1].trim();
		return valor;
	}

}
