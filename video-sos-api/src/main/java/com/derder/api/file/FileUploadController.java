package com.derder.api.file;

import com.derder.base.BaseController;
import com.derder.common.util.DateUtil;
import com.derder.common.util.ErrorCode;
import com.derder.common.util.ResultData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: zhaolei
 * Date: 16-12-15
 * Time: 下午11:20
 */
@RestController
public class FileUploadController extends BaseController {
    private final Logger log = Logger.getLogger(getClass());

    @Value("${os.file.system.path.split}")
    String PATH_SPLIT;

    @Value("${file.upload.location}")
    String UPLOAD_LOCATION;

    @RequestMapping(value="/upload", method= RequestMethod.POST)
    public @ResponseBody
    ResultData handleFileUpload(@RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.indexOf("."),fileName.length());
            String newFileName = System.currentTimeMillis() + suffix;
            String newFileDir = UPLOAD_LOCATION + datePath();
            File newDir = new File(newFileDir);
            if (!newDir.exists()){
                newDir.mkdirs();
            }
            String newFilePath = newFileDir + newFileName;
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(newFilePath)));
                stream.write(bytes);
                stream.close();
                return getResultData(true,"","","");
            } catch (Exception e) {

                return getResultData(false,"","","");
            }
        } else {
            return getResultData(false,"", ErrorCode.UPLOAD_FILE_CANNOT_EMPTY);
        }
    }

    String datePath(){
        Date now = new Date();
        String todayStr = DateUtil.formatDate(now,"yyyy-MM-dd");
        String[] ymdArr = todayStr.split("-");
        return ymdArr[0] + PATH_SPLIT + ymdArr[1] + PATH_SPLIT + ymdArr[2] + PATH_SPLIT;
    }
}
