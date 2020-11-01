package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validador {
	
	public boolean validarCPF(String cpf) {
		//retiro espaços antes e depois do CPF
		cpf = cpf.trim();
		
		//se o CPF tiver mais ou menos que 11 caracteres, é inválido!
		if(cpf.length() != 11) {
			return false;
		}
		
		//evito que o usuário digite um conjunto de números iguais
		if(	cpf.equals("00000000000") ||
			cpf.equals("11111111111") ||
			cpf.equals("22222222222") ||
			cpf.equals("33333333333") ||
			cpf.equals("44444444444") ||
			cpf.equals("55555555555") ||
			cpf.equals("66666666666") ||
			cpf.equals("77777777777") ||
			cpf.equals("88888888888") ||
			cpf.equals("99999999999")) {
			return false;
		}
		
		//criando as variáveis que precisarei nesse método
		char digito10, digito11;
		int somatoria, contador, resto, numero, peso;
		
		//calculando décimo dígito
		somatoria = 0;
		peso = 10;
		for(contador = 0; contador < 9; contador++) {
			numero = (int)(cpf.charAt(contador) - 48);
			somatoria += (numero * peso);
			peso--;
		}
		
		resto = 11 - (somatoria % 11);
		if(resto == 10 || resto == 11) {
			digito10 = '0';
		} else {
			digito10 = (char)(resto + 48);
		}
		
		//calculando décimo primeiro dígito
		somatoria = 0;
		peso = 11;
		for(contador = 0; contador < 10; contador++) {
			numero = (int)(cpf.charAt(contador) - 48);
			somatoria += (numero * peso);
			peso--;
		}
		
		resto = 11 - (somatoria % 11);
		if(resto == 10 || resto == 11) {
			digito11 = '0';
		} else {
			digito11 = (char)(resto + 48);
		}
		
		//definindo resultado de acordo com o cálculo dos dígitos
		if(digito10 == cpf.charAt(9) && digito11 == cpf.charAt(10)) {
			return true;
		}
		
		return false;
	}
	
	public boolean validarEmail(String email) {
        if (email != null && email.length() > 0) {
            String regex = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
	}
	
	public boolean validarData(String data) {
		data = data.trim();
		
		if(data.length() != 10) {
			return false;
		}
		
		String[] lista = new String[3];
		lista = data.split("/");
		
		int dia = Integer.parseInt(lista[0]);
		int mes = Integer.parseInt(lista[1]);
		int ano = Integer.parseInt(lista[2]);
		
		if(dia < 1 || dia > 31) {
			return false;
		}
		
		if(mes < 1 || mes > 12) {
			return false;
		}
		
		if(ano < 1500) {
			return false;
		}
		
		return true;
	}

}
