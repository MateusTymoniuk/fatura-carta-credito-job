<h3 align="center">
  Job spring batch utilizando steps com processamento multithread
</h3>


<p align="center">
  <a href="#-sobre">Sobre</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-instalação">Instalação</a>&nbsp;&nbsp;&nbsp;
</p>


## **Sobre**
Esta é uma aplicação feita com Spring Batch, que lê dados do banco e após processá-los, salva o resultado em arquivos.
O diferencial é que a configuração dos steps é feita de forma a utilizar várias threads para o processamento.

O programa é feito baseado no curso de [Otimização de desempenho para jobs Spring Batch](https://www.udemy.com/share/1042o8A0MedFZXTH4=/)

Este não é o exemplo utilizado no decorrer do curso. Ainda existe um problema que eu não consegui resolver que é, ao tentar salvar os arquivos, as threads não estão conseguindo sincronizar a escrita (aparentemente).

## **Utilização**
1 - Clonar o [repositório](https://github.com/MateusTymoniuk/fatura-carta-credito-job) em seu computador;

2 - **Criar o banco** mysql da forma que achar conveniente, ou outro banco relacional

3 - **Alterar o application.properties** com as respectivas credenciais e conexões do banco

4 - **Criar as tabelas** contidas no arquivo:

    files/scripts.sql

5 - **Executar a aplicação**
