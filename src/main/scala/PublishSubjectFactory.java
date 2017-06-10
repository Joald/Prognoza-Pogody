import rx.subjects.PublishSubject;

/**
 * Due to Scala's lack of support for static members of non-static classes
 * I resorted to creating appropriate rxJava objects in a factory class.
 */
public class PublishSubjectFactory {
    public static PublishSubject<scala.Int> intPipe() {
        return PublishSubject.create();
    }
    public static PublishSubject<scala.Double> doublePipe() {
        return PublishSubject.create();
    }
    public static PublishSubject<String> stringPipe() {
        return PublishSubject.create();
    }
}