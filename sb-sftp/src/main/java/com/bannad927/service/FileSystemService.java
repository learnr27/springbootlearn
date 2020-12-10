package com.bannad927.service;

import java.io.File;
import java.io.InputStream;

/**
 * @author chengbb
 * @date 2020.7.15
 */
public interface FileSystemService {

    boolean uploadFile(String targetPath, InputStream inputStream) throws Exception;

    boolean uploadFile(String targetPath, File file) throws Exception;

    File downloadFile(String targetPath) throws Exception;

    boolean deleteFile(String targetPath) throws Exception;
}
