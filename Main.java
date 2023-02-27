import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

public class Main
{
    private static Scanner sc = null;
    private static HashMap<String,String> filters = new HashMap<String,String>();

    public static void main(String[] args)
    {
        try
        {
            sc = new Scanner(System.in);
            while(true)
            {
                String filter = FilterChoice();
                if(filter == null)
                {
                    System.out.println(NoteBook.FilterSearchResult(filters));
                    break;
                }
                FilterValueChoice(filter);
            }
        }
        catch (Exception e)
        {
            System.out.printf("Ошибка: %s", e.getMessage());
        }
        finally
        {
            if(sc != null)
                sc.close();

        }
    }

    private static String FilterChoice()
    {
        ArrayList<Entry<String,String[]>> configList = NoteBook.GetConfigList();
        for(int menuIndex = 1; menuIndex <= configList.size(); menuIndex++)
        {
            var config = configList.get(menuIndex - 1);
            System.out.printf("%d - %s", menuIndex, config.getKey());
            if(filters.containsKey(config.getKey()))
                System.out.printf(": %s", filters.get(config.getKey()));
            System.out.println();
        }
        int menuIndex = sc.nextInt() - 1;
        if(menuIndex < 0) return null;
        return configList.get(menuIndex).getKey();
    }

    private static void FilterValueChoice(String filterKey)
    {
        String[] values = NoteBook.GetConfigValues(filterKey);
        for(int menuIndex = 1; menuIndex <= values.length; menuIndex++)
        {
            var value = values[menuIndex - 1];
            System.out.printf("%d - %s", menuIndex, value);
            System.out.println();
        }
        int menuIndex = sc.nextInt() - 1;
        filters.put(filterKey, values[menuIndex]);
    }
}
