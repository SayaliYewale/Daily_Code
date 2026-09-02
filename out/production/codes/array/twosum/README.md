# Two Sum — Java

**LeetCode:** #1  
**Difficulty:** Easy  
**Topics:** Array, HashMap

## 📌 Problem Statement

Given an integer array `nums` and an integer `target`, return the indices of the two numbers such that they add up to `target`. You may assume that each input has at most one solution, and you may not use the same element twice. The implementations below return `[-1, -1]` when no valid pair is found.

## Example

Input:

```java
nums = [1, 2, 3, 4, 5]
target = 3
```

Output:

```text
[0, 1]
```

Explanation: `nums[0] + nums[1] = 1 + 2 = 3`

---

# 🔹 Approach 1 — Brute Force

## Idea

Check every possible pair of elements and return the first pair whose sum equals the target.

## Algorithm

1. Iterate i from 0 to n-1.
2. For each i iterate j from i+1 to n-1 and check if a[i] + a[j] == target.
3. If found, return {i, j}.
4. If no pair found, return {-1, -1}.

## Code (as implemented)

```java
public static int[] bruteForce(int[] a, int target){
    for(int i=0;i<a.length;i++){
        for (int j=1;j<a.length;j++){
            if(a[i]+a[j]==target){
                return new int[]{i,j};
            }
        }
    }
    return new int[]{-1,-1};
}
```

Note: the implementation's inner loop starts at `j = 1` (as in the source). A typical and slightly more correct bound is `j = i + 1` to avoid comparing an element with itself and to reduce redundant checks. The current implementation may still work for many inputs but does extra comparisons.

## Complexity

| Complexity | Value |
| ---------- | ----- |
| Time       | O(n²) |
| Space      | O(1)  |

---

# 🔹 Approach 2 — Optimal HashMap Approach

## Idea

Use a HashMap to store seen values and their indices. For each element, check whether its complement (target - current) has already been seen; if so, you've found the pair.

## Algorithm

1. Create an empty HashMap<Integer,Integer> map.
2. For each index i:
   - Let num = target - a[i].
   - If map contains key num, return {map.get(num), i}.
   - Otherwise put (a[i], i) into the map.
3. If no pair found, return {-1, -1}.

## Code (as implemented)

```java
public static int[] optimalApproch(int[] a,int target){
    HashMap<Integer,Integer> map=new HashMap<>();
    for(int i=0;i<a.length;i++){
        int num=target-a[i];
        if(map.containsKey(num)){
            return new int[]{map.get(num),i};
        }
        map.put(a[i],i);
    }
    return new int[]{-1,-1};
}
```

> Note: The method name in the source is `optimalApproch()` (missing an "a" in "Approach"); this README preserves the original method name.

## Complexity

| Complexity | Value |
| ---------- | ----- |
| Time       | O(n)  |
| Space      | O(n)  |

---

# 🔹 Java Streams Approach

This implementation demonstrates a Java 8 Streams pipeline to generate index pairs and find the first matching pair.

## Code (as implemented)

```java
public static int[] streamApproch(int[] nums, int target) {
    return IntStream.range(0, nums.length)
            .boxed()
            .flatMap(i ->
                    IntStream.range(i + 1, nums.length)
                            .filter(j -> nums[i] + nums[j] == target)
                            .mapToObj(j -> new int[]{i, j})
            )
            .findFirst()
            .orElse(new int[]{-1, -1});
}
```

## How It Works (Stream pipeline)

1. `IntStream.range(0, nums.length)` — generates indexes [0..n-1].
2. `.boxed()` — converts the primitive `IntStream` to `Stream<Integer>` so we can use `flatMap` that returns object streams.
3. `.flatMap(i -> IntStream.range(i + 1, nums.length)...)` — for each `i` creates a stream of subsequent indices `j` to avoid duplicate unordered pairs.
4. `.filter(j -> nums[i] + nums[j] == target)` — keeps only j where sum matches target.
5. `.mapToObj(j -> new int[]{i, j})` — converts matching indices into an `int[]` result.
6. `.findFirst()` — returns the first found pair wrapped in `Optional<int[]>`.
7. `.orElse(new int[]{-1, -1})` — returns a sentinel if no pair found.

Although implemented with Streams, the pipeline still checks pairs in a nested manner — the algorithmic complexity remains O(n²).

## Complexity

| Complexity | Value |
| ---------- | ----- |
| Time       | O(n²) |
| Space      | O(1) (excluding stream allocation overhead) |

---

# ☕ Java Concepts Used

* Arrays (for example `Arrays.toString` used in the `main`).
* HashMap for the optimal approach.
* Java 8 Streams: `IntStream`, boxing, `flatMap`, `filter`, `mapToObj`, `Optional`.

# 📊 Complexity Comparison

| Approach    | Time  | Space | Purpose |
| ----------- | -----:| -----:| ------- |
| Brute Force | O(n²) | O(1)  | Simple, easy to reason about |
| HashMap     | O(n)  | O(n)  | Optimal average-case solution |
| Streams     | O(n²) | O(1)  | Demonstrates Java Streams; not algorithmically better |

---

# 💡 Interview Note

Start by explaining the brute-force solution to show understanding. Then present the HashMap approach as the optimized solution (O(n) time, O(n) space) — this is the expected interview answer for Two Sum. Mention the Streams implementation if asked about Java 8 features, and note that Streams demonstrates language features but does not improve algorithmic complexity.

---

# ▶️ Sample Execution

The repository's `twosum` class includes a `main` method that demonstrates all three approaches:

```java
int[] a = {1, 2, 3, 4, 5};
int target = 3;
System.out.println(Arrays.toString(twosum.bruteForce(a, target)));
System.out.println(Arrays.toString(twosum.optimalApproch(a, target)));
System.out.println(Arrays.toString(twosum.streamApproch(a, target)));
```

Expected output:

```
[0, 1]
[0, 1]
[0, 1]
```

---

# 📝 Key Takeaways

* Hashing is an effective technique to reduce pair-search from O(n²) to O(n) on average.
* Java Streams are useful for expressive, declarative code but may still have the same algorithmic cost.
* Pay attention to loop bounds (the brute force inner loop starts at `1` in the current implementation; typically it should be `i + 1`).

# 📚 Concepts Practiced

## DSA
* Hash Table
* Brute-force pair search

## Java
* Collections: HashMap
* Arrays utility

## Java Streams
* IntStream, boxed(), flatMap(), filter(), mapToObj(), Optional

---

## Files analyzed

* `twosum.java` (implementation of three approaches shown above)

---

If you'd like, I can:

* Update this README further to preserve or merge any local notes you want kept from an earlier README, or
* Create per-approach example unit tests or a small runner to exercise edge cases.

