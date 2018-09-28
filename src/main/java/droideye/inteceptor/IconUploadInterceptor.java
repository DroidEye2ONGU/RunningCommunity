package droideye.inteceptor;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IconUploadInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = true;
        //判断是否为文件上传请求
        if (request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> fileMap = multipartHttpServletRequest.getFileMap();
            Iterator<String> iterator = fileMap.keySet().iterator();

            //对多部件请求资源进行过滤
            while (iterator.hasNext()) {
                String formKey = iterator.next();
                MultipartFile multipartFile = multipartHttpServletRequest.getFile(formKey);
                String fileName = multipartFile.getOriginalFilename();
                //判断是否为限制文件类型
                if (!checkFile(fileName)) {
                    //限制文件类型,请求转发到请求原始页面,并携带错误提示信息
                    request.setAttribute("errormessage", "不支持的文件类型!");
                    request.getRequestDispatcher("/member/toCreateSpacePage").forward(request, response);
                    flag = false;
                }
            }
        }

        return flag;
    }

    /**
     * 判断是否为允许的上传文件类型,true表示允许
     */
    private boolean checkFile(String fileName) {
        //设置允许上传文件类型
        String suffixList = "jpg,gif,png,ico,bmp,jpeg";
        //获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (suffixList.contains(suffix.trim().toLowerCase())) {
            return true;
        }
        return false;
    }
}
