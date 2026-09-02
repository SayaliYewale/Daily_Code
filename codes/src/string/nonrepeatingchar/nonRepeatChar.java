package string.nonrepeatingchar;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class nonRepeatChar {

    public static int bruteForce(String input){
        for(int i=0;i<input.length();i++){
            int count=0;
            for(int j=0;j<input.length();j++){
                if(input.charAt(i)==input.charAt(j)){
                    count++;
                }
            }
            if(count==1){
                return i;
            }
        }
        return -1;
    }

    public static int optimalApproch(String input){
        Map<Character,Integer> map=new LinkedHashMap<>();
        for(int i=0;i<input.length();i++){
            char s=input.charAt(i);
            map.put(s,map.getOrDefault(s,0)+1);
        }

        for (int i=0;i<input.length();i++){
            if (map.get(input.charAt(i))==1){
                return i;
            }
        }
        return -1;
    }

    public static int streamApproch(String input){
        Character ch= input.chars()
                .mapToObj(c->(char)c)
                .collect(Collectors.groupingBy(c->c,LinkedHashMap::new,Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e->e.getValue()==1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow();
        return ch == null ? -1 : input.indexOf(ch);
    }

    static void main() {
        String input="sayasli";
        System.out.println(bruteForce(input));
        System.out.println(optimalApproch(input));
        System.out.println(streamApproch(input));
    }
}
