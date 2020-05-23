Comparable接口
我们在字符串中见到过CompareTo方法,知道这个方法是用于比较字符串顺序的,根据字典顺序进行排序。
Java中很多类也都有CompareTo方法，甚至于排序算法的底层组成也是依赖于比较的，而这个比较就是依赖于各种数据类型的CompareTo或者Compare方法。
Java中所有的compareTo方法都源于一个共同的接口，那就是Comparable。
这个接口只有一个方法，那就是CompareTo。
所有想要具有比较功能的类，都建议实现这个接口，而非是自己定义这个功能，这是面向对象的概念（将具有相同功能的事物抽象到一个共同的类或接口），并且为了多态也建议通过实现接口来进行向上转型，通过接口来操作具体实现，这也是面向接口编程要求我们做的。
下面我们来具体了解一下Comparable接口。

回到顶部
1. 接口概述
查看API我们发现对Comparable定义如下：

public interface Comparable<T>
此接口强行对实现它的每个类的对象进行整体排序。这种排序被称为类的自然排序，类的 compareTo 方法被称为它的自然比较方法。实现此接口的对象列表（和数组）可以通过 Collections.sort（和 Arrays.sort）进行自动排序。实现此接口的对象可以用作有序映射中的键或有序集合中的元素，无需指定比较器。

对于类 C(Collections) 的每一个 e1 (Element)和 e2 来说，当且仅当 e1.compareTo(e2) == 0 与 e1.equals(e2) 具有相同的 boolean 值时，类 C 的自然排序才叫做与 equals 一致。注意，null 不是任何类的实例，即使 e.equals(null) 返回 false，e.compareTo(null) 也将抛出 NullPointerException。【这里也给了我们一种比较两个数据内容的方法compareTo,如果返回值为0说明比较的内容相同,在下一节详细方法介绍中我们再探讨!】。建议（虽然不是必需的）最好使自然排序与 equals 一致。这是因为在使用自然排序与 equals 不一致的元素（或键）时，没有显式比较器的有序集合（和有序映射表）行为表现“怪异”。尤其是，这样的有序集合（或有序映射表）违背了根据 equals 方法定义的集合（或映射表）的常规协定。 例如，如果将两个键 a 和 b 添加到没有使用显式比较器的有序集合中，使 (!a.equals(b) && a.compareTo(b) == 0)，那么第二个 add 操作将返回 false（有序集合的大小没有增加），因为从有序集合的角度来看，a 和 b 是相等的。

实际上，所有实现 Comparable 的 Java 核心类都具有与 equals 一致的自然排序。java.math.BigDecimal 是个例外，它的自然排序将值相等但精确度不同的 BigDecimal 对象（比如 4.0 和 4.00）视为相等。 从数学上讲，定义给定类 C 上自然排序的关系式 如下：

      {(x, y)|x.compareTo(y) <= 0}。
 整体排序的商是：

      {(x, y)|x.compareTo(y) == 0}。
它直接遵循 compareTo 的协定，商是 C 的等价关系，自然排序是 C 的整体排序。当说到类的自然排序与 equals 一致 时，是指自然排序的商是由类的 equals(Object) 方法定义的等价关系。

    {(x, y)|x.equals(y)}。

此接口是 Java Collections Framework 的成员。

回到顶部
2. 接口方法详读
接口和类一样都是对共有功能的抽象，不同的是类中的功能是具体的实现，而接口的则是对一个功能的抽象。下面我们了解一下Comparable接口的抽象方法，该接口只提供了一个方法，接口方法如下：

int

compareTo(T o) 比较此对象与指定对象的顺序。O为要比较的对象

如上所诉，该方法用于比较此对象与指定对象的顺序。如果该对象小于、等于或大于指定对象，则分别返回负整数、零或正整数。 根据不同类的实现返回不同，大部分返回1,0和-1三个数，如下测试：

@Test

public void test1(){

Integer i1 = 3;

Integer i2 = 7;

System.out.println(i2.compareTo(i1));//1

}

实现类必须确保对于所有的 x 和 y 都存在 sgn(x.compareTo(y)) == -sgn(y.compareTo(x)) 的关系。（这意味着如果 y.compareTo(x) 抛出一个异常，则 x.compareTo(y) 也要抛出一个异常。）
实现类还必须确保关系是可传递的：(x.compareTo(y)>0 && y.compareTo(z)>0) 意味着 x.compareTo(z)>0。
最后，实现者必须确保 x.compareTo(y)==0 意味着对于所有的 z，都存在 sgn(x.compareTo(z)) == sgn(y.compareTo(z))。 强烈推荐 (x.compareTo(y)==0) == (x.equals(y)) 这种做法，但并不是 严格要求这样做。一般来说，任何实现 Comparable 接口和违背此条件的类都应该清楚地指出这一事实。推荐如此阐述：“注意：此类具有与 equals 不一致的自然排序。”
　　在前面的描述中，符号 sgn(expression) 指定 signum 数学函数，该函数根据 expression 的值是负数、零还是正数，分别返回 -1、0 或 1 中的一个值。

　　注意会抛出的异常:ClassCastException - 如果指定对象的类型不允许它与此对象进行比较。

回到顶部
3. 接口方法的实践操作
　　编程最好的学习莫过于大量的代码,通过代码发现问题,下面我们就自己来实践一下Comparable的方法.

3.1  String和Integer对于compareTo()的实现
　　String和Integer这两个类都实现了Comparable接口,都对compareTo方法进行了实现,下面我们通过源码来看一下它们各自对于该方法的具体实现:

复制代码
private final char value[];//String的底层是字符数组  a.compareTo(b)
    public int compareTo(String anotherString) {
        int len1 = value.length;//获取调用该方法的字符串的长度a
        int len2 = anotherString.value.length;//获取比较字符串的长度b
        int lim = Math.min(len1, len2);//(a <= b) ? a : b;  min底层代码  这句代码是为了获取较短的字符串的长度
        char v1[] = value;  //创建两个字符数组,分别指向这两个字符串的所在
        char v2[] = anotherString.value;
        //循环比较,循环次数,是较短的字符串的长度,如果用较长的字符串的长度,那么会出现nullPointException
        int k = 0;
        while (k < lim) {
            char c1 = v1[k];
            char c2 = v2[k];
            //比较相对应索引的元素,如果元素不同则比较返回中间差距的顺序,如果相等,那么就继续循环比较
            if (c1 != c2) {
                return c1 - c2;//字符对应的Unicode码表中的数字,这也就是为什么说String是按照字典书序比较的,如a比b靠前,那么a对应的数字比b小,相减返回负数,差多少顺序,就返回多少
            }
            k++;
        }
        //如果两个字符串的长度不同,其它都相同,那么返回的就是长度的差距了
        return len1 - len2;
    }
复制代码
我们可以看到String对于compareTo的实现就是依据Unicode码表中字符对应的数字来判断的,返回的是字符串长度差或者是字符间在码表上的差距,依据具体情况,返回也不同.下面我们看看Integer的compareTo();

复制代码
//Integer的compareTo方法,底层依据的是compare方法,这个方法是Comparator接口的一个方法
    public int compareTo(Integer anotherInteger) {
        //实际上Integer的比较是通过Integer中包括的整数来比较的
        return compare(this.value, anotherInteger.value);
    }
    public static int compare(int x, int y) {//a.compateTo(b)
        //如果a比b小,那么返回-1,相等就是0,否则就是1
        return (x < y) ? -1 : ((x == y) ? 0 : 1);
    }
复制代码
有了对String和Integer的compareTo的实现的了解,下面我们试着自己来实现一个类的compareTo方法;

复制代码
package cn.comparable.introduce;
public class ComparableLength implements Comparable<ComparableLength>{
    @Override
    public int compareTo(ComparableLength o) {
        int len1 = Integer.toString(this.hashCode(), 16).length();
        int len2 = Integer.toString(o.hashCode(), 16).length();
        return (len1 - len2 > 0) ? 1 : -1 ;
    }
    public String sort(ComparableLength cl){
        if(this.compareTo(cl) == 1){
            return "该对象比后者hashcode长度长";
        }else {
            return "该对象比后者hashcode长度短,也可能相等";
        }
    }
}
复制代码
复制代码
@Test
    public void test3(){
        ComparableLength c1 = new ComparableLength();
        ComparableLength c2 = new ComparableLength();
        ComparableLength c3 = new ComparableLength();
        ComparableLength c4 = c1;
        System.out.println(c1.sort(c2) + c1.hashCode());
        System.out.println(c2.sort(c1));
        System.out.println(c2.sort(c3) + c2.hashCode());
        System.out.println(c1.sort(c3) + c3.hashCode());
        System.out.println(c4.sort(c3) + c4.hashCode());
    }
复制代码


如上我们自己也能写一个比较方法了,但是这个方法只简单比较了一下对象的hashcode的长度，下面我们比较一下hashcode是否相等，和equals()返回同样结果,这也是建议我们做的;

复制代码
package cn.comparable.introduce;
import org.junit.runner.manipulation.Sortable;
public class ComparableLength implements Comparable<ComparableLength>{
    public String sort(ComparableLength c2){
        if(this.compareTo(c2) == 1)
            return "这两个对象不是同一个对象";
        else 
            return "这两个对象是同一个对象";
    }
    @Override
    public int compareTo(ComparableLength o) {
        String str1 = Integer.toString(this.hashCode());
        String str2 = Integer.toString(o.hashCode());
        int len1 = str1.length();
        int len2 = str2.length();
        char[] ch1 = str1.toCharArray();
        char[] ch2 = str2.toCharArray();
        int lim = len1 - len2 ;
        if(lim == 0){
            int k = 0;
            while (k < len1) {
                char char1 = ch1[k];
                char char2 = ch2[k];
                if(char1 != char2)
                    return 1;
                k++;
            }
        }else if(lim != 0)
            return 1;
        return 0;
    }
}
复制代码
复制代码
@Test
    public void test4(){
        ComparableLength c1 = new ComparableLength();
        ComparableLength c2 = new ComparableLength();
        ComparableLength c3 = new ComparableLength();
        ComparableLength c4 = c1;
        System.out.println(c1.sort(c2) + "--"+c1.equals(c2)+"--"+c1.hashCode());
        System.out.println(c2.sort(c1) + "--"+c2.equals(c1));
        System.out.println(c2.sort(c3) + "--"+c2.equals(c3)+"--"+c2.hashCode());
        System.out.println(c1.sort(c3) + "--"+c1.equals(c3)+"--"+ c3.hashCode());
        System.out.println(c4.sort(c1) + "--"+c4.equals(c1)+"--"+ c4.hashCode());
    }
复制代码


我们发现可以实现该功能;

Java中除了提供一个比较方法的接口外,还提供了一个比较器的接口Comparator,下节我们来读一下Comparator。