package com.qa.portal.common.util.filemanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class QaOneDriveFileManager implements QaFileManager {

    private final Logger LOGGER = LoggerFactory.getLogger(QaOneDriveFileManager.class);

    private OneDriveRestAdapter oneDriveRestAdapter;

    private Environment environment;

    public static final String ARCHIVE_FOLDER_NAME = "archive";

    public QaOneDriveFileManager(OneDriveRestAdapter oneDriveRestAdapter,
                                 Environment environment) {
        this.oneDriveRestAdapter = oneDriveRestAdapter;
        this.environment = environment;
    }

    public void storeFile(String folderName, String fileName, String fileVersion, byte[] cvByteArray) {
        // Delete current File
        oneDriveRestAdapter.deleteFile(folderName, fileName);

        // Store new File (store it as filename)
        oneDriveRestAdapter.saveFile(folderName, fileName, cvByteArray);

        // Store archive file
        oneDriveRestAdapter.saveFile(folderName, ARCHIVE_FOLDER_NAME, fileVersion + "-" + fileName, cvByteArray);
    }
}
