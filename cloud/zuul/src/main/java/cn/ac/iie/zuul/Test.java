package cn.ac.iie.zuul;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName Test
 * @Author tjh
 * @Date 18/8/24 下午3:27
 * @Version 1.0
 **/
public class Test {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/apple/Documents/a.txt");

        if(!file.exists()) {
            file.createNewFile();
        }
    }
}
