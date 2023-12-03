package entity;

import data_access.DialogflowDAOImpl;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

//public class TestTrain {
//    @Test
//    public void testTrain() throws IOException {
//        DialogflowDAOImpl dao = new DialogflowDAOImpl();
//        Map<String, List<String>> map = new TreeMap<String, List<String>>();
//        map.put("itch", Arrays.asList(new String[]{"itchy"}));
//        map.put("bump", Arrays.asList(new String[]{"small bumps", "bumps"}));
//        dao.setIntentNEntities("Allergy", Arrays.asList(new String[]{"My skins feel itchy", "My skin has bumps"}),
//                Arrays.asList(new String[]{"You might have allergies, finding you a specialist"}),
//                map);
//    }
//}
