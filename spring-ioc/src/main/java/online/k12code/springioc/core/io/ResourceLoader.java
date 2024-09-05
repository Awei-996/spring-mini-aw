package online.k12code.springioc.core.io;

/**
 *
 * 资源加载器接口
 * @author Carl
 * @since 1.0.0
 */
public interface ResourceLoader {

    /**
     * 获取资源
     * @param location 位置
     * @return 资源
     */
    Resource getResource(String location);
}
