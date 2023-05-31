package implementation.testingutils;

import java.awt.Container;
import java.util.*;
import java.util.stream.Collectors;

public class SwingUtils {
    // Container is imported from java.awt
    public static Container getChildComponentByName(Container parentComponent, String childName) {

        // Define data structures that will hold components
        Map<String, Container> allComponentsMap = new HashMap();
        List<Container> allComponents = new ArrayList<>();

        // Iterating through the components structure and adding it to the List using our recursive function
        addAllChildComponentsToList(allComponents, parentComponent);

        // Iterating through the List and adding them to a HashMap keyed with their name
        for (Container c : allComponents) {
            allComponentsMap.put(c.getName(), c);
        }

        // Returning a component with the given name
        if (allComponentsMap.containsKey(childName)) {
            return allComponentsMap.get(childName);
        } else {
            System.out.println("ERROR: No match found when looking for GUI child components.");
            return null;
        }
    }

    public static void addAllChildComponentsToList(List<Container> componentArr, Container parentComponent) {

        // Making a list with all child components
        List<Container> childComponentsArr = Arrays.stream(parentComponent.getComponents()).map(c -> (Container) c).collect(Collectors.toList());

        if (childComponentsArr.size() > 0) {

            for (Container c : childComponentsArr) {

                // Adding a child component to the passed List
                componentArr.add(c);

                // Repeating the process if child has its own child components
                if (c.getComponents().length > 0) {
                    addAllChildComponentsToList(componentArr, c);
                }
            }
        } else {
            return;
        }

    }
}
