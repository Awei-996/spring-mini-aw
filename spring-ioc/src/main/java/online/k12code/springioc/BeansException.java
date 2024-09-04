package online.k12code.springioc;

/**
 * 处理Bean异常
 * @author Carl
 * @since 1.0.0
 */
public class BeansException extends RuntimeException {

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
