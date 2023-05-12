package com.skyworth.device.controller;

import com.skyworth.device.entity.Device;
import com.skyworth.device.service.DeviceService;
import com.skyworth.web.response.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName DeviceController
 * @Description TODO
 * @Author sky
 * @Date 2023/5/11 14:51
 * @Version 1.0
 **/
@Api(tags = "设备列表")
@RestController
@RequestMapping("/device")
public class DeviceController {

    @Resource
    private DeviceService deviceService;

    @GetMapping("/list")
    public R<List<Device>> getList() {
        return R.success(deviceService.list());
    }
}
