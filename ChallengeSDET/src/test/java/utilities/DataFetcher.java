package utilities;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataFetcher {
    @DataProvider(name = "JsonDataFetcher")
    public static Object[][] fetchTestData(Method m) {
        Object[][] resultList = new Object[0][];
        try {
            String jsonData = "";
            String fileName = "src/test/resources/testdata/" + m.getName() + ".json";
            Scanner reader = new Scanner(new File(fileName));
            while(reader.hasNextLine()) {
                jsonData = jsonData.concat(reader.nextLine());
            }
            JSONArray jsonArray = new JSONArray(jsonData);
            resultList = testDataJsonArrayToOutputList(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }
    private static List<Object> jsonArrayToList(JSONArray jsonArr){
        List<Object> resultList = new ArrayList<>();
        for(int i = 0; i < jsonArr.length(); i++) {
            resultList.add(jsonArr.get(i));
        }
        return resultList;
    }
    private static Object[][] testDataJsonArrayToOutputList(JSONArray jsonArr) {
        Object[][] resultList = new Object[jsonArr.length()][];
        List<Object> listOfObjects = jsonArrayToList(jsonArr);
        for(int i = 0; i < listOfObjects.size(); i++) {
            resultList[i] = jsonArrayToList(
                    ((JSONObject)listOfObjects.get(i)).getJSONArray("Parameters")
            ).toArray();
        }
        return resultList;
    }
}
