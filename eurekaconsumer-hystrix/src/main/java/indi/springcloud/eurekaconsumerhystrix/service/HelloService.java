package indi.springcloud.eurekaconsumerhystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年03月30日 10时29分
 */
@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;


    public String hiError(String name) {
        return "错误,断路器回复: hi " + name;
    }


    /**
     * 断路器配置，当无法调用如下方法时，就会调用自定的hiError方法。
     */
    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name) {
        return restTemplate.getForObject("http://SERVICE-HI/hi?name=" + name, String.class);
    }
}