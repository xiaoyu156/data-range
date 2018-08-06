package ac.iie.common.utils;

import net.lingala.zip4j.core.NativeStorage;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;


/**
 * @Description: 文件压缩工具类
 * @Author: wangxiaoyua
 * @CreateDate: 2018-8-2 15:24
 * @version: 1.0.0
 */
public class CompressUtil {
    /**
     * @Description: 解压缩
     * @param:
     * @return:
     * @date: 2018-8-3 10:49
     */
    public static void unZip(String sourceFile, String dest, String passwd) throws ZipException {
        ZipFile zFile = new ZipFile(sourceFile);
        zFile.setFileNameCharset("GBK");
        if (!zFile.isValidZipFile()) {
            throw new ZipException("压缩文件不合法,可能被损坏.");
        }
        File destDir = new File(dest);
        if (destDir.isDirectory() && !destDir.exists()) {
            destDir.mkdir();
        }
        if (zFile.isEncrypted()) {
            zFile.setPassword(passwd.toCharArray());
        }
        zFile.extractAll(dest);
    }

    public static void zip(String src, String dest, boolean is, String passwd) {
        File srcFile = new File(src);
        //创建目标文件
        String destName = buildDestFileName(srcFile, dest);
        ZipParameters par = new ZipParameters();
        par.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        par.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
        if (passwd != null) {
            par.setEncryptFiles(true);
            par.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
            par.setPassword(passwd.toCharArray());
        }
        try {
            ZipFile zipfile = new ZipFile(destName);
            if (srcFile.isDirectory()) {
                if (!is) {
                    File[] listFiles = srcFile.listFiles();
                    ArrayList<File> temp = new ArrayList<>();
                    Collections.addAll(temp, listFiles);
                    zipfile.addFiles(temp, par);
                }
                zipfile.addFolder(new NativeStorage(srcFile), par);
            } else {
                zipfile.addFile(srcFile, par);
            }
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 构建名称
     * @param:
     * @return:
     * @date: 2018-8-3 10:55
     */
    public static String buildDestFileName(File srcfile, String dest) {
        if (dest == null) {
            if (srcfile.isDirectory()) {
                dest = srcfile.getParent() + File.separator + srcfile.getName() + ".zip";
            } else {
                String filename = srcfile.getName().substring(0, srcfile.getName().lastIndexOf("."));
                dest = srcfile.getParent() + File.separator + filename + ".zip";
            }
        } else {
            //路径的创建
            createPath(dest);
            if (dest.endsWith(File.separator)) {
                String filename = "";
                if (srcfile.isDirectory()) {
                    filename = srcfile.getName();
                } else {
                    filename = srcfile.getName().substring(0, srcfile.getName().lastIndexOf("."));
                }
                dest += filename + ".zip";
            }
        }
        return dest;
    }

    /**
     * @Description: 创建路径
     * @param:
     * @return:
     * @date: 2018-8-3 10:55
     */
    private static void createPath(String dest) {
        File destDir = null;
        if (dest.endsWith(File.separator)) {
            //给出的是路径时
            destDir = new File(dest);
        } else {
            destDir = new File(dest.substring(0, dest.lastIndexOf(File.separator)));
        }
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
    }


    public static void main(String[] args) throws Exception {
        //CompressUtil.unZip("C:\\Users\\wangxiaoyu\\Desktop\\演示文稿1.zip", "J:\\", "111d1");
        CompressUtil.zip("J:\\log", "J:\\log.zip", true, "1111");
    }

}
