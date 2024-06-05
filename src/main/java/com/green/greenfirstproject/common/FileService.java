package com.green.greenfirstproject.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Objects;

@Service
@Slf4j
public class FileService {

    public String getExt(MultipartFile file) throws NullPointerException
    {
        if (file == null) throw new NullPointerException("파일이 비어 있습니다.") ;
        if (file.isEmpty()) throw new NullPointerException("파일이 비어 있습니다.") ;

        String fileName = file.getOriginalFilename();
        return Objects.requireNonNull(fileName).substring(fileName.lastIndexOf(".") + 1);
    }

    public String RandomFileNameMaker()
    {
        Date date = new Date();
        return String.valueOf(date.getTime());
    }

    public String RandomFileNameMaker(MultipartFile file)
    {
        String filename = "" ;
        try {
            filename = RandomFileNameMaker() + getExt(file) ;
        } catch (NullPointerException e) {
            filename = RandomFileNameMaker() ;
        }

        return filename ;
    }
}
