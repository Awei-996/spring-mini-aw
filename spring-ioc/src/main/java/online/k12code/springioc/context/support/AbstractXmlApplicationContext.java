package online.k12code.springioc.context.support;


import online.k12code.springioc.factory.support.DefaultListableBeanFactory;
import online.k12code.springioc.factory.xml.XmlBeanDefinitionReader;

/**
 * @author Carl
 * @since 1.0.0
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (configLocations != null) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
