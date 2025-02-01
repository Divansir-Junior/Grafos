package entities;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Graph<K extends Book> {
    private Map<K, List<Node<K, Book>>> G = null;
    private List<Book> allBooks = null;
    private Queue<K> queueBooks = null;

    public Graph() {
        G = new HashMap<>();
        allBooks = new ArrayList<>();
        queueBooks = new LinkedList<>();
    }

    public boolean addEdge(K v1, K v2, List<Book> recommendations) {
        if (!G.containsKey(v1)) {
            G.put(v1, new LinkedList<>());
        }
        List<Node<K, Book>> nodes = G.get(v1);
        nodes.add(new Node<>(v1, v2, recommendations));
        G.put(v1, nodes); // Atualiza a lista de nós para o vértice v1
        
        // Adiciona os livros ao grafo para garantir que estejam disponíveis para exclusão
        for (Book book : recommendations) {
            addBook(book);
        }
        
        return true;
    }

    public boolean addUndirectedEdge(K v1, K v2, List<Book> recommendations) {
        addEdge(v1, v2, recommendations);
        addEdge(v2, v1, recommendations); // Adiciona uma aresta de volta para permitir arestas bidirecionais
        return true;
    }

    public void searchBook(String bookName) {   
        for (List<Node<K, Book>> nodes : G.values()) {  //Percorre a lista de nós e livros procurando o nome do livr
            for (Node<K, Book> node : nodes) {
                Book currentBook = node.getKey();
                if (currentBook.getNome().equals(bookName)) {
                    System.out.println("Livro encontrado: " + currentBook);
                    return;
                }
            }
        }
        System.out.println("Livro não encontrado.");
    }

    public void searchRecommendationOfBook(String procurar) { //Percorre uma lista,caso o nó de recomendação seja igual a valor da chave,retorna o valor correspondente
        for (List<Node<K, Book>> nodes : G.values()) {
            for (Node<K, Book> node : nodes) {
                for (Book recommendation : node.getRecommendations()) {
                    if (recommendation.getNome().equals(procurar)) {
                        System.out.println("Livro encontrado: " + node.getKey());
                        return;
                    }
                }
            }
        }
        System.out.println("Recomendação de livro com o nome : " + "{" +  procurar +  "}" + " " + "não encontrada");
    }

    public boolean removeBook(Book bookToRemove) {  
        Iterator<Map.Entry<K, List<Node<K, Book>>>> iterator = G.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<K, List<Node<K, Book>>> entry = iterator.next();
            List<Node<K, Book>> nodes = entry.getValue();
            nodes.removeIf(node -> node.getEndNode().equals(bookToRemove) || node.getKey().equals(bookToRemove));
            if (nodes.isEmpty()) {
                iterator.remove(); // Remove o vértice se não houver mais arestas associadas
            }
        }
        return allBooks.remove(bookToRemove); // Remove o livro da lista de todos os livros
    }

    public void printGraph() {  //Exibe o grafo 
        System.out.println(" === LISTAGEM DE LIVROS === ");
        System.out.println(this);
    }

    public void addQueu(K book) {  //Adiciona todos os livros para uma fila 
        queueBooks.add(book);
        
    }
    
    public void firstBookQueue() { //Procura o primeiro elemento do topo da fila
        System.out.println("O primeiro elemento da fila é : " + queueBooks.peek());
    }
    
    public void printLastElement() {
        Queue<K> tempQueue = new LinkedList<>(queueBooks); //Fila  temporária 

        while (tempQueue.size() > 1) {  //Percorre a lista temporaria,até encontrar 
            tempQueue.remove();
        }

        if (!tempQueue.isEmpty()) {
            System.out.println("O último elemento da fila é : " + tempQueue.peek());
        } else {
            System.out.println("A fila está vazia.");
        }
    }

    public void printQueue() {
        System.out.println(" === LIVROS NA FILA ===");
        int i = 1; 
        for (K key : queueBooks) { //Percorre a fila de livros,imprimindo cada valor da fila com seu indíce 
            System.out.println(i  +  " " + key.getNome()); 
            i++;
        }
    }
    
    public void addBook(Book book) {
        allBooks.add(book);   //Adiciona  livro(s) 
    }

    @Override
    public String toString() {  
        StringBuilder data = new StringBuilder();
        for (K key : G.keySet()) {
            List<Node<K, Book>> nodes = G.get(key);
            for (Node<K, Book> node : nodes) {
                data.append("Nó: ").append(node.getKey()).append(" | ");
                data.append("Nó Vizinho: ").append(node.getEndNode()).append(" | ");
                data.append("Recomendações: ");
                List<Book> recommendations = node.getRecommendations();
                for (Book recommendation : recommendations) {
                    data.append(recommendation.getNome());
                    if (recommendations.size() > 1) {
                        data.append(", ");
                    }
                }
                data.append("\n");
            }
        }
        return data.toString();
    }
    
    public List<Book> findShortestPathBetweenBooks(String sourceBookName, String destinationBookName) {
        Book sourceBook = null;
        Book destinationBook = null;

        // Encontra os livros correspondentes aos nomes fornecidos
        for (K key : G.keySet()) {
            if (key.getNome().equals(sourceBookName)) {
                sourceBook = key;
            } else if (key.getNome().equals(destinationBookName)) {
                destinationBook = key;
            }
        }

        // Verifica se os livros de origem e destino foram encontrados
        if (sourceBook == null || destinationBook == null) {
            System.out.println("Livro de origem ou destino não encontrado.");
            return new ArrayList<>();
        }

        // Chama o método findShortestPath para encontrar o menor caminho entre os livros
        return findShortestPath(sourceBook, destinationBook);
    }

    
    
    
    
    public List<Book> findShortestPath(Book sourceBook, Book destinationBook) {
        // Mapeia cada nó para seu custo de distância do nó de origem
        Map<Book, Integer> distance = new HashMap<>();
        // Mapeia cada nó para o nó anterior no caminho mais curto do nó de origem a ele
        Map<Book, Book> previous = new HashMap<>();
        // Inicializa uma fila de prioridade para ordenar os nós pelo custo acumulado do nó de origem até o nó atual
        PriorityQueue<Book> queue = new PriorityQueue<>((b1, b2) -> distance.get(b1) - distance.get(b2));
        
        // Inicializa o custo de distância de todos os nós, exceto o nó de origem, como infinito
        for (Book book : G.keySet()) {
            if (book.equals(sourceBook)) {
                distance.put(book, 0); // O custo de distância para o nó de origem é 0
            } else {
                distance.put(book, Integer.MAX_VALUE); // Inicializa todos os outros nós com custo infinito
            }
            queue.offer(book); // Adiciona todos os nós à fila de prioridade
        }
        
        while (!queue.isEmpty()) {
            Book current = queue.poll(); // Remove o nó com o menor custo de distância da fila
            // Itera sobre todos os vizinhos do nó atual
            for (Node<Book, Book> neighbor : G.get(current)) {
                // Calcula o novo custo de distância considerando o custo do nó atual até o vizinho
                int newDistance = distance.get(current) + 1; // Considerando que todas as arestas têm peso 1
                if (newDistance < distance.get(neighbor.getEndNode())) {
                    distance.put(neighbor.getEndNode(), newDistance); // Atualiza o custo de distância do vizinho
                    previous.put(neighbor.getEndNode(), current); // Atualiza o nó anterior do vizinho
                    queue.remove(neighbor.getEndNode()); // Remove o vizinho e o reinsere para reordenar na fila de prioridade
                    queue.offer(neighbor.getEndNode());
                }
            }
        }
        
        // Reconstrói o caminho mais curto a partir do nó de destino até o nó de origem
        List<Book> shortestPath = new ArrayList<>();
        Book current = destinationBook;
        while (previous.containsKey(current)) {
            shortestPath.add(current);
            current = previous.get(current);
        }
        // Adiciona o nó de origem ao caminho mais curto
        shortestPath.add(sourceBook);
        // Inverte a ordem dos nós para obter o caminho do nó de origem ao nó de destino
        Collections.reverse(shortestPath);
        
        return shortestPath;
    }

    
}



