import java.util.List;

public class Route {
    private String currentNode;
    private int  remainingStrength;
    private List<String> path;

    public Route(String currentNode, int remainingStrength, List<String> path) {
        this.currentNode = currentNode;
        this.remainingStrength = remainingStrength;
        this.path = path;
    }

    public String getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(String currentNode) {
        this.currentNode = currentNode;
    }

    public int getRemainingStrength() {
        return remainingStrength;
    }

    public void setRemainingStrength(int remainingStrength) {
        this.remainingStrength = remainingStrength;
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }
}
