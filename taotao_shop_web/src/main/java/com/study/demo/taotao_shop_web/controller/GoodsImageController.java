package com.study.demo.taotao_shop_web.controller;

import com.study.demo.taotao_common.common.BusinessException;
import com.study.demo.taotao_common.common.CommonReturnType;
import com.study.demo.taotao_interface.service.GoodsImageService;
import com.study.demo.taotao_pojo.params.BaseLongId;
import com.study.demo.taotao_pojo.pojo.TbGoodsImage;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/goodsImageController")
@PropertySource("classpath:ImageServie.properties")
public class GoodsImageController {

    @Value("${IMAGE_URL_IP}")
    private String imageUrlIp;
    @Reference
    private GoodsImageService service;

    @RequestMapping(value = "/uploadeImage",method = RequestMethod.POST)
    public CommonReturnType uploadeImage(@RequestParam("file") MultipartFile multipartFile,@RequestParam("goodsId")Long id,
                                         @RequestParam("color")String color){
        String filename = multipartFile.getOriginalFilename();
        if (filename == null) {
            return CommonReturnType.create("","文件接受失败!!!");
        }
        String extName = filename.substring(filename.lastIndexOf(".") + 1);
        try {
            String filePath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();
            ClientGlobal.init(filePath);
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
            StorageClient storageClient = new StorageClient(trackerServer,storageServer);
            String[] imageUrl = storageClient.upload_file(multipartFile.getBytes(), extName, null);
            StringBuilder builder = new StringBuilder();
            for (String url :imageUrl) {
                if(url.contains("group")){
                    url+="/";
                }
                builder.append(url);
            }
            TbGoodsImage goodsImage = new TbGoodsImage();
            goodsImage.setGoodsId(id);
            goodsImage.setColor(color);
            goodsImage.setUrl(imageUrlIp+builder.toString());
            boolean insertStatus = service.insertGoodsIamgeUrl(goodsImage);
            if (insertStatus){
                return CommonReturnType.create(imageUrlIp+builder.toString(),"保存图片URL成功!!!");
            }else {
                return CommonReturnType.create("","保存图片Url失败!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return CommonReturnType.create(e.getMessage());
        }
    }

    @RequestMapping(value = "/selectGoodsImageByGoodsId",method = RequestMethod.POST,consumes = "application/json")
    public CommonReturnType selectGoodsImageByGoodsId(@RequestBody BaseLongId id) throws BusinessException {
        List<TbGoodsImage> goodsImages = service.selectGoodsImageByGoodsId(id);
        if (goodsImages==null){
            return CommonReturnType.create("","数据为空!!!");
        }else {
            return CommonReturnType.create(goodsImages);
        }
    }


    @RequestMapping(value = "/uploadeContentImage",method = RequestMethod.POST)
    public CommonReturnType uploadeImage(@RequestParam("file") MultipartFile multipartFile){
        String filename = multipartFile.getOriginalFilename();
        if (filename == null) {
            return CommonReturnType.create("","文件接受失败!!!");
        }
        String extName = filename.substring(filename.lastIndexOf(".") + 1);
        try {
            String filePath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();
            ClientGlobal.init(filePath);
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
            StorageClient storageClient = new StorageClient(trackerServer,storageServer);
            String[] imageUrl = storageClient.upload_file(multipartFile.getBytes(), extName, null);
            StringBuilder builder = new StringBuilder();
            for (String url :imageUrl) {
                if(url.contains("group")){
                    url+="/";
                }
                builder.append(url);
            }
            if (!StringUtils.isEmpty(builder.toString())){
                return CommonReturnType.create(imageUrlIp+builder.toString());
            }else {
                return CommonReturnType.create("","保存图片Url失败!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return CommonReturnType.create(e.getMessage());
        }
    }
}