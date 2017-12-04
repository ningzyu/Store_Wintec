package com.nzy.zkyt.store_wintec.util;

import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipException;

//import java.util.zip.GZIPInputStream;
//import java.util.zip.GZIPOutputStream;
//import java.util.zip.ZipInputStream;
//import java.util.zip.ZipOutputStream;

/**
 * Created by NZY on 2017/6/26.
 */

public class ZipUtil {
    //酷迪提供
    public static String Compres(String str) {
        byte[] input = null;

        try {
            input = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        Deflater compressor = new Deflater();
        compressor.setInput(input);
        compressor.finish();
        ByteArrayOutputStream bos = new ByteArrayOutputStream(input.length);
        byte[] buf = new byte[1024];
        while (!compressor.finished()) {
            int count = compressor.deflate(buf);
            bos.write(buf, 0, count);
        }
        try {
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String s = "";
        try {
            s = new String(Base64.encode(bos.toByteArray(), Base64.DEFAULT), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }


    /**
     * 解压缩
     *
     * @param
     * @return
     */

    public static String decompressBytes(String value) {
        ByteArrayInputStream bis = new ByteArrayInputStream(Base64.decode(value, Base64.DEFAULT));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        // 相对应的解压缩的参数也要设置未true
        Inflater inf = new Inflater();
        InflaterInputStream iis = new InflaterInputStream(bis, inf);
        int readCount = 0;
        byte[] buf = new byte[1024];
        try {
            while ((readCount = iis.read(buf, 0, buf.length)) > 0) {
                bos.write(buf, 0, readCount);
            }
            iis.close();
            return new String(bos.toByteArray(), "utf-8");
        } catch (ZipException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}










//
//
//
//    //    已byte[]方式处理解压的数据，返回byte[]。
//    public static byte[] getZipData1(byte[] sourcesByte) {
//        // Decompress the bytes // 开始解压,
//        // 数组长度不够将导致丢失部分数据
//        int dataLength = 1024 * 1024; // 对byte[]进行解压，同时可以要解压的数据包中的某一段数据，就好像从zip中解压出某一个文件一样。
//        byte[] result = new byte[dataLength];
//        try {
//            Inflater decompresser = new Inflater();
//            decompresser.setInput(sourcesByte, 0, sourcesByte.length);
//            int resultLength = decompresser.inflate(result); // 返回的是解压后的的数据包大小，
//            decompresser.end();
//        } catch (Exception ex) {
//        }
//        return result;
//    }
//
//
//    // 压缩
//    public static String compress(String str) throws IOException {
//        if (str == null || str.length() == 0) {
//            return str;
//        }
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        GZIPOutputStream gzip = new GZIPOutputStream(out);
//        gzip.write(str.getBytes());
//        gzip.close();
//        return out.toString("ISO-8859-1");
//        //"ISO-8859-1"
//    }
//
//    // 解压缩
//    public static String uncompress(String str) throws IOException {
//        if (str == null || str.length() == 0) {
//            return str;
//        }
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
//        GZIPInputStream gunzip = new GZIPInputStream(in);
//        byte[] buffer = new byte[256];
//        int n;
//        while ((n = gunzip.read(buffer)) >= 0) {
//            out.write(buffer, 0, n);
//        }
//        // toString()使用平台默认编码，也可以显式的指定如toString("GBK")
//        return out.toString();
//    }
//
//
//    //     以流的方式处理解压的数据
//    public byte[] getZipData2(byte[] sourcesByte) {
//        Inflater decompresser = new Inflater();
//        decompresser.setInput(sourcesByte, 0, sourcesByte.length);
//        byte[] zipPostData = new byte[0];
//        try {
//            zipPostData = decompress(sourcesByte);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        decompresser.end();
//        return zipPostData;
//    }
//
//    private byte[] decompress(byte[] compress) throws Exception {
//        ByteArrayInputStream bais = new ByteArrayInputStream(compress);
//        InflaterInputStream iis = new InflaterInputStream(bais);
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//
//        int c = 0;
//        byte[] buf = new byte[1024];
//        while (true) {
//            c = iis.read(buf);
//            if (c == -1) {
//                break;
//            }
//            baos.write(buf, 0, c);
//        }
//
//        baos.flush();
//        baos.close();
//        bais.close();
//        iis.close();
//        return baos.toByteArray();
//    }
//
//    /**
//     * 压缩字符串为 byte[]
//     * 储存可以使用new sun.misc.BASE64Encoder().encodeBuffer(byte[] b)方法
//     * 保存为字符串
//     *
//     * @param str 压缩前的文本
//     * @return
//     */
//    public static final byte[] compress2(String str) {
//        if (str == null)
//            return null;
//        byte[] compressed;
//        ByteArrayOutputStream out = null;
//        ZipOutputStream zout = null;
//
//        try {
//            out = new ByteArrayOutputStream();
//            zout = new ZipOutputStream(out);
//            zout.putNextEntry(new ZipEntry("0"));
//            zout.write(str.getBytes());
//            zout.closeEntry();
//            compressed = out.toByteArray();
//        } catch (IOException e) {
//            compressed = null;
//        } finally {
//            if (zout != null) {
//                try {
//                    zout.close();
//                } catch (IOException e) {
//                }
//            }
//            if (out != null) {
//                try {
//                    out.close();
//                } catch (IOException e) {
//                }
//            }
//        }
//
//        return compressed;
//    }
//
//    /**
//     * 将压缩后的 byte[] 数据解压缩
//     *
//     * @param compressed 压缩后的 byte[] 数据
//     * @return 解压后的字符串
//     */
//    public static final String decompress2(byte[] compressed) {
//        if (compressed == null)
//            return null;
//        ByteArrayOutputStream out = null;
//        ByteArrayInputStream in = null;
//        ZipInputStream zin = null;
//        String decompressed;
//        try {
//            out = new ByteArrayOutputStream();
//            in = new ByteArrayInputStream(compressed);
//            zin = new ZipInputStream(in);
//            ZipEntry entry = zin.getNextEntry();
//            byte[] buffer = new byte[1024];
//            int offset = -1;
//            while ((offset = zin.read(buffer)) != -1) {
//                out.write(buffer, 0, offset);
//            }
//            decompressed = out.toString();
//        } catch (IOException e) {
//            decompressed = null;
//        } finally {
//            if (zin != null) {
//                try {
//                    zin.close();
//                } catch (IOException e) {
//                }
//            }
//            if (in != null) {
//                try {
//                    in.close();
//                } catch (IOException e) {
//                }
//            }
//            if (out != null) {
//                try {
//                    out.close();
//                } catch (IOException e) {
//                }
//            }
//        }
//
//        return decompressed;
//    }
//}
