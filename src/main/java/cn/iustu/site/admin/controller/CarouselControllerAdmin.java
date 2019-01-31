package cn.iustu.site.admin.controller;

import cn.iustu.site.config.constant.IUSTUConstant;
import cn.iustu.site.common.entity.Carousel;
import cn.iustu.site.common.model.Result;
import cn.iustu.site.common.util.UploadUtil;
import cn.iustu.site.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/admin/carousel")
public class CarouselControllerAdmin {

    @Autowired
    private CarouselService carouselService;

    @GetMapping("/list")
    public Result list(){
        List<Carousel> list = carouselService.list();
        return Result.success().add("carousels", list);
    }

    @PostMapping("/add")
    public Result add(Integer id, MultipartFile image, HttpServletRequest request){
        if(id == null) return Result.fail().setMsg("图片id（1-5）不能为空");
        if(!(id >= 1 && id <= 5)) return Result.fail().setMsg("图片id必须为1-5的整数");
        if(image == null) return Result.fail().setMsg("图片不能为空");

        try {
            String path = UploadUtil.uploadFile(IUSTUConstant.DOMAIN, IUSTUConstant.UPLOAD_PATH, image, request);
            Carousel carousel = new Carousel();
            carousel.setId(id);
            carousel.setImage(path);
            carouselService.update(carousel);
            return Result.success().setMsg("上传成功").add("image", path);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail().setMsg("上传失败");
        }

    }

}
