package Model;

import java.util.List;
import java.util.Map;

public class Language {

    String name;
    List<Map<Character,Long>> mapList;

    public Language(String name, List<Map<Character, Long>> mapList) {
        this.name = name;
        this.mapList = mapList;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Map<Character, Long>> getMapList() {
        return mapList;
    }
}
