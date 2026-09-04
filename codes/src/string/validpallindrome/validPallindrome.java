package string.validpallindrome;

public class validPallindrome {
    public static boolean bruteForce(String s) {
        String cleaned = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        String reversed = new StringBuilder(cleaned).reverse().toString();

        return cleaned.equals(reversed);
    }

    public static boolean optimalApproach(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {

            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }

            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            if (Character.toLowerCase(s.charAt(left))
                    != Character.toLowerCase(s.charAt(right))) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    public static boolean streamApproach(String s) {

        String cleaned = s.chars()
                .filter(Character::isLetterOrDigit)
                .mapToObj(c -> String.valueOf((char) c))
                .collect(java.util.stream.Collectors.joining())
                .toLowerCase();

        return cleaned.equals(
                new StringBuilder(cleaned).reverse().toString());
    }

    static void main() {
        String s = "A man, a plan, a canal: Panama";

        System.out.println(bruteForce(s));
        System.out.println(optimalApproach(s));
        System.out.println(streamApproach(s));

    }
}
