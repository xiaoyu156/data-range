package ac.iie.common.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @Description: 文件操作工具类
 * @Author: wangxiaoyua
 * @CreateDate: 2018-8-6 15:01
 * @version: 1.0.0
 */
public class FileUtil {

    // 创建目录
    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        // 判断目录是否存在
        if (dir.exists()) {
            System.out.println("==========创建目录失败，目标目录已存在！");
            return false;
        }
        // 结尾是否以"/"结束
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        // 创建目标目录
        if (dir.mkdirs()) {
            System.out.println("==========创建目录成功！" + destDirName);
            return true;
        } else {
            System.out.println("创建目录失败！");
            return false;
        }
    }

    // 创建单个文件
    public static boolean createFile(String filePath) {
        File file = new File(filePath);
        // 判断文件是否存在
        if (file.exists()) {
            System.out.println("==========目标文件已存在" + filePath);
            return false;
        }
        // 判断文件是否为目录
        if (filePath.endsWith(File.separator)) {
            System.out.println("==========目标文件不能为目录！");
            return false;
        }
        // 判断目标文件所在的目录是否存在
        if (!file.getParentFile().exists()) {
            // 如果目标文件所在的文件夹不存在，则创建父文件夹
            System.out.println("目标文件所在目录不存在，准备创建它！");
            // 判断创建目录是否成功
            if (!file.getParentFile().mkdirs()) {
                System.out.println("创建目标文件所在的目录失败！");
                return false;
            }
        }
        try {
            // 创建目标文件
            if (file.createNewFile()) {
                System.out.println("==========创建文件成功:" + filePath);
                return true;
            } else {
                System.out.println("==========创建文件失败！");
                return false;
            }
        } catch (IOException e) {// 捕获异常
            e.printStackTrace();
            System.out.println("==========创建文件失败！" + e.getMessage());
            return false;
        }
    }

    public static boolean deleteFile(String filePath) {// 删除单个文件
        boolean flag = false;
        File file = new File(filePath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();// 文件删除
            flag = true;
        }
        return flag;
    }

    // 使用管道复制
    @SuppressWarnings({"resource", "unused", "null"})
    public static boolean copyFileUsingFileChannels(String source, String dest) throws IOException {
        boolean flag = false;
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            FileUtil.createFile(source);
            FileUtil.createFile(dest);
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
            flag = true;
        } finally {
            if (inputChannel != null) {
                inputChannel.close();
            }
            if (inputChannel != null) {
                outputChannel.close();
            }
        }
        return flag;
    }

    public static Boolean WriteStringToFile(String filePath, String content) {
        try {
            FileWriter fw = new FileWriter(filePath, false);
            BufferedWriter bw = new BufferedWriter(fw);
            // 往已有的文件上添加字符串
            bw.write(content);
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            String source = "F:\\mnt\\di-disk1\\tyfiles\\ycl_tyfile\\tp\\20170714\\54a14271da71458d8105a262c0683741.jpeg";
            String dest = "C:\\KDR\\565\\3\\334\\54a14271da71458d8105a262c0683741.jpeg";
            copyFileUsingFileChannels(source, dest);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
