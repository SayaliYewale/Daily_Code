# First Unique Character in a String — Java

**LeetCode:** #387 (First Unique Character in a String)  
**Difficulty:** Easy  
**Topics:** String, HashMap, LinkedHashMap, Java Streams

## 📌 Problem Statement

Given a string, find the index of the first non-repeating (unique) character in it. If no such character exists, return -1.

Example

Input: "sayasli"  
Output: 1  
Explanation: The first non-repeating character is 'a' at index 1.

---

# 🔹 Approach 1 — Brute Force

## Idea
Check each character and count how many times it appears by scanning the whole string. The first character with count 1 is the answer.

## Algorithm
1. For each index i from 0 to n-1:
2.   Initialize count = 0.
3.   For each index j from 0 to n-1:
4.     If s[i] == s[j], increment count.
5.   If count == 1, return i.
6. If no unique character found, return -1.

## Code
```java
// Method from nonRepeatChar.java
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
```

## Complexity
| Complexity | Value |
| ---------- | ----- |
| Time       | O(n^2) |
| Space      | O(1)   |

---

# 🔹 Approach 2 — Optimal (LinkedHashMap)

## Idea
Use a LinkedHashMap to count occurrences while preserving insertion order. Then iterate through the string and return the first character whose count is 1. Using LinkedHashMap ensures deterministic order equal to first occurrence.

## Algorithm
1. Create LinkedHashMap<Character, Integer> map.
2. Iterate the string and update counts in the map.
3. Iterate the string again and return the index of the first character with count 1 from the map.
4. If none found, return -1.

## Code
```java
// Method from nonRepeatChar.java
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
```

> Note: The method name in the implementation is `optimalApproch()` (spelling preserved).

## Complexity
| Complexity | Value |
| ---------- | ----- |
| Time       | O(n)  |
| Space      | O(n)  |

---

# 🔹 Approach 3 — Java Streams

## Idea
Use an IntStream over the string characters to build a LinkedHashMap of character counts via collectors, then find the first entry with count 1.

## Code
```java
// Method from nonRepeatChar.java
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
```

## How It Works (Stream pipeline)
1. input.chars() — creates an IntStream of code points (as ints).
2. mapToObj(c -> (char) c) — boxes each int to a Character.
3. collect(groupingBy(..., LinkedHashMap::new, counting())) — groups by character into a LinkedHashMap and counts occurrences (Long values).
4. entrySet().stream() — stream over map entries in insertion order.
5. filter(e -> e.getValue() == 1) — keep entries with count 1.
6. map(Map.Entry::getKey) — extract the Character key.
7. findFirst().orElseThrow() — return the first unique character or throw if none found.
8. input.indexOf(ch) — find index of that character in the original string.

Important note: `orElseThrow()` will throw a NoSuchElementException if there is no unique character. The code then returns input.indexOf(ch) — normally this would be -1 if character not present, but the exception prevents that fallback.

## Complexity
| Complexity | Value |
| ---------- | ----- |
| Time       | O(n) average (stream operations traverse input and grouping) |
| Space      | O(n)  |

---

# ☕ Java Concepts Used

* Primitive streams: IntStream via `input.chars()`
* Boxing: `.mapToObj(c -> (char) c)`
* Collections: LinkedHashMap to preserve insertion order
* Map methods: `getOrDefault`
* Collectors: `groupingBy`, `counting`
* Stream terminal ops: `findFirst()`, `orElseThrow()`

---

# 🔍 Stream Operations Used

* chars() / IntStream — produces a stream of int code points from the string
* mapToObj(...) — converts ints to Character objects
* collect(groupingBy(..., LinkedHashMap::new, counting())) — groups characters into a LinkedHashMap and counts occurrences
* entrySet().stream() — streams over Map entries
* filter(...) — keeps entries with count 1
* map(...) — projects entries to keys
* findFirst() — returns first element of the stream (wrapped in Optional)
* orElseThrow() — get value or throw if absent

---

# 📊 Complexity Comparison

| Approach    | Time    | Space | Purpose |
| ----------- | -------:| -----:| ------- |
| Brute Force | O(n^2)  | O(1)  | Simple, easy to reason, but slow for large n |
| Optimal     | O(n)    | O(n)  | Practical solution using counts with deterministic order (LinkedHashMap) |
| Streams     | O(n)    | O(n)  | Functional style; similar algorithmic complexity to Optimal but uses Streams and collectors |

---

# 💡 Interview Note

I would first explain the brute-force approach to demonstrate understanding (O(n^2)). Then present the optimized counting approach using a HashMap/LinkedHashMap (O(n) time, O(n) space). The LinkedHashMap variant is useful when you need to preserve first-occurrence order.

The Streams implementation is useful to show familiarity with Java 8+ features, but it is not algorithmically better than the LinkedHashMap approach (it has similar time and space complexity). Also, be careful to handle the case where there is no unique character — the current `streamApproch()` uses `orElseThrow()` which will throw an exception if none exists.

---

# ▶️ Sample Execution

The `nonRepeatChar` class includes a `static void main()` (no args) that demonstrates the methods:

```java
String input = "sayasli";
System.out.println(bruteForce(input));        // prints 1
System.out.println(optimalApproch(input));    // prints 1
System.out.println(streamApproch(input));     // prints 1
```

Note: `optimalApproch()` is the exact method name used in the implementation (spelling preserved). Also `streamApproch()` uses `orElseThrow()` which may throw when no unique character exists.

---

# 📝 Key Takeaways

* Counting character occurrences is the key to solving this problem efficiently.
* LinkedHashMap preserves insertion order and is handy to retrieve the first unique character.
* Java Streams can express the same logic functionally; be mindful of exceptions from `orElseThrow()` and boxing costs.

---

# 📚 Concepts Practiced

## DSA
* String processing
* Hashing / frequency counting

## Java
* Collections (LinkedHashMap)
* Map APIs (getOrDefault, entrySet)

## Java Streams
* IntStream, mapToObj, collect(groupingBy, counting), filter, map, findFirst, Optional

