package lang;

import java.util.Arrays;

/**
 * Created by wyg on 2020/5/23.
 */
public class Test {

  public static void main(String[] args) {
    String[] strArr = {"A", "B", "C", "D"};
    Arrays.sort(strArr);
    for (String string : strArr) {
      System.out.println(string+";");
    }
  }

}
