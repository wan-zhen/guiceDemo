import javax.inject.Singleton;

public class SimpleHelloPrinter implements IHelloPrinter {
    public void print() {
        System.out.println("SimpleHelloPrinter");
    }
}
