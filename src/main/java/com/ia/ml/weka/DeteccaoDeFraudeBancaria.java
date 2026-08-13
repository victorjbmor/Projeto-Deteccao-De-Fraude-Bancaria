package com.ia.ml.weka;

// =======================
// ETAPA 1: IMPORTACAO DE BIBLIOTECAS
// =======================

import weka.classifiers.Classifier; //Interface que define metodos obrigatorios para algoritmos de classificacao
import weka.classifiers.trees.J48;
import weka.classifiers.trees.j48.*; // Algorito de decisao que aprende a responder

import weka.core.Attribute; // Representa uma coluna dos dados (ex: "valor", "origem")
import weka.core.Instance; // Representa uma linha de dados (como uma linha do Excel)
import weka.core.DenseInstance; // Representa uma linha completa de dados (com valores reais)
import weka.core.Instances; // O conjunto completo de dados (como uma planilha)

// Ferramentas de log usadas para ocultar avisos do Weka (nao afetam o funcionamento do codigo)
import java.util.logging.Level; // Controla o nivel de importancia dos avisos exibidos
import java.util.logging.Logger; // Usado para configurar o sistema de logs do Java

import java.util.ArrayList;


public class DeteccaoDeFraudeBancaria {
	
	private Classifier classificador; // Modelo de classificacao utilizado
	private Instances dadosTreinamento; // Conjunto de dados usados para treinar o modelo
	
	// Atributos do conjunto de dados
	private Attribute atributoValor;
	private Attribute atributoOrigem;
	private Attribute atributoFraude;
	
	// =================
	// ETAPA 2: DEFINICAO DOS ATRIBUTOS (colunas da "planilha")
	
	public void definirAtributos() {
		atributoValor = new Attribute("valor"); // valor da transacao
		
		ArrayList<String> valoresOrigem = new ArrayList<>();
		valoresOrigem.add("internacional");
		valoresOrigem.add("nacional");
		atributoOrigem = new Attribute("origem", valoresOrigem); // Origem da transacao
		
		ArrayList<String> valoresFraude = new ArrayList<>();
		valoresFraude.add("nao");
		valoresFraude.add("sim");
		atributoFraude = new Attribute("fraude", valoresFraude); // rotulo: se é fraude ou nao
		
		ArrayList<Attribute> atributos = new ArrayList<>();
		atributos.add(atributoValor);
		atributos.add(atributoOrigem);
		atributos.add(atributoFraude);
		
		// ========================
		// ETAPA 3: CRIACAO DO DATASET (estrutura base da planilha)
		// ========================
		
		// Cria o dataset chamado "transacoes" com os atributos definidos. Comeca vazio (0 linhas)
		dadosTreinamento = new Instances("transacoes", atributos, 0); 
		
		// Define o ultimo atributo ("fraude") como classe alvo para a previsao
		dadosTreinamento.setClassIndex(dadosTreinamento.numAttributes() - 1);
		
	}
	
	// Metodo auxiliar para criar e adicionar uma nova transacao ao dataset de treino
	private void adicionarTransacao(double valor, String origem,String fraude) {
		Instance instancia = new DenseInstance(dadosTreinamento.numAttributes());
		// setDataSet(...) configuracao obrigatorio que diz a instancia:
		// "Voce vai seguir a mesma estrutura do dataset - os mesmos atributos,
		// na mesma ordem e com os mesmos tipos de dados." 
		instancia.setDataset(dadosTreinamento);
		
		instancia.setValue(atributoValor, valor);
		instancia.setValue(atributoOrigem, origem);
		instancia.setValue(atributoFraude, fraude);
		dadosTreinamento.add(instancia);
	}
	
	// =======================
	// ETAPA 4: ADICAO DE EXEMPLOS (dados de treino para o modelo aprender)
	// =======================
	
	public void adicionarExemplos() {
		// Exemplos de transacoes FRAUDULENTAS (Valores altos + origem internacional)
		adicionarTransacao(5000,"internacional", "sim");
		adicionarTransacao(10000,"internacional", "sim");
		adicionarTransacao(7500,"internacional", "sim");
		adicionarTransacao(8000,"internacional", "sim");
		
		
		// Exemplos de transacoes NORMAIS (valores baixos + origem nacional)
		adicionarTransacao(200,"nacional", "nao");
		adicionarTransacao(300,"nacional", "nao");
		adicionarTransacao(400,"nacional", "nao");
		adicionarTransacao(150,"nacional", "nao");
		
		// Exemplos adicionais:
		// Transacoes com valores medios/altos em territorio nacional
		// Pode ser usado para demonstrar variacao ou desafiar o modelo
		
		//adicionarTransacao(1000, "nacional", "sim");
		//adicionarTransacao(1500, "nacional", "sim");
		//adicionarTransacao(20000, "nacional", "sim");
	}
	
	public void treinarModelo() throws Exception {
		classificador = new J48(); // Cria modelo decisao chamado J48 (metodo matematico)
		classificador.buildClassifier(dadosTreinamento); // Treina o modelo com os dados fornecidos
	}
	
	
	
	
}
