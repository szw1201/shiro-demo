import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Created by lenovo on 2018/5/29.
 */
public class Md5 {

    public static void main(String[] args) {
        String password = "123";
        //md5直接加密
        Md5Hash md5Hash = new Md5Hash(password);
        System.out.println(md5Hash);
        //fae0b27c451c728867a567e8c1bb4e53:md5一次加密

        //MD5+盐值：加密
        String name = "haha";
        Md5Hash md5 = new Md5Hash(password,name);
        System.out.println(md5);
        //b630a2dd1ec4718d8f915f8c5477574a :zs
        //eabe51b927419df61f1ea47105eebd56 :ls

        //MD5+盐值：加密
        Md5Hash md5hashIterations = new Md5Hash(password,name ,1000 );
        System.out.println(md5hashIterations);
        //fba0e6ecc630294326ca8ed34e7f042d :zs + 666 +1000次
        //fd00b461859cd20bcb4b28263f3c5f98 : ls + 666 + 1000
    }

}
