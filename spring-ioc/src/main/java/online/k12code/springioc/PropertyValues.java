package online.k12code.springioc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Carl
 * @since 1.0.0
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>(0);

    public void addPropertyValue(PropertyValue propertyValue) {
        propertyValueList.add(propertyValue);
    }

    public PropertyValue[] getPropertyValues(){
        return propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName){
        for (PropertyValue pv : this.propertyValueList) {
            if (pv.getName().equals(propertyName)) {
                return pv;
            }
        }
        return null;
    }
}
