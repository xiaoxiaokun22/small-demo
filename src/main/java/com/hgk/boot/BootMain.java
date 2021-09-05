package com.hgk.boot;

import com.hgk.boot.config.AppConfig;
import com.hgk.boot.config.Brand;
import com.hgk.boot.entity.User;
import com.hgk.boot.interceptor.MyInterceptor;
import com.hgk.boot.proxy.ProxyBean;
import com.hgk.boot.service.HelloService;
import com.hgk.boot.service.impl.HelloServiceImpl;
import org.apache.tomcat.util.codec.binary.Base64;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import sun.misc.BASE64Decoder;

import javax.servlet.Servlet;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

//@SpringBootApplication
//@MapperScan("com.hgk.boot.mapper")
public class BootMain{

    private static Logger log = LoggerFactory.getLogger(BootMain.class);

    private static final String ENCODING = "UTF-8";
    private static final String SIGNATURE_ALGORITHM = "SHA1WithRSA";
    private static final String KEY_ALGORITHM = "RSA";

//    static class Father{
//        public static int num = 1;
//        static {
//            num = 2;
//        }
//    }
//    static class Son extends Father{
//        private static int m = num;
//    }

    public static void main(String[] args) throws Exception {
        //ioc容器测试
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        Brand brand = context.getBean(Brand.class);
        Brand brand = (Brand) context.getBean("Brand");
        log.info(brand.getDesc());

        //约定编程测试
//        HelloService helloService = new HelloServiceImpl();
//        HelloService proxy = (HelloService) ProxyBean.getProxyBean(helloService,new MyInterceptor());
//        proxy.sayHello("hello xiaoming");

//        BootMain bm = new BootMain();
//        int num = 10;
//        bm.test1();
    }

    public void test1(){
        Date date = new Date();
        String name1 = "hgk";
        String info = test2(date,name1);
        System.out.println(date + info);
    }

    public String test2(Date date, String name){
        double num = 3.14;
        char c = '男';
        date = null;
        name = "wp";
        return date + name;
    }

}
