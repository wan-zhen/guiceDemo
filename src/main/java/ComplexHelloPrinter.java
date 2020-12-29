import javax.inject.Singleton;

@Singleton
public class ComplexHelloPrinter implements IHelloPrinter{
    public void print() {
        System.out.println("ComplexHelloPrinter");
    }
}
