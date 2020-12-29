import com.google.inject.Guice;
import com.google.inject.Injector;

import javax.inject.Inject;
import javax.inject.Named;

public class Sample {
    @Inject
    private HelloPrinter printer;

    @Named("simple")
    private IHelloPrinter simpleHelloPrinter;

    @Named("complex")
    private IHelloPrinter complexHelloPrinter;

    @Inject
    public Sample(@Named("simple") IHelloPrinter simpleHelloPrinter,
                  @Named("complex") IHelloPrinter complexHelloPrinter) {
        this.simpleHelloPrinter = simpleHelloPrinter;
        this.complexHelloPrinter = complexHelloPrinter;
    }

    public void hello() {
        printer.print();
        simpleHelloPrinter.print();
        complexHelloPrinter.print();
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new SampleModule());
        Sample sample = injector.getInstance(Sample.class);
        sample.hello();
    }
}
