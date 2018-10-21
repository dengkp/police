package com.zz.police.modules.sys.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zz.police.common.utils.UploadUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 富文本上传controller
 * @author dengkp
 */
@RestController
@RequestMapping("/editor")
public class SysEditorUploadController {

    private static final String EDITOR_IMG_UPLOAD_DIR = "editor/";

    /**
     * 上传图片
     * @param request
     * @return
     */
    @RequestMapping("/upload")
    public Map<String, Object> editorUpload(HttpServletRequest request) {
        // 大部分场景下，每次仅上传一张图片
        Map<String, Object> results = new HashMap<>(1);
        results.put("errno", 0);
        try {
            List<String> pathList = UploadUtils.uploadFile(request, EDITOR_IMG_UPLOAD_DIR);
            results.put("data", pathList);
        } catch (Exception e) {
            results.put("errno", 500);
        }
        return results;
    }

}
