📚 Sistema de Gestão de Livros com Grafos

Este projeto implementa um sistema de gerenciamento de livros utilizando um grafo para representar as conexões entre livros e suas recomendações. Além disso, há suporte para manipulação de filas e busca pelo menor caminho entre livros.

O projeto é dividido em quatro principais classes:

Main → Classe principal responsável pelo menu interativo.

Graph<K extends Book> → Classe que implementa um grafo genérico de livros.

Node<K, T> → Representa uma conexão (aresta) entre dois livros (vértices) do grafo.

Book → Representa um livro com nome, autor e ano de publicação.

===========================================================================================================================================================================================================================
 Funcionamento do Código

1️⃣ Classe Main

Inicializa o grafo e adiciona os livros.

Implementa um menu interativo para operações como:

Visualizar livros.

Procurar livros.

Buscar recomendações de livros.

Excluir livros.

Gerenciar uma fila de leitura.

Encontrar o caminho mais curto entre dois livros.

Utiliza Scanner para entrada do usuário.

Exempo de execução do menu : 
▛----------------------------------▜
|(1) Ver livros
|(2) Procurar livro
|(3) Procurar recomendações de livro
|(4) Excluir livro
|(5) Ver fila de livros
|(6) Ver caminho mais curto entre livros
|(7) Sair
▙---------------------------------▟

2️⃣ Classe Graph<K extends Book>

Implementa um grafo utilizando HashMap<K, List<Node<K, Book>>> para armazenar conexões entre livros.

Métodos principais:

addEdge() → Adiciona conexões entre livros.

searchBook() → Procura livros no grafo.

searchRecommendationOfBook() → Retorna recomendações de livros.

removeBook() → Remove um livro e suas conexões.

findShortestPathBetweenBooks() → Calcula o menor caminho entre dois livros usando Dijkstra.

Métodos auxiliares para manipulação da fila de leitura.

4️⃣ Classe Book

Representa um livro com atributos:

nome

autor

anoPublicacao

Implementa equals() e hashCode() para comparação de livros.

🚀 Funcionalidades Implementadas

✅ Adicionar livros ao grafo✅ Procurar livros pelo nome✅ Buscar recomendações de livros✅ Remover livros✅ Manipulação de filas de leitura✅ Encontrar o menor caminho entre livros
