package online.k12code.springioc.core;

import online.k12code.springioc.core.io.DefaultResourceLoader;
import online.k12code.springioc.core.io.Resource;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author Carl
 * @since 1.0.0
 */
public class ResourceAndResourceLoaderTest {

    @Test
    public void testResourceLoader() throws Exception {
        // 先获取默认的资源加载器
        DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();

        // 加载classpath下面的资源
        // 他会根据传递的location返回对应Resource解析类
        Resource resource = defaultResourceLoader.getResource("classpath:hello.txt");
        InputStream inputStream = resource.getInputStream();
        String s = readFromInputStream(inputStream);
        System.err.println("classpath:"+s);

        // 加载文件系统资源
        Resource resource1 = defaultResourceLoader.getResource("src/test/resources/hello.txt");
        InputStream inputStream1 = resource1.getInputStream();
        String s1 = readFromInputStream(inputStream1);
        System.err.println("系统文件:"+s1);

        // 加载url资源
        Resource resource2 = defaultResourceLoader.getResource("https://www.baidu.com");
        InputStream inputStream2 = resource2.getInputStream();
        String s2 = readFromInputStream(inputStream2);
        System.err.println("url:"+s2);

    }

    public String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
        }
        return result.toString();
    }
}
