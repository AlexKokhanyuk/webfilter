package ua.kohaniuk.webfilter.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kohaniuk.webfilter.filters.WebFilter;

/**
 * @author Oleksandr Kokhaniuk
 * @created 5/11/2022, 6:39 AM
 */
@Component
public class BlackListFileReader {
    private Long lastModified = 0L;
    private Set<String> setOfIP = new HashSet<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(WebFilter.class);

    private Environment env;
    private String fileName;
    private String fileNameDep;

    @Autowired
    public BlackListFileReader(Environment env) {
        this.env = env;
        this.fileName = env.getProperty("blackListFilePath");
        this.fileNameDep = env.getProperty("blackListFilePathDep");
    }

    public BlackListFileReader(String fileName, String fileNameDep) {
        this.fileName = fileName;
        this.fileNameDep = fileNameDep;
    }

    @Scheduled(fixedDelay = 1000)
    public void toRead() {

        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file = new File(fileNameDep);
                if (!file.exists()) {
                    LOGGER.warn("Blacklist file not found");
                }

            }
            if (!(file.lastModified() == lastModified)) {
                setOfIP.clear();
                lastModified = file.lastModified();
                FileReader fr = new FileReader(file);
                BufferedReader reader = new BufferedReader(fr);
                String line = reader.readLine();
                while (line != null) {
                    setOfIP.add(line);
                    line = reader.readLine();
                }
                fr.close();
            }

        } catch (FileNotFoundException e) {
            LOGGER.warn("File not found: ", e);
        } catch (IOException e) {
            LOGGER.warn("IO Exception: ", e);
        }
    }

    public Set<String> getSetOfIP() {
        return setOfIP;
    }


}
