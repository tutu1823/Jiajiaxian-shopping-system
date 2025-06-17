package com.tyl.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.tyl.dao.TukuDao;
import com.tyl.model.TuKu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tuku")
public class TuKuApi {

    @Value("${wep.uploadPath}")
    private String baseUploadPath;

    @Autowired
    private TukuDao tukuDao;

    //增删改查通过接口即可调用不同功能
    @PostMapping
    public Integer create(@RequestBody TuKu tuKu){
        String name = tuKu.getName();
        String base64 = tuKu.getBase64();
        String[] dataArray = StrUtil.splitToArray(base64,"base64,");//dataArray[0]放base64,及之前的东西，dataArray[1]放之后的码，即真正需要的图片的base64码
        byte[] bytes = Base64.decode(dataArray[1]);//将图片base64码转成二进制码

        String newName = IdUtil.fastSimpleUUID() +"_"+ name;
        FileUtil.writeBytes(bytes,baseUploadPath+newName);

        tuKu.setImg("http://127.0.0.1:8081/images/"+newName);
        return tukuDao.create(tuKu);
    }

    @DeleteMapping  //用 /api/tuku?id=4
    public Integer delete(@RequestParam Integer id){

        return tukuDao.delete(id);
    }

    @PutMapping
    public Integer update(@RequestBody TuKu tuKu){

        return tukuDao.update(tuKu);
    }

    @GetMapping
    public List<TuKu> select(){

        return tukuDao.select();
    }

}
