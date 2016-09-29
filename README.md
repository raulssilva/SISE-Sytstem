# SISE-Sytstem
O SISE (Sistema de Integração de Serviços para Estudantes) é um sistema que promove a integração de alguns serviços da UFRN para os alunos, baseando-se nas preferências de cada indivíduo.

## Autores ##
- [Felipe Barbalho](https://github.com/Barbalho12)
- [Jackson Rauup](https://github.com/jacksonrauupti)
- [Raul Silveira](https://github.com/RaulMacintosh)

## Guia de Execução ##

#### Pré-requisitos 
 	
[Maven](http://maven.apache.org/install.html)

[Java8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)

#### Executando pelo maven 

Clona o projeto:

```sh
git clone https://github.com/RaulMacintosh/SISE-Sytstem.git
```

Acessa a pasta do projeto e Executa:
```sh
$ mvn package
```

Após construir o *target* do projeto, executa:

```sh
$ java -cp target/sise-1.0-SNAPSHOT-jar-with-dependencies.jar br.ufrn.imd.sise.engine.Manager
```

#### Executando pelo eclipse 

1. Importa o projeto: 
	
	'File > 'Import' > 'Existing Maven Projects' > 'Next' > 'Browse' > Seleciona a pasta do projeto > 'Ok' > 'Finish' 

2. Espera atualizar as dependencias de bibliotecas;

3. Executa a classe Manager.java;

## Resultados ##

Os resultados serão exibidos em um log de execução de cada etapa do sistema.
