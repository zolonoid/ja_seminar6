import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

/**
 * NoteBook
 */
public class NoteBook
{
    private static HashMap<String,String[]> configMap;
    private static Random rand;
    private static ArrayList<NoteBook> notebooks;
    private static int lastId = 0;
    
    private HashMap<String,String> _configs;
    private int _id;

    static
    {
        configMap = new HashMap<String,String[]>();
        SetConfigurationMap();
        rand = new Random();
        notebooks = new ArrayList<NoteBook>();
        for(int i = 0; i < 100; i++)
            notebooks.add(new NoteBook());
    }

    private NoteBook()
    {
        _configs = new HashMap<String,String>();
        SetRandomConfigs();
        _id = ++lastId;
    }
    
    private static void SetConfigurationMap()
    {
        configMap.put("ОЗУ", new String[] { "2GB", "4GB", "8GB", "12GB", "16GB", "32GB", "64GB" });
        configMap.put("Объем ЖД", new String[] { "128GB", "256GB", "512GB", "1TB", "2TB", "3TB", "4TB" });
        configMap.put("Операционная система", new String[] { "Windows 10", "Windows 11", "Mac OS", "Ubuntu" });
        configMap.put("Цвет", new String[] { "Черный", "Белый", "Серый" });
    }
    

    private void SetRandomConfigs()
    {
        for(var config : configMap.entrySet())
        {
            
            int index = rand.nextInt(config.getValue().length);
            _configs.put(config.getKey(), config.getValue()[index]);
        }
    }

    private boolean isMatch(Map<String,String> configs)
    {
        for(var config : configs.entrySet())
        {
            if(!_configs.containsKey(config.getKey()))
                return false;
            if(!_configs.get(config.getKey()).equals(config.getValue())) 
                return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        var str = new StringBuilder(String.format("NoteBook %d", _id));
        for(var config : _configs.entrySet())
            str.append(String.format("\n%s: %s", config.getKey(), config.getValue()));
        return str.toString();
    }

    public static ArrayList<Entry<String,String[]>> GetConfigList()
    {
        return new ArrayList<Entry<String,String[]>>(configMap.entrySet());
    }

    public static String[] GetConfigValues(String configKey)
    {
        String[] configValues = configMap.get(configKey);
        return Arrays.copyOf(configValues, configValues.length);
    }

    public static String FilterSearchResult(HashMap<String,String> filters)
    {
        var result = new StringBuilder();
        for(NoteBook notebook : notebooks)
        {
            if(!notebook.isMatch(filters)) continue;
            result.append(notebook.toString());
            result.append("\n\n");
        }
        return result.toString();
    }
}