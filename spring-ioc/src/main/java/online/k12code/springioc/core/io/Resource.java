package online.k12code.springioc.core.io;

import online.k12code.springioc.BeansException;

import java.io.IOException;
import java.io.InputStream;

/**
 * 资源接口
 *
 * @author Carl
 * @since 1.0.0
 */
public interface Resource {


    /**
     * 获取输入流
     *
     * @return
     * @throws IOException
     */
    InputStream getInputStream() throws IOException;
}
