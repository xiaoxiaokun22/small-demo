package com.hgk.boot.test;

import com.hgk.boot.common.Project;
import com.hgk.boot.hashmap.MyHashMap;
import com.hgk.boot.interceptor.MyInterceptor;
import com.hgk.boot.zdemo14.Person;
import com.hgk.boot.zdemo14.PersonImpl;
import com.hgk.boot.zdemo14.cglib_dynamic_proxy.MyMethodInterceptor;
import com.hgk.boot.zdemo14.cglib_dynamic_proxy.PersonTwoImpl;
import com.hgk.boot.zdemo14.jdk_dynamic_proxy.MyInvocationHandler;
import com.hgk.boot.zdemo14.static_proxy.PersonProxy;
import lombok.Data;
import net.sf.cglib.core.DebuggingClassWriter;
import org.springframework.beans.factory.BeanFactory;

import javax.swing.text.Segment;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class CharTest {
    static char c;
    public static void main(String[] args){

//        test01();
//        test02();
//        test03();
//        test04();
//        test05();
//        test06();
//        test07();
//        test08();
//        test09();
//        test10();
//        test11();
//        test12();
//        test13();
//        test14();
//        test15();
//        test16();
//        test17();
//        test18();
//        test19();
        test20();
    }

    private static class A{
        String a;
        public A(String str){
            a = str;
        }
    }
    /**
     * ==和equals
     * 1.==
     *  基本类型比较值，其他类型比较引用地址
     * 2.equals：
     *  String,Integer等包装类，都重写了equals方法，比较的是属性的值
     *  e.equals(f)返回false,使用的Object的equals方法,比较的是对象的引用。
     * 3.Byte,Short,Integer,Long包装类默认创建了【-128，127】的缓存数据（常量池技术），超出则新建对象；Float,Double没有缓存数据。
     *  Character创建了【0，127】的缓存数据。
     *  因此==比较时，ka，kb的值如果为-129时，返回false，-128～127时返回true。
     */
    public static void test01(){
        String a = "abc";
        String b = "abc";
        String c = new String("abc");
        String d = new String("abc");
        A e = new A("hello");
        A f = new A("hello");
        System.out.println(a == b);
        System.out.println(c == d);
        System.out.println(a == c);
        System.out.println(a.equals(b));
        System.out.println(c.equals(d));
        System.out.println(a.equals(c));
        System.out.println(e.equals(f));
        System.out.println("========");
        Integer ka = -129;//自动装箱相当于调用 Integer.valueOf(12)
        Integer kb = -129;
        Integer kd = new Integer(-129);
        System.out.println(ka == kb);
        System.out.println(ka.equals(kb));
        System.out.println(ka == kd);
        Character ke = 'a'; //Character.valueOf('a');
        Character kf = 'a';
        System.out.println(ke == kf);
    }

    /**
     *
     */
    public static void test02(){
        Integer i = Integer.valueOf(3);
        int t = i.intValue();
        System.out.println(t);
    }

    /**
     * 参数传值
     * 1.一个方法不能修改一个基本数据类型的参数
     * 2.一个方法可以修改数组类型,Hashmap类型的参数
     */
    public  static void test03(){
        //案例2 传数组对象
        int[] arr = {1,2,3,4,5};
        System.out.println("change之前(int[] arr):" + arr[0]);
        change(arr);
        System.out.println("change之后:" + arr[0]);
        //案例3 传list集合
        HashMap<String,Object> map = new HashMap();
        map.put("name","guokun");
        map.put("age",18);
        System.out.println("change之前(HashMap<String,Object>):" + map.get("age"));
        change(map);
        System.out.println("change之前:" + map.get("age"));
    }
    public  static void change(int[] arrProp){
        arrProp[0] = 100;
    }
    public  static void change(HashMap mapProp){
        mapProp.put("age","25");
    }

    /**
     * String,StringBuilder,StringBuffer
     * 比较一下它们的字符串拼接执行效率:StringBuilder > StringBuffer > String
     * StringBuffer多线程操作大量字符时使用
     */
    public static void test04(){
        String str1 = "gk";
        StringBuffer str2 = new StringBuffer("gk");
        StringBuilder str3 = new StringBuilder("gk");
        Object obj = new Object();

        long bTime = System.currentTimeMillis();
        for(int i = 0;i<10000;i++){
            str1 = str1 + i;
        }
        long eTime = System.currentTimeMillis();
        System.out.println("String的加号拼接：" + (eTime - bTime));

        bTime = System.currentTimeMillis();
        for(int i = 0;i<10000;i++){
            str2 = str2.append(i);
        }
        eTime = System.currentTimeMillis();
        System.out.println("StringBuffer的append：" + (eTime - bTime));

        bTime = System.currentTimeMillis();
        for(int i = 0;i<10000;i++){
            str3 = str3.append(i);
        }
        eTime = System.currentTimeMillis();
        System.out.println("StringBuilder的append：" + (eTime - bTime));
    }

    /**
     * Object常见方法使用
     */
    public static void test05(){
        String a = "name";

        HashMap<String,String> map = new HashMap<>();
        map.put("name","hgk");
        map.put("age","25");
        System.out.println(a.getClass());//class java.lang.String
        System.out.println(a.hashCode());
        //clone
        System.out.println(map == map.clone());//false
        System.out.println(map.getClass());
        System.out.println(map.clone().getClass());
        System.out.println(map.getClass() == map.clone().getClass());//true
        //toString
        System.out.println(map.toString());
        //wait,notify,notifyAll

    }

    /**
     * byte,byte[],Byte
     */
    public static void test06(){
        byte bt = 127;//-128 ~ 127
        byte bt2 = 'a';
        byte[] arr = new byte[3];
        arr[0] = 'a';
        arr[1] = 'b';
        arr[2] = 'c';
        Byte b = new Byte("127");
        System.out.println(bt);
        System.out.println(bt2);
        System.out.println(arr);
        System.out.println(b);
    }

    /**
     * 键盘输入
     * 1.Scanner
     * 2.BufferedReader
     */
    public static void test07(){
        System.out.println("input(Scanner):");
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        System.out.println("print:" + s);
//        input.close();

        System.out.println("input(BufferedReader):");
        BufferedReader input2 = new BufferedReader(new InputStreamReader(System.in));
        try {
            String b = input2.readLine();
            System.out.println("print:" + b);
            input2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 单/双精度浮点数，BigDecimal使用
     */
    public static void test08(){
        float a = 1.0f;
        float b = 0.9f;
        float c = 0.8f;
        float a_b = a-b;
        float b_c = b-c;
        System.out.println(a_b);//0.100000024
        System.out.println(b_c);//0.099999964
        System.out.println(a_b == b_c);//false

        double da = 1.0d;
        double db = 0.9d;
        double dc = 0.8d;
        double da_db = da-db;
        double db_dc = db-dc;
        System.out.println(da_db);//0.09999999999999998
        System.out.println(db_dc);//0.09999999999999998
        System.out.println(da_db == db_dc);//true

        BigDecimal ba = new BigDecimal("1.0");
        BigDecimal bb = new BigDecimal("0.9");
        BigDecimal bc = new BigDecimal("0.8");
        BigDecimal ba_bb = ba.subtract(bb).setScale(2,BigDecimal.ROUND_HALF_UP);
        BigDecimal bb_bc = bb.subtract(bc);
        System.out.println(ba_bb);
        System.out.println(bb_bc);
        System.out.println(ba_bb.compareTo(bb_bc));//true

    }

    /**
     * stream常用方法
     */
    public static void test09(){
        //===初始化三种类型数据===
        // 1>
        Map<String,Object> map = new HashMap<String,Object>(){{
            put("q_name","name");
            put("q_age",26);
            put("q_address","address");
            put("files",Arrays.asList("21","22"));
        }};
        // 2>
        List<Map<String,Object>> maplist = new ArrayList<>();
        for(int i=0;i < 5;i++){
            int finalI = i;
            Map<String,Object> row = new HashMap<String,Object>(){{
                put("q_name","name" + finalI);
                put("q_age",25 + finalI);
                put("q_address","address");
                put("files",Arrays.asList("21","22"));
            }};
            maplist.add(row);
        }
        maplist.add(maplist.get(0));
        // 3>
        List<String> list = Arrays.asList("hgk","qxl","qf","qxl");
        //===对数据处理===
        //list去重
        System.out.println("list去重:\n"+ list.stream().distinct().collect(Collectors.toList()));
        System.out.println(maplist.stream().distinct().collect(Collectors.toList()));
        //maplist取出age>28
        List<Map<String,Object>> maplist2  = maplist.stream().filter(i->Integer.parseInt(i.get("q_age").toString()) >= 28).collect(Collectors.toList());
        System.out.println("maplist取出age>28:\n" + maplist2);
        //maplist取出age>28的集合，并且只取q_name字段
        List<String> maplist3  = maplist.stream().filter(i->Integer.parseInt(i.get("q_age").toString()) >= 28).map(m->m.get("q_name").toString()).collect(Collectors.toList());
        System.out.println("maplist取出age>28的集合，并且只取q_name字段:\n" + maplist3);
        //取map里面值放到list
        List<Object> list2 = map.entrySet().stream().map(i->i.getValue()).collect(Collectors.toList());
        System.out.println("取map里面值放到list：" + list2);
        //map的key统一加后缀
        Map<String,Object> map2 = map.entrySet().stream().collect(Collectors.toMap(row->row.getKey() + "_mf",row->row.getValue()));
        System.out.println("map的key统一加后缀：" + map2);

    }

    /**
     * 数组转list的几种方式
     * asList坑：
     * 1.Arrays.asList("12","13").add("20") add方法会报错不支持
     * 2。int[] myArray = {1, 2, 3}; Arrays.asList(myArray) myArray.get(1)会报错
     */
    public static void test10(){
        String[] strArr = {"12","14","31"};
        List<String> list = Arrays.asList(strArr);
//        list.add("32");//UnsupportedOperationException
//        System.out.println("asList未实现集合的add等方法，不支持异常：" + list);
//        list = new ArrayList<>(list);
//        list.add("32");//
//        System.out.println("正常：" +  list);

//        int[] arr = {2,3,4};
//        List list2 = Arrays.asList(arr);
//        System.out.println("基本类型的数组不能用asList()，索引溢出异常：" + list2.get(1));//ArrayIndexOutOfBoundsException

        List<String> list3 = Arrays.stream(strArr).collect(Collectors.toList());
        System.out.println(list3);
        String[] strArr1 = list3.toArray(new String[0]);
        System.out.println(strArr1[2]);
    }

    /**
     * 集合不要在foreach循环remove/add操作，以及解决方法
     */
    public static void test11(){

        List<String> list = new ArrayList<>(Arrays.asList("10","11","12"));
        //1.迭代器
        Iterator<String> iterable = list.iterator();
        while (iterable.hasNext()){
            String str = iterable.next();
            if(str.equals("11")){
                iterable.remove();
            }
        }
        System.out.println("迭代器移除元素11：" + list);
        //2.removeIf()
        list.removeIf(f->{
            return f.toString().equals("12");
        });
        System.out.println("list.removeIf(f->{})移除元素12：" + list);

    }

    /**
     * static关键字的应用场景
     */
    public static void test12(){
        StaticTest st = new StaticTest(25,"haoguokun");
//        StaticTest st2 = new StaticTest(26,"haoguokun");

//        st.getName();

//        StaticTest.setScope("18-26");
//        System.out.println(StaticTest.scope);
    }

    @Data
    public static class UserFanshe{
        private String name;
        public String desc;
        private Integer height;

        public UserFanshe(){
        }
        public UserFanshe(Integer height){
            this.height = height;
        }
        public UserFanshe(String name,Integer height){
            this.name = name;
            this.height = height;
        }
        public void setNameAndDesc(String name, String desc){
            this.name = name;
            this.desc = desc;
        }

        private static void setDescTest(String desc){
        }

    }
    /**
     *  反射的应用
     */
    public static void test13(){
        try {
            UserFanshe user = new UserFanshe(176);
            //调用指定方法
            Method method = user.getClass().getMethod("setNameAndDesc",String.class,String.class);
            method.invoke(user,"haoguokun","基本信息描述");
            System.out.println(user.getName());
            //获取所有方法
            Method[] methods = UserFanshe.class.getDeclaredMethods();
            System.out.println("===getDeclaredMethods获取class所有方法(不会获取继承的)===");
            for (Method met: methods){
                System.out.println(met.getName());
            }
//            Arrays.stream(methods).forEach(r->{System.out.println(r.getName());});
            //设置字段值
            System.out.println("===getDeclaredFields获取class所有字段(不会获取继承的)===");
            Field[] fields = user.getClass().getDeclaredFields();
            for (Field field: fields){
                System.out.println(field.getName());
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    /**
     * 参考 https://github.com/Snailclimb/JavaGuide/blob/master/docs/java/basis/%E4%BB%A3%E7%90%86%E6%A8%A1%E5%BC%8F%E8%AF%A6%E8%A7%A3.md
     * 静态/动态代理 zdemo14
     */
    public static void test14() {
        PersonImpl person = new PersonImpl();
        //静态代理
        PersonProxy personProxy = new PersonProxy(person);
        personProxy.saylove("静态代理实现，i love you");
        //jdk动态代理 代理对象必须为Person接口，实体类PersonImpl不行
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        Person jdkProxy = (Person) MyInvocationHandler.getProxy(person);
        jdkProxy.saylove("jdk动态代理实现，i love you");
        //cglib动态代理
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/hanxiaofeng/web/code/boot-study/cglib/");
        PersonTwoImpl cglibProxy = (PersonTwoImpl) new MyMethodInterceptor().getProxy(PersonTwoImpl.class);
        cglibProxy.saylove("cglib动态代理实现，i love you");

    }

    /**
     * 集合扩容断点调试，集合排序
     */
    public static void test15(){
        //查看ArrayList扩容原理
//        List<String> list = new ArrayList<>();
//        StringBuffer sb = new StringBuffer("element ");
//        for(int i=0;i<21;i++){
//            if(i>=10){
//                System.out.println("添加第十一个元素");
//            }
//            list.add(sb.append(i).toString());
//        }
//        System.out.println(list);
        //ArrayList
        //1. 创建Comparator匿名类，重写Comparator的compare实现定制排序
        ArrayList<Integer> integerList = new ArrayList<>();
        integerList.add(23);
        integerList.add(-3);
        integerList.add(12);
        integerList.add(18);
        integerList.add(-9);
        System.out.println("根据索引随机获取元素（高效）：" + integerList.get(1));
        System.out.println("原始arraylist：" + integerList);
        Collections.reverse(integerList);
        System.out.println("反转arraylist：" + integerList);
        Collections.sort(integerList);
        System.out.println("sort排序（默认是升序的，降序要定制）：" + integerList);
        Collections.sort(integerList,new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println("sort定制排序（降序）" + integerList);
        //2.排序的对象要实现comparable接口,重写comparaTo()方法
        ArrayList<Project> projects = new ArrayList<>();
        projects.add(new Project(5,"京东项目",202005));
        projects.add(new Project(20,"美团项目",209905));
        projects.add(new Project(126,"饿了吗项目",209905));
        projects.add(new Project(11,"抖音项目",209905));
        projects.add(new Project(83,"直播项目",199505));
        System.out.println("对Project对象排序前：" + projects);
        Collections.sort(projects);
        System.out.println("对Project对象排序后：" + projects);
        //LinkedList
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("linedlist 001");
        linkedList.add("linedlist 002");
        linkedList.add("linedlist 003");
        System.out.println("根据索引随机获取元素(非高效)：" + linkedList.get(1));
    }
    /**
     * set类使用
     * set类没有sets.get(1)方法
     */
    public static void test16(){
        //HashSet 无序唯一
        Set<String> sets = new HashSet<>();
        for(int i=0;i<5;i++){
            StringBuffer sb = new StringBuffer("hashset 00");
            sets.add(sb.append(i).toString());
        }
        sets.add("hashset 002");
        System.out.println("hashset原始数据:\n" + sets);
        sets.removeIf(f -> { return f.equals("hashset 003");});
        System.out.println("删除元素 'hashset 003':\n" + sets);
        System.out.println("查询元素是否存在:\n" + sets.contains("hashset 002"));
        //TreeSet 有序唯一
        Set<String> treeSets = new TreeSet<>();
        for(int i=0;i<5;i++){
            StringBuffer sb = new StringBuffer("treeset 00");
            treeSets.add(sb.append(i).toString());
        }
        treeSets.add("treeset 002");
        System.out.println("treeset原始数据:\n" + treeSets);
        treeSets.remove("treeset 003");
        System.out.println("删除元素 'treeset 003':\n" + treeSets);
        System.out.println("查询元素是否存在:\n" + treeSets.contains("treeset 002"));

    }
    /**
     * 1.测试 map 键值为空问题
     * HashMap 键值都可以为null
     * concurrentHashMap,HashTable 键值都不可以为null
     * TreeMap 值可以为null
     */
    public static void test17(){
        HashMap<String,Object> map = new HashMap<>();
        map.put(null,null);
        map.put(null,"2");
        map.put("2",null);
        System.out.println(map);

        Hashtable<String,Object> hashtable = new Hashtable<>();
        hashtable.put(null,null);//抛出null异常
        hashtable.put(null,"2");//抛出null异常
        hashtable.put("2",null);//抛出null异常
        System.out.println(hashtable);

        TreeMap<String,Object> treeMap = new TreeMap<>();
        treeMap.put(null,null);//抛出null异常
        treeMap.put(null,"2");////抛出null异常
        treeMap.put("2",null);
        System.out.println(treeMap);

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put(null,null);//抛出null异常
        concurrentHashMap.put(null,2);//抛出null异常
        concurrentHashMap.put("2",null);//抛出null异常
        System.out.println(concurrentHashMap);

    }

    /**
     * 探究hashmap原理，手动实现
     */
    public static void test18(){
        HashMap<String,Object> hashMap = new HashMap<>();
        for(int i=1;i<22;i++){
            if(i == 11){
                System.out.println("插入第十六个元素");
            }
            hashMap.put("name" + i,"wp" + i);
        }
//        MyHashMap<String,Object> myHashMap = new MyHashMap<>();
//        myHashMap.put("name","haoguokun");
        //treemap排序
        TreeMap<String,Object> treeMapList = new TreeMap<>();
        treeMapList.put("1","wp");
        treeMapList.put("13","hgk");
        treeMapList.put("6","xx");
        treeMapList.put("99","ba");
        treeMapList.put("51","fz");
        System.out.println(treeMapList);
    }

    /**
     * 探究hashmap7种循环方式的执行效率
     * 1.使用迭代器（Iterator）EntrySet 的方式进行遍历；
     * 2.使用迭代器（Iterator）KeySet 的方式进行遍历；
     * 3.使用 For Each EntrySet 的方式进行遍历；
     * 4.使用 For Each KeySet 的方式进行遍历；
     * 5.使用 Lambda 表达式的方式进行遍历；
     * 6.使用 Streams API 单线程的方式进行遍历；
     * 7.使用 Streams API 多线程的方式进行遍历。
     */
    public static void test19(){
        HashMap<String,Object> map = new HashMap<>();
        for(int i=1;i<=500;i++){
            map.put("name" + i,"wp" + i);
        }
        long startTime,endTime = 0;
        //1.使用迭代器（Iterator）EntrySet 的方式进行遍历
        startTime = System.currentTimeMillis();
        Iterator<Map.Entry<String,Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,Object> entry = iterator.next();
//            System.out.println("key:" + entry.getKey() + ",value:" +entry.getValue());
        }
        endTime = System.currentTimeMillis();
        System.out.println( "使用迭代器（Iterator）EntrySet 的方式进行遍历:" + (endTime - startTime));
        //2.使用迭代器（Iterator）KeySet 的方式进行遍历
        startTime = System.currentTimeMillis();
        Iterator<String> iterator1 = map.keySet().iterator();
        while (iterator1.hasNext()){
            String key = iterator1.next();
//            System.out.println("key:" + iterator1.next());
        }
        endTime = System.currentTimeMillis();
        System.out.println("使用迭代器（Iterator）KeySet 的方式进行遍历:" + (endTime - startTime));
        //3.使用 For Each EntrySet 的方式进行遍历
    }

    /**
     * 输入一个字符串
     * 实现字符串反转并输出
     */
    public static void test20(){
         Scanner sc = new Scanner(System.in);
         System.out.println("请输入一个字符串：");
         String str = sc.nextLine();
         //1
         String res = "";
         for(int i = str.length()-1;i >=0;i--){
             char c = str.charAt(i);
             res += c;
         }
         System.out.println("字符串反转结果输出：" + res);
         //2
        String res2 = "";
         char[] arr = str.toCharArray();
         for(int i = arr.length-1;i >= 0;i--){
             res2 += arr[i];
         }
        System.out.println("字符串反转结果输出：" + res2);
    }

}
