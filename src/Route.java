import java.util.List;

public class Route {
    String currentNode;
    int  remainingStrength;
    List<String> path;

    public Route(String currentNode, int remainingStrength, List<String> path) {
        this.currentNode = currentNode;
        this.remainingStrength = remainingStrength;
        this.path = path;
    }
}
