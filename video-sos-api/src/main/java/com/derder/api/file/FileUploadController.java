package com.derder.admin.file;

import com.derder.admin.BaseApiController;
import com.derder.common.util.DateUtil;
import com.derder.common.util.ErrorCode;
import com.derder.common.util.ResultData;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhaolei
 * Date: 16-12-15
 * Time: 下午11:20
 */
@RestController
public class FileUploadController extends BaseApiController {
    private final Logger log = Logger.getLogger(getClass());

    @Value("${os.file.system.path.split}")
    private String PATH_SPLIT;

    @Value("${file.upload.location}")
    private String UPLOAD_LOCATION;

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private VelocityEngine velocityEngine;

    @RequestMapping(value="/sendVideo", method= RequestMethod.POST)
    public @ResponseBody
    ResultData handleFileUpload(@RequestParam("file") MultipartFile file,
                                @RequestParam("location") String locationJson){
        try {

        }catch (Exception e){
            log.error("#####sendVideo:参数格式错误。\n"
                    + "locationJson:" +locationJson,e);
            return getResultData(false,"",ErrorCode.PARAM_FORMAT_ERROR);
        }

        if (!file.isEmpty()) {
            long userId = getUserId();
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.indexOf("."),fileName.length());
            String newFileName = userId + "_" + System.currentTimeMillis() + suffix;
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
                log.error("#####文件上传异常",e);
                return getResultData(false,"",ErrorCode.UPLOAD_FILE_EXCEPTION);
            }
        } else {
            return getResultData(false,"", ErrorCode.UPLOAD_FILE_CANNOT_EMPTY);
        }
    }

    @RequestMapping(value="/testEmail", produces = "application/json; charset=UTF-8")
    ResultData testEmail() throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("zhaolei828@163.com");
        helper.setTo("zhaolei828@163.com");
        helper.setSubject("主题：模板邮件");
        Map<String, Object> model = new HashedMap();
        model.put("username", "didi");
        String text = VelocityEngineUtils.mergeTemplateIntoString(
                velocityEngine, "template.vm", "UTF-8", model);
        helper.setText(text, true);
        FileSystemResource file = new FileSystemResource(new File("E:\\idea_workspace\\playground\\video-sos-parent\\video-sos-api\\868855d6996300000125dc94961c.jpg"));
        helper.addAttachment("附件-1.jpg", file);
        helper.addAttachment("附件-2.jpg", file);
        mailSender.send(mimeMessage);
        return getResultData(true,"","","");
    }

    String datePath(){
        Date now = new Date();
        String todayStr = DateUtil.formatDate(now,"yyyy-MM-dd");
        String[] ymdArr = todayStr.split("-");
        return ymdArr[0] + PATH_SPLIT + ymdArr[1] + PATH_SPLIT + ymdArr[2] + PATH_SPLIT;
    }
}
