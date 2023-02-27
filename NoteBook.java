import java.awt.*;
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
    // private static int[] rams = { 2, 4, 8, 12, 16, 32, 64 };
    // private static int[] hdds = { 128, 256, 512, 1024, 2048, 3072, 4096, 8192 };
    // private static String[] oss = { "Windows 10", "Windows 11", "Mac OS", "Ubuntu" };
    // private static Color[] colors = { Color.black, Color.white, Color.gray };
    private static HashMap<String,String[]> configMap;
    private static Random rand;
    private static ArrayList<NoteBook> notebooks;
    
    // private int _ram;
    // private int _hdd;
    // private int _os;
    // private int _color;
    private HashMap<String,String> _configs;

    static
    {
        SetConfigurationMap();
        rand = new Random();
        notebooks = new ArrayList<NoteBook>();
        for(int i = 0; i < 100; i++)
            notebooks.add(new NoteBook());
    }

    private NoteBook()
    {
        SetRandomConfigs();
        // SetRandomRam();
        // SetRandomHdd();
        // SetRandomOs();
        // SetRandomColor();
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
    
    // private void SetRandomRam()
    // {
    //     int ramIndex = rand.nextInt(rams.length);
    //     _ram = rams[ramIndex];
    // }

    // private void SetRandomHdd()
    // {
    //     int hddIndex = rand.nextInt(hdds.length);
    //     _hdd = hdds[hddIndex];
    // }

    // private void SetRandomOs()
    // {
    //     int osIndex = rand.nextInt(oss.length);
    //     _os = oss[osIndex];
    // }

    // private void SetRandomColor()
    // {
    //     int colorIndex = rand.nextInt(colors.length);
    //     _color = colors[colorIndex];
    // }

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

    public static ArrayList<Entry<String,String[]>> GetConfigList()
    {
        return new ArrayList<Entry<String,String[]>>(configMap.entrySet());
    }

    public static String[] GetConfigValues(String configKey)
    {
        String[] configValues = configMap.get(configKey);
        return Arrays.copyOf(configValues, configValues.length);
    }
}