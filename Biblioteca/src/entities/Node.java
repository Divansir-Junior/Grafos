package entities;

import java.util.List;

class Node<K, T> {
    private K key;
    private K endNode;
    private List<T> recommendations;

    public Node(K key, K endNode, List<T> recommendations) {
        this.key = key;
        this.endNode = endNode;
        this.recommendations = recommendations;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public K getEndNode() {
        return endNode;
    }

    public void setEndNode(K endNode) {
        this.endNode = endNode;
    }

    public List<T> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<T> recommendations) {
        this.recommendations = recommendations;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Node<?, ?> node = (Node<?, ?>) obj;
        return key.equals(node.key) && endNode.equals(node.endNode);
    }

    @Override
    public int hashCode() {
        int result = key.hashCode();
        result = 31 * result + endNode.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", endNode=" + endNode +
                ", recommendations=" + recommendations +
                '}';
    }
}
