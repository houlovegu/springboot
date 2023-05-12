package com.skyworth.device.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skyworth.device.entity.Device;
import com.skyworth.device.service.DeviceService;
import com.skyworth.device.mapper.DeviceMapper;
import org.springframework.stereotype.Service;

/**
* @author sky
* @description 针对表【device(设备表)】的数据库操作Service实现
* @createDate 2023-05-11 14:49:28
*/
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device>
    implements DeviceService{

}




