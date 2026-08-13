package com.ia.ml.weka;

// =======================
// ETAPA 1: IMPORTACAO DE BIBLIOTECAS
// =======================

import weka.classifiers.Classifier; //Interface que define metodos obrigatorios para algoritmos de classificacao
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
	
	
}
