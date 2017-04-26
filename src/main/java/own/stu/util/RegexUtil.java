package own.stu.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by dell on 2017/4/26.
 */
public class RegexUtil {

    /**
     * 正则匹配第一条结果
     * @param dealStr
     * @param regexStr
     * @param n
     * @return
     */
    public static String getFirstString(String dealStr, String regexStr, int n){
        if(StringUtils.isEmpty(dealStr) || n < 1){
            return "";
        }
        Pattern pattern = Pattern.compile(regexStr, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher matcher = pattern.matcher(dealStr);
        while (matcher.find()){
            return matcher.group(n).trim();
        }
        return "";
    }

    /**
     * 正则匹配结果，将匹配结果组装成数组
     * @param dealStr
     * @param regexStr
     * @param n
     * @return
     */
    public static List<String> getList(String dealStr, String regexStr, int n){
        List<String> list = new ArrayList<String>();
        if(StringUtils.isEmpty(dealStr) || n < 1){
            return list;
        }
        Pattern pattern = Pattern.compile(regexStr, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher matcher = pattern.matcher(dealStr);
        while (matcher.find()){
            list.add(matcher.group(n).trim());
        }
        return list;
    }

    /**
     * 获取全部
     * @param dealStr
     * @param regexStr
     * @param array
     * @return
     */
    public static List<String[]> getList(String dealStr, String regexStr, int[] array){
        List<String[]> list = new ArrayList<String[]>();
        if(StringUtils.isEmpty(dealStr) || array == null){
            return list;
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 1) {
                return list;
            }
        }
        Pattern pattern = Pattern.compile(regexStr, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher matcher = pattern.matcher(dealStr);
        while (matcher.find()){
            String[] ss = new String[array.length];
            for (int i = 0; i < array.length; i++) {
                ss[i] = matcher.group(array[i]).trim();
            }
            list.add(ss);
        }
        return list;
    }

    public static void main(String[] args) {
        String dealStr = "ab1234asdv";
        String regexStr = "a(.*?)a";
        System.out.println(RegexUtil.getFirstString(dealStr, regexStr, 1));
        System.out.println(RegexUtil.getList(dealStr, regexStr, 1));

    }
}
